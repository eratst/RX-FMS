/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: OrgFindAreaServiceImpl
 * Date:18-3-8 下午6:23
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.bll.itf.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import pcitc.imp.common.ettool.utils.ObjectConverter;

import com.pcitc.fms.bll.entity.AreaDictionary;
import com.pcitc.fms.bll.entity.Department;
import com.pcitc.fms.bll.itf.OrgFindAreaService;
import com.pcitc.fms.dal.dao.AreaDictionaryDao;
import com.pcitc.fms.dal.pojo.NodeDictionary;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.imp.common.exception.BusiException;

/**
 * The type Org find area service.
 */
@Service
public class OrgFindAreaServiceImpl implements OrgFindAreaService{

	/**
	 * The constant log.
	 */
	private static Logger log = LoggerFactory.getLogger(MaterialServiceImpl.class);
	/**
	 * The Area dictionary dao.
	 */
	@Autowired
	private AreaDictionaryDao areaDictionaryDao;
	/**
	 * The T pm org dao.
	 */
	@Autowired
	private com.pcitc.fms.dal.dao.TPmOrgDao TPmOrgDao;

	/**
	 * Gets area dictionarys.
	 *
	 * @param areaDictionaryTableModel the area dictionary table model
	 * @param pageable the pageable
	 * @param orgCode the org code
	 * @return the area dictionarys
	 * @throws BusinessException the business exception
	 */
	@Override
	public Pager<AreaDictionary> getAreaDictionarys(com.pcitc.fms.service.model.AreaDictionary areaDictionaryTableModel,
			Pageable pageable,String orgCode) throws BusinessException {
		Pager<com.pcitc.fms.bll.entity.AreaDictionary>  pageData = new Pager<>();
		Page<com.pcitc.fms.dal.pojo.AreaDictionary> properties = areaDictionaryDao.getAreas(null,pageable);
		List<AreaDictionary> EntityList = null;
		try {
			EntityList = ObjectConverter.listConverter(properties.getContent(),AreaDictionary.class);
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
		pageData.setTotalElements(properties.getTotalElements());
		pageData.setTotalPages(properties.getTotalPages());
	return pageData;
	}

	/**
	 * Gets org find area nodes.
	 *
	 * @param areaDictionaryTableModel the area dictionary table model
	 * @param pageable the pageable
	 * @param orgCode the org code
	 * @return the org find area nodes
	 * @throws BusinessException the business exception
	 */
/* @Description
	 * parameter    * @DATE 2018/1/2
   * return void
   * **/
	@Override
	public Pager<AreaDictionary> getOrgFindAreaNodes(com.pcitc.fms.service.model.AreaDictionary areaDictionaryTableModel,
			Pageable pageable,String orgCode) throws BusinessException {
		Pager<com.pcitc.fms.bll.entity.AreaDictionary>  pageData = new Pager<>();
		Page<com.pcitc.fms.dal.pojo.AreaDictionary> properties = areaDictionaryDao.getAreaDictionaryNodesModels(areaDictionaryTableModel,pageable,orgCode);
		List<AreaDictionary> EntityList = null;
		try {
			EntityList = ObjectConverter.listConverter(properties.getContent(),AreaDictionary.class);
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
		pageData.setTotalElements(properties.getTotalElements());
		pageData.setTotalPages(properties.getTotalPages());
		return pageData;
	}

}

