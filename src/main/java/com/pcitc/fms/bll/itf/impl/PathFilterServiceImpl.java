package com.pcitc.fms.bll.itf.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcitc.fms.bll.itf.PathFilterService;
//import com.pcitc.fms.dal.dao.NodeTopDTLDao;

//@Service
//public class PathFilterServiceImpl implements PathFilterService {
//	
//	@Autowired
//	private NodeTopDTLDao nodeTopDTLDao;
//	
//	@Override
//	public List<String> PathFilter(List<String> paths) {
//		List<String> newPath=new ArrayList<String>();
//		newPath.addAll(paths);
//		for(String path:newPath){
//			//取节点名称
//			String[] nodeNameArray = path.split("->");
//			for(int i=0;i<nodeNameArray.length;i++){
//				//获取节点类型
//				Integer nodeTypeIds=nodeTopDTLDao.findNodeTypeId(nodeNameArray[i]);
//				//判断节点类型
//				if(nodeTypeIds==11){
//					paths.remove(path);
//					break;
//				}
//			}
//		}
//		return paths;
//	}
//}
