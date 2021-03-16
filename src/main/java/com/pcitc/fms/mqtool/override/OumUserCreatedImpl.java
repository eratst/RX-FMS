package com.pcitc.fms.mqtool.override;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.dal.dao.DbPrimaryIdDao;
import com.pcitc.fms.dal.dao.OrgUnitContrastDao;
import com.pcitc.fms.dal.dao.OumOrgUnitDao;
import com.pcitc.fms.dal.dao.OumUserDao;
import com.pcitc.fms.dal.dao.TPmOrgDao;
import com.pcitc.fms.dal.dao.UserContrastDao;
import com.pcitc.fms.dal.dao.UserDao;
import com.pcitc.fms.dal.pojo.OrgUnitContrast;
import com.pcitc.fms.dal.pojo.OumOrgUnit;
import com.pcitc.fms.dal.pojo.OumUser;
import com.pcitc.fms.dal.pojo.TPmOrg;
import com.pcitc.fms.dal.pojo.User;
import com.pcitc.fms.dal.pojo.UserContrast;
import com.pcitc.fms.mqtool.impl.UserCreated;

import amq.synchronize.model.SyncEvent;
import amq.synchronize.model.SyncUser;

@Service
@Component
public class OumUserCreatedImpl extends UserCreated implements OumUserCreated{
	
	@Autowired
	private OumUserDao oumUserDao;
	
	@Autowired
	private OrgUnitContrastDao orgUnitContrastDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserContrastDao userContrastDao;
	
	@Autowired
	private DbPrimaryIdDao dbPrimaryIdDao;
	
	@Autowired
	private OumOrgUnitDao oumOrgUnitDao;
	
	@Autowired
	private TPmOrgDao tPmOrgDao;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
    public void process(SyncEvent evt) {
		OumUser oumUser=new OumUser();
		SyncUser syncUser2 = getSyncUser();
        BeanUtils.copyProperties(syncUser2, oumUser);
        //处理上级机构单元ID
        String oumOrgUnitCode=evt.getOrgunitcode();
        OumOrgUnit oumOrgUnit=oumOrgUnitDao.findOumOrgUnitByCode(oumOrgUnitCode);
        if (oumOrgUnit!=null) {
        	oumUser.setOrgUnitId(oumOrgUnit.getOrgUnitId());
		}
        try{
        	oumUser.setIsDeploy(0);
        	oumUserDao.save(oumUser);
        	oumUserDao.flush();
        	TPmOrg tPmOrg = tPmOrgDao.getTPmOrg(oumOrgUnitCode);
        	if (tPmOrg!=null) {
        		User user=userDao.findUserByOrgIdAndUserCode(tPmOrg.getOrgId(),oumUser.getUserCode());
        		if(user==null){
        			user=new User();
        			//添加fms用户
        			List<BigDecimal> seqId = dbPrimaryIdDao.getSeqId(1, "T_PM_USER");
        			BeanUtils.copyProperties(getSyncUser(), user);
        			user.setUserId(seqId.get(0).longValue());
        			user.setOrgId(tPmOrg.getOrgId());
        			if (oumUser.getCrtUserId()!=null) {
        				user.setCrtUserCode(oumUser.getCrtUserId());
					}else{
						user.setCrtUserCode("admin");
					}
        			if (oumUser.getCrtUserName()!=null) {
        				user.setCrtUserName(oumUser.getCrtUserName());
					}else{
						user.setCrtUserName("admin");
					}
        			if (oumUser.getMntUserId()!=null) {
        				user.setMntUserCode(oumUser.getMntUserId());
					}else{
						user.setMntUserCode("admin");
					}
        			if (oumUser.getMntUserName()!=null) {
        				user.setMntUserName(oumUser.getMntUserName());
					}else{
						user.setMntUserName("admin");
					}
        			user.setCrtDate(new Date());
        			user.setMntDate(new Date());
        			user.setVersion(0);
        			user.setInUse(1);
        			user.setSortNum(0);
        			userDao.save(user);
        			userDao.flush();
            		//用户对照表添加数据
        			List<BigDecimal> seqUserContrastId = dbPrimaryIdDao.getSeqId(1, "T_PM_USERCONTRAST");
        			UserContrast userContrast =new UserContrast();
        			userContrast.setUserContrastId(seqUserContrastId.get(0).longValue());
        			userContrast.setFmUserId(user.getUserId());
        			userContrast.setOumUserId(oumUser.getUserId());
        			userContrast.setCrtUserName("admin");
        			userContrast.setMntUserName("admin");
        			userContrast.setCrtUserCode("admin");
        			userContrast.setMntUserCode("admin");
        			userContrast.setCrtDate(new Date());
        			userContrast.setMntDate(new Date());
        			userContrast.setVersion(0);
        			userContrast.setInUse(1);
        			userContrast.setSortNum(0);
        			userContrastDao.save(userContrast);
        			userContrastDao.flush();
                	oumUser.setIsDeploy(1);
                	oumUserDao.save(oumUser);
        		}
			} 
        }catch(Exception e){
        	e.printStackTrace();
        }
	}

}
