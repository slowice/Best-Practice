package xb.aop;

import org.springframework.stereotype.Service;

@Service
public class AnnotationBasedAOPServiceImpl implements AnnotationBasedAOPService {
    @Override
    public String doOK() {
        System.out.println("this is dook");
        return "OK";
    }
}
