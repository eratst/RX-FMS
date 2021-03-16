/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: DivisionServiceImpl
 * Date:18-3-8 下午6:21
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.bll.itf.impl;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.bll.entity.Division;
import com.pcitc.fms.bll.itf.DivisionService;
import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.dal.dao.BizOrgDTLDao;
import com.pcitc.fms.dal.dao.DbPrimaryIdDao;
import com.pcitc.fms.dal.dao.DivisionDao;
import com.pcitc.fms.dal.dao.TPmOrgDao;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.service.model.Pager;

import pcitc.imp.common.ettool.utils.ObjectConverter;

/**
 * Title: DivisionServiceImpl Description: TODO task mark zhenqiang.zhao
 *
 * @author zhenqiang.zhao
 * @version 1.0
 * @date 2017年11月21日
 */
@Service
public class DivisionServiceImpl implements DivisionService {

	/**
	 * The constant log.
	 */
	private static Logger log = LoggerFactory.getLogger(DivisionServiceImpl.class);
	/**
	 * The Division dao.
	 */
	@Autowired
	private  DivisionDao divisionDao;
	/**
	 * The T pm org dao.
	 */
	@Autowired
	private TPmOrgDao tPmOrgDao;
	/**
	 * The Db primary id dao.
	 */
	@Autowired
	private DbPrimaryIdDao dbPrimaryIdDao;
	/**
	 * The Bizorgdtldao.
	 */
	@Autowired
	private BizOrgDTLDao bizorgdtldao;

	/**
	 * Gets divisions.
	 *
	 * @param divisionModel the division model
	 * @param pageable the pageable
	 * @return the divisions
	 * @throws BusinessException the business exception
	 */
	@Override
	@Transactional(rollbackFor = BusinessException.class)
	public Pager<Division> getDivisions(com.pcitc.fms.service.model.Division divisionModel,Pageable pageable) throws BusinessException {
		Pager<com.pcitc.fms.bll.entity.Division>  pageData = new Pager<>();
		MyPageImpl properties=null;
		List<Division> EntityList = null;
		try {
			properties = divisionDao.findDivisions(divisionModel,pageable);
			EntityList = ObjectConverter.listConverter(properties.getContent(),Division.class);
		} catch (Exception e) {
			if (e instanceof UndeclaredThrowableException) {
				throw new BusinessException("", e.getCause().getMessage());
			}
			throw new BusinessException("", e.getMessage());
		}
		pageData.setContent(EntityList);
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
	 * Gets division by code.
	 *
	 * @param orgCode the org code
	 * @return the division by code
	 * @throws BusinessException the business exception
	 */
	@Override
	@Transactional(rollbackFor = BusinessException.class)
	public Division getDivisionByCode(String orgCode)throws BusinessException {
		com.pcitc.fms.dal.pojo.Division division =  divisionDao.getDivisionByOrgCode(orgCode);
			if(null == division){
					 throw new BusinessException("",orgCode,":不存在该组织机构编码");
			}
		try {
			return ObjectConverter.entityConverter(division, Division.class);
		} catch (Exception e) {
			log.error("fail",e);
			throw  new BusinessException("","",e.getMessage());
		}
	}


 }
