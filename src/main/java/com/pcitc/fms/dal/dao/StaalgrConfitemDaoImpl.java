/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: StaalgrConfitemDaoImpl
 * Date:18-3-9 上午8:35
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pcitc.fms.config.AreaNodeBasicSql;
import com.pcitc.fms.service.model.Administration;
import com.pcitc.fms.service.model.StaalgrConfitem;

import pcitc.imp.common.ettool.utils.ObjectConverter;

/**
 * The type Staalgr confitem dao.
 */
@Service
public class StaalgrConfitemDaoImpl {

	/**
	 * The Em.
	 */
	@PersistenceContext
    private EntityManager em;


	/**
	 * Create sql string.
	 *
	 * @param model the model
	 * @param parameterMap the parameter map
	 * @return the string
	 */
	private String createSql(StaalgrConfitem model, Map<String, Object> parameterMap) {
		String dataSql = AreaNodeBasicSql.staalgrConfitem;
		if(model.getOpeindexCode()!=null){
			dataSql+=" and op.code="+"'"+model.getOpeindexCode()+"'";
		}
		if(model.getStaalgrConfId()!=null){
			dataSql+=" and a.staalgrConfId="+"'"+model.getStaalgrConfId()+"'";
		}
		if(model.getOpeindexId()!=null){
			dataSql+=" and a.opeindexId="+"'"+model.getOpeindexId()+"'";
		}
		if(model.getAgentCode()!=null){
			dataSql+=" and a.agentCode="+"'"+model.getAgentCode()+"'";
		}
		if(model.getCraftSchemeId()!=null){
			dataSql+=" and a.craftSchemeId="+"'"+model.getCraftSchemeId()+"'";
		}
		if(model.getInUse()!=null){
			dataSql+=" and a.inUse="+"'"+model.getInUse()+"'";
		}
		if(model.getName()!=null){
			dataSql+=" and a.name like"+"'%"+model.getName()+"%'";
		}
		
		if(null != model.getCodeList() && model.getCodeList().size() > 0){
			dataSql += " and a.codeList in :codes";
            parameterMap.put("codes", model.getCodeList());
        }
		dataSql +=" order by a.staalgrConfitemId";
		return dataSql;
	}

	/**
	 * Find page staalgr confitems page.
	 *
	 * @param staalgrConfitem the staalgr confitem
	 * @param pageable the pageable
	 * @return the page
	 */
	@SuppressWarnings("unchecked")
	public Page<com.pcitc.fms.dal.pojo.StaalgrConfitem> findPageStaalgrConfitems(StaalgrConfitem staalgrConfitem, Pageable pageable) {
		Map<String ,Object> parameterMap = new HashedMap();
		String dataSql = createSql(staalgrConfitem, parameterMap);
		Query dataQuery = em.createQuery(dataSql);
		for (String parameter : parameterMap.keySet()) {
			dataQuery.setParameter(parameter, parameterMap.get(parameter));
		}
		Integer skip = null;
		List<com.pcitc.fms.dal.pojo.StaalgrConfitem> list = dataQuery.getResultList();
  		long count = list.size();
		if(null != pageable){
		    skip = staalgrConfitem.getSkip();
            dataQuery = em.createQuery(dataSql);
            dataQuery.setFirstResult(skip);
            dataQuery.setMaxResults(pageable.getPageSize());
            return new  PageImpl<com.pcitc.fms.dal.pojo.StaalgrConfitem>(dataQuery.getResultList(), pageable, count);
            
		}
		return new PageImpl<com.pcitc.fms.dal.pojo.StaalgrConfitem>(list, pageable, count);

	}


}
