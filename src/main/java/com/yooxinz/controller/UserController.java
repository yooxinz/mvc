package com.yooxinz.controller;

import com.yooxinz.common.PageDefault;
import com.yooxinz.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by star on 2018/8/17.
 */
@RestController
@RequestMapping(value = "/user/")
@Slf4j
public class UserController {

    @Autowired
    UserService userSerivce;

    @GetMapping(value = "list")
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

    @PostMapping(value = "import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ModelAndView importUser(HttpServletRequest request,
                                   @NotNull(message = "file不能为空") MultipartFile file,
                                   ModelAndView mv) throws Exception {
        mv.setViewName("/uploadStatus");
        if (null == file || file.isEmpty()) {
            mv.addObject("message", "The file is empty!");
            return mv;
        }
        String result  = userSerivce.importUser(file.getInputStream());
        log.info(result);
        mv.addObject("message",result);
        return mv;
    }
}
