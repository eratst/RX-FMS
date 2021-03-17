package com.pcitc.fms.common.dispatcher;


import java.util.Enumeration;
import java.util.Hashtable;
import java.util.ResourceBundle;

import com.pcitc.fms.common.CheckUtil;



 /**
 * Title: SysGlobal
 * <p>Description: 用于获得系统配置文件中的信息</p>
 * @author 赵振强
 * @date 2017年8月4日
 * @version 1.0
 */
public class SysGlobal {

    private static String encode_from_input = null;
    private static String encode_from_db = null;

    private static String db_drive_name = null;

    private static String db_url = null;
    private static String db_user_id = null;
    private static String db_password = null;
    
    //add by 非核心数据源
    private static String noncore_data_source_name = null;
    private static String noncore_initial_context_factory = null;
    private static String noncore_provider_url = null;
    private static String noncore_security_principal = null;
    private static String noncore_security_cerdentials = null;

    private static String order_provider_url = null;
    private static String order_security_principal = null;
    private static String order_security_cerdentials = null;


    private static Hashtable dispatcher_hash = null;
    private static Hashtable config_hash = null;

	private static final Object objWait = new Object();
    private static String operaionFlag = null;


    /**
     * 获得 order_security_cerdentials
     * @return
     * @see <br>author 李飞
     */
    public static String getOrderSecurityCerdentials() {
		initParam();
        return order_security_cerdentials;
    }

    /**
     * 获得 order_security_principal
     * @return
     * @see <br>author 李飞
     */
    public static String getOrderSecurityPrincipal() {
		initParam();
        return order_security_principal;
    }

    /**
     * 获得 order_provider_url
     * @return
     * @see <br>author 李飞
     */
    public static String getOrderProviderUrl() {
		initParam();
        return order_provider_url;
    }


    
    
    /**
     * 获得 initial_context_factory
     * @return
     * @see <br>author 赵振强
     */
    public static String getNonCoreDataSourceName() {
		initParam();
        return noncore_data_source_name;
    }

    /**
     * 获得 initial_context_factory
     * @return
     * @see <br>author 赵振强
     */
    public static String getNonCoreInitialContextFactory() {
		initParam();
        return noncore_initial_context_factory;
    }

    /**
     * 获得 provider url
     * @return
     * @see <br>author 赵振强
     */
    public static String getNonCoreProviderUrl() {
		initParam();
        return noncore_provider_url;
    }

    /**
     * 获得 security principal
     * @return
     * @see <br>author 赵振强
     */
    public static String getNonCoreSecurityPrincipal() {
		initParam();
        return noncore_security_principal;
    }

    /**
     * 获得 security cerdentials
     * @return
     * @see <br>author 赵振强
     */
    public static String getNonCoreSecurityCerdentials() {
		initParam();
        return noncore_security_cerdentials;
    }
        
    
    /**
     * 获得 DB 的 Drive Name
     * @return
     * @see <br>author 赵振强
     */
    public static String getDBDriveName() {
    	initParam();
    	return db_drive_name;
    }
    
    /**
     * 获得 DB 的 URL
     * @return
     * @see <br>author 赵振强
     */
    
    public static String getDBUrl() {
    	initParam();
    	return db_url;
    }

    
    /**
     * 获得 DB 的 USER ID
     * @return
     * @see <br>author 赵振强
     */

    public static String getDBUserId() {
		initParam();
        return db_user_id;
    }

    /**
     * 获得 DB 的 PASS_WORD
     * @return
     * @see <br>author 赵振强
     */

    public static String getDBPassword() {
		initParam();
        return db_password;
    }




    /**
     * 获得对页面的中文进行转码的 encode
     * @return
     * @see <br>author 赵振强
     */

    public static String getEncodeFromInput() {
		initParam();
        return encode_from_input;
    }

    /**
     * 获得对 数据库 的中文进行转码的 encode
     * @return
     * @see <br>author 赵振强
     */

    public static String getEncodeFromDB() {
		initParam();
        return encode_from_db;
    }

    /**
     * 读取系统的配置文件，获取系统定义的变量。
     * 这个方法会在 Controller 中的 init() 方法中运行，执行一次就可以了，其他地方不用再运行；
     * @see <br>author 赵振强
     */
	public static void initParam()
	{
		
		if(dispatcher_hash == null)
		{
			synchronized(objWait)
			{
				if(dispatcher_hash == null)
				{
					setDispatcherParam();
				}
			}
		}
//		if(config_hash == null)
//		{
//			synchronized(objWait)
//			{
//				if(config_hash == null)
//				{
//					setConfigParam();
//				}
//			}
//		}
		
	}

	public void init()
	{
		initParam();
	}

    /**
     * 获得转发的类名的集合。
     * @return
     * @see <br>author 赵振强
     */

    public static String getDispatcherParam(String param_name)
	{
		initParam();
        return CheckUtil.stringValue(dispatcher_hash.get(param_name));
    }

    /**
     * 获得转发的类名的集合。
     * @return
     * @see <br>author 赵振强
     */

    public static String getConfigParam(String param_name) {
		initParam();
        return CheckUtil.stringValue(config_hash.get(param_name));
    }

    /**
     *获得CONFIG配置文件的内容。
     */

    private static void setConfigParam() {
        //==获得配置文件
        ResourceBundle rb_config = ResourceBundle.getBundle("config");
        Enumeration enu = rb_config.getKeys();

        config_hash = new Hashtable();
        String key_name;

        if (enu != null) {
            while (enu.hasMoreElements()) {
                key_name = String.valueOf(enu.nextElement());
                config_hash.put(key_name, rb_config.getString(key_name));
            }
        }

        //==获得中文转换的编码
        encode_from_input = getConfigParam("encodingFromInput"); //rb_config.getString("encodingFromInput");
        encode_from_db = getConfigParam("encodingFromDB"); //rb_config.getString("encodingFromDB");

        if (encode_from_input.equals("")) {
            encode_from_input = null;
        }
        if (encode_from_db.equals("")) {
            encode_from_db = null;
        }
        //== get db connection config
        db_drive_name = getConfigParam("driver"); //rb_config.getString("driver");

        db_url = getConfigParam("url"); //rb_config.getString("url");
        db_user_id = getConfigParam("userid"); //rb_config.getString("userid");
        db_password = getConfigParam("passwd"); //
        
        //==get jiaoqu config info
        order_provider_url = getConfigParam("order_provider_url");
        order_security_principal = getConfigParam("order_security_principal");
        order_security_cerdentials = getConfigParam("order_security_cerdentials");
        
        noncore_data_source_name = getConfigParam("noncore_data_source_name"); 
        noncore_initial_context_factory = getConfigParam("noncore_initial_context_factory"); 
        noncore_provider_url = getConfigParam("noncore_provider_url"); 
        noncore_security_principal = getConfigParam("noncore_security_principal"); 
        noncore_security_cerdentials = getConfigParam("noncore_security_cerdentials"); 

		//get operaion_flag config info
		operaionFlag =getConfigParam("operaion_flag");
    }

    /**
     * 获得dispatcher配置文件的内容。
     */

    private static void setDispatcherParam() {
        //==获得配置文件
        ResourceBundle rb_dispatcher = ResourceBundle.getBundle("dispatcher");
        Enumeration enu = rb_dispatcher.getKeys();

        dispatcher_hash = new Hashtable();
        String key_name;

        if (enu != null) {
            while (enu.hasMoreElements()) {
                key_name = String.valueOf(enu.nextElement());
                dispatcher_hash.put(key_name, rb_dispatcher.getString(key_name));
            }
        }
    }
    public static String getOperaionFlag() {
		initParam();
		return operaionFlag;
    }

}
