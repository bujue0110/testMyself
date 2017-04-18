package com.nepu.controller;

import com.nepu.dao.UserDao;
import com.nepu.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/3/15.
 */

@Controller
public class HomeController {

    /* 页面跳转主要用于错误页面 */
    @RequestMapping(value = "{page}", method = RequestMethod.GET)
    public String toPage(@PathVariable("page") String page) {
        return page;
    }

    @RequestMapping(value = {"", "/home"}, method= RequestMethod.GET)
    public String home(){
        return "home";
    }

    @GetMapping(value = "/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String helloAdmin(){
        return "admin/helloAdmin";
    }

    @GetMapping(value = "/user")
    @PreAuthorize("hasRole('USER')")
    public String helloUser(){
        return "user/helloUser";
    }

    @RequestMapping(value = "/login", method=RequestMethod.GET)
    public String login(){
        return "login";
    }

}
