/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: NodeIndexTypeServiceImpl
 * Date:18-3-8 下午6:23
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.bll.itf.impl;

import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.service.handler.NodeDictionaryHandler;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcitc.fms.bll.itf.NodeIndexTypeService;
import com.pcitc.fms.dal.dao.NodeIdxTypeDao;
import com.pcitc.fms.dal.pojo.NodeIdxType;

import pcitc.imp.common.ettool.utils.ObjectConverter;

/**
 * The type Node index type service.
 */
@Service
public class NodeIndexTypeServiceImpl implements NodeIndexTypeService{

	/**
	 * The constant log.
	 */
	private static Logger log = LoggerFactory.getLogger(NodeIndexTypeServiceImpl.class);
	/**
	 * The Node idx type dao.
	 */
	@Autowired
	private NodeIdxTypeDao nodeIdxTypeDao;

	/**
	 * Gets by idx type.
	 *
	 * @param type the type
	 * @return the by idx type
	 * @throws BusinessException the business exception
	 */
	@Override
	public List<com.pcitc.fms.service.model.NodeIdxType> getByIdxType(String type) throws BusinessException {
		List<com.pcitc.fms.dal.pojo.NodeIdxType> IdxTypePojos = nodeIdxTypeDao.getByIdxType(type);
		List<com.pcitc.fms.service.model.NodeIdxType> modelList = null;
		try {
			modelList = ObjectConverter.listConverter(IdxTypePojos, com.pcitc.fms.service.model.NodeIdxType.class);
		} catch (Exception e) {
			log.error("fail",e);
			throw  new BusinessException("","",e.getMessage());
		}
		return modelList;
	}
}
