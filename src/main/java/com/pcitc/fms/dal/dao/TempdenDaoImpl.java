package com.pcitc.fms.dal.dao;

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
import com.pcitc.fms.common.SortParam;
import com.pcitc.fms.config.AreaNodeBasicSql;
import com.pcitc.fms.dal.pojo.Tempden;
import com.pcitc.imp.common.exception.BusiException;

@Service
public class TempdenDaoImpl {
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public MyPageImpl findTempden(com.pcitc.fms.service.model.Tempden tempden, Pageable pageable) throws BusiException {
		StringBuilder dataSql = new StringBuilder();
		dataSql.append(AreaNodeBasicSql.tempden + " and 1 = 1 ");
		Map<String, Object> parameterMap = new HashedMap();
		if (null != tempden.getMtrlCode() && !StringUtils.isEmpty(tempden.getMtrlCode())) {
			dataSql.append(" and m.mtrlCode = :mtrlCode");
			parameterMap.put("mtrlCode", tempden.getMtrlCode());
		}
		if (tempden.getMtrlCodeList() != null && !tempden.getMtrlCodeList().isEmpty()) {
			dataSql.append(" and m.mtrlCode in :mtrlCodes");
			parameterMap.put("mtrlCodes", tempden.getMtrlCodeList());
		}
		
		if (StringUtils.isNotEmpty(tempden.getOrderby())) {
			String value = SortParam.getSortParam(Tempden.class, tempden.getOrderby());
			dataSql.append(value);
		}
		
		Query dataQuery = em.createQuery(dataSql.toString());
		for (String parameter : parameterMap.keySet()) {
			dataQuery.setParameter(parameter, parameterMap.get(parameter));
		}
		dataSql.append(" order by m.mtrlCode asc, t.cubaTempCofe asc");
		long count = dataQuery.getResultList().size();
		if (null != pageable) {
			int skip = tempden.getSkip();
			dataQuery.setFirstResult(skip);
			dataQuery.setMaxResults(pageable.getPageSize());
		} 
		List<com.pcitc.fms.dal.pojo.Tempden> resultList = dataQuery.getResultList();
		MyPageImpl myPageImpl=new MyPageImpl(resultList, pageable, count);
		myPageImpl.setCount(count);
		return myPageImpl;
		}
	
}
