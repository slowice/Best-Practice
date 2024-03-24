package xb.spring.aop;

/**
 * 5 SpringAOP
 * AOP即面向切面编程，是对面向对象编程的一种补充。OOP允许纵向定义事务，比如学校类，教师类等。但是不能横向定义。比如建学校，招老师都要告诉教育局。
 * 在这种需要横跨多个无关类编程时，就可以用到AOP。
 * 面向对象编程的核心是类（class），面向切面编程的核心则是切面（aspect）。
 * 在Spring框架中AOP主要服务于声明式事务管理。
 * 业务中主要用到AOP的地方有权限认证，日志和事务管理。
 *
 * 5.1 SpringAOP Concepts
 * AOP的四个关键点：
 *  aspect切面:横跨多个类的某种逻辑或行为
 *  join point连接点：程序执行的某一个点，比如方法的执行，异常的处理。SpringAop中，连接点只有方法的执行
 *  advice：切面在某一确定连接点所做出的的行为。before，after return，after throwing，after finally,around
 *  pointcut：一种表达式，用来点位连接点
 *  (1)声明切面Aspect：当一个类被标记为切面，则不会被代理。即切面不可发现切面
 *  (2)声明切点pointcut
 *
 * 5.2 SpringAop capablities and gols
 * Join points目前只支持方法，但是基本够用了。想用字段的用AspectJ
 * 5.3 AOP Proxies
 * SpringAOP默认使用标准jdk动态代理实现。允许代理接口。可以强制使用cglib代理，来代理没有实现接口的类，但是这样不好，一般业务类都会实现业务接口。
 *
 * 5.4 @AspectJ Surpport
 * 5.5 Schema-based AOP Surpport
 */
public class AopOverview {
}
