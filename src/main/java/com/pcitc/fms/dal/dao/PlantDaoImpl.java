/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: PlantDaoImpl
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
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pcitc.fms.common.CheckUtil;
import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.common.RentCondition;
import com.pcitc.fms.common.SortParam;
import com.pcitc.fms.common.Test;
import com.pcitc.fms.config.AreaNodeBasicSql;
import com.pcitc.fms.dal.pojo.Plant;
import com.pcitc.fms.dal.pojo.PlantArea;
import com.pcitc.imp.common.exception.BusiException;


/**
 * The type Plant dao.
 *
 * @version 创建时间 ：2017年6月8日 下午4:30:08  类说明 装置的特殊查询
 * @author:
 */
@Service
public class PlantDaoImpl {

	/**
	 * The Em.
	 */
	@PersistenceContext
    private EntityManager em;

	/**
	 * Find page plants page.
	 *
	 * @param model the model
	 * @param pageable the pageable
	 * @return the page
	 * @throws BusiException 
	 */
	@SuppressWarnings("unchecked")
	public Test findPagePlants(com.pcitc.fms.service.model.Plant model ,Pageable pageable) throws BusiException{
		Map<String ,Object> parameterMap = new HashedMap();
		String dataSql = AreaNodeBasicSql.plant;
		if(model.getInUse()!=null){
			dataSql+=" and ad.enabled="+"'"+model.getInUse()+"'";
		}
		if(model.getPlantTypeCode()!=null){
			dataSql+=" and u.unitTypeCode="+"'"+model.getPlantTypeCode()+"'";
		}
		if(model.getTechnicCode()!=null){
			dataSql+=" and t.technicCode="+"'"+model.getTechnicCode()+"'";
		}
		if(model.getPlantCode()!=null){
			dataSql+=" and a.plantCode="+"'"+model.getPlantCode()+"'";
		}
		if(model.getPlantName()!=null){
			dataSql+=" and ad.name like"+"'%"+model.getPlantName()+"%'";
		}
		if(model.getPlantAlias()!=null){
			dataSql+=" and ad.shortName like"+"'%"+model.getPlantAlias()+"%'";
		}
		if(null != model.getOrgs() && model.getOrgs().size() > 0){
			dataSql += " and org.orgCode in "+CheckUtil.getList(model.getOrgs());
        }
		if(null != model.getOrgCode() && model.getOrgs()== null){
			dataSql+=" and org.orgCode="+"'"+model.getOrgCode()+"'";
        }
		if(null != model.getAreaCodes() && model.getAreaCodes().size() > 0){
			dataSql += " and a.plantCode in "+CheckUtil.getList(model.getAreaCodes());
        }
		//----------处理租户过滤
		String rentOrgCodes = "";
		if(StringUtils.isNotEmpty(model.getRentCode())){
			RentCondition<Plant> rentCondition = new RentCondition<Plant>();
			String field=" org.orgCode ";
			rentOrgCodes = rentCondition.getRentOrgCodeCondition(model.getRentCode(),model.getBizCode(),field);
			if(StringUtils.isNotEmpty(rentOrgCodes)){
				dataSql +=" and "+rentOrgCodes;
			}else{
				return new Test(new ArrayList(), null, 0L,"有租户"+rentOrgCodes);
			}
		}
		//----------处理租户过滤
		if (StringUtils.isNotEmpty(model.getOrderby())) {
			String value = SortParam.getSortParam(PlantArea.class, model.getOrderby());
			dataSql+=value;
		}
		Query dataQuery = em.createQuery(dataSql);		
		for (String parameter : parameterMap.keySet()) {			
			dataQuery.setParameter(parameter, parameterMap.get(parameter));		
		}		
		Integer skip = null;		
			List<com.pcitc.fms.dal.pojo.PlantArea> list = dataQuery.getResultList();		
			long count = list.size();		
			if(null != pageable){		    
				skip = model.getSkip();            
				dataQuery = em.createQuery(dataSql);            
				dataQuery.setFirstResult(skip);            
				dataQuery.setMaxResults(pageable.getPageSize());   
				Test test= new Test(dataQuery.getResultList(),pageable, count,"无租户");
//				MyPageImpl myPageImpl = new MyPageImpl(dataQuery.getResultList(),pageable, count);
//				myPageImpl.setCount(count);
				test.setCount(count);
				return test;
			}else {	
				Test test= new Test(dataQuery.getResultList(),null, count,"无租户");
//				MyPageImpl myPageImpl = new MyPageImpl(dataQuery.getResultList(),null, count);
//				myPageImpl.setCount(count);
				test.setCount(count);
				return test;
			}
	}

}
