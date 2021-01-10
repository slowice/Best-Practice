package xb.data_output;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class Demo {
    @RequestMapping("/responseTest")
    public void reponseTest(HttpServletResponse response) throws IOException {
        //设置字符编码与浏览器的解码
        response.setCharacterEncoding("utf-8");
        response.setHeader("content-type", "text/html;charset=UTF-8");
        response.getWriter().write("对不起，我是警察");
    }
}
