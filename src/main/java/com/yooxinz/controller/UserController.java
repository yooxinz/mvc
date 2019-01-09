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
    public ModelAndView query(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize,
            ModelAndView mv){
        mv.setViewName("users");
        mv.addObject("users",userSerivce.query(pageNum,pageSize));
        return mv;
    }

    @GetMapping(value = "/query/create")
    public ModelAndView queryByCreateTime(
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startTime,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endTime,
            ModelAndView mv){
        mv.setViewName("users");
        mv.addObject("startTime",startTime);
        mv.addObject("endTime",endTime);
        mv.addObject("users",userSerivce.query(startTime,endTime));
        return mv;
    }
}
