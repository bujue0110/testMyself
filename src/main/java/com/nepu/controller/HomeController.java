package com.nepu.controller;

import com.nepu.dao.UserDao;
import com.nepu.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/3/15.
 */

@Controller
public class HomeController {

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
