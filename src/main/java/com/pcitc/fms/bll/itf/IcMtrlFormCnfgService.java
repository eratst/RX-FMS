package com.pcitc.fms.bll.itf;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.pcitc.fms.bll.entity.IcMtrlFormCnfg;
import com.pcitc.fms.service.model.Pager;

public interface IcMtrlFormCnfgService {
	
	public Pager<IcMtrlFormCnfg> findIcMtrlFormCnfgs(com.pcitc.fms.service.model.IcMtrlFormCnfg icMtrlFormCnfg, Pageable pageable)
			throws Exception;

	public List<IcMtrlFormCnfg> findIcMtrlFormCnfgById(Long mtrlFormCnfgId) throws Exception;


}
