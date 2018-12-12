package simple.static_proxy;

import simple.service.Service;
import simple.service.impl.ServiceImpl;

public class Test {
    public static void main(String[] args) {
        Service service = new StaticProxyServiceImpl(new ServiceImpl());
        service.request("abg");
    }

}
