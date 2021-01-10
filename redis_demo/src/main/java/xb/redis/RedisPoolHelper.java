package xb.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Protocol;

import java.util.concurrent.locks.ReentrantLock;

public class RedisPoolHelper {
    private static ReentrantLock lockPool = new ReentrantLock();

    private String host;
    private int port;
    private String password;

    public RedisPoolHelper(String host, int port) {
        super();
        this.host = host;
        this.port = port;
        getJedisPoolClient();
    }

    public RedisPoolHelper(String host, int port, String password) {
        super();
        this.host = host;
        this.port = port;
        this.password = password;
        getJedisPoolClient();
    }

    /**
     * 获取单机jedis连接池
     */
    private synchronized JedisPool getJedisPoolClient() {
        // 断言 ，当前锁是否已经锁住，如果锁住了，就啥也不干，没锁的话就执行下面步骤
        assert !lockPool.isHeldByCurrentThread();

        lockPool.lock();

        try {
            return new JedisPool(RedisPoolConfig.initPoolConfig(), host, port, Protocol.DEFAULT_TIMEOUT, password);
        } catch (Exception exp) {
            // 创建Redis Pool失败
            exp.printStackTrace();
        } finally {
            lockPool.unlock();
        }
        return null;
    }

    /**
     * @方法描述: 从jedis连接池中获取获取jedis对象
     *
     * @return
     */
    public synchronized Jedis getRedisClient() {
        return getJedisPoolClient().getResource();
    }

    /**
     * @方法描述: 回收jedis(放到finally中)
     *
     * @param jedisClient
     */
    public void closeRedisClient(Jedis jedisClient) {
        if (jedisClient != null) {
            jedisClient.close();
        }
    }
}
