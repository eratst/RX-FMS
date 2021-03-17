/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: PlantServiceImpl
 * Date:18-3-8 下午6:23
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.bll.itf.impl;

import java.lang.reflect.UndeclaredThrowableException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import com.pcitc.fms.bll.entity.Plant;
import com.pcitc.fms.bll.itf.PlantService;
import com.pcitc.fms.common.CheckError;
import com.pcitc.fms.common.HostService;
import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.common.Test;
import com.pcitc.fms.dal.dao.AreaDictionaryDao;
import com.pcitc.fms.dal.dao.DbPrimaryIdDao;
import com.pcitc.fms.dal.dao.EquipmentDao;
import com.pcitc.fms.dal.dao.FactoryDao;
import com.pcitc.fms.dal.dao.InclusionDao;
import com.pcitc.fms.dal.dao.NodeDictionaryDao;
import com.pcitc.fms.dal.dao.OrgRelationDao;
import com.pcitc.fms.dal.dao.OutletDao;
import com.pcitc.fms.dal.dao.PlantDao;
import com.pcitc.fms.dal.dao.PlantDaoImpl;
import com.pcitc.fms.dal.dao.PlateDao;
import com.pcitc.fms.dal.dao.SideLineDao;
import com.pcitc.fms.dal.dao.SiloDao;
import com.pcitc.fms.dal.dao.TeeDao;
import com.pcitc.fms.dal.dao.ValveDao;
import com.pcitc.fms.dal.pojo.AreaDictionary;
import com.pcitc.fms.dal.pojo.Factory;
import com.pcitc.fms.dal.pojo.Inclusion;
import com.pcitc.fms.dal.pojo.NodeDictionary;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.exception.BusinessExceptionMessage;
import com.pcitc.fms.service.model.OrgUnitModel;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.imp.common.exception.BusiException;

import pcitc.imp.common.ettool.utils.ObjectConverter;
import pcitc.imp.common.ettool.utils.RestfulTool;

/**
 * The type Plant service.
 *
 * @version 创建时间 ：2017年6月8日 下午3:52:01  类说明
 * @author:
 */
@Service
@Component
public class PlantServiceImpl implements PlantService {

	/**
	 * The Plant dao.
	 */
	@Autowired
	private PlantDao plantDao;

	/**
	 * Gets page plants.
	 *
	 * @param findPlantModel the find plant model
	 * @param pageable the pageable
	 * @return the page plants
	 * @throws BusinessException the business exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Pager<Plant> getPagePlants(com.pcitc.fms.service.model.Plant findPlantModel, Pageable pageable)  throws BusinessException{
		Test properties = null;
		List<Plant> administrationEntityList = null;
		
		try {
			properties = plantDao.findPagePlants(findPlantModel ,pageable);
			administrationEntityList = ObjectConverter.listConverter(properties.getContent(),Plant.class);
		} catch (Exception e) {
			if (e instanceof UndeclaredThrowableException) {
				throw new BusinessException("", e.getCause().getMessage());
			}
			throw  new BusinessException("","",e.getMessage());
		}
		String logs = properties.getCodes();
		Pager<com.pcitc.fms.bll.entity.Plant> pageData = new Pager<>();
		pageData.setContent(administrationEntityList);
		pageData.setFirst(properties.isFirst());
		pageData.setLast(properties.isLast());
		pageData.setNumber(properties.getNumber());
		pageData.setNumberOfElements(properties.getNumberOfElements());
		pageData.setSize(properties.getSize());
		pageData.setSort(properties.getSort());
		pageData.setTotalElements(properties.getCount());
		pageData.setTotalPages(properties.getTotalPages());
		pageData.setLogs(logs);
		return pageData;
	}
	
//	/**   
//	 * @Description: //查询指定组织机构单元编码的装置区
//	 * @date 2017年9月20日      
//	 * @return: Pager<Plant>
//	 */
//	@Override
//	public Pager<Plant> findPlantsByOrg(String orgUnitRootCode, String name,String orgCode, String orgUnitCode,String urlType,Pageable pageable) throws Exception {
//		List<com.pcitc.fms.dal.pojo.Plant> datalist = new ArrayList<>();
//		com.pcitc.fms.dal.pojo.OrgRelation orgRelationfactory = orgRelationDao.findAllBySourceCodeAndSourceTypeAndTargetType(orgUnitRootCode ,"orgUnitMeta");//查询组织结构编码对应的工厂
//		String factoryCode = orgRelationfactory == null ? null : orgRelationfactory.getTargetCode();
//		String orgUnitMetaStr = HostService.getOrgUnitMeta(orgCode, orgUnitCode);
//			List<OrgUnitModel> orgUnitMetaist = RestfulTool.toResourceRepList(orgUnitMetaStr, OrgUnitModel.class);
//			List<String> codeList = new ArrayList<>();
//			for (OrgUnitModel orgUnitMeta : orgUnitMetaist) {
//				codeList.add(orgUnitMeta.getOrgUnitCode());
//			}
//		Pager<com.pcitc.fms.bll.entity.Plant>  pageData = new Pager<>();
//		Page<com.pcitc.fms.dal.pojo.Plant> properties =	plantDao.getAllByRelation(codeList,name,factoryCode,urlType,pageable);
//		List<com.pcitc.fms.bll.entity.Plant> tankAreaList = ObjectConverter.listConverter(properties.getContent(),com.pcitc.fms.bll.entity.Plant.class);
//		pageData.setContent(tankAreaList);
//		pageData.setFirst(properties.isFirst());
//		pageData.setLast(properties.isLast());
//		pageData.setNumber(properties.getNumber());
//		pageData.setNumberOfElements(properties.getNumberOfElements());
//		pageData.setSize(properties.getSize());
//		pageData.setSort(properties.getSort());
//		pageData.setTotalElements(properties.getTotalElements());
//		pageData.setTotalPages(properties.getTotalPages());
//	return pageData;
//	}
//	/**
//	 * 查询工厂下是否有该装置
//	 * @param factoryId
//	 * @param plantId
//	 * @throws Exception
//	 */
//	private void checkParentAndPlant(String factoryCode, String plantCode) throws Exception{
//		Factory findOne = factoryDao.findByCode(factoryCode);
//		if(null == findOne)
//			throw new BusiException("", "factory"+":"+CheckError.NO_EXIST_ENTITY_ID+":"+factoryCode);
//		List<com.pcitc.fms.dal.pojo.Plant> plantList = plantDao.findPlantByFactoryCodeAndCode(factoryCode,plantCode);
//		if(null == plantList || plantList.size() == 0)
//			throw new BusiException("", "工厂:"+factoryCode+",不存在装置:"+plantCode);
//	}

}
