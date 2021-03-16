package com.pcitc.fms.bll.itf;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pcitc.fms.bll.entity.TankCnfg;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.imp.common.exception.BusiException;

/**
 * [计量模型-罐量指标]单罐配置对象
 * 
 * @author chao.guo
 *
 */
@Service
public interface TankCnfgService {


	public Pager<TankCnfg> getPageTankCnfg(com.pcitc.fms.service.model.TankCnfg tankCnfgModel, Pageable pageable) throws BusiException;
}
