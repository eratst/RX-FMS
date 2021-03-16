/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: DeupdownlimitServiceImpl
 * Date:18-3-8 下午6:21
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.bll.itf.impl;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.pcitc.fms.bll.entity.Deupdownlimit;
import com.pcitc.fms.bll.itf.DeupdownlimitService;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.dal.dao.DeupdownlimitDaoImpl;
import com.pcitc.fms.exception.BusinessException;

import pcitc.imp.common.ettool.utils.ObjectConverter;

/**
 * The type Deupdownlimit service.
 *
 * @version 创建时间 ：2017年6月8日 下午3:52:01  类说明
 * @author: hanxiao
 */
@Service
@Component
public class DeupdownlimitServiceImpl implements DeupdownlimitService {

	/**
	 * The constant log.
	 */
	private static Logger log = LoggerFactory.getLogger(DeupdownlimitServiceImpl.class);
	/**
	 * The Deupdownlimit dao.
	 */
	@Autowired
private DeupdownlimitDaoImpl deupdownlimitDaoImpl;

	/**
	 * Gets page deupdownlimits.
	 *
	 * @param deupdownlimit the deupdownlimit
	 * @return the page deupdownlimits
	 * @throws BusinessException the business exception
	 * @throws SQLException the sql exception
	 */
	@Override
	public List<Deupdownlimit> getPageDeupdownlimits(com.pcitc.fms.service.model.Deupdownlimit deupdownlimit)
			throws BusinessException, SQLException {
		//创建 Pageable
		Pageable pageable = getPageable(deupdownlimit);
		//传入dao层
		List<com.pcitc.fms.dal.pojo.Deupdownlimit> pojoList  = deupdownlimitDaoImpl.findPageDeupdownlimits(deupdownlimit);
		//对返回做处理
		List<Deupdownlimit> deupdownlimitEntityList = null;
		try {
			deupdownlimitEntityList = ObjectConverter.listConverter(pojoList,Deupdownlimit.class);
		} catch (Exception e) {
			log.error("fail",e);
			throw  new BusinessException("","",e.getMessage());
		}
//		Pager<com.pcitc.fms.bll.entity.Deupdownlimit> pageData = new Pager<>();
//		pageData.setContent(deupdownlimitEntityList);
//		pageData.setFirst(properties.isFirst());
//		pageData.setLast(properties.isLast());
//		pageData.setNumber(properties.getNumber());
//		pageData.setNumberOfElements(properties.getNumberOfElements());
//		pageData.setSize(properties.getSize());
//		pageData.setSort(properties.getSort());
//		pageData.setTotalElements(properties.getTotalElements());
//		pageData.setTotalPages(properties.getTotalPages());
	    return deupdownlimitEntityList;
	}

	/**
	 * Gets pageable.
	 *
	 * @param model the model
	 * @return the pageable
	 * @throws BusinessException the business exception
	 */
	private Pageable getPageable(com.pcitc.fms.service.model.Deupdownlimit model) throws BusinessException {
		Pageable  pageable  = null;
		Sort sort = new Sort(Sort.Direction.ASC, "deupdownlimitId");
		//分页校验
		if (null != model.getTop() && null != model.getSkip()) {
			pageable = PageUtil.pageable(model.getTop(), model.getSkip(), sort);
		}
		return pageable;
	}

}
