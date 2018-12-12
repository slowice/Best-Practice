package simple.dynamic_proxy.jdkProxy;

import simple.service.Service;
import simple.service.impl.ServiceImpl;

import java.lang.reflect.Proxy;

public class jdkProxyTest {
    public static void main(String[] args) {
        Service service = (Service) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),new Class[]{Service.class},new JdkProxyServiceImpl(new ServiceImpl()));
        String result = service.request("abg");
        System.out.println(result);
    }
}
