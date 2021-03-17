/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: NodeAndAreaService
 * Date:18-3-5 上午10:29
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.bll.itf;

import com.pcitc.fms.exception.BusinessException;
import org.springframework.stereotype.Service;

import com.pcitc.fms.service.model.NodeAndArea;

 /**
 * Title: OrgRelationService
* Description: TODO task mark zhenqiang.zhao
 * @author zhenqiang.zhao
 * @date 2017年7月21日
 * @version 1.0
 */
@Service
public interface NodeAndAreaService {


	/**   
	 * @Title: getNodeAndAreas   
	 * @Description: TODO task mark zhenqiang.zhao
	 * @param nodeAndAreaModel
	 * @date 2017年8月4日      
	 * @return: void
	 * @author 赵振强      
	 * @throws Exception 
	 */
	public com.pcitc.fms.bll.entity.NodeAndArea getNodeAndAreas(NodeAndArea nodeAndAreaModel) throws BusinessException;

}
