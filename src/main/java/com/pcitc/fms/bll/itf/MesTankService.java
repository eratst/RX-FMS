package com.pcitc.fms.bll.itf;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface MesTankService  extends RESTClientService{
	
	public List<Object> getTanks(String code) throws Exception;
}
