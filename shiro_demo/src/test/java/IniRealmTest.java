import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class IniRealmTest {
    IniRealm iniRealm = new IniRealm("classpath:user.ini");

    @Test
    public void authTest(){

        //获取安全管理器
        DefaultSecurityManager manager = new DefaultSecurityManager();
        manager.setRealm(iniRealm);
        SecurityUtils.setSecurityManager(manager);
        //获得主体
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("abg","123456");
        //主体提交认证请求
        subject.login(token);

        System.out.println("isAuthenticated : " + subject.isAuthenticated());
        //授权
        subject.checkRole("admin");
        subject.checkPermission("user:delete");
    }
}
