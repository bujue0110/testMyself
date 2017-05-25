package com.nepu.controller;

import com.nepu.dao.PaperDao;
import com.nepu.dao.SubjectDao;
import com.nepu.dao.UserDao;
import com.nepu.entity.Paper;
import com.nepu.entity.Subject;
import com.nepu.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/25.
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    UserDao userDao;
    @Autowired
    SubjectDao subjectDao;
    @Autowired
    PaperDao paperDao;

    @GetMapping(value = "/paper")
    public String adminPaper(){
        return "admin/paper";
    }
    @GetMapping(value = "/user")
    public String adminUser(){
        return "admin/user";
    }
    @GetMapping(value = "/subject")
    public String adminSubject(){
        return "admin/subject";
    }

    //查询全部用户
    @GetMapping(value = "/queryUser")
    public @ResponseBody Map<String,Object> queryUser(){
        Map<String, Object> resultMap = new HashMap<>();
        List<User> users = userDao.findAll();
        resultMap.put("data",users);
        return resultMap;
    }

    //删除用户
    @PostMapping(value = "/deleteUser")
    public @ResponseBody Map<String, Object> deleteUser(HttpServletRequest request){
        Map<String, Object> resultMap = new HashMap<>();
        try{
            Integer userid = Integer.parseInt(request.getParameter("userid"));
            userDao.delete(userid);
            resultMap.put("returnString","删除成功！");
        }catch (Exception e){
            resultMap.put("returnString","删除失败！");
        }
        return resultMap;
    }

    //查询全部试题
    @GetMapping(value = "/querySubject")
    public @ResponseBody Map<String,Object> querySubject(){
        Map<String, Object> resultMap = new HashMap<>();
        List<Subject> subjects = subjectDao.findAll();
        resultMap.put("recordsTotal",subjects.size());
        resultMap.put("recordsFiltered",subjects.size());
        resultMap.put("draw",1);
        resultMap.put("data",subjects);
        return resultMap;
    }
    @PostMapping(value = "/queryOneSubject")
    public @ResponseBody Map<String,Object> queryOneSubject(HttpServletRequest request){
        Map<String, Object> resultMap = new HashMap<>();
        String subjectId = request.getParameter("subjectId");
        Subject subject = subjectDao.findBySubjectId(Integer.parseInt(subjectId));
        resultMap.put("data",subject);
        return resultMap;
    }

    //删除试题
    @PostMapping(value = "/deleteSubject/{subjectId}")
    public @ResponseBody Map<String, Object> deleteSubject(@PathVariable("subjectId")String subjectId, HttpServletRequest request){
        Map<String, Object> resultMap = new HashMap<>();
        try{
            subjectDao.delete(Integer.parseInt(subjectId));
            resultMap.put("returnString","删除成功！");
        }catch (Exception e){
            resultMap.put("returnString","删除失败！");
        }
        return resultMap;
    }
    //查询全部试卷
    @GetMapping(value = "/queryPaper")
    public @ResponseBody Map<String,Object> queryPaper(){
        Map<String, Object> resultMap = new HashMap<>();
        List<Paper> papers = paperDao.findAll();
        resultMap.put("data",papers);
        return resultMap;
    }

    //删除用户
    @PostMapping(value = "/deletePaper")
    public @ResponseBody Map<String, Object> deletePaper(HttpServletRequest request){
        Map<String, Object> resultMap = new HashMap<>();
        try{
            Integer paperId = Integer.parseInt(request.getParameter("paperId"));
            paperDao.delete(paperId);
            resultMap.put("returnString","删除成功！");
        }catch (Exception e){
            resultMap.put("returnString","删除失败！");
        }
        return resultMap;
    }
}
