/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: PipeNetworkDaoImpl
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
import com.pcitc.fms.dal.pojo.PipeNetworkArea;
import com.pcitc.fms.dal.pojo.Tank;
import com.pcitc.imp.common.exception.BusiException;


/**
 * The type Pipe network dao.
 */
public class PipeNetworkDaoImpl {

	/**
	 * The Em.
	 */
	@PersistenceContext
    private EntityManager em;

	/**
	 * Get pipe network by group page.
	 *
	 * @param model the model
	 * @param pageable the pageable
	 * @return the page
	 * @throws BusiException 
	 */
	@SuppressWarnings("unchecked")
	public MyPageImpl getPipeNetworks(com.pcitc.fms.service.model.PipeNetwork model,Pageable pageable) throws BusiException{
		Map<String ,Object> parameterMap = new HashedMap();
		String dataSql = AreaNodeBasicSql.pipeNetwork;
		if(model.getInUse()!=null){
			dataSql+=" and ad.enabled="+"'"+model.getInUse()+"'";
		}
		if(model.getPipeNetworkCode()!=null){
			dataSql+=" and  a.pipeNetworkCode="+"'"+model.getPipeNetworkCode()+"'";
		}
		if(model.getPipeNetworkName()!=null){
			dataSql+=" and ad.name like"+"'%"+model.getPipeNetworkName()+"%'";
		}
		if(model.getPipeNetworkAlias()!=null){
			dataSql+=" and ad.shortName like"+"'%"+model.getPipeNetworkAlias()+"%'";
		}
		if(null != model.getAreaCodes() && model.getAreaCodes().size() > 0){
			dataSql += " and a.pipeNetworkCode in "+CheckUtil.getList(model.getAreaCodes());
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
		
		//----------处理租户过滤
		if(StringUtils.isNotEmpty(model.getRentCode())){
			RentCondition<PipeNetworkArea> rentCondition = new RentCondition<PipeNetworkArea>();
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
			String value = SortParam.getSortParam(PipeNetworkArea.class, model.getOrderby());
			dataSql+=value;
		}
		Query dataQuery = em.createQuery(dataSql);
		for (String parameter : parameterMap.keySet()) {
			dataQuery.setParameter(parameter, parameterMap.get(parameter));
		}
		Integer skip = null;
		
		long count = dataQuery.getResultList().size();
		if(null != pageable){
		    skip = model.getSkip();
            dataQuery = em.createQuery(dataSql);
            dataQuery.setFirstResult(skip);
            dataQuery.setMaxResults(pageable.getPageSize());
            
		}
		List<com.pcitc.fms.dal.pojo.PipeNetworkArea> list = dataQuery.getResultList();
		MyPageImpl myPageImpl=new MyPageImpl(list, pageable, count);
		myPageImpl.setCount(count);
		return myPageImpl;

	}

}
