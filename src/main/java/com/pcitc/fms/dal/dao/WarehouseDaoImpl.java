/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: WarehouseDaoImpl
 * Date:18-3-9 上午8:35
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
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.common.CheckUtil;
import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.common.RentCondition;
import com.pcitc.fms.common.SortParam;
import com.pcitc.fms.config.AreaNodeBasicSql;
import com.pcitc.fms.dal.pojo.TankArea;
import com.pcitc.fms.dal.pojo.WarehouseArea;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.imp.common.exception.BusiException;

/**
 * The type Warehouse dao.
 */
@Service
public class WarehouseDaoImpl {

	/**
	 * The Em.
	 */
	@PersistenceContext
    private EntityManager em;

	/**
	 * Find page warehouses page.
	 *
	 * @param model the model
	 * @param pageable the pageable
	 * @return the page
	 * @throws BusiException 
	 */
	@SuppressWarnings("unchecked")
	@Transactional(rollbackFor=BusinessException.class)
	public	MyPageImpl findPageWarehouses(com.pcitc.fms.service.model.Warehouse model,
			Pageable pageable) throws BusiException {
		Map<String ,Object> parameterMap = new HashedMap();
		String dataSql = AreaNodeBasicSql.warehouse;
		if(model.getInUse()!=null){
			dataSql+=" and ad.enabled="+"'"+model.getInUse()+"'";
		}
		if(model.getWarehouseName()!=null){
			dataSql+=" and ad.name like"+"'%"+model.getWarehouseName()+"%'";
		}
		if(model.getWarehouseAlias()!=null){
			dataSql+=" and ad.shortName like"+"'%"+model.getWarehouseAlias()+"%'";
		}
		if(null != model.getAreaCodes() && model.getAreaCodes().size() > 0){
			dataSql += " and a.warehouseCode in "+CheckUtil.getList(model.getAreaCodes());
        }
		if(null != model.getTechnicCode() && !StringUtils.isEmpty(model.getTechnicCode())){
			dataSql+=" and ta.technicCode="+"'"+model.getTechnicCode()+"'";
        }
		if(null != model.getOrgs() && model.getOrgs().size() > 0){
			dataSql += " and org.orgCode in "+CheckUtil.getList(model.getOrgs());
        }
		if(null != model.getOrgCode() && !StringUtils.isEmpty(model.getOrgCode())){
			dataSql+=" and org.orgCode="+"'"+model.getOrgCode()+"'";
        }
		if(null != model.getWarehouseCode() && !StringUtils.isEmpty(model.getWarehouseCode())){
			dataSql+=" and a.warehouseCode="+"'"+model.getWarehouseCode()+"'";
        }
		if(null != model.getWarehouseTypeCode() && !StringUtils.isEmpty(model.getWarehouseTypeCode())){
			dataSql+=" and tt.warehouseTypeCode="+"'"+model.getWarehouseTypeCode()+"'";
        }
		
		//----------处理租户过滤
		if(StringUtils.isNotEmpty(model.getRentCode())){
			RentCondition<WarehouseArea> rentCondition = new RentCondition<WarehouseArea>();
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
			String value = SortParam.getSortParam(WarehouseArea.class, model.getOrderby());
			dataSql+=value;
		}
		
		Query dataQuery = em.createQuery(dataSql);
		for (String parameter : parameterMap.keySet()) {
			dataQuery.setParameter(parameter, parameterMap.get(parameter));
		}
		Integer skip = null;
		List<com.pcitc.fms.dal.pojo.WarehouseArea> list = dataQuery.getResultList();
		long count = list.size();
		if(null != pageable){
		    skip = model.getSkip();
            dataQuery = em.createQuery(dataSql);
            dataQuery.setFirstResult(skip);
            dataQuery.setMaxResults(pageable.getPageSize());
            MyPageImpl myPageImpl = new MyPageImpl(dataQuery.getResultList(), pageable,count);
            myPageImpl.setCount(count);	
            return myPageImpl;
		} else {
			MyPageImpl myPageImpl = new MyPageImpl(dataQuery.getResultList(), null,count);
            myPageImpl.setCount(count);	
            return myPageImpl;
		}

	}

}
