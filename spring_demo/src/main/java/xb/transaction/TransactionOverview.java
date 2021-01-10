package xb.transaction;

/**
 * 1.Spring事务用来干什么的？
 * 2.Spring事务和数据库事务有什么关系？
 * 3.什么是全局事务，什么是本地事务，有什么区别？Spring事务和它们有什么关系？
 *      全局事务：可以让你同时处理多个事务资源（一般指关系型数据库和消息队列）。应用通过JTA来管理全局事务，JTA需要运行在服务器环境内
 *      又需要通过JNDI来注册。所以以前是用EJB来管理事务，虽然免去了JNDI注册，但是还是和JTA和应用服务器绑定在一起。
 *      本地事务：是针对资源的，比如jdbc链接。本地事务易于使用，但是无法在多个事务资源中使用。比如一个jdbc事务无法运行在一个全局JTA事务下。
 * spring 为我们提供了操作简单的编程式和声明式事务管理。
 * 声明式事务利用AOP来实现
 *
 * ********1.1Spring事务优点
 * Spring结合了传统全局事务和本地事务的优点，提供了声明式和编程式两种编程模型，好处是同一套代码可以工作在任何事务环境下。

 * ********1.2理解Spring事务抽象
 *      Spring事务抽象的核心点就是：事务策略。
 *      事务策略由PlatformTransactionManager，即事务管理器接口定义。PlatformTransactionManager接口的实现依赖于事务工作的环境，
 *      通常有jta，jdbc，Hirbernate三种。其中jdbc类型的用的最多。Spring通过事务管理器替我们封装了详细的事务操作，达到一套代码可以运行在各种事务环境中。
 *      PlatformTransactionManager接口的getTransaction方法根据其输入参数TransactionDefinition，返回一个TransactionStatus对象，
 *      该对象代表一个新事务或者一个已存在的事务。TransactionDefinition包含事务传播，隔离级别，超时时间，是否是只读事务
 *      PlatformTransactionManager的注册：
 *  *      1.定义数据源
 *  *          <bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource" destroy-method="close">
 *  *              <property name="driverClassName" value="${jdbc.driverClassName}" />
 *  *              <property name="url" value="${jdbc.url}" />
 *  *              <property name="username" value="${jdbc.username}" />
 *  *              <property name="password" value="${jdbc.password}" />
 *  *          </bean>
 *  *      2.注册PlatformTransactionManager并且添加dataSource的依赖
 *  *      <bean id="txManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
 *  *          <property name="dataSource" ref="dataSource"/>
 *  *      </bean>
 *
 * ********1.3利用事务来同步资源：没看懂
 * ********1.4声明式事务管理：
 * 1.利用AOP实现，代码入侵少，通俗易懂。
 * 2.同一套代码可以工作在JTA环境下（全局事务），也可以工作在jdbc（本地环境），jpa或者hibernate环境下。
 * 3.提供声明式回滚规则
 * 4.可以自定义事务行为
 * 1.4.3.异常回滚：默认情况下只回滚runtime, unchecked exceptions，Error.Checked exceptions不会回滚。可以添加rollback标签来指定哪些异常需要回滚，也可以是checked异常。同理还有不回滚的标签
 * 1.4.4可以配置多个pointcut，多个advice，然后用多个advisor将前者绑定起来，从而实现不同的service对应不同的回滚逻辑
 * 1.4.5<tx:advice/>标签的属性有：
 * propagation 默认REQUIRED
 * isolation 隔离级别，默认DEFAULT。只有在传播特性为REQUIRED or REQUIRES_NEW有效
 * timeout 超时自动回滚。默认-1，即不会超时。只有在传播特性为REQUIRED or REQUIRES_NEW有效
 * read-only 默认false
 * rollback-for，no-rollback-for。回滚和不回滚异常。以逗号分隔(自定义异常用包名.类名)
 * 1.4.6.使用@Transactional注解来是一个类运行在事务环境。需要在xml中配置开启事务注解。或者添加@EnableTransactionManagement。另外方法
 * 一定要是public的原因和代理模式有关。在代理模式当中，只有外部调用该方法才会被拦截（事务生效）。方法内调用其它方法时不会生效的。并且类一定要是完全初始化的。
 * 如果希望在方法内调用其它方法也可以走事务，可以不用代理模式，而用apsectJ模式（在开启事务注解即annotation-driven标签中可以设置）
 *
 * 四种隔离级别及其造成的影响
 * 脏读，不可重复读，幻读
 * Read_Uncommitted:脏读 不可重复读 幻读
 * Read_Committed:不可重复读 幻读
 * Repeatable_Read:幻读
 * Serializable
 * 3种传播行为
 * Required Required_New Required_Nested
 * 在外层不开启事务的情况，内层各方法各事务独立运行，外层异常不影响内层回滚
 * 在外层开启事务的情况下，
 * Required修饰的事务都会加入到外层事务中，共享外层事务，外层回滚，Required也回滚，某一内层回滚，全部跟着回滚。
 * Required_New修饰的事务，内部事务独立运行，与外部事务不相干（内外事务独立运行），且内部事务之间也不相干
 * Required_Nested修饰的事务，作为外层事务的子事务，内外可以互相影响，单内部之间不会相互影响。
 */
public class TransactionOverview {
}
