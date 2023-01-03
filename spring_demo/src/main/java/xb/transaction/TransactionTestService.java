package xb.transaction;

import xb.entity.User;

/**
 * 事务的隔离界别
 */
public interface TransactionTestService {
    void updateUserNameWithException();
    void updateUserName() throws InterruptedException;
    //基于xml配置事务
    void testRollBackForException(User user);
    //基于注解配置事务
    void testRollBackForExceptionWithAnnotation(User user);
    //region 测试事务的四种隔离级别
    /*
        脏读：当一个事务修改了一行数据，但是还没提交，而此时另一个事务恰巧读取了这个数据，而前事务回滚了，后事务就读取了错误的数据，就是脏读
        不可重复读：在一个事务期间内，多次重复读取一条数据，但是忽然另一条事务改变了此条数据，前事务读的数据就变了。就是不可重复读
        幻读：在一个事务期间内，另一条事务由于新增或者删除数据，而影响到了前事务的结果。就是幻读。比如A事务对全表进行删除操作，还未提交时，
        B事务新增了一条数据。A提交后发现还有一条数据未删。
        不可重复读强调的是修改影响前事务，幻读强调的是增删影响前事务。
     */
    //读未提交。允许读取未提交的数据。会出现脏读，不可重复读和幻读。
    void testReadUncommitted() throws InterruptedException;
    void getUserNameUncommitted();

    //读已提交。多数引擎的默认隔离级别。会出现不可重复读和幻读
    void testReadCommitted() throws InterruptedException;
    void getUserNameCommitted();

    //可重复读。mysql的默认隔离级别(mysql innodb的默认隔离级别，因为该引擎不存在幻读问题)。会出现幻读
    void testRepeatableRead() throws InterruptedException;
    void testUnRepeatableRead() throws InterruptedException;
    //序列化。所有操作串行化执行。不存在上述问题。
    void testSerializable();
    //endregion

    //region 测试事务的三种传播特性
    void testRequired();
    void testRequiredNew();
    void testNested();
    //endregion
}
