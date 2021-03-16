/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: OpenindexService
 * Date:18-3-5 上午10:31
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.bll.itf;

import com.pcitc.fms.bll.entity.Openindex;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.service.model.Pager;
import org.springframework.stereotype.Service;

@Service
public interface OpenindexService {

	public Pager<Openindex> getPageOpenindexs(com.pcitc.fms.service.model.Openindex openindex) throws BusinessException;



}
