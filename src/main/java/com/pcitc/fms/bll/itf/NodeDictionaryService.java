package com.pcitc.fms.bll.itf;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pcitc.fms.bll.entity.AreaDictionary;
import com.pcitc.fms.bll.entity.LoadingDock;
import com.pcitc.fms.bll.entity.NodeDictionary;
import com.pcitc.fms.service.model.Administration;
import com.pcitc.fms.service.model.Pager;

import com.pcitc.fms.exception.BusinessException;

@Service
public interface NodeDictionaryService {

	public List<NodeDictionary> getNodeDictionaryByCode(String nodeDictionaryCode) throws BusinessException;

	public List<NodeDictionary> getNodeDictionaryByCode(String areaDictionaryTableCode, String nodeDictionaryCode)
			throws BusinessException;

	public Pager<NodeDictionary> getNodeDictionaryTablesByModel(
			com.pcitc.fms.service.model.NodeDictionary nodeDictionaryTableModel, Pageable pageable) throws BusinessException;



	
}
