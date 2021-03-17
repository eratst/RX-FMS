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
import com.pcitc.fms.dal.pojo.PrdtcellMeasindex;
import com.pcitc.fms.dal.pojo.SamplePoint;
import com.pcitc.fms.dal.pojo.TPmBizOrgDTL;
import com.pcitc.imp.common.exception.BusiException;

@Service
public class PrdtcellMeasindexDaoImpl {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public MyPageImpl getPrdtcellMeasindexs(
			com.pcitc.fms.service.model.PrdtcellMeasindex prdtcellMeasindex, Pageable pageable) throws BusiException {
		String prdtcellMeasindexs = " select count(1) from PrdtcellMeasindex prdtcellMeasindex, IdxType idxType, Prdtcell prdtcell, Dimension dimension, AreaDictionary area, Plant plant, TPmOrg org "
				+ "where prdtcellMeasindex.idxTypeId = idxType.idxTypeId and prdtcellMeasindex.cellId = prdtcell.cellId and prdtcellMeasindex.dimensionId = dimension.dimensionId and "
				+ "org.orgId = area.factoryId and area.areaDictionaryId = plant.plantId and plant.plantId = prdtcell.areaId ";
		StringBuilder dataSql = new StringBuilder();
		dataSql.append(AreaNodeBasicSql.prdtcellMeasindexs);

		StringBuilder countSql = new StringBuilder();
		countSql.append(prdtcellMeasindexs);

		Map<String, Object> parameterMap = new HashedMap();
		if (null != prdtcellMeasindex.getIdxCode() && !StringUtils.isEmpty(prdtcellMeasindex.getIdxCode())) {
			countSql.append(" and prdtcellMeasindex.idxCode = :idxCode");
			dataSql.append(" and prdtcellMeasindex.idxCode = :idxCode");
			parameterMap.put("idxCode", prdtcellMeasindex.getIdxCode());
		}
		if (null != prdtcellMeasindex.getIdxName() && !StringUtils.isEmpty(prdtcellMeasindex.getIdxName())) {
			countSql.append(" and prdtcellMeasindex.idxName like :idxName");
			dataSql.append(" and prdtcellMeasindex.idxName like :idxName");
			parameterMap.put("idxName", "%" + prdtcellMeasindex.getIdxName() + "%");
		}
		if (null != prdtcellMeasindex.getIdxAbbrName() && !StringUtils.isEmpty(prdtcellMeasindex.getIdxAbbrName())) {
			countSql.append(" and prdtcellMeasindex.idxAbbrName like :idxAlias");
			dataSql.append(" and prdtcellMeasindex.idxAbbrName like :idxAlias");
			parameterMap.put("idxAlias", "%" + prdtcellMeasindex.getIdxAbbrName() + "%");
		}
		if (null != prdtcellMeasindex.getIdxTypeCode() && !StringUtils.isEmpty(prdtcellMeasindex.getIdxTypeCode())) {
			countSql.append(" and idxType.idxTypeCode like :idxTypeCode");
			dataSql.append(" and idxType.idxTypeCode like :idxTypeCode");
			parameterMap.put("idxTypeCode", "%" + prdtcellMeasindex.getIdxTypeCode() + "%");
		}
		if (null != prdtcellMeasindex.getDimensionAbbrName()
				&& !StringUtils.isEmpty(prdtcellMeasindex.getDimensionAbbrName())) {
			countSql.append(" and dimension.dimensionAlias like :dimensionAlias");
			dataSql.append(" and dimension.dimensionAlias like :dimensionAlias");
			parameterMap.put("dimensionAlias", "%" + prdtcellMeasindex.getDimensionAbbrName() + "%");
		}
		if (null != prdtcellMeasindex.getInUse() && !StringUtils.isEmpty(prdtcellMeasindex.getInUse().toString())) {
			countSql.append(" and prdtcellMeasindex.inUse = :inUse");
			dataSql.append(" and prdtcellMeasindex.inUse = :inUse");
			parameterMap.put("inUse", prdtcellMeasindex.getInUse());
		}
		if (null != prdtcellMeasindex.getCodeList() && prdtcellMeasindex.getCodeList().size() > 0) {
			countSql.append(" and prdtcellMeasindex.idxCode in :codeList");
			dataSql.append(" and prdtcellMeasindex.idxCode in :codeList");
			parameterMap.put("codeList", prdtcellMeasindex.getCodeList());
		}
		
		if (null != prdtcellMeasindex.getCellCodes() && prdtcellMeasindex.getCellCodes().size() > 0) {
			countSql.append(" and prdtcell.cellCode in :cellCodes");
			dataSql.append(" and prdtcell.cellCode in :cellCodes");
			parameterMap.put("cellCodes", prdtcellMeasindex.getCellCodes());
		}
		if (StringUtils.isNoneEmpty(prdtcellMeasindex.getCellCode())) {
			countSql.append(" and prdtcell.cellCode = :cellCode");
			dataSql.append(" and prdtcell.cellCode = :cellCode");
			parameterMap.put("cellCode", prdtcellMeasindex.getCellCode());
		}
		
		//----------处理租户过滤
		if(StringUtils.isNotEmpty(prdtcellMeasindex.getRentCode())){
			RentCondition<SamplePoint> rentCondition = new RentCondition<SamplePoint>();
			String field=" org.orgCode ";
			String rentOrgCodes = rentCondition.getRentOrgCodeCondition(prdtcellMeasindex.getRentCode(),prdtcellMeasindex.getBizCode(),field);
			if(StringUtils.isNotEmpty(rentOrgCodes)){
				dataSql.append( " and "+rentOrgCodes);
				countSql.append( " and "+rentOrgCodes);
			}else{
				return new MyPageImpl(new ArrayList(), null, 0L);
			}
		}
		//----------处理租户过滤	
		
		if (StringUtils.isNotEmpty(prdtcellMeasindex.getOrderby())) {
			String value = SortParam.getSortParam(PrdtcellMeasindex.class, prdtcellMeasindex.getOrderby());
			dataSql.append(value);
		}
		
		
		Query dataQuery = em.createQuery(dataSql.toString());
		Query countQuery = em.createQuery(countSql.toString());
		for (String parameter : parameterMap.keySet()) {
			countQuery.setParameter(parameter, parameterMap.get(parameter));
			dataQuery.setParameter(parameter, parameterMap.get(parameter));
		}

		long count = (long) countQuery.getResultList().get(0);
		if (null != pageable) {
			int skip = prdtcellMeasindex.getSkip();
			dataQuery.setFirstResult(skip);
			dataQuery.setMaxResults(pageable.getPageSize());
		}
		List<com.pcitc.fms.dal.pojo.PrdtcellMeasindex> resultList = dataQuery.getResultList();
		MyPageImpl myPageImpl=new MyPageImpl(resultList, pageable, count);
		myPageImpl.setCount(count);
		return myPageImpl;
	}
}
