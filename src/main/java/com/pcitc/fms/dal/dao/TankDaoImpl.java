/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: TankDaoImpl
 * Date:18-3-9 上午8:35
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.dal.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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

import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.common.RentCondition;
import com.pcitc.fms.common.SortParam;
import com.pcitc.fms.config.AreaNodeBasicSql;
import com.pcitc.fms.dal.pojo.Tank;
import com.pcitc.fms.dal.pojo.Tee;
import com.pcitc.imp.common.exception.BusiException;

/**
 * The type Tank dao.
 */
@Service
public class TankDaoImpl {

	/**
	 * The Em.
	 */
	@PersistenceContext
	private EntityManager em;

	/**
	 * Find page tanks page.
	 *
	 * @param model
	 *            the model
	 * @param pageable
	 *            the pageable
	 * @return the page
	 * @throws BusiException
	 */
	@SuppressWarnings("unchecked")
	public MyPageImpl findPageTanks(com.pcitc.fms.service.model.Tank model, Pageable pageable) throws BusiException {
		
		String count_tank="select count(1) from Tank a,NodeDictionary b,TankArea c,TankType d,AreaDictionary e, TPmOrg org where"
            + " a.nodeCode = b.nodeCode and a.tankTypeId = d.tankTypeId and b.areaId = e.areaDictionaryId and e.areaDictionaryId=c.tankAreaId and org.orgId=e.factoryId ";
		StringBuilder countSql = new StringBuilder();
		StringBuilder dataSql = new StringBuilder();
		Map<String, Object> parameterMap = new HashedMap();
		countSql.append(count_tank);
		dataSql.append(AreaNodeBasicSql.Tank);

		if (null != model.getNodeCodes() && !model.getNodeCodes().isEmpty()) {
			dataSql.append(" and b.nodeCode in :nodeCodes");
			countSql.append(" and b.nodeCode in :nodeCodes");
			parameterMap.put("nodeCodes", model.getNodeCodes());
		}
		if (null != model.getNodeCode() && !model.getNodeCode().isEmpty()) {
			dataSql.append(" and b.nodeCode in :nodeCode");
			countSql.append(" and b.nodeCode in :nodeCode");
			parameterMap.put("nodeCode", model.getNodeCode());
		}

		if (null != model.getNodeName() && !StringUtils.isEmpty(model.getNodeName())) {
			dataSql.append(" and b.nodeName like :nodeName");
			countSql.append(" and b.nodeName like :nodeName");
			parameterMap.put("nodeName", "%" + model.getNodeName() + "%");
		}

		if (null != model.getNodeAlias() && !StringUtils.isEmpty(model.getNodeAlias())) {
			dataSql.append(" and b.nodeAlias like :nodeAlias");
			countSql.append(" and b.nodeAlias like :nodeAlias");
			parameterMap.put("nodeAlias", "%" + model.getNodeAlias() + "%");
		}

		if (null != model.getOrgCode() && !StringUtils.isEmpty(model.getOrgCode())) {
			dataSql.append(" and org.orgCode = :orgCode");
			countSql.append(" and org.orgCode = :orgCode");
			parameterMap.put("orgCode", model.getOrgCode());
		}

		if (null != model.getAreaCode() && !StringUtils.isEmpty(model.getAreaCode())) {
			dataSql.append(" and e.areaCode = :areaCode");
			countSql.append(" and e.areaCode = :areaCode");
			parameterMap.put("areaCode", model.getAreaCode());
		}

		if (null != model.getTankTypeId() && !StringUtils.isEmpty(String.valueOf(model.getTankTypeId()))) {
			dataSql.append(" and a.tankTypeId = :tankTypeId");
			countSql.append(" and a.tankTypeId = :tankTypeId");
			parameterMap.put("tankTypeId", model.getTankTypeId());
		}

		if (null != model.getInUse() && !StringUtils.isEmpty(String.valueOf(model.getInUse()))) {
			if (model.getInUse() != -1) {
				dataSql.append(" and b.dataStatus = :inUse");
				countSql.append(" and b.dataStatus = :inUse");
				parameterMap.put("inUse", model.getInUse());
			}
		}
		if (null != model.getAreaCodes() && !model.getAreaCodes().isEmpty()) {
			dataSql.append(" and e.areaCode in :areaCodes");
			countSql.append(" and e.areaCode in :areaCodes");
			parameterMap.put("areaCodes", model.getAreaCodes());
		}
		if (null != model.getTankTypeCode() && !model.getTankTypeCode().isEmpty()) {
			dataSql.append(" and d.tankTypeCode = :tankTypeCode");
			countSql.append(" and d.tankTypeCode = :tankTypeCode");
			parameterMap.put("tankTypeCode", model.getTankTypeCode());
		}
		
		//----------处理租户过滤
		if(StringUtils.isNotEmpty(model.getRentCode())){
			RentCondition<Tank> rentCondition = new RentCondition<Tank>();
			String field=" org.orgCode ";
			String rentOrgCodes = rentCondition.getRentOrgCodeCondition(model.getRentCode(),model.getBizCode(),field);
			if(StringUtils.isNotEmpty(rentOrgCodes)){
				dataSql.append( " and "+rentOrgCodes);
				countSql.append( " and "+rentOrgCodes);
			}else{
				return new MyPageImpl(new ArrayList(), null, 0L);
			}
		}
		//----------处理租户过滤
		
		if (StringUtils.isNotEmpty(model.getOrderby())) {
			String value = SortParam.getSortParam(Tank.class, model.getOrderby());
			dataSql.append(value);
		}
		
		Query dataQuery = em.createQuery(dataSql.toString());
		Query countSqlQuery = em.createQuery(countSql.toString());
		for (String parameter : parameterMap.keySet()) {
			dataQuery.setParameter(parameter, parameterMap.get(parameter));
			countSqlQuery.setParameter(parameter, parameterMap.get(parameter));
		}
		long count = (long) countSqlQuery.getResultList().get(0);
		int skip = model.getSkip();
		dataQuery.setFirstResult(skip);
		if (null != pageable) {
			dataQuery.setMaxResults(pageable.getPageSize());
		}
		List<com.pcitc.fms.dal.pojo.Tank> list = dataQuery.getResultList();
		MyPageImpl myPageImpl=new MyPageImpl(list, pageable, count);
		myPageImpl.setCount(count);
		return myPageImpl;
	}

}
