package com.pcitc.fms.mqtool.override;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.pcitc.fms.dal.dao.DbPrimaryIdDao;
import com.pcitc.fms.dal.dao.OrgContrastDao;
import com.pcitc.fms.dal.dao.OumOrgDao;
import com.pcitc.fms.dal.dao.TPmOrgDao;
import com.pcitc.fms.dal.pojo.OrgContrast;
import com.pcitc.fms.dal.pojo.OumOrg;
import com.pcitc.fms.dal.pojo.TPmOrg;
import com.pcitc.fms.mqtool.impl.OrgCreated;

/**
 * Created by dell on 2018/11/19.
 */
@Service
@Component
public class OumOrgCreatedImpl extends OrgCreated implements OumOrgCreated{
	
	@Autowired
    private OumOrgDao oumOrgDao;
	
	@Autowired
	private TPmOrgDao tpmOrgDao;
	
	@Autowired
	private OrgContrastDao orgContrastDao;
	
	@Autowired
	private DbPrimaryIdDao dbPrimaryIdDao;
	
    @Override
    public void process() {
        OumOrg oumOrg=new OumOrg();
        BeanUtils.copyProperties(getOrg(), oumOrg);
        try{
        	oumOrgDao.save(oumOrg);
        	TPmOrg tPmOrg = tpmOrgDao.getTPmOrg(oumOrg.getOrgCode());
        	if(tPmOrg!=null){
        		//自动存关联表
        		List<BigDecimal> seqId = dbPrimaryIdDao.getSeqId(1, "T_PM_ORGCONTRAST");
        		OrgContrast orgContrast=new OrgContrast();
        		orgContrast.setOrgContrastId(seqId.get(0).longValue());
        		orgContrast.setFmOrgId(tPmOrg.getOrgId());
        		orgContrast.setOumOrgId(oumOrg.getOrgId());
        		orgContrastDao.save(orgContrast);
        	}
        }catch(Exception e){
        	e.printStackTrace();
        }
    }
}
