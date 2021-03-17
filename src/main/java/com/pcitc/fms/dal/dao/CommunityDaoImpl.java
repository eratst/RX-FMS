/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: CommunityDaoImpl
 * Date:18-3-9 上午8:33
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.dal.dao;

import com.pcitc.fms.exception.BusinessException;
import com.pcitc.imp.common.exception.BusiException;

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

import com.pcitc.fms.common.CheckUtil;
import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.common.RentCondition;
import com.pcitc.fms.common.SortParam;
import com.pcitc.fms.config.AreaNodeBasicSql;
import com.pcitc.fms.dal.pojo.CommunityArea;
import com.pcitc.fms.dal.pojo.Tank;

/**
 * The type Community dao.
 */
@Service
public class CommunityDaoImpl {

	/**
	 * The Em.
	 */
	@PersistenceContext
    private EntityManager em;

	/**
	 * Find pages page.
	 *
	 * @param model the model
	 * @param pageable the pageable
	 * @return the page
	 * @throws BusinessException the business exception
	 * @throws BusiException 
	 */
//hanxiao
	@SuppressWarnings("unchecked")
	public MyPageImpl findPages(com.pcitc.fms.service.model.Community model, Pageable pageable) throws BusinessException, BusiException {
		Map<String ,Object> parameterMap = new HashedMap();
		String dataSql = AreaNodeBasicSql.community;
		if(model.getInUse()!=null){
			dataSql+=" and ad.enabled="+"'"+model.getInUse()+"'";
		}
		if(model.getOrgCode()!=null && model.getOrgs()== null){
			dataSql+=" and org.orgCode="+"'"+model.getOrgCode()+"'";
		}
		if(model.getCommunityName()!=null){
			dataSql+=" and ad.name like"+"'%"+model.getCommunityName()+"%'";
		}
		if(model.getCommunityCode()!=null){
			dataSql+=" and a.communityCode="+"'"+model.getCommunityCode()+"'";
		}
		if(model.getCommunityName()!=null){
			dataSql+=" and ad.name like"+"'%"+model.getCommunityName()+"%'";
		}
		if(model.getCommunityAlias()!=null){
			dataSql+=" and ad.shortName like"+"'%"+model.getCommunityAlias()+"%'";
		}
		if(null != model.getAreaCodes() && model.getAreaCodes().size() > 0){
			dataSql += " and a.communityCode in "+CheckUtil.getList(model.getAreaCodes());
        }
		if(null != model.getOrgs() && model.getOrgs().size() > 0){
			dataSql += " and org.orgCode in "+CheckUtil.getList(model.getOrgs());
        }
		
		//----------处理租户过滤
		if(StringUtils.isNotEmpty(model.getRentCode())){
			RentCondition<CommunityArea> rentCondition = new RentCondition<CommunityArea>();
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
			String value = SortParam.getSortParam(CommunityArea.class, model.getOrderby());
			dataSql += value;
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
		List<com.pcitc.fms.dal.pojo.CommunityArea> list = dataQuery.getResultList();
		MyPageImpl myPageImpl=new MyPageImpl(list, pageable, count);
		myPageImpl.setCount(count);
		return myPageImpl;
	}
}
