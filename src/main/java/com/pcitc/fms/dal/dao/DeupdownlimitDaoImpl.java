/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: DeupdownlimitDaoImpl
 * Date:18-3-9 上午8:33
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.dal.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pcitc.fms.common.ApplicationContextTool;
import com.pcitc.fms.dal.pojo.Deupdownlimit;
import com.pcitc.fms.exception.BusinessException;

/**
 * The type Deupdownlimit dao.
 */
@Service
public class DeupdownlimitDaoImpl {

	/**
	 * The constant log.
	 */
	private static Logger log = LoggerFactory.getLogger(DeupdownlimitDaoImpl.class);

	/**
	 * Gets connection.
	 *
	 * @return the connection
	 */
//	@PersistenceContext
//    private EntityManager em;
//	
//	private String createSql(com.pcitc.fms.service.model.Deupdownlimit model) {
//		String dataSql = AreaNodeBasicSql.deupdownlimit;
//		
//		if(model.getCode()!=null){
//			dataSql+=" op.code = '"+model.getCode()+"' and a.opeindexId=op.openindexId";
//		}
//	
//		dataSql +=" order by a.deupDownLimitId";
//		return dataSql;
//	}
//	@SuppressWarnings("unchecked")
//	public Page<com.pcitc.fms.dal.pojo.Deupdownlimit> findPageDeupdownlimits(com.pcitc.fms.service.model.Deupdownlimit model, Pageable pageable) {
//		Map<String ,Object> parameterMap = new HashedMap();
//		String dataSql = createSql(model);
//		Query dataQuery = em.createQuery(dataSql);
//		Integer skip = null;
//		List<com.pcitc.fms.dal.pojo.Deupdownlimit> list = dataQuery.getResultList();
//  		long count = list.size();
//		if(null != pageable){
//		    skip = model.getSkip();
//            dataQuery = em.createQuery(dataSql);
//            dataQuery.setFirstResult(skip);
//            dataQuery.setMaxResults(pageable.getPageSize());
//            return new  PageImpl<com.pcitc.fms.dal.pojo.Deupdownlimit>(dataQuery.getResultList(), pageable, count);
//            
//		}
//		return new PageImpl<com.pcitc.fms.dal.pojo.Deupdownlimit>(list, pageable, count);
//
//	}
	private Connection getConnection()  {
		DataSource dataSource = (DataSource) ApplicationContextTool.getBean("dataSource");
		Connection con = null;
		try {
			con = dataSource.getConnection();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return con;
//		return ConnectionFactory.getHikariConnection(dbParam);
	}

	/**
	 * Find page deupdownlimits list.
	 *
	 * @param model the model
	 * @return the list
	 * @throws BusinessException the business exception
	 * @throws SQLException the sql exception
	 */
	public List<com.pcitc.fms.dal.pojo.Deupdownlimit> findPageDeupdownlimits(com.pcitc.fms.service.model.Deupdownlimit model)
			throws BusinessException, SQLException {
		List<com.pcitc.fms.dal.pojo.Deupdownlimit> list = new ArrayList<com.pcitc.fms.dal.pojo.Deupdownlimit>();
		Connection con = null;
		java.sql.PreparedStatement pst = null;
		ResultSet ret = null;
		try {
			con = getConnection();
			if(con == null){
				throw new BusinessException("","","findPageDeupdownlimits(con = null)!");
			}
			String sql = "select *" + 
					"  from  T_OPM_DEUPDOWNLIMIT d\r\n" + 
					"  left join T_OPM_CRAFTSCHEME c on d.CRAFTSCHEME_ID = c.CRAFTSCHEME_ID\r\n" + 
					"  left join T_OPM_MONLEVEL m on d.MONLEVEL_ID=m.MONLEVEL_ID\r\n" + 
					" join  T_OPM_OPEINDEX op on op.OPEINDEX_ID=d.OPEINDEX_ID where op.OPEINDEX_STDCODE=?";
			pst = con.prepareStatement(sql);
			pst.setString(1, model.getCode());
			ret = pst.executeQuery();
			while (ret.next()) {
				com.pcitc.fms.dal.pojo.Deupdownlimit deu = new Deupdownlimit();
				deu.setApplyReason(ret.getString("APPLY_REASON"));
				deu.setAttentionFormula(ret.getString("ATTENTION_FORMULA"));
				deu.setAttentionValue(ret.getInt("ATTENTION_VALUE"));
				deu.setCmtDate(ret.getDate("CMTDATE"));
				deu.setCmtUserName(ret.getString("CMTUSER_NAME"));
				deu.setCmtUserCode(ret.getString("CMTUSER_CODE"));
				deu.setCraftScheme(ret.getString("CRAFTSCHEME_NAME"));
				deu.setCraftSchemeId(ret.getLong("CRAFTSCHEME_ID"));
				deu.setCrtUserName(ret.getString("CRTUSER_NAME"));
				deu.setCrtUserCode(ret.getString("CRTUSER_CODE"));
				deu.setCrtDate(ret.getDate("CRTDATE"));
				deu.setDes(ret.getString("DES"));
				deu.setDeupDownLimitId(ret.getLong("DEUPDOWNLIMIT_ID"));
				deu.setDownLimitFormula(ret.getString("DOWN_LIMIT_FORMULA"));
				deu.setDownLimitValue(ret.getInt("DOWN_LIMIT_VALUE"));
				deu.setMntUserName(ret.getString("MNTUSER_NAME"));
				deu.setMntUserCode(ret.getString("MNTUSER_CODE"));
				deu.setMntDate(ret.getDate("MNTDATE"));
				deu.setInUse(ret.getInt("INUSE"));
				deu.setMaxChangeTate(ret.getInt("MAX_CHANGE_RATE"));
				deu.setMinDvtTime(ret.getInt("MIN_DVT_TIME"));
				deu.setMntFlag(ret.getInt("MNT_FLAG"));
				deu.setMonFlag(ret.getInt("MON_FLAG"));
				deu.setMonLevel(ret.getString("MONLEVEL_NAME"));
				deu.setMonLevelId(ret.getLong("MONLEVEL_ID"));
				deu.setOpeindexId(ret.getLong("OPEINDEX_ID"));
				deu.setPtrlFlag(ret.getInt("PTRL_FLAG"));
				deu.setRiskAndSolution(ret.getString("RISK_AND_SOLUTION"));
				deu.setRlsDate(ret.getDate("RLSDATE"));
				deu.setRlsUserName(ret.getString("RLSUSER_NAME"));
				deu.setRlsUserCode(ret.getString("RLSUSER_CODE"));
				deu.setStartTime(ret.getDate("START_TIME"));
				deu.setStatus(ret.getInt("STATUS"));
				deu.setTechcardCtlFlag(ret.getInt("TECHCARD_CTL_FLAG"));
				deu.setThresholdValue(ret.getInt("THRESHOLD_VALUE"));
				deu.setUniqueId(ret.getString("UNIQUE_ID"));
				deu.setUpLimitFormula(ret.getString("UP_LIMIT_FORMULA"));
				deu.setUpLimitValue(ret.getInt("UP_LIMIT_VALUE"));
				
				list.add(deu);
			}
		} catch (Exception e) {

			throw new BusinessException("","","findPageDeupdownlimits is fail!"+e.toString());
		}
		finally{
			try {
				if(ret != null){
					ret.close();
				}
			} catch (SQLException e) {
				log.error(e.getMessage());
			}finally {
				try {
					if(pst != null){
            pst.close();
          }
				} catch (SQLException e) {
					log.error(e.getMessage());
				} finally {
					if(con != null){
						con.close();
					}
				}
			}
		}
		return list;
	}
}
