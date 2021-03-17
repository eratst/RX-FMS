package com.pcitc.fms.mqtool.override;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.pcitc.fms.dal.dao.OumOrgUnitDao;
import com.pcitc.fms.dal.dao.TPmOrgDao;
import com.pcitc.fms.dal.pojo.OumOrgUnit;
import com.pcitc.fms.dal.pojo.TPmOrg;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.mqtool.impl.OrgUnitEdited;

@Service
@Component
public class OumOrgUnitEditedImpl extends OrgUnitEdited implements OumOrgUnitEdited{
	@Autowired
    private OumOrgUnitDao oumOrgUnitDao;
	
	@Autowired
	private TPmOrgDao tpmOrgDao;
	
	@Override
    public void process() {
		 try{
			 OumOrgUnit oumOrgUnit=new  OumOrgUnit();
			 BeanUtils.copyProperties(getOrgUnit(), oumOrgUnit);
			 OumOrgUnit oumOrgUnitOld = oumOrgUnitDao.findOumOrgUnitByCode(getOrgUnit().getOrgUnitCode());
			 TPmOrg fmOrg = tpmOrgDao.getTPmOrg(getOrgUnit().getOrgUnitCode());
			 
	    	 if(oumOrgUnitOld==null){
	    		 throw new BusinessException("", "不存在该oum机构单元");
	    	 }
	    	 //自动更新Org表
	    	 if(fmOrg!=null){
	    		 fmOrg.setOrgAlias(oumOrgUnit.getOrgUnitAlia());
	    		 fmOrg.setOrgName(oumOrgUnit.getOrgUnitName());
	    		 tpmOrgDao.save(fmOrg);
	    	 }
    		 oumOrgUnit.setOrgId(oumOrgUnitOld.getOrgUnitId());
    		 oumOrgUnit.setMntUserId("1");
    		 oumOrgUnit.setMntUserName("admin");
    		 oumOrgUnit.setMntUserDate(new Date());
    		 oumOrgUnitDao.save(oumOrgUnit);
	    	 
		}catch(Exception e) {
			e.printStackTrace();
        }
	}
}
