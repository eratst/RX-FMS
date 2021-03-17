package com.pcitc.fms.bll.itf;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pcitc.fms.bll.entity.LiquidProdCoef;
import com.pcitc.fms.service.model.Pager;

@Service
public interface LiquidProdCoefService {

	/**
	 * @param liquidProdCoef 
	 * @Title: searchData
	 * @Description: 查询液化产品系数
	 * @param
	 * @return List
	 * @throws Exception
	 */
	public Pager<LiquidProdCoef> findLiquidProdCoefs(com.pcitc.fms.service.model.LiquidProdCoef liquidProdCoef, Pageable pageable) throws Exception;

}
