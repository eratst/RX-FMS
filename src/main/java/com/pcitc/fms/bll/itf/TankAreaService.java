/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: TankAreaService
 * Date:18-3-5 上午10:44
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.bll.itf;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pcitc.fms.bll.entity.TankArea;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.fms.service.model.TankAreaModelStr;
import com.pcitc.imp.common.exception.BusiException;

public interface TankAreaService {


	public Pager<TankArea> getTankAreas(com.pcitc.fms.service.model.TankArea TankArea, Pageable pageable)throws BusinessException, BusiException;


}
