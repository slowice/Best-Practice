package xb.service;

import org.springframework.stereotype.Service;
import xb.iservice.XbService;

@Service
public class XbServiceImpl implements XbService {
    @Override
    public String doJenkins(String code) {
        code = "simple";

        return null;
    }
}
