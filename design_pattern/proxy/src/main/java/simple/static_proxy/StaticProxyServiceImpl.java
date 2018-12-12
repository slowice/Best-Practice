package simple.static_proxy;

import simple.service.Service;
import simple.service.impl.ServiceImpl;

public class StaticProxyServiceImpl implements Service {
    private ServiceImpl service;

    private StaticProxyServiceImpl(){}

    public StaticProxyServiceImpl(ServiceImpl service){
        this.service = service;
    }

    @Override
    public String request(String name) {
        System.out.println(" this is Service of Static Proxy");
        return service.request(name);

    }
}
