/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: LoadingDockService
 * Date:18-3-5 上午10:27
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.bll.itf;

import java.util.List;

import org.springframework.data.domain.Pageable;
import com.pcitc.fms.bll.entity.LoadingDock;
import com.pcitc.fms.service.model.Pager;

import com.pcitc.fms.exception.BusinessException;


public interface LoadingDockService {


  public Pager<LoadingDock> getLoadingDocksByModel(
      com.pcitc.fms.service.model.LoadingDock loadingDockModel, Pageable pageable) throws BusinessException;

}
