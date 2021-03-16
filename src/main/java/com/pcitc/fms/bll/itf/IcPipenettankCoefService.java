package com.pcitc.fms.bll.itf;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.pcitc.fms.bll.entity.IcPipenettankCoef;
import com.pcitc.fms.service.model.Pager;

public interface IcPipenettankCoefService {
	
	public Pager<IcPipenettankCoef> findIcPipenettankCoefs(com.pcitc.fms.service.model.IcPipenettankCoef icPipenettankCoef, Pageable pageable)
			throws Exception;

	public List<IcPipenettankCoef> findIcPipenettankCoefById(Long pipenettankCoefId) throws Exception;

}
