package com.pcitc.fms.bll.itf;

import com.pcitc.fms.bll.entity.AreaDictionary;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.service.model.Pager;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface OrgFindAreaService {


	public Pager<AreaDictionary> getAreaDictionarys(com.pcitc.fms.service.model.AreaDictionary areaDictionaryTableModel,
			Pageable pageable, String orgCode)throws BusinessException;

	public Pager<AreaDictionary> getOrgFindAreaNodes(com.pcitc.fms.service.model.AreaDictionary areaDictionaryTableModel,
			Pageable pageable, String orgCode)throws BusinessException;

}

