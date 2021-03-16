package com.pcitc.fms.dal.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.common.SortParam;
import com.pcitc.fms.dal.pojo.LiquidProdCoef;
import com.pcitc.imp.common.exception.BusiException;

public class LiquidProdCoefDaoImpl {

	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public MyPageImpl findLiquidProdCoefs(com.pcitc.fms.service.model.LiquidProdCoef liquidProdCoef,
			Pageable pageable) throws BusiException{
		
		final String liquidProdCoefSql = "select new LiquidProdCoef(l.liqProdId,l.denTempCofe, l.cubaTempCofe, l.den, l.crtUserCode,l.crtUserName, l.crtDate,l.mntUserCode, l. mntUserName,l.mntDate, l.des,l.sortNum, l.version, l.mtrlId, m.mtrlCode, m.mtrlName, l.inUse) from LiquidProdCoef l,Material m where l.mtrlId=m.mtrlId";

		StringBuilder dataSql  = new StringBuilder(liquidProdCoefSql);
		
		String countLiquid = "select count(1) from LiquidProdCoef l,Material m where l.mtrlId=m.mtrlId";
		
		StringBuilder countSql = new StringBuilder(countLiquid);
		
		Map<String, Object> parameterMap = new HashMap<>();
		
		if(null !=liquidProdCoef.getMtrlCode() && !StringUtils.isEmpty(liquidProdCoef.getMtrlCode())) {
			dataSql.append(" and m.mtrlCode = :mtrlCode");
			countSql.append(" and m.mtrlCode = :mtrlCode");
			parameterMap.put("mtrlCode", liquidProdCoef.getMtrlCode());
		}
		if(null !=liquidProdCoef.getMtrlName() && !StringUtils.isEmpty(liquidProdCoef.getMtrlName())) {
			dataSql.append(" and m.mtrlName like :mtrlName");
			countSql.append(" and m.mtrlName like :mtrlName");
			parameterMap.put("mtrlName", "%"+ liquidProdCoef.getMtrlName() +"%");
		}
		if (null != liquidProdCoef.getMtrlCodeList() && !liquidProdCoef.getMtrlCodeList().isEmpty()) {
			countSql.append(" and m.mtrlCode in :codeList");
			dataSql.append(" and m.mtrlCode in :codeList");
			parameterMap.put("codeList", liquidProdCoef.getMtrlCodeList());
		}
		
		if (StringUtils.isNotEmpty(liquidProdCoef.getOrderby())) {
			String value = SortParam.getSortParam(LiquidProdCoef.class, liquidProdCoef.getOrderby());
			dataSql.append(value);
		}
		
		Query dataQuery = entityManager.createQuery(dataSql.toString());
		Query countQuery = entityManager.createQuery(countSql.toString());
		
		for(String parameter : parameterMap.keySet()) {
			dataQuery.setParameter(parameter, parameterMap.get(parameter));
			countQuery.setParameter(parameter, parameterMap.get(parameter));
		}
		
		long count = (long) countQuery.getResultList().get(0);
		//long count =dataQuery.getResultList().size();
		if(null != pageable) {
			int skip=liquidProdCoef.getSkip();
			dataQuery.setFirstResult(skip);
			dataQuery.setMaxResults(pageable.getPageSize());
		}
		List<com.pcitc.fms.dal.pojo.LiquidProdCoef> resultList = dataQuery.getResultList();
		MyPageImpl myPageImpl=new MyPageImpl(resultList, pageable, count);
		myPageImpl.setCount(count);
		return myPageImpl;
	}
}
