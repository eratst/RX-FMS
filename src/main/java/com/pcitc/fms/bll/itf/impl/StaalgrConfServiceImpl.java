/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: StaalgrConfServiceImpl
 * Date:18-3-8 下午6:24
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.bll.itf.impl;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.pcitc.fms.bll.entity.StaalgrConf;
import com.pcitc.fms.bll.itf.StaalgrConfService;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.dal.dao.PlantDao;
import com.pcitc.fms.dal.dao.StaalgrConfDao;
import com.pcitc.fms.dal.dao.StaalgrConfDaoImpl;
import com.pcitc.fms.dal.dao.StaalgrConfitemDao;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.fms.service.model.UnitAlarm;
import com.pcitc.imp.common.exception.BusiException;

import pcitc.imp.common.ettool.utils.ObjectConverter;

/**
 * The type Staalgr conf service.
 *
 * @version 创建时间 ：12.6  类说明
 * @author: hanxiao
 */
@Service
@Component
public class StaalgrConfServiceImpl implements StaalgrConfService {

	/**
	 * The constant log.
	 */
	private static Logger log = LoggerFactory.getLogger(StaalgrConfServiceImpl.class);
	/**
	 * The Staalgr conf dao.
	 */
	@Autowired
	private StaalgrConfDaoImpl staalgrConfDaoImpl;
	/**
	 * The Staalgr conf dao.
	 */
	@Autowired
	private StaalgrConfDao staalgrConfDao;
	/**
	 * The Staalgr confitem dao.
	 */
	@Autowired
	private StaalgrConfitemDao staalgrConfitemDao;
	/**
	 * The Plant dao.
	 */
	@Autowired
	private PlantDao plantDao;

	/**
	 * Gets page staalgr confs.
	 *
	 * @param staalgrConf the staalgr conf
	 * @return the page staalgr confs
	 * @throws BusinessException the business exception
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Pager<StaalgrConf> getPageStaalgrConfs(com.pcitc.fms.service.model.StaalgrConf staalgrConf) throws BusinessException {
		//创建 Pageable
		Pageable pageable = getPageable(staalgrConf);
		Page<com.pcitc.fms.dal.pojo.StaalgrConf> properties =null;
		//传入dao层
		try{
		properties= staalgrConfDaoImpl.findPageStaalgrConfs(staalgrConf ,pageable);
		} catch (Exception e) {
			if (e instanceof UndeclaredThrowableException) {
				throw new BusinessException("", e.getCause().getMessage());
			}
		}
		//对返回做处理
		List<StaalgrConf> entityList = null;
		try {
			entityList = ObjectConverter.listConverter(properties.getContent(),StaalgrConf.class);
		} catch (Exception e) {
			log.error("fail",e);
			throw  new BusinessException("","",e.getMessage());
		}
		Pager<com.pcitc.fms.bll.entity.StaalgrConf> pageData = new Pager<>();
		pageData.setContent(entityList);
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
	 * Gets pageable.
	 *
	 * @param staalgrConf the staalgr conf
	 * @return the pageable
	 * @throws BusinessException the business exception
	 */
	private Pageable getPageable(com.pcitc.fms.service.model.StaalgrConf staalgrConf) throws BusinessException {
		Pageable  pageable  = null;
		Sort sort = new Sort(Sort.Direction.ASC, "staalgrConfId");
		//分页校验
		if (null != staalgrConf.getTop() && null != staalgrConf.getSkip()) {
			pageable = PageUtil.pageable(staalgrConf.getTop(), staalgrConf.getSkip(), sort);
		}
		return pageable;
	}

	/**
	 * Gets by code.
	 *
	 * @param code the code
	 * @return the by code
	 * @throws BusinessException the business exception
	 */
	@Override
	public StaalgrConf getByCode(String code) throws BusinessException {
		com.pcitc.fms.dal.pojo.StaalgrConf pojo = staalgrConfDao.getByCode(code);
		StaalgrConf entity = null;
		try {
			entity = ObjectConverter.entityConverter(pojo, StaalgrConf.class);
		} catch (Exception e) {
			log.error("fail",e);
			throw  new BusinessException("","",e.getMessage());
		}

		return entity;
	}

	/**
	 * Add staalgr conf list.
	 *
	 * @param modelList the model list
	 * @return the list
	 * @throws BusinessException the business exception
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<StaalgrConf> addStaalgrConf(List<com.pcitc.fms.service.model.StaalgrConf> modelList) throws BusinessException {
		List<com.pcitc.fms.dal.pojo.StaalgrConf> pojoList = new ArrayList<>();
	    List<com.pcitc.fms.bll.entity.StaalgrConf> entityList =  new ArrayList<>(); 
	    try {
			// 将viewmodel转换成entity  
	    	entityList = ObjectConverter.listConverter(modelList,com.pcitc.fms.bll.entity.StaalgrConf.class);
	    	pojoList = ObjectConverter.listConverter(entityList,com.pcitc.fms.dal.pojo.StaalgrConf.class);
	    	pojoList = staalgrConfDao.save(pojoList);
	    	entityList = ObjectConverter.listConverter(pojoList,com.pcitc.fms.bll.entity.StaalgrConf.class);
		}  catch (Exception e) {
			throw new BusinessException("", e.getMessage());
			
		}
		return entityList;
	}

	/**
	 * Update staalgr conf.
	 *
	 * @param modelList the model list
	 * @param unitCode the unit code
	 * @throws BusinessException the business exception
	 */
	@Override
	public void updateStaalgrConf(List<com.pcitc.fms.service.model.StaalgrConf> modelList,String unitCode) throws BusinessException {
		// 将viewmodel转换成entity 	
		List<StaalgrConf> entityList = null;
		try {
			entityList = ObjectConverter.listConverter(modelList, StaalgrConf.class);
		} catch (Exception e) {
			log.error("fail",e);
			throw  new BusinessException("","",e.getMessage());
		}
		try {
			//校验传入的实体信息，是否是一个，是否存在，并返回pojo
			com.pcitc.fms.dal.pojo.StaalgrConf pojo = checkForEntity(entityList,unitCode);
			//装配区域分别表并更新
			newSubArea(pojo,entityList);
			staalgrConfDao.saveAndFlush(pojo);
		}  catch (Exception e) {
				throw new BusinessException("", e.toString());
		}

	}

	/**
	 * 查询传入实体是否唯一，是够存在
	 *
	 * @param entityList the entity list
	 * @param unitCode the unit code
	 * @return the com . pcitc . fms . dal . pojo . staalgr conf
	 * @throws BusinessException the business exception
	 */
	private com.pcitc.fms.dal.pojo.StaalgrConf checkForEntity(List<com.pcitc.fms.bll.entity.StaalgrConf> entityList,String unitCode) throws BusinessException {
			//校验是否唯一个实体
			if(entityList.size() != 1) {
				throw new BusinessException("", "", "更新操作只能传入一个实体！");
			}
			com.pcitc.fms.dal.pojo.Plant plant = plantDao.findByCode(unitCode);
			if(plant == null) {
				throw new BusinessException("", "", "不存在unitCode："+unitCode+"的设备！");
			} 
			Integer equipId = plant.getPlantId().intValue();
			//校验是否存在等于code的entity实体
			Integer monLevelId = entityList.get(0).getMonLevelId();
			com.pcitc.fms.dal.pojo.StaalgrConf pojo = staalgrConfDao.getByEquipIdAndMonLevelId(equipId,monLevelId);
			if(pojo == null) {
				throw new BusinessException("", "", "不存在unitCode:"+unitCode+" monLevelId:"+monLevelId+"的平稳率配置项信息！");
			}
		    return pojo;
	}

	/**
	 * 通过查询到的分表来装配新的分表
	 *
	 * @param pojo the pojo
	 * @param entityList the entity list
	 */
	private void newSubArea(com.pcitc.fms.dal.pojo.StaalgrConf pojo,
				List<com.pcitc.fms.bll.entity.StaalgrConf> entityList) {
			// 找到要改的字段,共有字段和特有字段
			com.pcitc.fms.bll.entity.StaalgrConf entity = entityList.get(0);
			pojo.setName(entity.getName());
			pojo.setInUse(entity.getInUse());
			pojo.setDes(entity.getDes());
			pojo.setEditor(entity.getEditor());
			pojo.setEditorId(entity.getEditorId());
			pojo.setMaintainTime(new Date());
			pojo.setSortNum(entity.getSortNum());
			pojo.setMonLevelId(entity.getMonLevelId());
			pojo.setStaalgrId(entity.getStaalgrId());
		}

	/**
	 * Delete by mon level id and equip id.
	 *
	 * @param monLevelId the mon level id
	 * @param unitCode the unit code
	 * @throws BusinessException the business exception
	 */
	@Override
	public void deleteByMonLevelIdAndEquipId(Integer monLevelId, String unitCode) throws BusinessException {
		
		com.pcitc.fms.dal.pojo.Plant plant = plantDao.findByCode(unitCode);
		if(plant == null) {
			throw new BusinessException("", "", "不存在unitCode："+unitCode+"的设备！");
		} 
		Integer equipId = plant.getPlantId().intValue();
		com.pcitc.fms.dal.pojo.StaalgrConf pojo = staalgrConfDao.getByEquipIdAndMonLevelId(equipId,monLevelId);
		if(pojo == null) {
			throw new BusinessException("", "", "不存在unitCode："+unitCode+"monLevelId:"+monLevelId+"的数据！");
		}
		//获取平稳率配置id
		Integer staalgrConfId = pojo.getStaalgrConfId();
		//校验是否有平稳率配置项关联
		List<com.pcitc.fms.dal.pojo.StaalgrConfitem> staalgrConfitemLists=staalgrConfitemDao.getByStaalgrConfId(staalgrConfId);
		if(staalgrConfitemLists.size() != 0) {
			throw new BusinessException("", "", "此平稳率配置下有关联的平稳率配置项，无法直接删除！");
		}
		staalgrConfDao.deleteByMonLevelIdAndEquipId(monLevelId,equipId);
		
	}

	/**
	 * Delete by agent code.
	 *
	 * @param agentCode the agent code
	 * @throws BusinessException the business exception
	 */
	@Override
	public void deleteByAgentCode(String agentCode) throws BusinessException {
		List<com.pcitc.fms.dal.pojo.StaalgrConf> pojos = staalgrConfDao.getByAgentCode(agentCode);
		for(com.pcitc.fms.dal.pojo.StaalgrConf pojo: pojos) {
			//获取平稳率配置id
			Integer staalgrConfId = pojo.getStaalgrConfId();
			//校验是否有平稳率配置项关联
			List<com.pcitc.fms.dal.pojo.StaalgrConfitem> staalgrConfitemLists=staalgrConfitemDao.getByStaalgrConfId(staalgrConfId);
			if(staalgrConfitemLists.size() != 0) {
				throw new BusinessException("", "", "此平稳率配置下有关联的平稳率配置项，无法直接删除！");
			}
		}
		staalgrConfDao.deleteByAgentCode(agentCode);
		
	}
	

}
