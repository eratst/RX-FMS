/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: OutletService
 * Date:18-3-5 上午10:39
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.bll.itf;

import com.pcitc.fms.exception.BusinessException;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pcitc.fms.bll.entity.NodeDictionary;
import com.pcitc.fms.service.model.Outlet;
import com.pcitc.fms.service.model.Pager;

@Service
public interface OutletService {

	public Pager<com.pcitc.fms.bll.entity.Outlet> getPageOutlets(Outlet outletModel, Pageable pageable) throws BusinessException;

}
