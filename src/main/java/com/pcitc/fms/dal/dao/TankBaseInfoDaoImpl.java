package com.pcitc.fms.dal.dao;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pcitc.fms.dal.pojo.TankBaseInfo;
@Service
public class TankBaseInfoDaoImpl {

	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public Page<TankBaseInfo> findTankBaseInfos(com.pcitc.fms.service.model.TankBaseInfo tankmo, Pageable pageable){
		
		String tankBaseInfo = "select new TankBaseInfo(t.nodeId, t.nodeCode, nd.nodeName, nd.nodeAlias,"
				+ " tt.tankTypeId,tt.tankTypeCode,tt.tankTypeName,t.tankHgt,"
				+ " ta.tankAreaId,ta.tankAreaCode,ad.name,"
				+ " t.maxTankHgt,t.minTankHgt,t.maxTankStoarge,t.minTankStoarge,t.tankTotlCuba"
				+ " )"
				+ " from Tank t,NodeDictionary nd,TankType tt,"
				+ " TankArea ta,AreaDictionary ad "
				+ " where t.nodeId = nd.nodeId"
				+ " and t.tankTypeId = tt.tankTypeId"
				+ " and nd.nodeId = ad.areaDictionaryId"
				+ " and ad.areaDictionaryId = ta.tankAreaId";
		
		StringBuilder dataSql = new StringBuilder();
		dataSql.append(tankBaseInfo);
		Map<String, Object> parameterMap = new HashMap<>();
		
		if(tankmo.getCodeList() != null && !tankmo.getCodeList().isEmpty()) {
			dataSql.append(" and t.nodeCode in :codeList");
			parameterMap.put("codeList", tankmo.getCodeList());
		}
		Query dataQuery = entityManager.createQuery(dataSql.toString());
		for (String parameter : parameterMap.keySet()) {
			dataQuery.setParameter(parameter, parameterMap.get(parameter));
		}
		long count = dataQuery.getResultList().size();
		if(null != pageable) {
			int skip = tankmo.getSkip();
			dataQuery.setFirstResult(skip);
			dataQuery.setMaxResults(pageable.getPageSize());
			return new PageImpl<TankBaseInfo>(dataQuery.getResultList(),pageable,count);
		}else {
			return new PageImpl<TankBaseInfo>(dataQuery.getResultList(),null,count);
		}
	}
}
