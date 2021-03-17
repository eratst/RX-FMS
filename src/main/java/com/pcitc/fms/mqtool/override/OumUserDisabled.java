package com.pcitc.fms.mqtool.override;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.pcitc.fms.dal.dao.OrgUnitContrastDao;
import com.pcitc.fms.dal.dao.OumUserDao;
import com.pcitc.fms.dal.dao.UserDao;
import com.pcitc.fms.dal.pojo.OrgUnitContrast;
import com.pcitc.fms.dal.pojo.OumUser;
import com.pcitc.fms.dal.pojo.User;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.mqtool.impl.UserDisabled;

@Service
@Component
public class OumUserDisabled extends UserDisabled{
	
	@Autowired
	private OumUserDao oumUserDao;
	
	@Autowired
	private OrgUnitContrastDao orgUnitContrastDao;
	
	@Autowired
	private UserDao userDao;
	
	@Override
    public void process() {
		try{
			OumUser oumUser = oumUserDao.findOumUserByCode(getSyncUser().getUserCode());
			if(oumUser==null){
				throw new BusinessException("", "该oum用户不存在！");
			}
			OrgUnitContrast oc=orgUnitContrastDao.findByOrgUnitContrastId(oumUser.getOrgUnitId());
			if(oc!=null){
        		User user=userDao.findUserByOrgIdAndUserCode(oc.getFmOrgId(),oumUser.getUserCode());
        		if(user!=null){
        			user.setInUse(0);
        			userDao.save(user);
        		}
			}
			oumUser.setEnabled(0l);
			oumUserDao.save(oumUser);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
