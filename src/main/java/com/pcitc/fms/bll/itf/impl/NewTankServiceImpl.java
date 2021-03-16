/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: TankServiceImpl
 * Date:18-3-8 下午6:29
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.bll.itf.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.pcitc.fms.bll.entity.NewTank;
import com.pcitc.fms.bll.itf.NewTankService;
import com.pcitc.fms.common.QueryParams;
import com.pcitc.fms.dal.dao.NewTankDao;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.service.model.Pager;

import pcitc.imp.common.ettool.utils.ObjectConverter;

/**
 * The type Tank service.
 */
@Service
public class NewTankServiceImpl implements NewTankService {

	@Autowired
	private NewTankDao newTankDao;
	
//	 @PersistenceContext
//	 private EntityManager em;
	
	@SuppressWarnings("unchecked")
	/**
	 * Gets page tanks.
	 *
	 * @param modelStr the model str
	 * @param areaCode the area code
	 * @param pageable the pageable
	 * @return the page tanks
	 * @throws BusinessException the business exception
	 */
	@Override
	public Pager<NewTank> getPageTanks(com.pcitc.fms.service.model.NewTank modelStr, 
			Pageable pageable) throws BusinessException {
//		SimpleJpaRepository JpaSpecificationExecutor =new SimpleJpaRepository(com.pcitc.fms.dal.pojo.NewTank.class,em);
//		List list = JpaSpecificationExecutor.findAll();
		Specification<com.pcitc.fms.dal.pojo.NewTank> whereClause = QueryParams.getWhereClause(modelStr);
		Page<com.pcitc.fms.dal.pojo.NewTank> pageTanks = null;
		List<com.pcitc.fms.dal.pojo.NewTank> sortResults = new ArrayList<>(); 
		if (modelStr.getSkip()!=null && modelStr.getTop()!=null) {
			pageTanks =newTankDao.findAll(whereClause,pageable);
		} else {
			sortResults = newTankDao.findAll(whereClause, modelStr.getOrderby());
		}
		
		Pager<NewTank> pageData = new Pager<>();
		List<com.pcitc.fms.dal.pojo.NewTank> content = new ArrayList<>();
		if (pageTanks!=null) {
			content = pageTanks.getContent();
		} else {
			content = sortResults;
		}
		
		List<NewTank> entityLists = new ArrayList<>();
		try {
			entityLists = ObjectConverter.listConverter(content, NewTank.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		setContent(content, entityLists);
		pageData.setContent(entityLists);
		pageData.setTotalElements((long)entityLists.size());
		if (pageTanks!=null) {
			pageData.setFirst(pageTanks.isFirst());
			pageData.setLast(pageTanks.isLast());
			pageData.setNumber(pageTanks.getNumber());
			pageData.setNumberOfElements(pageTanks.getNumberOfElements());
			pageData.setSize(pageTanks.getSize());
			pageData.setSort(pageTanks.getSort());
			pageData.setTotalPages(pageTanks.getTotalPages());
		}
		return pageData;
		
	}

	private void setContent(List<com.pcitc.fms.dal.pojo.NewTank> content, List<NewTank> entityLists) {
		for (int i=0;i<entityLists.size();i++) {
			entityLists.get(i).setAreaId(content.get(i).getAreaId());
			entityLists.get(i).setAreaCode(content.get(i).getNewArea().getAreaCode());
			entityLists.get(i).setAreaName(content.get(i).getNewArea().getAreaName());
			entityLists.get(i).setAreaAlias(content.get(i).getNewArea().getAreaAlias());
			entityLists.get(i).setOrgId(content.get(i).getNewArea().getNewOrg().getOrgId());
			entityLists.get(i).setOrgCode(content.get(i).getNewArea().getNewOrg().getOrgCode());
			entityLists.get(i).setOrgName(content.get(i).getNewArea().getNewOrg().getOrgName());
			entityLists.get(i).setOrgAlias(content.get(i).getNewArea().getNewOrg().getOrgAlias());
			entityLists.get(i).setAreaTypeId(content.get(i).getNewArea().getAreaTypeId());
			entityLists.get(i).setAreaTypeCode(content.get(i).getNewArea().getAreaType().getAreaTypeCode());
			entityLists.get(i).setAreaTypeName(content.get(i).getNewArea().getAreaType().getAreaTypeName());
			if (null != content.get(i).getNewArea().getNewTankArea()) {
				entityLists.get(i).setTankAreaTypeId(content.get(i).getNewArea().getNewTankArea().getTankAreaTypeId());
				entityLists.get(i).setTankAreaTypeCode(content.get(i).getNewArea().getNewTankArea().getTankAreaType().getTankAreaTypeCode());
				entityLists.get(i).setTankAreaTypeName(content.get(i).getNewArea().getNewTankArea().getTankAreaType().getTankAreaTyprName());
			}
			entityLists.get(i).setOrgTypeId(content.get(i).getNewArea().getNewOrg().getOrgTypeId());
			entityLists.get(i).setOrgTypeCode(content.get(i).getNewArea().getNewOrg().getOrgType().getOrgTypeCode());
			entityLists.get(i).setOrgTypeName(content.get(i).getNewArea().getNewOrg().getOrgType().getOrgTypeName());
			entityLists.get(i).setTankTypeDes(content.get(i).getTankType().getDes());
			entityLists.get(i).setTankTypeCode(content.get(i).getTankType().getTankTypeCode());
			entityLists.get(i).setTankTypeName(content.get(i).getTankType().getTankTypeName());
			entityLists.get(i).setTankTypeSortNum(content.get(i).getTankType().getSortNum());
			//nodeType
			entityLists.get(i).setNodeTypeDes(content.get(i).getNodeType().getDes());
			entityLists.get(i).setNodeTypeCode(content.get(i).getNodeType().getNodeTypeCode());
			entityLists.get(i).setNodeTypeName(content.get(i).getNodeType().getNodeTypeName());
			entityLists.get(i).setNodeTypeSortNum(content.get(i).getNodeType().getSortNum());
			//node
			entityLists.get(i).setNodeDes(content.get(i).getDes());
		}
	}

//	private Specification<com.pcitc.fms.dal.pojo.NewTank> getWhereClause(com.pcitc.fms.service.model.NewTank modelStr) {
//		return new Specification<com.pcitc.fms.dal.pojo.NewTank>() {
//			public Predicate toPredicate(Root<com.pcitc.fms.dal.pojo.NewTank> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
//
//				List<Predicate> listPre = new ArrayList<Predicate>();
//				
//				if (null != modelStr.getTankTypeCode()) {
//					Predicate pre = cb.equal(root.get("newTank").get("tankType").get("tankTypeCode").as(String.class),
//							modelStr.getTankTypeCode());
//					listPre.add(pre);
//				}
//
//				Predicate[] p = new Predicate[listPre.size()];
//				return cb.and(listPre.toArray(p));
//			}
//
//		};
//    }

}
