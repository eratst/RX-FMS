/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: PlateServiceImpl
 * Date:18-3-8 下午6:23
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.bll.entity.Plate;
import com.pcitc.fms.bll.itf.AdministrationService;
import com.pcitc.fms.bll.itf.CommunityService;
import com.pcitc.fms.bll.itf.LoadingDockService;
import com.pcitc.fms.bll.itf.PipeNetworkService;
import com.pcitc.fms.bll.itf.PlantService;
import com.pcitc.fms.bll.itf.PlateService;
import com.pcitc.fms.bll.itf.TankAreaService;
import com.pcitc.fms.bll.itf.WarehouseService;
import com.pcitc.fms.common.CheckError;
import com.pcitc.fms.common.CheckType;
import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.dal.dao.AdministrationDao;
import com.pcitc.fms.dal.dao.AreaDictionaryDao;
import com.pcitc.fms.dal.dao.AreaTypeDao;
import com.pcitc.fms.dal.dao.CommunityDao;
import com.pcitc.fms.dal.dao.DbPrimaryIdDao;
import com.pcitc.fms.dal.dao.FactoryDao;
import com.pcitc.fms.dal.dao.InclusionDao;
import com.pcitc.fms.dal.dao.LoadingDockDao;
import com.pcitc.fms.dal.dao.NodeDictionaryDao;
import com.pcitc.fms.dal.dao.NodeTypeDao;
import com.pcitc.fms.dal.dao.PipeNetworkDao;
import com.pcitc.fms.dal.dao.PlantDao;
import com.pcitc.fms.dal.dao.PlateDao;
import com.pcitc.fms.dal.dao.TankAreaDao;
import com.pcitc.fms.dal.dao.WarehouseDao;
import com.pcitc.fms.dal.pojo.AreaDictionary;
import com.pcitc.fms.dal.pojo.NodeDictionary;
import com.pcitc.fms.dal.pojo.NodeType;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.exception.BusinessExceptionMessage;
import com.pcitc.fms.service.model.Pager;

import pcitc.imp.common.ettool.utils.ObjectConverter;

/**
 * Title: PlateServiceImpl Description:TODO
 *
 * @author zhenqiang.zhao
 * @version 1.0
 * @date 2017年6月16日
 */
@Service
public class PlateServiceImpl extends NodeCommonServiceImpl implements PlateService {
	/**
	 * The Plate dao.
	 */
	@Autowired
	private PlateDao plateDao;


	/**
	 * Title: getPlates Description: TODO
	 *
	 * @param plateModel the plate model
	 * @param pageable the pageable
	 * @param areaCode the area code
	 * @return plates plates
	 * @throws BusinessException the business exception
	 * @date 2017年6月16日
	 * @author zhenqiang.zhao  com.pcitc.fms.bll.itf.PlateService#getPlates(com.pcitc.fms.service.model.Plate)
	 */
	@Override
	public Pager<com.pcitc.fms.bll.entity.Plate> getPlates(com.pcitc.fms.service.model.Plate plateModel,
			Pageable pageable) throws BusinessException {
		List<com.pcitc.fms.bll.entity.Plate> plateEntityList = new ArrayList<>();

		Pager<com.pcitc.fms.bll.entity.Plate> pageData = new Pager<>();

		MyPageImpl properties = null;
		try {
			properties = plateDao.getPlates(plateModel, pageable);
		} catch (Exception e) {
			if (e instanceof UndeclaredThrowableException) {
				throw new BusinessException("", e.getCause().getMessage());
			}
		}
		try {
			plateEntityList = ObjectConverter.listConverter(properties.getContent(), Plate.class);
		} catch (Exception e) {
			throw  new BusinessException("","",e.getMessage());
		}
		pageData.setContent(plateEntityList);
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
