package com.pcitc.fms.bll.itf;

import org.springframework.data.domain.Pageable;

import com.pcitc.fms.service.model.MtrlVcf;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.imp.common.exception.BusiException;

public interface MtrlVcfService {

	Pager<com.pcitc.fms.bll.entity.MtrlVcf> getPageMtrlVcf(MtrlVcf model, Pageable pageable) throws BusiException;

}
