/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: PipeNetworkServiceImpl
 * Date:18-3-8 下午6:23
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.bll.entity.LoadingDock;
import com.pcitc.fms.bll.entity.PipeNetwork;
import com.pcitc.fms.bll.itf.PipeNetworkService;
import com.pcitc.fms.common.CheckError;
import com.pcitc.fms.common.HostService;
import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.dal.dao.AreaDictionaryDao;
import com.pcitc.fms.dal.dao.DbPrimaryIdDao;
import com.pcitc.fms.dal.dao.FactoryDao;
import com.pcitc.fms.dal.dao.InclusionDao;
import com.pcitc.fms.dal.dao.NodeDictionaryDao;
import com.pcitc.fms.dal.dao.OrgRelationDao;
import com.pcitc.fms.dal.dao.PipeNetworkDao;
import com.pcitc.fms.dal.dao.PipeNetworkDaoImpl;
import com.pcitc.fms.dal.pojo.AreaDictionary;
import com.pcitc.fms.dal.pojo.Factory;
import com.pcitc.fms.dal.pojo.Inclusion;
import com.pcitc.fms.dal.pojo.NodeDictionary;
import com.pcitc.fms.exception.BusinessExceptionMessage;
import com.pcitc.fms.service.model.OrgUnitModel;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.imp.common.exception.BusiException;

import com.pcitc.fms.exception.BusinessException;
import pcitc.imp.common.ettool.utils.ObjectConverter;
import pcitc.imp.common.ettool.utils.RestfulTool;

/**
 * The type Pipe network service.
 */
@Service
public class PipeNetworkServiceImpl implements PipeNetworkService {

	/**
	 * The constant log.
	 */
	private static Logger log = LoggerFactory.getLogger(PipeNetworkServiceImpl.class);
	/**
	 * The Pipe network dao.
	 */
	@Autowired
	private PipeNetworkDaoImpl pipeNetworkDaoImpl;

	/**
	 * Gets pipe network by group.
	 *
	 * @param pipeNetworkModel the pipe network model
	 * @param pageable the pageable
	 * @return the pipe network by group
	 * @throws BusinessException the business exception
	 */
//hanxiao
	@Override
	@SuppressWarnings("unchecked")
	@Transactional(rollbackFor=BusinessException.class)
	public Pager<com.pcitc.fms.bll.entity.PipeNetwork> getPipeNetworks(com.pcitc.fms.service.model.PipeNetwork pipeNetworkModel, Pageable pageable) throws BusinessException {
		List<PipeNetwork> pipeNetworkEntityList = null;	
		MyPageImpl properties  = null;
			
		try {
			properties = pipeNetworkDaoImpl.getPipeNetworks(pipeNetworkModel ,pageable);
			pipeNetworkEntityList = ObjectConverter.listConverter(properties.getContent(),PipeNetwork.class);
		} catch (Exception e) {
			if (e instanceof UndeclaredThrowableException) {
				throw new BusinessException("", e.getCause().getMessage());
			}
			throw new BusinessException("", e.getMessage());
		}
		Pager<com.pcitc.fms.bll.entity.PipeNetwork> pageData = new Pager<>();
		pageData.setContent(pipeNetworkEntityList);
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
