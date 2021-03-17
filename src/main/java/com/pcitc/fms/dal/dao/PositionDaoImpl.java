package com.pcitc.fms.dal.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.config.AreaNodeBasicSql;

@Service
public class PositionDaoImpl {
	
	@PersistenceContext
    private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public MyPageImpl getPositionsByModel(
            com.pcitc.fms.service.model.Position position, Pageable pageable) {
	
		Map<String ,Object> parameterMap = new HashedMap();
		String dataSql = AreaNodeBasicSql.Position;
		
		if(position.getInUse()!=null){
			dataSql+=" and position.inUse="+"'"+position.getInUse()+"'";
		}
		
		if (StringUtils.isNotEmpty(position.getPositionCode())) {
			dataSql+=" and position.positionCode="+"'"+position.getPositionCode()+"'";
		}
		
		if (StringUtils.isNotEmpty(position.getPositionName())) {
			dataSql+=" and position.positionName like %"+"'"+position.getPositionName()+"'%";
		}
		
		if (StringUtils.isNotEmpty(position.getPositionAlias())) {
			dataSql+=" and position.positionAlias like %"+"'"+position.getPositionAlias()+"'%";
		}
		
		if (position.getPositionCodes() != null && !position.getPositionCodes().isEmpty()) {
			dataSql+=" and position.positionCode in "+"'"+position.getPositionCodes()+"'";
		}
		
		Query dataQuery = em.createQuery(dataSql);
		
		for (String parameter : parameterMap.keySet()) {
			dataQuery.setParameter(parameter, parameterMap.get(parameter));
		}
		
		List<com.pcitc.fms.dal.pojo.Position> list = dataQuery.getResultList();
		long count = list.size();
		
		Integer skip = null;
		
		if(null != pageable){
		    skip = position.getSkip();
            dataQuery.setFirstResult(skip);
            dataQuery.setMaxResults(pageable.getPageSize());
            
            MyPageImpl myPageImpl =new MyPageImpl(dataQuery.getResultList(), pageable,count);
            myPageImpl.setCount(count);
            return myPageImpl;
		} else {
			 MyPageImpl myPageImpl =new MyPageImpl(dataQuery.getResultList(), null,count);
	         myPageImpl.setCount(count);
	         return myPageImpl;
		}
	}

}
