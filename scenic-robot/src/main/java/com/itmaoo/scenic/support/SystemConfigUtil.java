package com.itmaoo.scenic.support;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.itmaoo.scenic.support.SystemConfig.CurrencyType;
import com.itmaoo.scenic.support.SystemConfig.PointType;
import com.itmaoo.scenic.support.SystemConfig.RoundType;
import com.itmaoo.scenic.support.SystemConfig.StoreFreezeTime;
import com.itmaoo.scenic.support.SystemConfig.WatermarkPosition;


/**
 * 
 */

public class SystemConfigUtil {
	
	public static final String CONFIG_FILE_NAME = "scenic.xml";// 系统配置文件名称
	public static final String SYSTEM_CONFIG_CACHE_KEY = "systemConfig";// systemConfig缓存Key

	/**
	 * 获取系统配置信息
	 * 
	 * @return SystemConfig对象
	 */
	public static SystemConfig getSystemConfig() {
		SystemConfig systemConfig =null;
		File configFile = null;
		Document document = null;
		try {
			String configFilePath = Thread.currentThread().getContextClassLoader().getResource("").toURI().getPath() + CONFIG_FILE_NAME;
			configFile = new File(configFilePath);
			SAXReader saxReader = new SAXReader();
			document = saxReader.read(configFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Node systemNameNode = document.selectSingleNode("/zuuyii/systemConfig/systemName");
		Node systemVersionNode = document.selectSingleNode("/zuuyii/systemConfig/systemVersion");
		Node systemDescriptionNode = document.selectSingleNode("/zuuyii/systemConfig/systemDescription");
		Node isInstalledNode = document.selectSingleNode("/zuuyii/systemConfig/isInstalled");
		Node shopNameNode = document.selectSingleNode("/zuuyii/systemConfig/shopName");
		Node shopUrlNode = document.selectSingleNode("/zuuyii/systemConfig/shopUrl");
		Node shopLogoNode = document.selectSingleNode("/zuuyii/systemConfig/shopLogo");
		Node hotSearchNode = document.selectSingleNode("/zuuyii/systemConfig/hotSearch");
		Node metaKeywordsNode = document.selectSingleNode("/zuuyii/systemConfig/metaKeywords");
		Node metaDescriptionNode = document.selectSingleNode("/zuuyii/systemConfig/metaDescription");
		Node addressNode = document.selectSingleNode("/zuuyii/systemConfig/address");
		Node phoneNode = document.selectSingleNode("/zuuyii/systemConfig/phone");
		Node zipCodeNode = document.selectSingleNode("/zuuyii/systemConfig/zipCode");
		Node emailNode = document.selectSingleNode("/zuuyii/systemConfig/email");
		Node currencyTypeNode = document.selectSingleNode("/zuuyii/systemConfig/currencyType");
		Node currencySignNode = document.selectSingleNode("/zuuyii/systemConfig/currencySign");
		Node currencyUnitNode = document.selectSingleNode("/zuuyii/systemConfig/currencyUnit");
		Node priceScaleNode = document.selectSingleNode("/zuuyii/systemConfig/priceScale");
		Node priceRoundTypeNode = document.selectSingleNode("/zuuyii/systemConfig/priceRoundType");
		Node orderScaleNode = document.selectSingleNode("/zuuyii/systemConfig/orderScale");
		Node orderRoundTypeNode = document.selectSingleNode("/zuuyii/systemConfig/orderRoundType");
		Node certtextNode = document.selectSingleNode("/zuuyii/systemConfig/certtext");
		Node storeAlertCountNode = document.selectSingleNode("/zuuyii/systemConfig/storeAlertCount");
		Node storeFreezeTimeNode = document.selectSingleNode("/zuuyii/systemConfig/storeFreezeTime");
		Node uploadLimitNode = document.selectSingleNode("/zuuyii/systemConfig/uploadLimit");
		Node isLoginFailureLockNode = document.selectSingleNode("/zuuyii/systemConfig/isLoginFailureLock");
		Node loginFailureLockCountNode = document.selectSingleNode("/zuuyii/systemConfig/loginFailureLockCount");
		Node loginFailureLockTimeNode = document.selectSingleNode("/zuuyii/systemConfig/loginFailureLockTime");
		Node isRegisterNode = document.selectSingleNode("/zuuyii/systemConfig/isRegister");
		Node watermarkImagePathNode = document.selectSingleNode("/zuuyii/systemConfig/watermarkImagePath");
		Node watermarkPositionNode = document.selectSingleNode("/zuuyii/systemConfig/watermarkPosition");
		Node watermarkAlphaNode = document.selectSingleNode("/zuuyii/systemConfig/watermarkAlpha");
		Node bigProductImageWidthNode = document.selectSingleNode("/zuuyii/systemConfig/bigProductImageWidth");
		Node bigProductImageHeightNode = document.selectSingleNode("/zuuyii/systemConfig/bigProductImageHeight");
		Node smallProductImageWidthNode = document.selectSingleNode("/zuuyii/systemConfig/smallProductImageWidth");
		Node smallProductImageHeightNode = document.selectSingleNode("/zuuyii/systemConfig/smallProductImageHeight");
		Node thumbnailProductImageWidthNode = document.selectSingleNode("/zuuyii/systemConfig/thumbnailProductImageWidth");
		Node thumbnailProductImageHeightNode = document.selectSingleNode("/zuuyii/systemConfig/thumbnailProductImageHeight");
		Node defaultBigProductImagePathNode = document.selectSingleNode("/zuuyii/systemConfig/defaultBigProductImagePath");
		Node defaultSmallProductImagePathNode = document.selectSingleNode("/zuuyii/systemConfig/defaultSmallProductImagePath");
		Node defaultThumbnailProductImagePathNode = document.selectSingleNode("/zuuyii/systemConfig/defaultThumbnailProductImagePath");
		Node allowedUploadImageExtensionNode = document.selectSingleNode("/zuuyii/systemConfig/allowedUploadImageExtension");
		Node allowedUploadMediaExtensionNode = document.selectSingleNode("/zuuyii/systemConfig/allowedUploadMediaExtension");
		Node allowedUploadFileExtensionNode = document.selectSingleNode("/zuuyii/systemConfig/allowedUploadFileExtension");
		Node smtpFromMailNode = document.selectSingleNode("/zuuyii/systemConfig/smtpFromMail");
		Node smtpHostNode = document.selectSingleNode("/zuuyii/systemConfig/smtpHost");
		Node smtpPortNode = document.selectSingleNode("/zuuyii/systemConfig/smtpPort");
		Node smtpUsernameNode = document.selectSingleNode("/zuuyii/systemConfig/smtpUsername");
		Node smtpPasswordNode = document.selectSingleNode("/zuuyii/systemConfig/smtpPassword");
		Node pointTypeNode = document.selectSingleNode("/zuuyii/systemConfig/pointType");
		Node pointScaleNode = document.selectSingleNode("/zuuyii/systemConfig/pointScale");
		Node imageServer=document.selectSingleNode("/zuuyii/systemConfig/imageServer");
		
		systemConfig = new SystemConfig();
		systemConfig.setSystemName(systemNameNode.getText());
		systemConfig.setSystemVersion(systemVersionNode.getText());
		systemConfig.setSystemDescription(systemDescriptionNode.getText());
		systemConfig.setIsInstalled(Boolean.valueOf(isInstalledNode.getText()));
		systemConfig.setShopName(shopNameNode.getText());
		systemConfig.setShopUrl(shopUrlNode.getText());
		systemConfig.setShopLogo(shopLogoNode.getText());
		systemConfig.setHotSearch(hotSearchNode.getText());
		systemConfig.setMetaKeywords(metaKeywordsNode.getText());
		systemConfig.setMetaDescription(metaDescriptionNode.getText());
		systemConfig.setAddress(addressNode.getText());
		systemConfig.setPhone(phoneNode.getText());
		systemConfig.setZipCode(zipCodeNode.getText());
		systemConfig.setEmail(emailNode.getText());
		systemConfig.setCurrencyType(CurrencyType.valueOf(currencyTypeNode.getText()));
		systemConfig.setCurrencySign(currencySignNode.getText());
		systemConfig.setCurrencyUnit(currencyUnitNode.getText());
		systemConfig.setPriceScale(Integer.valueOf(priceScaleNode.getText()));
		systemConfig.setPriceRoundType(RoundType.valueOf(priceRoundTypeNode.getText()));
		systemConfig.setOrderScale(Integer.valueOf(orderScaleNode.getText()));
		systemConfig.setOrderRoundType(RoundType.valueOf(orderRoundTypeNode.getText()));
		systemConfig.setCerttext(certtextNode.getText());
		systemConfig.setStoreAlertCount(Integer.valueOf(storeAlertCountNode.getText()));
		systemConfig.setStoreFreezeTime(StoreFreezeTime.valueOf(storeFreezeTimeNode.getText()));
		systemConfig.setUploadLimit(Integer.valueOf(uploadLimitNode.getText()));
		systemConfig.setIsLoginFailureLock(Boolean.valueOf(isLoginFailureLockNode.getText()));
		systemConfig.setLoginFailureLockCount(Integer.valueOf(loginFailureLockCountNode.getText()));
		systemConfig.setLoginFailureLockTime(Integer.valueOf(loginFailureLockTimeNode.getText()));
		systemConfig.setIsRegister(Boolean.valueOf(isRegisterNode.getText()));
		systemConfig.setWatermarkImagePath(watermarkImagePathNode.getText());
		systemConfig.setWatermarkPosition(WatermarkPosition.valueOf(watermarkPositionNode.getText()));
		systemConfig.setWatermarkAlpha(Integer.valueOf(watermarkAlphaNode.getText()));
		systemConfig.setBigProductImageWidth(Integer.valueOf(bigProductImageWidthNode.getText()));
		systemConfig.setBigProductImageHeight(Integer.valueOf(bigProductImageHeightNode.getText()));
		systemConfig.setSmallProductImageWidth(Integer.valueOf(smallProductImageWidthNode.getText()));
		systemConfig.setSmallProductImageHeight(Integer.valueOf(smallProductImageHeightNode.getText()));
		systemConfig.setThumbnailProductImageWidth(Integer.valueOf(thumbnailProductImageWidthNode.getText()));
		systemConfig.setThumbnailProductImageHeight(Integer.valueOf(thumbnailProductImageHeightNode.getText()));
		systemConfig.setDefaultBigProductImagePath(defaultBigProductImagePathNode.getText());
		systemConfig.setDefaultSmallProductImagePath(defaultSmallProductImagePathNode.getText());
		systemConfig.setDefaultThumbnailProductImagePath(defaultThumbnailProductImagePathNode.getText());
		systemConfig.setAllowedUploadImageExtension(allowedUploadImageExtensionNode.getText());
		systemConfig.setAllowedUploadMediaExtension(allowedUploadMediaExtensionNode.getText());
		systemConfig.setAllowedUploadFileExtension(allowedUploadFileExtensionNode.getText());
		systemConfig.setSmtpFromMail(smtpFromMailNode.getText());
		systemConfig.setSmtpHost(smtpHostNode.getText());
		systemConfig.setSmtpPort(Integer.valueOf(smtpPortNode.getText()));
		systemConfig.setSmtpUsername(smtpUsernameNode.getText());
		systemConfig.setSmtpPassword(smtpPasswordNode.getText());
		systemConfig.setPointType(PointType.valueOf(pointTypeNode.getText()));
		systemConfig.setPointScale(Double.valueOf(pointScaleNode.getText()));
		systemConfig.setImageServer(imageServer.getText());
		
		//OsCacheConfigUtil.putInCache(SYSTEM_CONFIG_CACHE_KEY, systemConfig);
		return systemConfig;
	}
	
	/**
	 * 更新系统配置信息
	 * 
	 * @param systemConfig
	 *          SystemConfig对象
	 */
	public static void update(SystemConfig systemConfig) {
		File configFile = null;
		Document document = null;
		try {
			String configFilePath = Thread.currentThread().getContextClassLoader().getResource("").toURI().getPath() + CONFIG_FILE_NAME;
			configFile = new File(configFilePath);
			SAXReader saxReader = new SAXReader();
			document = saxReader.read(configFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Element rootElement = document.getRootElement();
		Element systemConfigElement = rootElement.element("systemConfig");
		Node systemNameNode = document.selectSingleNode("/zuuyii/systemConfig/systemName");
		Node systemVersionNode = document.selectSingleNode("/zuuyii/systemConfig/systemVersion");
		Node systemDescriptionNode = document.selectSingleNode("/zuuyii/systemConfig/systemDescription");
		Node isInstalledNode = document.selectSingleNode("/zuuyii/systemConfig/isInstalled");
		Node shopNameNode = document.selectSingleNode("/zuuyii/systemConfig/shopName");
		Node shopUrlNode = document.selectSingleNode("/zuuyii/systemConfig/shopUrl");
		Node shopLogoNode = document.selectSingleNode("/zuuyii/systemConfig/shopLogo");
		Node hotSearchNode = document.selectSingleNode("/zuuyii/systemConfig/hotSearch");
		Node metaKeywordsNode = document.selectSingleNode("/zuuyii/systemConfig/metaKeywords");
		Node metaDescriptionNode = document.selectSingleNode("/zuuyii/systemConfig/metaDescription");
		Node addressNode = document.selectSingleNode("/zuuyii/systemConfig/address");
		Node phoneNode = document.selectSingleNode("/zuuyii/systemConfig/phone");
		Node zipCodeNode = document.selectSingleNode("/zuuyii/systemConfig/zipCode");
		Node emailNode = document.selectSingleNode("/zuuyii/systemConfig/email");
		Node currencyTypeNode = document.selectSingleNode("/zuuyii/systemConfig/currencyType");
		Node currencySignNode = document.selectSingleNode("/zuuyii/systemConfig/currencySign");
		Node currencyUnitNode = document.selectSingleNode("/zuuyii/systemConfig/currencyUnit");
		Node priceScaleNode = document.selectSingleNode("/zuuyii/systemConfig/priceScale");
		Node priceRoundTypeNode = document.selectSingleNode("/zuuyii/systemConfig/priceRoundType");
		Node orderScaleNode = document.selectSingleNode("/zuuyii/systemConfig/orderScale");
		Node orderRoundTypeNode = document.selectSingleNode("/zuuyii/systemConfig/orderRoundType");
		Node certtextNode = document.selectSingleNode("/zuuyii/systemConfig/certtext");
		Node storeAlertCountNode = document.selectSingleNode("/zuuyii/systemConfig/storeAlertCount");
		Node storeFreezeTimeNode = document.selectSingleNode("/zuuyii/systemConfig/storeFreezeTime");
		Node uploadLimitNode = document.selectSingleNode("/zuuyii/systemConfig/uploadLimit");
		Node isLoginFailureLockNode = document.selectSingleNode("/zuuyii/systemConfig/isLoginFailureLock");
		Node loginFailureLockCountNode = document.selectSingleNode("/zuuyii/systemConfig/loginFailureLockCount");
		Node loginFailureLockTimeNode = document.selectSingleNode("/zuuyii/systemConfig/loginFailureLockTime");
		Node isRegisterNode = document.selectSingleNode("/zuuyii/systemConfig/isRegister");
		Node watermarkImagePathNode = document.selectSingleNode("/zuuyii/systemConfig/watermarkImagePath");
		Node watermarkPositionNode = document.selectSingleNode("/zuuyii/systemConfig/watermarkPosition");
		Node watermarkAlphaNode = document.selectSingleNode("/zuuyii/systemConfig/watermarkAlpha");
		Node bigProductImageWidthNode = document.selectSingleNode("/zuuyii/systemConfig/bigProductImageWidth");
		Node bigProductImageHeightNode = document.selectSingleNode("/zuuyii/systemConfig/bigProductImageHeight");
		Node smallProductImageWidthNode = document.selectSingleNode("/zuuyii/systemConfig/smallProductImageWidth");
		Node smallProductImageHeightNode = document.selectSingleNode("/zuuyii/systemConfig/smallProductImageHeight");
		Node thumbnailProductImageWidthNode = document.selectSingleNode("/zuuyii/systemConfig/thumbnailProductImageWidth");
		Node thumbnailProductImageHeightNode = document.selectSingleNode("/zuuyii/systemConfig/thumbnailProductImageHeight");
		Node defaultBigProductImagePathNode = document.selectSingleNode("/zuuyii/systemConfig/defaultBigProductImagePath");
		Node defaultSmallProductImagePathNode = document.selectSingleNode("/zuuyii/systemConfig/defaultSmallProductImagePath");
		Node defaultThumbnailProductImagePathNode = document.selectSingleNode("/zuuyii/systemConfig/defaultThumbnailProductImagePath");
		Node allowedUploadImageExtensionNode = document.selectSingleNode("/zuuyii/systemConfig/allowedUploadImageExtension");
		Node allowedUploadMediaExtensionNode = document.selectSingleNode("/zuuyii/systemConfig/allowedUploadMediaExtension");
		Node allowedUploadFileExtensionNode = document.selectSingleNode("/zuuyii/systemConfig/allowedUploadFileExtension");
		Node smtpFromMailNode = document.selectSingleNode("/zuuyii/systemConfig/smtpFromMail");
		Node smtpHostNode = document.selectSingleNode("/zuuyii/systemConfig/smtpHost");
		Node smtpPortNode = document.selectSingleNode("/zuuyii/systemConfig/smtpPort");
		Node smtpUsernameNode = document.selectSingleNode("/zuuyii/systemConfig/smtpUsername");
		Node smtpPasswordNode = document.selectSingleNode("/zuuyii/systemConfig/smtpPassword");
		Node pointTypeNode = document.selectSingleNode("/zuuyii/systemConfig/pointType");
		Node pointScaleNode = document.selectSingleNode("/zuuyii/systemConfig/pointScale");
		
		if(systemNameNode == null){
			systemNameNode = systemConfigElement.addElement("systemName");
		}
		if(systemVersionNode == null){
			systemVersionNode = systemConfigElement.addElement("systemVersion");
		}
		if(systemDescriptionNode == null){
			systemDescriptionNode = systemConfigElement.addElement("systemDescription");
		}
		if(isInstalledNode == null){
			isInstalledNode = systemConfigElement.addElement("isInstalled");
		}
		if(shopNameNode == null){
			shopNameNode = systemConfigElement.addElement("shopName");
		}
		if(shopUrlNode == null){
			shopUrlNode = systemConfigElement.addElement("shopUrl");
		}
		if(shopLogoNode == null){
			shopLogoNode = systemConfigElement.addElement("shopLogo");
		}
		if(hotSearchNode == null){
			hotSearchNode = systemConfigElement.addElement("hotSearch");
		}
		if(metaKeywordsNode == null){
			metaKeywordsNode = systemConfigElement.addElement("metaKeywords");
		}
		if(metaDescriptionNode == null){
			metaDescriptionNode = systemConfigElement.addElement("metaDescription");
		}
		if(addressNode == null){
			addressNode = systemConfigElement.addElement("address");
		}
		if(phoneNode == null){
			phoneNode = systemConfigElement.addElement("phone");
		}
		if(zipCodeNode == null){
			zipCodeNode = systemConfigElement.addElement("zipCode");
		}
		if(emailNode == null){
			emailNode = systemConfigElement.addElement("email");
		}
		if(currencyTypeNode == null){
			currencyTypeNode = systemConfigElement.addElement("currencyType");
		}
		if(currencySignNode == null){
			currencySignNode = systemConfigElement.addElement("currencySign");
		}
		if(currencyUnitNode == null){
			currencyUnitNode = systemConfigElement.addElement("currencyUnit");
		}
		if(priceScaleNode == null){
			priceScaleNode = systemConfigElement.addElement("priceScale");
		}
		if(priceRoundTypeNode == null){
			priceRoundTypeNode = systemConfigElement.addElement("priceRoundType");
		}
		if(orderScaleNode == null){
			orderScaleNode = systemConfigElement.addElement("orderScale");
		}
		if(orderRoundTypeNode == null){
			orderRoundTypeNode = systemConfigElement.addElement("orderRoundType");
		}
		if(certtextNode == null){
			certtextNode = systemConfigElement.addElement("certtext");
		}
		if(storeAlertCountNode == null){
			storeAlertCountNode = systemConfigElement.addElement("storeAlertCount");
		}
		if(storeFreezeTimeNode == null){
			storeFreezeTimeNode = systemConfigElement.addElement("storeFreezeTime");
		}
		if(uploadLimitNode == null){
			uploadLimitNode = systemConfigElement.addElement("uploadLimit");
		}
		if(isLoginFailureLockNode == null){
			isLoginFailureLockNode = systemConfigElement.addElement("isLoginFailureLock");
		}
		if(loginFailureLockCountNode == null){
			loginFailureLockCountNode = systemConfigElement.addElement("loginFailureLockCount");
		}
		if(loginFailureLockTimeNode == null){
			loginFailureLockTimeNode = systemConfigElement.addElement("loginFailureLockTime");
		}
		if(isRegisterNode == null){
			isRegisterNode = systemConfigElement.addElement("isRegister");
		}
		if(watermarkImagePathNode == null){
			watermarkImagePathNode = systemConfigElement.addElement("watermarkImagePath");
		}
		if(watermarkPositionNode == null){
			watermarkPositionNode = systemConfigElement.addElement("watermarkPosition");
		}
		if(watermarkAlphaNode == null){
			watermarkAlphaNode = systemConfigElement.addElement("watermarkAlpha");
		}
		if(bigProductImageWidthNode == null){
			bigProductImageWidthNode = systemConfigElement.addElement("bigProductImageWidth");
		}
		if(bigProductImageHeightNode == null){
			bigProductImageHeightNode = systemConfigElement.addElement("bigProductImageHeight");
		}
		if(smallProductImageWidthNode == null){
			smallProductImageWidthNode = systemConfigElement.addElement("smallProductImageWidth");
		}
		if(smallProductImageHeightNode == null){
			smallProductImageHeightNode = systemConfigElement.addElement("smallProductImageHeight");
		}
		if(thumbnailProductImageWidthNode == null){
			thumbnailProductImageWidthNode = systemConfigElement.addElement("thumbnailProductImageWidth");
		}
		if(thumbnailProductImageHeightNode == null){
			thumbnailProductImageHeightNode = systemConfigElement.addElement("thumbnailProductImageHeight");
		}
		if(defaultBigProductImagePathNode == null){
			defaultBigProductImagePathNode = systemConfigElement.addElement("defaultBigProductImagePath");
		}
		if(defaultSmallProductImagePathNode == null){
			defaultSmallProductImagePathNode = systemConfigElement.addElement("defaultSmallProductImagePath");
		}
		if(defaultThumbnailProductImagePathNode == null){
			defaultThumbnailProductImagePathNode = systemConfigElement.addElement("defaultThumbnailProductImagePath");
		}
		if(allowedUploadImageExtensionNode == null){
			allowedUploadImageExtensionNode = systemConfigElement.addElement("allowedUploadImageExtension");
		}
		if(allowedUploadMediaExtensionNode == null){
			allowedUploadMediaExtensionNode = systemConfigElement.addElement("allowedUploadMediaExtension");
		}
		if(allowedUploadFileExtensionNode == null){
			allowedUploadFileExtensionNode = systemConfigElement.addElement("allowedUploadFileExtension");
		}
		if(smtpFromMailNode == null){
			smtpFromMailNode = systemConfigElement.addElement("smtpFromMail");
		}
		if(smtpHostNode == null){
			smtpHostNode = systemConfigElement.addElement("smtpHost");
		}
		if(smtpPortNode == null){
			smtpPortNode = systemConfigElement.addElement("smtpPort");
		}
		if(smtpUsernameNode == null){
			smtpUsernameNode = systemConfigElement.addElement("smtpUsername");
		}
		if(smtpPasswordNode == null){
			smtpPasswordNode = systemConfigElement.addElement("smtpPassword");
		}
		if(pointTypeNode == null){
			pointTypeNode = systemConfigElement.addElement("pointType");
		}
		if(pointScaleNode == null){
			pointScaleNode = systemConfigElement.addElement("pointScale");
		}
		
		systemNameNode.setText(systemConfig.getSystemName());
		systemVersionNode.setText(systemConfig.getSystemVersion());
		systemDescriptionNode.setText(systemConfig.getSystemDescription());
		isInstalledNode.setText(systemConfig.getIsInstalled().toString());
		shopNameNode.setText(systemConfig.getShopName());
		shopUrlNode.setText(StringUtils.removeEnd(systemConfig.getShopUrl(), "/"));
		shopLogoNode.setText(systemConfig.getShopLogo());
		hotSearchNode.setText(systemConfig.getHotSearch());
		metaKeywordsNode.setText(systemConfig.getMetaKeywords());
		metaDescriptionNode.setText(systemConfig.getMetaDescription());
		addressNode.setText(systemConfig.getAddress());
		phoneNode.setText(systemConfig.getPhone());
		zipCodeNode.setText(systemConfig.getZipCode());
		emailNode.setText(systemConfig.getEmail());
		currencyTypeNode.setText(String.valueOf(systemConfig.getCurrencyType()));
		currencySignNode.setText(systemConfig.getCurrencySign());
		currencyUnitNode.setText(systemConfig.getCurrencyUnit());
		priceScaleNode.setText(String.valueOf(systemConfig.getPriceScale()));
		priceRoundTypeNode.setText(String.valueOf(systemConfig.getPriceRoundType()));
		orderScaleNode.setText(String.valueOf(systemConfig.getOrderScale()));
		orderRoundTypeNode.setText(String.valueOf(systemConfig.getOrderRoundType()));
		certtextNode.setText(systemConfig.getCerttext());
		storeAlertCountNode.setText(String.valueOf(systemConfig.getStoreAlertCount()));
		storeFreezeTimeNode.setText(String.valueOf(systemConfig.getStoreFreezeTime()));
		uploadLimitNode.setText(String.valueOf(systemConfig.getUploadLimit()));
		isLoginFailureLockNode.setText(String.valueOf(systemConfig.getIsLoginFailureLock()));
		loginFailureLockCountNode.setText(String.valueOf(systemConfig.getLoginFailureLockCount()));
		loginFailureLockTimeNode.setText(String.valueOf(systemConfig.getLoginFailureLockTime()));
		isRegisterNode.setText(String.valueOf(systemConfig.getIsRegister()));
		watermarkImagePathNode.setText(systemConfig.getWatermarkImagePath());
		watermarkPositionNode.setText(String.valueOf(systemConfig.getWatermarkPosition()));
		watermarkAlphaNode.setText(String.valueOf(systemConfig.getWatermarkAlpha()));
		bigProductImageWidthNode.setText(String.valueOf(systemConfig.getBigProductImageWidth()));
		bigProductImageHeightNode.setText(String.valueOf(systemConfig.getBigProductImageHeight()));
		smallProductImageWidthNode.setText(String.valueOf(systemConfig.getSmallProductImageWidth()));
		smallProductImageHeightNode.setText(String.valueOf(systemConfig.getSmallProductImageHeight()));
		thumbnailProductImageWidthNode.setText(String.valueOf(systemConfig.getThumbnailProductImageWidth()));
		thumbnailProductImageHeightNode.setText(String.valueOf(systemConfig.getThumbnailProductImageHeight()));
		defaultBigProductImagePathNode.setText(systemConfig.getDefaultBigProductImagePath());
		defaultSmallProductImagePathNode.setText(systemConfig.getDefaultSmallProductImagePath());
		defaultThumbnailProductImagePathNode.setText(systemConfig.getDefaultThumbnailProductImagePath());
		allowedUploadImageExtensionNode.setText(systemConfig.getAllowedUploadImageExtension());
		allowedUploadMediaExtensionNode.setText(systemConfig.getAllowedUploadMediaExtension());
		allowedUploadFileExtensionNode.setText(systemConfig.getAllowedUploadFileExtension());
		smtpFromMailNode.setText(systemConfig.getSmtpFromMail());
		smtpHostNode.setText(systemConfig.getSmtpHost());
		if (systemConfig.getSmtpPort() == null) {
			smtpPortNode.setText("25");
		} else {
			smtpPortNode.setText(String.valueOf(systemConfig.getSmtpPort()));
		}
		smtpUsernameNode.setText(systemConfig.getSmtpUsername());
		smtpPasswordNode.setText(systemConfig.getSmtpPassword());
		pointTypeNode.setText(systemConfig.getPointType().toString());
		pointScaleNode.setText(systemConfig.getPointScale().toString());
		try {
			OutputFormat outputFormat = OutputFormat.createPrettyPrint();// 设置XML文档输出格式
			outputFormat.setEncoding("UTF-8");// 设置XML文档的编码类型
			outputFormat.setIndent(true);// 设置是否缩进
			outputFormat.setIndent("	");// 以TAB方式实现缩进
			outputFormat.setNewlines(true);// 设置是否换行
			XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(configFile), outputFormat);
			xmlWriter.write(document);
			xmlWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 获取精度处理后的商品价格
	 * 
	 */
	public static BigDecimal getPriceScaleBigDecimal(BigDecimal price) {
		Integer priceScale = getSystemConfig().getPriceScale();
		RoundType priceRoundType = getSystemConfig().getPriceRoundType();
		if (priceRoundType == RoundType.roundHalfUp) {
			return price.setScale(priceScale, BigDecimal.ROUND_HALF_UP);
		} else if (priceRoundType == RoundType.roundUp) {
			return price.setScale(priceScale, BigDecimal.ROUND_UP);
		} else {
			return price.setScale(priceScale, BigDecimal.ROUND_DOWN);
		}
	}
	
	/**
	 * 获取精度处理后的订单额
	 * 
	 */
	public static BigDecimal getOrderScaleBigDecimal(BigDecimal orderAmount) {
		Integer orderScale = getSystemConfig().getOrderScale();
		RoundType orderRoundType = getSystemConfig().getOrderRoundType();
		if (orderRoundType == RoundType.roundHalfUp) {
			return orderAmount.setScale(orderScale, BigDecimal.ROUND_HALF_UP);
		} else if (orderRoundType == RoundType.roundUp) {
			return orderAmount.setScale(orderScale, BigDecimal.ROUND_UP);
		} else {
			return orderAmount.setScale(orderScale, BigDecimal.ROUND_DOWN);
		}
	}
	
	/**
	 * 获取商品价格货币格式字符串
	 * 
	 */
	public static String getPriceCurrencyFormat() {
		Integer priceScale = getSystemConfig().getPriceScale();
		String currencySign = getSystemConfig().getCurrencySign();
		if (priceScale == 0) {
			return currencySign + "#0";
		} else if (priceScale == 1) {
			return currencySign + "#0.0";
		} else if (priceScale == 2) {
			return currencySign + "#0.00";
		} else if (priceScale == 3) {
			return currencySign + "#0.000";
		} else if (priceScale == 4) {
			return currencySign + "#0.0000";
		} else {
			return currencySign + "#0.00000";
		}
	}
	
	/**
	 * 获取商品价格货币格式字符串（包含货币单位）
	 * 
	 */
	public static String getPriceUnitCurrencyFormat() {
		Integer priceScale = getSystemConfig().getPriceScale();
		String currencySign = getSystemConfig().getCurrencySign();
		String currencyUnit = getSystemConfig().getCurrencyUnit();
		if (priceScale == 0) {
			return currencySign + "#0" + currencyUnit;
		} else if (priceScale == 1) {
			return currencySign + "#0.0" + currencyUnit;
		} else if (priceScale == 2) {
			return currencySign + "#0.00" + currencyUnit;
		} else if (priceScale == 3) {
			return currencySign + "#0.000" + currencyUnit;
		} else if (priceScale == 4) {
			return currencySign + "#0.0000" + currencyUnit;
		} else {
			return currencySign + "#0.00000" + currencyUnit;
		}
	}
	
	/**
	 * 获取订单价格货币格式字符串
	 * 
	 */
	public static String getOrderCurrencyFormat() {
		Integer orderScale = getSystemConfig().getOrderScale();
		String currencySign = getSystemConfig().getCurrencySign();
		if (orderScale == 0) {
			return currencySign + "#0";
		} else if (orderScale == 1) {
			return currencySign + "#0.0";
		} else if (orderScale == 2) {
			return currencySign + "#0.00";
		} else if (orderScale == 3) {
			return currencySign + "#0.000";
		} else if (orderScale == 4) {
			return currencySign + "#0.0000";
		} else {
			return currencySign + "#0.00000";
		}
	}
	
	/**
	 * 获取订单价格货币格式字符串（包含货币单位）
	 * 
	 */
	public static String getOrderUnitCurrencyFormat() {
		Integer orderScale = getSystemConfig().getOrderScale();
		String currencySign = getSystemConfig().getCurrencySign();
		String currencyUnit = getSystemConfig().getCurrencyUnit();
		if (orderScale == 0) {
			return currencySign + "#0" + currencyUnit;
		} else if (orderScale == 1) {
			return currencySign + "#0.0" + currencyUnit;
		} else if (orderScale == 2) {
			return currencySign + "#0.00" + currencyUnit;
		} else if (orderScale == 3) {
			return currencySign + "#0.000" + currencyUnit;
		} else if (orderScale == 4) {
			return currencySign + "#0.0000" + currencyUnit;
		} else {
			return currencySign + "#0.00000" + currencyUnit;
		}
	}

}