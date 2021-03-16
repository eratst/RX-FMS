/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: AreaDictGatherServiceImpl
 * Date:18-3-8 下午6:19
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.bll.itf.impl;

import com.pcitc.fms.bll.dictionary.entity.AreaType;
import com.pcitc.fms.bll.dictionary.entity.Controldep;
import com.pcitc.fms.bll.dictionary.entity.LoadrackTechnic;
import com.pcitc.fms.bll.dictionary.entity.OpenindexClass;
import com.pcitc.fms.bll.dictionary.entity.OrgType;
import com.pcitc.fms.bll.dictionary.entity.PipenetTechnic;
import com.pcitc.fms.bll.dictionary.entity.Staalgr;
import com.pcitc.fms.bll.dictionary.entity.TankAreaTechnic;
import com.pcitc.fms.bll.dictionary.entity.TankAreaType;
import com.pcitc.fms.bll.dictionary.entity.Technic;
import com.pcitc.fms.bll.dictionary.entity.UnitType;
import com.pcitc.fms.bll.dictionary.entity.WarehousTechnic;
import com.pcitc.fms.bll.dictionary.entity.WarehouseType;
import com.pcitc.fms.bll.itf.AreaDictGatherService;
import com.pcitc.fms.dal.dao.AreaTypeDao;
import com.pcitc.fms.dal.dao.ControldepDao;
import com.pcitc.fms.dal.dao.LoadrackTechnicDao;
import com.pcitc.fms.dal.dao.OpenindexClassDao;
import com.pcitc.fms.dal.dao.OrgTypeDao;
import com.pcitc.fms.dal.dao.PipenetTechnicDao;
import com.pcitc.fms.dal.dao.StaalgrDao;
import com.pcitc.fms.dal.dao.TankAreaTechnicDao;
import com.pcitc.fms.dal.dao.TankAreaTypeDao;
import com.pcitc.fms.dal.dao.TechnicDao;
import com.pcitc.fms.dal.dao.UnitTypeDao;
import com.pcitc.fms.dal.dao.WarehousTechnicDao;
import com.pcitc.fms.dal.dao.WarehouseTypeDao;
import com.pcitc.fms.exception.BusinessException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pcitc.imp.common.ettool.utils.ObjectConverter;

;

/**
 * The type Area dict gather service.
 */
@Service
public class AreaDictGatherServiceImpl implements AreaDictGatherService {

	/**
	 * The constant log.
	 */
	private static Logger log = LoggerFactory.getLogger(AreaDictGatherServiceImpl.class);
	/**
	 * The Unit type dao.
	 */
	@Autowired
	private UnitTypeDao unitTypeDao;
	/**
	 * The Org type dao.
	 */
	@Autowired
	private OrgTypeDao orgTypeDao;
	/**
	 * The Area type dao.
	 */
	@Autowired
	private AreaTypeDao areaTypeDao;
	/**
	 * The Technic dao.
	 */
	@Autowired
	private TechnicDao technicDao;
	/**
	 * The Tank area type dao.
	 */
	@Autowired
	private TankAreaTypeDao tankAreaTypeDao;
	/**
	 * The Tank area technic dao.
	 */
	@Autowired
	private TankAreaTechnicDao tankAreaTechnicDao;
	/**
	 * The Warehouse type dao.
	 */
	@Autowired
	private WarehouseTypeDao warehouseTypeDao;
	/**
	 * The Warehous technic dao.
	 */
	@Autowired
	private WarehousTechnicDao warehousTechnicDao;
	/**
	 * The Loadrack technic dao.
	 */
	@Autowired
	private LoadrackTechnicDao loadrackTechnicDao;
	/**
	 * The Pipenet technic dao.
	 */
	@Autowired
	private PipenetTechnicDao pipenetTechnicDao;
	/**
	 * The Openindex class dao.
	 */
	@Autowired
	private OpenindexClassDao  openindexClassDao;
	/**
	 * The Controldep dao.
	 */
	@Autowired
	private ControldepDao  controldepDao;
	/**
	 * The Staalgr dao.
	 */
	@Autowired
	private StaalgrDao  staalgrDao;

	/**
	 * Gets org types.
	 *
	 * @param code the code
	 * @return the org types
	 * @throws BusinessException the business exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<OrgType> getOrgTypes(String code) throws BusinessException {
		List<com.pcitc.fms.bll.dictionary.entity.OrgType> orgTypes_entity = new ArrayList<>();
		List<com.pcitc.fms.dal.pojo.OrgType> orgTypes = new ArrayList<>();
		try {
			if(null != code && !"".equals(code)){
				orgTypes = orgTypeDao.findByOrgTypeCode(code);
			}else{
				orgTypes = orgTypeDao.findAll();
			}
			orgTypes_entity = ObjectConverter.listConverter(orgTypes,com.pcitc.fms.bll.dictionary.entity.OrgType.class);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new BusinessException("", "", e.getMessage());
		}
		return orgTypes_entity;
	}

	/**
	 * Gets area types.
	 *
	 * @param code the code
	 * @return the area types
	 * @throws BusinessException the business exception
	 */
	@Override
	public List<AreaType> getAreaTypes(String code) throws BusinessException {
		List<com.pcitc.fms.bll.dictionary.entity.AreaType> orgTypes_entity = new ArrayList<>();
		List<com.pcitc.fms.dal.pojo.AreaType> orgTypes = new ArrayList<>();
		try {
			if(null != code && !"".equals(code)){
				orgTypes = areaTypeDao.findByAreaTypeCode(code);
			}else{
				orgTypes = areaTypeDao.findAll();
			}
			orgTypes_entity = ObjectConverter.listConverter(orgTypes,com.pcitc.fms.bll.dictionary.entity.AreaType.class);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new BusinessException("", "", e.getMessage());
		}
		return orgTypes_entity;
	}

	/**
	 * Gets unit types.
	 *
	 * @param code the code
	 * @return the unit types
	 * @throws BusinessException the business exception
	 */
	@Override
	public List<UnitType> getUnitTypes(String code) throws BusinessException{
		List<com.pcitc.fms.bll.dictionary.entity.UnitType> dictlist_entity = new ArrayList<>();
		List<com.pcitc.fms.dal.pojo.UnitType> dictlist_pojo = new ArrayList<>();
		try {
			if(null != code && !"".equals(code)){
				dictlist_pojo = unitTypeDao.findByUnitTypeCode(code);
			}else{
				dictlist_pojo = unitTypeDao.findAll();
			}
			dictlist_entity = ObjectConverter.listConverter(dictlist_pojo,com.pcitc.fms.bll.dictionary.entity.UnitType.class);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new BusinessException("", "", e.getMessage());
		}
		return dictlist_entity;
	}

	/**
	 * Gets technics.
	 *
	 * @param code the code
	 * @return the technics
	 * @throws BusinessException the business exception
	 */
	@Override
	public List<Technic> getTechnics(String code) throws BusinessException {
		List<com.pcitc.fms.bll.dictionary.entity.Technic> dictlist_entity = new ArrayList<>();
		List<com.pcitc.fms.dal.pojo.Technic> dictlist_pojo = new ArrayList<>();
		try {
			if(null != code && !"".equals(code)){
				dictlist_pojo = technicDao.findByTechnicCode(code);
			}else{
				dictlist_pojo = technicDao.findAll();
			}
			dictlist_entity = ObjectConverter.listConverter(dictlist_pojo,com.pcitc.fms.bll.dictionary.entity.Technic.class);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new BusinessException("", "", e.getMessage());
		}
		return dictlist_entity;
	}

	/**
	 * Gets tank area types.
	 *
	 * @param code the code
	 * @return the tank area types
	 * @throws BusinessException the business exception
	 */
	@Override
	public List<TankAreaType> getTankAreaTypes(String code) throws BusinessException {
		List<com.pcitc.fms.bll.dictionary.entity.TankAreaType> dictlist_entity = new ArrayList<>();
		List<com.pcitc.fms.dal.pojo.TankAreaType> dictlist_pojo = new ArrayList<>();
		try {
			if(null != code && !"".equals(code)){
				dictlist_pojo = tankAreaTypeDao.findByTankAreaTypeCode(code);
			}else{
				dictlist_pojo = tankAreaTypeDao.findAll();
			}
			dictlist_entity = ObjectConverter.listConverter(dictlist_pojo,com.pcitc.fms.bll.dictionary.entity.TankAreaType.class);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new BusinessException("", "", e.getMessage());
		}
		return dictlist_entity;
	}

	/**
	 * Gets tank area technics.
	 *
	 * @param code the code
	 * @return the tank area technics
	 * @throws BusinessException the business exception
	 */
	@Override
	public List<TankAreaTechnic> getTankAreaTechnics(String code) throws BusinessException {
		List<com.pcitc.fms.bll.dictionary.entity.TankAreaTechnic> dictlist_entity = new ArrayList<>();
		List<com.pcitc.fms.dal.pojo.TankAreaTechnic> dictlist_pojo = new ArrayList<>();
		try {
			if(null != code && !"".equals(code)){
				dictlist_pojo = tankAreaTechnicDao.findByTechnicCode(code);
			}else{
				dictlist_pojo = tankAreaTechnicDao.findAll();
			}
			dictlist_entity = ObjectConverter.listConverter(dictlist_pojo,com.pcitc.fms.bll.dictionary.entity.TankAreaTechnic.class);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new BusinessException("", "", e.getMessage());
		}
		return dictlist_entity;
	}

	/**
	 * Gets warehouse types.
	 *
	 * @param code the code
	 * @return the warehouse types
	 * @throws BusinessException the business exception
	 */
	@Override
	public List<WarehouseType> getWarehouseTypes(String code) throws BusinessException {
		List<com.pcitc.fms.bll.dictionary.entity.WarehouseType> dictlist_entity = new ArrayList<>();
		List<com.pcitc.fms.dal.pojo.WarehouseType> dictlist_pojo = new ArrayList<>();
		try {
			if(null != code && !"".equals(code)){
				dictlist_pojo = warehouseTypeDao.findByWarehouseTypeCode(code);
			}else{
				dictlist_pojo = warehouseTypeDao.findAll();
			}
			dictlist_entity = ObjectConverter.listConverter(dictlist_pojo,com.pcitc.fms.bll.dictionary.entity.WarehouseType.class);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new BusinessException("", "", e.getMessage());
		}
		return dictlist_entity;
	}

	/**
	 * Gets warehous technics.
	 *
	 * @param code the code
	 * @return the warehous technics
	 * @throws BusinessException the business exception
	 */
	@Override
	public List<WarehousTechnic> getWarehousTechnics(String code) throws BusinessException {
		List<com.pcitc.fms.bll.dictionary.entity.WarehousTechnic> dictlist_entity = new ArrayList<>();
		List<com.pcitc.fms.dal.pojo.WarehousTechnic> dictlist_pojo = new ArrayList<>();
		try {
			if(null != code && !"".equals(code)){
				dictlist_pojo = warehousTechnicDao.findByTechnicCode(code);
			}else{
				dictlist_pojo = warehousTechnicDao.findAll();
			}
			dictlist_entity = ObjectConverter.listConverter(dictlist_pojo,com.pcitc.fms.bll.dictionary.entity.WarehousTechnic.class);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new BusinessException("", "", e.getMessage());
		}
		return dictlist_entity;
	}

	/**
	 * Gets loadrack technics.
	 *
	 * @param code the code
	 * @return the loadrack technics
	 * @throws BusinessException the business exception
	 */
	@Override
	public List<LoadrackTechnic> getLoadrackTechnics(String code) throws BusinessException {
		List<com.pcitc.fms.bll.dictionary.entity.LoadrackTechnic> dictlist_entity = new ArrayList<>();
		List<com.pcitc.fms.dal.pojo.LoadrackTechnic> dictlist_pojo = new ArrayList<>();
		try {
			if(null != code && !"".equals(code)){
				dictlist_pojo = loadrackTechnicDao.findByTechnicCode(code);
			}else{
				dictlist_pojo = loadrackTechnicDao.findAll();
			}
			dictlist_entity = ObjectConverter.listConverter(dictlist_pojo,com.pcitc.fms.bll.dictionary.entity.LoadrackTechnic.class);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new BusinessException("", "", e.getMessage());
		}
		return dictlist_entity;
	}

	/**
	 * Gets pipenet technics.
	 *
	 * @param code the code
	 * @return the pipenet technics
	 * @throws BusinessException the business exception
	 */
	@Override
	public List<PipenetTechnic> getPipenetTechnics(String code) throws BusinessException {
		List<com.pcitc.fms.bll.dictionary.entity.PipenetTechnic> dictlist_entity = new ArrayList<>();
		List<com.pcitc.fms.dal.pojo.PipenetTechnic> dictlist_pojo = new ArrayList<>();
		try {
			if(null != code && !"".equals(code)){
				dictlist_pojo = pipenetTechnicDao.findByTechnicCode(code);
			}else{
				dictlist_pojo = pipenetTechnicDao.findAll();
			}
			dictlist_entity = ObjectConverter.listConverter(dictlist_pojo,com.pcitc.fms.bll.dictionary.entity.PipenetTechnic.class);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new BusinessException("", "", e.getMessage());
		}
		return dictlist_entity;
	}

	/**
	 * Gets openindex class.
	 *
	 * @param code the code
	 * @return the openindex class
	 * @throws BusinessException the business exception
	 */
	@Override
	public List<OpenindexClass> getOpenindexClass(String code) throws BusinessException {
		List<com.pcitc.fms.bll.dictionary.entity.OpenindexClass> dictlist_entity = new ArrayList<>();
		List<com.pcitc.fms.dal.pojo.OpenindexClass> dictlist_pojo = new ArrayList<>();
		try {
			if(null != code && !"".equals(code)){
				dictlist_pojo = openindexClassDao.findByCode(code);
			}else{
				dictlist_pojo = openindexClassDao.findAll();
			}
			dictlist_entity = ObjectConverter.listConverter(dictlist_pojo,com.pcitc.fms.bll.dictionary.entity.OpenindexClass.class);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new BusinessException("", "", e.getMessage());
		}
		return dictlist_entity;
	}

	/**
	 * Gets controldeps.
	 *
	 * @param code the code
	 * @return the controldeps
	 * @throws BusinessException the business exception
	 */
	@Override
	public List<Controldep> getControldeps(String code) throws BusinessException {
		List<com.pcitc.fms.bll.dictionary.entity.Controldep> dictlist_entity = new ArrayList<>();
		List<com.pcitc.fms.dal.pojo.Controldep> dictlist_pojo = new ArrayList<>();
		try {
			if(null != code && !"".equals(code)){
				dictlist_pojo = controldepDao.findByCode(code);
			}else{
				dictlist_pojo = controldepDao.findAll();
			}
			dictlist_entity = ObjectConverter.listConverter(dictlist_pojo,com.pcitc.fms.bll.dictionary.entity.Controldep.class);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new BusinessException("", "", e.getMessage());
		}
		return dictlist_entity;
	}

	/**
	 * Gets staalgrs.
	 *
	 * @param code the code
	 * @return the staalgrs
	 * @throws BusinessException the business exception
	 */
	@Override
	public List<Staalgr> getStaalgrs(String code) throws BusinessException {
		List<com.pcitc.fms.bll.dictionary.entity.Staalgr> dictlist_entity = new ArrayList<>();
		List<com.pcitc.fms.dal.pojo.Staalgr> dictlist_pojo = new ArrayList<>();
		try {
			if(null != code && !"".equals(code)){
				dictlist_pojo = staalgrDao.findByCode(code);
			}else{
				dictlist_pojo = staalgrDao.findAll();
			}
			dictlist_entity = ObjectConverter.listConverter(dictlist_pojo,com.pcitc.fms.bll.dictionary.entity.Staalgr.class);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new BusinessException("", "", e.getMessage());
		}
		return dictlist_entity;
	}

}
