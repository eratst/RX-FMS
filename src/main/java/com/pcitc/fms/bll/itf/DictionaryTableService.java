package com.pcitc.fms.bll.itf;

import com.pcitc.fms.exception.BusinessException;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;

import com.pcitc.fms.bll.entity.DictionaryTable;
import com.pcitc.fms.service.model.Pager;


public interface DictionaryTableService {

	public Pager<com.pcitc.fms.bll.entity.DictionaryTable> getDictionaryTables(
			com.pcitc.fms.service.model.DictionaryTable dictionaryTableModel,Pageable pageable) throws BusinessException;


	public List<com.pcitc.fms.bll.entity.DictionaryTable> getDictionaryTable(String dictionaryTableId) throws BusinessException;

	public void updateDictionaryTable(List<com.pcitc.fms.bll.entity.DictionaryTable> dictionaryTableEntityList)
			throws BusinessException;

	public void deleteDictionaryTable(String dictionaryTableId) throws BusinessException;


	public List addDictionaryTable(List<DictionaryTable> dictionaryTableEntityList)
			throws BusinessException;

}
