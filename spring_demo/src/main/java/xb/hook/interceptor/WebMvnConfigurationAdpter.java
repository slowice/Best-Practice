package xb.hook.interceptor;


import bean.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Configuration
@EnableWebMvc
public class WebMvnConfigurationAdpter implements WebMvcConfigurer {

    //register interceptor
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new ParamIntercepter()).addPathPatterns("/test");
    }

    public static void main(String[] args) {
        List<User> list = new ArrayList();
        for(int i=0;i<50000;i++){
            User u = new User();
            u.setName("gggg"+i);
            u.setAge(i);
            u.setMobilePhone("111111111112"+i);
            list.add(u);
        }
        Set<User> s = new HashSet();
        long long1 = System.currentTimeMillis();
        for(int i=0;i<list.size();i++){
            s.add(list.get(i));
        }
        long long2 = System.currentTimeMillis();

        long long3 = System.currentTimeMillis();
        Set<String> s2 = new HashSet<>();
        s2= list.parallelStream().map(User::getName).collect(Collectors.toSet());
        long long4 = System.currentTimeMillis();

        System.out.println("cost1 = " + (long2-long1));
        System.out.println("cost2 = " + (long4-long3));
    }
}
