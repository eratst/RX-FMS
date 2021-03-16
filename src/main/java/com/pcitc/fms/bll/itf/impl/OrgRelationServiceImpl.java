/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: OrgRelationServiceImpl
 * Date:18-3-8 下午6:23
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.bll.itf.impl;

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
import com.pcitc.fms.bll.itf.OrgRelationService;
import com.pcitc.fms.common.CheckError;
import com.pcitc.fms.common.CheckType;
import com.pcitc.fms.common.CheckUtil;
import com.pcitc.fms.common.dispatcher.SysGlobal;
import com.pcitc.fms.dal.dao.OrgRelationDao;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.exception.BusinessExceptionMessage;
import com.pcitc.fms.service.model.Pager;

import pcitc.imp.common.ettool.utils.ObjectConverter;

/**
 * Title: OrgRelationServiceImpl Description: TODO task mark zhenqiang.zhao
 *
 * @author zhenqiang.zhao
 * @version 1.0
 * @date 2017年7月21日
 */
@Service
public class OrgRelationServiceImpl implements OrgRelationService {

	/**
	 * The constant log.
	 */
	private static Logger log = LoggerFactory.getLogger(OrgRelationServiceImpl.class);
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
	 * Gets org relations.
	 *
	 * @param orgRelationModel the org relation model
	 * @param pageable the pageable
	 * @return the org relations
	 * @throws BusinessException the business exception
	 */
	@Override
	@Transactional(rollbackFor=BusinessException.class)
	public Pager<OrgRelation> getOrgRelations(
			com.pcitc.fms.service.model.OrgRelation orgRelationModel,Pageable pageable) throws BusinessException {
		Page<com.pcitc.fms.dal.pojo.OrgRelation>  properties = orgRelationDao.getOrgRelations(orgRelationModel,pageable);
		List<OrgRelation> propertiesEntity= null;
		try {
			propertiesEntity = ObjectConverter.listConverter(properties.getContent(), OrgRelation.class);
		} catch (Exception e) {
			log.error("fail",e);
			throw  new BusinessException("","",e.getMessage());
		}
		Pager<OrgRelation> pageData = new Pager<>();
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
	 * Gets org relation by code.
	 *
	 * @param code the code
	 * @return the org relation by code
	 * @throws BusinessException the business exception
	 */
	@Override
	@Transactional(rollbackFor=BusinessException.class)
	public OrgRelation getOrgRelationByCode(String code) throws BusinessException {
		com.pcitc.fms.dal.pojo.OrgRelation orgRelationPojo = orgRelationDao.findByCode(code);
		try {
			return orgRelationPojo==null?null:ObjectConverter.entityConverter(orgRelationPojo, OrgRelation.class);
		} catch (Exception e) {
			log.error("fail",e);
			throw  new BusinessException("","",e.getMessage());
		}
	}

	/**
	 * Add org relations list.
	 *
	 * @param orgRelationList the org relation list
	 * @return the list
	 * @throws BusinessException the business exception
	 */
	@Override
	@Transactional(rollbackFor=BusinessException.class)
	public List<OrgRelation> addOrgRelations(List<OrgRelation> orgRelationList) throws BusinessException {
		List<com.pcitc.fms.dal.pojo.OrgRelation> orgRelPojoList = new ArrayList<com.pcitc.fms.dal.pojo.OrgRelation>();
		List<String> errMessage = new ArrayList<String>();
		for (OrgRelation orgRelation : orgRelationList) {
//			checkType.check(orgRelation.getOrgRelationType(), "orgUnitTypes");//类型校验
			checkType.checkOrgRelationTypeAndCode(orgRelation.getSourceType(),orgRelation.getSourceCode(),"sourceType","sourceType");//类型与Code校验
			checkType.checkOrgRelationTypeAndCode(orgRelation.getTargetType(),orgRelation.getTargetCode(),"targetType","targetType");
			BusinessException exception = CheckUtil.getUniqueOrgRelationException(getUniqueOrgRelation(orgRelation));//唯一性校验
			if (exception != null) {
				throw new BusinessException("", "", exception.getMessage());
			}
		}
		try {
			orgRelPojoList = orgRelationDao.save(ObjectConverter.listConverter(orgRelationList, com.pcitc.fms.dal.pojo.OrgRelation.class));
			orgRelationDao.flush();
		} catch (DataIntegrityViolationException e) {
			throw new BusinessException("", "", BusinessExceptionMessage.getPrimarykeyUnique(e));
		} catch (Exception e) {
			log.error("fail",e);
			throw  new BusinessException("","",e.getMessage());
		}
		try {
			return ObjectConverter.listConverter(orgRelPojoList, OrgRelation.class);
		} catch (Exception e) {
			log.error("fail",e);
			throw  new BusinessException("","",e.getMessage());
		}
	}

	/**
	 * Gets unique org relation.
	 *
	 * @param orgRelation the org relation
	 * @return unique org relation
	 * @throws BusinessException the business exception
	 * @Title: getOrgRelations
	 * @Description: TODO task mark zhenqiang.zhao
	 * @date 2017年7月25日
	 * @return: List<com.pcitc.fms.dal.pojo.OrgRelation>
	 * @author zhenqiang.zhao
	 */
	@Transactional(rollbackFor=BusinessException.class)
	public OrgRelation getUniqueOrgRelation(OrgRelation orgRelation) throws BusinessException {
		com.pcitc.fms.dal.pojo.OrgRelation  orgRelationPojo = null;
		try {
			orgRelationPojo = orgRelationDao.getUniqueOrgRelations(
					ObjectConverter.entityConverter(orgRelation, com.pcitc.fms.service.model.OrgRelation.class));
		} catch (Exception e) {
			log.error("fail",e);
			throw  new BusinessException("","",e.getMessage());
		}
		try {
			return  (null != orgRelationPojo)?ObjectConverter.entityConverter(orgRelationPojo, OrgRelation.class):null;
		} catch (Exception e) {
			log.error("fail",e);
			throw  new BusinessException("","",e.getMessage());
		}
	}

	/**
	 * Update org relation.
	 *
	 * @param orgRelation the org relation
	 * @throws BusinessException the business exception
	 */
	@Override
	@Transactional(rollbackFor=BusinessException.class)
	public void updateOrgRelation(
			OrgRelation orgRelation) throws BusinessException {
		com.pcitc.fms.dal.pojo.OrgRelation  orgRelationPojo = null;
		try {
			orgRelationPojo = ObjectConverter.entityConverter(orgRelation, com.pcitc.fms.dal.pojo.OrgRelation.class);
		} catch (Exception e) {
			log.error("fail",e);
			throw  new BusinessException("","",e.getMessage());
		}
		com.pcitc.fms.dal.pojo.OrgRelation oldOrgRelationPojo = orgRelationDao.findByCode(orgRelationPojo.getCode());
		if (null == oldOrgRelationPojo) {
			throw new  BusinessException("", "", "orgRelationCode:"+orgRelationPojo.getCode()+":"+CheckError.IS_NOT_EXIST);
		}
			orgRelationPojo.setCreator(oldOrgRelationPojo.getCreator());
			orgRelationPojo.setCreateTime(oldOrgRelationPojo.getCreateTime());
			orgRelationPojo.setOrgRelationId(oldOrgRelationPojo.getOrgRelationId());
		checkType.check(orgRelation.getTargetType(), "entityTypes");
//		String entityName = "";
		String entityName = SysGlobal.getDispatcherParam("areas"+"."+orgRelation.getTargetType());
		/*if (null != orgRelation.getTargetType() && orgRelation.getTargetType().endsWith("ies")) {
			entityName = orgRelation.getTargetType().replace("ies", "ys");
		}
		if (null != orgRelation.getTargetType() && orgRelation.getTargetType().endsWith("s")) {
			entityName =orgRelation.getTargetType().substring(0, orgRelation.getTargetType().lastIndexOf("s"));
		}*/
		checkType.checkEntityCode(entityName,orgRelation.getTargetCode());
		orgRelationDao.save(orgRelationPojo);

	}

	/**
	 * Delete org relation by codes.
	 *
	 * @param codeList the code list
	 */
	@Override
	@Transactional(rollbackFor=BusinessException.class)
	public void deleteOrgRelationByCodes(List<String> codeList) {
		orgRelationDao.deleteByCodeIn(codeList);

	}

	/**
	 * Delete org relation by code.
	 *
	 * @param code the code
	 * @throws BusinessException the business exception
	 */
	@Override
	@Transactional(rollbackFor=BusinessException.class)
	public void deleteOrgRelationByCode(String code) throws BusinessException {
		com.pcitc.fms.dal.pojo.OrgRelation orgRelationPojo = orgRelationDao.findByCode(code);
		if (orgRelationPojo == null) {
			throw new  BusinessException("","",BusinessExceptionMessage.getDataNotExist("ID",code));
		}
		orgRelationDao.deleteByCode(code);

	}

	/**
	 * Title: getOrgRelationById Description: TODO task mark zhenqiang.zhao
	 *
	 * @param factoryCode the factory code
	 * @param orgRelationCode the org relation code
	 * @return org relation by code
	 * @throws BusinessException the business exception
	 * @date 2017年7月23日
	 * @author zhenqiang.zhao  java.lang.Integer) com.pcitc.fms.bll.itf.OrgRelationService#getOrgRelationById(java.lang.Integer,
	 * java.lang.Integer)
	 */
	@Override
	@Transactional(rollbackFor=BusinessException.class)
	public OrgRelation getOrgRelationByCode(String factoryCode, String orgRelationCode) throws BusinessException {
		com.pcitc.fms.dal.pojo.OrgRelation orgRelationPojo = orgRelationDao.findByFactoryCodeAndCode(factoryCode,orgRelationCode);
		try {
			return orgRelationPojo==null?null:ObjectConverter.entityConverter(orgRelationPojo, OrgRelation.class);
		} catch (Exception e) {
			log.error("fail",e);
			throw  new BusinessException("","",e.getMessage());
		}
	}

	/**
	 * Title: deleteOrgRelationById Description: TODO task mark zhenqiang.zhao
	 *
	 * @param factoryCode the factory code
	 * @param orgRelationCode the org relation code
	 * @throws BusinessException the business exception
	 * @date 2017年7月23日
	 * @author zhenqiang.zhao  java.lang.Integer) com.pcitc.fms.bll.itf.OrgRelationService#deleteOrgRelationById(java.lang.Integer,
	 * java.lang.Integer)
	 */
	@Override
	@Transactional(rollbackFor=BusinessException.class)
	public void deleteOrgRelationByCode(String factoryCode, String orgRelationCode) throws BusinessException {
		com.pcitc.fms.dal.pojo.OrgRelation orgRelationPojo = orgRelationDao.findByFactoryCodeAndCode(factoryCode,orgRelationCode);
		if (orgRelationPojo == null) {
			throw new  BusinessException("","",BusinessExceptionMessage.getDataNotExist("ID",orgRelationCode));
		}
		orgRelationDao.deleteOrgRelationByFactoryCodeAndCode(factoryCode,orgRelationCode);
	}

	/**
	 * Title: deleteOrgRelationByIds Description: TODO task mark zhenqiang.zhao
	 *
	 * @param factoryCode the factory code
	 * @param codeList the code list
	 * @date 2017年7月23日
	 * @author zhenqiang.zhao  java.util.List) com.pcitc.fms.bll.itf.OrgRelationService#deleteOrgRelationByIds(java.lang.Integer,
	 * java.util.List)
	 */
	@Override
	@Transactional(rollbackFor=BusinessException.class)
	public void deleteOrgRelationByCodes(String factoryCode, List<String> codeList) {
		orgRelationDao.deleteOrgRelationByFactoryCodeAndCodeIn(factoryCode,codeList);
	}

}
