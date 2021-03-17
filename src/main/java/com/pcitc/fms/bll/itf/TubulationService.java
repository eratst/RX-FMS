/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: TubulationService
 * Date:18-3-5 上午10:46
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.bll.itf;

import org.springframework.data.domain.Pageable;

import com.pcitc.fms.bll.entity.Tubulation;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.service.model.Pager;


public interface TubulationService {


  public  Pager<Tubulation> getPageTubulations(com.pcitc.fms.service.model.Tubulation tubulationModel, Pageable pageable) throws BusinessException;

	

}
