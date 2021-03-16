package com.pcitc.fms.bll.itf;

import java.util.List;

import com.pcitc.fms.service.model.MtrlR;

public interface MesMtrlService  extends RESTClientService {
	public List<MtrlR> getMtrls(String code) throws Exception;
}
