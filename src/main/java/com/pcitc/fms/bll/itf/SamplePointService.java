/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: SamplePointService
 * Date:18-3-5 上午10:42
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.bll.itf;

import com.pcitc.fms.exception.BusinessException;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pcitc.fms.bll.entity.NodeDictionary;
import com.pcitc.fms.bll.entity.SamplePoint;
import com.pcitc.fms.service.model.Pager;

/** 
* @author: jzx
* @version 创建时间：2017年6月8日 下午1:47:05 
* 类说明 
*/
@Service
public interface SamplePointService {

	public Pager<com.pcitc.fms.bll.entity.SamplePoint> getPageSamplePoints(com.pcitc.fms.service.model.SamplePoint samplePointModel, Pageable pageable)throws BusinessException;

}
