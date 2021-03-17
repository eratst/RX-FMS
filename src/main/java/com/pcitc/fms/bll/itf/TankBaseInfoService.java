package com.pcitc.fms.bll.itf;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pcitc.fms.bll.entity.TankBaseInfo;
import com.pcitc.fms.service.model.Pager;

@Service
public interface TankBaseInfoService {

	public Pager<TankBaseInfo> getTankBaseInfo(com.pcitc.fms.service.model.TankBaseInfo tankmo,Pageable pageable) throws Exception;
}
