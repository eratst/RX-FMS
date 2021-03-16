/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: AreaNodeTypeServiceImpl
 * Date:18-3-8 下午6:20
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.bll.itf.impl;

import com.pcitc.fms.bll.entity.TPmAreaNodeType;
import com.pcitc.fms.bll.itf.AreaNodeTypeService;
import com.pcitc.fms.dal.dao.AreaNodeTypeDao;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.service.model.Pager;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pcitc.imp.common.ettool.utils.ObjectConverter;

/**
 * Title: AreaNodeTypeServiceImpl
 *
 * @author zhenqiang.zhao
 * @version 1.0
 */
@Service
public class AreaNodeTypeServiceImpl implements AreaNodeTypeService {

  /**
   * The constant log.
   */
  private static Logger log = LoggerFactory.getLogger(AreaNodeTypeServiceImpl.class);
  /**
   * The Area node type dao.
   */
  @Autowired
  private AreaNodeTypeDao areaNodeTypeDao;

  /**
   * Gets area node types.
   *
   * @param tpmBizOrgDTLModel the tpm biz org dtl model
   * @param pageable the pageable
   * @return the area node types
   * @throws BusinessException the business exception
   */
  @Override
  @Transactional(rollbackFor = BusinessException.class)
  public Pager<TPmAreaNodeType> getAreaNodeTypes(
      com.pcitc.fms.service.model.TPmAreaNodeType tpmBizOrgDTLModel, Pageable pageable)
      throws BusinessException {
    Pager<TPmAreaNodeType> pageData = new Pager<>();
    Page<com.pcitc.fms.dal.pojo.TPmAreaNodeType> properties = areaNodeTypeDao.findAreaNodeTypes(tpmBizOrgDTLModel, pageable);
    List<TPmAreaNodeType> EntityList = null;
    try {
      EntityList = ObjectConverter.listConverter(properties.getContent(), TPmAreaNodeType.class);
    } catch (Exception e) {
      log.error("fail",e);
      throw  new BusinessException("","",e.getMessage());
    }
    pageData.setContent(EntityList);
    pageData.setFirst(properties.isFirst());
    pageData.setLast(properties.isLast());
    pageData.setNumber(properties.getNumber());
    pageData.setNumberOfElements(properties.getNumberOfElements());
    pageData.setSize(properties.getSize());
    pageData.setSort(properties.getSort());
    pageData.setTotalElements(properties.getTotalElements());
    pageData.setTotalPages(properties.getTotalPages());
    return pageData;
  }


  /**
   * Gets area node type.
   *
   * @param orgCode the org code
   * @param opertype the opertype
   * @return the area node type
   * @throws BusinessException the business exception
   */
  @Override
  @Transactional(rollbackFor = BusinessException.class)
  public List<TPmAreaNodeType> getAreaNodeType(String orgCode, String opertype) throws BusinessException {
    List<com.pcitc.fms.dal.pojo.TPmAreaNodeType> tPmBizOrgDTLs = new ArrayList<>();
    com.pcitc.fms.dal.pojo.TPmAreaNodeType tpmBizOrgDTL = areaNodeTypeDao.getTPmAreaNodeTypeByAreaCode(orgCode);
    if (opertype != null && "leaves".equals(opertype)) {//TODO 叶子节点,不包括中间级别
      tpmBizOrgDTL = areaNodeTypeDao.getTPmAreaNodeTypeByAreaCode(orgCode);
      List<com.pcitc.fms.dal.pojo.TPmAreaNodeType> tPmBizOrgDTLsLeaves = new ArrayList<>();
    }
    try {
      return ObjectConverter.listConverter(tPmBizOrgDTLs, TPmAreaNodeType.class);
    } catch (Exception e) {
      log.error("fail",e);
      throw  new BusinessException("","",e.getMessage());
    }
  }


}
