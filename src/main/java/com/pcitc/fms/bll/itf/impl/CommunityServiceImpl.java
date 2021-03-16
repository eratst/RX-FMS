/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: CommunityServiceImpl
 * Date:18-3-8 下午6:20
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.bll.itf.impl;

import java.lang.reflect.UndeclaredThrowableException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.bll.itf.CommunityService;
import com.pcitc.fms.common.CheckError;
import com.pcitc.fms.common.CheckType;
import com.pcitc.fms.common.HostService;
import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.dal.dao.AreaDictionaryDao;
import com.pcitc.fms.dal.dao.CommunityDao;
import com.pcitc.fms.dal.dao.CommunityDaoImpl;
import com.pcitc.fms.dal.dao.DbPrimaryIdDao;
import com.pcitc.fms.dal.dao.FactoryDao;
import com.pcitc.fms.dal.dao.InclusionDao;
import com.pcitc.fms.dal.dao.NodeDictionaryDao;
import com.pcitc.fms.dal.dao.OrgRelationDao;
import com.pcitc.fms.dal.pojo.AreaDictionary;
import com.pcitc.fms.dal.pojo.Community;
import com.pcitc.fms.dal.pojo.Factory;
import com.pcitc.fms.dal.pojo.Inclusion;
import com.pcitc.fms.dal.pojo.NodeDictionary;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.exception.BusinessExceptionMessage;
import com.pcitc.fms.service.model.OrgUnitModel;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.imp.common.exception.BusiException;

import pcitc.imp.common.ettool.utils.ObjectConverter;
import pcitc.imp.common.ettool.utils.RestfulTool;

/**
 * The type Community service.
 */
@Service
@Component
public class CommunityServiceImpl implements CommunityService {

	/**
	 * The constant log.
	 */
	private static Logger log = LoggerFactory.getLogger(CommunityServiceImpl.class);
	/**
	 * The Community dao.
	 */
	@Autowired
	private CommunityDao communityDao;

	/**
	 * Gets page communities.
	 *
	 * @param modelStr the model str
	 * @param pageable the pageable
	 * @return the page communities
	 * @throws BusinessException the business exception
	 */
		@Override
		@Transactional(rollbackFor=BusinessException.class)
		public Pager<com.pcitc.fms.bll.entity.Community> getPageCommunities(com.pcitc.fms.service.model.Community modelStr, Pageable pageable) throws BusinessException {
			MyPageImpl properties  = null;
			List<com.pcitc.fms.bll.entity.Community> communityEntityList = null;
			try {
				properties = communityDao.findPages(modelStr ,pageable);
			} catch (Exception e) {
				if (e instanceof UndeclaredThrowableException) {
					throw new BusinessException("", e.getCause().getMessage());
				}
			}
			try {
				communityEntityList = ObjectConverter.listConverter(properties.getContent(),com.pcitc.fms.bll.entity.Community.class);
			} catch (Exception e) {
				log.error("fail",e);
				throw  new BusinessException("","",e.getMessage());
			}
			Pager<com.pcitc.fms.bll.entity.Community> pageData = new Pager<>();
			pageData.setContent(communityEntityList);
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

	

