package com.cardanonft.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/view/")
public class DesignViewController {
    private static Logger logger = LoggerFactory.getLogger(DesignViewController.class);


    @RequestMapping(value="/main")
    public ModelAndView main(HttpServletRequest request, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName( "/main");
        return modelAndView;
    }
}
