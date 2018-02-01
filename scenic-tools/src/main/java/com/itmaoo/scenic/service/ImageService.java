package com.itmaoo.scenic.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.ObjectMetadata;

/**
 * 
 * @author mario
 *
 */
public class ImageService {

	private static final String ACCESS_ID = "LTAIccZyGlS1msbo";
	private static final String ACCESS_KEY = "rQAJtg2InCnX5Sv3t2GKVxoXrrCUR7";
	private static final String OSS_ENDPOINT = "http://oss-cn-shanghai.aliyuncs.com";
	private String bucketName = "iukiss";

	public void saveOssImage(MultipartFile file, String key) throws OSSException, ClientException, IOException {
		// 可以使用ClientConfiguration对象设置代理服务器、最大重试次数等参数。
		ClientConfiguration config = new ClientConfiguration();
		OSSClient client = new OSSClient(OSS_ENDPOINT, ACCESS_ID, ACCESS_KEY, config);
		uploadFile(client, bucketName, key, file);
	}

	// 上传文件
	private static void uploadFile(OSSClient client, String bucketName, String key, MultipartFile file)
			throws OSSException, ClientException, IOException {
		ObjectMetadata objectMeta = new ObjectMetadata();
		objectMeta.setContentLength(file.getSize());
		// 可以在metadata中标记文件类型
		objectMeta.setContentType("image/jpeg");
		client.putObject(bucketName, key, file.getInputStream(), objectMeta);

	}

}
