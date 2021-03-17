package com.pcitc.fms.bll.itf;

import org.springframework.data.domain.Pageable;

import com.pcitc.fms.service.model.Pager;
import com.pcitc.fms.service.model.StdcmmmCubas;

public interface StdcmmmCubasService {

	Pager<com.pcitc.fms.bll.entity.StdcmmmCubas> getstdcmmmCubas(StdcmmmCubas stdcmmmCubasModel, Pageable pageable)throws Exception;

}
