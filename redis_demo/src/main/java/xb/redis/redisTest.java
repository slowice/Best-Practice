package xb.redis;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.concurrent.TimeUnit;

@Slf4j
public class redisTest {
    public static void baseRedisConnectionTest(){
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(8);
        config.setMaxTotal(18);
        JedisPool pool = new JedisPool(config, "127.0.0.1", 6379, 2000, "test123");
        Jedis jedis = pool.getResource();
        jedis.set("key","HAHA");
        String value = jedis.get("key");
        System.out.println("the key is +" +value);
        jedis.close();
        pool.close();
    }
    public static void main(String[] args) throws InterruptedException {
        //channelTest();
        commonCmdTest();
    }
    //测试redis常用命令
    public static void commonCmdTest() throws InterruptedException {
        Jedis jedis = new RedisPoolHelper("127.0.0.1",6379,"test123").getRedisClient();
        //setex 设置超时时间
        jedis.setex("name",5,"阿斌哥");
        log.info("get key of 'name' " + jedis.get("name"));
        TimeUnit.SECONDS.sleep(6);
        log.info("wait for 6s get key of 'name' " + jedis.get("name"));
    }
    public static void channelTest(){
        JedisChannel jedisChannel=new JedisChannel();
        jedisChannel.sendMessage("hello hello");
    }
}
