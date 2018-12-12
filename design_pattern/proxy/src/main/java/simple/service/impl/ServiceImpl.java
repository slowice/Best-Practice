package simple.service.impl;

import simple.service.Service;

public class ServiceImpl implements Service {
    @Override
    public String  request(String name) {
        System.out.println(" this is real serviceImpl");
        return "hello "+ name;
    }
}
