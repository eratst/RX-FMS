/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: UnitAlarmDaoImpl
 * Date:18-3-9 上午8:35
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.dal.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.common.SortParam;
import com.pcitc.fms.config.AreaNodeBasicSql;
import com.pcitc.fms.service.model.UnitAlarm;
import com.pcitc.imp.common.exception.BusiException;

/**
 * The type Unit alarm dao.
 */
@Service
public class UnitAlarmDaoImpl {

	/**
	 * The Em.
	 */
	@PersistenceContext
    private EntityManager em;


	/**
	 * Create sql string.
	 *
	 * @param model the model
	 * @param parameterMap the parameter map
	 * @return the string
	 * @throws BusiException 
	 */
	private String createSql(UnitAlarm model, Map<String, Object> parameterMap) throws BusiException {
		String dataSql =  AreaNodeBasicSql.unitAlarm;
		if(model.getUnitCode()!=null){
			dataSql+=" and pl.plantCode="+"'"+model.getUnitCode()+"'"+" and a.unitId = pl.plantId";
		}
		if(model.getInUse()!=null){
			dataSql+=" and a.inUse="+"'"+model.getInUse()+"'";
		}
		if(model.getUnitAlarmName()!=null){
			dataSql+=" and a.unitAlarmName like"+"'%"+model.getUnitAlarmName()+"%'";
		}
		if(model.getUnitAlarmAlias()!=null){
			dataSql+=" and a.unitAlarmShortName like"+"'%"+model.getUnitAlarmAlias()+"%'";
		}
		if(null != model.getCodeList() && model.getCodeList().size() > 0){
			dataSql += " and a.codeList in :codes";
            parameterMap.put("codes", model.getCodeList());
        }
		
		if (StringUtils.isNotEmpty(model.getOrderby())) {
			String value = SortParam.getSortParam(com.pcitc.fms.dal.pojo.UnitAlarm.class, model.getOrderby());
			dataSql += value;
		}
		
		return dataSql;
	}

	/**
	 * Find page unit alarms page.
	 *
	 * @param unitAlarm the unit alarm
	 * @param pageable the pageable
	 * @return the page
	 * @throws BusiException 
	 */
	@Transactional
	@SuppressWarnings("unchecked")
	public MyPageImpl findPageUnitAlarms(UnitAlarm unitAlarm, Pageable pageable) throws BusiException {
		Map<String ,Object> parameterMap = new HashedMap();
		String dataSql = createSql(unitAlarm, parameterMap);
		Query dataQuery = em.createQuery(dataSql);
		for (String parameter : parameterMap.keySet()) {
			dataQuery.setParameter(parameter, parameterMap.get(parameter));
		}
		Integer skip = null;
		List<com.pcitc.fms.dal.pojo.UnitAlarm> list = dataQuery.getResultList();
  		long count = list.size();
		if(null != pageable){
		    skip = unitAlarm.getSkip();
            dataQuery = em.createQuery(dataSql);
            dataQuery.setFirstResult(skip);
            dataQuery.setMaxResults(pageable.getPageSize());
            List<com.pcitc.fms.dal.pojo.UnitAlarm> lists = dataQuery.getResultList();
            
		}
		List<com.pcitc.fms.dal.pojo.UnitAlarm> resultList = dataQuery.getResultList();
		MyPageImpl myPageImpl=new MyPageImpl(resultList, pageable, count);
		myPageImpl.setCount(count);
		return myPageImpl;

	}


}
