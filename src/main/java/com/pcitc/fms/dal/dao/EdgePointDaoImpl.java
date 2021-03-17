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

import com.pcitc.fms.common.CheckUtil;
import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.common.RentCondition;
import com.pcitc.fms.common.SortParam;
import com.pcitc.fms.config.AreaNodeBasicSql;
import com.pcitc.fms.dal.pojo.EdgePoint;
import com.pcitc.fms.dal.pojo.Equipment;
import com.pcitc.fms.dal.pojo.OrgRelation;
import com.pcitc.fms.dal.pojo.Tank;
import com.pcitc.fms.dal.pojo.TankArea;
import com.pcitc.fms.dal.pojo.Tee;
import com.pcitc.imp.common.exception.BusiException;

/**
 * The type Edge point dao.
 */
@Service
public class EdgePointDaoImpl {

	/**
	 * The Em.
	 */
	@PersistenceContext
	private EntityManager em;

	/**
	 * Gets edge point by model.
	 *
	 * @param edgePointModel the edge point model
	 * @param pageable the pageable
	 * @return the edge point by model
	 * @throws BusiException 
	 */
	@SuppressWarnings("unchecked")
	public MyPageImpl getEdgePointByModel(
			com.pcitc.fms.service.model.EdgePoint edgePointModel,
			Pageable pageable) throws BusiException {
		Map<String, Object> parameterMap = new HashedMap();
		String sql = "";
		String sql_count="select count(1) from EdgePoint a,NodeDictionary b,NodeType c,TransType d,AreaDictionary e,TPmOrg org  where "
								   + " a.nodeCode = b.nodeCode and b.nodeTypeId = c.nodeTypeId and a.transTypeId = d.transTypeId and b.areaId = e.areaDictionaryId and org.orgId=e.factoryId ";
		sql += AreaNodeBasicSql.edgePoints;
		 if(edgePointModel.getInUse()!=null){
		 sql+=" and b.dataStatus="+"'"+edgePointModel.getInUse()+"'";
		 sql_count+=" and b.dataStatus="+"'"+edgePointModel.getInUse()+"'";
		 }
		 if(edgePointModel.getNodeName()!=null){
		 sql+=" and b.nodeName like"+"'%"+edgePointModel.getNodeName()+"%'";
		 sql_count+=" and b.nodeName like"+"'%"+edgePointModel.getNodeName()+"%'";
		 
		 }
		 if(edgePointModel.getNodeAlias()!=null){
		 sql+=" and b.nodeAlias like"+"'%"+edgePointModel.getNodeAlias()+"%'";
		 sql_count+=" and b.nodeAlias like"+"'%"+edgePointModel.getNodeAlias()+"%'";
		 }
		if(null != edgePointModel.getAreaCode() && !StringUtils.isEmpty(edgePointModel.getAreaCode())){
			sql +=" and e.areaCode ="+"'"+edgePointModel.getAreaCode()+"'";
			sql_count +=" and e.areaCode ="+"'"+edgePointModel.getAreaCode()+"'";
		}
		if (null != edgePointModel.getTransTypeCode()
				&& !StringUtils.isEmpty(edgePointModel.getTransTypeCode())) {
			sql += " and d.transTypeCode = :transTypeCode";
			sql_count += " and d.transTypeCode = :transTypeCode";
			parameterMap.put("transTypeCode", edgePointModel.getTransTypeCode());
		}
		if (null != edgePointModel.getNodeCode()
				&& !StringUtils.isEmpty(edgePointModel.getNodeCode())) {
			sql += " and a.nodeCode = :nodeCode";
			sql_count += " and a.nodeCode = :nodeCode";
			parameterMap.put("nodeCode", edgePointModel.getNodeCode());
		}
		if (null != edgePointModel.getNodeCodes()
				&& edgePointModel.getNodeCodes().size() > 0) {
			sql += " and a.nodeCode in :nodeCodes";
			sql_count += " and a.nodeCode in :nodeCodes";
			parameterMap.put("nodeCodes", edgePointModel.getNodeCodes());
		}
		if (null != edgePointModel.getAreaCodes()
				&& edgePointModel.getAreaCodes().size() > 0) {
			sql += " and e.areaCode in :areaCodes";
			sql_count += " and e.areaCode in :areaCodes";
			parameterMap.put("areaCodes", edgePointModel.getAreaCodes());
		}
		
		//----------处理租户过滤
		if(StringUtils.isNotEmpty(edgePointModel.getRentCode())){
			RentCondition<EdgePoint> rentCondition = new RentCondition<EdgePoint>();
			String field=" org.orgCode ";
			String rentOrgCodes = rentCondition.getRentOrgCodeCondition(edgePointModel.getRentCode(),edgePointModel.getBizCode(),field);
			if(StringUtils.isNotEmpty(rentOrgCodes)){
				sql +=" and "+rentOrgCodes;
				sql_count +=" and "+rentOrgCodes;
			}else{
				return new MyPageImpl(new ArrayList(), null, 0L);
			}
		}
		//----------处理租户过滤
		
		if (StringUtils.isNotEmpty(edgePointModel.getOrderby())) {
			String value = SortParam.getSortParam(EdgePoint.class, edgePointModel.getOrderby());
			sql += value;
		}
		
		
		Query dataQuery = em.createQuery(sql);
		Query countQuery = em.createQuery(sql_count);
		for (String parameter : parameterMap.keySet()) {
			dataQuery.setParameter(parameter, parameterMap.get(parameter));
			countQuery.setParameter(parameter, parameterMap.get(parameter));
		}
		long count = (long)countQuery.getResultList().get(0);
		int skip = edgePointModel.getSkip();
		dataQuery.setFirstResult(skip);
		if (null != pageable) {
			dataQuery.setMaxResults(pageable.getPageSize());
		}
		
		List<com.pcitc.fms.dal.pojo.EdgePoint> list = dataQuery.getResultList();
		MyPageImpl myPageImpl=new MyPageImpl(list, pageable, count);
		myPageImpl.setCount(count);
		return myPageImpl;
	}
}
