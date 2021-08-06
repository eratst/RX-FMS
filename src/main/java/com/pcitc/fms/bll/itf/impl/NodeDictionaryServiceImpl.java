/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: NodeDictionaryServiceImpl
 * Date:18-3-8 下午6:22
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.bll.itf.impl;

import com.pcitc.fms.service.handler.NodeDictionaryHandler;

import java.lang.reflect.UndeclaredThrowableException;
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

import com.pcitc.fms.bll.entity.AreaDictionary;
import com.pcitc.fms.bll.entity.LoadingDock;
import com.pcitc.fms.bll.entity.NodeDictionary;
import com.pcitc.fms.bll.itf.AdministrationService;
import com.pcitc.fms.bll.itf.AreaDictionaryService;
import com.pcitc.fms.bll.itf.NodeDictionaryService;
import com.pcitc.fms.common.CheckError;
import com.pcitc.fms.common.HostService;
import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.dal.dao.AdministrationDao;
import com.pcitc.fms.dal.dao.AreaDictionaryDao;
import com.pcitc.fms.dal.dao.FactoryDao;
import com.pcitc.fms.dal.dao.NodeDictionaryDao;
import com.pcitc.fms.dal.dao.OrgRelationDao;
import com.pcitc.fms.dal.pojo.Factory;
import com.pcitc.fms.service.model.Administration;
import com.pcitc.fms.service.model.OrgRelation;
import com.pcitc.fms.service.model.OrgUnitModel;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.imp.common.exception.BusiException;

import com.pcitc.fms.exception.BusinessException;
import pcitc.imp.common.ettool.utils.ObjectConverter;
import pcitc.imp.common.ettool.utils.RestfulTool;

/**
 * The type Node dictionary service.
 *
 * @version 创建时间 ：2017年9月22日   类说明
 * @author: 韩啸
 */
@Service
@Component
public class NodeDictionaryServiceImpl implements NodeDictionaryService {

	/**
	 * The constant log.
	 */
	private static Logger log = LoggerFactory.getLogger(NodeDictionaryServiceImpl.class);
	/**
	 * The Node dictionary dao.
	 */
	@Autowired
	private NodeDictionaryDao nodeDictionaryDao;

	/**
	 * Gets node dictionary by code.
	 *
	 * @param nodeDictionaryCode the node dictionary code
	 * @return the node dictionary by code
	 * @throws BusinessException the business exception
	 */
	@Override
	public List<NodeDictionary> getNodeDictionaryByCode(String nodeDictionaryCode) throws BusinessException {
		List<com.pcitc.fms.dal.pojo.NodeDictionary> nodeDictionaryPojoList =  nodeDictionaryDao.getNodeDictionaryByNodeCodes(nodeDictionaryCode);
		List<NodeDictionary> entityList = null;
		try {
			entityList = ObjectConverter.listConverter(nodeDictionaryPojoList, NodeDictionary.class);
		} catch (Exception e) {
			log.error("fail",e);
			throw  new BusinessException("","",e.getMessage());
		}
		return entityList;
	}

	/**
	 * Gets node dictionary by code.
	 * @param areaDictionaryTableCode the area dictionary table code
	 * @param nodeDictionaryCode the node dictionary code
	 * @return the node dictionary by code
	 * @throws BusinessException the business exception
	 */
	@Override
	public List<NodeDictionary> getNodeDictionaryByCode(String areaDictionaryTableCode, String nodeDictionaryCode) throws BusinessException {
		List<com.pcitc.fms.dal.pojo.NodeDictionary> nodeDictionaryPojoList =  nodeDictionaryDao.getNodeDictionaryByNodeCode(areaDictionaryTableCode, nodeDictionaryCode);
		List<NodeDictionary> entityList = null;
		try {
			entityList = ObjectConverter.listConverter(nodeDictionaryPojoList, NodeDictionary.class);
		} catch (Exception e) {
			log.error("fail",e);
			throw  new BusinessException("","",e.getMessage());
		}
		return entityList;
	}

	/**
	 * Gets node dictionary tables by model.
	 *
	 * @param nodeDictionaryTableModel the node dictionary table model
	 * @param pageable the pageable
	 * @return the node dictionary tables by model
	 * @throws BusinessException the business exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Pager<NodeDictionary> getNodeDictionaryTablesByModel(
		com.pcitc.fms.service.model.NodeDictionary nodeDictionaryTableModel, Pageable pageable) throws BusinessException {
		Pager<com.pcitc.fms.bll.entity.NodeDictionary> nodeDictionaryTablePager = new Pager<>();
	    List<com.pcitc.fms.bll.entity.NodeDictionary> nodeDictionaryTableEntity = new ArrayList<>();
		try{
			MyPageImpl nodeDictionaryTablePojoPageList = null;
		    try {
		    	nodeDictionaryTablePojoPageList = nodeDictionaryDao.getNodeDictionaryTablesByModel(nodeDictionaryTableModel,pageable);
			} catch (Exception e) {
				if (e instanceof UndeclaredThrowableException) {
					throw new BusinessException("", e.getCause().getMessage());
				}
			}
		    
		    nodeDictionaryTableEntity = ObjectConverter.listConverter(nodeDictionaryTablePojoPageList.getContent(), com.pcitc.fms.bll.entity.NodeDictionary.class);
		    for(int i=0;i<nodeDictionaryTableEntity.size();i++){
		    	nodeDictionaryTableEntity.get(i).setInUse(((com.pcitc.fms.dal.pojo.NodeDictionary) nodeDictionaryTablePojoPageList.getContent().get(i)).getDataStatus());
		    }
		    nodeDictionaryTablePager.setContent(nodeDictionaryTableEntity);
		    nodeDictionaryTablePager.setFirst(nodeDictionaryTablePojoPageList.isFirst());
		    nodeDictionaryTablePager.setLast(nodeDictionaryTablePojoPageList.isLast());
		    nodeDictionaryTablePager.setNumber(nodeDictionaryTablePojoPageList.getNumber());
		    nodeDictionaryTablePager.setNumberOfElements(nodeDictionaryTablePojoPageList.getNumberOfElements());
		    nodeDictionaryTablePager.setSize(nodeDictionaryTablePojoPageList.getSize());
		    nodeDictionaryTablePager.setSort(nodeDictionaryTablePojoPageList.getSort());
		    nodeDictionaryTablePager.setTotalElements((long)nodeDictionaryTablePojoPageList.getCount());
		    nodeDictionaryTablePager.setTotalPages(nodeDictionaryTablePojoPageList.getTotalPages());
		}catch(Exception e){
			throw  new BusinessException("","",e.getMessage());
		}
		return nodeDictionaryTablePager;
	}
}
