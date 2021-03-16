package com.pcitc.fms.bll.itf;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.pcitc.fms.service.model.DataDegree;
import com.pcitc.fms.service.model.MtrlMolar;
import com.pcitc.fms.service.model.MtrlMolarDegree;
import com.pcitc.fms.service.model.Pager;


public interface MtrlMolarService {
	
	Pager<com.pcitc.fms.bll.entity.MtrlMolar> getMtrlMolars(MtrlMolar mtrlMolar, Pageable pageable) throws Exception;

	List getMtrlMolarDegree(MtrlMolarDegree model);

}
