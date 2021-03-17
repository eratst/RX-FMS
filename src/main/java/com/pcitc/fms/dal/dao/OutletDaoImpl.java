/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: OutletDaoImpl
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

import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.common.RentCondition;
import com.pcitc.fms.common.SortParam;
import com.pcitc.fms.dal.pojo.Outlet;
import com.pcitc.fms.dal.pojo.PipeNetworkArea;
import com.pcitc.fms.dal.pojo.Tee;
import com.pcitc.imp.common.exception.BusiException;


/**
 * The type Outlet dao.
 */
public class OutletDaoImpl {

	/**
	 * The Em.
	 */
	@PersistenceContext
	private EntityManager em;

	/**
	 * Find page outlets page.
	 *
	 * @param model
	 *            the model
	 * @param pageable
	 *            the pageable
	 * @return the page
	 * @throws BusiException 
	 */
	@SuppressWarnings("unchecked")
	public MyPageImpl findPageOutlets(com.pcitc.fms.service.model.Outlet model,
			Pageable pageable) throws BusiException {
		Map<String, Object> mapParameter = new HashedMap();
		// 排放口
		final String Outlet = "select new Outlet(a.nodeId,a.nodeCode,a.signboardTypeId,b.crtUserCode, b.crtUserName, b.crtDate, b.mntUserCode, b.mntUserName, b.mntDate,b.des,b.areaId,b.nodeLongitude,b.nodeLatitude,b.nodeTypeId,b.dataStatus,b.nodeAltitude,b.nodeName,b.nodeAlias,c.nodeTypeName,d.signboardTypeName,d.signboardTypeCode,e.areaCode,e.name,e.shortName,e.sortNum) from Outlet a,NodeDictionary b,NodeType c,SignboardType d,AreaDictionary e,TPmOrg org where "
				+ " a.nodeCode = b.nodeCode and b.nodeTypeId = c.nodeTypeId and a.signboardTypeId = d.signboardTypeId and b.areaId = e.areaDictionaryId and e.factoryId=org.orgId ";

		String count_outlet = "select count(1) from Outlet a,NodeDictionary b,NodeType c,SignboardType d,AreaDictionary e,TPmOrg org where"
				+ " a.nodeCode = b.nodeCode and b.nodeTypeId = c.nodeTypeId and a.signboardTypeId = d.signboardTypeId and b.areaId = e.areaDictionaryId and e.factoryId=org.orgId ";
		StringBuilder dataSql = new StringBuilder();
		dataSql.append(Outlet);
		StringBuilder countSql = new StringBuilder();
		countSql.append(count_outlet);

		if(null != model.getNodeCode() && !StringUtils.isEmpty(model.getNodeCode())){
			dataSql.append( " and a.nodeCode = :nodeCode");
			countSql.append( " and a.nodeCode = :nodeCode");
			mapParameter.put("nodeCode", model.getNodeCode());
		}
		if(null != model.getAreaCode() && !StringUtils.isEmpty(model.getAreaCode())){
			dataSql.append( " and e.areaCode = :areaCode");
			countSql.append( " and e.areaCode = :areaCode");
			mapParameter.put("areaCode", model.getAreaCode());
		}
		if(null != model.getNodeName() && !StringUtils.isEmpty(model.getNodeName())){
			dataSql.append(" and b.nodeName like :nodeName");
			countSql.append(" and b.nodeName like :nodeName");
			mapParameter.put("nodeName",  "%"+model.getNodeName() +"%");
		}
		if(null != model.getNodeAlias() && !StringUtils.isEmpty(model.getNodeAlias())){
			dataSql.append(" and b.nodeAlias like :nodeAlias") ;
			countSql.append(" and b.nodeAlias like :nodeAlias") ;
			mapParameter.put("nodeAlias", "%"+model.getNodeAlias() +"%");
		}
		if(null != model.getInUse() && !StringUtils.isEmpty(String.valueOf(model.getInUse()))){
			dataSql.append( " and b.dataStatus  = :inUse");
			countSql.append( " and b.dataStatus  = :inUse");
			mapParameter.put("inUse", model.getInUse());
		}

		if (null != model && null != model.getNodeCodes() && model.getNodeCodes().size() > 0) {
			dataSql.append(" and a.nodeCode in :codes");
			countSql.append(" and a.nodeCode in :codes");
			mapParameter.put("codes", model.getNodeCodes());
		}

		if (null != model.getAreaCodes() && model.getAreaCodes().size() > 0) {
			dataSql.append(" and e.areaCode in :areaCodes");
			countSql.append(" and e.areaCode in :areaCodes");
			mapParameter.put("areaCodes", model.getAreaCodes());
		}
		if (StringUtils.isNotEmpty(model.getOrderby())) {
			String value = SortParam.getSortParam(Outlet.class, model.getOrderby());
			dataSql.append(value);
		}
		//----------处理租户过滤
		if(StringUtils.isNotEmpty(model.getRentCode())){
			RentCondition<Outlet> rentCondition = new RentCondition<Outlet>();
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

		Query dataQuery = em.createQuery(dataSql.toString());
		Query countQuery = em.createQuery(countSql.toString());
		
		for (String parameter : mapParameter.keySet()) {
			dataQuery.setParameter(parameter, mapParameter.get(parameter));
			countQuery.setParameter(parameter, mapParameter.get(parameter));
		}
		long count = (long) countQuery.getResultList().get(0);
		if (null != pageable) {
			dataQuery.setFirstResult( model.getSkip());
			dataQuery.setMaxResults(pageable.getPageSize());

		}
		List<com.pcitc.fms.dal.pojo.Outlet> list = dataQuery.getResultList();
		MyPageImpl myPageImpl=new MyPageImpl(list, pageable, count);
		myPageImpl.setCount(count);
		return myPageImpl;
	}

}
