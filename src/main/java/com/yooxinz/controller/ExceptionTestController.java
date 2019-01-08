package com.yooxinz.controller;

import com.yooxinz.common.ResultEnum;
import com.yooxinz.exception.BusinessException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exception")
public class ExceptionTestController {

    @GetMapping("/{id}")
    public String test(@PathVariable(name="id") Integer id) throws BusinessException {

        if(id == 1) {
            System.out.println(1);
            throw new BusinessException(ResultEnum.QUERY_ERROR);
        }

        return "SUCCESS";
    }

}