/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: OpenindexVOService
 * Date:18-3-5 上午10:31
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.bll.itf;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.pcitc.fms.bll.entity.Community;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.service.model.Pager;


public  interface OpenindexVOService {

	
	
	//hanxiao
	public List<com.pcitc.fms.service.model.OpenindexVO> getOpenindexVOs(String unitCode) throws BusinessException;

}



















