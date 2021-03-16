/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: TIcNodetopMainServiceImpl
 * Date:18-3-8 下午6:29
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
import org.springframework.stereotype.Service;

import com.pcitc.fms.bll.entity.TIcNodetopMain;
import com.pcitc.fms.bll.itf.TIcNodetopMainService;
import com.pcitc.fms.dal.dao.TIcNodetopMainDao;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.exception.BusinessExceptionMessage;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.imp.common.exception.BusiException;

import pcitc.imp.common.ettool.utils.ObjectConverter;

/**
 * The type T ic nodetop main service.
 */
@Service
public class TIcNodetopMainServiceImpl implements TIcNodetopMainService {
	
	private static Logger log = LoggerFactory.getLogger(TIcNodetopMainServiceImpl.class);
	@Autowired
	private TIcNodetopMainDao ticNodetopMainDao;


	/**
	 * Gets nodetop mains.
	 *
	 * @param nodetopMainsTableModel the nodetop mains table model
	 * @param pageable the pageable
	 * @return the nodetop mains
	 * @throws Exception the exception
	 */
	@Override
	public Pager<TIcNodetopMain> getNodetopMains(com.pcitc.fms.service.model.TIcNodetopMain nodetopMainsTableModel,
			Pageable pageable) throws Exception {
		Pager<TIcNodetopMain>  pageData = new Pager<>();
	       Page<com.pcitc.fms.dal.pojo.TIcNodetopMain> properties = ticNodetopMainDao.findNodetopMains(nodetopMainsTableModel,pageable);
	       List<TIcNodetopMain> EntityList = ObjectConverter.listConverter(properties.getContent(),TIcNodetopMain.class);
	       pageData.setContent(EntityList);
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
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List addNodetopMains(List<TIcNodetopMain> ticNodetopMainList) throws Exception {
		List<com.pcitc.fms.dal.pojo.TIcNodetopMain> ticNodetopMainPojoList = new ArrayList<>();
		try{
			ticNodetopMainPojoList = ObjectConverter.listConverter(ticNodetopMainList, com.pcitc.fms.dal.pojo.TIcNodetopMain.class);
			ticNodetopMainDao.save(ticNodetopMainPojoList);
		} catch (DataIntegrityViolationException e) {
			log.error(e.getMessage());
			throw new BusinessException("", "", BusinessExceptionMessage.getUniqueKeyMessage(e));
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new BusiException("", e.getMessage());
		}
		return ticNodetopMainPojoList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void updateNodetopMain(List<TIcNodetopMain> tIcNodetopMainEntityList, String topCode) throws Exception {
		List<com.pcitc.fms.dal.pojo.TIcNodetopMain> ticNodetopMainPojoList = new ArrayList<>();
		try{
			com.pcitc.fms.dal.pojo.TIcNodetopMain ticNodetopMainPojo = ticNodetopMainDao.findByTopCode(topCode);
			if(null == ticNodetopMainPojo){
				throw new BusiException("编码", topCode+"的拓扑关系编码不存在");
			}
			ticNodetopMainPojoList = ObjectConverter.listConverter(tIcNodetopMainEntityList, com.pcitc.fms.dal.pojo.TIcNodetopMain.class);
			
			update(ticNodetopMainPojoList.get(0),ticNodetopMainPojo);
			
			ticNodetopMainDao.save(ticNodetopMainPojo);
		} catch (DataIntegrityViolationException e) {
			log.error(e.getMessage());
			throw new BusinessException("", "", BusinessExceptionMessage.getUniqueKeyMessage(e));
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new BusiException("", e.getMessage());
		}
	}

	@Override
	public void deleteNodetopMainByCode(String topCode) throws Exception {
		try{
			com.pcitc.fms.dal.pojo.TIcNodetopMain ticNodetopMainPojo = ticNodetopMainDao.findByTopCode(topCode);
			if(null == ticNodetopMainPojo){
				throw new BusiException("编码", topCode+"的拓扑关系编码不存在");
			}
			
			ticNodetopMainDao.delete(ticNodetopMainPojo.getTopId());
		} catch (DataIntegrityViolationException e) {
			log.error(e.getMessage());
			throw new BusinessException("", "", BusinessExceptionMessage.getUniqueKeyMessage(e));
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new BusiException("", e.getMessage());
		}
	}

	
	@Override
	public List<TIcNodetopMain> getNodetopMain(String topCode) throws Exception {
		// TODO Auto-generated method stub
		List<TIcNodetopMain> ticNodetopMainList = new ArrayList<>();
		
		com.pcitc.fms.dal.pojo.TIcNodetopMain ticNodetopMainPojo = ticNodetopMainDao.findByTopCode(topCode);
		if(null != ticNodetopMainPojo)
			ticNodetopMainList.add(ObjectConverter.entityConverter(ticNodetopMainPojo, TIcNodetopMain.class));
		return ticNodetopMainList;
	}

	
	
	
	
	private void update(com.pcitc.fms.dal.pojo.TIcNodetopMain tIcNodetopMain,
			com.pcitc.fms.dal.pojo.TIcNodetopMain ticNodetopMainPojo) {
		Integer dataStatus = tIcNodetopMain.getDataStatus();
		Date mntDate = tIcNodetopMain.getMntDate();
		Integer mntUserId = tIcNodetopMain.getMntUserId();
		String mntUserName = tIcNodetopMain.getMntUserName();
		String topAlias = tIcNodetopMain.getTopAlias();
		String topCode = tIcNodetopMain.getTopCode();
		String topName = tIcNodetopMain.getTopName();
		
		if(null != dataStatus){
			ticNodetopMainPojo.setDataStatus(dataStatus);
		}
		if(null != mntDate){
			ticNodetopMainPojo.setMntDate(mntDate);
		}
		if(null != mntUserId){
			ticNodetopMainPojo.setMntUserId(mntUserId);
		}
		if(null != mntUserName){
			ticNodetopMainPojo.setMntUserName(mntUserName);
		}
		if(null != topAlias){
			ticNodetopMainPojo.setTopAlias(topAlias);
		}
		if(null != topCode){
			ticNodetopMainPojo.setTopCode(topCode);
		}
		if(null != topName){
			ticNodetopMainPojo.setTopName(topName);
		}
		
	}
	
}
