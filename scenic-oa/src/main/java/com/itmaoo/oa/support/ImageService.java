package com.itmaoo.oa.support;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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

	public void saveOssExcel(HSSFWorkbook file, String key) throws OSSException, ClientException, IOException {
		// 可以使用ClientConfiguration对象设置代理服务器、最大重试次数等参数。
		ClientConfiguration config = new ClientConfiguration();
		OSSClient client = new OSSClient(OSS_ENDPOINT, ACCESS_ID, ACCESS_KEY, config);
		uploadFile(client, bucketName, key, file);
	}

	// 上传文件
	private static void uploadFile(OSSClient client, String bucketName, String key, HSSFWorkbook workbook)
			throws OSSException, ClientException, IOException {
	  ByteArrayOutputStream os = new ByteArrayOutputStream();
    try {
        workbook.write(os);
    } catch (IOException e) {
        e.printStackTrace();
    }

    byte[] content = os.toByteArray();
    InputStream is = new ByteArrayInputStream(content);
    
		ObjectMetadata objectMeta = new ObjectMetadata();
		// 可以在metadata中标记文件类型
		objectMeta.setContentType("application/octet-stream");
		client.putObject(bucketName, key, is, objectMeta);

	}

}
