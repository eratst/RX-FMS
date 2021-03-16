/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: FactorySiteDaoImpl
 * Date:18-3-9 上午8:34
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.dal.dao;

import com.pcitc.fms.exception.BusinessException;
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
import com.pcitc.fms.dal.pojo.AdministrationArea;
import com.pcitc.fms.service.model.Administration;
import com.pcitc.fms.service.model.FactorySite;

import pcitc.imp.common.ettool.utils.ObjectConverter;

/**
 * The type Factory site dao.
 */
@Service
public class FactorySiteDaoImpl {

	/**
	 * The Em.
	 */
	@PersistenceContext
    private EntityManager em;

	/**
	 * Find page factory sites page.
	 *
	 * @param model the model
	 * @param pageable the pageable
	 * @return the page
	 * @throws BusinessException the business exception
	 */
	@SuppressWarnings("unchecked")
	public Page<com.pcitc.fms.dal.pojo.FactorySiteArea> findPageFactorySites(FactorySite model, Pageable pageable) throws BusinessException {
//		Map<String ,Object> parameterMap = new HashedMap();
//		String dataSql = AreaNodeBasicSql.factorySite+" where 1 = 1 ";
//		if(model.getFactoryId()!=null){
//			dataSql+=" and ad.factoryId="+"'"+model.getFactoryId()+"'";
//		}
//		if(model.getOrgId()!=null){
//			dataSql+=" and ad.orgId="+"'"+model.getOrgId()+"'";
//		}
//		if(model.getEnabled()!=null){
//			dataSql+=" and ad.enabled="+"'"+model.getEnabled()+"'";
//		}
//		if(model.getName()!=null){
//			dataSql+=" and ad.name like"+"'%"+model.getName()+"%'";
//		}
//		if(model.getShortName()!=null){
//			dataSql+=" and ad.shortName like"+"'%"+model.getShortName()+"%'";
//		}
//		if(null != model.getCodeList() && model.getCodeList().size() > 0){
//			dataSql += " and a.code in :codes";
//            parameterMap.put("codes", model.getCodeList());
//        }
//        if(null != model.getIdList() && model.getIdList().size() > 0){
//        	dataSql += " and ad.areaDictionaryId in :ids";
//            parameterMap.put("ids", model.getIdList());
//        }
//		dataSql +=" and a.factorySiteId = ad.areaDictionaryId order by a.factorySiteId";
//		Query dataQuery = em.createQuery(dataSql);
//		for (String parameter : parameterMap.keySet()) {
//			dataQuery.setParameter(parameter, parameterMap.get(parameter));
//		}
//		Integer skip = null;
//		List<com.pcitc.fms.dal.pojo.FactorySiteArea> list = dataQuery.getResultList();
//		long count = list.size();
//		if(null != pageable){
//		    skip = model.getSkip();
//            dataQuery = em.createQuery(dataSql);
//            dataQuery.setFirstResult(skip);
//            dataQuery.setMaxResults(pageable.getPageSize());
//            return new  PageImpl<com.pcitc.fms.dal.pojo.FactorySiteArea>(list, pageable, count);
//            
//		}else{
//		    if (null != model && null != model.getTop()) {
//                Integer top = model.getTop();
//                if(top >= 0){
//                    dataQuery.setMaxResults(top);
//                }
//            }
//            if(null != model && null != model.getSkip()){
//                 skip = model.getSkip();
//                if(skip >= 0){
//                    dataQuery.setFirstResult(skip);
//                }
//                dataQuery.setFirstResult(skip);
//            }
//            return new PageImpl<com.pcitc.fms.dal.pojo.FactorySiteArea>(list, pageable, count);
//		}
//
//	}

		return null;
	}
}

