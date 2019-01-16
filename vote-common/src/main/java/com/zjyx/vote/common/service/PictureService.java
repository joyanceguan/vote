package com.zjyx.vote.common.service;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.csource.common.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.zjyx.vote.common.constants.VoteConstants;
import com.zjyx.vote.common.enums.Error_Type;
import com.zjyx.vote.common.model.PictureDto;
import com.zjyx.vote.common.model.ThumbnailatorDto;
import com.zjyx.vote.common.plugin.FastFDSClient;
import com.zjyx.vote.common.utils.PictureUtils;
import com.zjyx.vote.common.utils.StringUtils;


public class PictureService {
	
	@Value("${saveRealBasePath}")
	private String saveRealBasePath;

	@Value("${webBasePath}")
	private String webBasePath;

	@Value("${imageContextPath}")
	private String imageContextPath;

	@Value("${fastFDSContextPath}")
	private String fastFDSContextPath;

	@Value("${watermarker}")
	private String watermarker;

	@Value("${imageContextPath}${webBasePath}")
	private String webContextPath;
	
	@Autowired
	FastFDSClient fastFDSClient;

	private static String zipPath = "/p_";// 压缩图存放位置

	private static String tailor = "/t_h_";// 裁剪高度为x的图片

	private static String wartermark = "/wm_";// 裁剪高度为200的图片路径
	
	private Log log = LogFactory.getLog(PictureService.class);

	public String getImageContextPath() {
		return imageContextPath;
	}

	public List<PictureDto> uploadPic(HttpServletRequest request, String imagePath, String fileName, ThumbnailatorDto thumbnailator) {
		List<PictureDto> list = new ArrayList<PictureDto>();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		List<MultipartFile> files = multipartRequest.getFiles(fileName);
		for (MultipartFile file : files) {
			PictureDto pdto = uploadFile(file, thumbnailator, imagePath);
			list.add(pdto);
		}
		return list;
	}

	private PictureDto uploadFile(MultipartFile file, ThumbnailatorDto thumbnailator, String imagePath) {
		PictureDto dto = new PictureDto();
		try {
			InputStream inputStream = file.getInputStream();
			if (file.isEmpty() || file.getSize() == 0 || inputStream == null || StringUtils.isEmpty(imagePath) || !file.getContentType().contains("image")) {
				dto.setErrorInfo(Error_Type.PARAM_ERROR ,null, "请传入图片");
				return dto;
			}
			if (file.getSize() > VoteConstants.MAX_FILE_SIZE * 1024 * 1024) {
				dto.setErrorInfo(Error_Type.PARAM_ERROR ,null, file.getName() + "超过" + VoteConstants.MAX_FILE_SIZE + "M");
				return dto;
			}
			dto.setOriginName(getOriginFileName(file.getOriginalFilename()));
			String imageName = PictureUtils.generateImageName();
			String saveImagePath = PictureUtils.getImageSavePath(saveRealBasePath, imagePath, imageName);
			int flag;
			int buffSize = 1024 * 512;
			byte[] buff = new byte[buffSize];
			File outputfile = new File(saveImagePath);
			outputfile.createNewFile();
			FileOutputStream outputStream = new FileOutputStream(outputfile, true);
			int index = 0;
			while ((flag = inputStream.read(buff)) != -1) {
				outputStream.write(buff);
				index++;
			}
			inputStream.close();
			outputStream.close();
			if (thumbnailator.isAdapt()) {
				thumbnailator.calScale(index * buffSize);
			}
			String imageUrl = PictureUtils.getImageAccessPath(webBasePath, imagePath, imageName);
			dto.setImageUrl(imageUrl);
			thumbnailator(thumbnailator,dto,saveImagePath,imagePath,imageName,false);
		} catch (Exception e) {
			log.error("uploadFile exception",e);
			dto.setErrorInfo(Error_Type.SYSTEM_ERROR,null,null);
		}
		return dto;
	}
	
	private PictureDto uploadFileBase64(String base64, ThumbnailatorDto thumbnailator, String imagePath) {
		PictureDto dto = new PictureDto();
		try {
			if (base64 == null || base64.length() < 100) {
				dto.setErrorInfo(Error_Type.PARAM_ERROR,null,"请传入图片");
				return dto;
			}
			byte[] file = decodeBase64(base64.substring(base64.indexOf(",")+1));
			if (file.length > VoteConstants.MAX_FILE_SIZE * 1024 * 1024) {
				dto.setErrorInfo(Error_Type.PARAM_ERROR,null, "图片大小不能超过" + VoteConstants.MAX_FILE_SIZE + "M");
				return dto;
			}
			dto.setOriginName(getOriginFileName("zidingy.jap"));
			String imageName = PictureUtils.generateImageName();
			String saveImagePath = PictureUtils.getImageSavePath(saveRealBasePath, imagePath, imageName);
			File outputfile = new File(saveImagePath);
			outputfile.createNewFile();
			FileOutputStream outputStream = new FileOutputStream(outputfile, true);
			outputStream.write(file);
			outputStream.close();
			String imageUrl = PictureUtils.getImageAccessPath(webBasePath, imagePath, imageName);
			dto.setImageUrl(imageUrl);
			thumbnailator(thumbnailator,dto,saveImagePath,imagePath,imageName,false);
		} catch (Exception e) {
			log.error("uploadFileBase64 exception",e);
			dto.setErrorInfo(Error_Type.SYSTEM_ERROR,null,null);
		}
		return dto;
	}

	private void thumbnailator(ThumbnailatorDto thumbnailator,PictureDto dto,String saveImagePath,String imagePath,String imageName,boolean isWriteToFast) throws Exception{
		if (thumbnailator != null) {
			thumbnailator.setFromPath(saveImagePath);
			if (thumbnailator.getPositions() != null) {
				thumbnailator.setWatermark(watermarker);
				String p1ImagePath = PictureUtils.getImageSavePath(saveRealBasePath, imagePath + wartermark, imageName);// 1:1压缩图存放位置
				thumbnailator.setToPath(p1ImagePath);
				String wmImageUrl = PictureUtils.thumbnailatorImage(thumbnailator);
				if(isWriteToFast){
					wmImageUrl = writeToFast(wmImageUrl);
				}else{
					wmImageUrl = webBasePath + wmImageUrl.replaceAll(saveRealBasePath, "");
				}
				dto.setWatermarkImageUrl(wmImageUrl);
			} else if (thumbnailator.getHeight() != 0 && thumbnailator.getWidth() != 0) {
				String tailorImagePath = PictureUtils.getImageSavePath(saveRealBasePath, imagePath + tailor + thumbnailator.getHeight(), imageName);// 裁剪图片位置
				thumbnailator.setToPath(tailorImagePath);
				String tailorImageUrl = PictureUtils.thumbnailatorImage(thumbnailator);
				if(isWriteToFast){
					tailorImageUrl = writeToFast(tailorImageUrl);
				}else{
					tailorImageUrl = webBasePath + tailorImageUrl.replaceAll(saveRealBasePath, "");
				}
				dto.setTailorImageUrl(tailorImageUrl);
			} else {
				if (thumbnailator.getScale() == 0) {
					thumbnailator.setScale(1f);
				}
				String p1ImagePath = PictureUtils.getImageSavePath(saveRealBasePath, imagePath + zipPath + (int) (thumbnailator.getScale() * 100), imageName);// 1:1压缩图存放位置
				thumbnailator.setToPath(p1ImagePath);
				String minImageUrl = PictureUtils.thumbnailatorImage(thumbnailator);
				if(isWriteToFast){
					minImageUrl = writeToFast(minImageUrl);
				}else{
					minImageUrl = webBasePath + minImageUrl.replaceAll(saveRealBasePath, "");
				}
				dto.setMinImageUrl(minImageUrl);
			}
		}
	}
	
	private String writeToFast(String filePath) throws IOException, MyException{
		String path = null;
		String[] values = fastFDSClient.uploadGroupFile("group2", filePath);
		if(values==null||values.length!=2){
		}else{
			path = StringUtils.jointString("/",values[0],"/",values[1]);
		}
		return path;
	}
	
	
	private String getOriginFileName(String fileName) {
		int pos = fileName.lastIndexOf(".");
		return fileName.substring(0, pos);
	}

	public String getWebContextPath() {
		return webContextPath;
	}

	public List<PictureDto> uploadPicBase64(String base64, String imagePath, String fileName, ThumbnailatorDto thumbnailator) {
		List<PictureDto> list = new ArrayList<PictureDto>();
		PictureDto pdto = uploadFileBase64(base64, thumbnailator, imagePath);
		list.add(pdto);
		return list;
	}
	
	public static byte[] decodeBase64(String input) throws Exception{  
        Class clazz=Class.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");  
        Method mainMethod= clazz.getMethod("decode", String.class);  
        mainMethod.setAccessible(true);  
        Object retObj=mainMethod.invoke(null, input);  
        return (byte[])retObj;  
    }

	public String getFastFDSContextPath() {
		return fastFDSContextPath;
	} 
	
}
