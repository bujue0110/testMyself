package com.nepu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2017/3/25.
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    @GetMapping(value = "/hello")
    public String hello(){
        return "admin/hello";
    }


}
