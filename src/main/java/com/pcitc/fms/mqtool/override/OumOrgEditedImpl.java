package com.pcitc.fms.mqtool.override;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.pcitc.fms.dal.dao.OrgContrastDao;
import com.pcitc.fms.dal.dao.OumOrgDao;
import com.pcitc.fms.dal.dao.TPmOrgDao;
import com.pcitc.fms.dal.pojo.OumOrg;
import com.pcitc.fms.dal.pojo.TPmOrg;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.mqtool.impl.OrgEdited;

@Service
@Component
public class OumOrgEditedImpl extends OrgEdited implements OumOrgEdited{
	
	@Autowired
    private OumOrgDao oumOrgDao;
	
	@Autowired
	private TPmOrgDao tpmOrgDao;
	
	
	public void process(){
		 try{
			 OumOrg oumOrg=new OumOrg();
			 BeanUtils.copyProperties(getOrg(), oumOrg);
	    	 OumOrg oumOrgOld = oumOrgDao.findOumOrgByOrgCode(getOrg().getOrgCode());
	    	 TPmOrg fmOrg = tpmOrgDao.getTPmOrg(getOrg().getOrgCode());
	    	 
	    	 if(oumOrgOld==null){
	    		 throw new BusinessException("", "不存在该oum组织机构");
	    	 }
	    	 //自动更新Org表
	    	 if(fmOrg!=null){
	    		 fmOrg.setOrgAlias(oumOrg.getOrgAlia());
	    		 fmOrg.setOrgName(oumOrg.getOrgName());
	    		 tpmOrgDao.save(fmOrg);
	    	 }
	    	 
    		 oumOrg.setOrgId(oumOrgOld.getOrgId());
    		 oumOrg.setMntUserId("1");
    		 oumOrg.setMntUserName("admin");
    		 oumOrg.setMntUserDate(new Date());
    		 oumOrgDao.save(oumOrg);
	    	 
		}catch(Exception e) {
			e.printStackTrace();
        }
	}
}
