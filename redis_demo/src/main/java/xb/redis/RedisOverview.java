package xb.redis;

import org.springframework.web.servlet.DispatcherServlet;

/**
 * 1.redis支持哪些数据类型
 * String:二进制安全，可以包含任何数据类型
 * Hash:键值对集合，适合存储对象
 * List：字符串列表，按照插入顺序排序，可以在首尾部分插入
 * Set:String类型的无序集合，通过hash表实现，增删查的时间复杂度都是O(1)
 * ZSet：通过关联的double数据来进行排序，不允许重复数据
 *
 * 2、Redis是如何进行持久化的？
 * (1)redis database（RBD）:通过rdbSave和rdbLoad两个函数来操作rdb文件。传输速度快。缺点是无法进行实时备份。
 * (2)append-only file(AOF):支持秒级持久化，但是对性能影响较大。
 *
 * 3.Redis有6中数据淘汰策略
 * noeviction：不删除，达到最大内存时返回错误信息
 * allkeys-lru：优先删除最近最少使用的key
 * volatile-lru：优先删除最近最少使用的key，针对设置了expire的key
 * allkeys-random:随机删除
 * volatile-random：随机删除，针对设置了过期时间的
 * volatile-ttl:优先删除过期时间短的
 *
 * 4.Redis速度快的原因：
 *  (1)基于内存 (2)数据结构都是专门设计的  (3)采用单线程,不存在上下文切换的消耗，也不存在锁的消耗 (4)多路IO复用。
 */
public class RedisOverview {
}
