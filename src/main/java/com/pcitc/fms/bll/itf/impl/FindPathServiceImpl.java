//package com.pcitc.fms.bll.itf.impl;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//import org.springframework.stereotype.Service;
//
//import com.pcitc.fms.bll.itf.FindPathService;
//import com.pcitc.fms.common.Graph;
//import com.pcitc.fms.service.model.NodeTopDTL;
//
//@Service
//public class FindPathServiceImpl implements FindPathService{
//
//	@Override
//	public List<String> findPath(NodeTopDTL nodeTopDTLModel, List<com.pcitc.fms.dal.pojo.NodeTopDTL> ListNodeTop) {
//		//路径集合
//		List<String> visit=new ArrayList<String>();
//		if(nodeTopDTLModel.getSnodeName()!=null && nodeTopDTLModel.getTnodeName()!=null){
//			//创建图对象
//			Graph g=new Graph(ListNodeTop.size());
//			//将所有节点信息放到Set中
//			Set<String> snameSet=new HashSet<String>();
//				for(int i=0;i<ListNodeTop.size();i++){
//					snameSet.add(ListNodeTop.get(i).getSnodeName());
//					snameSet.add(ListNodeTop.get(i).getTnodeName());
//				}
//			Object[] snameArray= snameSet.toArray();
//			//初始化图对象
//			g.initVertext(snameArray);
//			//配置路径
//			for (com.pcitc.fms.dal.pojo.NodeTopDTL nodeTopDTL : ListNodeTop) {
//				g.addEdge(nodeTopDTL.getSnodeName(), nodeTopDTL.getTnodeName());
//			}
//			//所有路径
//			visit = g.visit(nodeTopDTLModel.getSnodeName(),nodeTopDTLModel.getTnodeName());
//		}
//		return visit;
//	}
//
//	
//
//}
