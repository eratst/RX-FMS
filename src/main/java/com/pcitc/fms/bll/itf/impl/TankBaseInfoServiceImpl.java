package com.pcitc.fms.bll.itf.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pcitc.fms.bll.entity.Tank;
import com.pcitc.fms.bll.entity.TankBaseInfo;
import com.pcitc.fms.bll.itf.MesTankService;
import com.pcitc.fms.bll.itf.TankBaseInfoService;
import com.pcitc.fms.bll.itf.TankService;
import com.pcitc.fms.dal.dao.TankBaseInfoDao;
import com.pcitc.fms.dal.dao.TankDao;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.service.model.Pager;

import pcitc.imp.common.ettool.utils.ObjectConverter;
@Service
public class TankBaseInfoServiceImpl implements TankBaseInfoService {
	
	@Autowired
	private MesTankService MesTankService;
	@Autowired
	private TankService tankService;
	@Autowired
	private  TankDao tankDao;
	@Autowired
	private TankBaseInfoDao tankBaseInfoDao;
	
	@Override
	public Pager<TankBaseInfo> getTankBaseInfo(com.pcitc.fms.service.model.TankBaseInfo tankmo, Pageable pageable) throws Exception {
		List<String> codeList = new ArrayList<>();;
		//步骤1
		if((tankmo.getIdList() == null || tankmo.getIdList().isEmpty()) && (tankmo.getCodeList() == null || tankmo.getCodeList().isEmpty())) {
				throw new BusinessException("","","请提供罐ID或者罐编码！");
		}else if((tankmo.getIdList() != null && !tankmo.getIdList().isEmpty()) && (tankmo.getCodeList() != null && !tankmo.getCodeList().isEmpty())) {
				throw new BusinessException("","","罐ID列表和罐编码列表只能输入一个！");
		}
		List<Integer> errId = new ArrayList<>();
//		List<String> codeLists = new ArrayList<>();
		//如果idList非空
		if(tankmo.getIdList() != null && !tankmo.getIdList().isEmpty()) {
			//遍历idList找codeList
			for(Integer id : tankmo.getIdList()) {
				com.pcitc.fms.dal.pojo.Tank tankp= tankDao.findOne(id);
//				List<com.pcitc.fms.dal.pojo.Tank> tanks=tankDao.findAll();
				
				Tank tanken = ObjectConverter.entityConverter(tankp, Tank.class);
				if(tanken != null) {
					String code = tanken.getNodeCode();
					//如果不为空 添加code到codeLists
					codeList.add(code);
				}else { //如果code为空 将id添加到idERR
					errId.add(id);
					//最后添加错误信息 idERR
				}
				
			}
		}else {
			codeList = tankmo.getCodeList();
		}
		List<String> ListErrorMes = new ArrayList<>();
		//如果idList为空
		//遍历codeLists 获取id
		//如果id为空 添加错误信息 【MES】根据罐编码获取罐ID失败，罐编码：XXX
		
		List<TankBaseInfo> tankBaseInfoList = new ArrayList<>();
		//多条记录 
		for(int i=0;i<codeList.size();i++) {
			List<Object> t= MesTankService.getTanks(codeList.get(i));
				String t2 = t.get(i).toString(); 
				JSONObject json = JSONObject.parseObject(t2);  
				JSONArray items = json.getJSONObject("collection").getJSONArray("items");
				//一条
				if(items.size() == 0) {
					ListErrorMes.add("【MES】根据罐编码获取罐ID失败，罐编码："+codeList.get(i));
				}
				for(int j=0;j<items.size();j++) {
					JSONObject item = items.getJSONObject(j);
					JSONArray data = item.getJSONArray("data");
					JSONObject id = data.getJSONObject(j);
					Integer idVal = (Integer) id.get("value");
					if(idVal == null) {
						ListErrorMes.add("【MES】根据罐编码获取罐ID失败，罐编码："+codeList.get(i));
					}else {
						//wl MesMtrlService 
						TankBaseInfo tank = new TankBaseInfo();
						//罐
						tank.setNodeId(idVal);
						//物料
//						tank.setMtrlCode("sss");
//						检尺  没有
//						tank.setChkTime("");
						tankBaseInfoList.add(tank);
						System.out.println(idVal);
				}
				System.out.println(json);
			}
			
			
			//用id获取罐编码、物料编码、检尺时间、罐存等信息
			//如果检尺时间为空 添加错误细信息【MES】根据罐ID获取罐检尺信息为空，罐ID：XXX
			//如果非空 添加到返回对象
		}
		tankmo.setCodeList(codeList);
		Pager<TankBaseInfo> pageData= new Pager<>();
		Page<com.pcitc.fms.dal.pojo.TankBaseInfo> tankpo = tankBaseInfoDao.findTankBaseInfos(tankmo, pageable);
		if(tankpo == null) {
			throw new BusinessException("","","未找到数据！");
		}else {
//			tankpo.getContent().get(0).setMtrlId("1");
//			tankpo.getContent().get(0).setChkTime(new Date());
//			tankpo.getContent().get(0).setNetoilMass(1);
//			tankpo.getContent().get(0).setMtrlCode("ss");
//			tankpo.getContent().get(0).setMtrlName("3453");
//			tankpo.getContent().get(0).setTankErrorMessage("asa");
			List<TankBaseInfo> entityList = ObjectConverter.listConverter(tankpo.getContent(), TankBaseInfo.class);
			
//			System.out.println("asasasa"+t.get(0).getName());
			pageData.setContent(entityList);
			pageData.setFirst(tankpo.isFirst());
			pageData.setLast(tankpo.isLast());
			pageData.setNumber(tankpo.getNumber());
			pageData.setNumberOfElements(tankpo.getNumberOfElements());
			pageData.setSize(tankpo.getSize());
			pageData.setSort(tankpo.getSort());
			pageData.setTotalElements(tankpo.getTotalElements());
			pageData.setTotalPages(tankpo.getTotalPages());
		}
		tankBaseInfoDao.flush();
		tankDao.flush();
		return pageData;
		
	
		
		//根据罐编码、物料编码查询罐基本信息
	}

}
