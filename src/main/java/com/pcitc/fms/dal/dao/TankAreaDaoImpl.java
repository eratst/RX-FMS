/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: TankAreaDaoImpl
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

import com.pcitc.fms.common.CheckUtil;
import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.common.RentCondition;
import com.pcitc.fms.common.SortParam;
import com.pcitc.fms.config.AreaNodeBasicSql;
import com.pcitc.fms.dal.pojo.PlantArea;
import com.pcitc.fms.dal.pojo.TankArea;
import com.pcitc.imp.common.exception.BusiException;

/**
 * The type Tank area dao.
 */
@Service
public class TankAreaDaoImpl {

	/**
	 * The Em.
	 */
	@PersistenceContext
	private EntityManager em;

	/**
	 * Find tank areas page.
	 *
	 * @param model
	 *            the model
	 * @param pageable
	 *            the pageable
	 * @return the page
	 * @throws BusiException
	 */
	@SuppressWarnings("unchecked")
	public MyPageImpl findTankAreas(com.pcitc.fms.service.model.TankArea model,
			Pageable pageable) throws BusiException {
		Map<String, Object> parameterMap = new HashedMap();
		String dataSql = AreaNodeBasicSql.tankArea;
		if (model.getTankAreaCode() != null) {
			dataSql += " and a.tankAreaCode=" + "'" + model.getTankAreaCode() + "'";
		}
		if (model.getOrgCode() != null && model.getOrgs()== null) {
			dataSql += " and org.orgCode=" + "'" + model.getOrgCode() + "'";
		}
		if(null != model.getOrgs() && model.getOrgs().size() > 0){
			dataSql += " and org.orgCode in "+CheckUtil.getList(model.getOrgs());
        }
		if (model.getTankAreaName() != null) {
			dataSql += " and ad.name like" + "'%" + model.getTankAreaName() + "%'";
		}
		if (model.getTankAreaAlias() != null) {
			dataSql += " and ad.shortName like" + "'%" + model.getTankAreaAlias() + "%'";
		}
		if (null != model.getAreaCodes() && model.getAreaCodes().size() > 0) {
			dataSql += " and a.tankAreaCode in " + CheckUtil.getList(model.getAreaCodes());
		}
		if (model.getTankAreaTypeCode() != null) {
			dataSql += " and tt.tankAreaTypeCode=" + "'" + model.getTankAreaTypeCode() + "'";
		}
		if (model.getTechnicCode() != null) {
			dataSql += " and ta.technicCode=" + "'" + model.getTechnicCode() + "'";
		}
		if (model.getInUse() != null) {
			if (model.getInUse() != -1) {
				dataSql += " and ad.enabled=" + "'" + model.getInUse() + "'";
			}
		}
		//----------处理租户过滤
			if(StringUtils.isNotEmpty(model.getRentCode())){
				RentCondition<TankArea> rentCondition = new RentCondition<TankArea>();
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
			String value = SortParam.getSortParam(TankArea.class, model.getOrderby());
			dataSql+=value;
		}
		Query dataQuery = em.createQuery(dataSql);
		for (String parameter : parameterMap.keySet()) {
			dataQuery.setParameter(parameter, parameterMap.get(parameter));
		}
		Integer skip = null;
		List<com.pcitc.fms.dal.pojo.TankArea> list = dataQuery.getResultList();
		long count = list.size();
		if (null != pageable) {
			skip = model.getSkip();
			dataQuery = em.createQuery(dataSql);
			dataQuery.setFirstResult(skip);
			dataQuery.setMaxResults(pageable.getPageSize());
			MyPageImpl myPageImpl = new MyPageImpl(dataQuery.getResultList(), pageable, count);
			myPageImpl.setCount(count);
			return myPageImpl;
		} else {
			MyPageImpl myPageImpl = new MyPageImpl(dataQuery.getResultList(), null, count);
			myPageImpl.setCount(count);
			return myPageImpl;
		}
	}
}
