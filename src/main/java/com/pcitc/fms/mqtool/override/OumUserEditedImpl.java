package com.pcitc.fms.mqtool.override;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.pcitc.fms.dal.dao.DbPrimaryIdDao;
import com.pcitc.fms.dal.dao.OrgUnitContrastDao;
import com.pcitc.fms.dal.dao.OumUserDao;
import com.pcitc.fms.dal.dao.UserContrastDao;
import com.pcitc.fms.dal.dao.UserDao;
import com.pcitc.fms.dal.pojo.OrgUnitContrast;
import com.pcitc.fms.dal.pojo.OumUser;
import com.pcitc.fms.dal.pojo.User;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.mqtool.impl.UserEdited;

@Service
@Component
public class OumUserEditedImpl extends UserEdited implements OumUserEdited{
	
	@Autowired
	private OumUserDao oumUserDao;
	
	@Autowired
	private OrgUnitContrastDao orgUnitContrastDao;
	
	@Autowired
	private UserDao userDao;
	
	@Override
    public void process() {
		 try{
			 OumUser oumUser=new OumUser();
			 BeanUtils.copyProperties(getSyncUser(), oumUser);
			 OumUser oumUserOld = oumUserDao.findOumUserByCode(getSyncUser().getUserCode());
			 if(oumUserOld==null){
	    		 throw new BusinessException("", "不存在该oum用户");
	    	 }
			 OrgUnitContrast oc=orgUnitContrastDao.findByOrgUnitContrastId(oumUserOld.getOrgUnitId());
			 if(oc!=null){
				 User user=userDao.findUserByOrgIdAndUserCode(oc.getFmOrgId(),oumUserOld.getUserCode());
				 user.setBirthday(oumUser.getBirthday());
				 user.setEmail(oumUser.getEmail());
				 user.setTel(oumUser.getTel());
				 user.setSex(oumUser.getSex().intValue());
				 user.setJobDesc(oumUser.getJobDesc());
				 user.setMobile(oumUser.getMobile());
				 user.setUserName(oumUser.getUserName());
				 userDao.save(user);
			 }
    		 oumUser.setUserId(oumUserOld.getUserId());
    		 oumUser.setMntUserId("1");
    		 oumUser.setMntUserName("admin");
    		 oumUser.setMntUserDate(new Date());
    		 oumUserDao.save(oumUser);
		}catch(Exception e) {
			e.printStackTrace();
        }
	}

}
