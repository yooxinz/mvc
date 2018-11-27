package com.yooxinz.controller;

import com.yooxinz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by star on 2018/8/17.
 */
@RestController
public class ConfigController{

    @Autowired
    UserService userSerivce;

    @RequestMapping("/")
    @ResponseBody
    public String index(){

        return userSerivce.getUserNameById((long)1).toString();
    }

}
