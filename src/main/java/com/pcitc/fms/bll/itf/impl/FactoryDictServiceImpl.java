/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: FactoryDictServiceImpl
 * Date:18-3-8 下午6:22
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.bll.itf.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.pcitc.fms.bll.itf.FactoryDictService;
import com.pcitc.imp.common.exception.BusiException;

import pcitc.imp.common.ettool.utils.ObjectConverter;

/**
 * The type Factory dict service.
 */
@Service
@Component
public class FactoryDictServiceImpl implements FactoryDictService {
//	private static Logger log = LoggerFactory.getLogger(FactoryDictServiceImpl.class);
//	@Autowired
//	private FactoryDictDao factoryDictDao;
//	@Autowired
//	private PlantBusinessDictDao plantDictDao;
//	@Autowired
//	private PlantTechnicDictDao plantTechnicDict;
//	@Autowired
//	private TankAreaBusineDictDao tankAreaBusineDictDao;
//	@Autowired
//	private TankAreaTechnicDictDao tankAreaTechnicDictDao;
//	@Autowired
//	private WarehouseBusineDictDao warehouseBusineDictDao;
//	@Autowired
//	private WarehouseTechniDictDao warehouseTechniDictDao;
//	@Autowired
//	private LoadingTechnicDictDao loadingTechnicDictDao;
//	@Autowired
//	private SidelineImportDictDao sidelineImportDictDao;
//	@Autowired
//	private SidelineTechnicDictDao sidelineTechnicDict;
//	@Autowired
//	private NodeObjectTypeDictDao nodeObjectTypeDict;
//	@Autowired
//	private TankNodeTypeDictDao tankNodeTypeDictDao;
//	@Autowired
//	private SiloNodeTypeDictDao siloNodeTypeDictDao;
//	@Autowired
//	private EdgepointTypeDictDao edgepointTypeDictDao;
//	@Autowired
//	private SamplepoinTypeDictDao samplepoinTypeDictDao;
//
//	/*
//	 * 设施类型
//	 */
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<FacilityTypeDict> getFacilityTypeDict(String value)
//			throws BusiException {
//		try {
//			CheckUtil.valueSize(value);
//			List<com.pcitc.fms.dal.pojo.FacilityTypeDict> list = factoryDictDao
//					.getFacilityTypeDict(Long.parseLong(value));
//			List<FacilityTypeDict> agents = ObjectConverter.listConverter(list,
//					FacilityTypeDict.class);
//			return agents;
//		} catch (Exception e) {
//			log.error(e.getMessage());
//			throw new BusiException("", e.getMessage(), e);
//		}
//	}
//	/*
//	 * 查询所有设施类型
//	 */
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<FacilityTypeDict> getFacilityTypeDictFind()
//			throws BusiException {
//		try {
//			List<com.pcitc.fms.dal.pojo.FacilityTypeDict> list = factoryDictDao
//					.findAll();
//			List<FacilityTypeDict> agents = ObjectConverter.listConverter(list,
//					FacilityTypeDict.class);
//			return agents;
//		} catch (Exception e) {
//			log.error(e.getMessage());
//			throw new BusiException("", e.getMessage(), e);
//		}
//	}
//	/*
//	 * 查询全部装置业务类型
//	 */
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<PlantBusinessDict> getPlantBusinessDictFind()
//			throws BusiException {
//		try {
//			List<com.pcitc.fms.dal.pojo.PlantBusinessDict> list = plantDictDao
//					.findAll();
//			List<PlantBusinessDict> agents = ObjectConverter.listConverter(
//					list, PlantBusinessDict.class);
//			return agents;
//		} catch (Exception e) {
//			log.error(e.getMessage());
//			throw new BusiException("", e.getMessage(), e);
//		}
//
//	}
//	/*
//	 * 装置业务类型
//	 */
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<PlantBusinessDict> getPlantBusinessDict(String value)
//			throws BusiException {
//		try {
//			CheckUtil.valueSize1(value);
//			List<com.pcitc.fms.dal.pojo.PlantBusinessDict> list = plantDictDao
//					.getPlantBusinessDict(Long.parseLong(value));
//			List<PlantBusinessDict> agents = ObjectConverter.listConverter(
//					list, PlantBusinessDict.class);
//			return agents;
//		} catch (Exception e) {
//			log.error(e.getMessage());
//			throw new BusiException("", e.getMessage(), e);
//		}
//
//	}
//	/*
//	 * 查询装置全部工艺类型
//	 */
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<PlantTechnicDict> getPlantTechnicDictFind()
//			throws BusiException {
//		try {
//			List<com.pcitc.fms.dal.pojo.PlantTechnicDict> list = plantTechnicDict
//					.findAll();
//			List<PlantTechnicDict> agents = ObjectConverter.listConverter(
//					list, PlantTechnicDict.class);
//			return agents;
//		} catch (Exception e) {
//			log.error(e.getMessage());
//			throw new BusiException("", e.getMessage(), e);
//		}
//	}
//	/*
//	 * 装置工艺类型
//	 */
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<PlantTechnicDict> getPlantTechnicDict(String value)
//			throws BusiException {
//		try {
//			CheckUtil.valueSize2(value);
//			List<com.pcitc.fms.dal.pojo.PlantTechnicDict> list = plantTechnicDict
//					.getPlantTechnicDict(Long.parseLong(value));
//			if(list == null || list.isEmpty()){
//				throw new BusiException("", value+" :请输入1-29正整数！");
//			}
//			List<PlantTechnicDict> agents = ObjectConverter.listConverter(
//					list, PlantTechnicDict.class);
//			return agents;
//		} catch (Exception e) {
//			log.error(e.getMessage());
//			throw new BusiException("", e.getMessage(), e);
//		}
//	}
//	/*
//	 * 罐区业务类型
//	 */
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<TankAreaBusineDict> getTankAreaBusineDict(String value)
//			throws BusiException {
//		try {
//			CheckUtil.valueSize2(value);
//			List<com.pcitc.fms.dal.pojo.TankAreaBusineDict> list = tankAreaBusineDictDao
//					.getTankAreaBusineDict(Long.parseLong(value));
//			if(list == null || list.isEmpty()){
//				throw new BusiException("", value+" :请输入0-3正整数！");
//			}
//			List<TankAreaBusineDict> agents = ObjectConverter.listConverter(
//					list, TankAreaBusineDict.class);
//			return agents;
//		} catch (Exception e) {
//			log.error(e.getMessage());
//			throw new BusiException("", e.getMessage(), e);
//		}
//	}
//	/*
//	 * 查询罐区全部业务类型
//	 */
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<TankAreaBusineDict> getTankAreaBusineDictFind()
//			throws BusiException {
//		try {
//			List<com.pcitc.fms.dal.pojo.TankAreaBusineDict> list = tankAreaBusineDictDao
//					.findAll();
//			List<TankAreaBusineDict> agents = ObjectConverter.listConverter(
//					list, TankAreaBusineDict.class);
//			return agents;
//		} catch (Exception e) {
//			log.error(e.getMessage());
//			throw new BusiException("", e.getMessage(), e);
//		}
//	}
//	/*
//	 * 查询罐区全部工艺类型
//	 */
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<TankAreaTechnicDict> getTankAreaTechnicDictFind()
//			throws BusiException {
//		try {
//			List<com.pcitc.fms.dal.pojo.TankAreaTechnicDict> list = tankAreaTechnicDictDao
//					.findAll();
//			List<TankAreaTechnicDict> agents = ObjectConverter.listConverter(
//					list, TankAreaTechnicDict.class);
//			return agents;
//		} catch (Exception e) {
//			log.error(e.getMessage());
//			throw new BusiException("", e.getMessage(), e);
//		}
//	}
//	/*
//	 * 罐区工艺类型
//	 */
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<TankAreaTechnicDict> getTankAreaTechnicDict(String value)
//			throws BusiException {
//		try {
//			CheckUtil.valueSize2(value);
//			List<com.pcitc.fms.dal.pojo.TankAreaTechnicDict> list = tankAreaTechnicDictDao
//					.getTankAreaTechnicDict(Long.parseLong(value));
//			if(list == null || list.isEmpty()){
//				throw new BusiException("",value+" :请输入1-3正整数！");
//			}
//			List<TankAreaTechnicDict> agents = ObjectConverter.listConverter(
//					list, TankAreaTechnicDict.class);
//			return agents;
//		} catch (Exception e) {
//			log.error(e.getMessage());
//			throw new BusiException("", e.getMessage(), e);
//		}
//	}
//	/*
//	 *  仓库业务类型
//	 */
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<WarehouseBusineDict> getWarehouseBusineDict(String value)
//			throws BusiException {
//		try {
//			CheckUtil.valueSize2(value);
//			List<com.pcitc.fms.dal.pojo.WarehouseBusineDict> list = warehouseBusineDictDao
//					.getWarehouseBusineDict(Long.parseLong(value));
//			if(list == null || list.isEmpty()){
//				throw new BusiException("",value+" :请输入1-4正整数！");
//			}
//			List<WarehouseBusineDict> agents = ObjectConverter.listConverter(
//					list, WarehouseBusineDict.class);
//			return agents;
//		} catch (Exception e) {
//			log.error(e.getMessage());
//			throw new BusiException("", e.getMessage(), e);
//		}
//	}
//	/*
//	 * 查询仓库全部业务类型
//	 */
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<WarehouseBusineDict> getWarehouseBusineDictFind()
//			throws BusiException {
//		try {
//			List<com.pcitc.fms.dal.pojo.WarehouseBusineDict> list = warehouseBusineDictDao
//					.findAll();
//			List<WarehouseBusineDict> agents = ObjectConverter.listConverter(
//					list, WarehouseBusineDict.class);
//			return agents;
//		} catch (Exception e) {
//			log.error(e.getMessage());
//			throw new BusiException("", e.getMessage(), e);
//		}
//	}
//	/*
//	 * 查询仓库全部工艺类型
//	 */
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<WarehouseTechniDict> getWarehouseTechniDictFind()
//			throws BusiException {
//		try {
//			List<com.pcitc.fms.dal.pojo.WarehouseTechniDict> list = warehouseTechniDictDao
//					.findAll();
//			List<WarehouseTechniDict> agents = ObjectConverter.listConverter(
//					list, WarehouseTechniDict.class);
//			return agents;
//		} catch (Exception e) {
//			log.error(e.getMessage());
//			throw new BusiException("", e.getMessage(), e);
//		}
//	}
//	/*
//	 *仓库工艺类型 
//	 */
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<WarehouseTechniDict> getWarehouseTechniDict(String value)
//			throws BusiException {
//		try {
//			CheckUtil.valueSize2(value);
//			List<com.pcitc.fms.dal.pojo.WarehouseTechniDict> list = warehouseTechniDictDao
//					.getWarehouseTechniDict(Long.parseLong(value));
//			if(list == null || list.isEmpty()){
//				throw new BusiException("",value+" :请输入1-2正整数！");
//			}
//			List<WarehouseTechniDict> agents = ObjectConverter.listConverter(
//					list, WarehouseTechniDict.class);
//			return agents;
//		} catch (Exception e) {
//			log.error(e.getMessage());
//			throw new BusiException("", e.getMessage(), e);
//		}
//	}
//	/*
//	 * 查询装卸台全部工艺类型
//	 */
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<LoadingTechnicDict> getLoadingTechnicDictFind()
//			throws BusiException {
//		try {
//			List<com.pcitc.fms.dal.pojo.LoadingTechnicDict> list = loadingTechnicDictDao
//					.findAll();
//			List<LoadingTechnicDict> agents = ObjectConverter.listConverter(
//					list, LoadingTechnicDict.class);
//			return agents;
//		} catch (Exception e) {
//			log.error(e.getMessage());
//			throw new BusiException("", e.getMessage(), e);
//		}
//	}
//	/*
//	 *装卸台工艺类型
//	 */
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<LoadingTechnicDict> getLoadingTechnicDict(String value)
//			throws BusiException {
//		try {
//			CheckUtil.valueSize2(value);
//			List<com.pcitc.fms.dal.pojo.LoadingTechnicDict> list = loadingTechnicDictDao
//					.getLoadingTechnicDict(Long.parseLong(value));
//			if(list == null || list.isEmpty()){
//				throw new BusiException("",value+" :请输入1-4正整数！");
//			}
//			List<LoadingTechnicDict> agents = ObjectConverter.listConverter(
//					list, LoadingTechnicDict.class);
//			return agents;
//		} catch (Exception e) {
//			log.error(e.getMessage());
//			throw new BusiException("", e.getMessage(), e);
//		}
//	}
//	/*
//	 * 侧线进出类型
//	 */
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<SidelineImportDict> getSidelineImportDict(String value)
//			throws BusiException {
//		try {
//			CheckUtil.valueSize2(value);
//			List<com.pcitc.fms.dal.pojo.SidelineImportDict> list = sidelineImportDictDao
//					.getSidelineImportDict(Long.parseLong(value));
//			if(list == null || list.isEmpty()){
//				throw new BusiException("",value+" :请输入0-1正整数！");
//			}
//			List<SidelineImportDict> agents = ObjectConverter.listConverter(
//					list, SidelineImportDict.class);
//			return agents;
//		} catch (Exception e) {
//			log.error(e.getMessage());
//			throw new BusiException("", e.getMessage(), e);
//		}
//	}
//	/*
//	 * 查询侧线全部进出类型
//	 */
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<SidelineImportDict> getSidelineImportDictFind()
//			throws BusiException {
//		try {
//			List<com.pcitc.fms.dal.pojo.SidelineImportDict> list = sidelineImportDictDao
//					.findAll();
//			List<SidelineImportDict> agents = ObjectConverter.listConverter(
//					list, SidelineImportDict.class);
//			return agents;
//		} catch (Exception e) {
//			log.error(e.getMessage());
//			throw new BusiException("", e.getMessage(), e);
//		}
//	}
//	/*
//	 * 侧线全部工艺类型
//	 */
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<SidelineTechnicDict> getSidelineTechnicDictFind()
//			throws BusiException {
//		try {
//			List<com.pcitc.fms.dal.pojo.SidelineTechnicDict> list = sidelineTechnicDict
//					.findAll();
//			List<SidelineTechnicDict> agents = ObjectConverter.listConverter(
//					list, SidelineTechnicDict.class);
//			return agents;
//		} catch (Exception e) {
//			log.error(e.getMessage());
//			throw new BusiException("", e.getMessage(), e);
//		}
//	}
//	/*
//	 * 侧线工艺类型
//	 */
//	@SuppressWarnings("unchecked")
//	@Override 
//	public List<SidelineTechnicDict> getSidelineTechnicDict(String value)
//			throws BusiException {
//		try {
//			CheckUtil.valueSize2(value);
//			List<com.pcitc.fms.dal.pojo.SidelineTechnicDict> list = sidelineTechnicDict
//					.getSidelineTechnicDict(Long.parseLong(value));
//			if(list == null || list.isEmpty()){
//				throw new BusiException("", value+" :请输入1-5正整数！");
//			}
//			List<SidelineTechnicDict> agents = ObjectConverter.listConverter(
//					list, SidelineTechnicDict.class);
//			return agents;
//		} catch (Exception e) {
//			log.error(e.getMessage());
//			throw new BusiException("", e.getMessage(), e);
//		}
//	}
//	/*
//	 * 节点对象类型
//	 */
//	@SuppressWarnings("unchecked")
//	@Override 
//	public List<NodeObjectTypeDict> getNodeObjectTypeDict(String value)
//			throws BusiException {
//		try {
//			CheckUtil.valueSize2(value);
//			List<com.pcitc.fms.dal.pojo.NodeObjectTypeDict> list = nodeObjectTypeDict
//					.getNodeObjectTypeDict(Long.parseLong(value));
//			if(list == null || list.isEmpty()){
//				throw new BusiException("", value+" :请输入1-10正整数！");
//			}
//			List<NodeObjectTypeDict> agents = ObjectConverter.listConverter(
//					list, NodeObjectTypeDict.class);
//			return agents;
//		} catch (Exception e) {
//			log.error(e.getMessage());
//			throw new BusiException("", e.getMessage(), e);
//		}
//	}
//	/*
//	 * 全部节点对象类型
//	 */
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<NodeObjectTypeDict> getNodeObjectTypeDictFInd()
//			throws BusiException {
//		try {
//			List<com.pcitc.fms.dal.pojo.NodeObjectTypeDict> list = nodeObjectTypeDict
//					.findAll();
//			List<NodeObjectTypeDict> agents = ObjectConverter.listConverter(
//					list, NodeObjectTypeDict.class);
//			return agents;
//		} catch (Exception e) {
//			log.error(e.getMessage());
//			throw new BusiException("", e.getMessage(), e);
//		}
//	}
//	/*
//	 * 储罐节点类型
//	 */
//	@SuppressWarnings("unchecked")
//	@Override 
//	public List<TankNodeTypeDict> getTankNodeTypeDict(String value)
//			throws BusiException {
//		try {
//			CheckUtil.valueSize2(value);
//			List<com.pcitc.fms.dal.pojo.TankNodeTypeDict> list = tankNodeTypeDictDao
//					.getTankNodeTypeDict(Long.parseLong(value));
//			if(list == null || list.isEmpty()){
//				throw new BusiException("", value+" :请输入1-4正整数！");
//			}
//			List<TankNodeTypeDict> agents = ObjectConverter.listConverter(
//					list, TankNodeTypeDict.class);
//			return agents;
//		} catch (Exception e) {
//			log.error(e.getMessage());
//			throw new BusiException("", e.getMessage(), e);
//		}
//	}
//	/*
//	 * 储罐节点全部类型
//	 */
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<TankNodeTypeDict> getTankNodeTypeDictFind()
//			throws BusiException {
//		try {
//			List<com.pcitc.fms.dal.pojo.TankNodeTypeDict> list = tankNodeTypeDictDao
//					.findAll();
//			List<TankNodeTypeDict> agents = ObjectConverter.listConverter(
//					list, TankNodeTypeDict.class);
//			return agents;
//		} catch (Exception e) {
//			log.error(e.getMessage());
//			throw new BusiException("", e.getMessage(), e);
//		}
//	}
//	/*
//	 * 料仓节点类型
//	 */
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<SiloNodeTypeDict> getSiloNodeTypeDict(String value)
//			throws BusiException {
//		try {
//			CheckUtil.valueSize2(value);
//			List<com.pcitc.fms.dal.pojo.SiloNodeTypeDict> list = siloNodeTypeDictDao
//					.getSiloNodeTypeDict(Long.parseLong(value));
//			if(list == null || list.isEmpty()){
//				throw new BusiException("", value+" :请输入0-1正整数！");
//			}
//			List<SiloNodeTypeDict> agents = ObjectConverter.listConverter(
//					list, SiloNodeTypeDict.class);
//			return agents;
//		} catch (Exception e) {
//			log.error(e.getMessage());
//			throw new BusiException("", e.getMessage(), e);
//		}
//	}
//	/*
//	 * 料仓节点全部类型
//	 */
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<SiloNodeTypeDict> getSiloNodeTypeDictFind()
//			throws BusiException {
//		try {
//			List<com.pcitc.fms.dal.pojo.SiloNodeTypeDict> list = siloNodeTypeDictDao
//					.findAll();
//			List<SiloNodeTypeDict> agents = ObjectConverter.listConverter(
//					list, SiloNodeTypeDict.class);
//			return agents;
//		} catch (Exception e) {
//			log.error(e.getMessage());
//			throw new BusiException("", e.getMessage(), e);
//		}
//	}
//	/*
//	 * 进出点进出类型
//	 */
//	@SuppressWarnings("unchecked")
//	@Override 
//	public List<EdgepointTypeDict> getEdgepointTypeDict(String value)
//			throws BusiException {
//		try {
//			CheckUtil.valueSize2(value);
//			List<com.pcitc.fms.dal.pojo.EdgepointTypeDict> list = edgepointTypeDictDao
//					.getEdgepointTypeDict(Long.parseLong(value));
//			if(list == null || list.isEmpty()){
//				throw new BusiException("",value+" :请输入0-1正整数！");
//			}
//			List<EdgepointTypeDict> agents = ObjectConverter.listConverter(
//					list, EdgepointTypeDict.class);
//			return agents;
//		} catch (Exception e) {
//			log.error(e.getMessage());
//			throw new BusiException("", e.getMessage(), e);
//		}
//	}
//	/*
//	 * 进出点全部进出类型
//	 */
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<EdgepointTypeDict> getEdgepointTypeDictFind()
//			throws BusiException {
//		try {
//			List<com.pcitc.fms.dal.pojo.EdgepointTypeDict> list = edgepointTypeDictDao
//					.findAll();
//			List<EdgepointTypeDict> agents = ObjectConverter.listConverter(
//					list, EdgepointTypeDict.class);
//			return agents;
//		} catch (Exception e) {
//			log.error(e.getMessage());
//			throw new BusiException("", e.getMessage(), e);
//		}
//	}
//	/*
//	 * 采样点类型
//	 */
//	@SuppressWarnings("unchecked")
//	@Override 
//	public List<SamplepoinTypeDict> getSamplepoinTypeDict(String value)
//			throws BusiException {
//		try {
//			CheckUtil.valueSize2(value);
//			List<com.pcitc.fms.dal.pojo.SamplepoinTypeDict> list = samplepoinTypeDictDao
//					.getSamplepoinTypeDict(Long.parseLong(value));
//			if(list == null || list.isEmpty()){
//				throw new BusiException("",value+" :请输入1-4正整数！");
//			}
//			List<SamplepoinTypeDict> agents = ObjectConverter.listConverter(
//					list, SamplepoinTypeDict.class);
//			return agents;
//		} catch (Exception e) {
//			log.error(e.getMessage());
//			throw new BusiException("", e.getMessage(), e);
//		}
//	}
//	/*
//	 * 全部采样点类型
//	 */
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<SamplepoinTypeDict> getSamplepoinTypeDictFind()
//			throws BusiException {
//		try {
//			List<com.pcitc.fms.dal.pojo.SamplepoinTypeDict> list = samplepoinTypeDictDao
//					.findAll();
//			List<SamplepoinTypeDict> agents = ObjectConverter.listConverter(
//					list, SamplepoinTypeDict.class);
//			return agents;
//		} catch (Exception e) {
//			log.error(e.getMessage());
//			throw new BusiException("", e.getMessage(), e);
//		}
//	}
//	
//	
}
