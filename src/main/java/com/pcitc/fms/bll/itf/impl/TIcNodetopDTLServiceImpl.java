/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: TIcNodetopDTLServiceImpl
 * Date:18-3-8 下午6:29
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.bll.itf.impl;

import com.pcitc.fms.bll.entity.TIcNodetopDTL;
import com.pcitc.fms.bll.itf.TIcNodetopDTLService;
import com.pcitc.fms.dal.dao.DbPrimaryIdDao;
import com.pcitc.fms.dal.dao.NodeDictionaryDao;
import com.pcitc.fms.dal.dao.TIcNodetopDTLDao;
import com.pcitc.fms.dal.dao.TIcNodetopMainDao;
import com.pcitc.fms.dal.pojo.NodeDictionary;
import com.pcitc.fms.dal.pojo.TIcNodetopMain;
import com.pcitc.fms.dal.pojo.TPmOrg;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.exception.BusinessExceptionMessage;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.imp.common.exception.BusiException;
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
 * Title: NodetopDTLserviceImpl Description: TODO task mark zhenqiang.zhao
 *
 * @author zhenqiang.zhao
 * @version 1.0
 * @date 2017年11月21日
 */
@Service
public class TIcNodetopDTLServiceImpl implements TIcNodetopDTLService {

  /**
   * The constant log.
   */
  private static Logger log = LoggerFactory.getLogger(TIcNodetopDTLServiceImpl.class);
  /**
   * The T ic nodetop dtl dao.
   */
  @Autowired
   private TIcNodetopDTLDao tIcNodetopDTLDao;
  /**
   * The Node dictionary dao.
   */
  @Autowired
   private NodeDictionaryDao nodeDictionaryDao;
  /**
   * The Db primary id dao.
   */
  @Autowired
   private DbPrimaryIdDao dbPrimaryIdDao;
  /**
   * The T ic nodetop main dao.
   */
  @Autowired
   private TIcNodetopMainDao tIcNodetopMainDao;

  /**
   * Gets nodetop dt ls.
   *
   * @param tpmNodetopDTLModel the tpm nodetop dtl model
   * @param pageable the pageable
   * @return the nodetop dt ls
   * @throws BusinessException the business exception
   */
  @Override
   @Transactional(rollbackFor = BusinessException.class)
   public Pager<TIcNodetopDTL> getNodetopDTLs(com.pcitc.fms.service.model.TIcNodetopDTL tpmNodetopDTLModel,Pageable pageable) throws BusinessException {
       Pager<TIcNodetopDTL>  pageData = new Pager<>();
       Page<com.pcitc.fms.dal.pojo.TIcNodetopDTL> properties = tIcNodetopDTLDao.findNodetopDTLs(tpmNodetopDTLModel,pageable);
     List<TIcNodetopDTL> EntityList = null;
     try {
       EntityList = ObjectConverter.listConverter(properties.getContent(),TIcNodetopDTL.class);
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
   * Gets nodetop dtl by code.
   *
   * @param topCode the top code
   * @param nodeCode the node code
   * @return the nodetop dtl by code
   * @throws BusinessException the business exception
   */
  @Override
   @Transactional(rollbackFor = BusinessException.class)
   public List<TIcNodetopDTL> getNodetopDTLByCode(String topCode, String nodeCode)throws BusinessException {
     List<com.pcitc.fms.dal.pojo.TIcNodetopDTL> tPmNodetopDTLs = new ArrayList<>();
     /* com.pcitc.fms.dal.pojo.TIcNodetopDTL tpmNodetopDTL =  null;
//       com.pcitc.fms.dal.pojo.TIcNodetopDTL tpmNodetopDTL =  tIcNodetopDTLDao.getNodetopDTLByOrgCode(orgCode);
       if(null == tpmNodetopDTL){
                throw new BusinessException("",orgCode,":不存在该组织机构编码");
       }else {
           tPmNodetopDTLs.add(tpmNodetopDTL);
       }
       if (opertype != null && "children".equals(opertype)) {//下一级
//           tPmNodetopDTLs = tIcNodetopDTLDao.getNodetopDTLByOrgCodeAndChildren(tpmNodetopDTL.getOrgId());
       }
       if (opertype != null && "ancestors".equals(opertype)) {//上一级
           List<Integer> orgIds = new ArrayList<>();
//           orgIds.add(tpmNodetopDTL.getParentOrgId());
//           tPmNodetopDTLs =  tIcNodetopDTLDao.getNodetopDTLByOrgCodeAndAncestors(orgIds);
       }

       if (opertype != null && "leaves".equals(opertype)) {//TODO 叶子节点,不包括中间级别
//           tpmNodetopDTL = tIcNodetopDTLDao.getNodetopDTLByOrgCode(orgCode);
           List<com.pcitc.fms.dal.pojo.TIcNodetopDTL> tPmNodetopDTLsLeaves = new ArrayList<>();
           tPmNodetopDTLs = getLeaves(tpmNodetopDTL,tPmNodetopDTLsLeaves);
       }*/
	   TIcNodetopMain findByTopCode = tIcNodetopMainDao.findByTopCode(topCode);
       if(null==findByTopCode){
    	   throw new BusinessException("", "", topCode + "编码不存在");
       }
       tPmNodetopDTLs = tIcNodetopDTLDao.findByDataCode(topCode, nodeCode);
     try {
       return ObjectConverter.listConverter(tPmNodetopDTLs, TIcNodetopDTL.class);
     } catch (Exception e) {
       log.error("fail",e);
       throw  new BusinessException("","",e.getMessage());
     }
   }

  /**
   * 递归查询子节点
   *
   * @param tPmNodetopDTL the t pm nodetop dtl
   * @param tPmNodetopDTLsLeaves the t pm nodetop dt ls leaves
   * @return leaves
   */
  private List<com.pcitc.fms.dal.pojo.TIcNodetopDTL> getLeaves(com.pcitc.fms.dal.pojo.TIcNodetopDTL tPmNodetopDTL, List<com.pcitc.fms.dal.pojo.TIcNodetopDTL> tPmNodetopDTLsLeaves) {
        List<com.pcitc.fms.dal.pojo.TIcNodetopDTL> tPmNodetopDTLChildren =null;
//            tPmNodetopDTL.getChildren();
        if (tPmNodetopDTLChildren == null) {
            tPmNodetopDTLsLeaves.add(tPmNodetopDTL);
        }else{
            for (com.pcitc.fms.dal.pojo.TIcNodetopDTL TIcNodetopDTLChildren:tPmNodetopDTLChildren) {
                getLeaves(TIcNodetopDTLChildren,tPmNodetopDTLsLeaves);
            }
        }
        return tPmNodetopDTLsLeaves;
    }



  /**
   * Update nodetop dtl.
   *
   * @param tpmNodetopDTLEntityList the tpm nodetop dtl entity list
   * @param topCode the top code
   * @param tpmNodetopDTLCode the tpm nodetop dtl code
   * @throws BusinessException the business exception
   */
  @Override
   @Transactional(rollbackFor=BusinessException.class)
   public void updateNodetopDTL(List<TIcNodetopDTL> tpmNodetopDTLEntityList, String topCode, String tpmNodetopDTLCode) throws BusinessException {
     List<com.pcitc.fms.dal.pojo.TIcNodetopDTL> tpmNodetopDTLPojoList = null;
     try {
       tpmNodetopDTLPojoList = ObjectConverter.listConverter(tpmNodetopDTLEntityList,com.pcitc.fms.dal.pojo.TIcNodetopDTL.class);
     } catch (Exception e) {
       log.error("fail",e);
       throw  new BusinessException("","",e.getMessage());
     }
     com.pcitc.fms.dal.pojo.TIcNodetopDTL tpmNodetopDTLPojo = tpmNodetopDTLPojoList.get(0);
       TIcNodetopMain findByTopCode = tIcNodetopMainDao.findByTopCode(topCode);
       if(null==findByTopCode){
    	   throw new BusinessException("", "topCode", topCode + "编码不存在");
       }
       try {
           if (null == tpmNodetopDTLPojo.getMntDate()) {
               tpmNodetopDTLPojo.setMntDate(new Date());
           }
           com.pcitc.fms.dal.pojo.TIcNodetopDTL tpmNodetopDTL = tIcNodetopDTLDao.findByDataCode(tpmNodetopDTLCode);
           if(null==tpmNodetopDTL){
        	   throw new BusinessException("", "tpmNodetopDTLCode", tpmNodetopDTLCode + "编码不存在");
           }
           update(tpmNodetopDTLPojo,tpmNodetopDTL);
           tIcNodetopDTLDao.save(tpmNodetopDTL);
       } catch (DataIntegrityViolationException e) {
           log.error(e.getMessage());
           throw new BusinessException("", "", BusinessExceptionMessage.getUniqueKeyMessage(e));
       }
       catch (Exception e) {
         log.error("fail",e);
         throw  new BusinessException("","",e.getMessage());
       }
   }

  /**
   * Update.
   *
   * @param tpmNodetopDTLPojo the tpm nodetop dtl pojo
   * @param tpmNodetopDTL the tpm nodetop dtl
   */
  private void update(com.pcitc.fms.dal.pojo.TIcNodetopDTL tpmNodetopDTLPojo,
		com.pcitc.fms.dal.pojo.TIcNodetopDTL tpmNodetopDTL) {
	// TODO Auto-generated method stub
	   String des = tpmNodetopDTLPojo.getDes();
	   String snodeAlias = tpmNodetopDTLPojo.getSnodeAlias();
	   String snodeCode = tpmNodetopDTLPojo.getSnodeCode();
	   Integer snodeId = tpmNodetopDTLPojo.getSnodeId();
	   String tnodeAlias = tpmNodetopDTLPojo.getTnodeAlias();
	   String tnodeCode = tpmNodetopDTLPojo.getTnodeCode();
	   Integer tnodeId = tpmNodetopDTLPojo.getTnodeId();
	   Integer sortNum = tpmNodetopDTLPojo.getSortNum();
	   Integer version = tpmNodetopDTLPojo.getVersion();
	   
	   if(null != des){
		   tpmNodetopDTL.setDes(des);
	   }
	   if(null != snodeAlias){
		   tpmNodetopDTL.setSnodeAlias(snodeAlias);
	   }
	   if(null != snodeCode){
		   tpmNodetopDTL.setSnodeCode(snodeCode);
	   }
	   if(null != snodeId){
		   tpmNodetopDTL.setSnodeId(snodeId);
	   }
	   if(null != tnodeAlias){
		   tpmNodetopDTL.setTnodeAlias(tnodeAlias);
	   }
	   if(null != tnodeCode){
		   tpmNodetopDTL.setTnodeCode(tnodeCode);
	   }
	   if(null != tnodeId){
		   tpmNodetopDTL.setTnodeId(tnodeId);
	   }
	   if(null != sortNum){
		   tpmNodetopDTL.setSortNum(sortNum);
	   }
	   if(null != version){
		   tpmNodetopDTL.setVersion(version);
	   }
   }


  /**
   * Delete nodetop dtl by code.
   *
   * @param topCode the top code
   * @param tpmNodetopDTLCode the tpm nodetop dtl code
   * @throws BusinessException the business exception
   */
  @Override
   @Transactional(rollbackFor = BusinessException.class)
   public void deleteNodetopDTLByCode(String topCode, String tpmNodetopDTLCode) throws BusinessException{
	  
	   TIcNodetopMain findByTopCode = tIcNodetopMainDao.findByTopCode(topCode);
       if(null==findByTopCode){
    	   throw new BusinessException("", "", topCode + "编码不存在");
       }
       com.pcitc.fms.dal.pojo.TIcNodetopDTL chage = tIcNodetopDTLDao.chage(findByTopCode.getTopId(), tpmNodetopDTLCode);
       if(null == chage) {
            throw new BusinessException("",tpmNodetopDTLCode,":不存在该组织机构");
        }
       tIcNodetopDTLDao.delete(chage.getDataId());

   }


}
