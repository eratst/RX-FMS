package com.pcitc.fms.common;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.pcitc.fms.bll.itf.TPmOrgService;
import com.pcitc.fms.dal.dao.RentDao;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.imp.common.exception.BusiException;

@Component
public class RentCondition<T> {
	
//	@Autowired
//	private TPmOrgService tPmOrgService;
//	
//	@Autowired
//	private RentDao rentDao;
	
	public String dataSqlGlobal;
	
	public String countSqlGlobal;

	public String getCountSqlGlobal() {
		return countSqlGlobal;
	}

	public void setCountSqlGlobal(String countSqlGlobal) {
		this.countSqlGlobal = countSqlGlobal;
	}

	public String getDataSqlGlobal() {
		return dataSqlGlobal;
	}

	public void setDataSqlGlobal(String dataSqlGlobal) {
		this.dataSqlGlobal = dataSqlGlobal;
	}

	public boolean getRentCondition(String rentCode,String bizCode, StringBuilder dataSql,
			StringBuilder countSql, Map<String, Object> parameterMap,String inContent) {
		List<String> orgCodes = new ArrayList<>();
		Map<String, List<String>> bizCodesAndOrgCodes = new HashMap<>();
		if (null != rentCode && !StringUtils.isEmpty(rentCode)) {
			if (null != bizCode && !StringUtils.isEmpty(bizCode)) {
				orgCodes = CacheRentInfo.getOrgCodes(rentCode, bizCode);
				if (orgCodes ==null|| orgCodes.size()<=0) {
					return false;
				}
				countSql.append(inContent+"rentOrgCodes");//" and factory.orgCode in :"
				dataSql.append(inContent+"rentOrgCodes");
				parameterMap.put("rentOrgCodes", orgCodes);
			}else{
				bizCodesAndOrgCodes = CacheRentInfo.getBizCodesAndOrgCodes(rentCode);
				if (bizCodesAndOrgCodes.get("orgCodes") == null || bizCodesAndOrgCodes.get("orgCodes").isEmpty()) {
					return false;
				}
				countSql.append(inContent+"rentOrgCodes");
				dataSql.append(inContent+"rentOrgCodes");
				parameterMap.put("rentOrgCodes", bizCodesAndOrgCodes.get("orgCodes"));
			}
		}
		return true;
	}
	
	public boolean getRentConditionArea(String rentCode,String bizCode, StringBuilder dataSql,
			StringBuilder countSql, Map<String, Object> parameterMap,String inContent) {
		List<String> orgCodes = new ArrayList<>();
		Map<String, List<String>> bizCodesAndOrgCodes = new HashMap<>();
		if (null != rentCode && !StringUtils.isEmpty(rentCode)) {
			if (null != bizCode && !StringUtils.isEmpty(bizCode)) {
				try {
					orgCodes = CacheRentInfo.getOrgCodesRelationArea(rentCode, bizCode);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				if (orgCodes ==null || orgCodes.size()<=0) {
					return false;
				}
				countSql.append(inContent+"rentOrgCodes");//" and factory.orgCode in :"
				dataSql.append(inContent+"rentOrgCodes");
				parameterMap.put("rentOrgCodes", orgCodes);
			}else{
				try {
					bizCodesAndOrgCodes = CacheRentInfo.getBizCodesAndOrgCodesRelationArea(rentCode);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				if (bizCodesAndOrgCodes.get("orgCodes") == null || bizCodesAndOrgCodes.get("orgCodes").isEmpty()) {
					return false;
				}
				countSql.append(inContent+"rentOrgCodes");
				dataSql.append(inContent+"rentOrgCodes");
				parameterMap.put("rentOrgCodes", bizCodesAndOrgCodes.get("orgCodes"));
			}
		}
		return true;
	}
	
	public boolean getBizCondition(String rentCode, StringBuilder dataSql, StringBuilder countSql,
			Map<String, Object> parameterMap, String inContent) {
		List<String> bizCodes = new ArrayList<>();
		Map<String, List<String>> bizCodesAndOrgCodes = new HashMap<>();
		if (null != rentCode && !StringUtils.isEmpty(rentCode)) {
			bizCodesAndOrgCodes = CacheRentInfo.getBizCodesAndOrgCodes(rentCode);
			if (bizCodesAndOrgCodes.get("bizCodes") == null || bizCodesAndOrgCodes.get("bizCodes").isEmpty()) {
				return false;
			}
			bizCodes = bizCodesAndOrgCodes.get("bizCodes");
			countSql.append(inContent + "bizCodes");// " and factory.orgCode in :"
			dataSql.append(inContent + "bizCodes");
			parameterMap.put("bizCodes", bizCodes);
		}
		return true;
	}
	
	
	//针对工厂模型用String类型拼接hsql的方法
	public boolean getRentConditionArea(String rentCode,String bizCode, String dataSql,
			String inContent) {
		List<String> orgCodes = new ArrayList<>();
		Map<String, List<String>> bizCodesAndOrgCodes = new HashMap<>();
		if (null != rentCode && !StringUtils.isEmpty(rentCode)) {
			if (null != bizCode && !StringUtils.isEmpty(bizCode)) {
				try {
					orgCodes = CacheRentInfo.getOrgCodesRelationArea(rentCode, bizCode);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				if (orgCodes ==null || orgCodes.size()<=0) {
					return false;
				}
				dataSql += inContent + CheckUtil.getList(orgCodes);
			}else{
				try {
					bizCodesAndOrgCodes = CacheRentInfo.getBizCodesAndOrgCodesRelationArea(rentCode);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				if (bizCodesAndOrgCodes.get("orgCodes") == null || bizCodesAndOrgCodes.get("orgCodes").isEmpty()) {
					return false;
				}
				dataSql += inContent + CheckUtil.getList(bizCodesAndOrgCodes.get("orgCodes"));
			}
		}
		dataSqlGlobal=dataSql;
		
		return true;
	}
	
	//针对工厂模型用String类型拼接sql_count的方法
	public boolean getRentConditionArea(String rentCode,String bizCode, String dataSql, String countSql,
			String inContent) {
		List<String> orgCodes = new ArrayList<>();
		Map<String, List<String>> bizCodesAndOrgCodes = new HashMap<>();
		if (null != rentCode && !StringUtils.isEmpty(rentCode)) {
			if (null != bizCode && !StringUtils.isEmpty(bizCode)) {
				try {
					orgCodes = CacheRentInfo.getOrgCodesRelationArea(rentCode, bizCode);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				if (orgCodes ==null) {
					return false;
				}
				dataSql += inContent + CheckUtil.getList(orgCodes);
				countSql += inContent + CheckUtil.getList(orgCodes);
			}else{
				try {
					bizCodesAndOrgCodes = CacheRentInfo.getBizCodesAndOrgCodesRelationArea(rentCode);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				if (bizCodesAndOrgCodes.get("orgCodes") == null || bizCodesAndOrgCodes.get("orgCodes").isEmpty()) {
					return false;
				}
				dataSql += inContent + CheckUtil.getList(bizCodesAndOrgCodes.get("orgCodes"));
				countSql += inContent + CheckUtil.getList(bizCodesAndOrgCodes.get("orgCodes"));
			}
		}
		dataSqlGlobal=dataSql;
		countSqlGlobal=countSql;
		
		return true;
	}
	
	//针对单罐配置接口
	public List<String> getRentCondition(String rentCode,String bizCode){
		List<String> orgCodes = new ArrayList<>();
		Map<String, List<String>> bizCodesAndOrgCodes = new HashMap<>();
		if (null != rentCode && !StringUtils.isEmpty(rentCode)){
			if (null != bizCode && !StringUtils.isEmpty(bizCode)){
				try {
					orgCodes = CacheRentInfo.getOrgCodesRelationArea(rentCode, bizCode);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}else{
				try {
					bizCodesAndOrgCodes = CacheRentInfo.getBizCodesAndOrgCodesRelationArea(rentCode);
					orgCodes=bizCodesAndOrgCodes.get("orgCodes");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return orgCodes;
	} 
	
	
	public static String getRentOrgCodeCondition(String rentCode,String bizCode,String field) throws BusiException {
		List<String> orgCodes = new ArrayList<>();
		if (null != rentCode && !StringUtils.isEmpty(rentCode)) {
			if (null != bizCode && !StringUtils.isEmpty(bizCode)) {
				orgCodes = CacheRentInfo.getOrgCodes(rentCode, bizCode);
			}else{
				orgCodes = CacheRentInfo.getOrgCodes(rentCode, rentCode+"_SYSTEM_STANDARD_BIZ");
//				ApplicationContext context = JDBCUtil.getContext();
//				RentDao rentDao = context.getBean(RentDao.class);
//				TPmOrgService tPmOrgService = context.getBean(TPmOrgService.class);
//				
//				com.pcitc.fms.dal.pojo.Rent rent = rentDao.getRentByRentCode(rentCode);
//				if (rent != null) {
//					if (rent.getOrgId()==null) {
//						throw new BusiException("", rent.getRentCode() + "未在标准组织组织机构树上指定位置");
//					}
//					List<com.pcitc.fms.bll.entity.OrgTree> orgTrees = tPmOrgService.getBranchTrees(rent.getOrgId());
//					if (null != rent.getOrgId()) {
//						//说明租户在标准树上
//						for (com.pcitc.fms.bll.entity.OrgTree orgTree : orgTrees) {
//							Map<String, String> orgMaps = new ConcurrentHashMap();
//							String orgCodeByQuery = orgTree.getOrgCode();
//							orgCodes.add(orgCodeByQuery);
//						}
//					}
//				}
////				//找到标准业务域
////				List<String> standardBizCode= CacheRentAndBizInfo.getStandardBiz(rentCode);
////				if(standardBizCode==null){
////						throw new BusiException("", "没有数据！请检查该租户下是否存在业务域及下属数据。");
////				}
////				for(int i=0;i<standardBizCode.size();i++){
////					orgCodes.addAll(CacheRentInfo.getOrgCodes(rentCode,standardBizCode.get(i)));
////				}
			}
		}
		if(orgCodes.size()>0 && orgCodes.size()<=50){
			return field+" in "+CheckUtil.getList(orgCodes);
		}
		if(orgCodes.size()>50){
			return "( "+OracleIn(orgCodes, field)+" )";
		}
		if(orgCodes==null || orgCodes.size()==0){
			return "";
		}
		return "";
	}
	
	public List<String> getRentOrgCodeCondition(String rentCode,String bizCode) throws BusinessException {
		List<String> orgCodes = new ArrayList<>();
		if (null != rentCode && !StringUtils.isEmpty(rentCode)) {
			if (null != bizCode && !StringUtils.isEmpty(bizCode)) {
				orgCodes = CacheRentInfo.getOrgCodes(rentCode, bizCode);
			}else{
				
				ApplicationContext context = JDBCUtil.getContext();
				RentDao rentDao = context.getBean(RentDao.class);
				TPmOrgService tPmOrgService = context.getBean(TPmOrgService.class);
				
				com.pcitc.fms.dal.pojo.Rent rent = rentDao.getRentByRentCode(rentCode);
				if (rent != null) {
					List<com.pcitc.fms.bll.entity.OrgTree> orgTrees = tPmOrgService.getBranchTrees(rent.getOrgId());
					if (null != rent.getOrgId()) {
						//说明租户在标准树上
						for (com.pcitc.fms.bll.entity.OrgTree orgTree : orgTrees) {
							Map<String, String> orgMaps = new ConcurrentHashMap();
							String orgCodeByQuery = orgTree.getOrgCode();
							orgCodes.add(orgCodeByQuery);
						}
					}
				}
//				//找到标准业务域
//				List<String> standardBizCode= CacheRentAndBizInfo.getStandardBiz(rentCode);
//				if(standardBizCode==null){
//						throw new BusiException("", "没有数据！请检查该租户下是否存在业务域及下属数据。");
//				}
//				for(int i=0;i<standardBizCode.size();i++){
//					orgCodes.addAll(CacheRentInfo.getOrgCodes(rentCode,standardBizCode.get(i)));
//				}
			}
		}
		return orgCodes;
	}
	
	
	public String getRentBizCodeCondition(String rentCode,String field) {
		List<String> bizCodes = new ArrayList<>();
		if (null != rentCode && !StringUtils.isEmpty(rentCode)) {
			bizCodes = CacheRentInfo.getBizCodes(rentCode);
		}
		if (bizCodes != null) {
			if(bizCodes.size()>0){
				return field+" in "+CheckUtil.getList(bizCodes);
			}else{
				return "";
			}
		}
		return "";
	}
	
	public static String OracleIn(List<?> ids, String field){
		int count =50;
		int size=0;
		//size:分成or的次数
		size=ids.size()%count;
		if(size==0){
			size=ids.size()/count;
		}else{
			size=ids.size()/count+1;
		}
		StringBuilder stringBuilder=new StringBuilder();
		for(int i=0;i<size;i++){
			int fromIndex=i*count;
			int toIndex=Math.min(fromIndex+count, ids.size());
			String result=StringUtils.join(ids.subList(fromIndex, toIndex), "','");
			if(i!=0){
				stringBuilder.append(" or ");
			}
			stringBuilder.append(field).append(" in ('").append(result).append("')");
		}
		return stringBuilder.toString();
	}
	
	
}
