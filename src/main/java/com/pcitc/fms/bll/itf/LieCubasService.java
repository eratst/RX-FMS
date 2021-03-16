package com.pcitc.fms.bll.itf;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.service.model.DataDegree;
import com.pcitc.fms.service.model.LieCubas;
import com.pcitc.fms.service.model.Pager;

public interface LieCubasService {

	Pager<com.pcitc.fms.bll.entity.LieCubas> getPageLieCubas(LieCubas lieCubasModel, Pageable pageable) throws Exception;

	List getLieCubaDegree(DataDegree model) throws Exception;

}
