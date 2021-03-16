package com.pcitc.fms.mqtool.override;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.pcitc.fms.dal.dao.OumUserDao;
import com.pcitc.fms.dal.pojo.OumUser;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.mqtool.impl.UserDeleted;

@Service
@Component
public class OumUserDeletedImpl extends UserDeleted implements OumUserDeleted{
	@Autowired
	private OumUserDao oumUserDao;
	
	@Override
    public void process() {
	     try{
	    	 OumUser oumUser = oumUserDao.findOumUserByCode(getSyncUser().getUserCode());
	    	 if(oumUser!=null){
	    		 oumUserDao.deleteOumUserByUserCode(getSyncUser().getUserCode());
	    	 }else{
	    		 throw new BusinessException("", "不存在该oum用户");
	    	 }
		}catch(Exception e) {
			e.printStackTrace();
        }
	}

}
