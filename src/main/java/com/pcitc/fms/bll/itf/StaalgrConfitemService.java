/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: StaalgrConfitemService
 * Date:18-3-5 上午10:43
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.bll.itf;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pcitc.fms.bll.entity.StaalgrConfitem;
import com.pcitc.fms.bll.entity.UnitAlarm;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.service.model.Administration;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.imp.common.exception.BusiException;

@Service
public interface StaalgrConfitemService {


	public StaalgrConfitem getByCode(String code) throws BusinessException;

	public Pager<StaalgrConfitem> getPageStaalgrConfitems(com.pcitc.fms.service.model.StaalgrConfitem model) throws BusinessException;

	public List<StaalgrConfitem> addStaalgrConfitem(List<com.pcitc.fms.service.model.StaalgrConfitem> modelList) throws BusinessException;


	public void updateStaalgrConfitem(List<com.pcitc.fms.service.model.StaalgrConfitem> modelList) throws BusinessException;

//	public void deleteByStaalgrConfitemCode(String code);

//	public void deleteByOpeindexId(Integer openindexId);

	public void deleteByOpeindexIdAndStaalgrConfId(Integer opeindexId, Integer staalgrConfId) throws BusinessException;

	public void deleteByStaalgrConfId(Integer staalgrConfId) throws BusinessException;

	void deleteByAgentCode(String agentCode);


}
