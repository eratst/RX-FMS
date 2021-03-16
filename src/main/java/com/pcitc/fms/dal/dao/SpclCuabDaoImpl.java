package com.pcitc.fms.dal.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.common.RentCondition;
import com.pcitc.fms.common.SortParam;
import com.pcitc.fms.config.AreaNodeBasicSql;
import com.pcitc.fms.dal.pojo.SamplePoint;
import com.pcitc.fms.dal.pojo.SpclCuab;
import com.pcitc.imp.common.exception.BusiException;

@Service
public class SpclCuabDaoImpl {
	
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public MyPageImpl findSpclCuab(com.pcitc.fms.service.model.SpclCuab spclCuab, Pageable pageable) throws BusiException{
		StringBuilder dataSql = new StringBuilder();
		StringBuilder countSql = new StringBuilder();
		dataSql.append(AreaNodeBasicSql.spclCuab + " and 1 = 1 ");
		Map<String, Object> parameterMap = new HashedMap();
		if (null != spclCuab.getNodeCode() && !StringUtils.isEmpty(spclCuab.getNodeCode())) {
			dataSql.append(" and t.nodeCode = :nodeCode");
			parameterMap.put("nodeCode", spclCuab.getNodeCode());
		}
		if (spclCuab.getNodeCodeList() != null && !spclCuab.getNodeCodeList().isEmpty()) {
			dataSql.append(" and t.nodeCode in :nodeCodes");
			parameterMap.put("nodeCodes", spclCuab.getNodeCodeList());
		}
		
		//----------处理租户过滤
		if(StringUtils.isNotEmpty(spclCuab.getRentCode())){
			RentCondition<SpclCuab> rentCondition = new RentCondition<SpclCuab>();
			String field=" org.orgCode ";
			String rentOrgCodes = rentCondition.getRentOrgCodeCondition(spclCuab.getRentCode(),spclCuab.getBizCode(),field);
			if(StringUtils.isNotEmpty(rentOrgCodes)){
				dataSql.append( " and "+rentOrgCodes);
				countSql.append( " and "+rentOrgCodes);
			}else{
				return new MyPageImpl(new ArrayList(), null, 0L);
			}
		}
		//----------处理租户过滤
		
		if (StringUtils.isNotEmpty(spclCuab.getOrderby())) {
			String value = SortParam.getSortParam(SpclCuab.class, spclCuab.getOrderby());
			dataSql.append(value);
		}
		Query dataQuery = em.createQuery(dataSql.toString());
		for (String parameter : parameterMap.keySet()) {
			dataQuery.setParameter(parameter, parameterMap.get(parameter));
		}
		long count = dataQuery.getResultList().size();
		if (null != pageable) {
			int skip = spclCuab.getSkip();
			dataQuery.setFirstResult(skip);
			dataQuery.setMaxResults(pageable.getPageSize());
		}

		List<com.pcitc.fms.dal.pojo.SpclCuab> resultList = dataQuery.getResultList();
		MyPageImpl myPageImpl=new MyPageImpl(resultList, pageable, count);
		myPageImpl.setCount(count);
		return myPageImpl;
	}
}
