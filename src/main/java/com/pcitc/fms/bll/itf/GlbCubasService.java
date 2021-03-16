package com.pcitc.fms.bll.itf;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.service.model.DataDegree;
import com.pcitc.fms.service.model.GlbCubas;
import com.pcitc.fms.service.model.Pager;
public interface GlbCubasService {

	Pager<com.pcitc.fms.bll.entity.GlbCubas> getPageGlbCubas(GlbCubas glbCubasModel, Pageable pageable) throws Exception;
	
	public List getGlbCubaDegree(DataDegree model) throws Exception;
}
