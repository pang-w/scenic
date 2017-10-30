package com.itmaoo.scenic.action.user;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSException;
import com.itmaoo.scenic.action.base.BaseActiom;
import com.itmaoo.scenic.dao.IArticleDao;
import com.itmaoo.scenic.dao.IImageDao;
import com.itmaoo.scenic.entity.dto.Artilcle;
import com.itmaoo.scenic.entity.dto.ResponseData;
import com.itmaoo.scenic.entity.dto.SavedImage;
import com.itmaoo.scenic.entity.po.ArticlePo;
import com.itmaoo.scenic.entity.po.ImagePo;
import com.itmaoo.scenic.entity.po.UserPo;
import com.itmaoo.scenic.service.ImageService;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Controller
@RequestMapping(value = "/action/user/article/")
public class AritcleUserAction extends BaseActiom{
	@Autowired
	private IImageDao iamgeDao;
	@Autowired
	private IArticleDao articleDao;
	
	private String imagePrefixUrl = "http://img.iukiss.com/";
	
	private ImageService imageService = new ImageService();

	@RequestMapping("saveImg")
	@ResponseBody
	public ResponseData saveImg(HttpServletResponse request, @RequestParam("description") String desc, @RequestParam("file") MultipartFile file) {

		ResponseData rd = new ResponseData();
		SavedImage si = new SavedImage();
		String baseNum = UUID.randomUUID().toString().replaceAll("-", "");
		String ofn = file.getOriginalFilename().toLowerCase();
		String imageUri = null;
		if(ofn.lastIndexOf(".")!=-1 && (ofn.endsWith("jpg")||ofn.endsWith("png"))){
			String  imageSuffix = ofn.substring(ofn.lastIndexOf(".")+1,ofn.length());
			imageUri =  "img/user/"+ofn;
			if(!imagePrefixUrl.endsWith("/")){
				imagePrefixUrl = imagePrefixUrl +"/";
			}
			si.setUrl(imagePrefixUrl + imageUri);
			
			UserPo user = getLogedUser();
			ImagePo image = new ImagePo();
			image.setBaseNum(baseNum);
			image.setBaseUri(imageUri);
			image.setCreateDate(new Date());
			image.setDesc(desc);
			image.setLinkTo("");
			image.setType(imageSuffix);
			image.setUserId(user.getUserid());
			iamgeDao.insert(image);
		}else{
			rd.setStatus("0001");
			rd.setMsg("仅支持jpg和png格式图片");
		}
		
		try {
			imageService.saveOssImage(file, imageUri);
		} catch (OSSException e) {
			rd.setStatus("0002");
			rd.setMsg("保存图片失败 [OSSException]");
			e.printStackTrace();
		} catch (ClientException e) {
			rd.setStatus("0003");
			rd.setMsg("保存图片失败 [ClientException]");
			e.printStackTrace();
		} catch (IOException e) {
			rd.setStatus("0004");
			rd.setMsg("保存图片失败 [IOException]");
			e.printStackTrace();
		}
		rd.setData(si);
		return rd;

	}

	@RequestMapping("addArticle")
	public String addArticle(@RequestBody Artilcle article) {
		ArticlePo entity = new ArticlePo();
		entity.setContent(checkTextDanger(article.getContent()));
		entity.setLastModifyDate(new Date());
		//entity.setUuid(article.getUuid());
		
		// entity.setContent("wer");
		articleDao.insert(entity);

		try {
			// 创建一个合适的Configration对象
			Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);

			configuration.setDirectoryForTemplateLoading(
					new File("src/main/resources/templates"));

			// configuration.setDirectoryForTemplateLoading(new
			// File("/template"));
			configuration.setDefaultEncoding("UTF-8"); // 这个一定要设置，不然在生成的页面中 会乱码
			// 获取或创建一个模版。
			Template template = configuration.getTemplate("iukiss/article.ftl");

			Writer writer = new OutputStreamWriter(new FileOutputStream("src/main/webapp/article/a.html"), "UTF-8");
			Map<String, Object> commonData = new HashMap<String, Object>();

			commonData.put("content", entity.getContent());

			template.process(commonData, writer);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "User";

	}

	public Map<String, Object> getCommonData() {
		Map<String, Object> commonData = new HashMap<String, Object>();

		commonData.put("content", "http://192.168.1.137:8080");
		return commonData;
	}

	public String checkTextDanger(String checkText) {
		String newText = checkText.trim(); // 去掉头尾空格
		newText = newText.replace("\n", "<br>");
		newText = newText.replace("\t", "&nbsp&nbsp&nbsp&nbsp");
		// textBox里的换行是用\n来表示的，如果要在HTML里显示换行要用<br>
		// newText = newText.replace("<", "&lt"); //置换 <
		// newText = newText.replace(">", "&gt");
		// newText = newText.replace(".", "。");//置换 >
		// newText = newText.replace("'", "''");
		// 如果是用存储过程存储数据，这行不用加，如果你用的是SQL语句来存数据，这行要加上，功能为置换 ‘
		return newText;
	}
}
