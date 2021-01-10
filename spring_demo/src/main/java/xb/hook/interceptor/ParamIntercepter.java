package xb.hook.interceptor;

import bean.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;

@Slf4j
public class ParamIntercepter implements HandlerInterceptor {
    private ObjectMapper mapper = new ObjectMapper();

    //Object handler为controller类
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //通过此方法可以获得requestbody传的json
        BufferedReader br = request.getReader();
        String str, wholeStr = "";
        while((str = br.readLine()) != null){
            wholeStr += str;
        }

        // requestbody convert to Object
        try {
            User s = mapper.readValue(wholeStr,User.class);
            log.info(s.getIdUser());
        }catch (Exception e){

        }

        log.info("12312312312312");
        return true;
    }

    //前端信息已经返回
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           @Nullable ModelAndView modelAndView) throws Exception {
        log.info("postHandle");
    }
}
