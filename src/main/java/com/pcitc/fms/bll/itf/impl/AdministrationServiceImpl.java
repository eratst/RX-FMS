/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: AdministrationServiceImpl
 * Date:18-3-8 下午6:19
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.bll.itf.impl;

import com.pcitc.fms.bll.itf.AdministrationService;
import com.pcitc.fms.bll.itf.ProduceFactoryService;
import com.pcitc.fms.common.CheckUtil;
import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.dal.dao.AdministrationDao;
import com.pcitc.fms.dal.dao.AdministrationDaoImpl;
import com.pcitc.fms.dal.dao.AreaDictionaryDao;
import com.pcitc.fms.dal.dao.DbPrimaryIdDao;
import com.pcitc.fms.dal.dao.NodeDictionaryDao;
import com.pcitc.fms.dal.dao.TPmOrgDao;
import com.pcitc.fms.dal.pojo.AreaDictionary;
import com.pcitc.fms.dal.pojo.NodeDictionary;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.service.model.Administration;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.imp.common.exception.BusiException;

import java.lang.reflect.UndeclaredThrowableException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pcitc.imp.common.ettool.utils.ObjectConverter;

/**
 * The type Administration service.
 *
 * @version 创建时间 ：2017年6月8日 下午3:52:01 类说明
 * @author:
 */


@Service
@Component
public class AdministrationServiceImpl implements AdministrationService {

	/**
	 * The constant log.
	 */
	private static Logger log = LoggerFactory.getLogger(AdministrationServiceImpl.class);
	/**
	 * The Administration service.
	 */
	@Autowired
	private AdministrationService administrationService;
	/**
	 * The Produce factory service.
	 */
	@Autowired
	private ProduceFactoryService produceFactoryService;
	/**
	 * The Administration dao.
	 */
	@Autowired
	private AdministrationDao administrationDao;
	/**
	 * The Administration dao.
	 */
	@Autowired
	private AdministrationDaoImpl administrationDaoImpl;
	/**
	 * The Area dictionary dao.
	 */
	@Autowired
	private AreaDictionaryDao areaDictionaryDao;
	/**
	 * The Node dictionary dao.
	 */
	@Autowired
	private NodeDictionaryDao nodeDictionaryDao;
	/**
	 * The Tpm org dao.
	 */
	@Autowired
	private TPmOrgDao tpmOrgDao;

	/**
	 * 查询所有
	 *
	 * @param administrationModel the administration model
	 * @param pageable the pageable
	 * @return the page administrations
	 * @throws BusinessException the business exception
	 */
	@Override
	public Pager<com.pcitc.fms.bll.entity.Administration> getPageAdministrations(Administration administrationModel,
			Pageable pageable) throws BusinessException {
		MyPageImpl properties  = null;
		List<com.pcitc.fms.bll.entity.Administration> administrationEntityList = null;
		try {
			properties = administrationDaoImpl.findPageAdmins(administrationModel ,pageable);
		} catch (Exception e) {
			System.out.println(e.getClass().getName());
			if (e instanceof BusiException) {
				String errMessage = e.getMessage();
				throw new BusinessException("", errMessage);
			}
		}
		try {
			administrationEntityList = ObjectConverter.listConverter(properties.getContent(),com.pcitc.fms.bll.entity.Administration.class);
		} catch (Exception e) {
			throw  new BusinessException("","",e.getMessage());
		}
		Pager<com.pcitc.fms.bll.entity.Administration> pageData = new Pager<>();
		pageData.setContent(administrationEntityList);
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


}
