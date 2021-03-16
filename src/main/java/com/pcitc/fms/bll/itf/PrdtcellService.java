package com.pcitc.fms.bll.itf;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pcitc.fms.bll.entity.Prdtcell;
import com.pcitc.fms.service.model.Pager;

@Service
public interface PrdtcellService {
	// 查询全部
	public Pager<Prdtcell> getPrdtcellByParam(com.pcitc.fms.service.model.Prdtcell prdtcell, Pageable pageable) throws Exception;
	
	// 条件查询
	public List<Prdtcell> getPrdtcellByCellCode(String cellCode) throws Exception;
	
}
