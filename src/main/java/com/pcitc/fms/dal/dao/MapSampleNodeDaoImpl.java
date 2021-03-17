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

import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.common.RentCondition;
import com.pcitc.fms.common.SortParam;
import com.pcitc.fms.config.AreaNodeBasicSql;
import com.pcitc.fms.dal.pojo.Enterprise;
import com.pcitc.fms.dal.pojo.MapSampleNode;
import com.pcitc.fms.dal.pojo.SamplePoint;
import com.pcitc.imp.common.exception.BusiException;

@Service
public class MapSampleNodeDaoImpl {
	
	@PersistenceContext
	private EntityManager em;
	
	public MyPageImpl findPageMapSampleNodes(com.pcitc.fms.service.model.MapSampleNode model, Pageable pageable) throws BusiException{
		
		String countSqlStr = "select count(1) from MapSampleNode MSN,NodeDictionary n,AreaDictionary a,SamplePoint s,TPmOrg org where MSN.SnodeId=n.nodeId and n.areaId=a.areaDictionaryId and MSN.SnodeId=s.nodeId and a.areaDictionaryId=n.areaId and a.factoryId=org.orgId ";
		StringBuilder countSql = new StringBuilder();
		countSql.append(countSqlStr);
		
		StringBuilder dataSql = new StringBuilder();
		Map<String, Object> parameterMap = new HashedMap();
		
				
		dataSql.append(AreaNodeBasicSql.MapSampleNode);

		if (null != model.getNodeCodes() && !model.getNodeCodes().isEmpty()) {
			dataSql.append(" and n.nodeCode in :nodeCodes");
			countSql.append(" and n.nodeCode in :nodeCodes");
			parameterMap.put("nodeCodes", model.getNodeCodes());
		}
		
		if (StringUtils.isNotEmpty(model.getNodeCode())) {
			dataSql.append(" and n.nodeCode = :nodeCode");
			countSql.append(" and n.nodeCode = :nodeCode");
			parameterMap.put("nodeCode", model.getNodeCode());
		}
		
		if (StringUtils.isNotEmpty(model.getSampleCode())) {
			dataSql.append(" and s.nodeCode = :nodeCode");
			countSql.append(" and s.nodeCode = :nodeCode");
			parameterMap.put("nodeCode", model.getSampleCode());
		}
		

		if (null != model.getSampleCodes() && !model.getSampleCodes().isEmpty()) {
			dataSql.append(" and s.nodeCode in :nodeCode");
			countSql.append(" and s.nodeCode in :nodeCode");
			parameterMap.put("nodeCode", model.getSampleCodes());
		}


		if (null != model.getAreaCodes() && !model.getAreaCodes().isEmpty()) {
			dataSql.append(" and a.areaCode in :areaCodes");
			countSql.append(" and a.areaCode in :areaCodes");
			parameterMap.put("areaCodes", model.getAreaCodes());
		}
		if(model.getInUse()!=null){
			dataSql.append(" and n.dataStatus = :inUse");
			countSql.append(" and n.dataStatus = :inUse");
			parameterMap.put("inUse", model.getInUse());
		}
		
		//----------处理租户过滤
			if(StringUtils.isNotEmpty(model.getRentCode())){
				RentCondition<MapSampleNode> rentCondition = new RentCondition<MapSampleNode>();
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
			String value = SortParam.getSortParam(MapSampleNode.class, model.getOrderby());
			dataSql.append(value);
		}
		
		Query dataQuery = em.createQuery(dataSql.toString());
		Query countQuery = em.createQuery(countSql.toString());
		
		for (String parameter : parameterMap.keySet()) {
			dataQuery.setParameter(parameter, parameterMap.get(parameter));
			countQuery.setParameter(parameter, parameterMap.get(parameter));
		}
		int skip = model.getSkip();
		dataQuery.setFirstResult(skip);
		long count = (long) countQuery.getResultList().get(0);
		if (null != pageable) {
			dataQuery.setMaxResults(pageable.getPageSize());
		}
		List<com.pcitc.fms.dal.pojo.MapSampleNode> resultList = dataQuery.getResultList();
		MyPageImpl myPageImpl=new MyPageImpl(resultList, pageable, count);
		myPageImpl.setCount(count);
		return myPageImpl;
	
	}

}
