/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: AreaDictionaryServiceImpl
 * Date:18-3-8 下午6:19
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.bll.itf.impl;

import com.pcitc.fms.bll.entity.Area;
import com.pcitc.fms.bll.entity.AreaDictionary;
import com.pcitc.fms.bll.entity.TPmBizOrgDTL;
import com.pcitc.fms.bll.itf.AreaDictionaryService;
import com.pcitc.fms.common.CheckError;
import com.pcitc.fms.common.HostService;
import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.common.dispatcher.SysGlobal;
import com.pcitc.fms.dal.dao.AreaDictionaryDao;
import com.pcitc.fms.dal.dao.FactoryDao;
import com.pcitc.fms.dal.dao.OrgRelationDao;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.service.model.OrgUnitModel;
import com.pcitc.fms.service.model.Pager;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pcitc.imp.common.ettool.utils.ObjectConverter;
import pcitc.imp.common.ettool.utils.RestfulTool;

/**
 * The type Area dictionary service.
 *
 * @version 创建时间 ：2017年9月22日   类说明
 * @author: 韩啸
 */
@Service
@Component
public class AreaDictionaryServiceImpl implements AreaDictionaryService {

	/**
	 * The constant log.
	 */
	private static Logger log = LoggerFactory.getLogger(AreaDictionaryServiceImpl.class);
	/**
	 * The Area dictionary dao.
	 */
	@Autowired
	private AreaDictionaryDao areaDictionaryDao;
	/**
	 * The Org relation dao.
	 */
	@Autowired
	private  OrgRelationDao orgRelationDao;
	/**
	 * The Factory dao.
	 */
	@Autowired
	private FactoryDao factoryDao;

	/**
	 * Update area dictionary table.
	 *
	 * @param areaDictionaryTableEntityList the area dictionary table entity list
	 * @throws BusinessException the business exception
	 */
	@Override
	public void updateAreaDictionaryTable(List<AreaDictionary> areaDictionaryTableEntityList) throws BusinessException {
		try{
			List<com.pcitc.fms.dal.pojo.AreaDictionary> areaDictionaryTablePojoList = ObjectConverter.listConverter(areaDictionaryTableEntityList, com.pcitc.fms.dal.pojo.AreaDictionary.class);
//			for(com.pcitc.fms.dal.pojo.AreaDictionary pojo : areaDictionaryTablePojoList){
//			    
//				areaDictionaryDao.updateAreaDictionary(
//						pojo.getName(),
//						pojo.getCode(),
//						pojo.getFactoryCode(),
//						pojo.getEditor(),
//						pojo.getEnabled(),
//						pojo.getType(),
//						pojo.getDes()
//						);
//			}
		}catch(DataIntegrityViolationException e){
		    throw new BusinessException(CheckError.AREADICTIONARYTABLE_CHECK, "","区域字典编码和区域字典表名称和数据库中数据重复！");
		} catch (Exception e) {
			log.error("fail",e);
			throw  new BusinessException("","","转换实体失败,请检查报文是否正确.");
		}
	}

	/**
	 * Gets area dictionary table by code.
	 *
	 * @param factoryCode the factory code
	 * @param areaDictionaryTableCode the area dictionary table code
	 * @return the area dictionary table by code
	 * @throws BusinessException the business exception
	 */
	@Override
	public List<AreaDictionary> getAreaDictionaryTableByCode(String factoryCode, String areaDictionaryTableCode) throws BusinessException {
//		List<com.pcitc.fms.dal.pojo.AreaDictionary> areaDictionaryTablePojoList =  areaDictionaryDao.getAreaDictionaryByFactoryCodeAndCode(factoryCode,areaDictionaryTableCode);
//		List<com.pcitc.fms.bll.entity.AreaDictionary> entityList = ObjectConverter.listConverter(areaDictionaryTablePojoList, com.pcitc.fms.bll.entity.AreaDictionary.class);
//		return entityList;
		return null;
	}

	/**
	 * Delete area dictionary table by factory and code.
	 *
	 * @param factoryCode the factory code
	 * @param areaDictionaryTableCode the area dictionary table code
	 */
	@Override
	public void deleteAreaDictionaryTableByFactoryAndCode(String factoryCode, String areaDictionaryTableCode) {
//		areaDictionaryDao.deleteByCode(areaDictionaryTableCode);	
	}

	/**
	 * Add area dictionary table list.
	 *
	 * @param areaDictionaryTableEntityList the area dictionary table entity list
	 * @return the list
	 * @throws BusinessException the business exception
	 */
	@Override
	@Transactional(rollbackFor=BusinessException.class)
	public List<AreaDictionary> addAreaDictionaryTable(List<AreaDictionary> areaDictionaryTableEntityList)
			throws BusinessException {
		List<com.pcitc.fms.dal.pojo.AreaDictionary> pojos = new ArrayList<>();
				 
		try{
			List<com.pcitc.fms.dal.pojo.AreaDictionary> pojo = ObjectConverter.listConverter(areaDictionaryTableEntityList, com.pcitc.fms.dal.pojo.AreaDictionary.class);
			pojos = areaDictionaryDao.save(pojo);
		}catch(DataIntegrityViolationException e){
            throw new BusinessException(CheckError.AREADICTIONARYTABLE_CHECK, "","区域字典编码和区域字典表名称和数据库中数据重复！");
        }
		catch(Exception e){
			throw  new BusinessException("","",e.getMessage());
		}
		try {
			return ObjectConverter.listConverter(pojos, AreaDictionary.class);
		} catch (Exception e) {
			log.error("fail",e);
			throw  new BusinessException("","",e.getMessage());
		}
	}

	/**
	 * Gets area dictionary tables by model.
	 *
	 * @param sourceId the source id
	 * @param leavesId the leaves id
	 * @param sourceType the source type
	 * @param orgCode the org code
	 * @param orgUnitCode the org unit code
	 * @param pageable the pageable
	 * @param types the types
	 * @param areaIdList the area id list
	 * @return the area dictionary tables by model
	 * @throws BusinessException the business exception
	 */
	@Override
	public Pager<AreaDictionary> getAreaDictionaryTablesByModel(Integer sourceId,Integer leavesId, String sourceType, String orgCode,String orgUnitCode, Pageable pageable, List<String> types,List<Integer> areaIdList) throws BusinessException {
		List<AreaDictionary> tankAreaList = new ArrayList<>();
		MyPageImpl properties =null;
		Pager<com.pcitc.fms.bll.entity.AreaDictionary>  pageData = new Pager<>();
		try {
		List<com.pcitc.fms.dal.pojo.AreaDictionary> tankArealist = new ArrayList<>();
		com.pcitc.fms.dal.pojo.OrgRelation factory = orgRelationDao.findAllBySourceIdAndTargetType(orgCode ,"orgUnitMeta",types);
		String factiryCode = factory == null ? null : factory.getTargetCode();
		List<String> idList = new ArrayList<>();
//		if(null != leavesId){
//			idList.add(leavesId);
//		}
		//--------------调用AAA接口获取机构所有叶子节点start-----------------
		if(null != orgCode && null != orgUnitCode){
			String orgUnitMetaStr = HostService.getOrgUnitMeta(orgCode, orgUnitCode);
			List<OrgUnitModel> orgUnitMetaist = null;
				orgUnitMetaist = RestfulTool.toResourceRepList(orgUnitMetaStr, OrgUnitModel.class);
				
			for (OrgUnitModel orgUnitMeta : orgUnitMetaist) {
				idList.add(orgUnitMeta.getOrgUnitCode());
			}
		}
		
		
		List<String> orgtypes = new ArrayList<>();
		for (String type : types) {
			orgtypes.add(SysGlobal.getDispatcherParam("orgRelationsTarTypeName"+"."+type));
		}
			tankAreaList = ObjectConverter.listConverter(properties.getContent(),AreaDictionary.class);
		} catch (Exception e) {
			if (e instanceof UndeclaredThrowableException) {
				throw  new BusinessException("","",e.getCause().getMessage());
			}
//			log.error("fail",e);
			throw  new BusinessException("","",e.getMessage());
		}
		pageData.setContent(tankAreaList);
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
	 * Gets area dictionary table by code.
	 *
	 * @param areaDictionaryTableCode the area dictionary table code
	 * @return the area dictionary table by code
	 * @throws BusinessException the business exception
	 */
	@Override
	public List<AreaDictionary> getAreaDictionaryTableByCode(String areaDictionaryTableCode) throws BusinessException {
//		List<com.pcitc.fms.dal.pojo.AreaDictionary> areaDictionaryTablePojoList =  areaDictionaryDao.getAreaDictionaryByCode(areaDictionaryTableCode);
//		List<com.pcitc.fms.bll.entity.AreaDictionary> entityList = ObjectConverter.listConverter(areaDictionaryTablePojoList, com.pcitc.fms.bll.entity.AreaDictionary.class);
//		return entityList;
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Pager<Area> getAreas(com.pcitc.fms.service.model.Area area, Pageable pageable) throws Exception {
		MyPageImpl properties = null;
		Pager<Area>  pageData = new Pager<>();
	     List<Area> EntityList = new ArrayList<>();
	     try {
	    	 properties  = areaDictionaryDao.getAreas(area, pageable);
	       EntityList = ObjectConverter.listConverter(properties.getContent(),Area.class);
	     } catch (Exception e) {
	    	 if (e instanceof UndeclaredThrowableException) {
	    		 throw  new BusinessException("","",e.getCause().getMessage());
	    	 }
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
	       pageData.setTotalElements(properties.getCount());
	       pageData.setTotalPages(properties.getTotalPages());
	   return pageData;
	}		
}
