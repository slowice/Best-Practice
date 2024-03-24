package xb.transaction;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import xb.ApplicationTests;
import xb.persistence.transaction.UserService;

public class UserServiceImplTest extends ApplicationTests {
    @Autowired
    UserService service;

    @Test
    public void updateUser() throws Exception {
        //service.proganationTest(new ArrayList<>());
    }
}