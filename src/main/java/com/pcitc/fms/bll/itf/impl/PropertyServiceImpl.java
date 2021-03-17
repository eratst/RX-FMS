/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: PropertyServiceImpl
 * Date:18-3-8 下午6:24
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.bll.itf.impl;

import com.pcitc.fms.exception.BusinessException;
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

import com.pcitc.fms.bll.itf.PropertyService;
import com.pcitc.fms.common.CheckError;
import com.pcitc.fms.common.CheckUtil;
import com.pcitc.fms.dal.dao.EntityDao;
import com.pcitc.fms.dal.dao.PropertyDao;
import com.pcitc.fms.dal.pojo.EntityMeta;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.fms.service.model.PropertyMeta;
import com.pcitc.imp.common.exception.BusiException;

import pcitc.imp.common.ettool.utils.ObjectConverter;

/**
 * The type Property service.
 */
@Service
@Component
public class PropertyServiceImpl implements PropertyService {

	/**
	 * The constant log.
	 */
	private static Logger log = LoggerFactory.getLogger(PropertyServiceImpl.class);
	/**
	 * The Property dao.
	 */
	@Autowired
	private PropertyDao propertyDao;
	/**
	 * The Entity dao.
	 */
	@Autowired
	private EntityDao entityDao;

	/**
	 * Gets properties.
	 *
	 * @param propertyModel the property model
	 * @param pageable the pageable
	 * @return the properties
	 * @throws BusinessException the business exception
	 */
	@Override
	public Pager<com.pcitc.fms.bll.entity.PropertyMeta> getProperties(
			com.pcitc.fms.service.model.PropertyMeta propertyModel, Pageable pageable) throws BusinessException {
		Pager<com.pcitc.fms.bll.entity.PropertyMeta>  pageData = new Pager<>();
		if (null != propertyModel) {
			EntityMeta entity =new EntityMeta();
			 entity = entityDao.findByCode(propertyModel.getEntityCode());
			if(null != entity){
		
				List<com.pcitc.fms.bll.entity.PropertyMeta> propertyMetaList = new ArrayList<>();
			Page<com.pcitc.fms.dal.pojo.PropertyMeta> propertyPojos = propertyDao.findProperties(propertyModel,pageable);
				try {
					propertyMetaList = ObjectConverter.listConverter(propertyPojos.getContent(),com.pcitc.fms.bll.entity.PropertyMeta.class);
				} catch (Exception e) {
					log.error("fail",e);
					throw  new BusinessException("","",e.getMessage());
				}
				pageData.setContent(propertyMetaList);
			pageData.setFirst(propertyPojos.isFirst());
			pageData.setLast(propertyPojos.isLast());
			pageData.setNumber(propertyPojos.getNumber());
			pageData.setNumberOfElements(propertyPojos.getNumberOfElements());
			pageData.setSize(propertyPojos.getSize());
			pageData.setSort(propertyPojos.getSort());
			pageData.setTotalElements(propertyPojos.getTotalElements());
			pageData.setTotalPages(propertyPojos.getTotalPages());
	
			
			}else{
				throw new BusinessException("", "EntityCode"+CheckError.NO_EXIST_ENTITY_CODE+":"+propertyModel.getEntityCode());
			}
		}
		return pageData;
	}

	/**
	 * Add properties list.
	 *
	 * @param propertyMetaList the property meta list
	 * @param entityCode the entity code
	 * @return the list
	 * @throws BusinessException the business exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=BusinessException.class)
	public List addProperties(List<com.pcitc.fms.bll.entity.PropertyMeta> propertyMetaList,String entityCode) throws BusinessException {
		List<com.pcitc.fms.dal.pojo.PropertyMeta> propertyPojos = null;
		try {
			propertyPojos = ObjectConverter.listConverter(propertyMetaList,
          com.pcitc.fms.dal.pojo.PropertyMeta.class);
		} catch (Exception e) {
			log.error("fail",e);
			throw  new BusinessException("","",e.getMessage());
		}
		List<com.pcitc.fms.dal.pojo.PropertyMeta> save=new ArrayList();
		
			List<EntityMeta> entity = entityDao.getEntityByEntityCode(entityCode); 
			if(0 == entity.size()){
				throw new BusinessException("", "EntityCode"+CheckError.NO_EXIST_ENTITY_CODE+":"+entity.get(0).getCode());
			
			}else{
				try {
					 save = propertyDao.save(propertyPojos);
				} catch (Exception e) {
					log.error(e.getMessage());
					if(e.toString().contains("AK_PROPERTY_CODE"))
						throw new BusinessException("", CheckError.CODE_EXIST);
				}
			}
			
		
		

		return save;
	}

	/**
	 * Update property.
	 *
	 * @param propertyMetaList the property meta list
	 * @throws BusinessException the business exception
	 */
	@Override
	public void updateProperty(List<com.pcitc.fms.bll.entity.PropertyMeta> propertyMetaList) throws BusinessException {
		// try {
		List<com.pcitc.fms.dal.pojo.PropertyMeta> propertyPojoList = null;
		try {
			propertyPojoList = ObjectConverter.listConverter(propertyMetaList,
          com.pcitc.fms.dal.pojo.PropertyMeta.class);
		} catch (Exception e) {
			log.error("fail",e);
			throw  new BusinessException("","",e.getMessage());
		}
		com.pcitc.fms.dal.pojo.PropertyMeta propertyPojo = propertyPojoList.get(0);
		// 20170630 更改上有方法内容
		
		EntityMeta entid = entityDao.findByCode(propertyPojo.getEntityCode());
		if(entid==null){
			throw new BusinessException("", "EntityCode"+CheckError.NO_EXIST_ENTITY_CODE+":"+propertyPojo.getEntityCode());
		}
		List<com.pcitc.fms.dal.pojo.PropertyMeta> oldPostionPojo = propertyDao.getPropertyByPropertyCode(propertyPojo.getPropertyCode());
		if(oldPostionPojo.size()==0){
			throw new BusinessException("", "property"+CheckError.NO_EXIST_ENTITY_CODE+":"+propertyPojo.getPropertyCode());
		}
		oldPostionPojo = propertyDao.getPropertyByEntityAndPropertyCode(propertyPojo.getEntityCode(),propertyPojo.getPropertyCode());
		if(oldPostionPojo.size()==0){
			throw new BusinessException("","entityCode:"+propertyPojo.getEntityCode()+"下不存在propertyCode:"+propertyPojo.getPropertyCode());
		}
		com.pcitc.fms.service.model.PropertyMeta propertyModel=new PropertyMeta();
		propertyModel.setPropertyCode(propertyPojo.getPropertyCode());
		propertyModel.setEntityCode(propertyPojo.getEntityCode());

		try {
			propertyDao.updatProperty(
					propertyPojo.getPropertyCode(),
					propertyPojo.getPropertyName(),
					propertyPojo.getType(),
					propertyPojo.getShortName(),
					propertyPojo.getEditor(),
					propertyPojo.getDes(),
					propertyPojo.getDataType());
		} catch (Exception e) {
			log.error(e.getMessage());
			if(e.toString().contains("AK_PROPERTY_CODE")){
				throw new BusinessException("", CheckError.CODE_EXIST);
		}
//			throw new BusiException("", "property"+"重复的code"+":"+propertyPojo.getPropertyCode());
		}
		
		
		
		// propertyDao.updateProperty(
		// propertyPojo.getEntityCode(),
		// propertyPojo.getShortName(),
		// propertyPojo.getPropertyCode(),
		// propertyPojo.getPropertyName(),
		// propertyPojo.getType(),
		// propertyPojo.getDataType(),
		// propertyPojo.getPropertyId(),
		// propertyPojo.getEditor());
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// throw e;
		// }

	}

	/**
	 * Delete property by code.
	 *
	 * @param propertyCode the property code
	 * @throws BusinessException the business exception
	 */
	@Override
	public void deletePropertyByCode(String propertyCode) throws BusinessException {
		if(propertyCode==null||propertyCode.equals("")){
			throw new BusinessException("", "propertyCode"+"不能为空"+":"+propertyCode);
		}
		List<com.pcitc.fms.dal.pojo.PropertyMeta> oldPostionPojo = propertyDao.getPropertyByPropertyCode(propertyCode);
		if(oldPostionPojo.size() == 0){
			throw new BusinessException("", "propertyCode"+CheckError.NO_EXIST_ENTITY_CODE+":"+propertyCode);
		}
		 propertyDao.deleteByPropertyCode(propertyCode);
//		if(rint==null){
//			throw new BusiException("", "property"+"不匹配"+":"+propertyCode);
//		}
	}

	/**
	 * 查询 实体id  属性id
	 *
	 * @param propertyCode the property code
	 * @param EntityCode the entity code
	 * @return the property
	 * @throws BusinessException the business exception
	 */
	@Override
	public List<com.pcitc.fms.bll.entity.PropertyMeta> getProperty(String propertyCode, String EntityCode) throws BusinessException {
		List<com.pcitc.fms.bll.entity.PropertyMeta> propertyMetaList = new ArrayList<>();
		
		List<EntityMeta> entity = entityDao.getEntityByEntityCode(EntityCode);
		if(0 == entity.size()){
			throw new BusinessException("", "EntityCode"+CheckError.NO_EXIST_ENTITY_CODE+":"+EntityCode);
		
		}else{
//				if(propertyCode==""||propertyCode.equals("")){
			PropertyMeta propertyModel = new PropertyMeta();
			propertyModel.setEntityCode(EntityCode);
			propertyModel.setPropertyCode(propertyCode);
			Page<com.pcitc.fms.dal.pojo.PropertyMeta> factorylist = propertyDao.findProperties(propertyModel,null);
			try {
				propertyMetaList = ObjectConverter.listConverter(factorylist.getContent(), com.pcitc.fms.bll.entity.PropertyMeta.class);
			} catch (Exception e) {
				log.error("fail",e);
				throw  new BusinessException("","",e.getMessage());
			}
//				}
		}
		
		return propertyMetaList;
	}

	/**
	 * Gets property.
	 *
	 * @param propertyCode the property code
	 * @return the property
	 * @throws BusinessException the business exception
	 */
	@Override
	public List<com.pcitc.fms.bll.entity.PropertyMeta> getProperty(String propertyCode) throws BusinessException {
		List<com.pcitc.fms.bll.entity.PropertyMeta> propertyMetaList = new ArrayList<>();
		List<com.pcitc.fms.dal.pojo.PropertyMeta> factorylist = propertyDao.getPropertyByPropertyCode(propertyCode);
		try {
			propertyMetaList = ObjectConverter.listConverter(factorylist, com.pcitc.fms.bll.entity.PropertyMeta.class);
		} catch (Exception e) {
			log.error("fail",e);
			throw  new BusinessException("","",e.getMessage());
		}
		return propertyMetaList;
	}

	/**
	 * Gets property by entity code.
	 *
	 * @param entityCode the entity code
	 * @return the property by entity code
	 * @throws BusinessException the business exception
	 */
	@Override
	public List<com.pcitc.fms.bll.entity.PropertyMeta> getPropertyByEntityCode(String entityCode) throws BusinessException {
		List<com.pcitc.fms.dal.pojo.PropertyMeta> propertyPojoList = propertyDao.getPropertyByEntityCode(entityCode);
		List<com.pcitc.fms.bll.entity.PropertyMeta> propertyEntityList = null;
		try {
			propertyEntityList = ObjectConverter.listConverter(propertyPojoList, com.pcitc.fms.bll.entity.PropertyMeta.class);
		} catch (Exception e) {
			log.error("fail",e);
			throw  new BusinessException("","",e.getMessage());
		}
		return propertyEntityList;
	}

	/**
	 * Gets property by entity and property code.
	 *
	 * @param propertyCode the property code
	 * @param entityCode the entity code
	 * @return the property by entity and property code
	 * @throws BusinessException the business exception
	 */
	@Override
	public List<com.pcitc.fms.bll.entity.PropertyMeta> getPropertyByEntityAndPropertyCode(String propertyCode,
			String entityCode) throws BusinessException {
		List<com.pcitc.fms.bll.entity.PropertyMeta> propertyMetaList = new ArrayList<>();
		List<com.pcitc.fms.dal.pojo.PropertyMeta> factorylist = propertyDao.getPropertyByEntityAndPropertyCode(propertyCode,entityCode);
		try {
			propertyMetaList = ObjectConverter.listConverter(factorylist, com.pcitc.fms.bll.entity.PropertyMeta.class);
		} catch (Exception e) {
			log.error("fail",e);
			throw  new BusinessException("","",e.getMessage());
		}
		return propertyMetaList;
	}

}
