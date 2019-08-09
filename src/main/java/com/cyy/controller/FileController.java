package com.cyy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FileController {
    @RequestMapping(value = "/porn")
    public ModelAndView porn() {
        ModelAndView mav = new ModelAndView("porn");
        return mav;
    }
}
