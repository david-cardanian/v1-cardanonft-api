package com.cardanonft.view.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class ViewController {
    private static Logger logger = LoggerFactory.getLogger(ViewController.class);

    @RequestMapping(value="/login")
    public ModelAndView login(HttpServletRequest request, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName( "auth/login");
        return modelAndView;
    }
    @RequestMapping(value="/main")
    public ModelAndView main(HttpServletRequest request, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName( "/main");
        return modelAndView;
    }
    @RequestMapping(value="/guide")
    public ModelAndView guide(HttpServletRequest request, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName( "/guide");
        return modelAndView;
    }
}
