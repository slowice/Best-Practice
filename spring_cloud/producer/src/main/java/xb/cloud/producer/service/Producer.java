package xb.cloud.producer.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("producer")
public interface Producer {
    @RequestMapping(method = RequestMethod.GET, value = "/produce")
    String produce();
}
