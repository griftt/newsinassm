package com.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
//暂时用不上
public class RedisDao {
	
	
	static 	private JedisPool jedisPool;
	   public JedisPool getJedisPool() {
			return jedisPool;
		}
		public void setJedisPool(JedisPool jedisPool) {
			this.jedisPool = jedisPool;
		}
		
		//获取jedis实例
		public static Jedis getJedis(){
			if(jedisPool!=null){
				return jedisPool.getResource();
			}
			return null;
			
		}
		/**
	     * 释放连接池
	     * @param jedisPool
	     * @param jedis
	     */
	    public static void returnResource(JedisPool jedisPool, Jedis jedis) {
	         if (jedis != null) {
	            jedisPool.returnResource(jedis);
	            
	         	}
	    }
	    public static void main(String[] args) {
	    	Jedis jedis = new Jedis("localhost",6379);
	    	System.out.println(jedis);
	    	jedis.auth("admin");
	    	 System.out.println("Connection to server sucessfully");
	         //查看服务是否运行
	         System.out.println("Server is running: "+jedis.ping());
	    	}
}