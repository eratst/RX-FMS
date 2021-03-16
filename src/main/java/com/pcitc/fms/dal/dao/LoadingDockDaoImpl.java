/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: LoadingDockDaoImpl
 * Date:18-3-9 上午8:34
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.dal.dao;

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

import com.pcitc.fms.common.CheckUtil;
import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.common.RentCondition;
import com.pcitc.fms.common.SortParam;
import com.pcitc.fms.config.AreaNodeBasicSql;
import com.pcitc.fms.dal.pojo.LoadingDockArea;
import com.pcitc.fms.dal.pojo.Tank;
import com.pcitc.fms.dal.pojo.WarehouseArea;
import com.pcitc.imp.common.exception.BusiException;


/**
 * The type Loading dock dao.
 */
public class LoadingDockDaoImpl {

	/**
	 * The Em.
	 */
	@PersistenceContext
    private EntityManager em;

	/**
	 * Get loading docks by model page.
	 *
	 * @param model the model
	 * @param pageable the pageable
	 * @return the page
	 * @throws BusiException 
	 */
	@SuppressWarnings("unchecked")
	public MyPageImpl getLoadingDocksByModel (com.pcitc.fms.service.model.LoadingDock model,Pageable pageable) throws BusiException{
		Map<String ,Object> parameterMap = new HashedMap();
		String dataSql = AreaNodeBasicSql.loadingDock;
		if(model.getInUse()!=null){
			dataSql+=" and ad.enabled="+"'"+model.getInUse()+"'";
		}
		if(model.getLoadingDockName()!=null){
			dataSql+=" and ad.name like"+"'%"+model.getLoadingDockName()+"%'";
		}
		if(model.getLoadingDockAlias()!=null){
			dataSql+=" and ad.shortName like"+"'%"+model.getLoadingDockAlias()+"%'";
		}
		if(null != model.getAreaCodes() && model.getAreaCodes().size() > 0){
			dataSql += " and a.loadingDockCode in "+CheckUtil.getList(model.getAreaCodes());
        }
		if(null != model.getOrgs() && model.getOrgs().size() > 0){
			dataSql += " and org.orgCode in "+CheckUtil.getList(model.getOrgs());
        }
		if(null != model.getOrgCode() && model.getOrgs()== null){
			dataSql+=" and org.orgCode="+"'"+model.getOrgCode()+"'";
        }
		if(null != model.getTechnicCode()){
			dataSql+=" and ta.technicCode="+"'"+model.getTechnicCode()+"'";
        }
		if(null != model.getLoadingDockCode()){
			dataSql+=" and a.loadingDockCode="+"'"+model.getLoadingDockCode()+"'";
        }
		
		//----------处理租户过滤
			if(StringUtils.isNotEmpty(model.getRentCode())){
				RentCondition<LoadingDockArea> rentCondition = new RentCondition<LoadingDockArea>();
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
			String value = SortParam.getSortParam(LoadingDockArea.class, model.getOrderby());
			dataSql+=value;
		}
		Query dataQuery = em.createQuery(dataSql);
		for (String parameter : parameterMap.keySet()) {
			dataQuery.setParameter(parameter, parameterMap.get(parameter));
		}
		Integer skip = null;
		List<com.pcitc.fms.dal.pojo.LoadingDockArea> list = dataQuery.getResultList();
		long count = list.size();
		if(null != pageable){
		    skip = model.getSkip();
            dataQuery = em.createQuery(dataSql);
            dataQuery.setFirstResult(skip);
            dataQuery.setMaxResults(pageable.getPageSize());
            
            MyPageImpl myPageImpl =new MyPageImpl(dataQuery.getResultList(), pageable,count);
            myPageImpl.setCount(count);
            return myPageImpl;
		} else {
			 MyPageImpl myPageImpl =new MyPageImpl(dataQuery.getResultList(), null,count);
	         myPageImpl.setCount(count);
	         return myPageImpl;
		}

	}

		
}
