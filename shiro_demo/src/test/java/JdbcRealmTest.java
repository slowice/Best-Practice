import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

@Slf4j
public class JdbcRealmTest {
    DruidDataSource dataSource = new DruidDataSource();

    {
        dataSource.setUrl("jdbc:mysql://localhost:3306/best_practice?characterEncoding=utf8&useSSL=false");
        dataSource.setUsername("root");
        dataSource.setPassword("dy82341733");
    }

    @Test
    public void authTest(){
//        JdbcRealm jdbcRealm = new JdbcRealm();
//        jdbcRealm.setDataSource(dataSource);
//        //设置权限开关为true才能查询权限
//        jdbcRealm.setPermissionsLookupEnabled(true);
//
//        //自定义认证查询语句
//        String sql = "select password from xb_user where user_name = ?";
//        jdbcRealm.setAuthenticationQuery(sql);
//
//        //自定义角色查询sql
//        String roleSql = "select role_name from xb_user_roles where user_name = ?";
//        jdbcRealm.setUserRolesQuery(roleSql);
//
//        //自定义权限查询sql
//        String permissonSql = "select permission from xb_role_permissions where role_name = ?";
//        jdbcRealm.setPermissionsQuery(permissonSql);
//
//        //获取安全管理器
//        DefaultSecurityManager manager = new DefaultSecurityManager();
//        manager.setRealm(jdbcRealm);
//        SecurityUtils.setSecurityManager(manager);
//
//
//        Subject subject = SecurityUtils.getSubject();
//        UsernamePasswordToken token = new UsernamePasswordToken("abg","111");
//        subject.login(token);
//
//        log.info("isAuthenticated : " + subject.isAuthenticated());
//
//        subject.checkRole("admin");
//        subject.checkPermission("user:update");

    }
}
