/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: MeasurementDaoImpl
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
import org.springframework.stereotype.Service;

import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.common.RentCondition;
import com.pcitc.fms.common.SortParam;
import com.pcitc.fms.config.AreaNodeBasicSql;
import com.pcitc.fms.dal.pojo.Measurement;
import com.pcitc.fms.dal.pojo.Plate;
import com.pcitc.fms.dal.pojo.ProduceFactory;
import com.pcitc.fms.dal.pojo.SamplePoint;
import com.pcitc.fms.dal.pojo.TPmBizOrgDTL;
import com.pcitc.imp.common.exception.BusiException;

/**
 * The type Measurement dao.
 */
@Service
public class MeasurementDaoImpl {

	/**
	 * The Em.
	 */
	@PersistenceContext
    private EntityManager em;



	/**
	 * Find page measurements page.
	 *
	 * @param model the model
	 * @param pageable the pageable
	 * @return the page
	 * @throws BusiException 
	 */
	@SuppressWarnings("unchecked")
	public Page<Measurement> findPageMeasurements(com.pcitc.fms.service.model.Measurement model, Pageable pageable) throws BusiException{
		Map<String ,Object> parameterMap = new HashedMap();
		StringBuilder dataSql = new StringBuilder();
		dataSql.append(AreaNodeBasicSql.measurements);
		String measurement_count = "select count(1) from Measurement a,IdxType b,NodeDictionary c,NodeType d ,Dimension e,AreaDictionary area,TPmOrg org where " + 
				 " a.idxTypeId = b.idxTypeId and a.nodeId = c.nodeId and c.nodeTypeId = d.nodeTypeId and c.areaId=area.areaDictionaryId and e.dimensionId = a.dimensionId and area.factoryId=org.orgId " ; 
				
		StringBuilder countSql = new StringBuilder();
		countSql.append(measurement_count);
		
		
		if(null != model.getInUse() && !StringUtils.isEmpty(String.valueOf(model.getInUse()))){
			dataSql.append( " and a.inUse  = :inUse");
			countSql.append( " and a.inUse  = :inUse");
			parameterMap.put("inUse", model.getInUse());
		}
		if(null != model.getIdxTypeCode() && !StringUtils.isEmpty(model.getIdxTypeCode())){
			dataSql.append( " and b.idxTypeCode = :idxTypeCode");
			countSql.append( " and b.idxTypeCode = :idxTypeCode");
			parameterMap.put("idxTypeCode", model.getIdxTypeCode());
		}
		if(null != model.getNodeCode() && !StringUtils.isEmpty(model.getNodeCode())){
			dataSql.append( " and c.nodeCode = :nodeCode");
			countSql.append( " and c.nodeCode = :nodeCode");
			parameterMap.put("nodeCode", model.getNodeCode());
		}
		
		if(null != model.getIdxCode() && !StringUtils.isEmpty(model.getIdxCode())){
			dataSql.append(" and a.idxCode = :code");
			countSql.append(" and a.idxCode = :code");
			parameterMap.put("code", model.getIdxCode());
		}
		if(null != model.getSourceDataType() && !StringUtils.isEmpty(model.getSourceDataType())){
			dataSql.append(" and a.sourceDataType = :sourceDataType");
			countSql.append(" and a.sourceDataType = :sourceDataType");
			parameterMap.put("sourceDataType", model.getSourceDataType());
		}
		
		if(null != model.getIdxFormula() && !StringUtils.isEmpty(model.getIdxFormula())){
			dataSql.append(" and a.idxFormula = :idxFormula");
			countSql.append(" and a.idxFormula = :idxFormula");
			parameterMap.put("idxFormula", model.getIdxFormula());
		}
		
		if(null != model.getIdxName() && !StringUtils.isEmpty(model.getIdxName())){
			dataSql.append(" and a.idxName like :name");
			countSql.append(" and a.idxName like :name");
			parameterMap.put("name", "%"+model.getIdxName()+"%");
		}
		if(null != model.getIdxAlias() && !StringUtils.isEmpty(model.getIdxAlias())){
			dataSql.append( " and a.idxAlias like :shortName");
			countSql.append( " and a.idxAlias like :shortName");
			parameterMap.put("shortName", "%"+model.getIdxAlias()+"%");
		}
		if(null != model.getIdxCodes() && model.getIdxCodes().size() > 0){
			dataSql.append( " and a.idxCode in :codes");
			countSql.append( " and a.idxCode in :codes");
			parameterMap.put("codes", model.getIdxCodes());
		}
		if(null != model.getNodeCodes() && model.getNodeCodes().size() > 0){
			dataSql.append( " and c.nodeCode in :nodeCodes");
			countSql.append( " and c.nodeCode in :nodeCodes");
			parameterMap.put("nodeCodes", model.getNodeCodes());
		}
		if(null != model.getFilterData() && model.getFilterData().size() > 0){
			dataSql.append( " and  a.idxCode not in :idxCodes");
			countSql.append( " and  a.idxCode not in :idxCodes");
			parameterMap.put("idxCodes", model.getFilterData());
		}
		if(null != model.getIdxTypeName() && StringUtils.isNotEmpty(model.getIdxTypeName())){
			dataSql.append( " and  b.idxTypeName like :idxTypeName");
			countSql.append( " and  b.idxTypeName like :idxTypeName");
			parameterMap.put("idxTypeName", "%"+model.getIdxTypeName()+"%");
		}
		if(null != model.getNodeName() && StringUtils.isNotEmpty(model.getNodeName())){
			dataSql.append( " and  c.nodeName like :nodeName");
			countSql.append( " and  c.nodeName like :nodeName");
			parameterMap.put("nodeName", "%"+model.getNodeName()+"%");
		}
		if(StringUtils.isNotEmpty(model.getNodeTypeCode())){
			dataSql.append(" and d.nodeTypeCode = :nodeTypeCode");
			countSql.append(" and d.nodeTypeCode = :nodeTypeCode");
			parameterMap.put("nodeTypeCode", model.getNodeTypeCode());
		}
		if(null != model.getNodeTypeName() && StringUtils.isNotEmpty(model.getNodeTypeName())){
			dataSql.append( " and  d.nodeTypeName like :nodeTypeName");
			countSql.append( " and  d.nodeTypeName like :nodeTypeName");
			parameterMap.put("nodeTypeName", "%"+model.getNodeTypeName()+"%");
		}
		//----------处理租户过滤
		if(StringUtils.isNotEmpty(model.getRentCode())){
			RentCondition<Measurement> rentCondition = new RentCondition<Measurement>();
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
			String value = SortParam.getSortParam(Measurement.class, model.getOrderby());
			dataSql.append(value);
		} 
		
		Query dataQuery = em.createQuery(dataSql.toString());
		Query countQuery = em.createQuery(countSql.toString());
		
		for (String parameter : parameterMap.keySet()) {
			countQuery.setParameter(parameter, parameterMap.get(parameter));
			dataQuery.setParameter(parameter, parameterMap.get(parameter));
		}
		
		long count = (long) countQuery.getResultList().get(0);
		dataQuery.setFirstResult(model.getSkip());
		if(null != pageable){
			dataQuery.setMaxResults(pageable.getPageSize());
		}
		List<com.pcitc.fms.dal.pojo.Measurement> list = dataQuery.getResultList();
		MyPageImpl myPageImpl=new MyPageImpl(list, pageable, count);
		myPageImpl.setCount(count);
		return myPageImpl;	
		}

	
}
