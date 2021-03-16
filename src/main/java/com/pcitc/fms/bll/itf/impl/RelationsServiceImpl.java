/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: RelationsServiceImpl
 * Date:18-3-8 下午6:24
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.bll.itf.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.bll.entity.Relations;
import com.pcitc.fms.bll.itf.RelationsService;
import com.pcitc.fms.common.CheckUtil;
import com.pcitc.fms.common.VerifyUtil;
import com.pcitc.fms.dal.dao.RelationsDao;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.exception.BusinessExceptionMessage;
import com.pcitc.fms.service.model.Pager;

import pcitc.imp.common.ettool.utils.ObjectConverter;

/**
 * Title: RelationsServiceImpl Description:TODO
 *
 * @author zhenqiang.zhao
 * @version 1.0
 * @date 2017年6月16日
 */
@Service
public class RelationsServiceImpl implements RelationsService {

	/**
	 * The constant log.
	 */
	private static Logger log = LoggerFactory.getLogger(RelationsServiceImpl.class);
	/**
	 * The Relations dao.
	 */
	@Autowired
	private  RelationsDao relationsDao;

	/**
	 * Title: getRelations Description: TODO
	 *
	 * @param relationsModel the relations model
	 * @return relations
	 * @throws BusinessException the business exception
	 * @date 2017年6月18日
	 * @author zhenqiang.zhao
	 * @see com.pcitc.fms.bll.itf.RelationsService#getRelations(com.pcitc.fms.service.model.Relations)
	 * com.pcitc.fms.bll.itf.RelationsService#getRelations(com.pcitc.fms.service.model.Relations)
	 */
	@Override
	@Transactional(rollbackFor=BusinessException.class)
	public List<Relations> getRelations(
			com.pcitc.fms.service.model.Relations relationsModel) throws BusinessException {
		List<com.pcitc.fms.dal.pojo.Relations> RelationsList = relationsDao.getRelations(relationsModel);
		try {
			return null== RelationsList ? null :ObjectConverter.listConverter(RelationsList, Relations.class);
		} catch (Exception e) {
			log.error("fail",e);
			throw  new BusinessException("","",e.getMessage());
		}
	}

	/**
	 * Title: getRelationsById Description: TODO
	 *
	 * @param relationsCode the relations code
	 * @return relations by code
	 * @throws BusinessException the business exception
	 * @date 2017年6月18日
	 * @author zhenqiang.zhao
	 */
	@Override
	@Transactional(rollbackFor=BusinessException.class)
	public Relations getRelationsByCode(String relationsCode) throws BusinessException {
		com.pcitc.fms.dal.pojo.Relations relations= relationsDao.findByCode(relationsCode);
		try {
			return null == relations ? null : ObjectConverter.entityConverter(relations, Relations.class);
		} catch (Exception e) {
			log.error("fail",e);
			throw  new BusinessException("","",e.getMessage());
		}
	}

	/**
	 * Title: addRelations Description: TODO
	 *
	 * @param relationsModelEntityList the relations model entity list
	 * @return list
	 * @throws BusinessException the business exception
	 * @date 2017年6月18日
	 * @author zhenqiang.zhao
	 * @see com.pcitc.fms.bll.itf.RelationsService#addRelations(java.util.List)
	 * com.pcitc.fms.bll.itf.RelationsService#addRelations(java.util.List)
	 */
	@Override
	@Transactional(rollbackFor=BusinessException.class)
	public List<Relations> addRelations(List<Relations> relationsModelEntityList) throws BusinessException {
		List<com.pcitc.fms.dal.pojo.Relations> relationsListPojo = null;
		try {
			relationsListPojo = ObjectConverter.listConverter(relationsModelEntityList, com.pcitc.fms.dal.pojo.Relations.class);
		} catch (Exception e) {
			log.error("fail",e);
			throw  new BusinessException("","",e.getMessage());
		}
		for (com.pcitc.fms.dal.pojo.Relations relations : relationsListPojo) {
			BusinessException exception = CheckUtil.getUniqueRelationsException(getUniqueRelations(relations));//唯一性校验
			if (exception != null) {
				throw new BusinessException("", "", exception.getMessage());
			}
		}
		SimpleDateFormat fm = new SimpleDateFormat("yyyyMMddhhmmss");
		for (com.pcitc.fms.dal.pojo.Relations relation : relationsListPojo) {
			//用时间戳作为code
			Date time = new Date();
			String autoCode = fm.format(time);
			relation.setCode(autoCode);
		}
		relationsDao.save(relationsListPojo);
		try {
			return ObjectConverter.listConverter(relationsListPojo, Relations.class);
		} catch (Exception e) {
			log.error("fail",e);
			throw  new BusinessException("","",e.getMessage());
		}
	}

	/**
	 * Gets unique relations.
	 *
	 * @param relations the relations
	 * @return unique relations
	 * @throws BusinessException the business exception
	 * @Title: getUniqueRelations
	 * @Description: 添加数据时唯一性校验
	 * @date 2017年8月10日
	 * @return: Relations
	 * @author 赵振强
	 */
	@Transactional(rollbackFor=BusinessException.class)
	public Relations getUniqueRelations(com.pcitc.fms.dal.pojo.Relations relations) throws BusinessException {
		com.pcitc.fms.dal.pojo.Relations  relationsPojo = null;
		try {
			relationsPojo = relationsDao.getUniqueRelations(ObjectConverter.entityConverter(relations, com.pcitc.fms.service.model.Relations.class));
		} catch (Exception e) {
			log.error("fail",e);
			throw  new BusinessException("","",e.getMessage());
		}
		try {
			return  (null != relationsPojo)?ObjectConverter.entityConverter(relationsPojo, Relations.class):null;
		} catch (Exception e) {
			log.error("fail",e);
			throw  new BusinessException("","",e.getMessage());
		}
	}

	/**
	 * Title: updateRelations Description: TODO
	 *
	 * @param relations the relations
	 * @throws BusinessException the business exception
	 * @date 2017年6月18日
	 * @author zhenqiang.zhao
	 * @see com.pcitc.fms.bll.itf.RelationsService#updateRelations(com.pcitc.fms.bll.entity.Relations)
	 * com.pcitc.fms.bll.itf.RelationsService#updateRelations(com.pcitc.fms.bll.entity.Relations)
	 */
	@Override
	@Transactional(rollbackFor=BusinessException.class)
	public void updateRelations(Relations relations) throws BusinessException {
		com.pcitc.fms.dal.pojo.Relations relationsPojo = null;
		try {
			relationsPojo = ObjectConverter.entityConverter(relations, com.pcitc.fms.dal.pojo.Relations.class);
		} catch (Exception e) {
			log.error("fail",e);
			throw  new BusinessException("","",e.getMessage());
		}
		com.pcitc.fms.dal.pojo.Relations findByCode = relationsDao.findByCode(relationsPojo.getCode());
		if(null == findByCode){
			throw new BusinessException("", "", "编码"+relationsPojo.getCode()+"的数据不存在");
		}else{
			relationsPojo.setRelationId(findByCode.getRelationId());
			relationsDao.saveAndFlush(relationsPojo);
		}
	}

	/**
	 * Title: deleteRelationsByIds Description: TODO
	 *
	 * @param codeList the code list
	 * @date 2017年6月18日
	 * @author zhenqiang.zhao
	 */
	@Override
	@Transactional(rollbackFor=BusinessException.class)
	public void deleteRelationsByCodes(List<String> codeList) {
		for (String relationsCode : codeList) {
			relationsDao.deleteByCode(relationsCode);
		}
		
	}

	/**
	 * Title: deleteRelationsById Description: 根据ID删除
	 *
	 * @param code the code
	 * @throws BusinessException the business exception
	 * @date 2017年8月10日
	 * @author 赵振强
	 */
	@Override
	@Transactional(rollbackFor=BusinessException.class)
	public void deleteRelationsByCode(String code) throws BusinessException {
			try {
				relationsDao.deleteByCode(code);
			} catch (EmptyResultDataAccessException e) {
				throw new BusinessException("", "", BusinessExceptionMessage.getDataNotExist("ID", code));
			} catch (Exception e) {
				log.error(e.getMessage());
				throw e;
			}
	}


	/**
	 * Title: getPagerRelations Description: TODO task mark zhenqiang.zhao
	 *
	 * @param relationsModel the relations model
	 * @param pageable the pageable
	 * @return pager relations
	 * @throws BusinessException the business exception
	 * @date 2017年7月27日
	 * @author zhenqiang.zhao
	 * @see com.pcitc.fms.bll.itf.RelationsService#getPagerRelations(com.pcitc.fms.service.model.Relations,
	 * org.springframework.data.domain.Pageable) com.pcitc.fms.bll.itf.RelationsService#getPagerRelations(com.pcitc.fms.service.model.Relations,
	 * org.springframework.data.domain.Pageable)
	 */
	@Override
	@Transactional(rollbackFor=BusinessException.class)
	public Pager<Relations> getPagerRelations(com.pcitc.fms.service.model.Relations relationsModel, Pageable pageable) throws BusinessException {
		Page<com.pcitc.fms.dal.pojo.Relations>  properties = relationsDao.getPageRelations(relationsModel,pageable);
		List<Relations> propertiesEntity= null;
		try {
			propertiesEntity = ObjectConverter.listConverter(properties.getContent(), Relations.class);
		} catch (Exception e) {
			log.error("fail",e);
			throw  new BusinessException("","",e.getMessage());
		}
		Pager<Relations> pageData = new Pager<>();
		pageData.setContent(propertiesEntity);
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
	 * Gets relations code in.
	 *
	 * @param sourceType the source type
	 * @param sourceCodeList the source code list
	 * @param targetType the target type
	 * @return the relations code in
	 * @throws BusinessException the business exception
	 */
	@Override
	public List<Relations> getRelationsCodeIn(String sourceType, List<String> sourceCodeList,String targetType)
			throws BusinessException {
		List<com.pcitc.fms.dal.pojo.Relations> relationsList = new ArrayList<>();
		if(null == targetType || "".equals(targetType)){
			relationsList = relationsDao.getRelationsCodesIn(sourceType ,sourceCodeList);
		}else{
			relationsList = relationsDao.getRelationsCodesIn(sourceType ,sourceCodeList,targetType);
		}
		List<Relations> list = null;
		try {
			list = ObjectConverter.listConverter(relationsList, Relations.class);
		} catch (Exception e) {
			log.error("fail",e);
			throw  new BusinessException("","",e.getMessage());
		}
		return list;
	}
}
