/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: PostionService
 * Date:18-3-5 上午10:41
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.bll.itf;

import com.pcitc.fms.exception.BusinessException;
import java.util.List;

public interface PostionService {

	public List<com.pcitc.fms.bll.entity.PostionMeta> getPostions(com.pcitc.fms.service.model.PostionMeta postionMetaModel) throws BusinessException;

	public void deletePostionById(String postionId);

	public void updatePostion(List<com.pcitc.fms.bll.entity.PostionMeta> postionMeta) throws BusinessException;

	public List<com.pcitc.fms.bll.entity.PostionMeta> addPostion(List<com.pcitc.fms.bll.entity.PostionMeta> postionMetaList) throws BusinessException;

	public List<com.pcitc.fms.bll.entity.PostionMeta> getPostion(String postionId) throws BusinessException;
}
