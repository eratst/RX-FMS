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
import com.pcitc.fms.dal.pojo.Prdtcell;
import com.pcitc.fms.dal.pojo.PrdtcellMeasindex;
import com.pcitc.fms.dal.pojo.ProduceFactory;
import com.pcitc.fms.dal.pojo.SamplePoint;
import com.pcitc.fms.dal.pojo.TPmBizOrgDTL;
import com.pcitc.imp.common.exception.BusiException;

@Service
public class PrdtcellRepositoryImpl {
	@PersistenceContext
	private EntityManager em;
	
	
	@SuppressWarnings("unchecked")
	public MyPageImpl getPrdtcells(com.pcitc.fms.service.model.Prdtcell prdtcell, Pageable pageable) throws BusiException {

		StringBuilder dataSql = new StringBuilder();
		dataSql.append(AreaNodeBasicSql.prdtcells);
		StringBuilder countSql = new StringBuilder();
		Map<String, Object> parameterMap = new HashedMap();
		if (null != prdtcell.getCellCode() && !StringUtils.isEmpty(prdtcell.getCellCode())) {
			dataSql.append(" and prdtcell.cellCode = :cellCode");
			parameterMap.put("cellCode", prdtcell.getCellCode());
		}
		if (null != prdtcell.getCellName() && !StringUtils.isEmpty(prdtcell.getCellName())) {
			dataSql.append(" and prdtcell.cellName like :cellName");
			parameterMap.put("cellName", "%" + prdtcell.getCellName() + "%");
		}
		if (null != prdtcell.getCellAbbrName() && !StringUtils.isEmpty(prdtcell.getCellAbbrName())) {
			dataSql.append(" and prdtcell.cellAbbrName like :cellAbbrName");
			parameterMap.put("cellAbbrName", "%" + prdtcell.getCellAbbrName() + "%");
		}
		if (null != prdtcell.getInUse() && !StringUtils.isEmpty(String.valueOf(prdtcell.getInUse()))) {
			dataSql.append(" and prdtcell.inUse = :inUse");
			parameterMap.put("inUse", prdtcell.getInUse());
		}
		if (null != prdtcell.getCodeList() && !prdtcell.getCodeList().isEmpty()) {
			dataSql.append(" and prdtcell.cellCode in :codeList");
			parameterMap.put("codeList", prdtcell.getCodeList());
		}
		if (null != prdtcell.getPlantCodes() && !prdtcell.getPlantCodes().isEmpty()) {
			dataSql.append(" and plant.plantCode in :plantCodes");
			parameterMap.put("plantCodes", prdtcell.getPlantCodes());
		}
		if (StringUtils.isNotEmpty(prdtcell.getCellCode())) {
			dataSql.append(" and plant.plantCode = :plantCode");
			parameterMap.put("plantCode", prdtcell.getPlantCode());
		}
		
		//----------处理租户过滤
		if(StringUtils.isNotEmpty(prdtcell.getRentCode())){
			RentCondition<Prdtcell> rentCondition = new RentCondition<Prdtcell>();
			String field=" org.orgCode ";
			String rentOrgCodes = rentCondition.getRentOrgCodeCondition(prdtcell.getRentCode(),prdtcell.getBizCode(),field);
			if(StringUtils.isNotEmpty(rentOrgCodes)){
				dataSql.append( " and "+rentOrgCodes);
				countSql.append( " and "+rentOrgCodes);
			}else{
				return new MyPageImpl(new ArrayList(), null, 0L);
			}
		}
		//----------处理租户过滤
		if (StringUtils.isNotEmpty(prdtcell.getOrderby())) {
			String value = SortParam.getSortParam(Prdtcell.class, prdtcell.getOrderby());
			dataSql.append(value);
		}
			
		Query dataQuery = em.createQuery(dataSql.toString());
		for (String parameter : parameterMap.keySet()) {
			dataQuery.setParameter(parameter, parameterMap.get(parameter));
		}
		long count =  (long) dataQuery.getResultList().size();;
		if (null != pageable) {
			int skip = prdtcell.getSkip();
			dataQuery.setFirstResult(skip);
			dataQuery.setMaxResults(pageable.getPageSize());
		}
		List<com.pcitc.fms.dal.pojo.Prdtcell> list = dataQuery.getResultList();
		MyPageImpl myPageImpl=new MyPageImpl(list, pageable, count);
		myPageImpl.setCount(count);
		return myPageImpl;
	}
}
