package com.pcitc.fms.dal.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang.text.StrBuilder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.config.AreaNodeBasicSql;


@Service
public class IcMtrlFormCnfgDaoImpl {

	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public MyPageImpl findIcMtrlFormCnfgs(com.pcitc.fms.service.model.IcMtrlFormCnfg icMtrlFormCnfg, Pageable pageable) {
		
		StrBuilder dataSql = new StrBuilder();
		dataSql.append(AreaNodeBasicSql.IcMtrlFormCnfg);

		String datacount = "select count(1) from IcMtrlFormCnfg icMtrlFormCnfg ,Tank tank,Material mtrl,NodeDictionary node "
			+ "where icMtrlFormCnfg.mtrlId = mtrl.mtrlId and tank.nodeId = icMtrlFormCnfg.nodeId and node.nodeId = tank.nodeId ";

		StringBuilder countSql = new StringBuilder();
		countSql.append(datacount);

		Map<String, Object> parameterMap = new HashMap<>();
		if (null != icMtrlFormCnfg.getMtrlFormCnfgId()) {
			dataSql.append(" and icMtrlFormCnfg.mtrlFormCnfgId = :mtrlFormCnfgId");
			countSql.append(" and icMtrlFormCnfg.mtrlFormCnfgId = :mtrlFormCnfgId");
			parameterMap.put("mtrlFormCnfgId", icMtrlFormCnfg.getMtrlFormCnfgId());
		}
		if (null != icMtrlFormCnfg.getInUse()) {
			dataSql.append(" and icMtrlFormCnfg.inUse = :inUse");
			countSql.append(" and icMtrlFormCnfg.inUse = :inUse");
			parameterMap.put("inUse",icMtrlFormCnfg.getInUse());
		}
		
		if (StringUtils.isNotEmpty(icMtrlFormCnfg.getMtrlCode())) {
			dataSql.append(" and mtrl.mtrlCode = :mtrlCode");
			countSql.append(" and mtrl.mtrlCode = :mtrlCode");
			parameterMap.put("mtrlCode",icMtrlFormCnfg.getMtrlCode());	
		}
		
		if (StringUtils.isNotEmpty(icMtrlFormCnfg.getMtrlName())) {
			dataSql.append(" and mtrl.mtrlName like :mtrlName");
			countSql.append(" and mtrl.mtrlName like :mtrlName");
			parameterMap.put("mtrlName","%"+icMtrlFormCnfg.getMtrlName()+"%");	
		}
		
		if (StringUtils.isNotEmpty(icMtrlFormCnfg.getNodeCode())) {
			dataSql.append(" and node.nodeCode = :nodeCode");
			countSql.append(" and node.nodeCode = :nodeCode");
			parameterMap.put("nodeCode",icMtrlFormCnfg.getNodeCode());	
		}
		
		if (StringUtils.isNotEmpty(icMtrlFormCnfg.getNodeName())) {
			dataSql.append(" and node.nodeName like :nodeName");
			countSql.append(" and node.nodeName like :nodeName");
			parameterMap.put("nodeName","%"+icMtrlFormCnfg.getNodeName()+"%");	
		}


		Query dataQuery = entityManager.createQuery(dataSql.toString());
		Query countQuery = entityManager.createQuery(countSql.toString());

		for (String parameter : parameterMap.keySet()) {
			dataQuery.setParameter(parameter, parameterMap.get(parameter));
			countQuery.setParameter(parameter, parameterMap.get(parameter));
		}

		long count = (long) countQuery.getResultList().get(0);
		if (null != pageable) {
			int skip = icMtrlFormCnfg.getSkip();
			dataQuery.setFirstResult(skip);
			dataQuery.setMaxResults(pageable.getPageSize());
		} 
		List<com.pcitc.fms.dal.pojo.IcMtrlFormCnfg> resultList = dataQuery.getResultList();
		MyPageImpl myPageImpl=new MyPageImpl(resultList, pageable, count);
		myPageImpl.setCount(count);
		return myPageImpl;	

	}
}
