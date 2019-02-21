package xb.front_end_study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Home {

    @RequestMapping("/home")
    public ModelAndView home(){
        ModelAndView home = new ModelAndView();
        home.setViewName("home");
        return home;
    }
}
