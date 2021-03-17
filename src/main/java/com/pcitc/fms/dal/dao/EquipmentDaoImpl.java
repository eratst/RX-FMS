/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: EquipmentDaoImpl
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
import com.pcitc.fms.config.AreaNodeBasicSql;
import com.pcitc.fms.dal.pojo.Equipment;
import com.pcitc.fms.dal.pojo.Tank;
import com.pcitc.fms.dal.pojo.Tee;
import com.pcitc.imp.common.exception.BusiException;


/**
 * The type Equipment dao.
 */
public class EquipmentDaoImpl {

	/**
	 * The Em.
	 */
	@PersistenceContext
    private EntityManager em;

	/**
	 * Get all page.
	 *
	 * @param model the model
	 * @param pageable the pageable
	 * @return the page
	 * @throws BusiException 
	 */
	@SuppressWarnings("unchecked")
	public MyPageImpl getAll(com.pcitc.fms.service.model.Equipment model, Pageable pageable) throws BusiException{
		String count_equipment="select count(1) from Equipment a,NodeDictionary b,NodeType c,EquTechnic d,AreaDictionary e,TPmOrg org where "
					              + " a.nodeCode = b.nodeCode and b.nodeTypeId = c.nodeTypeId and a.technicId = d.technicId and b.areaId = e.areaDictionaryId and org.orgId=e.factoryId ";
		Map<String ,Object> parameterMap = new HashedMap();
		StringBuilder dataSql = new StringBuilder();
		StringBuilder countSql = new StringBuilder();
		dataSql.append(AreaNodeBasicSql.Equipment);
		countSql.append(count_equipment);
		if(null != model.getAreaCode() && !StringUtils.isEmpty(model.getAreaCode())){
			dataSql.append( " and e.areaCode = :areaCode");
			countSql.append( " and e.areaCode = :areaCode");
			parameterMap.put("areaCode", model.getAreaCode());
		}
		if(null != model.getAreaCodes() && model.getAreaCodes().size()>0){
			dataSql.append( " and e.areaCode in :areaCodes");
			countSql.append( " and e.areaCode in :areaCodes");
			parameterMap.put("areaCodes", model.getAreaCodes());
		}
		if(null != model && !StringUtils.isEmpty(model.getNodeCode())){
			dataSql.append( " and a.nodeCode = :nodeCode");
			countSql.append( " and a.nodeCode = :nodeCode");
			parameterMap.put("nodeCode", model.getNodeCode());
		}
		if(null != model.getNodeCodes() && model.getNodeCodes().size() > 0){
			dataSql.append(" and a.nodeCode in :nodecodes");
			countSql.append(" and a.nodeCode in :nodecodes");
			parameterMap.put("nodecodes", model.getNodeCodes());
		}
		if(null != model.getInUse() && !StringUtils.isEmpty(String.valueOf(model.getInUse()))){
		dataSql.append( " and b.dataStatus = :dataStatus");
		countSql.append( " and b.dataStatus = :dataStatus");
		parameterMap.put("dataStatus", model.getInUse());
		}
		if(null != model.getNodeName() && !StringUtils.isEmpty(model.getNodeName())){
		dataSql.append(" and b.nodeName like :name");
		countSql.append(" and b.nodeName like :name");
		parameterMap.put("name", "%"+model.getNodeName()+"%");
		}
		if(null != model.getEquMgrCode() && !StringUtils.isEmpty(model.getEquMgrCode())){
			dataSql.append(" and a.equMgrCode = :equMgrCode");
			countSql.append(" and a.equMgrCode = :equMgrCode");
			parameterMap.put("equMgrCode", model.getEquMgrCode());
		}
		if(null != model.getNodeAlias() && !StringUtils.isEmpty(model.getNodeAlias())){
		dataSql.append(" and b.nodeAlias like :nodeAlias");
		countSql.append(" and b.nodeAlias like :nodeAlias");
		parameterMap.put("nodeAlias", "%"+model.getNodeAlias()+"%");
		}
		if(null != model.getTechnicCode() && !StringUtils.isEmpty(model.getTechnicCode())){
			dataSql.append(" and d.technicCode like :technicCode");
			countSql.append(" and d.technicCode like :technicCode");
			parameterMap.put("technicCode",model.getTechnicCode());
		}
		//----------处理租户过滤
		if(StringUtils.isNotEmpty(model.getRentCode())){
			RentCondition<Equipment> rentCondition = new RentCondition<Equipment>();
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
		
		if(StringUtils.isNotEmpty(model.getOrderby())){
			String value = SortParam.getSortParam(Equipment.class, model.getOrderby());
			dataSql.append(value);
		}
		Query dataQuery = em.createQuery(dataSql.toString());
		Query countQuery = em.createQuery(countSql.toString());
		for (String parameter : parameterMap.keySet()) {
			dataQuery.setParameter(parameter, parameterMap.get(parameter));
			countQuery.setParameter(parameter, parameterMap.get(parameter));
		}
		
		int skip = model.getSkip();
		long count = (long) countQuery.getResultList().get(0);
		dataQuery.setFirstResult(skip);
		if(null != pageable){
			dataQuery.setMaxResults(pageable.getPageSize());
		}
		List<com.pcitc.fms.dal.pojo.Equipment> list = dataQuery.getResultList();
		MyPageImpl myPageImpl=new MyPageImpl(list, pageable, count);
		myPageImpl.setCount(count);
		return myPageImpl;
	}

}
