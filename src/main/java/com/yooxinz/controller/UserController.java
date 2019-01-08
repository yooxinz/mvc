package com.yooxinz.controller;

import com.yooxinz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

/**
 * Created by star on 2018/8/17.
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService userSerivce;

    @RequestMapping("/")
    @ResponseBody
    public String index(){

        return userSerivce.getUserNameById((long)1).toString();
    }

    @GetMapping(value = "/query")
    public ModelAndView query(ModelAndView mv){
        mv.setViewName("users");
        mv.addObject("users",userSerivce.query());
        return mv;
    }

}
