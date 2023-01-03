package xb.transaction;

import xb.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import xb.mybatis.dao.UserMapper;

@Service
public class UserService3Impl implements UserService3 {
    @Autowired
    UserMapper mapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateTransactional(User user) throws Exception {
        mapper.updateByPrimaryKey(user);
        //int i=1/0;
    }
}
