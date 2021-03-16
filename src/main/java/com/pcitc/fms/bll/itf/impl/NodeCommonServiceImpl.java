/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: NodeCommonServiceImpl
 * Date:18-3-8 下午6:22
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.bll.itf.impl;

import com.pcitc.fms.exception.BusinessException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.pcitc.fms.dal.dao.AdministrationDao;
import com.pcitc.fms.dal.dao.CommunityDao;
import com.pcitc.fms.dal.dao.FactoryDao;
import com.pcitc.fms.dal.dao.LoadingDockDao;
import com.pcitc.fms.dal.dao.PipeNetworkDao;
import com.pcitc.fms.dal.dao.PlantDao;
import com.pcitc.fms.dal.dao.TankAreaDao;
import com.pcitc.fms.dal.dao.WarehouseDao;

/**
 * The type Node common service.
 */
@Service
@Component
public class NodeCommonServiceImpl {

    /**
     * The F dao.
     */
    @Autowired
    private FactoryDao fDao;
    /**
     * The Administration dao.
     */
    @Autowired
    private AdministrationDao administrationDao;
    /**
     * The Community dao.
     */
    @Autowired
    private CommunityDao communityDao;
    /**
     * The Warehouse dao.
     */
    @Autowired
    private WarehouseDao warehouseDao;
    /**
     * The Tank area dao.
     */
    @Autowired
    private TankAreaDao tankAreaDao;
    /**
     * The Pipe network dao.
     */
    @Autowired
    private PipeNetworkDao pipeNetworkDao;
    /**
     * The Plant dao.
     */
    @Autowired
    private PlantDao plantDao;
    /**
     * The Loading dock dao.
     */
    @Autowired
    private LoadingDockDao loadingDockDao;

    /**
     * Node check url.
     *
     * @param parentCode the parent code
     * @param factoryCode the factory code
     * @param parentType the parent type
     * @throws BusinessException the business exception
     * @Title: nodeCheckURL
     * @Description: 节点的URL层级校验
     * @date 2017年7月28日
     * @return: BusinessException
     * @author Ping Wang
     */
    public void nodeCheckURL(String parentCode, String factoryCode, String parentType) throws BusinessException {
	String facCode = null;
//	switch (parentType) {
//	case "administration":
//	    com.pcitc.fms.dal.pojo.Administration areaA = administrationDao.findByCode(parentCode);
//	    if (areaA == null) {
//		throw new Exception("编码为"+parentCode+"的办公区不存在");
//	    }
//	    facCode = areaA.getFactoryCode();
//	    break;
//	case "community":
//	    com.pcitc.fms.dal.pojo.Community areaC = communityDao.findByCode(parentCode);
//	    if (areaC == null) {
//		throw new Exception("编码为"+parentCode+"的生活区不存在");
//	    }
//	    facCode = areaC.getFactoryCode();
//	    break;
//	case "warehouse":
//	    com.pcitc.fms.dal.pojo.Warehouse areaW = warehouseDao.findByCode(parentCode);
//	    if (areaW == null) {
//		throw new Exception("编码为"+parentCode+"的仓库不存在");
//	    }
//	    facCode = areaW.getFactoryCode();
//	    break;
//	case "tankArea":
//	    List<com.pcitc.fms.dal.pojo.TankArea> areaT = tankAreaDao.findTankAreaByCode(parentCode,factoryCode);
//	    if (areaT.size() == 0) {
//		throw new Exception("编码为"+parentCode+"的罐区不存在");
//	    }
//	    facCode = areaT.get(0).getFactoryCode();
//	    break;
//	case "pipeNetwork":
//	    com.pcitc.fms.dal.pojo.PipeNetwork areaPi = pipeNetworkDao.findPipeNetworkeByFactoryCodeAndCode(factoryCode,parentCode);
//	    if (areaPi == null) {
//		throw new Exception("编码为"+parentCode+"的管网不存在");
//	    }
//	    facCode = areaPi.getFactoryCode();
//	    break;
//	case "plant":
//	    com.pcitc.fms.dal.pojo.Plant areaPl = plantDao.findPlantByCode(parentCode);
//	    if (areaPl == null) {
//		throw new Exception("编码为"+parentCode+"的装置不存在");
//	    }
//	    facCode = areaPl.getFactoryCode();
//	    break;
//	case "loadingDock":
//	    com.pcitc.fms.dal.pojo.LoadingDock areaL = loadingDockDao.findLoadingDockByFactoryCodeAndCode(factoryCode,parentCode);
//	    if (areaL == null) {
//		throw new Exception("编码为"+parentCode+"的装卸台不存在");
//	    }
//	    facCode = areaL.getFactoryCode();
//	    break;
//	default:
//		throw new Exception("上一级类型"+parentType+"不存在");
//	}
//	// 工厂存在性校验
//	if (!factoryCode.equals(facCode)) {
//		throw new Exception("编码为"+parentCode+"的"+parentType+"所对应工厂Id不匹配");
//	} else {
//	    com.pcitc.fms.dal.pojo.Factory factory = fDao.findByCode(factoryCode);
//	    if (factory == null) {
//		throw new Exception("编码为"+factoryCode+"的工厂不存在");
//	    }
//	}
    }
}
