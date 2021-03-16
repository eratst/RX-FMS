package com.pcitc.fms.mqtool.override;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.pcitc.fms.dal.dao.OumOrgUnitDao;
import com.pcitc.fms.dal.pojo.OumOrgUnit;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.mqtool.impl.OrgUnitDeleted;

@Service
@Component
public class OumOrgUnitDeletedImpl extends OrgUnitDeleted implements OumOrgUnitDeleted{
	@Autowired
    private OumOrgUnitDao oumOrgUnitDao;
	
	@Override
    public void process() {
	     try{
	    	 OumOrgUnit oumOrgUnit = oumOrgUnitDao.findOumOrgUnitByCode(getOrgUnit().getOrgUnitCode());
	    	 if(oumOrgUnit!=null){
	    		 oumOrgUnitDao.deleteOumOrgUnitByOrgUnitCode(getOrgUnit().getOrgUnitCode());
	    	 }else{
	    		 throw new BusinessException("", "不存在该oum机构单元");
	    	 }
		}catch(Exception e) {
			e.printStackTrace();
        }
	}
	
}
