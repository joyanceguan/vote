package com.zjyx.vote.common.utils;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import com.zjyx.vote.common.model.ThumbnailatorDto;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;


public class PictureUtils {

	public final static String URL_SEPERATOR = "/";

	public static final String SYSTEM_FILE_SEPERATOR = System.getProperty("file.separator");

	public static final String SUFFIX = ".jpg";
	

	/**
	 * 日期+6位随机数
	 */
	public static String generateImageName() {
		String str = DateUtils.parseStringFromDate(new Date(), "yyyyMMddhhmmss");
		String random = getRandomNumber();
		return StringUtils.jointString(str, random);
	}

	public static String getRandomNumber() {
		Random random = new Random();
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < 6; i++) {
			str.append(random.nextInt(10));
		}
		return str.toString();
	}

	private static void initDir(File file) {
		if (!file.exists()) {
			file.mkdir();
		}
	}

	public static String getPath(String imagePath, String basePath, String seperator, boolean isNeedInitDir) {
		if (basePath.endsWith("/")) {
			basePath = basePath.substring(0, basePath.length() - 1);
		}
		String[] paths = imagePath.split("/");
		for (String path : paths) {
			basePath = StringUtils.jointString(basePath, seperator, path);
			if (isNeedInitDir)
				initDir(new File(basePath));
		}
		return basePath;
	}

	public static String getImageSavePath(String saveRealBasePath, String imagePath, String imageName) {
		// saveRealBasePath=系统路径+存放图片文件夹名称
		String path = getPath(imagePath, saveRealBasePath, SYSTEM_FILE_SEPERATOR, true);
		String imageSavePath = StringUtils.jointString(path, SYSTEM_FILE_SEPERATOR, imageName, SUFFIX);
		System.out.println("imageSavePath=" + imageSavePath);
		return imageSavePath;
	}

	public static String getImageAccessPath(String basePath, String imagePath, String imageName) {
		// basePath=存放图片文件夹名称
		String path = getPath(imagePath, basePath, URL_SEPERATOR, false);
		String imageAccessPath = StringUtils.jointString(path, URL_SEPERATOR, imageName, SUFFIX);
		System.out.println("imageAccessPath=" + imageAccessPath);
		return imageAccessPath;
	}

	public static String thumbnailatorImage(String maxfilePath, String minFilePath, float scale)throws IOException {
		Thumbnails.of(maxfilePath).scale(scale).toFile(minFilePath);
		String minHttpPath = minFilePath;
		return minHttpPath;
	}
	
	public static String thumbnailatorImage(String maxfilePath, String tailorFilePath,int height,int width)throws IOException {
		Thumbnails.of(maxfilePath).size(width, height).toFile(tailorFilePath);
		return tailorFilePath;
	}
	
	public static String thumbnailatorImage(ThumbnailatorDto thumbnailator) throws IOException{
		if(thumbnailator==null)
			return null;
		String fromPath=thumbnailator.getFromPath();
		String toPath=thumbnailator.getToPath();
		int height=thumbnailator.getHeight();
		int width=thumbnailator.getWidth();
		float scale=thumbnailator.getScale();
		String watermark=thumbnailator.getWatermark();
		Positions positions=thumbnailator.getPositions();
		
		if(StringUtils.isBlank(fromPath) || StringUtils.isBlank(toPath))
			return null;
		
		//如果需要水印
		if(StringUtils.isNotBlank(watermark) && positions!=null){
			if(width!=0 && height!=0){
				Thumbnails.of(fromPath).size(width, height).watermark(positions,ImageIO.read(new File(watermark)),0.3f).toFile(toPath); 
			}else{
				Thumbnails.of(fromPath).scale(1f).watermark(positions,ImageIO.read(new File(watermark)),0.3f).toFile(toPath); 
			}
		}
		//根据宽高缩放
		else if(width!=0 && height!=0){
			Thumbnails.of(fromPath).size(width, height).toFile(toPath);
		}
		//如果按比例缩放(默认1：1)
		else{
			Thumbnails.of(fromPath).scale(scale!=0?scale:1f).toFile(toPath);
		}
	    return toPath;
	}
	
	public static void main(String[] args) throws IOException {
//		PictureUtils.thumbnailatorImage("/Users/user/Documents/pic/led/f02.jpg", "/Users/user/Documents/pic/led/f00201.jpg", 0.1f);
	}
	
	public static List<String> getImgStr(String htmlStr) {
		List<String> pics = new ArrayList<>();
        String img = "";
        Pattern p_image;
        Matcher m_image;
        //     String regEx_img = "<img.*src=(.*?)[^>]*?>"; //图片链接地址
        String regEx_img = "<img.*src\\s*=\\s*(.*?)[^>]*?>";
        p_image = Pattern.compile
                (regEx_img, Pattern.CASE_INSENSITIVE);
        m_image = p_image.matcher(htmlStr);
        while (m_image.find()) {
            // 得到<img />数据
            img = m_image.group();
            // 匹配<img>中的src数据
            Matcher m = Pattern.compile("src\\s*=\\s*\"?(.*?)(\"|>|\\s+)").matcher(img);
            while (m.find()) {
                pics.add(m.group(1));
            }
        }
        return pics;
    }
	
	public static String replaceHtml(List<String> pics,String originHtml,String regex){ 
		//将文章图片替换为占位符
        if(pics.size()>0){
        	for(int i=0;i<pics.size();i++){
        		String pic = pics.get(i);
        		originHtml = originHtml.replaceFirst(pic, "{"+i+"}");
        		pics.set(i, pic.split(regex)[1]);
        	}
        }
        return originHtml;
	}
	
	public static String render(String[] pics,String content,String accessBasePath){
		if(pics == null || pics.length == 0){
			return content;
		}else{
			for(int i=0;i<pics.length;i++){
				StringBuilder str = new StringBuilder();
				if(StringUtils.isNotBlank(accessBasePath)){
					str.append(accessBasePath);
				}
				str.append(pics[i]);
				pics[i]=str.toString();
			}
			return MessageFormat.format(content, pics);
		}
	}
}
