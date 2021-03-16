/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: HeadquarterService
 * Date:18-3-5 上午10:26
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.bll.itf;

import com.pcitc.fms.exception.BusinessException;
import java.util.List;

import org.springframework.data.domain.Pageable;

import com.pcitc.fms.bll.entity.Headquarter;
import com.pcitc.fms.service.model.Pager;

 /**
 * Title: HeadquarterService
* Description: 总部
 * @author zhenqiang.zhao
 * @date 2017年11月21日
 * @version 1.0
 */
public interface HeadquarterService {

	 public Pager<Headquarter> getHeadquarters(com.pcitc.fms.service.model.Headquarter Headquarter, Pageable pageable)throws BusinessException;

	 public Headquarter getHeadquarterByCode(String headquarterCode)throws BusinessException;

}
