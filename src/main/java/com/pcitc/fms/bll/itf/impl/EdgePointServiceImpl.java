/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: EdgePointServiceImpl
 * Date:18-3-8 下午6:21
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.bll.itf.impl;

import com.pcitc.fms.bll.entity.EdgePoint;
import com.pcitc.fms.bll.itf.EdgePointService;
import com.pcitc.fms.common.CheckError;
import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.dal.dao.AreaDictionaryDao;
import com.pcitc.fms.dal.dao.ConnectionsDao;
import com.pcitc.fms.dal.dao.DbPrimaryIdDao;
import com.pcitc.fms.dal.dao.EdgePointDao;
import com.pcitc.fms.dal.dao.FactoryDao;
import com.pcitc.fms.dal.dao.InclusionDao;
import com.pcitc.fms.dal.dao.LoadingDockDao;
import com.pcitc.fms.dal.dao.NodeDictionaryDao;
import com.pcitc.fms.dal.pojo.AreaDictionary;
import com.pcitc.fms.dal.pojo.NodeDictionary;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.exception.BusinessExceptionMessage;
import com.pcitc.fms.service.model.Pager;

import java.lang.reflect.UndeclaredThrowableException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pcitc.imp.common.ettool.utils.ObjectConverter;

/**
 * The type Edge point service.
 */
@Service
public class EdgePointServiceImpl implements EdgePointService {

	/**
	 * The constant log.
	 */
	private static Logger log = LoggerFactory.getLogger(TankServiceImpl.class);
	/**
	 * The Edge point dao.
	 */
	@Autowired
	private EdgePointDao edgePointDao;
	/**
	 * Gets edge points by id list.
	 *
	 * @param parentCode the parent code
	 * @param parentType the parent type
	 * @param idList the id list
	 * @return the edge points by id list
	 * @throws BusinessException the business exception
	 */
	@Override
	public List<com.pcitc.fms.bll.entity.EdgePoint> getEdgePointsByIdList(String parentCode, String parentType, List<Integer> idList) throws BusinessException {
		List<com.pcitc.fms.bll.entity.EdgePoint> edgePointEntityList = new ArrayList<>();
		try{
			List<com.pcitc.fms.dal.pojo.EdgePoint> edgePointPojoList = edgePointDao.getEdgePointsByIdList(idList);
			edgePointEntityList = ObjectConverter.listConverter(edgePointPojoList, com.pcitc.fms.bll.entity.EdgePoint.class);
		}catch(Exception e){
			log.error("fail",e);
			throw  new BusinessException("","",e.getMessage());
		}
		return edgePointEntityList;
	}

	/**
	 * Gets edge point by model.
	 *
	 * @param edgePointModel the edge point model
	 * @param pageable the pageable
	 * @param areaCode the area code
	 * @return the edge point by model
	 * @throws BusinessException the business exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Pager<com.pcitc.fms.bll.entity.EdgePoint> getEdgePointByModel(com.pcitc.fms.service.model.EdgePoint edgePointModel, Pageable pageable) throws BusinessException {
		List<com.pcitc.fms.bll.entity.EdgePoint> edgePointEntitList = new ArrayList<>();
	    Pager<com.pcitc.fms.bll.entity.EdgePoint> edgePointEntitPager = new Pager<>();
	    MyPageImpl edgePointPojoPageList=null;
			
	    try {
	    	edgePointPojoPageList = edgePointDao.getEdgePointByModel(edgePointModel,pageable);
		} catch (Exception e) {
			if (e instanceof UndeclaredThrowableException) {
				throw new BusinessException("", e.getCause().getMessage());
			}
		}
		try {
			edgePointEntitList = ObjectConverter.listConverter(edgePointPojoPageList.getContent(), EdgePoint.class);
		} catch (Exception e) {
			log.error("fail",e);
			throw  new BusinessException("","",e.getMessage());
		}
			edgePointEntitPager.setContent(edgePointEntitList);
			edgePointEntitPager.setFirst(edgePointPojoPageList.isFirst());
			edgePointEntitPager.setLast(edgePointPojoPageList.isLast());
			edgePointEntitPager.setNumber(edgePointPojoPageList.getNumber());
			edgePointEntitPager.setNumberOfElements(edgePointPojoPageList.getNumberOfElements());
			edgePointEntitPager.setSize(edgePointPojoPageList.getSize());
			edgePointEntitPager.setSort(edgePointPojoPageList.getSort());
			edgePointEntitPager.setTotalElements(edgePointPojoPageList.getCount());
			edgePointEntitPager.setTotalPages(edgePointPojoPageList.getTotalPages());
		return edgePointEntitPager;
	}

	/**
	 * Gets edge pointes.
	 *
	 * @param parentCode the parent code
	 * @param parentType the parent type
	 * @return the edge pointes
	 * @throws BusinessException the business exception
	 */
	@Override
    public List<EdgePoint> getEdgePointes(String parentCode, String parentType) throws BusinessException {
        List<com.pcitc.fms.bll.entity.EdgePoint> edgePointEntityList = new ArrayList<>();
        try{
            List<com.pcitc.fms.dal.pojo.EdgePoint> edgePointPojoList = edgePointDao.getEdgePointes(parentCode);
            edgePointEntityList = ObjectConverter.listConverter(edgePointPojoList, com.pcitc.fms.bll.entity.EdgePoint.class);
        }catch(Exception e){
					log.error("fail",e);
					throw  new BusinessException("","",e.getMessage());
        }
        return edgePointEntityList;
    }

	/**
	 * Gets edge pointes by id links.
	 *
	 * @param parentType the parent type
	 * @param parentCode the parent code
	 * @param edgePointCode the edge point code
	 * @return the edge pointes by id links
	 * @throws BusinessException the business exception
	 */
	@Override
    public List<EdgePoint> getEdgePointesByIdLinks(String parentType, String parentCode, String edgePointCode) throws BusinessException {
        List<com.pcitc.fms.bll.entity.EdgePoint> edgePointEntityList = new ArrayList<>();
        try{
            List<com.pcitc.fms.dal.pojo.EdgePoint> edgePointPojoList = edgePointDao.getEdgePointesByCodeLinks(parentCode);
            edgePointEntityList = ObjectConverter.listConverter(edgePointPojoList, com.pcitc.fms.bll.entity.EdgePoint.class);
        }catch(Exception e){
					log.error("fail",e);
					throw  new BusinessException("","",e.getMessage());
        }
        return edgePointEntityList;
    }

	/**
	 * Gets edge pointes by links.
	 *
	 * @param parentType the parent type
	 * @param parentCode the parent code
	 * @return the edge pointes by links
	 * @throws BusinessException the business exception
	 */
	@Override
    public List<EdgePoint> getEdgePointesByLinks(String parentType, String parentCode) throws BusinessException {
        List<com.pcitc.fms.bll.entity.EdgePoint> edgePointEntityList = new ArrayList<>();
        try{
            List<com.pcitc.fms.dal.pojo.EdgePoint> edgePointPojoList = edgePointDao.getEdgePointesByLinks(parentCode);
            edgePointEntityList = ObjectConverter.listConverter(edgePointPojoList, com.pcitc.fms.bll.entity.EdgePoint.class);
        }catch(Exception e){
					log.error("fail",e);
					throw  new BusinessException("","",e.getMessage());
        }
        return edgePointEntityList;
    }

	/**
	 * Gets edge pointes.
	 *
	 * @param parentType the parent type
	 * @return the edge pointes
	 * @throws BusinessException the business exception
	 */
	@Override
    public List<EdgePoint> getEdgePointes(String parentType) throws BusinessException {
        List<com.pcitc.fms.bll.entity.EdgePoint> edgePointEntityList = new ArrayList<>();
        try{
            List<com.pcitc.fms.dal.pojo.EdgePoint> edgePointPojoList = edgePointDao.getEdgePointes(parentType);
            edgePointEntityList = ObjectConverter.listConverter(edgePointPojoList, com.pcitc.fms.bll.entity.EdgePoint.class);
        }catch(Exception e){
					log.error("fail",e);
					throw  new BusinessException("","",e.getMessage());
        }
        return edgePointEntityList;
    }


   

  
	

}
