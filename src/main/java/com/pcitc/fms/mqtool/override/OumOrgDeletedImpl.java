package com.pcitc.fms.mqtool.override;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.pcitc.fms.dal.dao.OumOrgDao;
import com.pcitc.fms.dal.pojo.OumOrg;
import com.pcitc.fms.mqtool.impl.OrgDeleted;
import com.pcitc.imp.common.exception.BusiException;

@Service
@Component
public class OumOrgDeletedImpl extends OrgDeleted implements OumOrgDeleted{
	@Autowired
    private OumOrgDao oumOrgDao;
	
	@Override
    public void process() {
	     try{
	    	 OumOrg oumOrg = oumOrgDao.findOumOrgByOrgCode(getOrg().getOrgCode());
	    	 if(oumOrg!=null){
	    		 oumOrgDao.deleteOumOrgByOrgCode(getOrg().getOrgCode());
	    	 }else{
	    		 throw new BusiException("", "不存在该oum组织机构");
	    	 }
	    	 
		}catch(Exception e) {
			e.printStackTrace();
        }
		
	}

}
