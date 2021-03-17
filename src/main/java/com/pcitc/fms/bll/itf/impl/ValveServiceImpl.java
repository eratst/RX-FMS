/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: ValveServiceImpl
 * Date:18-3-8 下午6:30
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
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.bll.entity.Valve;
import com.pcitc.fms.bll.itf.AdministrationService;
import com.pcitc.fms.bll.itf.CommunityService;
import com.pcitc.fms.bll.itf.LoadingDockService;
import com.pcitc.fms.bll.itf.PipeNetworkService;
import com.pcitc.fms.bll.itf.PlantService;
import com.pcitc.fms.bll.itf.TankAreaService;
import com.pcitc.fms.bll.itf.ValveService;
import com.pcitc.fms.bll.itf.WarehouseService;
import com.pcitc.fms.common.CheckError;
import com.pcitc.fms.common.CheckType;
import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.common.dispatcher.SysGlobal;
import com.pcitc.fms.dal.dao.AdministrationDao;
import com.pcitc.fms.dal.dao.AreaDictionaryDao;
import com.pcitc.fms.dal.dao.CommunityDao;
import com.pcitc.fms.dal.dao.DbPrimaryIdDao;
import com.pcitc.fms.dal.dao.FactoryDao;
import com.pcitc.fms.dal.dao.InclusionDao;
import com.pcitc.fms.dal.dao.LoadingDockDao;
import com.pcitc.fms.dal.dao.NodeDictionaryDao;
import com.pcitc.fms.dal.dao.PipeNetworkDao;
import com.pcitc.fms.dal.dao.PlantDao;
import com.pcitc.fms.dal.dao.SiloDao;
import com.pcitc.fms.dal.dao.TankAreaDao;
import com.pcitc.fms.dal.dao.ValveDao;
import com.pcitc.fms.dal.dao.WarehouseDao;
import com.pcitc.fms.dal.pojo.AreaDictionary;
import com.pcitc.fms.dal.pojo.Inclusion;
import com.pcitc.fms.dal.pojo.LoadingDock;
import com.pcitc.fms.dal.pojo.NodeDictionary;
import com.pcitc.fms.dal.pojo.PipeNetwork;
import com.pcitc.fms.dal.pojo.Plant;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.exception.BusinessExceptionMessage;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.imp.common.exception.BusiException;

import pcitc.imp.common.ettool.utils.ObjectConverter;

/**
 * Title: ValveServiceImpl Description:阀门
 *
 * @author zhenqiang.zhao
 * @version 1.0
 * @date 2017年6月14日
 */
@Service
@Component
public class ValveServiceImpl implements ValveService {

	/**
	 * The constant log.
	 */
	private static Logger log = LoggerFactory.getLogger(ValveServiceImpl.class);
	/**
	 * The Valve dao.
	 */
	@Autowired
	private ValveDao valveDao;


	/**
	 * Title: getValves Description: 查询多个或者单个
	 *
	 * @param valveModel the valve model
	 * @param pageable the pageable
	 * @param areaCode the area code
	 * @return valves valves
	 * @throws BusinessException the business exception
	 * @date 2017年6月15日
	 * @author zhenqiang.zhao  com.pcitc.fms.bll.itf.ValveService#getValves(com.pcitc.fms.service.model.Valve)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Pager<Valve> getValves(com.pcitc.fms.service.model.Valve valveModel, Pageable pageable)
			throws BusinessException {
		List<Valve> valveEntityList = new ArrayList<>();
		Pager<com.pcitc.fms.bll.entity.Valve> pageData = new Pager<>();
		MyPageImpl properties = null;
		try {
			properties = valveDao.getValves(valveModel, pageable);
		} catch (Exception e) {
			if (e instanceof UndeclaredThrowableException) {
				throw new BusinessException("", e.getCause().getMessage());
			}
		}
		try {
			valveEntityList = ObjectConverter.listConverter(properties.getContent(), Valve.class);
		} catch (Exception e) {
			log.error("fail",e);
			throw  new BusinessException("","",e.getMessage());
		}
		pageData.setContent(valveEntityList);
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
