/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: DimensioDaoImpl
 * Date:18-3-9 上午8:34
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.common.SortParam;
import com.pcitc.fms.dal.pojo.Dimension;
import com.pcitc.fms.dal.pojo.Stock;
import com.pcitc.imp.common.exception.BusiException;

/**
 * The type Dimensio dao.
 */
@Service
public class DimensioDaoImpl {

	/**
	 * The Em.
	 */
	@PersistenceContext
	private EntityManager em;

	/**
	 * Find page dimensions page.
	 *
	 * @param model the model
	 * @param pageable the pageable
	 * @return the page
	 * @throws BusiException 
	 */
	@SuppressWarnings("unchecked")
	public MyPageImpl findPageDimensions(com.pcitc.fms.service.model.Dimension model,Pageable pageable) throws BusiException {
		StringBuilder dataSql = new StringBuilder();
		StringBuilder countSql = new StringBuilder();
		Map<String, Object> parameterMap = new HashedMap();
		dataSql.append("select a from Dimension a ");
		countSql.append("select count(1) from Dimension a where 1=1");
//		if (null != model.getDimensionCode()&& !StringUtils.isEmpty(model.getDimensionCode())) {
//			dataSql.append(" and a.dimensionCode = :code");
//			parameterMap.put("code", model.getDimensionCode());
//		}
//		if (null != model.getDimensionAlias()&& !StringUtils.isEmpty(model.getDimensionAlias())) {
//			dataSql.append(" and a.dimensionAlias like  :dimensionAlias");
//			parameterMap.put("dimensionAlias", "%"+model.getDimensionAlias()+"%");
//		}
//		if (null != model.getDimensionName()&& !StringUtils.isEmpty(model.getDimensionName())) {
//			dataSql.append(" and a.dimensionName like :dimensionName");
//			parameterMap.put("dimensionName", "%"+model.getDimensionName()+"%");
//		}
//		if (null != model.getCodeList() && model.getCodeList().size() > 0) {
//			dataSql.append(" and a.dimensionCode in :codes");
//			parameterMap.put("codes", model.getCodeList());
//		}
//		if (null != model.getIdList() && model.getIdList().size() > 0) {
//			dataSql.append(" and a.dimensionId in :ids");
//			parameterMap.put("ids", model.getIdList());
//		}
//		if (null != model.getDataStatus()) {
//			dataSql.append(" and a.dataStatus = :dataStatus");
//			parameterMap.put("dataStatus", model.getDataStatus());
//		}
		if (StringUtils.isNotEmpty(model.getOrderby())) {
			String value = SortParam.getSortParam(Dimension.class, model.getOrderby());
			dataSql.append(value);
		}
		
		Query dataQuery = em.createQuery(dataSql.toString());
		
		Query countQuery = em.createQuery(countSql.toString());
		for (String parameter : parameterMap.keySet()) {
			dataQuery.setParameter(parameter, parameterMap.get(parameter));
		}
		long count = (long) countQuery.getResultList().get(0);
		int skip = model.getSkip();
		dataQuery.setFirstResult(skip);
		if (null != pageable) {
			dataQuery.setMaxResults(pageable.getPageSize());
		}
		List<com.pcitc.fms.dal.pojo.Dimension> list = dataQuery.getResultList();
		MyPageImpl myPageImpl=new MyPageImpl(list, pageable, count);
		myPageImpl.setCount(count);
		return myPageImpl;	}
}
