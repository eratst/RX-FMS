package com.pcitc.fms.bll.itf.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.pcitc.fms.bll.itf.AAAInfoService;
import com.pcitc.fms.common.YLCloseableHttpClient;
import com.pcitc.imp.common.exception.BusiException;

import pcitc.imp.common.ettool.utils.RestfulTool;

@Service
public class AAAInfoServiceImpl implements AAAInfoService{
	
	@Autowired
	private Environment env;

	@SuppressWarnings("unchecked")
	@Override
	public List<com.pcitc.fms.service.model.AAAProperty> getAAAProperties(String userCode,String rentCode) throws Exception {
		
		String aaa_addr_former =  env.getProperty("aaa.addr.former");
		String aaa_addr_later = env.getProperty("aaa.addr.later");
		String aaa_appcode = env.getProperty("aaa.appcode");
		String aaa_propertycode = env.getProperty("aaa.propertycode");
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("http://");
		
		if (StringUtils.isEmpty(aaa_addr_former)) {
			throw new BusiException("", "AAA地址有误！");
		}
		sb.append(aaa_addr_former);
		
		
		if (!StringUtils.isEmpty(aaa_addr_later)) {
			sb.append("."+rentCode+"."+aaa_addr_later);
		}
		
		sb.append("/ResourceService/apps/"+aaa_appcode+"/properties/"+aaa_propertycode+"/propertyValues"+"?userCode="+userCode);
		
		String aaaAddr = sb.toString();
		
		List<com.pcitc.fms.service.model.AAAProperty> modelAAAProperty = new ArrayList<>();
		try {
			Object resultStr = YLCloseableHttpClient.getObject(aaaAddr,null);
			modelAAAProperty = RestfulTool.toResourceRepList(resultStr.toString(), com.pcitc.fms.service.model.AAAProperty.class);

		} catch (Exception e) {
			throw new BusiException("", e.getMessage());
		}
				
		return modelAAAProperty;
	}

}
