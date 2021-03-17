package com.pcitc.fms.bll.itf;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pcitc.fms.bll.entity.LiqprodCubaTempCoef;
import com.pcitc.fms.service.model.Pager;

@Service
public interface LiqprodCubaTempCoefService {

	/**
	 * @Title: searchData
	 * @Description: 查询体积温度系数
	 * @param
	 * @return List
	 * @throws Exception
	 */
	public Pager<LiqprodCubaTempCoef> findLiqprodCubaTempCoefs(Pageable pageable) throws Exception;
}
