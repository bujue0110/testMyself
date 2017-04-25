package com.nepu.controller;

import com.nepu.dao.UserDao;
import com.nepu.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by Administrator on 2017/3/15.
 */

@Controller
public class HomeController {
    @Autowired
    UserDao userDao;

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
        return "admin/table";
    }

    @GetMapping(value = "/teacher")
    @PreAuthorize("hasRole('TEACHER')")
    public String helloTeacher(){ return "teacher/helloTeacher";}

    @GetMapping(value = "/user")
    @PreAuthorize("hasRole('USER')")
    public String helloUser(){
        return "user/helloUser";
    }

    @RequestMapping(value = "/login", method=RequestMethod.GET)
    public String login(){
        return "login";
    }

    @PostMapping(value = "/register")
    public @ResponseBody Map<String, Object> register(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        Map<String, Object> resultMap = new HashMap<>();
        User regUser = userDao.findByUsername(username);

        if (regUser != null){
            resultMap.put("returnString","用户名被占用");
        }else {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setRole(role);
            userDao.save(user);
            resultMap.put("returnString","注册成功");
        }
        return resultMap;
    }

}
