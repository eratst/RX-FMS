/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: OfficeServiceImpl
 * Date:18-3-8 下午6:23
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.bll.itf.impl;

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

import com.pcitc.fms.bll.entity.Office;
import com.pcitc.fms.bll.itf.OfficeService;
import com.pcitc.fms.common.DataException;
import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.dal.dao.BizOrgDTLDao;
import com.pcitc.fms.dal.dao.DbPrimaryIdDao;
import com.pcitc.fms.dal.dao.OfficeDao;
import com.pcitc.fms.dal.dao.InclusionDao;
import com.pcitc.fms.dal.dao.OrgRelationDao;
import com.pcitc.fms.dal.dao.TPmOrgDao;
import com.pcitc.fms.dal.pojo.TPmBizOrgDTL;
import com.pcitc.fms.dal.pojo.TPmOrg;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.exception.BusinessExceptionMessage;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.imp.common.exception.BusiException;

import pcitc.imp.common.ettool.utils.ObjectConverter;

/**
 * Title: OfficeServiceImpl Description: TODO task mark zhenqiang.zhao
 *
 * @author zhenqiang.zhao
 * @version 1.0
 * @date 2017年11月21日
 */
@Service
public class OfficeServiceImpl implements OfficeService {

	/**
	 * The constant log.
	 */
	private static Logger log = LoggerFactory.getLogger(OfficeServiceImpl.class);
	/**
	 * The Office dao.
	 */
	@Autowired
	private  OfficeDao officeDao;
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
	 * Gets offices.
	 *
	 * @param officeModel the office model
	 * @param pageable the pageable
	 * @return the offices
	 * @throws BusinessException the business exception
	 */
	@Override
	@Transactional(rollbackFor = BusinessException.class)
	public Pager<Office> getOffices(com.pcitc.fms.service.model.Office officeModel,Pageable pageable) throws BusinessException {
		Pager<com.pcitc.fms.bll.entity.Office>  pageData = new Pager<>();
		MyPageImpl properties = officeDao.findOffices(officeModel,pageable);
		List<Office> EntityList = null;
		try {
			EntityList = ObjectConverter.listConverter(properties.getContent(),Office.class);
		} catch (Exception e) {
			log.error("fail",e);
			throw  new BusinessException("","",e.getMessage());
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
	 * Gets office by code.
	 *
	 * @param orgCode the org code
	 * @return the office by code
	 * @throws BusinessException the business exception
	 */
	@Override
	@Transactional(rollbackFor = BusinessException.class)
	public Office getOfficeByCode(String orgCode)throws BusinessException {
		com.pcitc.fms.dal.pojo.Office office =  officeDao.getOfficeByOrgCode(orgCode);
			if(null == office){
					 throw new BusinessException("",orgCode,":不存在该组织机构编码");
			}
		try {
			return ObjectConverter.entityConverter(office, Office.class);
		} catch (Exception e) {
			log.error("fail",e);
			throw  new BusinessException("","",e.getMessage());
		}
	}


}
