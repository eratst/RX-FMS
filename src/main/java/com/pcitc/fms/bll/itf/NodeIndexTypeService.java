/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: NodeIndexTypeService
 * Date:18-3-5 上午10:30
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.bll.itf;

import com.pcitc.fms.exception.BusinessException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.pcitc.fms.service.model.NodeIdxType;

@Service
public interface NodeIndexTypeService {
	public List<NodeIdxType> getByIdxType(String type) throws BusinessException;
}
