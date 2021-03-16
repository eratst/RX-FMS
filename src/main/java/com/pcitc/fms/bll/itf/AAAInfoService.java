package com.pcitc.fms.bll.itf;

import java.util.List;

import com.pcitc.fms.bll.entity.AAAProperty;

public interface AAAInfoService {
	
	public List<com.pcitc.fms.service.model.AAAProperty> getAAAProperties(String userCode,String rentCode) throws Exception;

}
