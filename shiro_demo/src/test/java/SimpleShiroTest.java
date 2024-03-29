import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@Slf4j
public class SimpleShiroTest {
    SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();

    @BeforeEach
    public void addUser(){
        simpleAccountRealm.addAccount("abg","123456");
    }

    @Test
    public void authTest(){
        /*
        1.初始化realm
        2.获得安全管理器Security
        3.获得主体
        4.主体提交认证请求
         */
        DefaultSecurityManager manager = new DefaultSecurityManager();
        manager.setRealm(simpleAccountRealm);

        SecurityUtils.setSecurityManager(manager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("abg","123456");
        subject.login(token);

        log.info("isAuthenticated : " + subject.isAuthenticated());
    }
}
