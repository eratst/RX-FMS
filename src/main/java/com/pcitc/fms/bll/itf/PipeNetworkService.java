/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: PipeNetworkService
 * Date:18-3-5 上午10:39
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.bll.itf;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.pcitc.fms.bll.entity.LoadingDock;
import com.pcitc.fms.bll.entity.PipeNetwork;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.imp.common.exception.BusiException;

import com.pcitc.fms.exception.BusinessException;


public interface PipeNetworkService {


  public Pager<com.pcitc.fms.bll.entity.PipeNetwork> getPipeNetworks(
      com.pcitc.fms.service.model.PipeNetwork pipeNetworkModel, Pageable pageable) throws BusinessException;


}
