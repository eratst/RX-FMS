/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: FactorySiteServiceImpl
 * Date:18-3-8 下午6:22
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.bll.itf.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.bll.entity.FactorySite;
import com.pcitc.fms.bll.itf.FactorySiteService;
import com.pcitc.fms.dal.dao.AreaDictionaryDao;
import com.pcitc.fms.dal.dao.FactorySiteDao;
import com.pcitc.fms.dal.dao.FactorySiteDaoImpl;
import com.pcitc.fms.dal.pojo.AreaDictionary;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.exception.BusinessExceptionMessage;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.imp.common.exception.BusiException;

import pcitc.imp.common.ettool.utils.ObjectConverter;

/**
 * The type Factory site service.
 *
 * @version 创建时间 ：2017年6月8日 下午3:52:01 类说明
 * @author:
 */
@Service
@Component
public class FactorySiteServiceImpl implements FactorySiteService {
//
//	/**
//	 * The constant log.
//	 */
//	private static Logger log = LoggerFactory.getLogger(FactorySiteServiceImpl.class);
//	/**
//	 * The Factory site dao.
//	 */
//	@Autowired
//	private FactorySiteDaoImpl factorySiteDaoImpl;
//	/**
//	 * The Factory site dao.
//	 */
//	@Autowired
//	private FactorySiteDao factorySiteDao;
//	/**
//	 * The Area dictionary dao.
//	 */
//	@Autowired
//	private AreaDictionaryDao areaDictionaryDao;
//
//	/**
//	 * Gets by code.
//	 *
//	 * @param factoryId the factory id
//	 * @param code the code
//	 * @return the by code
//	 * @throws BusinessException the business exception
//	 */
//	@Override
//	public com.pcitc.fms.bll.entity.FactorySite getByCode(Integer factoryId,String code) throws BusinessException {
//		com.pcitc.fms.dal.pojo.FactorySiteArea factorySiteAreaPojo = factorySiteDao.getfactorySiteBycode(factoryId,code);
//		FactorySite factorySiteEntity = null;
//		try {
//			factorySiteEntity = ObjectConverter.entityConverter(factorySiteAreaPojo, FactorySite.class);
//		} catch (Exception e) {
//			log.error("fail",e);
//			throw  new BusinessException("","",e.getMessage());
//		}
//
//		return factorySiteEntity;
//	}
//
//
//	/**
//	 * Gets by code.
//	 *
//	 * @param code the code
//	 * @return the by code
//	 * @throws BusinessException the business exception
//	 */
//	@Override
//	public com.pcitc.fms.bll.entity.FactorySite getByCode(String code) throws BusinessException {
//		com.pcitc.fms.dal.pojo.FactorySiteArea factorySiteAreaPojo = factorySiteDao.getfactorySiteBycode(code);
//		FactorySite factorySiteEntity = null;
//		try {
//			factorySiteEntity = ObjectConverter.entityConverter(factorySiteAreaPojo, FactorySite.class);
//		} catch (Exception e) {
//			log.error("fail",e);
//			throw  new BusinessException("","",e.getMessage());
//		}
//
//		return factorySiteEntity;
//	}
//
//	/**
//	 * Gets page factory sites.
//	 *
//	 * @param factorySiteModel the factory site model
//	 * @param pageable the pageable
//	 * @return the page factory sites
//	 * @throws BusinessException the business exception
//	 */
//	@SuppressWarnings("unchecked")
//	@Override
//	public Pager<FactorySite> getPageFactorySites(com.pcitc.fms.service.model.FactorySite factorySiteModel,
//			Pageable pageable) throws BusinessException {
//		Page<com.pcitc.fms.dal.pojo.FactorySiteArea> properties  = factorySiteDaoImpl.findPageFactorySites(factorySiteModel ,pageable);
//		List<FactorySite> factorySiteEntityList = null;
//		try {
//			factorySiteEntityList = ObjectConverter.listConverter(properties.getContent(),FactorySite.class);
//		} catch (Exception e) {
//			log.error("fail",e);
//			throw  new BusinessException("","",e.getMessage());
//		}
//		Pager<com.pcitc.fms.bll.entity.FactorySite> pageData = new Pager<>();
//		pageData.setContent(factorySiteEntityList);
//		pageData.setFirst(properties.isFirst());
//		pageData.setLast(properties.isLast());
//		pageData.setNumber(properties.getNumber());
//		pageData.setNumberOfElements(properties.getNumberOfElements());
//		pageData.setSize(properties.getSize());
//		pageData.setSort(properties.getSort());
//		pageData.setTotalElements(properties.getTotalElements());
//		pageData.setTotalPages(properties.getTotalPages());
//	    return pageData;
//	}
//
//
//	/**
//	 * Add factory sites list.
//	 *
//	 * @param factorySiteModelList the factory site model list
//	 * @return the list
//	 * @throws BusinessException the business exception
//	 */
//	@SuppressWarnings("unchecked")
//	@Override
//	@Transactional(rollbackFor=BusinessException.class)
//	public List<com.pcitc.fms.bll.entity.FactorySite> addFactorySites(List<com.pcitc.fms.service.model.FactorySite> factorySiteModelList) throws BusinessException {
//		List<com.pcitc.fms.dal.pojo.FactorySite> factorySitePojoList = new ArrayList<>();
//	    List<com.pcitc.fms.bll.entity.FactorySite> factorySiteEntityList =  new ArrayList<>(); 
//	    List<com.pcitc.fms.dal.pojo.AreaDictionary> areaDictionaryPojoList = new ArrayList<>();
//	    try {
//			// 将viewmodel转换成entity  
//	    	factorySiteEntityList = ObjectConverter.listConverter(factorySiteModelList,com.pcitc.fms.bll.entity.FactorySite.class);
//	//		//获取id的序列
//	//		List<BigDecimal> primaryIds = dbPrimaryIdDao.getAreaDateSeqId(administrationEntityList.size());
//			//装配区域总表
//			List<com.pcitc.fms.bll.entity.AreaDictionary> areaDictionaryEntityList = newAreaDicList(factorySiteEntityList);
//			//将总表转换为pojo并持久化
//			areaDictionaryPojoList = ObjectConverter.listConverter(areaDictionaryEntityList,com.pcitc.fms.dal.pojo.AreaDictionary.class);
//			areaDictionaryPojoList = areaDictionaryDao.save(areaDictionaryPojoList);
//			//新增后的总表id也是新增此工厂的id，将areaDictionaryId放入factoryId,并持久化
//			for(AreaDictionary pojo : areaDictionaryPojoList) {
////				pojo.setFactoryId(pojo.getAreaDictionaryId());
//			}
//			areaDictionaryPojoList = areaDictionaryDao.save(areaDictionaryPojoList);
//			//转换分表pojo，将总表id装配到分表id上
//			factorySitePojoList = ObjectConverter.listConverter(factorySiteEntityList,com.pcitc.fms.dal.pojo.FactorySite.class);
//			for(int i = 0; i < areaDictionaryPojoList.size(); i++) {
//				if(null!= areaDictionaryPojoList.get(i) && areaDictionaryPojoList.get(i).getAreaCode().equals(factorySitePojoList.get(i).getFactorySiteCode())) {
////					factorySitePojoList.get(i).setFactorySiteId(areaDictionaryPojoList.get(i).getAreaDictionaryId());
//				}
//			}
//			
//			factorySitePojoList = factorySiteDao.save(factorySitePojoList);
//			
//		} catch (DataIntegrityViolationException e) {//这里要考虑name shortNam code重复报错
//			log.error(e.getMessage());
//			throw new BusinessException("", "", BusinessExceptionMessage.getUniqueKeyMessage(e));
//		} catch (Exception e) {
//			log.error(e.getMessage());
//			throw new BusinessException("", e.getMessage());
//			
//		}
//		try {
//			factorySiteEntityList = ObjectConverter.listConverter(factorySitePojoList, FactorySite.class);
//		} catch (Exception e) {
//			log.error("fail",e);
//			throw  new BusinessException("","",e.getMessage());
//		}
//		//将总表字段装入分表实体
//		mergerEntity(areaDictionaryPojoList,factorySiteEntityList);
//		return factorySiteEntityList;
//	}
//
//
//	/**
//	 * Update factory site.
//	 *
//	 * @param factorySiteModelList the factory site model list
//	 * @throws BusinessException the business exception
//	 */
////hanxiao
//	@SuppressWarnings("unchecked")
//	@Override
//	@Transactional(rollbackFor=BusinessException.class)
//	public void updateFactorySite(List<com.pcitc.fms.service.model.FactorySite> factorySiteModelList) throws BusinessException {
//		
//		// 将viewmodel转换成entity 	
//		List<FactorySite> factorySiteEntityList = null;
//		try {
//			factorySiteEntityList = ObjectConverter.listConverter(factorySiteModelList, FactorySite.class);
//		} catch (Exception e) {
//			log.error("fail",e);
//			throw  new BusinessException("","",e.getMessage());
//		}
//		try {
//			//校验传入的实体信息，是否是一个，是否存在
//			checkForEntity(factorySiteEntityList);
//			//从数据库中查到code对应的总表信息
//			com.pcitc.fms.dal.pojo.AreaDictionary areaDictionaryPojo = areaDictionaryDao.getAreaDictionaryByAreaCode(factorySiteEntityList.get(0).getFactorySiteCode());
//			//装配区域总表并更新
//			newAreaDicList(areaDictionaryPojo,factorySiteEntityList);
//			areaDictionaryDao.saveAndFlush(areaDictionaryPojo);
//			
//			//从数据库中查到code对应的分表信息
//			com.pcitc.fms.dal.pojo.FactorySite factorySitePojo = factorySiteDao.findByCode(factorySiteEntityList.get(0).getFactorySiteCode()); 
//			//装配区域分别表并更新
//			newSubArea(factorySitePojo,factorySiteEntityList);
//			factorySiteDao.saveAndFlush(factorySitePojo);
//		} catch (DataIntegrityViolationException e) {
//			throw new BusinessException("", "", BusinessExceptionMessage.getUniqueKeyMessage(e));
//		} catch (Exception e) {
//				throw new BusinessException("", e.toString());
//		}
//			
//		
//	}
//
//
//	/**
//	 * Delete factory site by code.
//	 *
//	 * @param code the code
//	 * @throws BusinessException the business exception
//	 */
//	@Override
//	@Modifying
////	@Transactional(rollbackFor=BusinessException.class)
//	public void deleteFactorySiteByCode(String code) throws BusinessException {
//		try {
//			
//			//删除区域分表数据
//			factorySiteDao.deleteByFactorySiteCode(code);
//			//删除区域主表数据
//			areaDictionaryDao.deleteByAreaCode(code);
//		}catch(Exception e) {
//			//要考虑万一区域下面右节点的报错！
//			log.error("fail",e);
//			throw  new BusinessException("","",e.getMessage());
//		}
//	}
//
//	/**
//	 * 通过分表来装配区域总表
//	 *
//	 * @param entityList 出入的实体list
//	 * @return list list
//	 */
//	private List<com.pcitc.fms.bll.entity.AreaDictionary> newAreaDicList(
//		List<com.pcitc.fms.bll.entity.FactorySite> entityList) {
//		List<com.pcitc.fms.bll.entity.AreaDictionary> areaDicList = new ArrayList<>();
//			for(int i = 0; i < entityList.size(); i++) {
//				com.pcitc.fms.bll.entity.FactorySite entity = entityList.get(i);
//				com.pcitc.fms.bll.entity.AreaDictionary areaDic = new com.pcitc.fms.bll.entity.AreaDictionary();
//				areaDic.setAreaDictionaryId(entity.getFactorySiteId());
//				areaDic.setAreaTypeId(entity.getAreaTypeId());
//				areaDic.setAreaCode(entity.getFactorySiteCode());
//				areaDic.setFactoryId(entity.getFactoryId());
//				areaDic.setDes(entity.getDes());
//				areaDic.setName(entity.getName());
//				areaDic.setShortName(entity.getShortName());
//				areaDic.setEnabled(entity.getEnabled());
//				areaDic.setSortNum(entity.getSortNum());
//				areaDic.setVersion(entity.getVersion());
//				areaDic.setCodeList(entity.getCodeList());
//				areaDic.setIdList(entity.getIdList());
//				areaDicList.add(areaDic);
//				
//			}
//			return areaDicList;
//		}
//
//	/**
//	 * 更新中的装配区域总表，
//	 *
//	 * @param pojo the pojo
//	 * @param factorySiteEntityList the factory site entity list
//	 */
//	private void newAreaDicList(com.pcitc.fms.dal.pojo.AreaDictionary pojo,
//			List<com.pcitc.fms.bll.entity.FactorySite> factorySiteEntityList) {
//		com.pcitc.fms.bll.entity.FactorySite factorySite = factorySiteEntityList.get(0);
//		pojo.setName(factorySite.getName());
//		pojo.setShortName(factorySite.getShortName());
//		pojo.setEnabled(factorySite.getEnabled());
//		pojo.setDes(factorySite.getDes());
//	}
//
//	/**
//	 * 把总表信息装入分表中
//	 *
//	 * @param areaDictionaryPojoList 总表信息
//	 * @param factorySiteEntityList the factory site entity list
//	 */
//	private void mergerEntity(List<AreaDictionary> areaDictionaryPojoList,
//			List<com.pcitc.fms.bll.entity.FactorySite> factorySiteEntityList) {
//		for(int i = 0; i<factorySiteEntityList.size(); i++) {
//			com.pcitc.fms.bll.entity.FactorySite entity = factorySiteEntityList.get(i);
//			com.pcitc.fms.dal.pojo.AreaDictionary pojo = areaDictionaryPojoList.get(i);
//			if(entity.getFactorySiteCode().equals(pojo.getAreaCode())) {
//				entity.setName(pojo.getName());
//				entity.setShortName(pojo.getShortName());
////				entity.setAreaTypeId(pojo.getAreaTypeId());
//				entity.setEnabled(pojo.getEnabled());
////				entity.setFactoryId(pojo.getFactoryId());
//			}
//		}
//		
//	}
//
//	/**
//	 * 通过查询到的分表来装配新的分表
//	 *
//	 * @param pojo the pojo
//	 * @param factorySiteEntityList the factory site entity list
//	 */
//	private void newSubArea(com.pcitc.fms.dal.pojo.FactorySite pojo,
//			List<com.pcitc.fms.bll.entity.FactorySite> factorySiteEntityList) {
//		// 找到要改的字段,共有字段和特有字段
//		com.pcitc.fms.bll.entity.FactorySite entity = factorySiteEntityList.get(0);
//		pojo.setEditor(entity.getEditor());
//		pojo.setEditorId(entity.getEditorId());
//		pojo.setMaintainTime(entity.getMaintainTime());
//	}
//
//
//	/**
//	 * 查询传入实体是否唯一，是够存在
//	 *
//	 * @param factorySiteEntityList the factory site entity list
//	 * @throws BusinessException the business exception
//	 */
//	private void checkForEntity(List<com.pcitc.fms.bll.entity.FactorySite> factorySiteEntityList) throws BusinessException {
//		//校验是否唯一个实体
//		if(factorySiteEntityList.size() != 1) {
//			throw new BusinessException("", "", "更新操作只能传入一个实体！");
//		}
//		//校验是否存在等于code的entity实体
//		String code = factorySiteEntityList.get(0).getFactorySiteCode();
//		com.pcitc.fms.dal.pojo.FactorySite factorySitePojo = factorySiteDao.findByCode(code);
//		if(factorySitePojo == null) {
//			throw new BusinessException("", "", "不存在code："+code+"的信息！");
//		}
//	}
//	
//
//
}

