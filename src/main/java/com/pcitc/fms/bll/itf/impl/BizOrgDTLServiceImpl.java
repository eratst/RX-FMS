/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: BizOrgDTLServiceImpl
 * Date:18-3-8 下午6:20
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.bll.itf.impl;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.bll.entity.OrgTree;
import com.pcitc.fms.bll.entity.TPmBizOrgDTL;
import com.pcitc.fms.bll.itf.BizOrgDTLService;
import com.pcitc.fms.common.LevelValue;
import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.dal.dao.AreaDictionaryDao;
import com.pcitc.fms.dal.dao.BizOrgDTLDao;
import com.pcitc.fms.dal.dao.BizOrgMainDao;
import com.pcitc.fms.dal.dao.DbPrimaryIdDao;
import com.pcitc.fms.dal.dao.OrgTypeDao;
import com.pcitc.fms.dal.dao.RentDao;
import com.pcitc.fms.dal.dao.TPmOrgDao;
import com.pcitc.fms.dal.pojo.Rent;
import com.pcitc.fms.dal.pojo.TPmBizOrgMain;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.service.model.Pager;

import pcitc.imp.common.ettool.utils.ObjectConverter;

/**
 * Title: BizOrgDTLserviceImpl Description: TODO task mark zhenqiang.zhao
 * 
 * @author zhenqiang.zhao
 * @date 2017年11月21日
 * @version 1.0
 */
@Service
public class BizOrgDTLServiceImpl implements BizOrgDTLService {
	private static Logger log = LoggerFactory.getLogger(BizOrgDTLServiceImpl.class);
	@Autowired
	private BizOrgDTLDao bizOrgDTLDao;
	@Autowired
	private TPmOrgDao tPmOrgDao;
	@Autowired
	private BizOrgMainDao bizOrgMainDao;
	@Autowired
	private AreaDictionaryDao areaDictionaryDao;
	@Autowired
	private OrgTypeDao orgTypeDao;
	@Autowired
	private RentDao rentDao;
	@Autowired
	private TPmOrgServiceImpl tPmOrgServiceImpl;
	@Autowired
	private DbPrimaryIdDao dbPrimaryIdDao;
	
	@Override
	@Transactional(rollbackFor = BusinessException.class)
	public Pager<TPmBizOrgDTL> getBizOrgDTLs(com.pcitc.fms.service.model.TPmBizOrgDTL tpmBizOrgDTLModel,
			Pageable pageable) throws BusinessException {
		
//		if (!StringUtils.isEmpty(tpmBizOrgDTLModel.getBizCode())) {
//			if (tpmBizOrgDTLModel.getBizCode().contains("_SYSTEM_STANDARD_BIZ")) {
//				getDataFromOrgAddToDtl(tpmBizOrgDTLModel);
//			}
//		} 
		
		if (StringUtils.isEmpty(tpmBizOrgDTLModel.getBizCode())) {
			if (StringUtils.isEmpty(tpmBizOrgDTLModel.getRentCode())){
				throw new BusinessException("", "rentCode不能为空");
			}
			String standardBizCode = tpmBizOrgDTLModel.getRentCode()+"_SYSTEM_STANDARD_BIZ";
			tpmBizOrgDTLModel.setBizCode(standardBizCode);
//			getDataFromOrgAddToDtl(tpmBizOrgDTLModel);
		}
		
		Pager<TPmBizOrgDTL> pageData = new Pager<>();
		MyPageImpl properties=null;
		List<TPmBizOrgDTL> EntityList = null;
		try {
			if(tpmBizOrgDTLModel.getParentOrgCode()!=null){
				if(!tpmBizOrgDTLModel.getParentOrgCode().equals("0")){
					com.pcitc.fms.dal.pojo.TPmOrg org = tPmOrgDao.getTPmOrg(tpmBizOrgDTLModel.getParentOrgCode());					
					if (org != null) {
						tpmBizOrgDTLModel.setParentOrgId(org.getOrgId()); 
					} else {
						throw new BusinessException("", tpmBizOrgDTLModel.getParentOrgCode()+"不存在");
					}
				}
			}
			
			
			properties = bizOrgDTLDao.findBizOrgDTLs(tpmBizOrgDTLModel, pageable);
			EntityList = ObjectConverter.listConverter(properties.getContent(), TPmBizOrgDTL.class);
		} catch (Exception e) {
			if (e instanceof UndeclaredThrowableException) {
				throw new BusinessException("", e.getCause().getMessage());
			}
			log.error("fail", e);
			throw new BusinessException("", "", e.getMessage());
		}
		pageData.setContent(EntityList);
		pageData.setFirst(properties.isFirst());
		pageData.setLast(properties.isLast());
		pageData.setNumber(properties.getNumber());
		pageData.setNumberOfElements(properties.getNumberOfElements());
		pageData.setSize(properties.getSize());
		pageData.setSort(properties.getSort());
		pageData.setTotalElements(properties.getCount());
		pageData.setTotalPages(properties.getTotalPages());
		return pageData;
	}

	

	@Override
	@Transactional(rollbackFor = BusinessException.class)
	public List<TPmBizOrgDTL> getBizOrgDTLByCode(String orgCode, String opertype, List<String> orgCodeList,
			String bizCode,com.pcitc.fms.service.model.TPmBizOrgDTL tpmBizOrgDTLModel,Pageable pageable) throws BusinessException {
		if (orgCodeList == null) {
			orgCodeList =  new ArrayList<>();
		}
		
		List<com.pcitc.fms.dal.pojo.TPmBizOrgDTL> tPmBizOrgDTLs = new ArrayList<>();
		List<com.pcitc.fms.dal.pojo.TPmBizOrgDTL> tPmBizOrgDTLsResult = new ArrayList<>();
		com.pcitc.fms.dal.pojo.TPmBizOrgDTL tPmBizOrgDTL = new com.pcitc.fms.dal.pojo.TPmBizOrgDTL();
		if(orgCode==null && orgCodeList==null){
			throw new BusinessException("", "", "组织机构编码不能为空！");
		}
		if(orgCodeList!=null){
			if(orgCodeList.size()<0){
				throw new BusinessException("", "", "组织机构编码不能为空！");
			}
		}
		if (StringUtils.isNotEmpty(orgCode)) {
				orgCodeList.add(orgCode);
		} 
		if (orgCodeList!=null&&!orgCodeList.isEmpty()) {
			tPmBizOrgDTLs = bizOrgDTLDao.getBizOrgDTLByCodeAndBizCode(orgCodeList,tpmBizOrgDTLModel.getBizCode());
		} else {
			throw new BusinessException("", "", "组织机构编码不能为空！");
		}
		
		if (tPmBizOrgDTLs==null || tPmBizOrgDTLs.isEmpty()) {
			throw new BusinessException("", "", ":不存在该组织机构编码");
		}

		for (int i = 0; i < tPmBizOrgDTLs.size(); i++) {
			if (opertype != null && "children".equals(opertype)) {// 下一级
				Long orgId = tPmBizOrgDTLs.get(i).getOrgId();
				com.pcitc.fms.dal.pojo.TPmBizOrgDTL isContinue = bizOrgDTLDao.getBizOrgDTLByOrgIdAndBizCode(orgId,tPmBizOrgDTLs.get(i).getBizCode());
				Integer inUse = isContinue.getInUse();
				if (inUse == 1) {
					try{
						tPmBizOrgDTLsResult = bizOrgDTLDao.getBizOrgDTLByOrgCodeAndChildren(orgId, bizCode, inUse, tpmBizOrgDTLModel, pageable);
					} catch(Exception e){
						if (e instanceof UndeclaredThrowableException) {
							throw new BusinessException("", e.getCause().getMessage());
						}
					}
				}
				
			}
			if (opertype != null && "ancestors".equals(opertype)) {// 上一级
				List<Long> orgIds = new ArrayList<>();
				orgIds.add(tPmBizOrgDTLs.get(i).getParentOrgId());
				try{
					tPmBizOrgDTLsResult = bizOrgDTLDao.getBizOrgDTLByOrgCodeAndAncestors(orgIds, bizCode);
				}catch(Exception e){
					if (e instanceof UndeclaredThrowableException) {
						throw new BusinessException("", e.getCause().getMessage());
					}
				}
				
			}

		}
		
		if (opertype != null && "Leaves".equals(opertype)) {
			List<String> leavesCodes = new ArrayList<>();
			Set<String> setCodes = new HashSet<>();
			for (com.pcitc.fms.dal.pojo.TPmBizOrgDTL tPmBiz: tPmBizOrgDTLs) {
				if (tPmBiz!=null) {
					if (tPmBiz.getInUse()==1) {
						List<String> leavesCodesPart = bizOrgDTLDao.getLeavesCodes(tPmBiz.getOrgCode(),tPmBiz.getBizCode());
						leavesCodes.addAll(leavesCodesPart);
					}
				}
			}
			setCodes.addAll(leavesCodes);
			leavesCodes.clear();
			leavesCodes.addAll(setCodes);
			tPmBizOrgDTLsResult = bizOrgDTLDao.getBizOrgDTLByCodeAndBizCode(leavesCodes,bizCode);
		}

		if (opertype != null && "allChildren".equals(opertype)) {
			if (orgCodeList != null && !orgCodeList.isEmpty()) {
				List<String> orgCodesTemp = new ArrayList<>();
				List<String> orgCodes = new ArrayList<>();
				Set<String> orgCodeSet = new HashSet<>();
				for (String str : orgCodeList) {
					orgCodesTemp = bizOrgDTLDao.getAllChildrenOrgCodes(str,LevelValue.LEVELVALUE);
					orgCodeSet.addAll(orgCodesTemp);
					orgCodeSet.remove(str);
				}
				orgCodes.addAll(orgCodeSet);
				try{
					if(orgCodes == null || orgCodes.isEmpty()){
						List<TPmBizOrgDTL> list = new ArrayList<TPmBizOrgDTL>();
						return list;
					}
					tPmBizOrgDTLsResult = bizOrgDTLDao.getBizOrgDTLByOrgCodeAndAllChildren(orgCodes, bizCode,tpmBizOrgDTLModel,pageable);
				}catch(Exception e){
					if (e instanceof UndeclaredThrowableException) {
						throw new BusinessException("", e.getCause().getMessage());
					}
				}
				
			} else {
				List<String> orgCodes = bizOrgDTLDao.getAllChildrenOrgCodes(orgCode,LevelValue.LEVELVALUE);
				try{
					tPmBizOrgDTLsResult = bizOrgDTLDao.getBizOrgDTLByOrgCodeAndAllChildren(orgCodes, bizCode,tpmBizOrgDTLModel,pageable);
				}catch(Exception e){
					if (e instanceof UndeclaredThrowableException) {
						throw new BusinessException("", e.getCause().getMessage());
					}
				}
				
			}

		}

		if (opertype != null && "toRoot".equals(opertype)) {
			if (orgCodeList != null && !orgCodeList.isEmpty()) {
				List<String> orgCodesTemp = new ArrayList<>();
				List<String> orgCodes = new ArrayList<>();
				Set<String> orgCodeSet = new HashSet<>();
				for (String str : orgCodeList) {
					orgCodesTemp = bizOrgDTLDao.getAllToRootOrgCodes(str);
					orgCodeSet.addAll(orgCodesTemp);
				}
				orgCodes.addAll(orgCodeSet);
				try{
					tPmBizOrgDTLsResult = bizOrgDTLDao.getBizOrgDTLByOrgCodeAndAllToRoot(orgCodes, bizCode,tpmBizOrgDTLModel,pageable);
				}catch(Exception e){
					if (e instanceof UndeclaredThrowableException) {
						throw new BusinessException("", e.getCause().getMessage());
					}
				}
				
			} else {
				List<String> orgCodes = bizOrgDTLDao.getAllToRootOrgCodes(orgCode);
				try{
					tPmBizOrgDTLsResult = bizOrgDTLDao.getBizOrgDTLByOrgCodeAndAllToRoot(orgCodes, bizCode,tpmBizOrgDTLModel,pageable);
				}catch(Exception e){
					if (e instanceof UndeclaredThrowableException) {
						throw new BusinessException("", e.getCause().getMessage());
					}
				}
				
			}
		}

		List<com.pcitc.fms.dal.pojo.TPmBizOrgDTL> tPmBizOrgDTLsList = new ArrayList<>();

		try {
			if (opertype == null) {
				return ObjectConverter.listConverter(tPmBizOrgDTLs, TPmBizOrgDTL.class);
			} else {
				List<com.pcitc.fms.bll.entity.TPmBizOrgDTL> res = ObjectConverter.listConverter(tPmBizOrgDTLsResult, TPmBizOrgDTL.class);
				return res;
				
			}

		} catch (Exception e) {
			log.error("fail", e);
			throw new BusinessException("", "", e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		String str = "fms.1012_SYSTEM_STANDARD_BIZ";
		String rentCode = str.substring(0, str.indexOf("_SYSTEM_STANDARD_BIZ"));
		System.out.println(rentCode);
	}
	
	private void getDataFromOrgAddToDtl(com.pcitc.fms.service.model.TPmBizOrgDTL tpmBizOrgDTLModel)
			throws BusinessException {
		String rentCode = tpmBizOrgDTLModel.getBizCode().substring(0, tpmBizOrgDTLModel.getBizCode().indexOf("_SYSTEM_STANDARD_BIZ"));
		Rent rent = rentDao.getRentByRentCode(rentCode);
		if (rent != null) {
			if (rent.getOrgId() != null) {
				List<OrgTree> orgTrees = tPmOrgServiceImpl.getBranchTrees(rent.getOrgId());
				
					List<com.pcitc.fms.dal.pojo.TPmBizOrgDTL> addContext = new ArrayList<>();
					for (OrgTree orgTree : orgTrees) {
						
						com.pcitc.fms.dal.pojo.TPmBizOrgDTL pojoTPmBizOrgDTL=new com.pcitc.fms.dal.pojo.TPmBizOrgDTL();
						
						TPmBizOrgMain tPmBizOrgMain = bizOrgMainDao.getBizOrgMainByBizCode(tpmBizOrgDTLModel.getBizCode());
						com.pcitc.fms.dal.pojo.TPmBizOrgDTL checkTPmBizOrgDTL = bizOrgDTLDao.getBizOrgDTLByOrgCodeAndBizId(orgTree.getOrgCode(),tPmBizOrgMain.getBizId());
						if (checkTPmBizOrgDTL==null) {
							List<java.math.BigDecimal> bigDecimals = dbPrimaryIdDao.getSeqId(1, "T_PM_BIZORGDTL");
							com.pcitc.fms.dal.pojo.TPmOrg tPmOrg = tPmOrgDao.getTPmOrg(orgTree.getOrgCode());
							pojoTPmBizOrgDTL.setDtlId(bigDecimals.get(0).longValue());
							pojoTPmBizOrgDTL.setBizId(tPmBizOrgMain.getBizId());
							pojoTPmBizOrgDTL.setOrgId(orgTree.getOrgId());
							if (orgTree.getUpperOrgId()==null) {
								pojoTPmBizOrgDTL.setParentOrgId(0L);
							} else {
								pojoTPmBizOrgDTL.setParentOrgId(orgTree.getUpperOrgId());
							}
							
							pojoTPmBizOrgDTL.setOrgCode(orgTree.getOrgCode());
							pojoTPmBizOrgDTL.setOrgName(orgTree.getOrgName());
							pojoTPmBizOrgDTL.setOrgAlias(orgTree.getOrgAlias());
							pojoTPmBizOrgDTL.setInUse(1);
							pojoTPmBizOrgDTL.setOrgTypeId(orgTree.getOrgTypeId());
							pojoTPmBizOrgDTL.setExpendFlag(1L);
							pojoTPmBizOrgDTL.setSortNum(1);
							pojoTPmBizOrgDTL.setVersion(1);
							pojoTPmBizOrgDTL.setCrtUserCode(tPmOrg.getCrtUserCode());
							pojoTPmBizOrgDTL.setCrtUserName(tPmOrg.getCrtUserName());
							pojoTPmBizOrgDTL.setCrtDate(tPmOrg.getCrtDate());
							pojoTPmBizOrgDTL.setMntUserCode(tPmOrg.getMntUserCode());
							pojoTPmBizOrgDTL.setMntUserName(tPmOrg.getMntUserName());
							pojoTPmBizOrgDTL.setMntDate(tPmOrg.getMntDate());
							addContext.add(pojoTPmBizOrgDTL);
						}
					}
					bizOrgDTLDao.save(addContext);
				
			} else {
				throw new BusinessException("", "租户未指定");
			}
		}
	}

}
