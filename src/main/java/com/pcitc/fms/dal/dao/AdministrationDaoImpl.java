/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: AdministrationDaoImpl
 * Date:18-3-9 上午8:33
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.dal.dao;

import com.pcitc.fms.exception.BusinessException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.common.CheckUtil;
import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.common.RentCondition;
import com.pcitc.fms.common.SortParam;
import com.pcitc.fms.config.AreaNodeBasicSql;
import com.pcitc.fms.dal.pojo.AdministrationArea;
import com.pcitc.fms.dal.pojo.Tank;
import com.pcitc.fms.service.model.Administration;
import com.pcitc.imp.common.exception.BusiException;

import pcitc.imp.common.ettool.utils.ObjectConverter;

/**
 * The type Administration dao.
 */
@Service
public class AdministrationDaoImpl {

	/**
	 * The Em.
	 */
	@PersistenceContext
    private EntityManager em;

	/**
	 * Find page admins page.
	 *
	 * @param model the model
	 * @param pageable the pageable
	 * @return the page
	 * @throws BusinessException the business exception
	 * @throws BusiException 
	 */
//
	@Transactional
	@SuppressWarnings("unchecked")
	public MyPageImpl findPageAdmins(Administration model, Pageable pageable) throws BusiException {
		Map<String ,Object> parameterMap = new HashedMap();
		String dataSql = AreaNodeBasicSql.administration;
		if(model.getInUse()!=null){
			dataSql+=" and ad.enabled="+"'"+model.getInUse()+"'";
		}
		if(model.getOrgCode()!=null && model.getOrgs()== null){
			dataSql+=" and org.orgCode="+"'"+model.getOrgCode()+"'";
		}
		if(model.getAdministrationName()!=null){
			dataSql+=" and ad.name like"+"'%"+model.getAdministrationName()+"%'";
		}
		if(model.getAdministrationCode()!=null){
			dataSql+=" and a.administrationCode = "+"'"+model.getAdministrationCode()+"'";
		}
		if(model.getAdministrationName()!=null){
			dataSql+=" and ad.name like "+"'%"+model.getAdministrationName()+"%'";
		}
		if(model.getAdministrationAlias()!=null){
			dataSql+=" and ad.shortName like"+"'%"+model.getAdministrationAlias()+"%'";
		}
		if(null != model.getAreaCodes() && model.getAreaCodes().size() > 0){
			dataSql += " and a.administrationCode in "+CheckUtil.getList(model.getAreaCodes());
        }
		if(null != model.getOrgs() && model.getOrgs().size() > 0){
			dataSql += " and org.orgCode in "+CheckUtil.getList(model.getOrgs());
        }
		//----------处理租户过滤
		if(StringUtils.isNotEmpty(model.getRentCode())){
			RentCondition<AdministrationArea> rentCondition = new RentCondition<AdministrationArea>();
			String field=" org.orgCode ";
			String rentOrgCodes = rentCondition.getRentOrgCodeCondition(model.getRentCode(),model.getBizCode(),field);
			if(StringUtils.isNotEmpty(rentOrgCodes)){
				dataSql +=" and "+rentOrgCodes;
			}else{
				return new MyPageImpl(new ArrayList(), null, 0L);
			}
		}
		//----------处理租户过滤
		
		if (StringUtils.isNotEmpty(model.getOrderby())) {
			String value = SortParam.getSortParam(AdministrationArea.class, model.getOrderby());
			dataSql += value;
		}
		Query dataQuery = em.createQuery(dataSql);
		
		for (String parameter : parameterMap.keySet()) {
			dataQuery.setParameter(parameter, parameterMap.get(parameter));
		}
		
		long count = dataQuery.getResultList().size();
		if(null != pageable){
			Integer skip = model.getSkip();
            dataQuery = em.createQuery(dataSql);
            dataQuery.setFirstResult(skip);
            dataQuery.setMaxResults(pageable.getPageSize());
		}
		List<com.pcitc.fms.dal.pojo.AdministrationArea> list = dataQuery.getResultList();
		MyPageImpl myPageImpl=new MyPageImpl(list, pageable, count);
		myPageImpl.setCount(count);
		return myPageImpl;
	}


}
