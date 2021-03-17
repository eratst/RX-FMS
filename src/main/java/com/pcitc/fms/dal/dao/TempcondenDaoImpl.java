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
import com.pcitc.fms.dal.pojo.Tempconden;
import com.pcitc.imp.common.exception.BusiException;

@Service
public class TempcondenDaoImpl {
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public MyPageImpl findTempconden(com.pcitc.fms.service.model.Tempconden tempconden, Pageable pageable) throws BusiException{
		StringBuilder dataSql = new StringBuilder();
		dataSql.append(AreaNodeBasicSql.tempconden + " and 1 = 1 ");
		Map<String, Object> parameterMap = new HashedMap();
		if (null != tempconden.getMtrlCode() && !StringUtils.isEmpty(tempconden.getMtrlCode())) {
			dataSql.append(" and m.mtrlCode = :mtrlCode");
			parameterMap.put("mtrlCode", tempconden.getMtrlCode());
		}
		if (tempconden.getMtrlCodeList() != null && !tempconden.getMtrlCodeList().isEmpty()) {
			dataSql.append(" and m.mtrlCode in :mtrlCodes");
			parameterMap.put("mtrlCodes", tempconden.getMtrlCodeList());
		}
		
		if (StringUtils.isNotEmpty(tempconden.getOrderby())) {
			String value = SortParam.getSortParam(Tempconden.class, tempconden.getOrderby());
			dataSql.append(value);
		}
		
		Query dataQuery = em.createQuery(dataSql.toString());
		for (String parameter : parameterMap.keySet()) {
			dataQuery.setParameter(parameter, parameterMap.get(parameter));
		}
		dataSql.append(" order by m.mtrlCode asc, t.cubaTempCofe asc, t.con asc");
		long count = dataQuery.getResultList().size();
		if (null != pageable) {
			int skip = tempconden.getSkip();
			dataQuery.setFirstResult(skip);
			dataQuery.setMaxResults(pageable.getPageSize());
		}
		List<com.pcitc.fms.dal.pojo.Tempconden> resultList = dataQuery.getResultList();
		MyPageImpl myPageImpl=new MyPageImpl(resultList, pageable, count);
		myPageImpl.setCount(count);
		return myPageImpl;

	}
}
