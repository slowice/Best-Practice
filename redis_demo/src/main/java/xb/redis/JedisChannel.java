package xb.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;


public class JedisChannel extends JedisPubSub {
    private static boolean ifOpen=false;

    static {
        if(ifOpen==false){
            ifOpen=true;
            System.out.println("初始化消息接收器");
            JedisReceiveMsg jedisReceiveMsg=new JedisReceiveMsg();
            jedisReceiveMsg.start();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //发布消息
    public  void sendMessage(String message){
        System.out.println("sendMessage.."+message);
        Jedis jedis = new RedisPoolHelper("127.0.0.1",6379,"test123").getRedisClient();
        jedis.publish("channel1", message);
    }
}
