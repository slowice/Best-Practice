package xb.exception_handler_demo.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class MyHandlerInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp,
                             Object handler)throws Exception{
        System.out.println("FirstInterceptor.....preHandle");
        return true;
    }

    public void postHandle(HttpServletRequest req, HttpServletResponse resp,
                           Object handler, ModelAndView modelAndView)throws Exception{
        System.out.println("FirstInterceptor.....postHandle");
    }

    public void afterCompletion(HttpServletRequest req,HttpServletResponse resp,
                                Object handler,Exception ex) throws Exception{
        System.out.println("FirstInterceptor.....afterCompletion");
    }
}
