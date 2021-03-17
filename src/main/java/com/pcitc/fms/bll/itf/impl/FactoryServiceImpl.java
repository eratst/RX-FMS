/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: FactoryServiceImpl
 * Date:18-3-8 下午6:22
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.bll.itf.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.bll.entity.OrgRelation;
import com.pcitc.fms.bll.entity.OrgUnitMeta;
import com.pcitc.fms.bll.itf.FactoryService;
import com.pcitc.fms.common.CheckError;
import com.pcitc.fms.common.CheckType;
import com.pcitc.fms.common.CheckUtil;
import com.pcitc.fms.dal.dao.AreaDictionaryDao;
import com.pcitc.fms.dal.dao.FactoryDao;
import com.pcitc.fms.dal.dao.OrgRelationDao;
import com.pcitc.fms.dal.pojo.Factory;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.exception.BusinessExceptionMessage;
import com.pcitc.fms.service.model.FactoryModelStr;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.imp.common.exception.BusiException;

import pcitc.imp.common.ettool.utils.ObjectConverter;

/**
 * The type Factory service.
 */
@Service
public class FactoryServiceImpl implements FactoryService {

	/**
	 * The constant log.
	 */
	private static Logger log = LoggerFactory.getLogger(FactoryServiceImpl.class);
	/**
	 * The Factory dao.
	 */
	@Autowired
	private FactoryDao factoryDao;

	/**
	 * The Org relation dao.
	 */
	@Autowired
	private OrgRelationDao orgRelationDao;

	/**
	 * The Check type.
	 */
	@Autowired
	private CheckType checkType;

	/**
	 * The Area dictionary dao.
	 */
	@Autowired
	private AreaDictionaryDao areaDictionaryDao;
//	@Override
//	public Pager<com.pcitc.fms.bll.entity.Factory> getFactories(FactoryModelStr modelStr, Pageable pageable)
//			throws Exception {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	
//	@Override
//	public List<com.pcitc.fms.bll.entity.Factory> getFactories(FactoryModelStr modelStr) {
//		List<com.pcitc.fms.bll.entity.Factory> factorylist_entity = new ArrayList<>();
//		try {
//			List<Factory> factorylist = factoryDao.findFactories(modelStr);
//			factorylist_entity = ObjectConverter.listConverter(factorylist,com.pcitc.fms.bll.entity.Factory.class);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			throw e;
//		}
//		return factorylist_entity;
//	}

	/**
	 * Gets factory by code.
	 *
	 * @param factoryCode the factory code
	 * @return the factory by code
	 * @throws BusinessException the business exception
	 */
	@Override
	public List<com.pcitc.fms.bll.entity.Factory> getFactoryByCode(String factoryCode) throws BusinessException {
		List<com.pcitc.fms.bll.entity.Factory> factorylist_entity = new ArrayList<>();
			CheckUtil.validateCodeException("factoryCode", factoryCode);
			List<Factory> factorylist = factoryDao.getFactoryByCode(factoryCode);
		try {
			factorylist_entity = ObjectConverter.listConverter(factorylist,com.pcitc.fms.bll.entity.Factory.class);
		} catch (Exception e) {
			log.error("fail",e);
			throw  new BusinessException("","","转换实体失败,请检查报文是否正确.");
		}
		return factorylist_entity;
	}

	/**
	 * Add factories list.
	 *
	 * @param factoryEntityList the factory entity list
	 * @return the list
	 * @throws BusinessException the business exception
	 */
	@Override
	@Transactional(rollbackFor=BusinessException.class)
	public List<com.pcitc.fms.bll.entity.Factory> addFactories(List<com.pcitc.fms.bll.entity.Factory> factoryEntityList)throws BusinessException  {
		List<com.pcitc.fms.bll.entity.Factory> factoryEntityLists = new ArrayList<>();
		try {
			List<com.pcitc.fms.dal.pojo.Factory> factoryPojoList = ObjectConverter.listConverter(factoryEntityList,Factory.class);
			List<com.pcitc.fms.dal.pojo.Factory> factoryPojos = factoryDao.save(factoryPojoList);
			factoryEntityLists = ObjectConverter.listConverter(factoryPojos, com.pcitc.fms.bll.entity.Factory.class);
		}catch(DataIntegrityViolationException e){
			log.error(e.getMessage());
			throw new BusinessException("", "", BusinessExceptionMessage.getUniqueKeyMessage(e));
		} catch (Exception e) {
			log.error(e.getMessage());
			throw  new BusinessException("","",e.getMessage());
		}
		return factoryEntityLists;
		
	}

	/**
	 * Update factory.
	 *
	 * @param factoryEntity the factory entity
	 * @throws BusinessException the business exception
	 */
	@Override
	@Transactional(rollbackFor=BusinessException.class)
	public void updateFactory(List<com.pcitc.fms.bll.entity.Factory> factoryEntity) throws BusinessException{
//		try {
		List<Factory> factoryPojoList = null;
		try {
			factoryPojoList = ObjectConverter.listConverter(factoryEntity,Factory.class);
		} catch (Exception e) {
			throw  new BusinessException("","","转换实体失败,请检查报文是否正确.");
		}
		Factory factoryPojo = factoryPojoList.get(0);
			com.pcitc.fms.dal.pojo.Factory resource = factoryDao.findByCode(factoryPojo.getCode());
			if(null == resource){
				throw new BusinessException("", "工厂"+CheckError.NO_EXIST_ENTITY_CODE+":"+factoryPojo.getCode());
			}else{
				factoryPojo.setCreator(resource.getCreator());
				factoryPojo.setCreateTime(resource.getCreateTime());
			}
			try {
				factoryDao.saveAndFlush(factoryPojo);
			} catch (Exception e) {
				log.error(e.getMessage());
				if(e.toString().contains("AK_FACTORY_CODE"))
					throw new BusinessException("", CheckError.CODE_EXIST);
			}
			/*
			factoryDao.updateFactory(factoryPojo.getFactoryId(),
					factoryPojo.getCode(),
					factoryPojo.getName(),
					factoryPojo.getShortName(),
					factoryPojo.getBusinessType(),
					factoryPojo.getEnabled(),
					factoryPojo.getEditor()
					);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//throw e;
			if(e.toString().contains("FMS.AK_FACTORY_CODE"))
				throw new BusiException("", CheckError.CODE_EXIST);
		}*/
		
	}

	/**
	 * Title: deleteByFactoryCode Description: * Description: 目前删除工厂表与映射表,如果存在区域则不删除
	 *
	 * @param factoryCode the factory code
	 * @param urlType the url type
	 * @throws BusinessException the business exception
	 * @date 2017年11月10日
	 * @author 赵振强
	 * @see com.pcitc.fms.bll.itf.FactoryService#deleteByFactoryCode(java.lang.String,
	 * java.lang.String) com.pcitc.fms.bll.itf.FactoryService#deleteByFactoryCode(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	@Transactional(rollbackFor=BusinessException.class)
	public void deleteByFactoryCode(String factoryCode,String urlType) throws BusinessException{
		com.pcitc.fms.dal.pojo.Factory factoryPojo = factoryDao.findByCode(factoryCode);
		if (factoryPojo == null) {
			throw new  BusinessException("","","当前工厂不存在");
		}
		try {
//			if(areaDictionaryDao.findByFactoryCode(factoryCode) != null) {
//				throw new BusiException("", "工厂编码:"+factoryCode+"下存在区域，不能删除此工厂！");
//			}
//			factoryDao.deleteByCode(factoryCode);
//			orgRelationDao.deleteByTargetTypeAndTargetCodeAndFactoryCode(urlType,factoryCode,factoryCode);
		} catch (DataIntegrityViolationException e) {
			throw new BusinessException("", "", BusinessExceptionMessage.getUniqueKeyMessage(e));
		} catch (Exception e) { 
			log.error(e.getMessage());
			if(e.toString().contains("_REFERENCE_FACTORY"))
				throw new BusinessException("","",CheckError.IS_FOREIGN_KEY);
		}
		
	}


	/**
	 * Gets factories.
	 *
	 * @param modelStr the model str
	 * @param pageable the pageable
	 * @return the factories
	 * @throws BusinessException the business exception
	 */
	@Override
	@Transactional(rollbackFor=BusinessException.class)
	public Pager<com.pcitc.fms.bll.entity.Factory> getFactories(FactoryModelStr modelStr, Pageable pageable) throws BusinessException {
//		List<com.pcitc.fms.dal.pojo.Factory> properties=  factoryDao.findFactories(modelStr);
		List<com.pcitc.fms.bll.entity.Factory> factoryEntity= new ArrayList<>();
		Pager<com.pcitc.fms.bll.entity.Factory> pageData = new Pager<>();
		Page<com.pcitc.fms.dal.pojo.Factory> properties = factoryDao.findFactories(modelStr, pageable);
		try {
			factoryEntity =	ObjectConverter.listConverter(properties.getContent(), com.pcitc.fms.bll.entity.Factory.class);
		} catch (Exception e) {
			throw  new BusinessException("","",e.getMessage());
		}
		pageData.setContent(factoryEntity);
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
	 * Add factories list.
	 *
	 * @param listConverter the list converter
	 * @param orgCode the org code
	 * @param typeByUrl the type by url
	 * @return the list
	 * @throws BusinessException the business exception
	 */
	@Override
	@Transactional(rollbackFor=BusinessException.class)
	public List<com.pcitc.fms.bll.entity.Factory> addFactories(List listConverter, String orgCode,String typeByUrl) throws BusinessException {
		List<com.pcitc.fms.bll.entity.Factory> factoryEntityLists = new ArrayList<>();
		List<com.pcitc.fms.bll.entity.Factory> factoryByOrgCode = getFactoryByOrgCode(orgCode,"orgUnitMeta",typeByUrl);
		if(factoryByOrgCode.size()>0){
			throw new BusinessException("", "一个组织机构下，只能存在一个工厂");
		}
		try {
			List<com.pcitc.fms.dal.pojo.Factory> factoryPojoList = ObjectConverter.listConverter(listConverter,Factory.class);
			List<com.pcitc.fms.dal.pojo.Factory> factoryPojos = factoryDao.save(factoryPojoList);
			factoryEntityLists = ObjectConverter.listConverter(factoryPojos, com.pcitc.fms.bll.entity.Factory.class);
			com.pcitc.fms.dal.pojo.OrgRelation addOrg = addOrg(factoryPojos.get(0),orgCode,typeByUrl);
			orgRelationDao.save(addOrg);
		}catch(DataIntegrityViolationException e){
			log.error(e.getMessage());
			throw new BusinessException("", "", BusinessExceptionMessage.getUniqueKeyMessage(e));
		} catch (Exception e) {
			log.error(e.getMessage());
			if(e.toString().contains("AK_FACTORY_CODE"))
				throw new BusinessException("", CheckError.CODE_EXIST);
			
		}
		return factoryEntityLists;
	}

	/**
	 * Add org com . pcitc . fms . dal . pojo . org relation.
	 *
	 * @param factory the factory
	 * @param orgCode the org code
	 * @param typeByUrl the type by url
	 * @return the com . pcitc . fms . dal . pojo . org relation
	 */
	private com.pcitc.fms.dal.pojo.OrgRelation addOrg(Factory factory, String orgCode,String typeByUrl) {
		com.pcitc.fms.dal.pojo.OrgRelation org = new com.pcitc.fms.dal.pojo.OrgRelation();
		java.util.Date date =  new java.util.Date();
		long times = date.getTime(); 
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		org.setCode(sdf.format(date));
		org.setSourceCode(orgCode);
		org.setSourceType("orgUnitMeta");
		org.setTargetCode(factory.getCode());
		org.setFactoryCode(factory.getCode());
		org.setTargetType(typeByUrl);
		org.setCreator(factory.getCreator());
		org.setCreateTime(factory.getCreateTime());
		org.setEnabled(factory.getEnabled());
		org.setDes(factory.getDes());
		return org;
	}

	/**
	 * Gets factory by org code.
	 *
	 * @param code the code
	 * @param type the type
	 * @param urlType the url type
	 * @return the factory by org code
	 * @throws BusinessException the business exception
	 */
	@Override
	public List<com.pcitc.fms.bll.entity.Factory> getFactoryByOrgCode(String code, String type,String urlType) throws BusinessException {
		List<com.pcitc.fms.bll.entity.Factory> factorylist_entity = new ArrayList<>();
			List<Factory> factorylist = factoryDao.getFactoryByOrgCode(code,type,urlType);
		try {
			factorylist_entity = ObjectConverter.listConverter(factorylist,com.pcitc.fms.bll.entity.Factory.class);
		} catch (Exception e) {
			throw  new BusinessException("","",e.getMessage());
		}
		return factorylist_entity;
	}

	
}
