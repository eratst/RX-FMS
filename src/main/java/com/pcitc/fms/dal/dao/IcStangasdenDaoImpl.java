package com.pcitc.fms.dal.dao;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang.text.StrBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.config.AreaNodeBasicSql;
import com.pcitc.fms.dal.pojo.IcStangasden;


@Service
public class IcStangasdenDaoImpl {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public MyPageImpl findIcStangasdens(com.pcitc.fms.service.model.IcStangasden icStangasden, Pageable pageable) {
		
		StrBuilder dataSql = new StrBuilder();
		dataSql.append(AreaNodeBasicSql.IcStangasden);

		String datacount = "select count(1) from IcStangasden icStangasden where 1=1 ";
		StringBuilder countSql = new StringBuilder();
		countSql.append(datacount);

		Map<String, Object> parameterMap = new HashMap<>();
		if (null != icStangasden.getStangasdenId()) {
			dataSql.append(" and icStangasden.stangasdenId = :stangasdenId");
			countSql.append(" and icStangasden.stangasdenId = :stangasdenId");
			parameterMap.put("stangasdenId", icStangasden.getStangasdenId());
		}
		if (null != icStangasden.getInUse()) {
			dataSql.append(" and icStangasden.inUse = :inUse");
			countSql.append(" and icStangasden.inUse = :inUse");
			parameterMap.put("inUse",icStangasden.getInUse());
		}

		Query dataQuery = entityManager.createQuery(dataSql.toString());
		Query countQuery = entityManager.createQuery(countSql.toString());

		for (String parameter : parameterMap.keySet()) {
			dataQuery.setParameter(parameter, parameterMap.get(parameter));
			countQuery.setParameter(parameter, parameterMap.get(parameter));
		}

		long count = (long) countQuery.getResultList().get(0);
		if (null != pageable) {
			int skip = icStangasden.getSkip();
			dataQuery.setFirstResult(skip);
			dataQuery.setMaxResults(pageable.getPageSize());
			return new MyPageImpl(dataQuery.getResultList(), pageable, count);
		} else {
			return new MyPageImpl(dataQuery.getResultList(), null, count);
		}

	}

}
