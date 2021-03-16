package com.pcitc.fms.bll.stormItf;


import com.google.gson.Gson;
import com.pcitc.fms.common.ApplicationContextTool;
import com.pcitc.fms.service.stormModel.PipeNetTankCoef;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.pcitc.fms.exception.BusinessException;

/**
 *
 * @author lzp
 *
 */
public class AdjustedEchoHepler{
	private static final String TABLENAME = "PM_PIPENETTNK_COEF_T";
	private static Logger log = LoggerFactory.getLogger(AdjustedEchoHepler.class);
	public AdjustedEchoHepler() {

	}
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

	public String getPipeNetTankCoef(String month) throws BusinessException, SQLException {
		List<PipeNetTankCoef> list = new ArrayList<PipeNetTankCoef>();
		//执行
		Connection con = null;
		Statement pst = null;
		ResultSet ret = null;
		Gson gson = new Gson();
		try {
			con = getConnection();
			if(con == null){
				throw new BusinessException("","","getPipeNetTankCoef(con = null)!");
			}
			pst = con.createStatement();
			ret = pst.executeQuery("select COEFFICIENT from "+TABLENAME+" where MONTH="+month);

			while(ret.next()){
				PipeNetTankCoef ptf = new PipeNetTankCoef();
				Double coefficient = ret.getObject("COEFFICIENT")==null?null:((BigDecimal)ret.getObject("COEFFICIENT")).doubleValue();

				//封装
				ptf.setIntmonth(Integer.parseInt(month));
				ptf.setCoefficient(coefficient);

				list.add(ptf);
			}

		} catch (SQLException e) {
			throw new BusinessException("","","getPipeNetTankCoef is fail!"+e.toString());
		}finally{
			try {
				if(ret != null)ret.close();
				} catch (SQLException e) {
           log.error(e.getMessage());
				}finally {
              try {
								if(pst != null)pst.close();
              } catch (SQLException e) {
								log.error(e.getMessage());
              }finally {
                if(null!=con){
                  con.close();
                }
              }
            }
          }
		return gson.toJson(list);
	}

}

