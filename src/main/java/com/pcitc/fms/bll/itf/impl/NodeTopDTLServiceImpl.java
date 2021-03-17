package com.pcitc.fms.bll.itf.impl;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pcitc.fms.bll.entity.NodeTopDTL;
import com.pcitc.fms.bll.itf.FindPathService;
import com.pcitc.fms.bll.itf.LoadNodeTopDTLService;
import com.pcitc.fms.bll.itf.NodeTopDTLService;
import com.pcitc.fms.bll.itf.PathFilterService;
//import com.pcitc.fms.common.Graph;
import com.pcitc.fms.common.MyPageImpl;
//import com.pcitc.fms.dal.dao.NodeTopDTLDao;
import com.pcitc.fms.dal.dao.NodeTopDTLRepository;
import com.pcitc.fms.dal.dao.TeamAndUserDao;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.imp.common.exception.BusiException;

import pcitc.imp.common.ettool.utils.ObjectConverter;

@Service
public class NodeTopDTLServiceImpl implements NodeTopDTLService {
	
	@Autowired
	private  NodeTopDTLRepository  nodeTopDTLRepo;
	
//	@Autowired
//	private LoadNodeTopDTLService  loadNodeTopDTLService;
//	
//	@Autowired
//	private PathFilterService  pathFilterService;
//	
//	@Autowired
//	private FindPathService findPathService;
	
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<String> getNodeTopDTLs(com.pcitc.fms.service.model.NodeTopDTL nodeTopDTLModel, Pageable pageable) throws BusinessException {
//			//判断加载范围
//			List<com.pcitc.fms.dal.pojo.NodeTopDTL> ListNodeTop = loadNodeTopDTLService.getLoadRangeForGraph(nodeTopDTLModel);
//			//找路径
//			List<String> visit = findPathService.findPath(nodeTopDTLModel, ListNodeTop);
//			//过滤条件
//			return pathFilterService.PathFilter(visit);
//	}

	@Override
	public Pager<NodeTopDTL> getAll(com.pcitc.fms.service.model.NodeTopDTL nodeTopDTL,
			Pageable pageable) throws Exception {
		Pager<NodeTopDTL> pageData = new Pager<>();
		MyPageImpl properties=null;
		try{
			properties = nodeTopDTLRepo.getNodeTopDTLs(nodeTopDTL,pageable);
			
		}catch (Exception e) {
			if (e instanceof UndeclaredThrowableException) {
				throw new BusinessException("", e.getCause().getMessage());
			}else{
				throw new BusinessException("", e.getMessage());
			}
		}
		List<NodeTopDTL> entityList = ObjectConverter.listConverter(properties.getContent(), NodeTopDTL.class);
		pageData.setContent(entityList);
		pageData.setFirst(properties.isFirst());
		pageData.setLast(properties.isLast());
		pageData.setNumber(properties.getNumber());
		pageData.setNumberOfElements(properties.getNumberOfElements());
		pageData.setSize(properties.getSize());
		pageData.setSort(properties.getSort());
		pageData.setTotalElements(properties.getTotalElements());
		pageData.setTotalPages(properties.getTotalPages());
		return pageData;
	}
}
