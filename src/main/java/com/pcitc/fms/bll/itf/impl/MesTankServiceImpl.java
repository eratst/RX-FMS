package com.pcitc.fms.bll.itf.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.pcitc.fms.bll.itf.MesTankService;
import com.pcitc.fms.service.model.TankR;
@Service
@Component
public class MesTankServiceImpl extends RESTClientServiceImpl implements MesTankService{

	private static final String FACTORY_SERVICE_ROOT = "http://10.238.255.163:32478/"
			+ "pm/tanks?$codeList=";
	@Override
	public List<Object> getTanks(String code) throws Exception {
		StringBuffer url = new StringBuffer(FACTORY_SERVICE_ROOT);
		url.append(code);
		try {			 
			return getList(url);
		}catch(Exception e){
			e.printStackTrace();
			return new ArrayList<Object>();
		}
		
	}

}
