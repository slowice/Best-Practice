package xb.ioc;

/**
 * 1.什么是IOC？
 * It is a process whereby objects define their dependencies (that is, the other objects they work with) only through
 * constructor arguments, arguments to a factory method, or properties that are set on the object instance after it is
 * constructed or returned from a factory method. The container then injects those dependencies when it creates the bean.
 * This process is fundamentally the inverse (hence the name, Inversion of Control) of the bean itself controlling the
 * instantiation or location of its dependencies by using direct construction of classes or a mechanism such as the
 * Service Locator pattern.
 *
 * 2.IOC有什么好处？
 * 3.Spring怎么注册bean？怎么读取BEAN？
 * 4.Bean中如何注册依赖？
 *
 * bean，context两个包很重要，BeanFactory接口提供了配置框架和基础功能，负责管理对象类型
 * ApplicationContext是BeanFactory的子接口，作为IOC的核心接口，添加了：集成AOP，国际化，事件发布，应用层上下文管理（比如WebApplication）
 * 的功能，负责实例化，配置和组装bean。容器通过读取bean的元数据来实例化配置组装bean。配置元数据有三种方法：XML，代码或者注解。
 * ApplicationContext接口有两个常用实现：ClassPahtXmlApplicaitonContext和FileSystemXmlApplicationContext
 *

 *
 * 二：bean
 *  什么是bean？
 *
 *
 */
public class IocOverview {
}
