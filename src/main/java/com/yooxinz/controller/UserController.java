package com.yooxinz.controller;

import com.yooxinz.common.PageDefault;
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

    @GetMapping
    public ModelAndView queryByCreateTime(
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startTime,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endTime,
            @RequestParam(defaultValue = PageDefault.pageNum) int pageNum,
            @RequestParam(defaultValue = PageDefault.pageSize) int pageSize,
            ModelAndView mv){
        mv.setViewName("users");
        mv.addObject("startTime",startTime);
        mv.addObject("endTime",endTime);
        mv.addObject("users",userSerivce.query(startTime,endTime,pageNum,pageSize));
        return mv;
    }
}
