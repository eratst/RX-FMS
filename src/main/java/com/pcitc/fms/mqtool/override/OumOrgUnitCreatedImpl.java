package com.pcitc.fms.mqtool.override;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.pcitc.fms.dal.dao.DbPrimaryIdDao;
import com.pcitc.fms.dal.dao.OrgUnitContrastDao;
import com.pcitc.fms.dal.dao.OumOrgDao;
import com.pcitc.fms.dal.dao.OumOrgUnitDao;
import com.pcitc.fms.dal.dao.TPmOrgDao;
import com.pcitc.fms.dal.pojo.OrgContrast;
import com.pcitc.fms.dal.pojo.OrgUnitContrast;
import com.pcitc.fms.dal.pojo.OumOrg;
import com.pcitc.fms.dal.pojo.OumOrgUnit;
import com.pcitc.fms.dal.pojo.TPmOrg;
import com.pcitc.fms.mqtool.impl.OrgUnitCreated;

import amq.synchronize.model.SyncEvent;

@Service
@Component
public class OumOrgUnitCreatedImpl extends OrgUnitCreated implements OumOrgUnitCreated{
	
	@Autowired
    private OumOrgUnitDao oumOrgUnitDao;
	
	@Autowired
	private TPmOrgDao tpmOrgDao;
	
	@Autowired
	private OrgUnitContrastDao orgUnitContrastDao;
	
	@Autowired
	private DbPrimaryIdDao dbPrimaryIdDao;
	
	@Autowired
	private OumOrgDao oumOrgdao;
	
	@Override
    public void process(SyncEvent evt) {
		OumOrgUnit oumOrgUnit=new OumOrgUnit();
        BeanUtils.copyProperties(getOrgUnit(), oumOrgUnit);
        //处理简称为空的情况
    	if(StringUtils.isEmpty(oumOrgUnit.getOrgUnitAlia())){
			oumOrgUnit.setOrgUnitAlia(oumOrgUnit.getOrgUnitName());
		}
    	oumOrgUnit.setIsDeploy(0);
    	oumOrgUnit = oumOrgUnitDao.save(oumOrgUnit);
    	//处理组织机构ID
    	String orgCode=evt.getOrgcode();
    	OumOrg oumOrg = oumOrgdao.findOumOrgByOrgCode(orgCode);
    	oumOrgUnit.setOrgId(oumOrg.getOrgId());
        try{
        	TPmOrg tPmOrg = tpmOrgDao.getTPmOrg(oumOrgUnit.getOrgUnitCode());
        	if(tPmOrg!=null){
        		//自动存关联表
        		List<BigDecimal> seqId = dbPrimaryIdDao.getSeqId(1, "T_PM_ORGUNITCONTRAST");
        		OrgUnitContrast orgUnitContrast=new OrgUnitContrast();
        		orgUnitContrast.setOrgUnitContrastId(seqId.get(0).longValue());
        		orgUnitContrast.setFmOrgId(tPmOrg.getOrgId());
        		orgUnitContrast.setSortNum(0);
        		orgUnitContrast.setVersion(0);
        		orgUnitContrast.setInUse(0);
        		orgUnitContrast.setOumOrgUnitId(oumOrgUnit.getOrgUnitId());
        		orgUnitContrastDao.save(orgUnitContrast);
        		oumOrgUnit.setIsDeploy(1);
        	}
        }catch(Exception e){
        	e.printStackTrace();
        }
	}
}
