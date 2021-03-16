/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: NodeDictGatherServiceImpl
 * Date:18-3-8 下午6:22
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.bll.itf.impl;

import com.pcitc.fms.exception.BusinessException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcitc.fms.bll.dictionary.entity.EquTechnic;
import com.pcitc.fms.bll.dictionary.entity.IdxType;
import com.pcitc.fms.bll.dictionary.entity.MtrlType;
import com.pcitc.fms.bll.dictionary.entity.NodeType;
import com.pcitc.fms.bll.dictionary.entity.SamplepointType;
import com.pcitc.fms.bll.dictionary.entity.SideMtrlType;
import com.pcitc.fms.bll.dictionary.entity.SidelineType;
import com.pcitc.fms.bll.dictionary.entity.SignboardType;
import com.pcitc.fms.bll.dictionary.entity.SiloType;
import com.pcitc.fms.bll.dictionary.entity.TankType;
import com.pcitc.fms.bll.dictionary.entity.TransType;
import com.pcitc.fms.bll.dictionary.entity.VcfType;
import com.pcitc.fms.bll.itf.NodeDictGatherService;
import com.pcitc.fms.dal.dao.EquTechnicDao;
import com.pcitc.fms.dal.dao.IdxTypeDao;
import com.pcitc.fms.dal.dao.MtrlTypeDao;
import com.pcitc.fms.dal.dao.NodeTypeDao;
import com.pcitc.fms.dal.dao.SamplepointTypeDao;
import com.pcitc.fms.dal.dao.SideMtrlTypeDao;
import com.pcitc.fms.dal.dao.SidelineTypeDao;
import com.pcitc.fms.dal.dao.SignboardTypeDao;
import com.pcitc.fms.dal.dao.SiloTypeDao;
import com.pcitc.fms.dal.dao.TankTypeDao;
import com.pcitc.fms.dal.dao.TransTypeDao;
import com.pcitc.fms.dal.dao.VcfTypeDao;

import pcitc.imp.common.ettool.utils.ObjectConverter;

/**
 * The type Node dict gather service.
 */
@Service
public class NodeDictGatherServiceImpl implements NodeDictGatherService {

	/**
	 * The constant log.
	 */
	private static Logger log = LoggerFactory.getLogger(NodeDictGatherServiceImpl.class);
	/**
	 * The Node type dao.
	 */
	@Autowired
	private NodeTypeDao nodeTypeDao;
	/**
	 * The Sideline type dao.
	 */
	@Autowired
	private SidelineTypeDao sidelineTypeDao;
	/**
	 * The Side mtrl type dao.
	 */
	@Autowired
	private SideMtrlTypeDao sideMtrlTypeDao;
	/**
	 * The Tank type dao.
	 */
	@Autowired
	private TankTypeDao tankTypeDao;
	/**
	 * The Vcf type dao.
	 */
	@Autowired
	private VcfTypeDao vcfTypeDao;
	/**
	 * The Equ technic dao.
	 */
	@Autowired
	private EquTechnicDao equTechnicDao;
	/**
	 * The Trans type dao.
	 */
	@Autowired
	private TransTypeDao transTypeDao;
	/**
	 * The Silo type dao.
	 */
	@Autowired
	private SiloTypeDao siloTypeDao;
	/**
	 * The Samplepoint type dao.
	 */
	@Autowired
	private SamplepointTypeDao samplepointTypeDao;
	/**
	 * The Signboard type dao.
	 */
	@Autowired
	private SignboardTypeDao signboardTypeDao;
	/**
	 * The Idx type dao.
	 */
	@Autowired
	private IdxTypeDao idxTypeDao;
	/**
	 * The Mtrl type dao.
	 */
	@Autowired
	private MtrlTypeDao mtrlTypeDao;

	/**
	 * Gets node types.
	 *
	 * @param code the code
	 * @return the node types
	 * @throws BusinessException the business exception
	 */
	@Override
	public List<NodeType> getNodeTypes(String code) throws BusinessException {
		List<com.pcitc.fms.bll.dictionary.entity.NodeType> orgTypes_entity = new ArrayList<>();
		List<com.pcitc.fms.dal.pojo.NodeType> orgTypes = new ArrayList<>();
		try {
			if(null != code && !"".equals(code)){
				orgTypes = nodeTypeDao.findByNodeTypeCode(code);
			}else{
				orgTypes = nodeTypeDao.findAll();
			}
			orgTypes_entity = ObjectConverter.listConverter(orgTypes,com.pcitc.fms.bll.dictionary.entity.NodeType.class);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new BusinessException("", e.getMessage());
		}
		return orgTypes_entity;
	}

	/**
	 * Gets sideline types.
	 *
	 * @param code the code
	 * @return the sideline types
	 * @throws BusinessException the business exception
	 */
	@Override
	public List<SidelineType> getSidelineTypes(String code) throws BusinessException {
		List<com.pcitc.fms.bll.dictionary.entity.SidelineType> dictlist_entity = new ArrayList<>();
		List<com.pcitc.fms.dal.pojo.SidelineType> dictlist_pojo = new ArrayList<>();
		try {
			if(null != code && !"".equals(code)){
				dictlist_pojo = sidelineTypeDao.findBySidelineTypeCode(code);
			}else{
				dictlist_pojo = sidelineTypeDao.findAll();
			}
			dictlist_entity = ObjectConverter.listConverter(dictlist_pojo,com.pcitc.fms.bll.dictionary.entity.SidelineType.class);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new BusinessException("", e.getMessage());
		}
		return dictlist_entity;
	}

	/**
	 * Gets side mtrl types.
	 *
	 * @param code the code
	 * @return the side mtrl types
	 * @throws BusinessException the business exception
	 */
	@Override
	public List<SideMtrlType> getSideMtrlTypes(String code) throws BusinessException {
		List<com.pcitc.fms.bll.dictionary.entity.SideMtrlType> dictlist_entity = new ArrayList<>();
		List<com.pcitc.fms.dal.pojo.SideMtrlType> dictlist_pojo = new ArrayList<>();
		try {
			if(null != code && !"".equals(code)){
				dictlist_pojo = sideMtrlTypeDao.findBySideMtrlTypeCode(code);
			}else{
				dictlist_pojo = sideMtrlTypeDao.getSideMtrl();
			}
			dictlist_entity = ObjectConverter.listConverter(dictlist_pojo,com.pcitc.fms.bll.dictionary.entity.SideMtrlType.class);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new BusinessException("", e.getMessage());
		}
		return dictlist_entity;
	}

	/**
	 * Gets tank types.
	 *
	 * @param code the code
	 * @return the tank types
	 * @throws BusinessException the business exception
	 */
	@Override
	public List<TankType> getTankTypes(String code) throws BusinessException {
		List<com.pcitc.fms.bll.dictionary.entity.TankType> dictlist_entity = new ArrayList<>();
		List<com.pcitc.fms.dal.pojo.TankType> dictlist_pojo = new ArrayList<>();
		try {
			if(null != code && !"".equals(code)){
				dictlist_pojo = tankTypeDao.findByTankTypeCode(code);
			}else{
				dictlist_pojo = tankTypeDao.findAll();
			}
			dictlist_entity = ObjectConverter.listConverter(dictlist_pojo,com.pcitc.fms.bll.dictionary.entity.TankType.class);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new BusinessException("", e.getMessage());
		}
		return dictlist_entity;
	}

	/**
	 * Gets vcf types.
	 *
	 * @param code the code
	 * @return the vcf types
	 * @throws BusinessException the business exception
	 */
	@Override
	public List<VcfType> getVcfTypes(String code) throws BusinessException {
		List<com.pcitc.fms.bll.dictionary.entity.VcfType> dictlist_entity = new ArrayList<>();
		List<com.pcitc.fms.dal.pojo.VcfType> dictlist_pojo = new ArrayList<>();
		try {
			if(null != code && !"".equals(code)){
				dictlist_pojo = vcfTypeDao.findByVcfTypeCode(code);
			}else{
				dictlist_pojo = vcfTypeDao.findAll();
			}
			dictlist_entity = ObjectConverter.listConverter(dictlist_pojo,com.pcitc.fms.bll.dictionary.entity.VcfType.class);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new BusinessException("", e.getMessage());
		}
		return dictlist_entity;
	}

	/**
	 * Gets equ technics.
	 *
	 * @param code the code
	 * @return the equ technics
	 * @throws BusinessException the business exception
	 */
	@Override
	public List<EquTechnic> getEquTechnics(String code) throws BusinessException {
		List<com.pcitc.fms.bll.dictionary.entity.EquTechnic> dictlist_entity = new ArrayList<>();
		List<com.pcitc.fms.dal.pojo.EquTechnic> dictlist_pojo = new ArrayList<>();
		try {
			if(null != code && !"".equals(code)){
				dictlist_pojo = equTechnicDao.findByTechnicCode(code);
			}else{
				dictlist_pojo = equTechnicDao.findAll();
			}
			dictlist_entity = ObjectConverter.listConverter(dictlist_pojo,com.pcitc.fms.bll.dictionary.entity.EquTechnic.class);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new BusinessException("", e.getMessage());
		}
		return dictlist_entity;
	}

	/**
	 * Gets trans types.
	 *
	 * @param code the code
	 * @return the trans types
	 * @throws BusinessException the business exception
	 */
	@Override
	public List<TransType> getTransTypes(String code) throws BusinessException {
		List<com.pcitc.fms.bll.dictionary.entity.TransType> dictlist_entity = new ArrayList<>();
		List<com.pcitc.fms.dal.pojo.TransType> dictlist_pojo = new ArrayList<>();
		try {
			if(null != code && !"".equals(code)){
				dictlist_pojo = transTypeDao.findByTransTypeCode(code);
			}else{
				dictlist_pojo = transTypeDao.findAll();
			}
			dictlist_entity = ObjectConverter.listConverter(dictlist_pojo,com.pcitc.fms.bll.dictionary.entity.TransType.class);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new BusinessException("", e.getMessage());
		}
		return dictlist_entity;
	}

	/**
	 * Gets silo types.
	 *
	 * @param code the code
	 * @return the silo types
	 * @throws BusinessException the business exception
	 */
	@Override
	public List<SiloType> getSiloTypes(String code) throws BusinessException {
		List<com.pcitc.fms.bll.dictionary.entity.SiloType> dictlist_entity = new ArrayList<>();
		List<com.pcitc.fms.dal.pojo.SiloType> dictlist_pojo = new ArrayList<>();
		try {
			if(null != code && !"".equals(code)){
				dictlist_pojo = siloTypeDao.findBySiloTypeCode(code);
			}else{
				dictlist_pojo = siloTypeDao.findAll();
			}
			dictlist_entity = ObjectConverter.listConverter(dictlist_pojo,com.pcitc.fms.bll.dictionary.entity.SiloType.class);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new BusinessException("", e.getMessage());
		}
		return dictlist_entity;
	}

	/**
	 * Gets samplepoint types.
	 *
	 * @param code the code
	 * @return the samplepoint types
	 * @throws BusinessException the business exception
	 */
	@Override
	public List<SamplepointType> getSamplepointTypes(String code) throws BusinessException {
		List<com.pcitc.fms.bll.dictionary.entity.SamplepointType> dictlist_entity = new ArrayList<>();
		List<com.pcitc.fms.dal.pojo.SamplepointType> dictlist_pojo = new ArrayList<>();
		try {
			if(null != code && !"".equals(code)){
				dictlist_pojo = samplepointTypeDao.findBySamplepointTypeCode(code);
			}else{
				dictlist_pojo = samplepointTypeDao.findAll();
			}
			dictlist_entity = ObjectConverter.listConverter(dictlist_pojo,com.pcitc.fms.bll.dictionary.entity.SamplepointType.class);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new BusinessException("", e.getMessage());
		}
		return dictlist_entity;
	}

	/**
	 * Gets signboard types.
	 *
	 * @param code the code
	 * @return the signboard types
	 * @throws BusinessException the business exception
	 */
	@Override
	public List<SignboardType> getSignboardTypes(String code) throws BusinessException {
		List<com.pcitc.fms.bll.dictionary.entity.SignboardType> dictlist_entity = new ArrayList<>();
		List<com.pcitc.fms.dal.pojo.SignboardType> dictlist_pojo = new ArrayList<>();
		try {
			if(null != code && !"".equals(code)){
				dictlist_pojo = signboardTypeDao.findBySignboardTypeCode(code);
			}else{
				dictlist_pojo = signboardTypeDao.findAll();
			}
			dictlist_entity = ObjectConverter.listConverter(dictlist_pojo,com.pcitc.fms.bll.dictionary.entity.SignboardType.class);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new BusinessException("", e.getMessage());
		}
		return dictlist_entity;
	}

	/**
	 * Gets idx types.
	 *
	 * @param code the code
	 * @return the idx types
	 * @throws BusinessException the business exception
	 */
	@Override
	public List<IdxType> getIdxTypes(String code) throws BusinessException {
		List<com.pcitc.fms.bll.dictionary.entity.IdxType> dictlist_entity = new ArrayList<>();
		List<com.pcitc.fms.dal.pojo.IdxType> dictlist_pojo = new ArrayList<>();
		try {
			if(null != code && !"".equals(code)){
				dictlist_pojo = idxTypeDao.findByIdxTypeCode(code);
			}else{
				dictlist_pojo = idxTypeDao.findAll();
			}
			dictlist_entity = ObjectConverter.listConverter(dictlist_pojo,com.pcitc.fms.bll.dictionary.entity.IdxType.class);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new BusinessException("", e.getMessage());
		}
		return dictlist_entity;
	}

	/**
	 * Gets mtrl types.
	 *
	 * @param code the code
	 * @return the mtrl types
	 * @throws BusinessException the business exception
	 */
	@Override
	public List<MtrlType> getMtrlTypes(String code) throws BusinessException {
		List<com.pcitc.fms.bll.dictionary.entity.MtrlType> dictlist_entity = new ArrayList<>();
		List<com.pcitc.fms.dal.pojo.MtrlType> dictlist_pojo = new ArrayList<>();
		try {
			if(null != code && !"".equals(code)){
				dictlist_pojo = mtrlTypeDao.findByMtrlTypeCode(code);
			}else{
				dictlist_pojo = mtrlTypeDao.findAll();
			}
			dictlist_entity = ObjectConverter.listConverter(dictlist_pojo,com.pcitc.fms.bll.dictionary.entity.MtrlType.class);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new BusinessException("", e.getMessage());
		}
		return dictlist_entity;
	}

}
