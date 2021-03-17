package com.pcitc.fms.bll.itf;

import com.pcitc.fms.exception.BusinessException;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pcitc.fms.bll.entity.Area;
import com.pcitc.fms.bll.entity.AreaDictionary;
import com.pcitc.fms.bll.entity.LoadingDock;
import com.pcitc.fms.service.model.Administration;
import com.pcitc.fms.service.model.Pager;

@Service
public interface AreaDictionaryService {

	public void updateAreaDictionaryTable(List<AreaDictionary> areaDictionaryTableEntityList) throws BusinessException;

	public List<AreaDictionary> getAreaDictionaryTableByCode(String factoryCode, String areaDictionaryTableCode) throws BusinessException;

	public void deleteAreaDictionaryTableByFactoryAndCode(String factoryCode, String areaDictionaryTableCode);

	public List<AreaDictionary> addAreaDictionaryTable(List<AreaDictionary> areaDictionaryTableEntityList)
			throws BusinessException;

	public List<AreaDictionary> getAreaDictionaryTableByCode(String areaDictionaryTableCode) throws BusinessException;

	public Pager<AreaDictionary> getAreaDictionaryTablesByModel(Integer sourceId,
			Integer leavesId, String sourceType, String orgCode,
			String orgUnitCode, Pageable pageable, List<String> typeList,
			List<Integer> areaIdList)throws BusinessException;

	public Pager<Area> getAreas(com.pcitc.fms.service.model.Area area, Pageable pageable) throws Exception;

}
