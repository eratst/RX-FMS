package com.pcitc.fms.dal.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Pageable;

import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.common.RentCondition;
import com.pcitc.fms.common.SortParam;
import com.pcitc.fms.config.AreaNodeBasicSql;
import com.pcitc.fms.dal.pojo.TeamAndUser;
import com.pcitc.imp.common.exception.BusiException;

public class NodeTopDTLRepositoryImpl {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public MyPageImpl getNodeTopDTLs(com.pcitc.fms.service.model.NodeTopDTL nodeTopDTL, Pageable pageable) throws BusiException {

		String nodeTopDtls = "select count(1) from NodeTopDTL n,NodeTopMain nm,NodeDictionary snd,NodeDictionary tnd,AreaDictionary area,TPmOrg org where n.topId=nm.topId and n.tnodeId=tnd.nodeId and n.snodeId=snd.nodeId and snd.areaId=area.areaDictionaryId and area.factoryId=org.orgId ";
		StringBuilder dataSql = new StringBuilder();
		dataSql.append(AreaNodeBasicSql.nodeTopDTLnew);

		StringBuilder countSql = new StringBuilder();
		countSql.append(nodeTopDtls);

		Map<String, Object> parameterMap = new HashMap<>();
		if (null != nodeTopDTL.getDataCode() && !StringUtils.isEmpty(nodeTopDTL.getDataCode())) {
			countSql.append(" and n.dataCode = :dataCode");
			dataSql.append(" and n.dataCode = :dataCode");
			parameterMap.put("dataCode", nodeTopDTL.getDataCode());
		}
		if (null != nodeTopDTL.getTopCode() && !StringUtils.isEmpty(nodeTopDTL.getTopCode())) {
			countSql.append(" and nm.topCode = :topCode");
			dataSql.append(" and nm.topCode = :topCode");
			parameterMap.put("topCode", nodeTopDTL.getTopCode());
		}

		if (null != nodeTopDTL.getSnodeCode() && !StringUtils.isEmpty(nodeTopDTL.getSnodeCode())) {
			countSql.append(" and snd.nodeCode = :nodeCode");
			dataSql.append(" and snd.nodeCode = :nodeCode");
			parameterMap.put("nodeCode", nodeTopDTL.getSnodeCode());
		}
		if (null != nodeTopDTL.getTopName() && !StringUtils.isEmpty(nodeTopDTL.getTopName())) {
			countSql.append(" and nm.topName like :topName");
			dataSql.append(" and nm.topName like :topName");
			parameterMap.put("topName", "%" + nodeTopDTL.getTopName() + "%");
		}

		if (null != nodeTopDTL.getCodeList() && nodeTopDTL.getCodeList().size() > 0) {
			countSql.append(" and n.dataCode in :dataCode");
			dataSql.append(" and n.dataCode in :dataCode");
			parameterMap.put("dataCode", nodeTopDTL.getCodeList());
		}

		//----------处理租户过滤
		if(StringUtils.isNotEmpty(nodeTopDTL.getRentCode())){
			RentCondition<TeamAndUser> rentCondition = new RentCondition<TeamAndUser>();
			String field=" org1.orgCode ";
			String rentOrgCodes = rentCondition.getRentOrgCodeCondition(nodeTopDTL.getRentCode(),nodeTopDTL.getBizCode(),field);
			if(StringUtils.isNotEmpty(rentOrgCodes)){
				dataSql.append( " and "+rentOrgCodes);
				countSql.append( " and "+rentOrgCodes);
			}else{
				return new MyPageImpl(new ArrayList(), null, 0L);
			}
		}
		//----------处理租户过滤
		
		if (StringUtils.isNotEmpty(nodeTopDTL.getOrderby())) {
			String value = SortParam.getSortParam(TeamAndUser.class, nodeTopDTL.getOrderby());
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
			int skip = nodeTopDTL.getSkip();
			dataQuery.setFirstResult(skip);
			dataQuery.setMaxResults(pageable.getPageSize());
		} 
		List<com.pcitc.fms.dal.pojo.NodeTopDTL> list = dataQuery.getResultList();
		MyPageImpl myPageImpl=new MyPageImpl(list, pageable, count);
		myPageImpl.setCount(count);
		return myPageImpl;
		

	}
}
