package xb.redis;


import redis.clients.jedis.JedisPoolConfig;

public final class RedisPoolConfig {
    /** 建立连接池配置参数 */
    private static JedisPoolConfig poolConfig = null;
    /**
     * @方法描述: 连接池基本配置
     *
     */
    public synchronized static JedisPoolConfig initPoolConfig() {
        if (poolConfig == null) {
            poolConfig = new JedisPoolConfig();
            // 设置最大连接数，默认值为8.如果赋值为-1，则表示不限制；
            poolConfig.setMaxTotal(1024);
            // 最大空闲连接数
            poolConfig.setMaxIdle(10);
            // 最小空闲连接数
            poolConfig.setMinIdle(10);
            // 获取Jedis连接的最大等待时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException
            poolConfig.setMaxWaitMillis(10000);
            // 每次释放连接的最大数目
            poolConfig.setNumTestsPerEvictionRun(1024);
            // 释放连接的扫描间隔（毫秒）,如果为负数,则不运行逐出线程, 默认-1
            poolConfig.setTimeBetweenEvictionRunsMillis(30000);
            // 连接最小空闲时间
            poolConfig.setMinEvictableIdleTimeMillis(1800000);
            // 连接空闲多久后释放, 当空闲时间&gt;该值 且 空闲连接&gt;最大空闲连接数 时直接释放
            poolConfig.setSoftMinEvictableIdleTimeMillis(10000);
            // 在获取Jedis连接时，自动检验连接是否可用
            poolConfig.setTestOnBorrow(true);
            // 在将连接放回池中前，自动检验连接是否有效
            poolConfig.setTestOnReturn(true);
            // 自动测试池中的空闲连接是否都是可用连接
            poolConfig.setTestWhileIdle(true);
            // 连接耗尽时是否阻塞, false报异常,ture阻塞直到超时, 默认true
            poolConfig.setBlockWhenExhausted(false);
            // 是否启用pool的jmx管理功能, 默认true
            poolConfig.setJmxEnabled(true);
            // 是否启用后进先出, 默认true
            poolConfig.setLifo(true);
            // 每次逐出检查时 逐出的最大数目 如果为负数就是 : 1/abs(n), 默认3
            poolConfig.setNumTestsPerEvictionRun(3);
        }

        return poolConfig;
    }
}