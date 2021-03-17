/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: DictionaryTableServiceImpl
 * Date:18-3-8 下午6:21
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.bll.itf.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.bll.itf.DictionaryTableService;
import com.pcitc.fms.common.CheckError;
import com.pcitc.fms.common.CheckUtil;
import com.pcitc.fms.dal.dao.DbPrimaryIdDao;
import com.pcitc.fms.dal.dao.DictionaryTableDao;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.service.model.DictionaryTable;
import com.pcitc.fms.service.model.Pager;

import pcitc.imp.common.ettool.utils.ObjectConverter;

/**
 * The type Dictionary table service.
 */
@Service
public class DictionaryTableServiceImpl implements DictionaryTableService {

	/**
	 * The constant log.
	 */
	private static Logger log = LoggerFactory.getLogger(DictionaryTableServiceImpl.class);
	/**
	 * The Dictionary table dao.
	 */
	@Autowired
	private DictionaryTableDao dictionaryTableDao;
	
	@Autowired
	private DbPrimaryIdDao dbPrimaryIdDao;

	/**
	 * Gets dictionary tables.
	 *
	 * @param findDictionaryTableModel the find dictionary table model
	 * @param pageable the pageable
	 * @return the dictionary tables
	 * @throws BusinessException the business exception
	 */
	@Override
	public Pager<com.pcitc.fms.bll.entity.DictionaryTable> getDictionaryTables(
			com.pcitc.fms.service.model.DictionaryTable findDictionaryTableModel,Pageable pageable) throws BusinessException {
		
		
			List<com.pcitc.fms.bll.entity.DictionaryTable> dictionaryTableEntityList = new ArrayList<>();
			Pager<com.pcitc.fms.bll.entity.DictionaryTable>  pageData = new Pager<>();
			Page<com.pcitc.fms.dal.pojo.DictionaryTable> properties = dictionaryTableDao.findDictionaryTables(findDictionaryTableModel,pageable);
		try {
			dictionaryTableEntityList = ObjectConverter.listConverter(properties.getContent(),com.pcitc.fms.bll.entity.DictionaryTable.class);
		} catch (Exception e) {
			log.error("fail",e);
			throw  new BusinessException("","",e.getMessage());
		}
		pageData.setContent(dictionaryTableEntityList);
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
	 * Add dictionary table list.
	 *
	 * @param dictionaryTableEntityList the dictionary table entity list
	 * @return the list
	 * @throws BusinessException the business exception
	 */
	@Override
	@Transactional(rollbackFor=BusinessException.class)
	public List addDictionaryTable(List<com.pcitc.fms.bll.entity.DictionaryTable> dictionaryTableEntityList)
			throws BusinessException {
		List<com.pcitc.fms.dal.pojo.DictionaryTable> dictionaryTablePojos = new ArrayList<>();
		try {
			dictionaryTablePojos = ObjectConverter.listConverter(dictionaryTableEntityList, com.pcitc.fms.dal.pojo.DictionaryTable.class);
		} catch (Exception e) {
			log.error("fail",e);
			throw  new BusinessException("","",e.getMessage());
		}
		com.pcitc.fms.dal.pojo.DictionaryTable olddic = dictionaryTablePojos.get(0);
		DictionaryTable findDictionaryTableModel = new DictionaryTable();
		findDictionaryTableModel.setEntityId(olddic.getEntityId());
		findDictionaryTableModel.setFieldName(olddic.getFieldName());
		findDictionaryTableModel.setFieldValue(olddic.getFieldValue());
		 Page<com.pcitc.fms.dal.pojo.DictionaryTable> finl = dictionaryTableDao.findDictionaryTables(findDictionaryTableModel,null);
//		 com.pcitc.fms.dal.pojo.DictionaryTable dicass = finl.getContent().get(0);
		 if(finl.getContent().size()>0){
			 throw new BusinessException("", "dictionary"+"重复的ID"+":"+olddic.getEntityId()+"字段名："+olddic.getFieldName()+"字段值："+olddic.getFieldValue());
		 }
//		 List<BigDecimal> bigDecimals = dbPrimaryIdDao.getSeqId(dictionaryTableEntityList.size(), "");
		 List<BigDecimal> bigDecimals=null;
		 for (int i =0; i < bigDecimals.size(); i++ ) {
			 dictionaryTablePojos.get(i).setDictionaryTableId(bigDecimals.get(i).intValue());
			 dictionaryTablePojos.get(i).setCreateTime(new Date());
			 dictionaryTablePojos.get(i).setMaintainTime(new Date());
		 }
		 List<com.pcitc.fms.dal.pojo.DictionaryTable> save = dictionaryTableDao.save(dictionaryTablePojos);
		 dictionaryTableDao.flush();

		return save;
	}

	/**
	 * Gets dictionary table.
	 *
	 * @param dictionaryTableId the dictionary table id
	 * @return the dictionary table
	 * @throws BusinessException the business exception
	 */
	@Override
	public List<com.pcitc.fms.bll.entity.DictionaryTable> getDictionaryTable(String dictionaryTableId)
			throws BusinessException {
		List<com.pcitc.fms.bll.entity.DictionaryTable> dictionaryTableEntityList = new ArrayList<>();
		
				Integer dictionaryid = CheckUtil.strToInt("dictionaryTableId", dictionaryTableId);
				try {
					
			List<com.pcitc.fms.dal.pojo.DictionaryTable> dictionaryTableList = dictionaryTableDao
					.getDictionaryTableById(dictionaryid);
			dictionaryTableEntityList = ObjectConverter.listConverter(dictionaryTableList,
					com.pcitc.fms.bll.entity.DictionaryTable.class);
		} catch (Exception e) {
			log.error(e.getMessage());
					throw  new BusinessException("","",e.getMessage());
		}
		return dictionaryTableEntityList;
	}

	/**
	 * Update dictionary table.
	 *
	 * @param dictionaryTableEntityList the dictionary table entity list
	 * @throws BusinessException the business exception
	 */
	@Override
	public void updateDictionaryTable(List<com.pcitc.fms.bll.entity.DictionaryTable> dictionaryTableEntityList)
			throws BusinessException {
		// try {

		List<com.pcitc.fms.dal.pojo.DictionaryTable> dictionaryTablePojoList = null;
		try {
			dictionaryTablePojoList = ObjectConverter
          .listConverter(dictionaryTableEntityList, com.pcitc.fms.dal.pojo.DictionaryTable.class);
		} catch (Exception e) {
			log.error("fail",e);
			throw  new BusinessException("","",e.getMessage());
		}
		com.pcitc.fms.dal.pojo.DictionaryTable dictionaryTablePojo = dictionaryTablePojoList.get(0);
		if(dictionaryTablePojo.getEntityId()==null&&dictionaryTablePojo.getEntityId().equals("")){
			throw new BusinessException("", "EntityId:"+"不能为null切不能为空");
		}
		dictionaryTablePojo.setMaintainTime(new Date());;
		com.pcitc.fms.dal.pojo.DictionaryTable oldDictionaryTablePojo = dictionaryTableDao
				.findOne(dictionaryTablePojo.getDictionaryTableId());
		if(oldDictionaryTablePojo==null){
			throw new BusinessException("", "dictionary"+CheckError.NO_EXIST_ENTITY_CODE+":"+dictionaryTablePojo.getDictionaryTableId());
		}
		if (null == dictionaryTablePojo.getCreateTime()) {
			dictionaryTablePojo.setCreateTime(oldDictionaryTablePojo.getCreateTime());
		}

		if (null == dictionaryTablePojo.getMaintainTime()) {
			dictionaryTablePojo.setMaintainTime(new Date());
		}
		if (null == dictionaryTablePojo.getCreator()) {
			dictionaryTablePojo.setCreator(oldDictionaryTablePojo.getCreator());
		}
		 com.pcitc.fms.dal.pojo.DictionaryTable olddictionayPojo = dictionaryTableDao.findOne(dictionaryTablePojo.getDictionaryTableId());
		 
			DictionaryTable findDictionaryTableModel = new DictionaryTable();
			findDictionaryTableModel.setEntityId(dictionaryTablePojo.getEntityId());
			findDictionaryTableModel.setFieldName(dictionaryTablePojo.getFieldName());
			findDictionaryTableModel.setFieldValue(dictionaryTablePojo.getFieldValue());
			 Page<com.pcitc.fms.dal.pojo.DictionaryTable> finl = dictionaryTableDao.findDictionaryTables(findDictionaryTableModel,null);
			// com.pcitc.fms.dal.pojo.DictionaryTable dicass = finl.get(0);
			 if(finl.getSize()>0){
				 throw new BusinessException("", "dictionary"+"重复的"+":"+dictionaryTablePojo.getEntityId()+dictionaryTablePojo.getFieldName()+dictionaryTablePojo.getFieldValue());
			 }
		 dictionaryTableDao.saveAndFlush(dictionaryTablePojo);
		 
	
	}

	/**
	 * Delete dictionary table.
	 *
	 * @param dictionaryTableId the dictionary table id
	 * @throws BusinessException the business exception
	 */
	@Override
	public void deleteDictionaryTable(String dictionaryTableId) throws BusinessException {
		boolean property = CheckUtil.isNumeric(dictionaryTableId);
		if(property==false){
			throw new BusinessException("", "property"+"格式不正确"+":"+dictionaryTableId);
	
		}
		if("".equals(dictionaryTableId)){
			throw new BusinessException("", "property"+"不能为空"+":"+dictionaryTableId);
		}
		 com.pcitc.fms.dal.pojo.DictionaryTable olddictionayPojo = dictionaryTableDao.findOne(Integer.valueOf(dictionaryTableId));
		if(olddictionayPojo==null){
			throw new BusinessException("", "property"+CheckError.NO_EXIST_ENTITY_CODE+":"+dictionaryTableId);
		}
		dictionaryTableDao.deleteByDictionaryTableId(Integer.valueOf(dictionaryTableId));
	}

}
