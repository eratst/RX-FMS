/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: OpenindexDaoImpl
 * Date:18-3-9 上午8:34
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.dal.dao;

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
import com.pcitc.fms.common.SortParam;
import com.pcitc.fms.config.AreaNodeBasicSql;
import com.pcitc.fms.dal.pojo.Openindex;
import com.pcitc.imp.common.exception.BusiException;

/**
 * The type Openindex dao.
 */
@Service
public class OpenindexDaoImpl{

	/**
	 * The Em.
	 */
	@PersistenceContext
    private EntityManager em;

	/**
	 * Create sql string.
	 *
	 * @param model the model
	 * @return the string
	 */
	private String createSql(com.pcitc.fms.service.model.Openindex model) {
		String dataSql = AreaNodeBasicSql.openindex;
		if(model.getMonLevelId() != null) {
			dataSql+=" ,MonLevel m, Deupdownlimit d, Plant pl where m.monLevelId=d.monLevelId and d.opeindexId=a.openindexId and a.opeindexClassId = op.openindexClassId and a.controlDepId = co.controlDepId and a.measUnitId=di.dimensionId and m.monLevelId="+model.getMonLevelId()+" and pl.plantCode = '"+model.getMcode()+"' and pl.plantId=a.unitId ";
		}
		else if(model.getType().equals("unit")) {
			dataSql+=" ,Plant pl, where a.opeindexClassId = op.openindexClassId and a.controlDepId = co.controlDepId and a.measUnitId=di.dimensionId and pl.plantCode = '"+model.getMcode()+"' and pl.plantId=a.unitId";
		}
		else if(model.getType().equals("equipment")){
			
			dataSql+=" ,Equipment eq where a.opeindexClassId = op.openindexClassId and a.controlDepId = co.controlDepId and a.measUnitId=di.dimensionId and eq.nodeCode = '"+model.getMcode()+"' and eq.nodeId=a.equipId";
		}
		
		dataSql +=" order by a.openindexId";
		return dataSql;
	}

	/**
	 * Find page openindexs page.
	 *
	 * @param openindex the openindex
	 * @param pageable the pageable
	 * @return the page
	 * @throws BusiException 
	 */
	@SuppressWarnings("unchecked")
	public MyPageImpl findPageOpenindexs(com.pcitc.fms.service.model.Openindex openindex, Pageable pageable) throws BusiException {
		Map<String ,Object> parameterMap = new HashedMap();
		String dataSql = AreaNodeBasicSql.openindex;
		StringBuilder sqlSB = new StringBuilder();
		if(openindex.getMonLevelId() != null) {
			dataSql+=" and m.monLevelId= " +"'" +openindex.getMonLevelId() +"'" ;
		}
		if(null != openindex.getType() && openindex.getType().equals("unit")) {
			dataSql+=" and pl.plantCode = "+"'"+openindex.getMcode()+"'";
		}
		if(null != openindex.getType() && openindex.getType().equals("equipment") ){
			
			dataSql+=" and eq.nodeCode =  " +"'" +openindex.getMcode() +"'";
		}
		
		if (StringUtils.isNotEmpty(openindex.getOrderby())){
			String value = SortParam.getSortParam(Openindex.class, openindex.getOrderby());
			dataSql+=value;
		}
		
		
//		String dataSql = createSql(openindex);
		Query dataQuery = em.createQuery(dataSql);
		Integer skip = null;
		long count = dataQuery.getResultList().size();
		if(null != pageable){
		    skip = openindex.getSkip();
            dataQuery.setFirstResult(skip);
            dataQuery.setMaxResults(pageable.getPageSize());
		}
		List<com.pcitc.fms.dal.pojo.Openindex> resultList = dataQuery.getResultList();
		MyPageImpl myPageImpl=new MyPageImpl(resultList, pageable, count);
		myPageImpl.setCount(count);
		return myPageImpl;
	}
}
