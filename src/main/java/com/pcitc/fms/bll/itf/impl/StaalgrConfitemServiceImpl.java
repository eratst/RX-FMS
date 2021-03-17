/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: StaalgrConfitemServiceImpl
 * Date:18-3-8 下午6:24
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
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.bll.entity.StaalgrConfitem;
import com.pcitc.fms.bll.itf.StaalgrConfitemService;
import com.pcitc.fms.common.PageUtil;
import com.pcitc.fms.dal.dao.StaalgrConfitemDao;
import com.pcitc.fms.dal.dao.StaalgrConfitemDaoImpl;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.fms.service.model.UnitAlarm;
import com.pcitc.imp.common.exception.BusiException;

import pcitc.imp.common.ettool.utils.ObjectConverter;

/**
 * The type Staalgr confitem service.
 *
 * @version 创建时间 ：12.6  类说明
 * @author: hanxiao
 */
@Service
@Component
public class StaalgrConfitemServiceImpl implements StaalgrConfitemService {

	/**
	 * The constant log.
	 */
	private static Logger log = LoggerFactory.getLogger(StaalgrConfitemServiceImpl.class);
	/**
	 * The Staalgr confitem dao.
	 */
	@Autowired
	private StaalgrConfitemDaoImpl staalgrConfitemDaoImpl;
	/**
	 * The Staalgr confitem dao.
	 */
	@Autowired
	private StaalgrConfitemDao staalgrConfitemDao;

	/**
	 * Gets page staalgr confitems.
	 *
	 * @param staalgrConfitem the staalgr confitem
	 * @return the page staalgr confitems
	 * @throws BusinessException the business exception
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Pager<StaalgrConfitem> getPageStaalgrConfitems(com.pcitc.fms.service.model.StaalgrConfitem staalgrConfitem) throws BusinessException {
		//创建 Pageable
		Pageable pageable = getPageable(staalgrConfitem);
		//传入dao层
		Page<com.pcitc.fms.dal.pojo.StaalgrConfitem> properties  = staalgrConfitemDaoImpl.findPageStaalgrConfitems(staalgrConfitem ,pageable);
		//对返回做处理
		List<StaalgrConfitem> entityList = null;
		try {
			entityList = ObjectConverter.listConverter(properties.getContent(),StaalgrConfitem.class);
		} catch (Exception e) {
			log.error("fail",e);
			throw  new BusinessException("","",e.getMessage());
		}
		Pager<com.pcitc.fms.bll.entity.StaalgrConfitem> pageData = new Pager<>();
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
	 * @param staalgrConfitem the staalgr confitem
	 * @return the pageable
	 * @throws BusinessException the business exception
	 */
	private Pageable getPageable(com.pcitc.fms.service.model.StaalgrConfitem staalgrConfitem) throws BusinessException {
		Pageable  pageable  = null;
		Sort sort = new Sort(Sort.Direction.ASC, "staalgrConfitemId");
		//分页校验
		if (null != staalgrConfitem.getTop() && null != staalgrConfitem.getSkip()) {
			pageable = PageUtil.pageable(staalgrConfitem.getTop(), staalgrConfitem.getSkip(), sort);
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
	public StaalgrConfitem getByCode(String code) throws BusinessException {
		com.pcitc.fms.dal.pojo.StaalgrConfitem pojo = staalgrConfitemDao.getByCodes(code);
		StaalgrConfitem entity = null;
		try {
			entity = ObjectConverter.entityConverter(pojo, StaalgrConfitem.class);
		} catch (Exception e) {
			log.error("fail",e);
			throw  new BusinessException("","",e.getMessage());
		}

		return entity;
	}

	/**
	 * Add staalgr confitem list.
	 *
	 * @param modelList the model list
	 * @return the list
	 * @throws BusinessException the business exception
	 */
	@Override
	@SuppressWarnings("unchecked")
	@Transactional(rollbackFor=BusinessException.class)
	public List<StaalgrConfitem> addStaalgrConfitem(List<com.pcitc.fms.service.model.StaalgrConfitem> modelList) throws BusinessException {
		List<com.pcitc.fms.dal.pojo.StaalgrConfitem> pojoList = new ArrayList<>();
		List<com.pcitc.fms.dal.pojo.StaalgrConfitem> pojoLists = new ArrayList<>();
	    List<com.pcitc.fms.bll.entity.StaalgrConfitem> entityList =  new ArrayList<>(); 
	    try {
			// 将viewmodel转换成entity  
	    	entityList = ObjectConverter.listConverter(modelList,com.pcitc.fms.bll.entity.StaalgrConfitem.class);
	    	pojoList = ObjectConverter.listConverter(entityList,com.pcitc.fms.dal.pojo.StaalgrConfitem.class);
	    	pojoLists = staalgrConfitemDao.save(pojoList);
	    	entityList = ObjectConverter.listConverter(pojoLists,com.pcitc.fms.bll.entity.StaalgrConfitem.class);
		} catch (DataIntegrityViolationException e) {//这里要考虑name shortNam code重复报错
			if(e.toString().contains("UK_CODE1")) {
				throw new BusinessException("", "", "code不能重复！");
			}
			if(e.toString().contains("UK_NAME1")) {
				throw new BusinessException("", "", "name不能重复！");
			}
			if(e.toString().contains("UK_STAALGRCONFITEM_1")) {
				throw new BusinessException("", "", "新增数据的名称和操作平稳率计算配置ID已经存在");
			}
			if(e.toString().contains("UK_STAALGRCONFITEM_OPEINDEX")) {
				throw new BusinessException("", "", "新增数据的操作平稳率计算配置ID, 操作指标和工艺方案已经存在");
			}
			if(e.toString().contains("FK_T_OPM_STAALGR_R_STAALGRCONF")) {
				throw new BusinessException("", "", "不存在此操作平稳率计算配置！");
			}
			if(e.toString().contains("FK_T_OPM_STAALGR_R_T_OPM_CRAFT")) {
				throw new BusinessException("", "", "不存在此工艺类型！");
			}
			if(e.toString().contains("FK_T_OPM_STAALGR_R_T_OPM_OPEIN")) {
				throw new BusinessException("", "", "不存在此操作指标！");
			}
		} catch (Exception e) {
			throw new BusinessException("", e.getMessage());
			
		}
		return entityList;
	}

	/**
	 * Update staalgr confitem.
	 *
	 * @param modelList the model list
	 * @throws BusinessException the business exception
	 */
	@Override
	@Transactional(rollbackFor=BusinessException.class)
	public void updateStaalgrConfitem(List<com.pcitc.fms.service.model.StaalgrConfitem> modelList) throws BusinessException {
		// 将viewmodel转换成entity 	
		List<StaalgrConfitem> entityList = null;
		try {
			entityList = ObjectConverter.listConverter(modelList, StaalgrConfitem.class);
		} catch (Exception e) {
			log.error("fail",e);
			throw  new BusinessException("","",e.getMessage());
		}
		try {
//			校验传入的实体信息，是否是一个，是否存在,并返回实体
			com.pcitc.fms.dal.pojo.StaalgrConfitem pojo = checkForEntity(entityList);
			//装配区域分别表并更新
			newSubArea(pojo,entityList);
			staalgrConfitemDao.saveAndFlush(pojo);
		} catch (Exception e) {
				throw new BusinessException("", e.toString());
		}

	}

	/**
	 * 查询传入实体是否唯一，是够存在
	 *
	 * @param entityList the entity list
	 * @return com . pcitc . fms . dal . pojo . staalgr confitem
	 * @throws BusinessException the business exception
	 */
	private com.pcitc.fms.dal.pojo.StaalgrConfitem checkForEntity(List<com.pcitc.fms.bll.entity.StaalgrConfitem> entityList) throws BusinessException {
			//校验是否唯一个实体，这个修改是批量修改所以不去限定只为一个
			if(entityList.size() != 1) {
				throw new BusinessException("", "", "更新操作只能传入一个实体！");
			}
			//校验是否存在等于code的entity实体
			Integer opeindexId = entityList.get(0).getOpeindexId();
			Integer staalgrConfId = entityList.get(0).getStaalgrConfId();
			com.pcitc.fms.dal.pojo.StaalgrConfitem pojo = staalgrConfitemDao.getByOpeindexIdAndStaalgrConfId(opeindexId, staalgrConfId);
			if(pojo == null) {
				throw new BusinessException("", "", "不存在opeindexId："+opeindexId+"staalgrConfId:"+staalgrConfId+"的数据！");
			}
			return pojo;
		
	}

	/**
	 * 通过查询到的分表来装配新的分表
	 *
	 * @param pojo the pojo
	 * @param entityList the entity list
	 */
	private void newSubArea(com.pcitc.fms.dal.pojo.StaalgrConfitem pojo,
				List<com.pcitc.fms.bll.entity.StaalgrConfitem> entityList) {
			// 找到要改的字段,共有字段和特有字段
			for(int i = 0;i<entityList.size();i++) {
				com.pcitc.fms.bll.entity.StaalgrConfitem entity = entityList.get(i);
					pojo.setName(entity.getName());
					pojo.setWeightings(entity.getWeightings());
					pojo.setInUse(entity.getInUse());
					pojo.setDes(entity.getDes());
					pojo.setEditor(entity.getEditor());
					pojo.setEditorId(entity.getEditorId());
					pojo.setMaintainTime(new Date());
					pojo.setSortNum(entity.getSortNum());
				
			}
			
		}

	/**
	 * Delete by opeindex id and staalgr conf id.
	 *
	 * @param opeindexId the opeindex id
	 * @param staalgrConfId the staalgr conf id
	 * @throws BusinessException the business exception
	 */
	@Override
	public void deleteByOpeindexIdAndStaalgrConfId(Integer opeindexId, Integer staalgrConfId) throws BusinessException {
		com.pcitc.fms.dal.pojo.StaalgrConfitem pojo = staalgrConfitemDao.getByOpeindexIdAndStaalgrConfId(opeindexId,staalgrConfId);
		if(pojo == null) {
			throw new BusinessException("", "", "不存在opeindexId："+opeindexId+"staalgrConfId:"+staalgrConfId+"的数据！");
		}
		staalgrConfitemDao.deleteByOpeindexIdAndStaalgrConfId(opeindexId,staalgrConfId);
	}

	/**
	 * Delete by staalgr conf id.
	 *
	 * @param staalgrConfId the staalgr conf id
	 * @throws BusinessException the business exception
	 */
	@Override
	public void deleteByStaalgrConfId(Integer staalgrConfId) throws BusinessException {
		List<com.pcitc.fms.dal.pojo.StaalgrConfitem> pojos = staalgrConfitemDao.getByStaalgrConfId(staalgrConfId);
		if(pojos.size()==0) {
			throw new BusinessException("", "", "不存在staalgrConfId:"+staalgrConfId+"的数据！");
		}
		staalgrConfitemDao.deleteByStaalgrConfId(staalgrConfId);
	}

	/**
	 * Delete by agent code.
	 *
	 * @param agentCode the agent code
	 */
	@Override
	public void deleteByAgentCode(String agentCode) {
		// TODO Auto-generated method stub
		staalgrConfitemDao.deleteByAgentCode(agentCode);
	}

}
