/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: ValveDaoImpl
 * Date:18-3-9 上午8:35
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.dal.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.common.RentCondition;
import com.pcitc.fms.common.SortParam;
import com.pcitc.fms.dal.pojo.ProduceFactory;
import com.pcitc.fms.dal.pojo.SamplePoint;
import com.pcitc.fms.dal.pojo.Tubulation;
import com.pcitc.fms.dal.pojo.Valve;
import com.pcitc.imp.common.exception.BusiException;


/**
 * Title: ValveDaoImpl Description:阀门数据操作实现
 *
 * @param <Valve> the type parameter
 * @author zhenqiang.zhao
 * @version 1.0
 * @date 2017年6月14日
 */
public class ValveDaoImpl {

	/**
	 * The Em.
	 */
	@PersistenceContext
	private EntityManager em;

	/**
	 * Gets valves.
	 *
	 * @param valveModel the valve model
	 * @param pageable the pageable
	 * @return the valves
	 * @Title: getValves
	 * @Description: TODO
	 * @date 2017年6月15日
	 * @return: void
	 * @author zhenqiang.zhao
	 * @throws BusiException 
	 */
	@SuppressWarnings("unchecked")
	public MyPageImpl getValves(com.pcitc.fms.service.model.Valve valveModel,
			Pageable pageable) throws BusiException {
		Map<String, Object> mapParameter = new HashMap<String, Object>();
		String Valve = "select new Valve(a.nodeId,a.nodeCode,b.crtUserCode, b.crtUserName, b.crtDate, b.mntUserCode, b.mntUserName, b.mntDate,b.des,b.areaId,b.nodeLongitude,b.nodeLatitude,b.nodeTypeId,b.dataStatus,b.nodeAltitude,b.nodeName,b.nodeAlias,c.nodeTypeName,a.sortNum,d.areaCode,d.name,d.shortName,b.nodeLevel,b.nodeModel) from Valve a,NodeDictionary b,NodeType c,AreaDictionary d,TPmOrg e  where "
				  + " a.nodeCode = b.nodeCode and b.nodeTypeId = c.nodeTypeId and b.areaId = d.areaDictionaryId and d.factoryId=e.orgId ";
		StringBuilder dataSql = new StringBuilder();
		dataSql.append(Valve);
		
		String count_valve = "select count(1) from Valve a,NodeDictionary b,NodeType c,AreaDictionary d ,TPmOrg e where "
				 + " a.nodeCode = b.nodeCode and b.nodeTypeId = c.nodeTypeId and b.areaId = d.areaDictionaryId and d.factoryId=e.orgId ";
		StringBuilder countSql = new StringBuilder();
		countSql.append(count_valve);
		
		if(null != valveModel.getNodeCode() && !StringUtils.isEmpty(valveModel.getNodeCode())){
			dataSql.append( " and a.nodeCode = :nodeCode");
			countSql.append( " and a.nodeCode = :nodeCode");
			mapParameter.put("nodeCode", valveModel.getNodeCode());
		}
		if(null != valveModel.getAreaCode() && !StringUtils.isEmpty(valveModel.getAreaCode())){
			dataSql.append( " and d.areaCode = :areaCode");
			countSql.append( " and d.areaCode = :areaCode");
			mapParameter.put("areaCode", valveModel.getAreaCode());
		}
		if(null != valveModel.getOrgCode() && !StringUtils.isEmpty(valveModel.getOrgCode())){
			dataSql.append( " and e.orgCode = :orgCode");
			countSql.append( " and e.orgCode = :orgCode");
			mapParameter.put("orgCode", valveModel.getOrgCode());
		}
		if(null != valveModel.getNodeName() && !StringUtils.isEmpty(valveModel.getNodeName())){
			dataSql.append(" and b.nodeName like :nodeName");
			countSql.append(" and b.nodeName like :nodeName");
			mapParameter.put("nodeName", "%"+valveModel.getNodeName()+"%");
		}
		if(null != valveModel.getNodeAlias() && !StringUtils.isEmpty(valveModel.getNodeAlias())){
			dataSql.append(" and b.nodeAlias like :nodeAlias") ;
			countSql.append(" and b.nodeAlias like :nodeAlias") ;
			mapParameter.put("nodeAlias",  "%"+valveModel.getNodeAlias()+"%");
		}
		if(null != valveModel.getInUse() && !StringUtils.isEmpty(String.valueOf(valveModel.getInUse()))){
			dataSql.append( " and b.dataStatus  = :inUse");
			countSql.append( " and b.dataStatus  = :inUse");
			mapParameter.put("inUse", valveModel.getInUse());
		}

		if (null != valveModel && null != valveModel.getNodeCodes() && valveModel.getNodeCodes().size() > 0) {
			dataSql.append(" and a.nodeCode in :codes");
			countSql.append(" and a.nodeCode in :codes");
			mapParameter.put("codes", valveModel.getNodeCodes());
		}

		if (null != valveModel.getAreaCodes() && valveModel.getAreaCodes().size() > 0) {
			dataSql.append(" and d.areaCode in :areaCodes");
			countSql.append(" and d.areaCode in :areaCodes");
			mapParameter.put("areaCodes", valveModel.getAreaCodes());
		}
		
		//----------处理租户过滤
		if(StringUtils.isNotEmpty(valveModel.getRentCode())){
			RentCondition<SamplePoint> rentCondition = new RentCondition<SamplePoint>();
			String field=" e.orgCode ";
			String rentOrgCodes = rentCondition.getRentOrgCodeCondition(valveModel.getRentCode(),valveModel.getBizCode(),field);
			if(StringUtils.isNotEmpty(rentOrgCodes)){
				dataSql.append( " and "+rentOrgCodes);
				countSql.append( " and "+rentOrgCodes);
			}else{
				return new MyPageImpl(new ArrayList(), null, 0L);
			}
		}
		//----------处理租户过滤	
		if (StringUtils.isNotEmpty(valveModel.getOrderby())) {
			String value = SortParam.getSortParam(Valve.class, valveModel.getOrderby());
			dataSql.append(value);
		}
	
		Query dataQuery = em.createQuery(dataSql.toString());
		Query countQuery = em.createQuery(countSql.toString());
		
		for (String parameter : mapParameter.keySet()) {
			dataQuery.setParameter(parameter, mapParameter.get(parameter));
			countQuery.setParameter(parameter, mapParameter.get(parameter));
		}
		long count = (long) countQuery.getResultList().get(0);
		if (null != pageable) {
			dataQuery.setFirstResult( valveModel.getSkip());
			dataQuery.setMaxResults(pageable.getPageSize());

		}
		List<com.pcitc.fms.dal.pojo.Valve> list = dataQuery.getResultList();
		MyPageImpl myPageImpl=new MyPageImpl(list, pageable, count);
		myPageImpl.setCount(count);
		return myPageImpl;
	}

}
