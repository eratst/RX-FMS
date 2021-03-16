package com.pcitc.fms.bll.itf;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.fms.service.model.Rent;

public interface RentService {

	Pager<com.pcitc.fms.bll.entity.Rent> getRents(Rent rent, Pageable pageable) throws BusinessException;

	List<com.pcitc.fms.bll.entity.Rent> addRent(List<com.pcitc.fms.bll.entity.Rent> et_areaList) throws BusinessException, Exception;

	void deleteByRentCode(String rentCode) throws Exception;

}
