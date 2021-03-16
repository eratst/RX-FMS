/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: MaterialService
 * Date:18-3-5 上午10:27
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.bll.itf;

import com.pcitc.fms.bll.entity.Material;
import com.pcitc.fms.exception.BusinessException;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pcitc.fms.service.model.Pager;

@Service
public interface MaterialService {

//	public List<com.pcitc.fms.bll.entity.Material> getMaterials(Material materialModel)throws BusinessException;

//	public List<com.pcitc.fms.bll.entity.Material> addMaterials(List<com.pcitc.fms.bll.entity.Material> materialEntityList)throws BusinessException;

//	public void updateMaterial(List<com.pcitc.fms.bll.entity.Material> materialEntityList)throws BusinessException;

//	public void deleteMaterialByCode(String materialCode)throws BusinessException;

//	public List<com.pcitc.fms.bll.entity.Material> getMaterials(String parentType, String parentCode) throws BusinessException;

	public Pager<Material> getMaterials(com.pcitc.fms.service.model.Material materialModel, Pageable pageable) throws BusinessException ;

	public List<Material> getMaterialsByOperType(String mtrlCode,List<String> mtrlCodes, String operType, com.pcitc.fms.service.model.Material plateModel,Pageable pageable) throws BusinessException;
//	public List<com.pcitc.fms.bll.entity.Material> getMeasurementForRelation(List<String> relSourceCodeList)throws BusinessException;



}
