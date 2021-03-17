package com.pcitc.fms.bll.itf;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pcitc.fms.bll.entity.UnitAlarm;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.service.model.Administration;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.imp.common.exception.BusiException;

@Service
public interface UnitAlarmService {

	public Pager<UnitAlarm> getPageUnitAlarms(com.pcitc.fms.service.model.UnitAlarm unitAlarm) throws BusinessException;

	public UnitAlarm getByCode(String code) throws BusinessException;



}
