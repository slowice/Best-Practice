package xb.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

/**
 * 消息接收器
 */
public class JedisReceiveMsg extends Thread {
    @Override
    public void run() {
        System.out.println("...频道一....");
        Jedis jedis = new RedisPoolHelper("127.0.0.1",6379,"test123").getRedisClient();
        JedisPubSub jedisPubSub=new JedisPubSub() {
            @Override
            public void onMessage(String channel, String message) {
                // 执行订阅消息
                super.onMessage(channel, message);
                //终止订阅
                //super.unsubscribe();
                System.out.println(message);
            }
        };
        jedis.subscribe(jedisPubSub,"channel1");
    }
}
