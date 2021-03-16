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
import com.pcitc.fms.dal.pojo.FltperCuab;
import com.pcitc.fms.dal.pojo.SamplePoint;
import com.pcitc.fms.dal.pojo.Tank;
import com.pcitc.imp.common.exception.BusiException;

@Service
public class FltperCuabDaoImpl {

	@PersistenceContext
    private EntityManager em;
	
	public MyPageImpl findFltperCuabs(com.pcitc.fms.service.model.FltperCuab fltperCuab, Pageable pageable) throws BusiException {
		StringBuilder dataSql = new StringBuilder();
		StringBuilder countSql = new StringBuilder();
		dataSql.append(AreaNodeBasicSql.fltperCuab +" and 1 = 1 ");
		Map<String ,Object> parameterMap = new HashedMap();
		if (null != fltperCuab.getNodeCode()  && !StringUtils.isEmpty(fltperCuab.getNodeCode())) {
			dataSql.append( " and t.nodeCode = :nodeCode");
			parameterMap.put("nodeCode", fltperCuab.getNodeCode());
		}
		if (fltperCuab.getNodeCodeList()!=null && !fltperCuab.getNodeCodeList().isEmpty()) {
			dataSql.append( " and t.nodeCode in :nodeCodes");
			parameterMap.put("nodeCodes", fltperCuab.getNodeCodeList());
		}
		//----------处理租户过滤
		if(StringUtils.isNotEmpty(fltperCuab.getRentCode())){
			RentCondition<SamplePoint> rentCondition = new RentCondition<SamplePoint>();
			String field=" org.orgCode ";
			String rentOrgCodes = rentCondition.getRentOrgCodeCondition(fltperCuab.getRentCode(),fltperCuab.getBizCode(),field);
			if(StringUtils.isNotEmpty(rentOrgCodes)){
				dataSql.append( " and "+rentOrgCodes);
				countSql.append( " and "+rentOrgCodes);
			}else{
				return new MyPageImpl(new ArrayList(), null, 0L);
			}
		}
		//----------处理租户过滤
		if (StringUtils.isNotEmpty(fltperCuab.getOrderby())) {
			String value = SortParam.getSortParam(FltperCuab.class, fltperCuab.getOrderby());
			dataSql.append(value);
		}
		Query dataQuery = em.createQuery(dataSql.toString());
		for (String parameter : parameterMap.keySet()) {
			dataQuery.setParameter(parameter, parameterMap.get(parameter));
		}
		long count = dataQuery.getResultList().size();
		if(null != pageable){
			int skip = fltperCuab.getSkip();
			dataQuery.setFirstResult(skip);
			dataQuery.setMaxResults(pageable.getPageSize());
		}
		List<com.pcitc.fms.dal.pojo.FltperCuab> resultList = dataQuery.getResultList();
		MyPageImpl myPageImpl=new MyPageImpl(resultList, pageable, count);
		myPageImpl.setCount(count);
		return myPageImpl;
	}
}
