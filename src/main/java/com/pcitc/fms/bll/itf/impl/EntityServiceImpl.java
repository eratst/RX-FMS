/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: EntityServiceImpl
 * Date:18-3-8 下午6:21
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.bll.itf.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.bll.itf.EntityService;
import com.pcitc.fms.common.CheckError;
import com.pcitc.fms.common.CheckUtil;
import com.pcitc.fms.dal.dao.EntityDao;
import com.pcitc.fms.dal.dao.PropertyDao;
import com.pcitc.fms.dal.pojo.EntityMeta;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.fms.service.model.PropertyMeta;
import com.pcitc.imp.common.exception.BusiException;

import pcitc.imp.common.ettool.utils.ObjectConverter;

/**
 * The type Entity service.
 */
@Service
@Component
public class EntityServiceImpl implements EntityService {

	/**
	 * The constant log.
	 */
	private static Logger log = LoggerFactory.getLogger(EntityServiceImpl.class);
	/**
	 * The Entity dao.
	 */
	@Autowired
	private EntityDao entityDao;
	/**
	 * The Property dao.
	 */
	@Autowired
	private PropertyDao propertyDao;

	/**
	 * Delete entity by code.
	 *
	 * @param entityCode the entity code
	 * @throws BusinessException the business exception
	 */
	@Override
	public void deleteEntityByCode(String entityCode) throws BusinessException{
		
		if(null != entityCode){
			//com.pcitc.fms.dal.pojo.EntityMeta oldentiyPojo = entityDao.findByCode(Integer.valueOf(entityCode));
			boolean enti = CheckUtil.isNumeric(entityCode);
			if(enti==false && entityCode.trim().isEmpty()){
				throw new BusinessException("", "entity"+CheckError.NO_EXIST_ENTITY_CODE+":"+entityCode);
		
			}else{
				com.pcitc.fms.service.model.PropertyMeta propertyModel=new PropertyMeta();
				
				propertyModel.setEntityCode(entityCode);
				Page<com.pcitc.fms.dal.pojo.PropertyMeta> proe = propertyDao.findProperties(propertyModel,null);
				List<com.pcitc.fms.dal.pojo.PropertyMeta> newproe = proe.getContent();
				if(newproe==null&&newproe.size()>1){
					throw new BusinessException("", "entity"+"存在关联property"+":"+entityCode);
				}
				EntityMeta delie = entityDao.findByCode(entityCode);
				if(delie==null){
					throw new BusinessException("", "entity"+"编码不存在"+":"+entityCode);
				}
				entityDao.deleteByCode(entityCode);
			}
		}else{
			throw new BusinessException("", "entity"+CheckError.NO_EXIST_ENTITY_CODE+":"+entityCode);
		}
		}

	/**
	 * Update entity.
	 *
	 * @param entity the entity
	 * @throws BusinessException the business exception
	 */
	@Override
	public void updateEntity(List<com.pcitc.fms.bll.entity.EntityMeta> entity) throws BusinessException {
		List<EntityMeta> entityPojoList = null;
		try {
			entityPojoList = ObjectConverter.listConverter(entity,EntityMeta.class);
		} catch (Exception e) {
			log.error("fail",e);
			throw  new BusinessException("","",e.getMessage());
		}
		com.pcitc.fms.dal.pojo.EntityMeta entityPojo = entityPojoList.get(0);
		
		EntityMeta oldEntityPojo = entityDao.findByCode(entityPojo.getCode());
		//判断是否重复  判断是否存在
//				if(null == oldEntityPojo){
//					
//				}else{
//					entityPojo.setCreateTime(oldEntityPojo.getCreateTime());
//					entityPojo.setCreator(oldEntityPojo.getCreator());
//					entityPojo.setEditor(entityPojo.getEditor());
//					entityPojo.setMaintainTime(entityPojo.getMaintainTime());
					
//					if(null == entityPojo.getEnabled()){
//						entityPojo.setEnabled(oldEntityPojo.getEnabled());
//				}
//					else if(entityPojo.getEnabled()!=oldEntityPojo.getEnabled()){
//					throw new BusiException("", "Enabled"+"与原数据不匹配"+":"+entityPojo.getEnabled());
//				}
//			}
		 if (null == oldEntityPojo) {
			 throw new BusinessException("", "entitieId："+entityPojo.getCode()+"数据不存在");
			}
		 if (null == entityPojo.getMaintainTime()) {
			 entityPojo.setMaintainTime(new Date());
			}
		 if (null == entityPojo.getCreateTime()) {
			 entityPojo.setCreateTime(oldEntityPojo.getCreateTime());
			}
		 if(entityPojo.getEnabled()!=1&&entityPojo.getEnabled()!=0){
			 throw new BusinessException("", "Enabled"+"不能为空，只能为0或1");
			
		 }
		try {
			
			
			 entityDao.saveAndFlush(entityPojo);
		} catch (Exception e) {
			log.error(e.getMessage());
			if(e.toString().contains("AK_ENTITY_CODE"))
				throw new BusinessException("", CheckError.CODE_EXIST);
		}
	}

	/**
	 * Add entities list.
	 *
	 * @param entityList the entity list
	 * @return the list
	 * @throws BusinessException the business exception
	 */
	@Override
	@Transactional(rollbackFor=BusinessException.class)
	public List addEntities(List<com.pcitc.fms.bll.entity.EntityMeta> entityList) throws BusinessException {
		List<com.pcitc.fms.dal.pojo.EntityMeta> entityPojos=new ArrayList<com.pcitc.fms.dal.pojo.EntityMeta>();

		List<EntityMeta> entityPojoList = null;
		try {
			entityPojoList = ObjectConverter.listConverter(entityList,
					EntityMeta.class);
		} catch (Exception e) {
			log.error("fail",e);
			throw  new BusinessException("","",e.getMessage());
		}
		try {
				
				 entityPojos = entityDao.save(entityPojoList);
		
			
			} catch (Exception e) {
				log.error(e.getMessage());
				if(e.toString().contains("PK_ENTITY"))
					throw new BusinessException("", "id存在冲突");
				if(e.toString().contains("AK_ENTITY_CODE"))
					throw new BusinessException("", CheckError.CODE_EXIST);
			}
			
				
			//ids = entityPojos.size();
	
		
		return entityPojos;
	}

	/**
	 * Gets entities.
	 *
	 * @param findEntities the find entities
	 * @param pageable the pageable
	 * @return the entities
	 * @throws BusinessException the business exception
	 */
	@Override
	public Pager<com.pcitc.fms.bll.entity.EntityMeta> getEntities(com.pcitc.fms.service.model.EntityMeta findEntities, Pageable pageable)
			throws BusinessException {
		List<com.pcitc.fms.bll.entity.EntityMeta> entityMetaList = new ArrayList<>();
		
			Pager<com.pcitc.fms.bll.entity.EntityMeta>  pageData = new Pager<>();
//			List<com.pcitc.fms.dal.pojo.EntityMeta> properties  =  entityDao.getEntities(findEntities);
			Page<com.pcitc.fms.dal.pojo.EntityMeta> properties = entityDao.findEntities(findEntities,pageable);
		try {
			entityMetaList = ObjectConverter.listConverter(properties.getContent(), com.pcitc.fms.bll.entity.EntityMeta.class);
		} catch (Exception e) {
			log.error("fail",e);
			throw  new BusinessException("","",e.getMessage());
		}
		pageData.setContent(entityMetaList);
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
	 * Gets entity by code.
	 *
	 * @param entityCode the entity code
	 * @return the entity by code
	 * @throws BusinessException the business exception
	 */
	@Override
	public List<com.pcitc.fms.bll.entity.EntityMeta> getEntityByCode(String entityCode) throws BusinessException {
		List<com.pcitc.fms.bll.entity.EntityMeta> factory_entitylist = new ArrayList<>();
			List<com.pcitc.fms.dal.pojo.EntityMeta> factorylist = entityDao.getEntityByEntityCode(entityCode);
		try {
			factory_entitylist = ObjectConverter.listConverter(factorylist, com.pcitc.fms.bll.entity.EntityMeta.class);
		} catch (Exception e) {
			log.error("fail",e);
			throw  new BusinessException("","",e.getMessage());
		}
		return factory_entitylist;
	}

	/**
	 * Update entity.
	 *
	 * @param entity the entity
	 * @throws BusinessException the business exception
	 */
	@Override
	public void updateEntity(com.pcitc.fms.bll.entity.EntityMeta entity) throws BusinessException {
		EntityMeta  entityPojo = null;
		try {
			entityPojo = ObjectConverter.entityConverter(entity, EntityMeta.class);
		} catch (Exception e) {
			log.error("fail",e);
			throw  new BusinessException("","",e.getMessage());
		}
		List<com.pcitc.fms.dal.pojo.EntityMeta>  findEntity = entityDao.getEntityByEntityCode(entityPojo.getCode());
		if(findEntity.size() == 0) {
			throw new BusinessException("", "", "entityCode:"+entityPojo.getCode()+"不存在！");
		}
		entityDao.updateEntity(entityPojo.getCode(), 
				               entityPojo.getEntityName(), 
				               entityPojo.getEntityType(), 
				               entityPojo.getShortName(), 
				               entityPojo.getEntityTableName(),
				               entityPojo.getEnabled(), 
				               entityPojo.getDes());
		
	}

}
