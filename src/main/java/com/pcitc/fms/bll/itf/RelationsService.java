/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: RelationsService
 * Date:18-3-5 上午10:42
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.bll.itf;

import com.pcitc.fms.exception.BusinessException;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pcitc.fms.service.model.Pager;
import com.pcitc.fms.service.model.Relations;

/**
 * Title: RelationsService Description:TODO
 *
 * @author zhenqiang.zhao
 * @version 1.0
 * @date 2017年6月16日
 */
@Service
public interface RelationsService {

	/**
	 * Gets relations.
	 *
	 * @param relationsModel the relations model
	 * @return relations
	 * @throws BusinessException the BusinessException
	 * @Title: getRelations
	 * @Description: TODO
	 * @date 2017年6月18日
	 * @return: List<com.pcitc.fms.bll.entity.Relations>
	 * @author zhenqiang.zhao
	 */
	public List<com.pcitc.fms.bll.entity.Relations> getRelations(
			Relations relationsModel) throws BusinessException;

	/**
	 * Gets relations by code.
	 *
	 * @param code the code
	 * @return relations by code
	 * @throws BusinessException the BusinessException
	 * @Title: getRelationsById
	 * @Description: TODO
	 * @date 2017年6月18日
	 * @return: com.pcitc.fms.bll.entity.Relations
	 * @author zhenqiang.zhao
	 */
	public com.pcitc.fms.bll.entity.Relations getRelationsByCode(
			String code) throws BusinessException;

	/**
	 * Add relations list.
	 *
	 * @param relationsModelEntityList the relations model entity list
	 * @return list
	 * @throws BusinessException the BusinessException
	 * @Title: addRelations
	 * @Description: TODO
	 * @date 2017年6月18日
	 * @return: Integer
	 * @author zhenqiang.zhao
	 */
	public List<com.pcitc.fms.bll.entity.Relations> addRelations(
			List<com.pcitc.fms.bll.entity.Relations> relationsModelEntityList) throws BusinessException;

	/**
	 * Update relations.
	 *
	 * @param relations the relations
	 * @throws BusinessException the BusinessException
	 * @Title: updateRelations
	 * @Description: TODO
	 * @date 2017年6月18日
	 * @return: void
	 * @author zhenqiang.zhao
	 */
	public void updateRelations(com.pcitc.fms.bll.entity.Relations relations) throws BusinessException;

	/**
	 * Delete relations by codes.
	 *
	 * @param codes the codes
	 * @Title: deleteRelationsByIds
	 * @Description: TODO
	 * @date 2017年6月18日
	 * @return: void
	 * @author zhenqiang.zhao
	 */
	public void deleteRelationsByCodes(List<String> codes);

	/**
	 * Gets pager relations.
	 *
	 * @param relationsModel the relations model
	 * @param pageable the pageable
	 * @return pager relations
	 * @throws BusinessException the BusinessException
	 * @Title: getPagerRelations
	 * @Description: 分页查询
	 * @date 2017年7月27日
	 * @return: List<com.pcitc.fms.bll.entity.Relations>
	 * @author zhenqiang.zhao
	 */
	public Pager<com.pcitc.fms.bll.entity.Relations> getPagerRelations(Relations relationsModel, Pageable pageable) throws BusinessException;

	/**
	 * Delete relations by code.
	 *
	 * @param code the code
	 * @throws BusinessException the BusinessException
	 * @Title: deleteRelationsById
	 * @Description: TODO task mark zhenqiang.zhao
	 * @date 2017年8月10日
	 * @return: void
	 * @author 赵振强
	 */
	public void deleteRelationsByCode(String code) throws BusinessException;

	/**
	 * Gets relations code in.
	 *
	 * @param targetType the target type
	 * @param codeList the code list
	 * @param sourceCode the source code
	 * @return the relations code in
	 * @throws BusinessException the BusinessException
	 */
	public List<com.pcitc.fms.bll.entity.Relations> getRelationsCodeIn(String targetType, List<String> codeList, String sourceCode)throws BusinessException;

	
}
