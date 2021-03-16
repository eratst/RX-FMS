/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: SideLineDaoImpl
 * Date:18-3-9 上午8:35
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.dal.dao;

import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.common.RentCondition;
import com.pcitc.fms.common.SortParam;
import com.pcitc.fms.config.AreaNodeBasicSql;
import com.pcitc.fms.dal.pojo.SideLine;
import com.pcitc.fms.dal.pojo.Tank;
import com.pcitc.fms.dal.pojo.Tee;
import com.pcitc.imp.common.exception.BusiException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;


/**
 * The type Side line dao.
 */
public class SideLineDaoImpl {

	/**
	 * The Em.
	 */
	@PersistenceContext
    private EntityManager em;

	/**
	 * Get side line models page.
	 *
	 * @param sideLineModel the side line model
	 * @param pageable the pageable
	 * @return the page
	 * @throws BusiException 
	 */
	@SuppressWarnings("unchecked")
	public MyPageImpl getSideLineModels(com.pcitc.fms.service.model.SideLine sideLineModel,Pageable pageable) throws BusiException{
		String count_sideLine="select count(1) from SideLine a,NodeDictionary b,NodeType c,SidelineType d,SideMtrlType e,AreaDictionary f, TPmOrg org where "
							     + " a.nodeCode = b.nodeCode and b.nodeTypeId = c.nodeTypeId and a.sidelineTypeId = d.sidelineTypeId and a.slineMtrlTypeId = e.sideMtrlTypeId and b.areaId = f.areaDictionaryId and f.factoryId=org.orgId";
		StringBuilder dataSql = new StringBuilder();
		StringBuilder countSql = new StringBuilder();
		Map<String, Object> mapParameter = new HashMap<String, Object>();
		String sql =AreaNodeBasicSql.SideLine;
		dataSql.append(sql);
		countSql.append(count_sideLine);
		if (!StringUtils.isEmpty(sideLineModel.getNodeCode()) && null != sideLineModel.getNodeCode()) {
			dataSql.append(" and a.nodeCode= :nodeCode ");
			countSql.append(" and a.nodeCode= :nodeCode ");
			mapParameter.put("nodeCode", sideLineModel.getNodeCode());
		}
		if (!StringUtils.isEmpty(sideLineModel.getOrgCode()) && null != sideLineModel.getOrgCode()) {
			dataSql.append(" and org.orgCode= :orgCode ");
			countSql.append(" and org.orgCode= :orgCode ");
			mapParameter.put("orgCode", sideLineModel.getOrgCode());
		}
		if (null != sideLineModel && null != sideLineModel.getSlineInOutTypeId()) {
			dataSql.append(" and a.slineInOutTypeId= :slineInOutTypeId ");
			countSql.append(" and a.slineInOutTypeId= :slineInOutTypeId ");
			mapParameter.put("slineInOutTypeId", sideLineModel.getSlineInOutTypeId());
		}
		if (!StringUtils.isEmpty(sideLineModel.getSidelineTypeCode()) && null != sideLineModel.getSidelineTypeCode()) {
			dataSql.append(" and d.sidelineTypeCode= :sidelineTypeCode ");
			countSql.append(" and d.sidelineTypeCode= :sidelineTypeCode ");
			mapParameter.put("sidelineTypeCode", sideLineModel.getSidelineTypeCode());
		}
		if (!StringUtils.isEmpty(sideLineModel.getSlineMtrlTypeCode()) && null != sideLineModel.getSlineMtrlTypeCode()) {
			dataSql.append(" and e.sideMtrlTypeCode= :slineMtrlTypeCode ");
			countSql.append(" and e.sideMtrlTypeCode= :slineMtrlTypeCode ");
			mapParameter.put("slineMtrlTypeCode", sideLineModel.getSlineMtrlTypeCode());
		}
		if (null != sideLineModel.getNodeCodes()) {
			if(!sideLineModel.getNodeCodes().isEmpty() ){
				dataSql.append(" and a.nodeCode in :nodeCodes ");
				countSql.append(" and a.nodeCode in :nodeCodes ");
				mapParameter.put("nodeCodes", sideLineModel.getNodeCodes());
			}
		}
		if (null != sideLineModel.getAreaCodes()) {
			if(!sideLineModel.getAreaCodes().isEmpty()){
				dataSql.append(" and f.areaCode in :areaCodes ");
				countSql.append(" and f.areaCode in :areaCodes ");
				mapParameter.put("areaCodes", sideLineModel.getAreaCodes());
			}
		}
		if (null != sideLineModel.getAreaCode()) {
			if(!sideLineModel.getAreaCode().isEmpty()){
				dataSql.append(" and f.areaCode = :areaCode ");
				countSql.append(" and f.areaCode = :areaCode ");
				mapParameter.put("areaCode", sideLineModel.getAreaCode());
			}
		}
		if(null != sideLineModel.getNodeName() && !StringUtils.isEmpty(sideLineModel.getNodeName())){
			dataSql.append(" and b.nodeName like :nodeName");
			countSql.append(" and b.nodeName like :nodeName");
			mapParameter.put("nodeName", "%"+sideLineModel.getNodeName()+"%");
		}
		if(sideLineModel.getNodeAlias()!=null&&!"".equals(sideLineModel.getNodeAlias())){
			dataSql.append(" and b.nodeAlias like :nodeAlias");
			countSql.append(" and b.nodeAlias like :nodeAlias");
			mapParameter.put("nodeAlias", "%"+sideLineModel.getNodeAlias()+"%");
		}
		if(sideLineModel.getInUse()!=null&&!"".equals(sideLineModel.getInUse())){
			dataSql.append(" and b.dataStatus = :dataStatus");
			countSql.append(" and b.dataStatus = :dataStatus");
			mapParameter.put("dataStatus", sideLineModel.getInUse());
		}
		
		//----------处理租户过滤
		if(StringUtils.isNotEmpty(sideLineModel.getRentCode())){
			RentCondition<SideLine> rentCondition = new RentCondition<SideLine>();
			String field=" org.orgCode ";
			String rentOrgCodes = rentCondition.getRentOrgCodeCondition(sideLineModel.getRentCode(),sideLineModel.getBizCode(),field);
			if(StringUtils.isNotEmpty(rentOrgCodes)){
				dataSql.append( " and "+rentOrgCodes);
				countSql.append( " and "+rentOrgCodes);
			}else{
				return new MyPageImpl(new ArrayList(), null, 0L);
			}
		}
		//----------处理租户过滤
		
		if (StringUtils.isNotEmpty(sideLineModel.getOrderby())) {
			String value = SortParam.getSortParam(SideLine.class, sideLineModel.getOrderby());
			dataSql.append(value);
		}
		String param = dataSql.toString();
		Query dataQuery = em.createQuery(param);
		Query countQuery = em.createQuery(countSql.toString());
		
		
		for (String key  : mapParameter.keySet()) {
			dataQuery.setParameter(key, mapParameter.get(key));
			countQuery.setParameter(key, mapParameter.get(key));
		}
		long count = (long) countQuery.getResultList().get(0);
		if (pageable != null) {
			dataQuery.setFirstResult(sideLineModel.getSkip());
			dataQuery.setMaxResults(pageable.getPageSize());
		}
		List<com.pcitc.fms.dal.pojo.SideLine> list = dataQuery.getResultList();
		MyPageImpl myPageImpl=new MyPageImpl(list, pageable, count);
		myPageImpl.setCount(count);
		return myPageImpl;
	}
	
}
