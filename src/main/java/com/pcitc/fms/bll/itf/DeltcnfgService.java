package com.pcitc.fms.bll.itf;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.pcitc.fms.service.model.Deltcnfg;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.imp.common.exception.BusiException;

import pcitc.imp.common.ettool.Exception.BusinessException;

public interface DeltcnfgService {

	Pager<com.pcitc.fms.bll.entity.Deltcnfg> getPageDeltcnfg(Deltcnfg deltcnfgModel, Pageable pageable) throws BusiException;

}
