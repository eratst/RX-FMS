/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: SideLineService
 * Date:18-3-5 上午10:43
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.bll.itf;

import com.pcitc.fms.exception.BusinessException;
import java.util.List;

import org.springframework.data.domain.Pageable;

import com.pcitc.fms.bll.entity.NodeDictionary;
import com.pcitc.fms.bll.entity.SideLine;
import com.pcitc.fms.bll.entity.Tank;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.fms.service.model.SideLineStr;
import com.pcitc.fms.service.model.TankModelStr;
import com.pcitc.imp.common.exception.BusiException;


public interface SideLineService {

	List<SideLine> getSideLineCode(String nodeCode, String parentCode, String sideLineCode, String parentType)
			throws BusinessException;

	Pager<SideLine> getSideLineModel(com.pcitc.fms.service.model.SideLine sideLineModel, Pageable pageable)
			throws BusinessException;

	List<SideLine> getSideLines(SideLineStr modelStr) throws BusinessException;

	List<SideLine> getLinkSideLines(SideLineStr modelStr) throws BusinessException;




}
