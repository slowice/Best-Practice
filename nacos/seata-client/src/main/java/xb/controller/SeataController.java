package xb.controller;

import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

public class SeataController {
    @GetMapping(value = "/testCommit")
    @GlobalTransactional
    public Object testCommit(@RequestParam(name = "id",defaultValue = "1") Integer id,
                             @RequestParam(name = "sum", defaultValue = "1") Integer sum) {
        // Boolean ok = productService.reduceStock(id, sum);
        Boolean ok = true;
        if (ok) {
            LocalDateTime now = LocalDateTime.now();
//            Orders orders = new Orders();
//            orders.setCreateTime(now);
//            orders.setProductId(id);
//            orders.setReplaceTime(now);
//            orders.setSum(sum);
//            orderService.save(orders);
            return "ok";
        } else {
            return "fail";
        }
    }
}
