package simple.dynamic_proxy.cglibProxy;

import org.springframework.cglib.proxy.Enhancer;
import simple.service.Service;
import simple.service.impl.ServiceImpl;

public class Test {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(ServiceImpl.class);
        enhancer.setCallback(new CglibProxyServiceImpl());
        Service service = (Service) enhancer.create();
        service.request("abg");
    }
}
