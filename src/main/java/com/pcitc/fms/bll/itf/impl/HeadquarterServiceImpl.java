/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: HeadquarterServiceImpl
 * Date:18-3-8 下午6:22
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.bll.itf.impl;

import com.pcitc.fms.bll.entity.Headquarter;
import com.pcitc.fms.bll.itf.HeadquarterService;
import com.pcitc.fms.common.DataException;
import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.dal.dao.BizOrgDTLDao;
import com.pcitc.fms.dal.dao.DbPrimaryIdDao;
import com.pcitc.fms.dal.dao.HeadquarterDao;
import com.pcitc.fms.dal.dao.RentDao;
import com.pcitc.fms.dal.dao.TPmOrgDao;
import com.pcitc.fms.dal.pojo.Rent;
import com.pcitc.fms.dal.pojo.TPmBizOrgDTL;
import com.pcitc.fms.dal.pojo.TPmOrg;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.exception.BusinessExceptionMessage;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.imp.common.exception.BusiException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pcitc.imp.common.ettool.utils.ObjectConverter;

import java.lang.reflect.UndeclaredThrowableException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Title: HeadquarterServiceImpl Description: TODO task mark zhenqiang.zhao
 *
 * @author zhenqiang.zhao
 * @version 1.0
 * @date 2017年11月21日
 */
@Service
public class HeadquarterServiceImpl implements HeadquarterService {

	/**
	 * The constant log.
	 */
	private static Logger log = LoggerFactory.getLogger(HeadquarterServiceImpl.class);
	/**
	 * The Headquarter dao.
	 */
	@Autowired
	private  HeadquarterDao headquarterDao;
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

	
	@Autowired
	private RentDao rentdao;

	/**
	 * Gets headquarters.
	 *
	 * @param headquarterModel the headquarter model
	 * @param pageable the pageable
	 * @return the headquarters
	 * @throws BusinessException the business exception
	 */
	@Override
	@Transactional(rollbackFor = BusinessException.class)
	public Pager<Headquarter> getHeadquarters(com.pcitc.fms.service.model.Headquarter headquarterModel,Pageable pageable) throws BusinessException {
		Pager<com.pcitc.fms.bll.entity.Headquarter>  pageData = new Pager<>();
		MyPageImpl properties =null;
		List<Headquarter> EntityList = null;
		
		try {
			properties = headquarterDao.findHeadquarters(headquarterModel,pageable);
			EntityList = ObjectConverter.listConverter(properties.getContent(),Headquarter.class);
		} catch (Exception e) {
			if (e instanceof UndeclaredThrowableException) {
				throw new BusinessException("", e.getCause().getMessage());
			}else{
				throw new BusinessException("", e.getMessage());
			}
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
	 * Gets headquarter by code.
	 *
	 * @param orgCode the org code
	 * @return the headquarter by code
	 * @throws BusinessException the business exception
	 */
	@Override
	@Transactional(rollbackFor = BusinessException.class)
	public Headquarter getHeadquarterByCode(String orgCode)throws BusinessException {
		com.pcitc.fms.dal.pojo.Headquarter headquarter =  headquarterDao.getHeadquarterByOrgCode(orgCode);
			if(null == headquarter){
					 throw new BusinessException("",orgCode,":不存在该组织机构编码");
			}
		try {
			return ObjectConverter.entityConverter(headquarter, Headquarter.class);
		} catch (Exception e) {
			log.error("fail",e);
			throw  new BusinessException("","",e.getMessage());
		}
	}

}
