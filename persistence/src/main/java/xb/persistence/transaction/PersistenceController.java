package xb.persistence.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xb.common.entity.User;

@Controller
public class PersistenceController {
    @Autowired
    UserService service;

    @RequestMapping("/transactionTest")
    @ResponseBody
    public String test() throws Exception {
        User u = new User();
        u.setIdUser("04106a036fad497f97b1ec14ef05c5fd");
        u.setName("000");
       // service.updateUser(u);
        return "";
    }
}
