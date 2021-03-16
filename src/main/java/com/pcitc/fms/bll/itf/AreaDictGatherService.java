package com.pcitc.fms.bll.itf;

import com.pcitc.fms.bll.dictionary.entity.Controldep;
import com.pcitc.fms.bll.dictionary.entity.OpenindexClass;
import com.pcitc.fms.bll.dictionary.entity.Staalgr;
import com.pcitc.fms.exception.BusinessException;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface AreaDictGatherService {
	public List<com.pcitc.fms.bll.dictionary.entity.WarehouseType> getWarehouseTypes(String code)throws BusinessException;

	public List<com.pcitc.fms.bll.dictionary.entity.OrgType> getOrgTypes(String code)throws  BusinessException;

	public List<com.pcitc.fms.bll.dictionary.entity.AreaType> getAreaTypes(String code)throws BusinessException;

	public List<com.pcitc.fms.bll.dictionary.entity.UnitType> getUnitTypes(String code)throws BusinessException;

	public List<com.pcitc.fms.bll.dictionary.entity.Technic> getTechnics(String code)throws BusinessException;

	public List<com.pcitc.fms.bll.dictionary.entity.TankAreaType> getTankAreaTypes(String code)throws BusinessException;

	public List<com.pcitc.fms.bll.dictionary.entity.TankAreaTechnic> getTankAreaTechnics(String code)throws BusinessException;

	public List<com.pcitc.fms.bll.dictionary.entity.WarehousTechnic> getWarehousTechnics(String code)throws BusinessException;

	public List<com.pcitc.fms.bll.dictionary.entity.LoadrackTechnic> getLoadrackTechnics(String code)throws BusinessException;

	public List<com.pcitc.fms.bll.dictionary.entity.PipenetTechnic> getPipenetTechnics(String code)throws BusinessException;

	public List<OpenindexClass> getOpenindexClass(String code)throws BusinessException;

	public List<Controldep> getControldeps(String code)throws BusinessException;

	public List<Staalgr> getStaalgrs(String code)throws BusinessException;

}
