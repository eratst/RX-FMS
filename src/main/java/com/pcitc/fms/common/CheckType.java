package com.pcitc.fms.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.common.dispatcher.SysGlobal;
import com.pcitc.fms.dal.dao.AreaDictionaryDao;
import com.pcitc.fms.dal.dao.AreaTypeDao;
import com.pcitc.fms.dal.dao.NodeTypeDao;
import com.pcitc.fms.dal.dao.TPmOrgDao;
import com.pcitc.fms.dal.dao.TypeDao;
import com.pcitc.fms.dal.pojo.AreaDictionary;
import com.pcitc.fms.dal.pojo.Fms_Type;
import com.pcitc.fms.dal.pojo.INodeAndArea;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.imp.common.exception.BusiException;

import io.vertx.core.http.HttpServerRequest;

/**
 * 校验上一级类型
 *
 * @author 123
 */
@Service
@Component
public class CheckType {

	/**
	 * The Type dao.
	 */
	@Autowired
	private TypeDao typeDao;

	/**
	 * The Check type.
	 */
	@Autowired
	private  CheckType checkType;
	/**
	 * The Tpm org dao.
	 */
	@Autowired
	private TPmOrgDao tpmOrgDao;
	/**
	 * The Area type dao.
	 */
	@Autowired
	private AreaTypeDao areaTypeDao;
	/**
	 * The Area dictionary dao.
	 */
	@Autowired
	private AreaDictionaryDao areaDictionaryDao;
	/**
	 * The Node type dao.
	 */
	@Autowired
	private NodeTypeDao nodeTypeDao;

	/**
	 * Check boolean.
	 *
	 * @param parentType 上一级类型
	 * @param type 节点类型
	 * @return boolean
	 * @throws BusiException the busi exception
	 * @Title: check
	 * @Description: 根据节点类型去查询上一级类型, 判断是否匹配
	 * @date 2017年6月20日
	 * @return: boolean
	 * @author zhenqiang.zhao
	 */
	public  boolean check(String parentType,String type) throws BusinessException{
		List<String> parentTypeList = this.getType(type);
		if (parentTypeList.contains(parentType)) {
			return parentTypeList.contains(parentType);
		}else{
			throw new BusinessException("", type+":"+CheckError.NO_PARENT_TYPE+":"+parentType);
		}
	}


	/**
	 * Get type list.
	 *
	 * @param code the code
	 * @return list
	 * @Title: getType
	 * @Description: TODO
	 * @date 2017年6月20日
	 * @return: List<String>
	 * @author zhenqiang.zhao
	 */
	private List<String> getType(String code){
		List<String> typeList = new ArrayList<>();
		Fms_Type type = typeDao.getType(code);
		if(type!=null){
			String str = type.getParent();
			String [] types = str.split(",");
			typeList = Arrays.asList(types);
		}
		return typeList;
	}

	/**
	 * Check entity code.
	 *
	 * @param entityType the entity type
	 * @param entityCode the entity code
	 * @throws BusiException the busi exception
	 * @Title: checkEntityId
	 * @Description: 校验单元视图关系实体id是否存在
	 * @date 2017年7月4日
	 * @return: void
	 * @author zhenqiang.zhao
	 */
	public void checkEntityCode(String entityType, String entityCode) throws BusinessException {
		 List  list= checkType.getEntity(entityType,entityCode);
		if (null ==list || (null != list && list.size()<=0)) {
			throw new BusinessException("", entityType+":"+CheckError.NO_EXIST_ENTITY_CODE+":"+entityCode);
		}
	}

	/**
	 * Check url entity id.
	 *
	 * @param entityType the entity type
	 * @param entityId the entity id
	 * @param name 提示资源的名称
	 * @throws BusiException the busi exception
	 * @Title: checkURLEntityId
	 * @Description: 校验url
	 * @date 2017年7月21日
	 * @return: void
	 * @author zhenqiang.zhao
	 */
	public void checkURLEntityId(String entityType, Integer entityId,String name) throws BusiException {
		List  list= checkType.getEntity(entityType,entityId);
		if (null ==list || (null != list && list.size()<=0)) {
			throw new BusiException("", name+":"+CheckError.NO_EXIST_ENTITY_CODE+":"+entityId);
		}
	}

	/**
	 * Check url entity code.
	 *
	 * @param entityType the entity type
	 * @param entityCode the entity code
	 * @param name the name
	 * @throws BusiException the busi exception
	 */
	public void checkURLEntityCode(String entityType, String entityCode,String name) throws BusiException {
		List  list= checkType.getEntity(SysGlobal.getDispatcherParam("areas"+"."+entityType),entityCode);
		if (null ==list || (null != list && list.size()<=0)) {
			throw new BusiException("", name+":"+CheckError.NO_EXIST_ENTITY_CODE+":"+entityCode);
		}
	}

	/**
	 * Gets entity.
	 *
	 * @param entityType the entity type
	 * @param entityId the entity id
	 * @return entity
	 * @Title: getEntity
	 * @Description: TODO task mark zhenqiang.zhao
	 * @date 2017年7月4日
	 * @return: List<String>
	 * @author zhenqiang.zhao
	 */
	public List getEntity(String entityType, Integer entityId) {
		return typeDao.getEntity( entityType,  entityId);
	}

	/**
	 * Gets entity.
	 *
	 * @param entityType the entity type
	 * @param entityCode the entity code
	 * @return entity
	 */
	public List getEntity(String entityType, String entityCode) {
		return typeDao.getEntity( entityType,  entityCode);
	}

	/**
	 * Gets entity.
	 *
	 * @param entityType the entity type
	 * @param entityId the entity id
	 * @param parentId the parent id
	 * @return the entity
	 */
	public List getEntity(String entityType, Integer entityId, Integer parentId) {
		return typeDao.getEntity( entityType,  entityId,parentId);
	}

	/**   
	 * @Title: checkURLEntityId   
	 * @Description: 两级校验,查询两次
	 * @param string
	 * @param orgId
	 * @param string2
	 * @param string3
	 * @param orgUnitId
	 * @param string4
	 * @date 2017年7月21日      
	 * @return: void
	 * @author zhenqiang.zhao      
//	 */
//	public void checkURLForThree(String parentType, Integer parentId, String parentName, String nextType, Integer nextId,String NextName) throws BusiException{
//		List  list = null;
//		list = checkType.getEntity(nextType,nextId);
//		if (null ==list || (null != list && list.size()<=0)) {
//			throw new BusiException("", NextName+":"+CheckError.NO_EXIST_ENTITY_ID+":"+nextId);
//		}
//		list= checkType.getEntity(nextType,nextId,parentId);//第二次查询
//		if (null ==list || (null != list && list.size()<=0)) {
//			throw new BusiException("", parentName+":"+CheckError.NO_EXIST_ENTITY_ID+":"+parentId);
//		}
//	}


	/**
	 * Check type and id boolean.
	 *
	 * @param type the type
	 * @param typeName the type name
	 * @param code 字典表查询条件字段
	 * @param id the id
	 * @return boolean
	 * @throws Exception the exception
	 * @Title: checkTypeAndId
	 * @Description: 校验类型是否匹配, 并校验ID是否存在
	 * @date 2017年7月27日
	 * @return: void
	 * @author zhenqiang.zhao
	 */
	public boolean checkTypeAndId(String type, String typeName,String code, Integer id) throws BusinessException {
		List<String> parentTypeList = this.getType(code);
		if (parentTypeList.contains(type)) {
			 if (parentTypeList.contains(type)) {
				 List  list= checkType.getEntity(type,id);
				 if (null ==list || (null != list && list.size()<=0)) {
					 	throw new BusinessException("", type+":"+CheckError.NO_EXIST_ENTITY_CODE+":"+id);
				 }
			}
		}else{
			throw new BusinessException("", typeName+":"+CheckError.NO_TYPE+":"+type);
		}
		return true;
	}
	
	/**   
	 * @Title: checkTypeAndCode   
	 * @Description: 用表T_FMS_DICTIONARY 配置 去验证
	 * (orgRelation.getSourceType(), "SourceType","relationSourceType",orgRelation.getSourceCode())
	 * List  list= checkType.getEntity(SysGlobal.getDispatcherParam("areas"+"."+entityType),entityCode);
	 */
//	public boolean checkTypeAndCode(String sourceType, String typeName,String validateType, String sourceCode) throws BusinessException {
//		List<String> parentTypeList = this.getType(validateType);//sourceType
//		if (parentTypeList.contains(sourceType)) {
//			 if (parentTypeList.contains(sourceType)) {
//				 List  list= checkType.getEntity(sourceType,sourceCode);
//				 if (null ==list || (null != list && list.size()<=0)) {
//					 	throw new BusiException("", sourceType+":"+CheckError.NO_EXIST_ENTITY_CODE+":"+sourceCode);
//				 }
//			}
//		}else{
//			throw new BusiException("", typeName+":"+CheckError.NO_TYPE+":"+sourceType);
//		}
//		return true;
//	}

	/**
	 * Check org relation type and code boolean.
	 *
	 * @param sourceType the source type
	 * @param sourceCode the source code
	 * @param sourceOrTargetType the source or target type
	 * @param typeName the type name
	 * @return the boolean
	 * @throws BusinessException the business exception
	 * @Title: checkTypeAndCode
	 * @Description: 用配置文件 去验证  checkOrgRelationTypeAndCode` (orgRelation.getSourceType(),
	 * "SourceType","relationSourceType",orgRelation.getSourceCode())
	 */
	public boolean checkOrgRelationTypeAndCode(String sourceType, String sourceCode,String  sourceOrTargetType,String typeName) throws BusinessException {
		List<String> parentTypeList = CheckUtil.buildStringToListString("sourceType",SysGlobal.getDispatcherParam("orgRelations."+sourceOrTargetType));
		if (parentTypeList.contains(sourceType)) {
			//没有机构单元表,不需要从机构单元验证改code是否存在
//			 if (parentTypeList.contains(sourceType)) {
//				 List  list= checkType.getEntity(sourceType,sourceCode);
//				 if (null ==list || (null != list && list.size()<=0)) {
//					 	throw new BusiException("", sourceType+":"+CheckError.NO_EXIST_ENTITY_CODE+":"+sourceCode);
//				 }
//			}
		}else{
			throw new BusinessException("", typeName+":"+CheckError.NO_TYPE+":"+sourceType);
		}
		return true;
	}


	/**
	 * Check url for three list.
	 *
	 * @param factoryType 工厂类型.eg :factories
	 * @param factoryId 工厂ID
	 * @param areaType 区域类型
	 * @param areaId 区域ID
	 * @param nodeType 节点类型
	 * @param nodeId 节点ID
	 * @param dispatcherPrefix the dispatcher prefix
	 * @return the list
	 * @throws Exception the exception
	 * @Title: checkURLForThree 三层校验 工厂,区域,节点  针对id
	 * @Description: TODO task mark zhenqiang.zhao
	 * @date 2017年8月8日
	 * @return: 返回查询数据
	 * @author 赵振强
	 */
	@Transactional
	public List checkURLForThree(String factoryType, Integer factoryId, String areaType, Integer areaId,String nodeType, Integer nodeId,String dispatcherPrefix) throws BusinessException {
			INodeAndArea  nodeEntity;
			INodeAndArea  areaEntity;
			try {
			checkAreaTypeMatch(areaType, nodeType);
			//节点
				String entityName = SysGlobal.getDispatcherParam(dispatcherPrefix+"."+nodeType);
				if (null ==entityName) {
					throw new BusinessException("", "", CheckError.URL_NODE_TYPE_ERR+":"+nodeType);
				}
				nodeEntity= (INodeAndArea)typeDao.getNodeEntity(nodeType, nodeId,entityName);
				if (nodeEntity != null) {
					if (!areaType.equals(nodeEntity.getParentType())) {
						throw new BusinessException("", "",  CheckError.URL_AREA_TYPE_ERR+","+" {"+nodeType+":"+nodeId+"}");
					}
					if (!String.valueOf(areaId.intValue()).equals(String.valueOf(nodeEntity.getParentCode()))) {
						//该节点不在该区域下+",areaId:"+areaId;
						throw new BusinessException("", "",  CheckError.URL_NODE_CONTAIN_ERR+",areaId:"+areaId);
					}
				}else {
					throw new BusinessException("", "", CheckError.URL_NODE_NODATA_ERR+":ID:"+nodeId);
				}
			//区域	
				entityName = SysGlobal.getDispatcherParam(dispatcherPrefix+"."+areaType);
				if (null==entityName) {
					throw new BusinessException("", "",  CheckError.URL_AREA_TYPE_ERR+":"+areaType);
				}
				nodeEntity= (INodeAndArea)typeDao.getNodeEntity(areaType, areaId,entityName);
				if (nodeEntity != null) {
					if (!factoryType.equals(nodeEntity.getParentType())) {
						throw new BusinessException("", "", 	CheckError.URL_FACTORY_TYPE_ERR+":"+factoryType);
					}
					if (!String.valueOf(factoryId).equals(String.valueOf(nodeEntity.getParentCode()))) {
						//该区域不在该工厂下 +",factoryId:"+factoryId
						throw new BusinessException("", "", 	CheckError.URL_AREA_CONTAIN_ERR+",factoryId:"+factoryId);
					}
				}else {
					throw new BusinessException("", "", CheckError.URL_AREA_NODATA_ERR+"ID:"+areaId);
				}
			//工厂	
				entityName = SysGlobal.getDispatcherParam(dispatcherPrefix+"."+factoryType);
				nodeEntity= (INodeAndArea)typeDao.getNodeEntity(factoryType, factoryId,entityName);
				if (nodeEntity == null) {
					throw new BusinessException("", "", CheckError.URL_FACTORY_NODATA_ERR+":"+factoryType+":"+factoryId);
				}
			} catch (Exception e) {
				throw e;
			}
			return null;
		}


	/**
	 * Check url for three list.
	 *
	 * @param factoryType 工厂类型.eg :factories
	 * @param factoryCode 工厂Code
	 * @param areaType 区域类型
	 * @param areaCode the area code
	 * @param nodeType 节点类型
	 * @param nodeCode 节点Code
	 * @param dispatcherPrefix the dispatcher prefix
	 * @return the list
	 * @throws Exception the exception
	 * @Title: checkURLForThree 三层校验 工厂,区域,节点 针对 Code
	 * @Description: TODO task mark zhenqiang.zhao
	 * @date 2017年8月8日
	 * @return: 返回查询数据
	 * @author 赵振强
	 */
	@Transactional
	public List checkURLForThree(String factoryType, String factoryCode, String areaType, String areaCode,String nodeType, String nodeCode,String dispatcherPrefix) throws BusinessException {
			INodeAndArea  nodeEntity;
			INodeAndArea  areaEntity;
			try {
			checkAreaTypeMatch(areaType, nodeType);
			//节点
				String entityName = SysGlobal.getDispatcherParam(dispatcherPrefix+"."+nodeType);
				if (null ==entityName) {
					throw new BusinessException("", "", CheckError.URL_NODE_TYPE_ERR+":"+nodeType);
				}
				nodeEntity= (INodeAndArea)typeDao.getNodeEntity(nodeType, nodeCode,entityName);
				if (nodeEntity != null) {
					if (!areaType.equals(nodeEntity.getParentType())) {
						throw new BusinessException("", "",  CheckError.URL_AREA_TYPE_ERR+","+" {"+nodeType+":"+nodeCode+"}");
					}
					if (!areaCode.equals(nodeEntity.getParentCode())) {
						//该节点不在该区域下+",areaCode:"+areaCode;
						throw new BusinessException("", "",  CheckError.URL_NODE_CONTAIN_ERR+",areaCode:"+areaCode);
					}
				}else {
					throw new BusinessException("", "", CheckError.URL_NODE_NODATA_ERR+":CODE:"+nodeCode);
				}
			//区域	
				entityName = SysGlobal.getDispatcherParam(dispatcherPrefix+"."+areaType);
				if (null==entityName) {
					throw new BusinessException("", "",  CheckError.URL_AREA_TYPE_ERR+":"+areaType);
				}
				nodeEntity= (INodeAndArea)typeDao.getNodeEntity(areaType, areaCode,entityName);
				if (nodeEntity != null) {
					if (!factoryType.equals(nodeEntity.getParentType())) {
						throw new BusinessException("", "", 	CheckError.URL_FACTORY_TYPE_ERR+":"+factoryType);
					}
					if (!String.valueOf(factoryCode).equals(String.valueOf(nodeEntity.getParentCode()))) {
						//该区域不在该工厂下 +",factoryCode:"+factoryCode
						throw new BusinessException("", "", 	CheckError.URL_AREA_CONTAIN_ERR+",factoryCode:"+factoryCode);
					}
				}else {
					throw new BusinessException("", "", CheckError.URL_AREA_NODATA_ERR+"CODE:"+areaCode);
				}
			//工厂	
				entityName = SysGlobal.getDispatcherParam(dispatcherPrefix+"."+factoryType);
				nodeEntity= (INodeAndArea)typeDao.getNodeEntity(factoryType, factoryCode,entityName);
				if (nodeEntity == null) {
					throw new BusinessException("", "", CheckError.URL_FACTORY_NODATA_ERR+":"+factoryType+":"+factoryCode);
				}
			} catch (Exception e) {
				throw e;
			}
			return null;
		}


	/**
	 * Check area type match.
	 *
	 * @param areaType 区域类型
	 * @param nodeType 节点类型
	 * @throws BusinessException 异常类
	 * @Title: checkAreaTypeMatch
	 * @Description: 上下级类型对应关系校验
	 * @date 2017年8月10日
	 * @return: void
	 * @author 赵振强
	 */
	public void checkAreaTypeMatch(String areaType, String nodeType) throws BusinessException {
		if (!CheckUtil.buildStringToListStringNoCheck("",SysGlobal.getDispatcherParam("areaType"+"."+nodeType)).contains(areaType)) {
			throw new BusinessException("", "", CheckError.URL_NODE_TYPE_ERR+","+"{"+areaType+":"+nodeType+"}") ;
		}
	};

	/**
	 * 层级校验
	 *
	 * @param orgCode the org code
	 * @param Type the type
	 * @param areaCode the area code
	 * @throws BusinessException the business exception
	 */
	public void checkOrgAndType(String orgCode,String Type,String areaCode,HttpServerRequest request) throws BusinessException{
		String hrefBase = request.uri();
		String[] hrefArr = hrefBase.split("/");
		String nodeTypeNameTemp="";
		if(hrefArr[2].equals("rents") && !hrefArr[4].equals("bizOrgMains")){
			 nodeTypeNameTemp = hrefArr[8];
		}else if(hrefArr[4].equals("bizOrgMains")){
			 nodeTypeNameTemp = hrefArr[10];
		}else{
			 nodeTypeNameTemp = hrefArr[6];
		}
		String[] nodeTypeNameArr ;
		String nodeTypeName;
		if (nodeTypeNameTemp.contains("?")) {
			nodeTypeNameArr = nodeTypeNameTemp.split("\\?");
			nodeTypeName = nodeTypeNameArr[0];
		} else {
			nodeTypeName = nodeTypeNameTemp;
		}
		//组织机构校验
		com.pcitc.fms.dal.pojo.TPmOrg org = tpmOrgDao.findByorgCode(orgCode);
		if(org == null ){
			throw new BusinessException("", "", orgCode+":不存在该组织结构!");
		}
		//区域类型校验
		List<com.pcitc.fms.dal.pojo.AreaType> areaType = areaTypeDao.findByAreaTypeCode(Type);
		if(areaType == null || areaType.isEmpty()){
			throw new BusinessException("", "", Type+":该区域类型不存在!");
		}
		//区域Code校验
		AreaDictionary areaId = areaDictionaryDao.getAreaDictionaryByAreaCode(areaCode,areaType.get(0).getAreaTypeId());
		if(areaId == null){
			throw new BusinessException("", "", "区域里不存在areacode为"+areaCode+"的数据!");
		}else if(!areaId.getFactoryId().equals(org.getOrgId())&& !org.getOrgId().equals(areaId.getFactoryId())){
			throw new BusinessException("", "", "该组织机构下不存在areacode为"+areaCode+"的数据。");
		}
		
		NodeAndAreaRelation.getNodeAndAreaRelation(Type, nodeTypeName);
		
	}
	public void checkOrgAndArea(String orgCode,String areaCode) throws BusinessException{
		//组织机构校验
		com.pcitc.fms.dal.pojo.TPmOrg org = tpmOrgDao.findByorgCode(orgCode);
		if(org == null ){
			throw new BusinessException("", "", orgCode+":不存在该组织结构!");
		}
		//区域Code校验
		AreaDictionary area = areaDictionaryDao.getAreaDictionaryByAreaCode(areaCode);
		if(area == null){
			throw new BusinessException("", "", "区域里不存在areacode为"+areaCode+"的数据!");
		}else if(!area.getFactoryId().equals(org.getOrgId())){
			throw new BusinessException("", "", "该组织机构下不存在areacode为"+areaCode+"的数据。");
		}
	}
	public void checkOrg(String orgCode) throws BusinessException{
		//组织机构校验
		com.pcitc.fms.dal.pojo.TPmOrg org = tpmOrgDao.findByorgCode(orgCode);
		if(org == null ){
			throw new BusinessException("", "", orgCode+":不存在该组织结构!");
		}
	}
}
