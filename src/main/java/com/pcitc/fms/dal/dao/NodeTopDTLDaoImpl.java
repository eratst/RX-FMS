//package com.pcitc.fms.dal.dao;
//
//import java.util.List;
//import java.util.Map;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.persistence.Query;
//
//import org.apache.commons.collections.map.HashedMap;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.Pageable;
//
//import com.pcitc.fms.common.SortParam;
//import com.pcitc.fms.config.AreaNodeBasicSql;
//import com.pcitc.fms.service.model.NodeTopDTL;
//import com.pcitc.imp.common.exception.BusiException;
//
//public class NodeTopDTLDaoImpl {
//	
//	@PersistenceContext
//    private EntityManager em;
//	
//	public Page<com.pcitc.fms.dal.pojo.NodeTopDTL> findNodeTopDTLs(com.pcitc.fms.service.model.NodeTopDTL nodeTopDTLModel,
//			Pageable pageable) throws BusiException{
//		Map<String ,Object> parameterMap = new HashedMap();
//		String dataSql = AreaNodeBasicSql.nodeTopDTL;
//		if(nodeTopDTLModel.getSnodeName()!=null && StringUtils.isNotEmpty(nodeTopDTLModel.getSnodeName())){
//			dataSql+=" and n1.nodeName="+"'"+nodeTopDTLModel.getSnodeName()+"'";
//		}
//		if(nodeTopDTLModel.getTnodeName()!=null && StringUtils.isNotEmpty(nodeTopDTLModel.getTnodeName())){
//			dataSql+=" and n2.nodeName="+"'"+nodeTopDTLModel.getTnodeName()+"'";
//		}
//		if(nodeTopDTLModel.getTnodeCode()!=null && StringUtils.isNotEmpty(nodeTopDTLModel.getTnodeCode())){
//			dataSql+=" and n2.nodeCode="+"'"+nodeTopDTLModel.getTnodeCode()+"'";
//		}
//		
//		
//		if (StringUtils.isNotEmpty(nodeTopDTLModel.getOrderby())) {
//			String value = SortParam.getSortParam(NodeTopDTL.class, nodeTopDTLModel.getOrderby());
//			dataSql += value;
//		}
//		Query dataQuery = em.createQuery(dataSql);
//		
//		Integer skip = null;
//		List<com.pcitc.fms.dal.pojo.NodeTopDTL> list = dataQuery.getResultList();
//		long count = list.size();
//		if(null != pageable){
//		    skip = nodeTopDTLModel.getSkip();
//            dataQuery = em.createQuery(dataSql);
//            dataQuery.setFirstResult(skip);
//            dataQuery.setMaxResults(pageable.getPageSize());
//            return new  PageImpl<com.pcitc.fms.dal.pojo.NodeTopDTL>(dataQuery.getResultList(), pageable, count);
//            
//		}
//		return new PageImpl<com.pcitc.fms.dal.pojo.NodeTopDTL>(list, pageable, count);
//
//	}
//
//}
