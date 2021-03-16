package com.pcitc.fms.common;

import java.util.Arrays;
import java.util.List;

import com.pcitc.imp.common.exception.BusiException;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtil {
	
	//服务器IP地址
    private static String ADDR = "127.0.0.1";
    //端口
    private static int PORT = 6379;
    //密码
    private static String AUTH = "123456";
    //连接实例的最大连接数
    private static int MAX_ACTIVE = 1024;
    //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
    private static int MAX_IDLE = 200;
    //等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException
    private static int MAX_WAIT = 10000;
    //连接超时的时间　　
    private static int TIMEOUT = 10000;
    // 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
    private static boolean TEST_ON_BORROW = true;

    private static JedisPool jedisPool = null;
    //数据库模式是16个数据库 0~15 
    public static final int DEFAULT_DATABASE = 1;
    /**
     * 初始化Redis连接池
     */

    static {

        try {
        	
        	if (System.getenv("REDIS_ADDR")!=null) {
        		ADDR = System.getenv("REDIS_ADDR");
        	} else {
        		throw new BusiException("", "redis地址不能为空！");
        	}
        	
        	if (System.getenv("REDIS_PORT")!=null) {
        		PORT = Integer.parseInt(System.getenv("REDIS_PORT"));
        	}

        	if (System.getenv("REDIS_TIMEOUT")!=null) {
        		TIMEOUT = Integer.parseInt(System.getenv("REDIS_TIMEOUT"));
        	}
        	
        	if (System.getenv("REDIS_AUTH")!=null) {
        		AUTH = System.getenv("REDIS_AUTH");
        	} 
        	
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(MAX_ACTIVE);
            config.setMaxIdle(MAX_IDLE);
            config.setMaxWaitMillis(MAX_WAIT);
            config.setTestOnBorrow(TEST_ON_BORROW);
            jedisPool = new JedisPool(config, ADDR, PORT, TIMEOUT,AUTH,DEFAULT_DATABASE);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    /**
     * 获取Jedis实例
     */

    public synchronized static Jedis getJedis() {

        try {

            if (jedisPool != null) {
                Jedis resource = jedisPool.getResource();
                System.out.println("redis--服务正在运行: "+resource.ping());
                return resource;
            } else {
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
    
    public static void main(String[] args) {
    	Jedis jedis = getJedis();
    	jedis.set("rents", "11");
    	jedis.flushAll();
//    	StringBuilder sb1 = new StringBuilder();
//    	StringBuilder sb2 = new StringBuilder();
//    	StringBuilder sb3 = new StringBuilder();
//    	String rents = jedis.get("rents");
//    	List<String> rentList = Arrays.asList(rents.split(","));
//    	for (String str : rentList) {
//    		sb1.append(str+",");
//    		String bizStr = jedis.get(str);
//    		List<String> bizList = Arrays.asList(bizStr.split(","));
//    		for (String str1 : bizList) {
//    			sb2.append(str1+",");
//    			String orgStr = jedis.get(str+":"+str1);
//    			List<String> orgList = Arrays.asList(orgStr.split(","));
//    			for (String str2 : orgList) {
//    				sb3.append(str2+",");
//    			}
//    		}
//    	}
//    	System.out.println("租户集合：" + sb1.toString());
//    	System.out.println("业务域集合：" + sb2.toString());
//    	System.out.println("组织机构集合：" + sb3.toString());
    	
	}
    
    /***
     * 
     * 释放资源
     */
    
    @SuppressWarnings("deprecation")
	public static void returnResource(final Jedis jedis) {
            if(jedis != null) {
                jedisPool.returnResource(jedis);
            }
        
    }

}
