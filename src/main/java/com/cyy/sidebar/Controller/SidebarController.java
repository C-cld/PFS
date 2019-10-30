package com.cyy.sidebar.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SidebarController {
    @RequestMapping(value = "/")
    public ModelAndView sidebar(){
        try {
            ModelAndView mav=new ModelAndView("sidebar");
            return mav;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
