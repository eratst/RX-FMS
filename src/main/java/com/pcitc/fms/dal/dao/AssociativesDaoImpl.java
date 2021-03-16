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
import com.pcitc.fms.dal.pojo.Associatives;
import com.pcitc.fms.dal.pojo.SamplePoint;
import com.pcitc.imp.common.exception.BusiException;

@Service
public class AssociativesDaoImpl {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public MyPageImpl findAssociatives(com.pcitc.fms.service.model.Associatives associatives, Pageable pageable) throws BusiException{
		
		StringBuilder dataSql = new StringBuilder();
		StringBuilder countSql = new StringBuilder();
		dataSql.append(AreaNodeBasicSql.associatives + " and 1 = 1 ");
		Map<String, Object> parameterMap = new HashedMap();
		if (null != associatives.getNodeCodes() && !associatives.getNodeCodes().isEmpty()) {
			dataSql.append(" and associatives.nodeCode in :nodeCodes");
			parameterMap.put("nodeCodes", associatives.getNodeCodes());
		}
		
		if (null != associatives.getMtrlCodes() && !associatives.getMtrlCodes().isEmpty()) {
			dataSql.append(" and associatives.mtrlCode in :mtrlCodes");
			parameterMap.put("mtrlCodes", associatives.getMtrlCodes());
		}
		
		if (StringUtils.isNotEmpty(associatives.getNodeName())) {
			dataSql.append(" and associatives.nodeName like :nodeName");
			parameterMap.put("nodeName", "%"+associatives.getNodeName()+"%");
		}
		
		if (StringUtils.isNotEmpty(associatives.getNodeAbbrName())) {
			dataSql.append(" and node.nodeAlias like :nodeAlias");
			parameterMap.put("nodeAlias", "%"+associatives.getNodeAbbrName()+"%");
		}
		if (StringUtils.isNotEmpty(associatives.getMtrlTypeCode())) {
			dataSql.append(" and mt.mtrlTypeCode = :mtrlTypeCode");
			parameterMap.put("mtrlTypeCode", associatives.getMtrlTypeCode());
		}
		
		if (null != associatives.getInUse()) {
			dataSql.append(" and associatives.inUse = :inUse");
			parameterMap.put("inUse", associatives.getInUse());
		}
		
		//----------处理租户过滤
		if(StringUtils.isNotEmpty(associatives.getRentCode())){
			RentCondition<Associatives> rentCondition = new RentCondition<Associatives>();
			String field=" org.orgCode ";
			String rentOrgCodes = rentCondition.getRentOrgCodeCondition(associatives.getRentCode(),associatives.getBizCode(),field);
			if(StringUtils.isNotEmpty(rentOrgCodes)){
				dataSql.append( " and "+rentOrgCodes);
				countSql.append( " and "+rentOrgCodes);
			}else{
				return new MyPageImpl(new ArrayList(), null, 0L);
			}
		}
		//----------处理租户过滤
		
		if (StringUtils.isNotEmpty(associatives.getOrderby())) {
			String value = SortParam.getSortParam(Associatives.class, associatives.getOrderby());
			dataSql.append(value);
		}
		
		Query dataQuery = em.createQuery(dataSql.toString());
		for (String parameter : parameterMap.keySet()) {
			dataQuery.setParameter(parameter, parameterMap.get(parameter));
		}
		
		long count = dataQuery.getResultList().size();
		
		if (null != pageable) {
			int skip = associatives.getSkip();
			dataQuery.setFirstResult(skip);
			dataQuery.setMaxResults(pageable.getPageSize());
		}
		List<com.pcitc.fms.dal.pojo.Associatives> resultList = dataQuery.getResultList();
		MyPageImpl myPageImpl=new MyPageImpl(resultList, pageable, count);
		myPageImpl.setCount(count);
		return myPageImpl;
	}
}
