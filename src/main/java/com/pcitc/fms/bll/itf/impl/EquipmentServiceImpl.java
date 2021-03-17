/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: EquipmentServiceImpl
 * Date:18-3-8 下午6:21
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.bll.itf.impl;

import java.lang.reflect.UndeclaredThrowableException;
import java.math.BigDecimal;
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

import com.pcitc.fms.bll.itf.AdministrationService;
import com.pcitc.fms.bll.itf.CommunityService;
import com.pcitc.fms.bll.itf.EquipmentService;
import com.pcitc.fms.bll.itf.LoadingDockService;
import com.pcitc.fms.bll.itf.PipeNetworkService;
import com.pcitc.fms.bll.itf.PlantService;
import com.pcitc.fms.bll.itf.TankAreaService;
import com.pcitc.fms.bll.itf.WarehouseService;
import com.pcitc.fms.common.CheckError;
import com.pcitc.fms.common.CheckType;
import com.pcitc.fms.common.CheckUtil;
import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.common.dispatcher.SysGlobal;
import com.pcitc.fms.dal.dao.AdministrationDao;
import com.pcitc.fms.dal.dao.AreaDictionaryDao;
import com.pcitc.fms.dal.dao.CommunityDao;
import com.pcitc.fms.dal.dao.DbPrimaryIdDao;
import com.pcitc.fms.dal.dao.EquipmentDao;
import com.pcitc.fms.dal.dao.FactoryDao;
import com.pcitc.fms.dal.dao.InclusionDao;
import com.pcitc.fms.dal.dao.LoadingDockDao;
import com.pcitc.fms.dal.dao.NodeDictionaryDao;
import com.pcitc.fms.dal.dao.PipeNetworkDao;
import com.pcitc.fms.dal.dao.PlantDao;
import com.pcitc.fms.dal.dao.TankAreaDao;
import com.pcitc.fms.dal.dao.WarehouseDao;
import com.pcitc.fms.dal.pojo.AreaDictionary;
import com.pcitc.fms.dal.pojo.Inclusion;
import com.pcitc.fms.dal.pojo.LoadingDock;
import com.pcitc.fms.dal.pojo.NodeDictionary;
import com.pcitc.fms.dal.pojo.PipeNetwork;
import com.pcitc.fms.dal.pojo.Plant;
import com.pcitc.fms.exception.BusinessExceptionMessage;
import com.pcitc.fms.service.model.Equipment;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.imp.common.exception.BusiException;

import com.pcitc.fms.exception.BusinessException;
import pcitc.imp.common.ettool.utils.ObjectConverter;

/**
 * The type Equipment service.
 *
 * @version 创建时间 ：2017年6月8日 下午3:52:01 类说明
 * @author:
 */
@Service
@Component
@SuppressWarnings("unchecked")
public class EquipmentServiceImpl  implements EquipmentService {

	/**
	 * The constant log.
	 */
	private static Logger log = LoggerFactory.getLogger(EquipmentServiceImpl.class);
	/**
	 * The Equipment dao.
	 */
	@Autowired
    private EquipmentDao equipmentDao;


	/**
	 * Gets page equipments.
	 *
	 * @param model the model
	 * @param pageable the pageable
	 * @param areaCode the area code
	 * @return the page equipments
	 * @throws BusinessException the business exception
	 */
	@Override
    @Transactional(rollbackFor = BusinessException.class)
    public Pager<com.pcitc.fms.bll.entity.Equipment> getPageEquipments(com.pcitc.fms.service.model.Equipment model, Pageable pageable) throws BusinessException {
		MyPageImpl properties=null;	
		List<com.pcitc.fms.bll.entity.Equipment> propertiesEntity = new ArrayList<>() ;
		try {
				com.pcitc.fms.bll.entity.Equipment entity = ObjectConverter.entityConverter(model, com.pcitc.fms.bll.entity.Equipment.class);
			} catch (Exception e) {
				log.error("fail",e);
				throw  new BusinessException("","",e.getMessage());
			}
			try {
				properties = equipmentDao.getAll(model, pageable);
			} catch (Exception e) {
				if (e instanceof UndeclaredThrowableException) {
					throw new BusinessException("", e.getCause().getMessage());
				}
			}
			try {
				propertiesEntity = ObjectConverter.listConverter(properties.getContent(), com.pcitc.fms.bll.entity.Equipment.class);
			} catch (Exception e) {
				log.error("fail",e);
				throw  new BusinessException("","",e.getMessage());
			}
			Pager<com.pcitc.fms.bll.entity.Equipment> pageData = new Pager<>();
			pageData.setContent(propertiesEntity);
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
