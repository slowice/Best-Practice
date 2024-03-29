package xb.basis.abstracClassAndInterface;

/**
 * 什么是接口的幂等性?
 *      接口的幂等性就是用户对于同一操作发起的一次请求或者多次请求的结果是一致的
 * 如何测试接口的幂等性？
 * 如何保证接口的幂等性？
 *      查询和删除是天然的幂等性，只要查询条件一致，返回的数据一定是一致的；删除条件一致，数据删除的结果也是一致的。需要特别注意的是新增和修改
 */
public class IdempotentInterface {
}
