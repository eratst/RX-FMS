/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: MeasurementService
 * Date:18-3-5 上午10:27
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.bll.itf;

import com.pcitc.fms.exception.BusinessException;
import java.util.List;

import org.springframework.data.domain.Pageable;

import com.pcitc.fms.bll.entity.Measurement;
import com.pcitc.fms.service.model.Pager;




public interface MeasurementService {





	public Pager<com.pcitc.fms.bll.entity.Measurement> getPageMeasurements(com.pcitc.fms.service.model.Measurement measurementModel, Pageable pageable)throws BusinessException;

}





















