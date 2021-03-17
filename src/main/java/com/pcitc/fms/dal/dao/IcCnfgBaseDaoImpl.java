package com.pcitc.fms.dal.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageImpl;

import com.pcitc.fms.dal.pojo.CnfgTank;
import com.pcitc.fms.dal.pojo.IcCnfgBase;

public class IcCnfgBaseDaoImpl {

	@PersistenceContext
	private EntityManager em;
	
	public List<IcCnfgBase> findIcCnfgBases(String nodeCode, List<String> codeList){
		
		final String cnfgBase1 = "select distinct new IcCnfgBase(icCnfgBase.bCnfgBaseId,"
				+ " icCnfgBase.bAreaId,icCnfgBase.bCnfgClassId,icCnfgBase.bClassParaId,"
				+ " icCnfgBase.bFormula,cnfgClass.tankType, cnfgClass.cnfgClassCode,cnfgClass.sortNum,"
				+ "cnfgClassPara.cnfgParaValue,cnfgClassPara.isUseFormula,node.nodeCode)"
			
				+ " from IcCnfgBase icCnfgBase,CnfgClass cnfgClass,CnfgClassPara cnfgClassPara,"
				+ " NodeDictionary node,Tank tank where "
				+ " icCnfgBase.bAreaId = node.areaId and "
				+ " tank.nodeId = node.nodeId  and icCnfgBase.bClassParaId = cnfgClassPara.classParaId "
				+ " and cnfgClass.cnfgClassId = cnfgClassPara.cnfgClassId ";
	
		Map<String, Object> parameterMap = new HashedMap();
		StringBuffer dataSql = new StringBuffer();
//		StringBuffer countSql = new StringBuffer();

		dataSql.append(cnfgBase1);
//		countSql.append(cnfgTankCount);

		if (null != nodeCode && !StringUtils.isEmpty(nodeCode)) {
//			countSql.append(" and node.nodeCode = :nodeCode");
			dataSql.append(" and node.nodeCode = :nodeCode");
			parameterMap.put("nodeCode", nodeCode);
		}
		if (null != codeList && !codeList.isEmpty()) {
			dataSql.append(" and node.nodeCode in :nodeCodeList");
			parameterMap.put("nodeCodeList", codeList);
		}
		

//		dataSql.append(" order by node.nodeCode asc,cnfgClass.sortNum asc");
//		countSql.append(" order by node.nodeCode asc,cnfgClass.sortNum asc");

		Query dataQuery = em.createQuery(dataSql.toString());
//		Query countQuery = em.createQuery(countSql.toString());
		for (String parameter : parameterMap.keySet()) {
//			countQuery.setParameter(parameter, parameterMap.get(parameter));
			dataQuery.setParameter(parameter, parameterMap.get(parameter));
		}

		List baseList = dataQuery.getResultList();
		return baseList;
		
	}
}
