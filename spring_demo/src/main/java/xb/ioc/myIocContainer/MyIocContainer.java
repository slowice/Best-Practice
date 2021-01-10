package xb.ioc.myIocContainer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *  容器。
 *  什么是IOC容器？
 *      ApplicationContext接口就代表IOC容器
 *  IOC容器是干嘛的？：
 *      实例化，配置，组装beans
 *  配置元数据
 *      配置元数据有三种方法：XML，代码或者注解。
 *  实例化容器
 *      利用ApplicaitonContext接口读取元数据来完成容器的实例化
 *      ApplicationContext context = new ClassPathXmlApplicationContext("services.xml", "daos.xml");
 *  使用容器读取bean
 *      T getBean(String name, Class<T> requiredType)
 *
 *
 *
 *
 *  *  如何注册bean？   通过读取元数据
 *  *  如何提取bean？：T getBean(String name, Class<T> requiredType)
 *  *  如何组合多个元数据配置？比如有三个xml分别对应业务1，业务2，业务3，如何整合到一起？
 *  *  可以ApplicationContext context = new ClassPathXmlApplicationContext("services.xml", "daos.xml");
 *  *  也可以
 *  *     <beans>
 *  *     <import resource="services.xml"/>
 *  *     <import resource="resources/messageSource.xml"/>
 *  *     <import resource="/resources/themeSource.xml"/>
 *  *
 *  *     <bean id="bean1" class="..."/>
 *  *     <bean id="bean2" class="..."/>
 *  *     </beans>
 */
@Slf4j
public class MyIocContainer {
    public static void main(String[] args) {
        ApplicationContext context1 = new ClassPathXmlApplicationContext("ioc/myContainer/Foo1.xml");
        log.info(context1.getApplicationName());
        //ApplicationContext context2 = new ClassPathXmlApplicationContext("myContainer/Foo2.xml");
        //log.info(context2.getApplicationName());
        ApplicationContext combinedContext1 = new ClassPathXmlApplicationContext("ioc/myContainer/Combined.xml");
        Foo1 foo1 = (Foo1)combinedContext1.getBean("foo1");
        Foo2 foo2 = (Foo2)combinedContext1.getBean("foo2");
        log.info(foo1.getName());
        log.info(foo2.getFoo1().getName());

        ApplicationContext combinedContext2 = new ClassPathXmlApplicationContext("ioc/myContainer/Foo1.xml", "ioc/myContainer/Foo2.xml");
        Foo1 foo11 = (Foo1)combinedContext2.getBean("foo1");
        Foo2 foo22 = (Foo2)combinedContext2.getBean("foo2");
        log.info(foo11.getName());
        log.info(foo22.getFoo1().getName());

    }
}
