/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: TubulationServiceImpl
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.bll.entity.Tubulation;
import com.pcitc.fms.bll.itf.TubulationService;
import com.pcitc.fms.common.CheckError;
import com.pcitc.fms.common.CheckType;
import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.common.dispatcher.SysGlobal;
import com.pcitc.fms.dal.dao.AreaDictionaryDao;
import com.pcitc.fms.dal.dao.DbPrimaryIdDao;
import com.pcitc.fms.dal.dao.InclusionDao;
import com.pcitc.fms.dal.dao.NodeDictionaryDao;
import com.pcitc.fms.dal.dao.TubulationDao;
import com.pcitc.fms.dal.pojo.AreaDictionary;
import com.pcitc.fms.dal.pojo.Inclusion;
import com.pcitc.fms.dal.pojo.NodeDictionary;
import com.pcitc.fms.exception.BusinessExceptionMessage;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.imp.common.exception.BusiException;

import com.pcitc.fms.exception.BusinessException;
import pcitc.imp.common.ettool.utils.ObjectConverter;

/**
 * The type Tubulation service.
 */
@Service
public class TubulationServiceImpl implements TubulationService {

	@Autowired
	private TubulationDao tubulationDao;

	@Override
	@Transactional
	public Pager<com.pcitc.fms.bll.entity.Tubulation> getPageTubulations(com.pcitc.fms.service.model.Tubulation tubulationModel,Pageable pageable) throws BusinessException {
		List<com.pcitc.fms.bll.entity.Tubulation> tubulationEntitList = new ArrayList<>();
        Pager<com.pcitc.fms.bll.entity.Tubulation> tubulationEntitPager = new Pager<>();
        try{
        	MyPageImpl tubulationPojoPageList= null;
        	try {
    			tubulationPojoPageList = tubulationDao.getTeesByModel(tubulationModel,pageable);
    		} catch (Exception e) {
    			if (e instanceof UndeclaredThrowableException) {
    				throw new BusinessException("", e.getCause().getMessage());
    			}
    		}
            tubulationEntitList = ObjectConverter.listConverter(tubulationPojoPageList.getContent(), com.pcitc.fms.bll.entity.Tubulation.class);
            tubulationEntitPager.setContent(tubulationEntitList);
            tubulationEntitPager.setFirst(tubulationPojoPageList.isFirst());
            tubulationEntitPager.setLast(tubulationPojoPageList.isLast());
            tubulationEntitPager.setNumber(tubulationPojoPageList.getNumber());
            tubulationEntitPager.setNumberOfElements(tubulationPojoPageList.getNumberOfElements());
            tubulationEntitPager.setSize(tubulationPojoPageList.getSize());
            tubulationEntitPager.setSort(tubulationPojoPageList.getSort());
            tubulationEntitPager.setTotalElements(tubulationPojoPageList.getCount());
            tubulationEntitPager.setTotalPages(tubulationPojoPageList.getTotalPages());
        }catch(Exception e){
					throw  new BusinessException("","",e.getMessage());
        }
        return tubulationEntitPager;
	}

}
