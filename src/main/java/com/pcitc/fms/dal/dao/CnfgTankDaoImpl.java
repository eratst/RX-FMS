package com.pcitc.fms.dal.dao;

import java.util.ArrayList;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.common.RentCondition;
import com.pcitc.fms.dal.pojo.CnfgTank;
import com.pcitc.imp.common.exception.BusiException;

public class CnfgTankDaoImpl {

	@PersistenceContext
	private EntityManager em;
	
	public Page<CnfgTank> findCnfgTanks(com.pcitc.fms.service.model.CnfgTank cnfgTank, Pageable pageable) throws BusiException{
		
		//CnfgTank:罐量计算单罐配置
		//CnfgClass:罐量计算配置基础分类
		//CnfgClassPara:罐量计算配置分类参数
		final String cnfgTank1 = "select new CnfgTank(cnfgTank.cnfgTankId,cnfgTank.nodeId,t.nodeCode,cnfgTank.cnfgClassId,cnfgClass.cnfgClassCode,cnfgClass.sortNum"
				+ ",cnfgClass.tankType,cnfgTank.classParaId,cnfgClassPara.cnfgParaValue,cnfgClassPara.isUseFormula,"
				+ "cnfgTank.formula,cnfgTank.crtUserCode,cnfgTank.crtUserName,cnfgTank.crtDate,cnfgTank.mntUserCode,"
				+ "cnfgTank.mntUserName,cnfgTank.mntDate,cnfgTank.des) "
				+ "from CnfgTank cnfgTank,CnfgClass cnfgClass,CnfgClassPara cnfgClassPara,Tank t,NodeDictionary n,AreaDictionary a,TPmOrg org where"
				+ " cnfgTank.cnfgClassId=cnfgClass.cnfgClassId and cnfgTank.classParaId=cnfgClassPara.classParaId and t.nodeId=cnfgTank.nodeId and a.areaDictionaryId=n.areaId and a.factoryId=org.orgId and n.nodeId=t.nodeId ";
		Map<String, Object> parameterMap = new HashedMap();
		StringBuilder dataSql = new StringBuilder();
		StringBuilder countSql = new StringBuilder();

		dataSql.append(cnfgTank1);

		if (null != cnfgTank.getNodeId()) {
			dataSql.append(" and cnfgTank.nodeId = :nodeId");
			parameterMap.put("nodeId", cnfgTank.getNodeId());
		}
		
		//----------处理租户过滤
		if(StringUtils.isNotEmpty(cnfgTank.getRentCode())){
			RentCondition<CnfgTank> rentCondition = new RentCondition<CnfgTank>();
			String field=" org.orgCode ";
			String rentOrgCodes = rentCondition.getRentOrgCodeCondition(cnfgTank.getRentCode(),cnfgTank.getBizCode(),field);
			if(StringUtils.isNotEmpty(rentOrgCodes)){
				dataSql.append( " and "+rentOrgCodes);
				countSql.append( " and "+rentOrgCodes);
			}else{
				return new MyPageImpl(new ArrayList(), null, 0L);
			}
		}
		//----------处理租户过滤

		dataSql.append(" order by cnfgClass.sortNum asc");

		Query dataQuery = em.createQuery(dataSql.toString());
		for (String parameter : parameterMap.keySet()) {
			dataQuery.setParameter(parameter, parameterMap.get(parameter));
		}
		
		long count = dataQuery.getResultList().size();
		if (null != pageable) {
			int skip = cnfgTank.getSkip();
			dataQuery.setFirstResult(skip);
			dataQuery.setMaxResults(pageable.getPageSize());
			return new PageImpl<CnfgTank>(dataQuery.getResultList(), pageable, count);
		} else {
			return new PageImpl<CnfgTank>(dataQuery.getResultList(), null, count);
		}
		
	}
}
