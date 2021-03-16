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
import com.pcitc.fms.dal.pojo.SamplePoint;
import com.pcitc.fms.dal.pojo.StddmCuabs;
import com.pcitc.imp.common.exception.BusiException;

@Service
public class StddmCuabsDaoImpl {
	@PersistenceContext
	private EntityManager em;
	
	public MyPageImpl getStddmCuabs(com.pcitc.fms.service.model.StddmCuabs stddmCuabs,Pageable pageable) throws BusiException{
		StringBuilder dataSql = new StringBuilder();
		StringBuilder countSql = new StringBuilder();
		Map<String ,Object> parameterMap = new HashedMap();
		dataSql.append(AreaNodeBasicSql.StddmCuabs);
		if(stddmCuabs.getNodeCodeList()!=null && !stddmCuabs.getNodeCodeList().isEmpty()){
			dataSql.append(" and t.nodeCode in :nodeCodes");
			parameterMap.put("nodeCodes", stddmCuabs.getNodeCodeList());
		}
		if(stddmCuabs.getNodeCode()!=null && !stddmCuabs.getNodeCode().isEmpty()){
			dataSql.append(" and t.nodeCode = :nodeCode");
			parameterMap.put("nodeCode", stddmCuabs.getNodeCode());
		}
		
		//----------处理租户过滤
		if(StringUtils.isNotEmpty(stddmCuabs.getRentCode())){
			RentCondition<StddmCuabs> rentCondition = new RentCondition<StddmCuabs>();
			String field=" org.orgCode ";
			String rentOrgCodes = rentCondition.getRentOrgCodeCondition(stddmCuabs.getRentCode(),stddmCuabs.getBizCode(),field);
			if(StringUtils.isNotEmpty(rentOrgCodes)){
				dataSql.append( " and "+rentOrgCodes);
				countSql.append( " and "+rentOrgCodes);
			}else{
				return new MyPageImpl(new ArrayList(), null, 0L);
			}
		}
		//----------处理租户过滤
		
		if (StringUtils.isNotEmpty(stddmCuabs.getOrderby())) {
			String value = SortParam.getSortParam(StddmCuabs.class, stddmCuabs.getOrderby());
			dataSql.append(value);
		}
		
		Query dataQuery = em.createQuery(dataSql.toString());
		for (String parameter : parameterMap.keySet()) {
	           dataQuery.setParameter(parameter, parameterMap.get(parameter));
	       }
		long count = dataQuery.getResultList().size();
		if (null != pageable) {
			int skip = stddmCuabs.getSkip();
			dataQuery.setFirstResult(skip);
			dataQuery.setMaxResults(pageable.getPageSize());
		}
		List<com.pcitc.fms.dal.pojo.StddmCuabs> resultList = dataQuery.getResultList();
		MyPageImpl myPageImpl=new MyPageImpl(resultList, pageable, count);
		myPageImpl.setCount(count);
		return myPageImpl;
	}
}
