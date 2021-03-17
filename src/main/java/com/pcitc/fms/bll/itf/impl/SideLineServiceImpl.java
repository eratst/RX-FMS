/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: SideLineServiceImpl
 * Date:18-3-8 下午6:24
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.bll.itf.impl;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.bll.entity.SideLine;
import com.pcitc.fms.bll.entity.Tank;
import com.pcitc.fms.bll.itf.SideLineService;
import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.dal.dao.SideLineDao;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.fms.service.model.SideLineStr;

import pcitc.imp.common.ettool.utils.ObjectConverter;

/**
 * The type Side line service.
 */
@Service
@Component
public class SideLineServiceImpl implements SideLineService {

	/**
	 * The constant log.
	 */
	private static Logger log = LoggerFactory.getLogger(SideLineServiceImpl.class);
	/**
	 * The Side line dao.
	 */
	@Autowired
	private SideLineDao sideLineDao;

	/**
	 * Gets side line code.
	 *
	 * @param nodeCode the node code
	 * @param parentCode the parent code
	 * @param sideLineCode the side line code
	 * @param parentType the parent type
	 * @return the side line code
	 * @throws BusinessException the business exception
	 */
	@Override
	public List<com.pcitc.fms.bll.entity.SideLine> getSideLineCode(String nodeCode,String parentCode, String sideLineCode,String parentType)throws BusinessException {
		List<com.pcitc.fms.bll.entity.SideLine> sideLineEntityList = new ArrayList<>();
		return sideLineEntityList;
	}


	/**
	 * Gets side line model.
	 *
	 * @param sideLineModel the side line model
	 * @param pageable the pageable
	 * @param areaCode the area code
	 * @return the side line model
	 * @throws BusinessException the business exception
	 */
	@Override
	public Pager<com.pcitc.fms.bll.entity.SideLine> getSideLineModel(com.pcitc.fms.service.model.SideLine sideLineModel, Pageable pageable) throws BusinessException {
		List<com.pcitc.fms.bll.entity.SideLine> sideLineEntityList = new ArrayList<>();
			
		MyPageImpl properties = null;
		try {
			properties = sideLineDao.getSideLineModels(sideLineModel,pageable);
		} catch (Exception e) {
			if (e instanceof UndeclaredThrowableException) {
				throw new BusinessException("", e.getCause().getMessage());
			}
		}
		try {
			sideLineEntityList = ObjectConverter.listConverter(properties.getContent(), SideLine.class);
		} catch (Exception e) {
			log.error("fail",e);
			throw  new BusinessException("","",e.getMessage());
		}
		Pager<com.pcitc.fms.bll.entity.SideLine> pageData = new Pager<>();
			pageData.setContent(sideLineEntityList);
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
	 * Gets side lines.
	 *
	 * @param modelStr the model str
	 * @return the side lines
	 * @throws BusinessException the business exception
	 */
	@Override
	@Transactional(rollbackFor = BusinessException.class)
	public List<SideLine> getSideLines(SideLineStr modelStr) throws BusinessException {
		List<SideLine> sideLineList_entity = new ArrayList<>();
		try {
			List<com.pcitc.fms.dal.pojo.SideLine> sideLineList = new ArrayList<>();
//			if(modelStr.getCode()!=null){
//				sideLineList =sideLineDao.findLinkSideLines(modelStr.getParentCode(),modelStr.getParentType(),modelStr.getCode());
//			}else{						
//				sideLineList =sideLineDao.findLinkSideLines(modelStr.getParentCode(),modelStr.getParentType());
//			}
			sideLineList_entity = ObjectConverter.listConverter(sideLineList,Tank.class);
		}catch (Exception e) {
			log.error("fail",e);
			throw  new BusinessException("","",e.getMessage());
		}
	
		return sideLineList_entity;
	}

	/**
	 * Gets link side lines.
	 *
	 * @param modelStr the model str
	 * @return the link side lines
	 * @throws BusinessException the business exception
	 */
	@Override
	@Transactional(rollbackFor = BusinessException.class)
	public List<SideLine> getLinkSideLines(SideLineStr modelStr) throws BusinessException {
		 List<SideLine> stockEntity = new ArrayList<>();
		 try {
//			List<com.pcitc.fms.dal.pojo.SideLine> siloList = sideLineDao.findLinkSideLines(modelStr.getParentCode(),modelStr.getParentType());
//			stockEntity = ObjectConverter.listConverter(siloList,SideLine.class);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
		return stockEntity;
	}

	

}
