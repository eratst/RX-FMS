package com.pcitc.fms.bll.itf;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.pcitc.fms.bll.entity.NodeDictionary;
import com.pcitc.fms.bll.entity.Tee;
import com.pcitc.fms.service.model.Pager;

import com.pcitc.fms.exception.BusinessException;

public interface TeeService {

	public List<Tee> getTeeByCode(String parentCode ,String teeCode, String parentType) throws BusinessException;


	public  Pager<Tee> getTeesByModel(com.pcitc.fms.service.model.Tee teeModel, Pageable pageable) throws BusinessException;


}
