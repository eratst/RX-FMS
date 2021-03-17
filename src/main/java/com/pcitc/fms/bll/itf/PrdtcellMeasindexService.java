package com.pcitc.fms.bll.itf;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.pcitc.fms.bll.entity.PrdtcellMeasindex;
import com.pcitc.fms.service.model.Pager;

/**
 *
 * zhao.li
 */
public interface PrdtcellMeasindexService {

	// 查询全部
	public Pager<PrdtcellMeasindex> findPrdtcellMeasindexs(
			com.pcitc.fms.service.model.PrdtcellMeasindex prdtcellMeasindex, Pageable pageable) throws Exception;

	// 条件查询
	public List<PrdtcellMeasindex> findPrdtcellMeasindexByIdxCode(String idxCode) throws Exception;

}
