package com.pcitc.fms.dal.dao;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pcitc.fms.config.AreaNodeBasicSql;
import com.pcitc.fms.dal.pojo.LoadPointType;

@Service
public class LoadPointTypeDaoImpl {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public Page<LoadPointType> findLoadPointTypes(com.pcitc.fms.service.model.LoadPointType loadPointType,
			Pageable pageable) {
		
		String loadPointTypeCountSql = "select count(1) from LoadPointType t where 1=1 ";

		StringBuilder dataSql = new StringBuilder();
		dataSql.append(AreaNodeBasicSql.loadPointType);

		StringBuilder countSql = new StringBuilder();
		countSql.append(loadPointTypeCountSql);
		Map<String, Object> parameterMap = new HashMap<>();
		if (StringUtils.isNotEmpty(loadPointType.getLoadPointTypeCode())) {
			countSql.append(" and t.loadPointTypeCode = :loadPointTypeCode");
			dataSql.append(" and t.loadPointTypeCode = :loadPointTypeCode");
			parameterMap.put("loadPointTypeCode", loadPointType.getLoadPointTypeCode());
		}
		if (StringUtils.isNotEmpty(loadPointType.getLoadPointTypeName())) {
			countSql.append(" and t.loadPointTypeName like :loadPointTypeName");
			dataSql.append(" and t.loadPointTypeName like :loadPointTypeName");
			parameterMap.put("loadPointTypeName", "%" + loadPointType.getLoadPointTypeName() + "%");
		}

		if (null != loadPointType.getInUse()) {
			countSql.append(" and t.inUse = :inUse");
			dataSql.append(" and t.inUse = :inUse");
			parameterMap.put("inUse", loadPointType.getInUse());
		}
		if (null != loadPointType.getCodeList() && loadPointType.getCodeList().size() > 0) {
			countSql.append(" and t.loadPointTypeCode in :codeList");
			dataSql.append(" and t.loadPointTypeCode in :codeList");
			parameterMap.put("orgCodes", loadPointType.getCodeList());
		}

		Query dataQuery = entityManager.createQuery(dataSql.toString());
		Query countQuery = entityManager.createQuery(countSql.toString());
		for (String parameter : parameterMap.keySet()) {
			dataQuery.setParameter(parameter, parameterMap.get(parameter));
			countQuery.setParameter(parameter, parameterMap.get(parameter));

		}

		long count = (long) countQuery.getResultList().get(0);
		if (null != pageable) {
			int skip = loadPointType.getSkip();
			dataQuery.setFirstResult(skip);
			dataQuery.setMaxResults(pageable.getPageSize());
			return new PageImpl<LoadPointType>(dataQuery.getResultList(), pageable, count);
		} else {
			return new PageImpl<LoadPointType>(dataQuery.getResultList(), null, count);
		}
	}

}
