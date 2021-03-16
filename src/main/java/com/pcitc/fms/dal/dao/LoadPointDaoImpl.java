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
import org.springframework.data.domain.Pageable;

import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.common.RentCondition;
import com.pcitc.fms.common.SortParam;
import com.pcitc.fms.config.AreaNodeBasicSql;
import com.pcitc.fms.dal.pojo.LoadPoint;
import com.pcitc.imp.common.exception.BusiException;

public class LoadPointDaoImpl {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public Page<LoadPoint> findLoadPoints(com.pcitc.fms.service.model.LoadPoint loadPoint, Pageable pageable)  throws BusiException{
		String loadPoints = "select count(1) from LoadPoint loadPoint,LoadPointType loadPointType,NodeDictionary node,NodeType nodeType,LoadingDock loadRack,AreaDictionary area,TPmOrg org "
				+ "where loadPoint.nodeId = node.nodeId and loadPoint.loadPointTypeId = loadPointType.loadPointTypeId "
				+ "and node.nodeTypeId = nodeType.nodeTypeId and node.areaId = loadRack.loadingDockId and loadRack.loadingDockId = area.areaDictionaryId and area.factoryId = org.orgId ";

		StringBuilder countSql = new StringBuilder();
		countSql.append(loadPoints);

		StringBuilder dataSql = new StringBuilder();
		dataSql.append(AreaNodeBasicSql.loadPoints);
		Map<String, Object> parameterMap = new HashedMap();

		if (null != loadPoint.getOrgAlias() && !StringUtils.isEmpty(loadPoint.getOrgAlias())) {
			dataSql.append(" and org.orgAlias like :orgAlias");
			countSql.append(" and org.orgAlias like :orgAlias");
			parameterMap.put("orgAlias", "%" + loadPoint.getOrgAlias() + "%");
		}

		if (null != loadPoint.getNodeCode() && !StringUtils.isEmpty(loadPoint.getNodeCode())) {
			dataSql.append(" and node.nodeCode = :nodeCode");
			countSql.append(" and node.nodeCode = :nodeCode");
			parameterMap.put("nodeCode", loadPoint.getNodeCode());
		}
		if (null != loadPoint.getNodeName() && !StringUtils.isEmpty(loadPoint.getNodeName())) {
			dataSql.append(" and node.nodeName like :nodeName");
			countSql.append(" and node.nodeName like :nodeName");
			parameterMap.put("nodeName", "%" + loadPoint.getNodeName() + "%");
		}
		if (null != loadPoint.getNodeAlias() && !StringUtils.isEmpty(loadPoint.getNodeAlias())) {
			dataSql.append(" and node.nodeAlias like :nodeAlias");
			countSql.append(" and node.nodeAlias like :nodeAlias");
			parameterMap.put("nodeAlias", "%" + loadPoint.getNodeAlias() + "%");
		}
		if (null != loadPoint.getAreaAlias() && !StringUtils.isEmpty(loadPoint.getAreaAlias())) {
			dataSql.append(" and area.shortName like :areaAlias");
			countSql.append(" and area.shortName like :areaAlias");
			parameterMap.put("areaAlias", "%" + loadPoint.getAreaAlias() + "%");
		}
		if (null != loadPoint.getInUse() && !StringUtils.isEmpty(String.valueOf(loadPoint.getInUse()))) {
			dataSql.append(" and node.dataStatus = :inUse");
			countSql.append(" and node.dataStatus = :inUse");
			parameterMap.put("inUse", loadPoint.getInUse());
		}
		if (null != loadPoint.getAreaCodes()) {
			if(!loadPoint.getAreaCodes().isEmpty()){
				dataSql.append(" and area.areaCode in :areaCodes ");
				countSql.append(" and area.areaCode in :areaCodes ");
				parameterMap.put("areaCodes", loadPoint.getAreaCodes());
			}
		}
		if (null != loadPoint.getCodeList() && loadPoint.getCodeList().size() > 0) {
			dataSql.append(" and node.nodeCode in :nodeCodes");
			countSql.append(" and node.nodeCode in :nodeCodes");
			parameterMap.put("nodeCodes", loadPoint.getCodeList());
		}
		//----------处理租户过滤
		if(StringUtils.isNotEmpty(loadPoint.getRentCode())){
			RentCondition<LoadPoint> rentCondition = new RentCondition<LoadPoint>();
			String field=" org.orgCode ";
			String rentOrgCodes = rentCondition.getRentOrgCodeCondition(loadPoint.getRentCode(),loadPoint.getBizCode(),field);
			if(StringUtils.isNotEmpty(rentOrgCodes)){
				dataSql.append( " and "+rentOrgCodes);
				countSql.append( " and "+rentOrgCodes);
			}else{
				return new MyPageImpl(new ArrayList(), null, 0L);
			}
		}
		//----------处理租户过滤
		
		if (StringUtils.isNotEmpty(loadPoint.getOrderby())) {
			String value = SortParam.getSortParam(LoadPoint.class, loadPoint.getOrderby());
			dataSql.append(value);
		}
		String param = dataSql.toString();
		Query dataQuery = em.createQuery(param);
		Query countQuery = em.createQuery(countSql.toString());
		
		
		for (String key  : parameterMap.keySet()) {
			dataQuery.setParameter(key, parameterMap.get(key));
			countQuery.setParameter(key, parameterMap.get(key));
		}
		long count = (long) countQuery.getResultList().get(0);
		if (pageable != null) {
			dataQuery.setFirstResult(loadPoint.getSkip());
			dataQuery.setMaxResults(pageable.getPageSize());
		}
		List<com.pcitc.fms.dal.pojo.SideLine> list = dataQuery.getResultList();
		MyPageImpl myPageImpl=new MyPageImpl(list, pageable, count);
		myPageImpl.setCount(count);
		return myPageImpl;
	}
}
