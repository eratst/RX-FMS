/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: NodeDictGatherService
 * Date:18-3-5 上午10:29
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.bll.itf;

import com.pcitc.fms.exception.BusinessException;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface NodeDictGatherService {

	public List<com.pcitc.fms.bll.dictionary.entity.NodeType> getNodeTypes(String code)throws BusinessException;

	public List<com.pcitc.fms.bll.dictionary.entity.SidelineType> getSidelineTypes(String code)throws BusinessException;

	public List<com.pcitc.fms.bll.dictionary.entity.SideMtrlType> getSideMtrlTypes(String code)throws BusinessException;

	public List<com.pcitc.fms.bll.dictionary.entity.TankType> getTankTypes(String code)throws BusinessException;

	public List<com.pcitc.fms.bll.dictionary.entity.VcfType> getVcfTypes(String code)throws BusinessException;

	public List<com.pcitc.fms.bll.dictionary.entity.EquTechnic> getEquTechnics(String code)throws BusinessException;

	public List<com.pcitc.fms.bll.dictionary.entity.TransType> getTransTypes(String code)throws BusinessException;

	public List<com.pcitc.fms.bll.dictionary.entity.SiloType> getSiloTypes(String code)throws BusinessException;

	public List<com.pcitc.fms.bll.dictionary.entity.SamplepointType> getSamplepointTypes(String code)throws BusinessException;

	public List<com.pcitc.fms.bll.dictionary.entity.SignboardType> getSignboardTypes(String code)throws BusinessException;

	public List<com.pcitc.fms.bll.dictionary.entity.IdxType> getIdxTypes(String code)throws BusinessException;

	public List<com.pcitc.fms.bll.dictionary.entity.MtrlType> getMtrlTypes(String code)throws BusinessException;
	
}
