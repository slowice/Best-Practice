package xb.persistence.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import xb.common.entity.User;
import xb.persistence.mybatis.dao.UserMapper;

@Service
public class UserService2Impl implements UserService2{
    @Autowired
    UserMapper mapper;

    @Transactional(propagation = Propagation.REQUIRED)
    public void updateTransactional(User user) throws Exception{
        mapper.updateByPrimaryKey(user);
        try{
            int i=1/0;
        } catch (Exception e){
            throw e;
        }

    }
}
