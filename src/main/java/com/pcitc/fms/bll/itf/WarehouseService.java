/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: WarehouseService
 * Date:18-3-5 上午10:47
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.bll.itf;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.pcitc.fms.bll.entity.Warehouse;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.service.model.Pager;

/**
 * The interface Warehouse service.
 */
public  interface WarehouseService {


	public Pager<Warehouse> getPageWarehouses(com.pcitc.fms.service.model.Warehouse modelStr, Pageable pageable) throws BusinessException;

}



















