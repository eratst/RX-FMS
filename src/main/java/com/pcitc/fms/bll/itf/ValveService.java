/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: ValveService
 * Date:18-3-5 上午10:47
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.bll.itf;

import com.pcitc.fms.exception.BusinessException;
import java.util.List;

import org.springframework.data.domain.Pageable;

import com.pcitc.fms.bll.entity.NodeDictionary;
import com.pcitc.fms.bll.entity.Valve;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.imp.common.exception.BusiException;

/**
 * Title: ValveService Description:阀门
 *
 * @author zhenqiang.zhao
 * @version 1.0
 * @date 2017年6月14日
 */
public interface ValveService {


	public Pager<com.pcitc.fms.bll.entity.Valve> getValves(com.pcitc.fms.service.model.Valve valveModel, Pageable pageable) throws BusinessException;

}
