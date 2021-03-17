/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: PostionServiceImpl
 * Date:18-3-8 下午6:23
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.bll.itf.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.bll.itf.PostionService;
import com.pcitc.fms.common.CheckError;
import com.pcitc.fms.dal.dao.PostionDao;

import com.pcitc.fms.exception.BusinessException;
import pcitc.imp.common.ettool.utils.ObjectConverter;

/**
 * The type Postion service.
 */
@Service
@Component
public class PostionServiceImpl implements PostionService {

	/**
	 * The constant log.
	 */
	private static Logger log = LoggerFactory.getLogger(PostionServiceImpl.class);
	/**
	 * The Postion dao.
	 */
	@Autowired
	private PostionDao postionDao;

	/**
	 * Gets postions.
	 *
	 * @param postionMetaModel the postion meta model
	 * @return the postions
	 * @throws BusinessException the business exception
	 */
	@Override
	public List<com.pcitc.fms.bll.entity.PostionMeta> getPostions(com.pcitc.fms.service.model.PostionMeta postionMetaModel) throws BusinessException {
		List<com.pcitc.fms.bll.entity.PostionMeta> postionMetaList = new ArrayList<>();
		try {
			List<com.pcitc.fms.dal.pojo.PostionMeta> orgUnitMetaPojos = postionDao.findPostions(postionMetaModel);
			postionMetaList = ObjectConverter.listConverter(orgUnitMetaPojos,
					com.pcitc.fms.bll.entity.PostionMeta.class);
		} catch (Exception e) {
			log.error("fail",e);
			throw  new BusinessException("","",e.getMessage());
		}
		return postionMetaList;
	}

	/**
	 * Delete postion by id.
	 *
	 * @param postionId the postion id
	 */
	@Override
	public void deletePostionById(String postionId) {
		// TODO Auto-generated method stub
		postionDao.deleteByPostionId(Integer.valueOf(postionId));
	}

	/**
	 * Update postion.
	 *
	 * @param postionMeta the postion meta
	 * @throws BusinessException the business exception
	 */
	@Override
	public void updatePostion(List<com.pcitc.fms.bll.entity.PostionMeta> postionMeta) throws BusinessException {
		List<com.pcitc.fms.dal.pojo.PostionMeta> postionPojoList = null;
		try {
			postionPojoList = ObjectConverter.listConverter(postionMeta,
          com.pcitc.fms.dal.pojo.PostionMeta.class);
		} catch (Exception e) {
			log.error("fail",e);
			throw  new BusinessException("","",e.getMessage());
		}
		com.pcitc.fms.dal.pojo.PostionMeta postionPojo = postionPojoList.get(0);
		com.pcitc.fms.dal.pojo.PostionMeta oldPostionPojo = postionDao.getPostionByCode(postionPojo.getCode());
		if (null == oldPostionPojo) {
			throw new  BusinessException("", "", "positionCode:"+oldPostionPojo.getCode()+":"+CheckError.IS_NOT_EXIST);
		}
		if (null == postionPojo.getCreateTime()) {
			postionPojo.setCreateTime(oldPostionPojo.getCreateTime());
		}
		if (null == postionPojo.getParentId()) {
			postionPojo.setParentId(oldPostionPojo.getParentId());
		}
		if (null == postionPojo.getParentType()) {
			postionPojo.setParentType(oldPostionPojo.getParentType());
		}
		if (null == postionPojo.getMaintainTime()) {
			postionPojo.setMaintainTime(new Date());
		}
		if (null == postionPojo.getCreator()) {
			postionPojo.setCreator(oldPostionPojo.getCreator());
		}
		postionDao.saveAndFlush(postionPojo);
	}

	/**
	 * Add postion list.
	 *
	 * @param postionMetaList the postion meta list
	 * @return the list
	 * @throws BusinessException the business exception
	 */
	@Override
	@Transactional(rollbackFor=BusinessException.class)
	public List<com.pcitc.fms.bll.entity.PostionMeta> addPostion(List<com.pcitc.fms.bll.entity.PostionMeta> postionMetaList) throws BusinessException {
		List<com.pcitc.fms.dal.pojo.PostionMeta> postionPojos = null;
		try {
			postionPojos = ObjectConverter.listConverter(postionMetaList,
          com.pcitc.fms.dal.pojo.PostionMeta.class);
		} catch (Exception e) {
			log.error("fail",e);
			throw  new BusinessException("","",e.getMessage());
		}
		for (com.pcitc.fms.dal.pojo.PostionMeta postionPojo : postionPojos) {
			List<com.pcitc.fms.dal.pojo.PostionMeta> find = postionDao.findByCode(postionPojo.getCode());
			if(find.size()>0){
				throw new BusinessException("", "","code已存在");
			}
		}
		List<com.pcitc.fms.dal.pojo.PostionMeta> save =postionDao.save(postionPojos);
		List<com.pcitc.fms.bll.entity.PostionMeta> postionEntitys= null;
		try {
			postionEntitys = ObjectConverter.listConverter(save,com.pcitc.fms.bll.entity.UserMeta.class);
		} catch (Exception e) {
			log.error("fail",e);
			throw  new BusinessException("","",e.getMessage());
		}
		return postionEntitys;
	}

	/**
	 * Gets postion.
	 *
	 * @param postionId the postion id
	 * @return the postion
	 * @throws BusinessException the business exception
	 */
	@Override
	public List<com.pcitc.fms.bll.entity.PostionMeta> getPostion(String postionId) throws BusinessException {

		List<com.pcitc.fms.bll.entity.PostionMeta> postionMetaList = new ArrayList<>();
		try {
			List<com.pcitc.fms.dal.pojo.PostionMeta> factorylist = postionDao
					.getPostionById(Integer.valueOf(postionId));
			postionMetaList = ObjectConverter.listConverter(factorylist, com.pcitc.fms.bll.entity.PostionMeta.class);
		} catch (Exception e) {
			log.error("fail",e);
			throw  new BusinessException("","",e.getMessage());
		}
		return postionMetaList;

	}
}
