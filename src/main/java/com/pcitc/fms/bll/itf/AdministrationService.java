/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: AdministrationService
 * Date:18-3-5 上午10:50
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.bll.itf;

import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.service.model.Administration;
import com.pcitc.fms.service.model.Pager;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface AdministrationService {
	public Pager<com.pcitc.fms.bll.entity.Administration> getPageAdministrations(Administration administrationModel,
			Pageable pageable) throws BusinessException;

}
