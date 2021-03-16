/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: PlantService
 * Date:18-3-5 上午10:39
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.bll.itf;

import com.pcitc.fms.exception.BusinessException;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pcitc.fms.bll.entity.Plant;
import com.pcitc.fms.service.model.Pager;

/**
 * @version 创建时间：2017年6月8日 下午1:47:05 类说明
 * @author: jzx
 */
@Service
public interface PlantService {


  public Pager<Plant> getPagePlants(com.pcitc.fms.service.model.Plant findPlantModel,
      Pageable pageable) throws BusinessException;


}
