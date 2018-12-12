package simple.dynamic_proxy.jdkProxy;

import simple.service.impl.ServiceImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class JdkProxyServiceImpl implements InvocationHandler {
    private ServiceImpl service;

    public JdkProxyServiceImpl(ServiceImpl service){
        this.service = service;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(" this is JDK-PROXY of Service");
        Object reuslt = method.invoke(service,args);
        return reuslt;
    }
}
