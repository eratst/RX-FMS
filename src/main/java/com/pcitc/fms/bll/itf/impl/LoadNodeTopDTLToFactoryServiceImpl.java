package com.pcitc.fms.bll.itf.impl;

//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.pcitc.fms.bll.itf.LoadNodeTopDTLService;
//import com.pcitc.fms.dal.dao.NodeTopDTLDao;
//import com.pcitc.fms.dal.pojo.NodeTopDTL;
//import com.pcitc.fms.exception.BusinessException;

//@Service
//public class LoadNodeTopDTLToFactoryServiceImpl implements LoadNodeTopDTLService {
//	
//	@Autowired
//	private NodeTopDTLDao nodeTopDTLDao;
//	
//	@Override
//	public List<NodeTopDTL> getLoadRangeForGraph(com.pcitc.fms.service.model.NodeTopDTL nodeTopDTLModel) throws BusinessException {
//		List<com.pcitc.fms.dal.pojo.NodeTopDTL> ListNodeTop=null;
//		Integer SorgId= nodeTopDTLDao.findOrgIdByNodeName(nodeTopDTLModel.getSnodeName());
//		Integer TorgId= nodeTopDTLDao.findOrgIdByNodeName(nodeTopDTLModel.getTnodeName());
//		//判断是否在同一工厂下
//		if(SorgId!=null && TorgId!=null){
//			if(SorgId.toString().equals(TorgId.toString())){
//				 //查询同一工厂下的全部node
//				 ListNodeTop=nodeTopDTLDao.findAllNodeTopDTL(TorgId);
//			}else{
//				throw  new BusinessException("", "节点必须属于同一工厂下！");
//			}
//		}else{
//			throw  new BusinessException("", "节点信息错误！");
//		}
//		return ListNodeTop;
//	}
//
//}
