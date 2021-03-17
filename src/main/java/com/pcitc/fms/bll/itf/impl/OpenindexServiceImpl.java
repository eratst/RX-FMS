/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: OpenindexServiceImpl
 * Date:18-3-8 下午6:23
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.bll.itf.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.pcitc.fms.bll.entity.Openindex;
import com.pcitc.fms.bll.itf.OpenindexService;
import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.dal.dao.OpenindexDao;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.service.model.Pager;

import pcitc.imp.common.ettool.utils.ObjectConverter;

/**
 * The type Openindex service.
 *
 * @version 创建时间 ：2017年6月8日 下午3:52:01  类说明
 * @author: jzx
 */
@Service
@Component
public class OpenindexServiceImpl implements OpenindexService {

	/**
	 * The Openindex dao.
	 */
	@Autowired
	private OpenindexDao openindexDaoImpl;

	/**
	 * Gets page openindexs.
	 *
	 * @param openindex the openindex
	 * @return the page openindexs
	 * @throws BusinessException the business exception
	 */
	@Override
	public Pager<Openindex> getPageOpenindexs(com.pcitc.fms.service.model.Openindex openindex) throws BusinessException {
		//创建 Pageable
		Pageable pageable = getPageable(openindex);
		//传入dao层
		MyPageImpl properties  = openindexDaoImpl.findPageOpenindexs(openindex ,pageable);
		//对返回做处理
		List<Openindex> openindexEntityList = null;
		try {
			openindexEntityList = ObjectConverter.listConverter(properties.getContent(),Openindex.class);
		} catch (Exception e) {
			throw  new BusinessException("","",e.getMessage());
		}
		Pager<com.pcitc.fms.bll.entity.Openindex> pageData = new Pager<>();
		pageData.setContent(openindexEntityList);
		pageData.setFirst(properties.isFirst());
		pageData.setLast(properties.isLast());
		pageData.setNumber(properties.getNumber());
		pageData.setNumberOfElements(properties.getNumberOfElements());
		pageData.setSize(properties.getSize());
		pageData.setSort(properties.getSort());
		pageData.setTotalElements(properties.getCount());
		pageData.setTotalPages(properties.getTotalPages());
	    return pageData;
	}

	/**
	 * Gets pageable.
	 *
	 * @param openindex the openindex
	 * @return the pageable
	 * @throws BusinessException the business exception
	 */
	private Pageable getPageable(com.pcitc.fms.service.model.Openindex openindex) throws BusinessException {
		Pageable  pageable  = null;
		Sort sort = new Sort(Sort.Direction.ASC, "openindexId");
		//分页校验
		if (null != openindex.getTop() && null != openindex.getSkip()) {
			pageable = PageUtil.pageable(openindex.getTop(), openindex.getSkip(), sort);
		}
		return pageable;
	}
}
