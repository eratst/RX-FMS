/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: TypeDaoImpl
 * Date:18-3-9 上午8:35
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.dal.dao;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.common.annotation.RegionMember;
import com.pcitc.fms.common.dispatcher.SysGlobal;
import com.pcitc.fms.dal.pojo.INodeAndArea;
import com.pcitc.fms.exception.BusinessException;

/**
 * Title: TypeDaoImpl Description: TODO task mark zhenqiang.zhao
 *
 * @author zhenqiang.zhao
 * @version 1.0
 * @date 2017年7月4日
 */
@Service
public class TypeDaoImpl {

	/**
	 * The constant log.
	 */
	private static Logger log = LoggerFactory.getLogger(TypeDaoImpl.class);
	/**
	 * The Em.
	 */
	@PersistenceContext
	private EntityManager em;

	/**
	 * Gets entity.
	 *
	 * @param entityType the entity type
	 * @param entityId the entity id
	 * @return the entity
	 * @throws BusinessException the exception
	 * @Title: getEntity
	 * @Description: TODO task mark zhenqiang.zhao
	 * @date 2017年7月4日
	 * @return: void
	 * @author zhenqiang.zhao
	 */
	public List getEntity(String entityType,Integer entityId)  throws BusinessException{
		String fieldName = null;
		StringBuffer dataSql = new StringBuffer();
		Map<String, Object> mapParameter = new HashMap<String, Object>();
		entityType = toUpperCaseFirstOne(entityType);
		Query dataQuery = null;
				try {
					Class entity= Class.forName("com.pcitc.fms.dal.pojo."+entityType);
					String entityName= Class.forName("com.pcitc.fms.dal.pojo."+entityType).getName();
					if (("com.pcitc.fms.dal.pojo."+entityType).equals(entityName)) {
						try {
							 Field[] declaredFields = entity.newInstance().getClass().getDeclaredFields();
							 for (Field field : declaredFields) {
								 RegionMember annotation = field.getAnnotation(RegionMember.class);
								 if (null!= annotation && annotation.IsRegion()) {
									 fieldName = field.getName();
									 break;
								}
								
							}
							if (null != entityType && null !=fieldName) {
								dataSql.append("select t from "+entityType+"  t where 1= 1 and t."+fieldName+"=:entityId");
								dataQuery = em.createQuery(dataSql.toString());
								dataQuery.setParameter("entityId", entityId);
							}
						} catch (Exception e) {
							log.warn(e.getMessage());
						} 
					}
				} catch (ClassNotFoundException e) {
					log.warn(e.getMessage());
				} catch (Exception e) {
					log.warn(e.getMessage());
				}
		if (null!= dataQuery) {
			return dataQuery.getResultList();
		}else {
			return null;
		}
	}

	/**
	 * 根据类型和code查询数据库是否有该记录
	 *
	 * @param entityType the entity type
	 * @param entityCode the entity code
	 * @return entity entity
	 * @throws BusinessException the exception
	 */
	public List getEntity(String entityType,String entityCode)  throws BusinessException{
		String fieldName = null;
		StringBuffer dataSql = new StringBuffer();
		entityType = toUpperCaseFirstOne(entityType);
		Query dataQuery = null;
				try {
					Class entity= Class.forName("com.pcitc.fms.dal.pojo."+entityType);
					String entityName= Class.forName("com.pcitc.fms.dal.pojo."+entityType).getName();
					if (("com.pcitc.fms.dal.pojo."+entityType).equals(entityName)) {
						try {
							 Field[] declaredFields = entity.newInstance().getClass().getDeclaredFields();
							 for (Field field : declaredFields) {
								 RegionMember annotation = field.getAnnotation(RegionMember.class);
								 if (null!= annotation && annotation.IsRegion()) {
									 fieldName = field.getName();
									 break;
								}
								
							}
							if (null != entityType && null !=fieldName) {
								dataSql.append("select t from "+entityType+"  t where 1= 1 and t."+fieldName+"=:code");
								dataQuery = em.createQuery(dataSql.toString());
								dataQuery.setParameter("code", entityCode);
							}
						} catch (Exception e) {
							log.warn(e.getMessage());
						} 
					}
				} catch (ClassNotFoundException e) {
					log.warn(e.getMessage());
				} catch (Exception e) {
					log.warn(e.getMessage());
				}
		if (null!= dataQuery) {
			return dataQuery.getResultList();
		}else {
			return null;
		}
	}


	/**
	 * Gets node entity.
	 *
	 * @param urlNodeName url中节点名称
	 * @param nodeId 节点id
	 * @return 返回一条实体数据 node entity
	 * @throws BusinessException the business exception
	 * @Title: getNodeEntity
	 * @Description: TODO task mark zhenqiang.zhao
	 * @date 2017年8月5日
	 * @return: Object
	 * @author 赵振强
	 */
	@Transactional
	public Object getNodeEntity(String urlNodeName,Integer nodeId)  throws BusinessException{
		String fieldName = null;
		StringBuffer dataSql = new StringBuffer();
		Map<String, Object> mapParameter = new HashMap<String, Object>();
		Query dataQuery = null;
				try {
					Class entity = Class.forName(SysGlobal.getDispatcherParam("pojo."+urlNodeName));
					String entityName= entity.getName();
						try {
							 Field[] declaredFields = entity.newInstance().getClass().getDeclaredFields();
							 for (Field field : declaredFields) {
								 RegionMember annotation = field.getAnnotation(RegionMember.class);
								 if (null!= annotation && annotation.IsRegion()) {
									 fieldName = field.getName();
									 break;
								}
							}
							if (null != entity && null !=fieldName) {
								dataSql.append("select t from "+entityName+"  t where 1= 1 and t."+fieldName+"=:entityId");
								dataQuery = em.createQuery(dataSql.toString());
								dataQuery.setParameter("entityId", nodeId);
							}
						} catch (Exception e) {
							log.warn(e.getMessage());
						} 
				} catch (ClassNotFoundException e) {
					log.warn(e.getMessage());
				} catch (Exception e) {
					log.warn(e.getMessage());
				}
		if (null!= dataQuery) {
			List list = dataQuery.getResultList();
			return list.size()>0?dataQuery.getSingleResult():null;
		}else {
			return null;
		}
	}


	/**
	 * Gets node entity.
	 *
	 * @param urlNodeName url中节点名称
	 * @param nodeCode the node code
	 * @return 返回一条实体数据 node entity
	 * @throws BusinessException the exception
	 * @Title: getNodeEntity
	 * @Description: TODO task mark zhenqiang.zhao
	 * @date 2017年8月5日
	 * @return: Object
	 * @author 赵振强
	 */
	@Transactional
	public Object getNodeEntity(String urlNodeName,String nodeCode)  throws BusinessException{
		String fieldName = null;
		StringBuffer dataSql = new StringBuffer();
		Map<String, Object> mapParameter = new HashMap<String, Object>();
		Query dataQuery = null;
				try {
					Class entity = Class.forName(SysGlobal.getDispatcherParam("pojo."+urlNodeName));
					String entityName= entity.getName();
						try {
							 Field[] declaredFields = entity.newInstance().getClass().getDeclaredFields();
							 for (Field field : declaredFields) {
								 RegionMember annotation = field.getAnnotation(RegionMember.class);
								 if (null!= annotation && annotation.IsRegion()) {
									 fieldName = field.getName();
									 break;
								}
							}
							if (null != entity && null !=fieldName) {
								dataSql.append("select t from "+entityName+"  t where 1= 1 and t."+fieldName+"=:entityCode");
								dataQuery = em.createQuery(dataSql.toString());
								dataQuery.setParameter("entityCode", nodeCode);
							}
						} catch (Exception e) {
							log.warn(e.getMessage());
						} 
				} catch (ClassNotFoundException e) {
					log.warn(e.getMessage());
				} catch (Exception e) {
					log.warn(e.getMessage());
				}
		if (null!= dataQuery) {
			List list = dataQuery.getResultList();
			return list.size()>0?dataQuery.getSingleResult():null;
		}else {
			return null;
		}
	}


	/**
	 * Gets entity.
	 *
	 * @param entityType the entity type
	 * @param entityId the entity id
	 * @param parentId the parent id
	 * @return entity entity
	 * @throws BusinessException the exception
	 * @Title: getEntity
	 * @Description: 查询当前表 where 条件 parentId
	 * @date 2017年7月21日
	 * @return: List
	 * @author zhenqiang.zhao
	 */
	public List getEntity(String entityType,Integer entityId,Integer parentId)  throws BusinessException{
		String fieldName = null;
		StringBuffer dataSql = new StringBuffer();
		Map<String, Object> mapParameter = new HashMap<String, Object>();
		entityType = toUpperCaseFirstOne(entityType);
		Query dataQuery = null;
				try {
					Class entity= Class.forName("com.pcitc.fms.dal.pojo."+entityType);
					String entityName= Class.forName("com.pcitc.fms.dal.pojo."+entityType).getName();
					if (("com.pcitc.fms.dal.pojo."+entityType).equals(entityName)) {
						try {
							 Field[] declaredFields = entity.newInstance().getClass().getDeclaredFields();
							 for (Field field : declaredFields) {
								 RegionMember annotation = field.getAnnotation(RegionMember.class);
								 if (null!= annotation && annotation.IsRegion()) {
									 fieldName = field.getName();
									 break;
								}
							}
							if (null != entityType && null !=fieldName) {
								dataSql.append("select t from "+entityType+"  t where 1= 1 and t."+fieldName+"=:entityId and t.parentId =: parentId");
								dataQuery = em.createQuery(dataSql.toString());
								dataQuery.setParameter("entityId", entityId);
								dataQuery.setParameter("parentId", parentId);
							}
						} catch (Exception e) {
							log.warn(e.getMessage());
						} 
					}
				} catch (ClassNotFoundException e) {
					log.warn(e.getMessage());
				} catch (Exception e) {
					log.warn(e.getMessage());
				}
		if (null!= dataQuery) {
			return dataQuery.getResultList();
		}else {
			return null;
		}
	}

	/**
	 * To upper case first one string.
	 *
	 * @param s the s
	 * @return string string
	 * @Title: toUpperCaseFirstOne
	 * @Description: 转换为首字母大写
	 * @date 2017年7月7日
	 * @return: String
	 * @author zhenqiang.zhao
	 */
	public static String toUpperCaseFirstOne(String s){
		  if(Character.isUpperCase(s.charAt(0)))
		    return s;
		  else
		    return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
	}
	
	/**   
	 * @Title: checkURLForThree   三层校验 工厂,区域,节点
	 * @Description: TODO task mark zhenqiang.zhao
	 * @param factoryType 工厂类型.eg :factories
	 * @param factoryId  工厂ID
	 * @param areaType 区域类型
	 * @param areaId 区域ID
	 * @param nodeType 节点类型
	 * @param nodeId  节点ID
	 * @date 2017年8月8日      
	 * @return: 返回查询数据
	 * @author 赵振强      
	 * @throws  
	 */
//	public List  getA(String factoryType, Integer factoryId, String areaType, Integer areaId,String nodeType, Integer nodeId,String dispatcherPrefix) {
//		INodeAndArea  nodeEntity;
//		INodeAndArea  areaEntity;
//		try {
//		//节点
//			String entityName = SysGlobal.getDispatcherParam(dispatcherPrefix+"."+nodeType);
//			if ("".equals(entityName) || null ==entityName) {
//				String msg = "节点URL不对应";
//				throw new BusinessException("", "", msg);
//			}
//			nodeEntity= (INodeAndArea)this.getNodeEntity(nodeType, nodeId,entityName);
//			if (nodeEntity != null) {
//				if (!nodeEntity.getParentType().equals(areaType)) {
//					String msg = "上一级类型不匹配:区域";
//					throw new BusinessException("", "", msg);
//				}
//			}else {
//				String msg = "节点不存在该数据";
//				throw new BusinessException("", "", msg);
//			}
//		//区域	
//			entityName = SysGlobal.getDispatcherParam(dispatcherPrefix+"."+nodeType);
//			nodeEntity= (INodeAndArea)this.getNodeEntity(areaType, areaId,entityName);
//			if ("".equals(entityName) || null ==entityName) {
//				String msg = "区域节点URL不对应";
//				throw new BusinessException("", "", msg);
//			}
//			nodeEntity= (INodeAndArea)this.getNodeEntity(nodeType, nodeId,entityName);
//			if (nodeEntity != null) {
//				if (!nodeEntity.getParentType().equals(factoryType)) {
//					String msg = "上一级类型不匹配:工厂";
//					throw new BusinessException("", "", msg);
//				}
//			}else {
//				String msg = "区域不存在该数据";
//				throw new BusinessException("", "", msg);
//			}
//		//工厂	
//			entityName = SysGlobal.getDispatcherParam(dispatcherPrefix+"."+nodeType);
//			nodeEntity= (INodeAndArea)this.getNodeEntity(factoryType, factoryId,entityName);
//			if (nodeEntity != null) {
//			}else {
//				String msg = "工厂不存在该数据";
//				throw new BusinessException("", "", msg);
//			}
//			
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	};


	/**
	 * Gets node entity.
	 *
	 * @param urlNodeName the url node name
	 * @param nodeId the node id
	 * @param pojoName the pojo name
	 * @return the node entity
	 * @throws BusinessException the exception
	 * @Title: getNodeEntity 该方法只用于 checkURLForThree
	 */
	public Object getNodeEntity(String urlNodeName,Integer nodeId,String pojoName)  throws BusinessException{
		String fieldName = null;
		StringBuffer dataSql = new StringBuffer();
		Map<String, Object> mapParameter = new HashMap<String, Object>();
		Query dataQuery = null;
				try {
					Class entity = Class.forName(pojoName);
					String entityName= entity.getName();
						try {
							 Field[] declaredFields = entity.newInstance().getClass().getDeclaredFields();
							 for (Field field : declaredFields) {
								 RegionMember annotation = field.getAnnotation(RegionMember.class);
								 if (null!= annotation && annotation.IsRegion()) {
									 fieldName = field.getName();
									 break;
								}
							}
							if (null != entity && null !=fieldName) {
								dataSql.append("select t from "+entityName+"  t where 1= 1 and t."+fieldName+"=:entityId");
								dataQuery = em.createQuery(dataSql.toString());
								dataQuery.setParameter("entityId", nodeId);
							}
						} catch (Exception e) {
							log.warn(e.getMessage());
						} 
				} catch (ClassNotFoundException e) {
					log.warn(e.getMessage());
				} catch (Exception e) {
					log.warn(e.getMessage());
				}
		if (null!= dataQuery) {
			List list = dataQuery.getResultList();
			return list.size()>0?dataQuery.getSingleResult():null;
		}else {
			return null;
		}
	}


	/**
	 * Gets node entity.
	 *
	 * @param urlNodeName the url node name
	 * @param nodeCode the node code
	 * @param pojoName the pojo name
	 * @return the node entity
	 * @throws BusinessException the exception
	 * @Title: getNodeEntity 该方法只用于 checkURLForThree
	 */
	public Object getNodeEntity(String urlNodeName,String nodeCode,String pojoName)  throws BusinessException{
		String fieldName = null;
		StringBuffer dataSql = new StringBuffer();
		Map<String, Object> mapParameter = new HashMap<String, Object>();
		Query dataQuery = null;
				try {
					Class entity = Class.forName(pojoName);
					String entityName= entity.getName();
						try {
							 Field[] declaredFields = entity.newInstance().getClass().getDeclaredFields();
							 for (Field field : declaredFields) {
								 RegionMember annotation = field.getAnnotation(RegionMember.class);
								 if (null!= annotation && annotation.IsRegion()) {
									 fieldName = field.getName();
									 break;
								}
							}
							if (null != entity && null !=fieldName) {
								dataSql.append("select t from "+entityName+"  t where 1= 1 and t."+fieldName+"=:entityCode");
								dataQuery = em.createQuery(dataSql.toString());
								dataQuery.setParameter("entityCode", nodeCode);
							}
						} catch (Exception e) {
							log.warn(e.getMessage());
						} 
				} catch (ClassNotFoundException e) {
					log.warn(e.getMessage());
				} catch (Exception e) {
					log.warn(e.getMessage());
				}
		if (null!= dataQuery) {
			List list = dataQuery.getResultList();
			return list.size()>0?dataQuery.getSingleResult():null;
		}else {
			return null;
		}
	}
}
