/**
 * 
 */
/**
 * @author chen cheng
 *
 */
package whut.utils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisUtil{
    //private static final long serialVersionUID = -1149678082569464779L;

    //Redis服务器IP
    private static String addr;
    //Redis的端口号
    private static int port;
    //访问密码
    private static String auth;
    //可用连接实例的最大数目，默认值为8；
    //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
    private static int maxActive;
    //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
    private static int maxIdle;
    //等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
    private static int maxWait;
    //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
    private static boolean testOnBorrow;
    
    public static Jedis jedis;//非切片额客户端连接
    public static JedisPool jedisPool;//非切片连接池
    
	/*
	     * 初始化列表
	     * 静态代码初始化池配置
     */
    static {
        try{
            Properties props = new Properties();
            InputStream in = JedisUtil.class.getResourceAsStream("/redis.properties");
            props.load(in);
            
            addr = String.valueOf(props.getProperty("redis.addr"));
            port = Integer.valueOf(props.getProperty("redis.port"));
            auth = String.valueOf(props.getProperty("redis.auth"));;
            maxActive = Integer.valueOf(props.getProperty("redis.maxActive"));
            maxIdle = Integer.valueOf(props.getProperty("redis.maxIdle"));
            maxWait = Integer.valueOf(props.getProperty("redis.maxWait"));
            testOnBorrow = Boolean.valueOf(props.getProperty("redis.testOnBorrow"));
            initialPool(); 

        }catch (Exception e) {
            //logger.info("redis连接池异常",e);
        }
    }
    
    /**
     * 初始化非切片池
     */
    private static void initialPool() 
    { 
        // 池基本配置 
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(maxActive); 
        config.setMaxIdle(maxIdle); 
        config.setMaxWaitMillis(maxWait); 
        config.setTestOnBorrow(testOnBorrow);

        //config.setTestOnReturn(Boolean.valueOf(props.getProperty("jedis.pool.testOnReturn")));
        jedisPool = new JedisPool(config, addr, port);
    }
    

    public static Jedis getJedis() {
        try {
             jedis = jedisPool.getResource();
             System.out.println(jedisPool+jedis.toString()+"  "+jedisPool.getNumActive());
        } catch (Exception e) {
            System.out.println("连接jedisPool失败!"+e);
        }
        return jedis;
    }

    public static void closeJedis(Jedis jedis) {
    	if(jedis!=null) {
    		jedis.close();
    	}
    	
    }
    
    //jedis调用示例（前台存储用户登录信息使用/后台暂不使用）
//	private static void jedisinit() {
//		Jedis jedis = JedisUtil.getJedis();
//		jedis.append("id", "12");
//		System.out.println(jedis.get("id"));
//	}
    
}