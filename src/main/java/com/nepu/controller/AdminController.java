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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    //查询全部用户
    @GetMapping(value = "/queryAllUsers")
    public String queryUser(HttpServletRequest request,Model model){
        String pageNumberStr = request.getParameter("pageNumber");
        if (pageNumberStr == null || "".equals(pageNumberStr)){
            pageNumberStr = "1";
        }
        int pageNumber = Integer.parseInt(pageNumberStr);
        int pageSize = 10;

        PageRequest pageRequest = this.buildPageRequest(pageNumber,pageSize);
        Page<User> users = userDao.findAll(pageRequest);
        model.addAttribute("users",users);
        model.addAttribute("totalPageNumber",users.getTotalElements());
        model.addAttribute("pageSize",pageSize);
        return "admin/users";
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

    //修改用户信息
//    public @ResponseBody Map<String, Object> updateUser(HttpServletRequest request){
//        Map<String, Object> resultMap = new HashMap<>();
//
//        return resultMap;
//    }

    //查询全部试题
    @GetMapping(value = "queryAllSubjects")
    public String querySubjects(HttpServletRequest request,Model model){
        String pageNumberStr = request.getParameter("pageNumber");
        if (pageNumberStr == null || "".equals(pageNumberStr)){
            pageNumberStr = "1";
        }
        int pageNumber = Integer.parseInt(pageNumberStr);
        int pageSize = 10;

        PageRequest pageRequest = this.buildPageRequest(pageNumber,pageSize);
        Page<Subject> subjects = subjectDao.findAll(pageRequest);

        model.addAttribute("subjects",subjects);
        model.addAttribute("totalPageNumber",subjects.getTotalElements());
        model.addAttribute("pageSize",pageSize);
        return "admin/subjects";
    }

    //删除试题
    @PostMapping(value = "/deleteSubject")
    public @ResponseBody Map<String, Object> deleteSubject(HttpServletRequest request){
        Map<String, Object> resultMap = new HashMap<>();
        try{
            Integer subjectId = Integer.parseInt(request.getParameter("subjectId"));
            subjectDao.delete(subjectId);
            resultMap.put("returnString","删除成功！");
        }catch (Exception e){
            resultMap.put("returnString","删除失败！");
        }
        return resultMap;
    }

    //查询全部试卷
    @GetMapping(value = "queryAllPapers")
    public String queryPapers(HttpServletRequest request,Model model){
        String pageNumberStr = request.getParameter("pageNumber");
        if (pageNumberStr == null || "".equals(pageNumberStr)){
            pageNumberStr = "1";
        }
        int pageNumber = Integer.parseInt(pageNumberStr);
        int pageSize = 10;

        PageRequest pageRequest = this.buildPageRequest(pageNumber,pageSize);
        Page<Paper> papers = paperDao.findAll(pageRequest);

        model.addAttribute("papers",papers);
        model.addAttribute("totalPageNumber",papers.getTotalElements());
        model.addAttribute("pageSize",pageSize);
        return "admin/papers";
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

    //构建PageRequest
    private PageRequest buildPageRequest(int pageNumber, int pageSize) {
        return new PageRequest(pageNumber - 1, pageSize, null);
    }


}
