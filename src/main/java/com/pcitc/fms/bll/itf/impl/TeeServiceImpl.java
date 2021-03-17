/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: TeeServiceImpl
 * Date:18-3-8 下午6:29
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

import com.pcitc.fms.bll.entity.Tee;
import com.pcitc.fms.bll.itf.TeeService;
import com.pcitc.fms.common.CheckError;
import com.pcitc.fms.common.CheckType;
import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.common.dispatcher.SysGlobal;
import com.pcitc.fms.dal.dao.AreaDictionaryDao;
import com.pcitc.fms.dal.dao.DbPrimaryIdDao;
import com.pcitc.fms.dal.dao.InclusionDao;
import com.pcitc.fms.dal.dao.NodeDictionaryDao;
import com.pcitc.fms.dal.dao.TeeDao;
import com.pcitc.fms.dal.pojo.AreaDictionary;
import com.pcitc.fms.dal.pojo.Inclusion;
import com.pcitc.fms.dal.pojo.NodeDictionary;
import com.pcitc.fms.exception.BusinessExceptionMessage;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.imp.common.exception.BusiException;

import com.pcitc.fms.exception.BusinessException;
import pcitc.imp.common.ettool.utils.ObjectConverter;

/**
 * The type Tee service.
 */
@Service
public class TeeServiceImpl extends NodeCommonServiceImpl implements TeeService{

	/**
	 * The constant log.
	 */
	private static Logger log = LoggerFactory.getLogger(TankServiceImpl.class);
	/**
	 * The Tee dao.
	 */
	@Autowired
    private TeeDao teeDao;

	/**
	 * Gets tee by code.
	 *
	 * @param parentCode the parent code
	 * @param nodeCode the node code
	 * @param parentType the parent type
	 * @return the tee by code
	 * @throws BusinessException the business exception
	 */
	@Override
	public List<com.pcitc.fms.bll.entity.Tee> getTeeByCode(String parentCode, String nodeCode, String parentType) throws BusinessException {
		List<com.pcitc.fms.bll.entity.Tee> teeEntityList = new ArrayList<>();
		try{
			List<com.pcitc.fms.dal.pojo.Tee> teePojoList = teeDao.getTeeByNodeCode(nodeCode);
			teeEntityList = ObjectConverter.listConverter(teePojoList, com.pcitc.fms.bll.entity.Tee.class);
		}catch(Exception e){
			log.error("fail",e);
			throw  new BusinessException("","",e.getMessage());
		}
		return teeEntityList;
	}



	/**
	 * Gets tees by model.
	 *
	 * @param teeModel the tee model
	 * @param pageable the pageable
	 * @param areaCode the area code
	 * @return the tees by model
	 * @throws BusinessException the business exception
	 */
	@Override
	public Pager<com.pcitc.fms.bll.entity.Tee> getTeesByModel(com.pcitc.fms.service.model.Tee teeModel, Pageable pageable) throws BusinessException {
		 List<com.pcitc.fms.bll.entity.Tee> teeEntitList = new ArrayList<>();
        Pager<com.pcitc.fms.bll.entity.Tee> teeEntitPager = new Pager<>();
        try{
        	MyPageImpl teePojoPageList = null;
            try {
            	teePojoPageList = teeDao.getTeesByModel(teeModel,pageable);
    		} catch (Exception e) {
    			if (e instanceof UndeclaredThrowableException) {
    				throw new BusinessException("", e.getCause().getMessage());
    			}
    		}
            teeEntitList = ObjectConverter.listConverter(teePojoPageList.getContent(), com.pcitc.fms.bll.entity.Tee.class);
            teeEntitPager.setContent(teeEntitList);
            teeEntitPager.setFirst(teePojoPageList.isFirst());
            teeEntitPager.setLast(teePojoPageList.isLast());
            teeEntitPager.setNumber(teePojoPageList.getNumber());
            teeEntitPager.setNumberOfElements(teePojoPageList.getNumberOfElements());
            teeEntitPager.setSize(teePojoPageList.getSize());
            teeEntitPager.setSort(teePojoPageList.getSort());
            teeEntitPager.setTotalElements(teePojoPageList.getCount());
            teeEntitPager.setTotalPages(teePojoPageList.getTotalPages());
        }catch(Exception e){
					log.error("fail",e);
					throw  new BusinessException("","",e.getMessage());
        }
        return teeEntitPager;
	}


	

}
