/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: StaalgrConfDaoImpl
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
import com.pcitc.fms.service.model.StaalgrConf;

import pcitc.imp.common.ettool.utils.ObjectConverter;

/**
 * The type Staalgr conf dao.
 */
@Service
public class StaalgrConfDaoImpl {

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
	private String createSql(StaalgrConf model, Map<String, Object> parameterMap) {
		String dataSql = AreaNodeBasicSql.staalgrConf;
		if(model.getUnitCode()!=null) {
			dataSql+=" and p.plantCode='"+model.getUnitCode()+"' and p.plantId=a.equipId";
		}
		if(model.getMonLevelId()!=null){
			dataSql+=" and a.monLevelId="+"'"+model.getMonLevelId()+"'";
		}
		if(model.getStaalgrId()!=null){
			dataSql+=" and a.staalgrId="+"'"+model.getStaalgrId()+"'";
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
		dataSql +=" order by a.staalgrConfId";
		return dataSql;
	}

	/**
	 * Find page staalgr confs page.
	 *
	 * @param staalgrConf the staalgr conf
	 * @param pageable the pageable
	 * @return the page
	 */
	@SuppressWarnings("unchecked")
	public Page<com.pcitc.fms.dal.pojo.StaalgrConf> findPageStaalgrConfs(StaalgrConf staalgrConf, Pageable pageable) {
		Map<String ,Object> parameterMap = new HashedMap();
		String dataSql = createSql(staalgrConf, parameterMap);
		Query dataQuery = em.createQuery(dataSql);
		for (String parameter : parameterMap.keySet()) {
			dataQuery.setParameter(parameter, parameterMap.get(parameter));
		}
		Integer skip = null;
		List<com.pcitc.fms.dal.pojo.StaalgrConf> list = dataQuery.getResultList();
  		long count = list.size();
		if(null != pageable){
		    skip = staalgrConf.getSkip();
            dataQuery = em.createQuery(dataSql);
            dataQuery.setFirstResult(skip);
            dataQuery.setMaxResults(pageable.getPageSize());
            return new  PageImpl<com.pcitc.fms.dal.pojo.StaalgrConf>(dataQuery.getResultList(), pageable, count);
            
		}
		return new PageImpl<com.pcitc.fms.dal.pojo.StaalgrConf>(list, pageable, count);

	}


}
