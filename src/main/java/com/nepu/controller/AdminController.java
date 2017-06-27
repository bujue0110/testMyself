package com.nepu.controller;

import com.nepu.dao.PaperDao;
import com.nepu.dao.SubjectDao;
import com.nepu.dao.SubjectTypeDao;
import com.nepu.dao.UserDao;
import com.nepu.entity.Paper;
import com.nepu.entity.Subject;
import com.nepu.entity.SubjectType;
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
    SubjectTypeDao subjectTypeDao;
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
    @GetMapping(value = "/type")
    public String adminType(){
        return "admin/type";
    }

    //查询全部用户
    @GetMapping(value = "/queryUser")
    public @ResponseBody Map<String,Object> queryUser(){
        Map<String, Object> resultMap = new HashMap<>();
        List<User> users = userDao.findAll();
        resultMap.put("data",users);
        resultMap.put("recordsTotal",users.size());
        resultMap.put("recordsFiltered",users.size());
        resultMap.put("draw",1);
        return resultMap;
    }
    @PostMapping(value = "/queryOneUser")
    public @ResponseBody Map<String,Object> queryOneUser(HttpServletRequest request){
        Map<String, Object> resultMap = new HashMap<>();
        String userId = request.getParameter("userid");
        User user = userDao.findByUserid(Integer.parseInt(userId));
        resultMap.put("data",user);
        return resultMap;
    }

    //修改用户
    @PostMapping(value = "/updateUser")
    public @ResponseBody Map<String, Object> updateUser(HttpServletRequest request) throws Exception{
        Map<String, Object> resultMap = new HashMap<>();
        try {
            String userid = request.getParameter("id");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String role = request.getParameter("role");
            String school = request.getParameter("school");
            String interest = request.getParameter("interest");
            String company = request.getParameter("company");
            String age = request.getParameter("age");

            userDao.update(company,school,interest,Integer.parseInt(age),Integer.parseInt(userid),username,password,role);
            resultMap.put("resultString","修改成功！");
        }catch (Exception e){
            resultMap.put("resultString","修改失败！");
        }
        return resultMap;
    }

    //删除用户
    @PostMapping(value = "/deleteUser")
    public @ResponseBody Map<String, Object> deleteUser(HttpServletRequest request){
        Map<String, Object> resultMap = new HashMap<>();
        try{
            Integer userid = Integer.parseInt(request.getParameter("userId"));
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
    //修改试题
    @PostMapping(value = "/updateSubject")
    public @ResponseBody Map<String, Object> updateSubject(HttpServletRequest request) throws Exception{
        Map<String, Object> resultMap = new HashMap<>();
        try {
            String subjectId = request.getParameter("id");
            String content = request.getParameter("content");
            String aItem = request.getParameter("aItem");
            String bItem = request.getParameter("bItem");
            String cItem = request.getParameter("cItem");
            String dItem = request.getParameter("dItem");
            if(aItem.equals("")&&bItem.equals("")&&cItem.equals("")&&dItem.equals("")){
                aItem = null;
                bItem = null;
                cItem = null;
                dItem = null;
            }
            String answer = request.getParameter("answer");
            String analysis = request.getParameter("analysis");
            //String subjectType = request.getParameter("subjectType");
            subjectDao.update(Integer.parseInt(subjectId),content,aItem,bItem,cItem,dItem,answer,analysis);
            resultMap.put("resultString","修改成功！");
        }catch (Exception e){
            resultMap.put("resultString","修改失败！");
        }
        return resultMap;
    }

    //删除试题
    @PostMapping(value = "/deleteSubject")
    public @ResponseBody Map<String, Object> deleteSubject(HttpServletRequest request){
        String subjectId = request.getParameter("subjectId");
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
    @PostMapping(value = "/queryOnePaper")
    public @ResponseBody Map<String,Object> queryOnePaper(HttpServletRequest request){
        Map<String, Object> resultMap = new HashMap<>();
        String paperId = request.getParameter("paperId");
        Paper paper = paperDao.findByPaperId(Integer.parseInt(paperId));
        resultMap.put("data",paper);
        return resultMap;
    }
    //修改试卷
    @PostMapping(value = "/updatePaper")
    public @ResponseBody Map<String, Object> updatePaper(HttpServletRequest request) throws Exception{
        Map<String, Object> resultMap = new HashMap<>();
        try {
            String paperId = request.getParameter("id");
            String paperName = request.getParameter("paperName");
            String subjectList = request.getParameter("subjectList");
            paperDao.update(paperId,paperName,subjectList);
            resultMap.put("resultString","修改成功！");
        }catch (Exception e){
            resultMap.put("resultString","修改失败！");
        }
        return resultMap;
    }

    //删除试卷
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


    //查询全部类别
    @GetMapping(value = "/queryType")
    public @ResponseBody Map<String,Object> queryType(){
        Map<String, Object> resultMap = new HashMap<>();
        List<SubjectType> subjectTypes = subjectTypeDao.findAll();
        resultMap.put("recordsTotal",subjectTypes.size());
        resultMap.put("recordsFiltered",subjectTypes.size());
        resultMap.put("draw",1);
        resultMap.put("data",subjectTypes);
        return resultMap;
    }
    @PostMapping(value = "/queryOneType")
    public @ResponseBody Map<String,Object> queryOneType(HttpServletRequest request){
        Map<String, Object> resultMap = new HashMap<>();
        String typeId = request.getParameter("typeId");
        SubjectType subjectType = subjectTypeDao.findByTypeId(Integer.parseInt(typeId));
        resultMap.put("data",subjectType);
        return resultMap;
    }
    //修改试题
    @PostMapping(value = "/updateType")
    public @ResponseBody Map<String, Object> updateType(HttpServletRequest request) throws Exception{
        Map<String, Object> resultMap = new HashMap<>();
        try {
            String typeId = request.getParameter("id");
            String typeName = request.getParameter("typeName");

            subjectTypeDao.update(Integer.parseInt(typeId),typeName);
            resultMap.put("resultString","修改成功！");
        }catch (Exception e){
            resultMap.put("resultString","修改失败！");
        }
        return resultMap;
    }

    //删除试题
    @PostMapping(value = "/deleteType")
    public @ResponseBody Map<String, Object> deleteType(HttpServletRequest request){
        String typeId = request.getParameter("typeId");
        Map<String, Object> resultMap = new HashMap<>();
        try{
            subjectTypeDao.delete(Integer.parseInt(typeId));
            resultMap.put("returnString","删除成功！");
        }catch (Exception e){
            resultMap.put("returnString","删除失败！");
        }
        return resultMap;
    }
}
