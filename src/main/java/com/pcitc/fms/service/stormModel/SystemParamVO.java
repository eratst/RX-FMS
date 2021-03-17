package com.pcitc.fms.service.stormModel;


import java.io.Serializable;

/**
 * 系统参数
 * 
 * @author pcitc
 *
 */
public class SystemParamVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//----------------通用信息------------------------

	private String toplogyName;
	
	private int worker_num;

	private int tuple_batch;//批量

	// ---------------数据库信息-----------------------

	private String dbtype = "mysql";//数据库类型

	private String dbURL;

	private String driverClass;

//	private String dbdriver = "oracle.jdbc.driver.OracleDriver";// 数据库驱动
	private String dbdriver = "com.mysql.jdbc.Driver";
	private String dbip;// 数据库服务器ip

	private String dbName;// 数据库实例

	private String username;// 数据库用户名

	private String password;// 数据库密码

	private int initialSize;// 初始化连接

	private int maxActive;// 最大连接数量

	private int minIdle;// 最小空闲连接

	private int maxIdle;// 最大空闲连接

	private boolean removeAbandoned;// 是否自动回收超时连接

	private int removeAbandonedTimeout;// 超时时间

	private int maxWait;// 最大等待时间
	
	//---------------服务地址------------------------------

	private String catcheUrl;// 缓存服务地址

	private String calendarUrl;// 工作日历服务地址

	private String factorySvcUrl;//工厂模型服务地址
	
	//---------------mongodb-------------------------------

	private String mongo_ip;// mongoDB的IP

	private int mongo_port;// mongoDB的端口

	private String mongo_database;// mongoDB的库名

	private int connectionsPerHost;// 与目标数据库可以建立的最大链接数

	private int connectTimeout; // 与数据库建立链接的超时时间

	private int maxWaitTime; // 一个线程成功获取到一个可用数据库之前的最大等待时间

	private int threadsAllowedToBlockForConnectionMultiplier;

	private int maxConnectionIdleTime;

	private int maxConnectionLifeTime;

	private int socketTimeout;

	private boolean socketKeepAlive;
	
	//---------------mongodb 集群-------------------------------
	private String mHost;
	
	private Integer mPort;
	
	private String mColliction;
	
	private String mDatabase;
	
	
	//------------------kafka---------------------------------

	private String bootstrapServers;
	
	private String groupId;
	
	private String enableAutoCommit;
	
	private String autoInterval;
	
	private String sessionTimeout;
	
	private String keyDeserializer;
	
	private String valueDeserializer;
	

	public String getFactorySvcUrl() {
		return factorySvcUrl;
	}

	public void setFactorySvcUrl(String factorySvcUrl) {
		this.factorySvcUrl = factorySvcUrl;
	}
	
	public String getCalendarUrl() {
		return calendarUrl;
	}

	public void setCalendarUrl(String calendarUrl) {
		this.calendarUrl = calendarUrl;
	}

	public String getToplogyName() {
		return toplogyName;
	}

	public void setToplogyName(String toplogyName) {
		this.toplogyName = toplogyName;
	}

	public String getDbtype() {
		return dbtype;
	}

	public void setDbtype(String dbtype) {
		this.dbtype = dbtype;
	}

	public String getDbURL() {
		return dbURL;
	}

	public void setDbURL(String dbURL) {
		this.dbURL = dbURL;
	}
	
	public String getDriverClass() {
		return driverClass;
	}

	public void setDriverClass(String driverClass) {
		this.driverClass = driverClass;
	}

	public int getTuple_batch() {
		return tuple_batch;
	}

	public void setTuple_batch(int tuple_batch) {
		this.tuple_batch = tuple_batch;
	}

	public int getWorker_num() {
		return worker_num;
	}

	public void setWorker_num(int worker_num) {
		this.worker_num = worker_num;
	}

	public String getCatcheUrl() {
		return catcheUrl;
	}

	public void setCatcheUrl(String catcheUrl) {
		this.catcheUrl = catcheUrl;
	}

	public int getInitialSize() {
		return initialSize;
	}

	public void setInitialSize(int initialSize) {
		this.initialSize = initialSize;
	}

	public int getMaxActive() {
		return maxActive;
	}

	public void setMaxActive(int maxActive) {
		this.maxActive = maxActive;
	}

	public int getMinIdle() {
		return minIdle;
	}

	public void setMinIdle(int minIdle) {
		this.minIdle = minIdle;
	}

	public int getMaxIdle() {
		return maxIdle;
	}

	public void setMaxIdle(int maxIdle) {
		this.maxIdle = maxIdle;
	}

	public boolean isRemoveAbandoned() {
		return removeAbandoned;
	}

	public void setRemoveAbandoned(boolean removeAbandoned) {
		this.removeAbandoned = removeAbandoned;
	}

	public int getRemoveAbandonedTimeout() {
		return removeAbandonedTimeout;
	}

	public void setRemoveAbandonedTimeout(int removeAbandonedTimeout) {
		this.removeAbandonedTimeout = removeAbandonedTimeout;
	}

	public int getMaxWait() {
		return maxWait;
	}

	public void setMaxWait(int maxWait) {
		this.maxWait = maxWait;
	}

	public String getURL() {

//		return "jdbc:oracle:thin:@" + dbip + ":1521:" + dbName;
		return "jdbc:mysql://10.238.255.10:30001/DEDOC";
	}

	public String getDbdriver() {
		return dbdriver;
	}

	public void setDbdriver(String dbdriver) {
		this.dbdriver = dbdriver;
	}

	public String getDbip() {
		return dbip;
	}

	public void setDbip(String dbip) {
		this.dbip = dbip;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getMongo_database() {
		return mongo_database;
	}

	public void setMongo_database(String mongo_database) {
		this.mongo_database = mongo_database;
	}

	public int getConnectionsPerHost() {
		return connectionsPerHost;
	}

	public void setConnectionsPerHost(int connectionsPerHost) {
		this.connectionsPerHost = connectionsPerHost;
	}

	public int getConnectTimeout() {
		return connectTimeout;
	}

	public void setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	public int getMaxWaitTime() {
		return maxWaitTime;
	}

	public void setMaxWaitTime(int maxWaitTime) {
		this.maxWaitTime = maxWaitTime;
	}

	public int getThreadsAllowedToBlockForConnectionMultiplier() {
		return threadsAllowedToBlockForConnectionMultiplier;
	}

	public void setThreadsAllowedToBlockForConnectionMultiplier(int threadsAllowedToBlockForConnectionMultiplier) {
		this.threadsAllowedToBlockForConnectionMultiplier = threadsAllowedToBlockForConnectionMultiplier;
	}

	public int getMaxConnectionIdleTime() {
		return maxConnectionIdleTime;
	}

	public void setMaxConnectionIdleTime(int maxConnectionIdleTime) {
		this.maxConnectionIdleTime = maxConnectionIdleTime;
	}

	public int getMaxConnectionLifeTime() {
		return maxConnectionLifeTime;
	}

	public void setMaxConnectionLifeTime(int maxConnectionLifeTime) {
		this.maxConnectionLifeTime = maxConnectionLifeTime;
	}

	public int getSocketTimeout() {
		return socketTimeout;
	}

	public void setSocketTimeout(int socketTimeout) {
		this.socketTimeout = socketTimeout;
	}

	public String getBootstrapServers() {
		return bootstrapServers;
	}

	public void setBootstrapServers(String bootstrapServers) {
		this.bootstrapServers = bootstrapServers;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getEnableAutoCommit() {
		return enableAutoCommit;
	}

	public void setEnableAutoCommit(String enableAutoCommit) {
		this.enableAutoCommit = enableAutoCommit;
	}

	public String getSessionTimeout() {
		return sessionTimeout;
	}

	public void setSessionTimeout(String sessionTimeout) {
		this.sessionTimeout = sessionTimeout;
	}

	public String getKeyDeserializer() {
		return keyDeserializer;
	}

	public void setKeyDeserializer(String keyDeserializer) {
		this.keyDeserializer = keyDeserializer;
	}

	public String getValueDeserializer() {
		return valueDeserializer;
	}

	public void setValueDeserializer(String valueDeserializer) {
		this.valueDeserializer = valueDeserializer;
	}

	public String getAutoInterval() {
		return autoInterval;
	}

	public void setAutoInterval(String autoInterval) {
		this.autoInterval = autoInterval;
	}

	public boolean getSocketKeepAlive() {
		return socketKeepAlive;
	}

	public void setSocketKeepAlive(boolean socketKeepAlive) {
		this.socketKeepAlive = socketKeepAlive;
	}

	public String getMongo_ip() {
		return mongo_ip;
	}

	public void setMongo_ip(String mongo_ip) {
		this.mongo_ip = mongo_ip;
	}

	public int getMongo_port() {
		return mongo_port;
	}

	public void setMongo_port(int mongo_port) {
		this.mongo_port = mongo_port;
	}

	public String getmHost() {
		return mHost;
	}

	public void setmHost(String mHost) {
		this.mHost = mHost;
	}

	public Integer getmPort() {
		return mPort;
	}

	public void setmPort(Integer mPort) {
		this.mPort = mPort;
	}

	public String getmColliction() {
		return mColliction;
	}

	public void setmColliction(String mColliction) {
		this.mColliction = mColliction;
	}

	public String getmDatabase() {
		return mDatabase;
	}

	public void setmDatabase(String mDatabase) {
		this.mDatabase = mDatabase;
	}

	 
}
