package com.pcitc.fms.bll.itf;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pcitc.fms.bll.entity.StaalgrConf;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.imp.common.exception.BusiException;

@Service
public interface StaalgrConfService {


	public StaalgrConf getByCode(String code) throws BusinessException;

	public Pager<StaalgrConf> getPageStaalgrConfs(com.pcitc.fms.service.model.StaalgrConf model) throws BusinessException;

	public List<StaalgrConf> addStaalgrConf(List<com.pcitc.fms.service.model.StaalgrConf> modelList) throws BusinessException;


	public void updateStaalgrConf(List<com.pcitc.fms.service.model.StaalgrConf> modelList,String unitCode) throws BusinessException;

//	public void deleteByStaalgrConfCode(String code);

	public void deleteByMonLevelIdAndEquipId(Integer monLevelId, String unitCode) throws BusinessException;

	void deleteByAgentCode(String string) throws BusinessException;


}
