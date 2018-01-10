package com.itmaoo.scenic.action.base;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSException;
import com.itmaoo.scenic.dao.IImageDao;
import com.itmaoo.scenic.entity.dto.ResponseData;
import com.itmaoo.scenic.entity.dto.SavedImage;
import com.itmaoo.scenic.entity.dto.UserDto;
import com.itmaoo.scenic.entity.po.ImagePo;
import com.itmaoo.scenic.entity.po.UserPo;
import com.itmaoo.scenic.entity.query.ImageQuery;
import com.itmaoo.scenic.service.ImageService;

@Controller
@RequestMapping(value = "/action/image/")
public class ImageAction extends BaseAction {
	@Autowired
	private IImageDao imageDao;

	private String imagePrefixUrl = "http://img.iukiss.com/";

	private ImageService imageService = new ImageService();

	@RequestMapping("saveImg")
	@ResponseBody
	public ResponseData saveImg(HttpServletRequest request, @RequestParam("description") String desc,
			@RequestParam("file") MultipartFile file) {

		ResponseData rd = new ResponseData();
		SavedImage si = new SavedImage();
		String baseNum = UUID.randomUUID().toString().replaceAll("-", "");
		String ofn = file.getOriginalFilename().toLowerCase();
		String imageUri = null;
		UserDto user = getLogedUser(request);
		if (user == null) {
			rd.setMsg("未登录");
			rd.setStatus("4000");
		} else {
			if (ofn.lastIndexOf(".") != -1 && (ofn.endsWith("jpg") || ofn.endsWith("png"))) {

				String imageSuffix = ofn.substring(ofn.lastIndexOf(".") + 1, ofn.length());
				imageUri = "img/user/" + user.getUsername().toLowerCase() + "/" + ofn;
				if (!imagePrefixUrl.endsWith("/")) {
					imagePrefixUrl = imagePrefixUrl + "/";
				}
				si.setUrl(imagePrefixUrl + imageUri);
				ImageQuery imageQuery = new ImageQuery();
				imageQuery.setImagename(ofn);
				imageQuery.setUsername(user.getUsername());
				ImagePo imagePo = imageDao.selectSingleByUserAndImageName(imageQuery);
				ImagePo image = new ImagePo();
				image.setBaseNum(baseNum);
				image.setBaseUri(imageUri);
				image.setCreateDate(new Date());
				image.setDesc(desc);
				image.setLinkTo("");
				image.setType(imageSuffix);
				image.setUsername(user.getUsername());
				image.setImagename(ofn);
				if(imagePo==null){
					imageDao.insert(image);
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
				}else if(imagePo.getUsername().equals(user.getUsername())){
					imageDao.updateByImageName(image);
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
				}else{
					rd.setStatus("0001");
					rd.setMsg("无权限");
				}
			} else {
				rd.setStatus("0001");
				rd.setMsg("仅支持jpg和png格式图片");
			}
		}
		rd.setData(si);
		return rd;

	}
	/**
	 * 
	 * @param request
	 * @param type ALY WX
	 * @param file
	 * @return
	 */
	@RequestMapping("savePayImg")
	@ResponseBody
	public ResponseData savePayImg(HttpServletRequest request, @RequestParam("type") String type,	@RequestParam("file") MultipartFile file) {

		ResponseData rd = new ResponseData();
		SavedImage si = new SavedImage();
		String ofn = file.getOriginalFilename().toLowerCase();
		String imageUri = null;
		UserDto user = getLogedUser(request);
		if (user == null) {
			rd.setMsg("未登录");
			rd.setStatus("4000");
		} else {
			if (ofn.lastIndexOf(".") != -1 && (ofn.endsWith("jpg") || ofn.endsWith("png"))) {

				String imageSuffix = ofn.substring(ofn.lastIndexOf(".") + 1, ofn.length());
				imageUri = "img/user/" + user.getUsername().toLowerCase() + "/pay/" + type.toLowerCase()+"."+imageSuffix;
				if (!imagePrefixUrl.endsWith("/")) {
					imagePrefixUrl = imagePrefixUrl + "/";
				}
				si.setUrl(imagePrefixUrl + imageUri);
				
				UserPo userPo = new UserPo();
				userPo.setUsername(user.getUsername());
				userPo.setLastModifyDate(new Date());
				if("ALI".equals(type)){
					userPo.setAlipayImgUrl(imageUri);
				}else if("WX".equals(type)){
					userPo.setWeixinImgUrl(imageUri);
				}else{
					rd.setStatus("0001");
					rd.setMsg("类型不存在");
					return rd;
				}
				try {
					Integer conut = getUserDao().updatePayImgUrl(userPo);
					if(conut!=1){
						rd.setStatus("0001");
						rd.setMsg("更新PayImgUrl失败");
						return rd;
					}
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
				resetLogedUser(request, user);
				
			} else {
				rd.setStatus("0001");
				rd.setMsg("仅支持jpg和png格式图片");
			}
		}
		rd.setData(si);
		return rd;

	}
}
