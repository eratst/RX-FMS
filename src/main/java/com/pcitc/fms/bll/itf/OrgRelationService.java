/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: OrgRelationService
 * Date:18-3-5 上午10:35
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.bll.itf;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.service.model.OrgRelation;
import com.pcitc.fms.service.model.Pager;

/**
 * Title: OrgRelationService Description: TODO task mark zhenqiang.zhao
 *
 * @author zhenqiang.zhao
 * @version 1.0
 */
@Service
public interface OrgRelationService {

	/**
	 * Gets org relations.
	 *
	 * @param orgModel the org model
	 * @param pageable the pageable
	 * @return org relations
	 * @throws Exception the exception
	 * @Title: getOrgRelations
	 * @Description: TODO task mark zhenqiang.zhao
	 * @date 2017年7月21日
	 * @return: List<com.pcitc.fms.bll.entity.OrgRelation>
	 * @author zhenqiang.zhao
	 */
	public Pager<com.pcitc.fms.bll.entity.OrgRelation> getOrgRelations(
			OrgRelation orgModel,Pageable pageable) throws BusinessException;

	/**
	 * Gets org relation by code.
	 *
	 * @param factoryCode the factory code
	 * @param orgRelationCode the org relation code
	 * @return org relation by code
	 * @throws Exception the exception
	 * @Title: getOrgRelationById
	 * @Description: TODO task mark zhenqiang.zhao
	 * @date 2017年7月21日
	 * @return: com.pcitc.fms.bll.entity.OrgRelation
	 * @author zhenqiang.zhao
	 */
	public com.pcitc.fms.bll.entity.OrgRelation getOrgRelationByCode(String factoryCode, String orgRelationCode ) throws BusinessException;

	/**
	 * Add org relations list.
	 *
	 * @param orgRelationList the org relation list
	 * @return list
	 * @throws Exception the exception
	 * @Title: addOrgRelations
	 * @Description: TODO task mark zhenqiang.zhao
	 * @date 2017年7月21日
	 * @return: List<com.pcitc.fms.bll.entity.OrgRelation>
	 * @author zhenqiang.zhao
	 */
	public List<com.pcitc.fms.bll.entity.OrgRelation> addOrgRelations(
			List<com.pcitc.fms.bll.entity.OrgRelation> orgRelationList) throws BusinessException;

	/**
	 * Update org relation.
	 *
	 * @param orgRelation the org relation
	 * @throws BusinessException the business exception
	 * @throws Exception the exception
	 * @Title: updateOrgRelation
	 * @Description: TODO task mark zhenqiang.zhao
	 * @date 2017年7月21日
	 * @return: void
	 * @author zhenqiang.zhao
	 */
	public void updateOrgRelation(
			com.pcitc.fms.bll.entity.OrgRelation orgRelation) throws BusinessException;

	/**
	 * Delete org relation by codes.
	 *
	 * @param buildStringToListInteger the build string to list integer
	 * @Title: deleteOrgRelationByIds
	 * @Description: TODO task mark zhenqiang.zhao
	 * @date 2017年7月21日
	 * @return: void
	 * @author zhenqiang.zhao
	 */
	public void deleteOrgRelationByCodes(List<String> buildStringToListInteger);

	/**
	 * Delete org relation by code.
	 *
	 * @param code the code
	 * @throws Exception the exception
	 * @Title: deleteOrgRelationById
	 * @Description: TODO task mark zhenqiang.zhao
	 * @date 2017年7月21日
	 * @return: void
	 * @author zhenqiang.zhao
	 */
	public void deleteOrgRelationByCode(String code) throws BusinessException;

	/**
	 * Gets org relation by code.
	 *
	 * @param code the code
	 * @return org relation by code
	 * @throws Exception the exception
	 * @Title: getOrgRelationById
	 * @Description: TODO task mark zhenqiang.zhao
	 * @date 2017年7月23日
	 * @return: com.pcitc.fms.bll.entity.OrgRelation
	 * @author zhenqiang.zhao
	 */
	public com.pcitc.fms.bll.entity.OrgRelation getOrgRelationByCode(String code) throws BusinessException;

	/**
	 * Delete org relation by code.
	 *
	 * @param code the code
	 * @param code1 the code 1
	 * @throws Exception the exception
	 * @Title: deleteOrgRelationById
	 * @Description: TODO task mark zhenqiang.zhao
	 * @date 2017年7月23日
	 * @return: void
	 * @author zhenqiang.zhao
	 */
	public void deleteOrgRelationByCode(String code, String code1) throws BusinessException;


	/**
	 * Delete org relation by codes.
	 *
	 * @param factoryCode the factory code
	 * @param codeList the code list
	 */
	public void deleteOrgRelationByCodes(String factoryCode, List<String> codeList);

}
