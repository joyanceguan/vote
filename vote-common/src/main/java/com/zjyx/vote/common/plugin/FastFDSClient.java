package com.zjyx.vote.common.plugin;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.ProtoCommon;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;

public class FastFDSClient implements FactoryBean<StorageClient>, InitializingBean, DisposableBean{

	private StorageClient client;
	
	@Value("${saveRealBasePath}")
	private String saveRealBasePath;
	
	@Value("${webBasePath}")
	private String webBasePath;
	
	private static final String PIC_SUFFIX = "jpg";
	
	@Override
	public void destroy() throws Exception {
		
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		 ClientGlobal.init("config.properties");
	     // 1、创建一个TrackerClient对象。
	     TrackerClient trackerClient = new TrackerClient();
	     // 2、创建一个TrackerServer对象。
	     TrackerServer trackerServer = trackerClient.getConnection();
	     // 3、声明一个StorageServer对象，null。
	     StorageServer storageServer = null;
	     // 4、获得StorageClient对象。
	     client = new StorageClient(trackerServer, storageServer);
	     ProtoCommon.activeTest(trackerServer.getSocket());
	}

	@Override
	public StorageClient getObject() throws Exception {
		return client;
	}

	@Override
	public Class<?> getObjectType() {
		return StorageClient.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

	/*
	 * 上传文件
	 */
	public String[] uploadFile(String filePath) throws IOException, MyException{
		filePath = saveRealBasePath+filePath;
		//直接调用StorageClient对象方法上传文件即可。
		String[] values = client.upload_file(filePath,PIC_SUFFIX, null);
        return values;
	}
	
	/*
	 * 上传文件
	 */
	public String[] uploadFile(String group_name, String master_filename, String prefix_name,
            String local_filename) throws IOException, MyException{
		//直接调用StorageClient对象方法上传文件即可。
		String[] values = client.upload_file(group_name, master_filename, prefix_name, local_filename, PIC_SUFFIX, null);
        return values;
	}
	
	@SuppressWarnings("resource")
	public String[] uploadGroupFile(String groupName,String filePath) throws IOException, MyException{
	    FileInputStream file = new FileInputStream(filePath);
	    List<Byte> list = new LinkedList<>();
	    byte[] bytes = new byte[1024];
	    while(file.read(bytes)!=-1){
	    	for(byte b:bytes){
	    		list.add(b);
	    	}
	    }
	    if(list!=null && list.size()>0){
	    	int index = 0;
	    	byte[] array = new byte[list.size()];
	    	for(byte b:list){
	    		array[index++]=b;
	    	}
	    	String[] values=client.upload_file(groupName, array, PIC_SUFFIX, null);
	    	return values;
	    }
	    return null;
	}
}
