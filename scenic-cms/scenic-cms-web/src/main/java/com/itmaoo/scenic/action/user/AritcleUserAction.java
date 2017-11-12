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
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
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
import com.google.common.collect.Lists;
import com.itmaoo.scenic.action.base.BaseActiom;
import com.itmaoo.scenic.dao.IArticleDao;
import com.itmaoo.scenic.dao.IImageDao;
import com.itmaoo.scenic.entity.dto.ArticleDto;
import com.itmaoo.scenic.entity.dto.ResponseData;
import com.itmaoo.scenic.entity.dto.SavedImage;
import com.itmaoo.scenic.entity.dto.UserDto;
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
	public ResponseData saveImg(HttpServletRequest request, @RequestParam("description") String desc, @RequestParam("file") MultipartFile file) {

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
			
			UserDto user = getLogedUser(request);
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
	@RequestMapping("menuList")
	@ResponseBody
	public ResponseData menuList(HttpServletRequest request) {
		ResponseData rd = new ResponseData();
		if(getLogedUser(request)==null){
			rd.setMsg("未登录");
			rd.setStatus("4000");
		}else{
			List<ArticleDto> aticleDtos = Lists.newArrayList();
			ArticleDto dto = new ArticleDto();
			dto.setTitle("阿斯顿分类阿斯顿非埃及哦；jli");
			dto.setDescription("asdfas");
			dto.setContent("asdfas");
			dto.setUuid("asdf");
			aticleDtos.add(dto);
			aticleDtos.add(dto);
			aticleDtos.add(dto);
			rd.setData(aticleDtos);
		}
		return rd;        

	}
}
