/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: MaterialServiceImpl
 * Date:18-3-8 下午6:22
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.bll.itf.impl;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.pcitc.fms.bll.entity.TPmBizOrgDTL;
import com.pcitc.fms.bll.itf.MaterialService;
import com.pcitc.fms.common.LevelValue;
import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.dal.dao.MaterialDao;
import com.pcitc.fms.service.model.Material;
import com.pcitc.fms.service.model.Pager;

import com.pcitc.fms.exception.BusinessException;
import pcitc.imp.common.ettool.utils.ObjectConverter;

/**
 * The type Material service.
 *
 * @version 创建时间 ：2017年6月8日 下午3:52:01 类说明
 * @author: jzx
 * @author: update zhaozhenqiang
 */
@Service
@Component
public class MaterialServiceImpl implements MaterialService {

	/**
	 * The constant log.
	 */
	private static Logger log = LoggerFactory.getLogger(MaterialServiceImpl.class);
	/**
	 * The Material dao.
	 */
	@Autowired
	private MaterialDao materialDao;

	/**
	 * Gets materials.
	 *
	 * @param materialModel
	 *            the material model
	 * @param pageable
	 *            the pageable
	 * @return the materials
	 * @throws BusinessException
	 *             the business exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Pager<com.pcitc.fms.bll.entity.Material> getMaterials(Material materialModel, Pageable pageable)
			throws BusinessException {
		List<com.pcitc.fms.bll.entity.Material> materialEntityList = new ArrayList<>();
		MyPageImpl properties = null;
		try {
			properties = (MyPageImpl) materialDao.queryMaterials(materialModel, pageable);
			materialEntityList = ObjectConverter.listConverter(properties.getContent(), com.pcitc.fms.bll.entity.Material.class);
		} catch (Exception e) {
			if (e instanceof UndeclaredThrowableException) {
				throw new BusinessException("", "", e.getCause().getMessage());
			}
			e.printStackTrace();
		}
		Pager<com.pcitc.fms.bll.entity.Material> pageData = new Pager<>();
		pageData.setContent(materialEntityList);
		pageData.setFirst(properties.isFirst());
		pageData.setLast(properties.isLast());
		pageData.setNumber(properties.getNumber());
		pageData.setNumberOfElements(properties.getNumberOfElements());
		pageData.setSize(properties.getSize());
		pageData.setSort(properties.getSort());
		pageData.setTotalElements(properties.getCount());
		pageData.setTotalPages(properties.getTotalPages());
		return pageData;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<com.pcitc.fms.bll.entity.Material> getMaterialsByOperType(String mtrlCode, List<String> mtrlCodes,
			String operType,Material materialModel,Pageable pageable) throws BusinessException {
		List<com.pcitc.fms.dal.pojo.Material> mtrlEn = new ArrayList<>();
		List<com.pcitc.fms.dal.pojo.Material> result = new ArrayList<>();
		// List<com.pcitc.fms.dal.pojo.Material> resultTemp = new ArrayList<>();
		if(mtrlCode==null && mtrlCodes==null){
			throw new BusinessException("", "", ":物料编码不能为空");
		}
		if(mtrlCodes!=null){
			if(mtrlCodes.size()<0){
				throw new BusinessException("", "", "物料编码不能为空！");
			}
		}
		if (null != mtrlCode && mtrlCodes == null) {
			com.pcitc.fms.dal.pojo.Material byMtrlCode = materialDao.getMtrlByMtrlCode(mtrlCode);
			if(byMtrlCode==null){
				throw new BusinessException("", "", ":不存在该物料编码");
			}
			mtrlCodes = new ArrayList<>();
			mtrlCodes.add(mtrlCode);
		}
		if (!mtrlCodes.isEmpty() && null != mtrlCodes) {
			mtrlEn = materialDao.getMtrlByCodes(mtrlCodes);
//			if (0 == mtrlEn.size()) {
//				throw new BusinessException("", "", ":不存在该物料编码");
//			}
		}

		for (int i = 0; i < mtrlEn.size(); i++) {
			// 找子节点
			if (null != operType && "children".equals(operType)) {
				try{
					result = materialDao.getMtrlByCodeWithChildsTest(mtrlCodes,materialModel,pageable);
				} catch (Exception e) {
					if (e instanceof UndeclaredThrowableException) {
						throw new BusinessException("", "", e.getCause().getMessage());
					}
				}
			}
			// 找父节点
			if (null != operType && "ancestors".equals(operType)) {
				try{
					result = materialDao.getMtrlByCodeWithAncestors(mtrlEn.get(i).getUpperMtrlCode(),materialModel);
					}catch (Exception e) {
						if (e instanceof UndeclaredThrowableException) {
							throw new BusinessException("", "", e.getCause().getMessage());
						}
				}
			}

			// 叶子节点
			if (null != operType && "Leaves".equals(operType)) {
				if (null != mtrlCodes && !mtrlCodes.isEmpty()) {
					List<String> CodesTemp = new ArrayList<>();
					List<String> Codes = new ArrayList<>();
					Set<String> CodeSet = new HashSet<>();
					for (String code : mtrlCodes) {
						CodesTemp = materialDao.getMtrlCodesLeaves(code);
						CodeSet.addAll(CodesTemp);
					}
					Codes.addAll(CodeSet);
					
					try{
						result = materialDao.getAllToRoot(Codes,materialModel,pageable);
					}catch (Exception e) {
						if (e instanceof UndeclaredThrowableException) {
							throw new BusinessException("", "", e.getCause().getMessage());
						}
					}
				} else {
					List<String> Codes = new ArrayList<>();
					for (String code : mtrlCodes) {
						Codes = materialDao.getMtrlCodesLeaves(code);
					}
					try{
						result = materialDao.getAllToRoot(Codes,materialModel,pageable);
						}catch (Exception e) {
							if (e instanceof UndeclaredThrowableException) {
								throw new BusinessException("", "", e.getCause().getMessage());
							}
						}
					}
				}
			}

		// 到根节点
		if (operType != null && "toRoot".equals(operType)) {
			if (null != mtrlCode && !mtrlCode.isEmpty()) {
				mtrlCodes.add(mtrlCode);
			}
			if (null != mtrlCodes && !mtrlCodes.isEmpty()) {
				List<String> CodesTemp = new ArrayList<>();
				List<String> Codes = new ArrayList<>();
				Set<String> CodeSet = new HashSet<>();
				StringBuilder sb = new StringBuilder();
				for (String code : mtrlCodes) {
					sb.append(code+",");
				}
				String params = sb.toString();
				if (params.endsWith(",")) {
					params = params.substring(0, params.lastIndexOf(","));
				}
				CodesTemp = materialDao.getAllMtrlCodeToRoot(params);
//				if (StringUtils.isNotEmpty(resCodeStr)) {
//					CodeSet.addAll(Arrays.asList(resCodeStr.split(",")));
//				}
//				CodesTemp = materialDao.getAllCodeToRoot(sb.toString());
				CodeSet.addAll(CodesTemp);
				Codes.addAll(CodeSet);
				materialModel.getMtrlCodes().clear();
				materialModel.getMtrlCodes().addAll(Codes);
				try{
					result = materialDao.queryMaterials(materialModel,pageable).getContent();
				}catch (Exception e) {
					if (e instanceof UndeclaredThrowableException) {
						throw new BusinessException("", "", e.getCause().getMessage());
					}
				}
//				List<com.pcitc.fms.dal.pojo.Material> prop = null;
//				if (null != Codes && !Codes.isEmpty()) {
//					prop = materialDao.getMtrlByCodes(Codes);
//				}
//				if (prop != null) {
//					List<com.pcitc.fms.dal.pojo.Material> list = new ArrayList<>();
//					if (null != result && !result.isEmpty()) {
//						list.addAll(result);
//					}
//					list.addAll(prop);
//					// 驱虫
//					List<com.pcitc.fms.dal.pojo.Material> resultTemp = new ArrayList<>();
//					resultTemp.addAll(list);
//					for (int i = 1; i < resultTemp.size(); i++) {
//						for (int j = 0; j < i; j++) {
//							if (resultTemp.get(i).getMtrlCode().equals(resultTemp.get(j).getMtrlCode())) {
//								if (StringUtils.isEmpty(resultTemp.get(i).getUpperMtrlName())) {
//									list.remove(resultTemp.get(i));
//								}
//							}
//						}
//					}
//					result = list;
//				}
			}
		}
		// 到子节点
		if (operType != null && "allChildren".equals(operType)) {
			if (null != mtrlCode && !mtrlCode.isEmpty()) {
				mtrlCodes.add(mtrlCode);
			}
			if (null != mtrlCodes && !mtrlCodes.isEmpty()) {
				List<String> CodesTemp = new ArrayList<>();
				List<String> Codes = new ArrayList<>();
				Set<String> CodeSet = new HashSet<>();
				for (String code : mtrlCodes) {
					CodesTemp = materialDao.getAllCodeToChild(code, LevelValue.LEVELVALUE);
					CodesTemp.remove(mtrlCode);
					CodeSet.addAll(CodesTemp);
				}
				Codes.addAll(CodeSet);
				if (Codes == null || Codes.isEmpty()) {
					result = null;
				} else {

					int numA = Codes.size() / 900;
					int numB = Codes.size() % 900;
					try{
						if (numA == 0 || (numA == 1 && numB == 0)) {
							result = materialDao.getAllToRoot(Codes,materialModel,pageable);
						} else {
							for (int i = 0; i < numA + 1; i++) {
								if (i > 0) {
									if (i == numA) {
										result.addAll(materialDao.getAllToRoot(Codes.subList(i * 900 + 1, Codes.size()),materialModel,pageable));
									} else {
										result.addAll(materialDao.getAllToRoot(Codes.subList(i * 900 + 1, 900 * (i + 1)),materialModel,pageable));
									}
								} else {
									List<String> strList = Codes.subList(i * 900, 900 * (i + 1));
									result.addAll(materialDao.getAllToRoot(strList,materialModel,pageable));
								}
							}
						}
					}catch (Exception e) {
						if (e instanceof UndeclaredThrowableException) {
							throw new BusinessException("", "", e.getCause().getMessage());
						}
						e.printStackTrace();
					}
				}
			}
		}

		try {
//			if (operType != null && "toRoot".equals(operType)) {
//				if (null != mtrlEn) {
//					result.addAll(mtrlEn);
//				}
//				List<com.pcitc.fms.dal.pojo.Material> resultTemp = new ArrayList<>();
//				resultTemp.addAll(result);
//				for (int i = 1; i < resultTemp.size(); i++) {
//					for (int j = 0; j < i; j++) {
//						if (resultTemp.get(i).getMtrlCode().equals(resultTemp.get(j).getMtrlCode())) {
//							if (StringUtils.isEmpty(resultTemp.get(i).getUpperMtrlName())) {
//								result.remove(resultTemp.get(i));
//							}
//						}
//					}
//				}
//			}
			return ObjectConverter.listConverter(result, com.pcitc.fms.bll.entity.Material.class);
		} catch (Exception e) {
			throw new BusinessException("", "", e.getMessage());
		}
	}

}
