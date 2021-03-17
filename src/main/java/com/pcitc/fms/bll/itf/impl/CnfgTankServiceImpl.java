package com.pcitc.fms.bll.itf.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pcitc.fms.bll.entity.CnfgTank;
import com.pcitc.fms.bll.itf.CnfgTankService;
import com.pcitc.fms.bll.itf.TankAreaService;
import com.pcitc.fms.bll.itf.TankService;
import com.pcitc.fms.common.RentCondition;
import com.pcitc.fms.dal.dao.CnfgTankDao;
import com.pcitc.fms.dal.dao.IcCnfgBaseDao;
import com.pcitc.fms.dal.pojo.Tank;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.service.model.Pager;

import pcitc.imp.common.ettool.utils.ObjectConverter;

@Service
public class CnfgTankServiceImpl implements CnfgTankService {

	@Autowired
	private CnfgTankDao cnfgTankDao;

	@Autowired
	private IcCnfgBaseDao IcCnfgBaseDao;

	@Override
	@SuppressWarnings("unused")
	public Pager<CnfgTank> findCnfgTanks(com.pcitc.fms.service.model.CnfgTank cnfgTank, Pageable pageable)
			throws Exception {
		Pager<CnfgTank> pageData = new Pager<>();
		if(cnfgTank.getNodeCode()!=null && cnfgTank.getNodeCodeList()==null){
			pageData = findByNodeCode(cnfgTank,pageable,cnfgTank.getNodeCode());
		}
		if(cnfgTank.getNodeCodeList()!=null && cnfgTank.getNodeCode()==null){
			List<CnfgTank> content2=new ArrayList<CnfgTank>();
			String[] split = cnfgTank.getNodeCodeList().split(",");
			for(int i=0;i<split.length;i++){
				pageData = findByNodeCode(cnfgTank,pageable,split[i]);
				List<CnfgTank> content = pageData.getContent();
				for (CnfgTank cnfgTank2 : content) {
					content2.add(cnfgTank2);
				}
			}
			Integer totalElements = content2.size();
			pageData.setContent(content2);
			pageData.setTotalElements(totalElements.longValue());
		}
		
		return pageData;

	}
	
	public Pager<CnfgTank> findByNodeCode(com.pcitc.fms.service.model.CnfgTank cnfgTank,Pageable pageable,String nodeCode) throws Exception{
		Pager<CnfgTank> pageData = new Pager<>();
		List<com.pcitc.fms.bll.entity.CnfgTank> EntityList = null;
		 //通过nodeCode查询nodeId,areaId,tankTypeId放到cnfgTankToQuery中
		com.pcitc.fms.dal.pojo.CnfgTank cnfgTankToQuery = cnfgTankDao.findTank(nodeCode);
		 if(cnfgTankToQuery==null){
			 throw new BusinessException("", "nodeCode:不存在！");
		 }
		 if(cnfgTankToQuery!=null){
			 cnfgTank.setNodeId(cnfgTankToQuery.getNodeId());
		 }
		 //查询单罐配置的信息
		 Page<com.pcitc.fms.dal.pojo.CnfgTank> properties = cnfgTankDao.findCnfgTanks(cnfgTank, pageable);
		 List<com.pcitc.fms.dal.pojo.CnfgTank> content = properties.getContent();
		 List<Long> cnfgClassIdList=new ArrayList<Long>();
		 if(content.size()>0){
			 for(int i=0;i<content.size();i++){
				 cnfgClassIdList.add(content.get(i).getCnfgClassId());
			 }
		 }
		 List<String> tankTypeIdList=new ArrayList<String>();
		 tankTypeIdList.add(0+"");
		 if(cnfgTankToQuery!=null){
			 tankTypeIdList.add(cnfgTankToQuery.getTankTypeId()+"");
		 }
		 List<com.pcitc.fms.dal.pojo.CnfgTank> content2=new ArrayList<com.pcitc.fms.dal.pojo.CnfgTank>();
		 //查询基础配置的信息
		 if(cnfgTank.getRentCode()!=null || cnfgTank.getBizCode()!=null){
			 //获取租户对应的组织机构Code
			 RentCondition rentCondition=new RentCondition();
			 List<String> orgCodes = rentCondition.getRentCondition(cnfgTank.getRentCode(), cnfgTank.getBizCode());
			 //如果orgCodes为空,则查不到
			 if(orgCodes!=null && orgCodes.size()>0){
				 if(cnfgClassIdList.size()>0){
					 content2=IcCnfgBaseDao.findCnfgBaseForRent(cnfgTankToQuery.getTankAreaId(),tankTypeIdList,cnfgClassIdList,orgCodes);
				 }else{
					 content2=IcCnfgBaseDao.findCnfgBaseForRent1(cnfgTankToQuery.getTankAreaId(),tankTypeIdList,orgCodes);
				 }
			 }
		 }else{
			 if(cnfgClassIdList.size()>0){
				 content2=IcCnfgBaseDao.findCnfgBase(cnfgTankToQuery.getTankAreaId(),tankTypeIdList,cnfgClassIdList);
			 }else{
				 content2=IcCnfgBaseDao.findCnfgBase1(cnfgTankToQuery.getTankAreaId(),tankTypeIdList);
			 }
		 }
		 if(content.size()>0){
			 //放置nodeCode
			 for (com.pcitc.fms.dal.pojo.CnfgTank cnfgTank2 : content2) {
				 cnfgTank2.setNodeCode(nodeCode);
			 }
			 //将查到的基础配置信息放到里面
			 for(int i=0;i<content.size();i++){
				 content2.add(content.get(i));
			 }
		 }
		 EntityList=ObjectConverter.listConverter(content2, com.pcitc.fms.bll.entity.CnfgTank.class);
		 Integer totalElement=content2.size();
			pageData.setContent(EntityList);
			pageData.setFirst(properties.isFirst());
			pageData.setLast(properties.isLast());
			pageData.setNumber(properties.getNumber());
			pageData.setNumberOfElements(properties.getNumberOfElements());
			pageData.setSize(properties.getSize());
			pageData.setSort(properties.getSort());
			pageData.setTotalElements(totalElement.longValue());
			pageData.setTotalPages(properties.getTotalPages());
		return pageData;
	}

}
