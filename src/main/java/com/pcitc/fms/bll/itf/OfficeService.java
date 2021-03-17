/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: OfficeService
 * Date:18-3-5 上午10:30
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.bll.itf;

import com.pcitc.fms.exception.BusinessException;
import java.util.List;

import org.springframework.data.domain.Pageable;

import com.pcitc.fms.bll.entity.Office;
import com.pcitc.fms.service.model.Pager;

 /**
 * Title: OfficeService
* Description: TODO task mark zhenqiang.zhao
 * @author zhenqiang.zhao
 * @date 2017年11月21日
 * @version 1.0
 */
public interface OfficeService {

	 public Pager<Office> getOffices(com.pcitc.fms.service.model.Office Office, Pageable pageable)throws BusinessException;

	 public Office getOfficeByCode(String officeCode)throws BusinessException;

}
