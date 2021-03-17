/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: WorkshopService
 * Date:18-3-5 上午10:47
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.bll.itf;

import com.pcitc.fms.exception.BusinessException;
import java.util.List;

import org.springframework.data.domain.Pageable;

import com.pcitc.fms.bll.entity.Workshop;
import com.pcitc.fms.service.model.Pager;

/**
 * Title: WorkshopService Description: TODO task mark zhenqiang.zhao
 *
 * @author zhenqiang.zhao
 * @version 1.0
 * @date 2017年11月21日
 */
public interface WorkshopService {

	/**
	 * Gets workshops.
	 *
	 * @param Workshop the workshop
	 * @param pageable the pageable
	 * @return the workshops
	 * @throws BusinessException the exception
	 */
	public Pager<Workshop> getWorkshops(com.pcitc.fms.service.model.Workshop Workshop, Pageable pageable)throws BusinessException;

	/**
	 * Gets workshop by code.
	 *
	 * @param workshopCode the workshop code
	 * @return the workshop by code
	 * @throws BusinessException the exception
	 */
	public Workshop getWorkshopByCode(String workshopCode)throws BusinessException;


}
