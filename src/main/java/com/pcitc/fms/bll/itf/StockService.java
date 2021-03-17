/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: StockService
 * Date:18-3-5 上午10:44
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.bll.itf;

import com.pcitc.fms.exception.BusinessException;
import java.util.List;

import org.springframework.data.domain.Pageable;

import com.pcitc.fms.bll.entity.NodeDictionary;
import com.pcitc.fms.bll.entity.Stock;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.fms.service.model.StockModelStr;

public interface StockService {

	public Pager<Stock> getStocks(com.pcitc.fms.service.model.Stock modelStr,Pageable pageable)throws BusinessException;

}
