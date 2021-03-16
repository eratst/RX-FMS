/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: EnterpriseServiceImpl
 * Date:18-3-8 下午6:21
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.bll.itf.impl;

import java.lang.reflect.UndeclaredThrowableException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.bll.entity.Enterprise;
import com.pcitc.fms.bll.itf.EnterpriseService;
import com.pcitc.fms.common.DataException;
import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.dal.dao.BizOrgDTLDao;
import com.pcitc.fms.dal.dao.DbPrimaryIdDao;
import com.pcitc.fms.dal.dao.EnterpriseDao;
import com.pcitc.fms.dal.dao.TPmOrgDao;
import com.pcitc.fms.dal.pojo.TPmBizOrgDTL;
import com.pcitc.fms.dal.pojo.TPmOrg;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.exception.BusinessExceptionMessage;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.imp.common.exception.BusiException;

import pcitc.imp.common.ettool.utils.ObjectConverter;

/**
 * Title: EnterpriseServiceImpl Description: TODO task mark zhenqiang.zhao
 *
 * @author zhenqiang.zhao
 * @version 1.0
 * @date 2017年11月21日
 */
@Service
public class EnterpriseServiceImpl implements EnterpriseService {

	/**
	 * The constant log.
	 */
	private static Logger log = LoggerFactory.getLogger(EnterpriseServiceImpl.class);
	/**
	 * The Enterprise dao.
	 */
	@Autowired
	private  EnterpriseDao enterpriseDao;
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
	 * Gets enterprises.
	 *
	 * @param enterpriseModel the enterprise model
	 * @param pageable the pageable
	 * @return the enterprises
	 * @throws BusinessException the business exception
	 */
	@Override
	@Transactional(rollbackFor = BusinessException.class)
	public Pager<Enterprise> getEnterprises(com.pcitc.fms.service.model.Enterprise enterpriseModel,Pageable pageable) throws BusinessException {
		Pager<com.pcitc.fms.bll.entity.Enterprise>  pageData = new Pager<>();
		MyPageImpl properties = null;
		List<Enterprise> EntityList = null;
		try {
			properties = enterpriseDao.findEnterprises(enterpriseModel,pageable);
			EntityList = ObjectConverter.listConverter(properties.getContent(),Enterprise.class);
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
	 * Gets enterprise by code.
	 *
	 * @param orgCode the org code
	 * @return the enterprise by code
	 * @throws BusinessException the business exception
	 */
	@Override
	@Transactional(rollbackFor = BusinessException.class)
	public Enterprise getEnterpriseByCode(String orgCode)throws BusinessException {
		com.pcitc.fms.dal.pojo.Enterprise enterprise =  enterpriseDao.getEnterpriseByOrgCode(orgCode);
			if(null == enterprise){
					 throw new BusinessException("",orgCode,":不存在该组织机构编码");
			}
		try {
			return ObjectConverter.entityConverter(enterprise, Enterprise.class);
		} catch (Exception e) {
			log.error("fail",e);
			throw  new BusinessException("","",e.getMessage());
		}
	}


}
