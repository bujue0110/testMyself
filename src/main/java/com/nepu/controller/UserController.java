package com.nepu.controller;

import com.nepu.dao.FavoriteDao;
import com.nepu.dao.PaperDao;
import com.nepu.dao.SubjectDao;
import com.nepu.dao.UserDao;
import com.nepu.entity.Favorite;
import com.nepu.entity.Paper;
import com.nepu.entity.Subject;
import com.nepu.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by Administrator on 2017/3/19.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    SubjectDao subjectDao;
    @Autowired
    PaperDao paperDao;
    @Autowired
    UserDao userDao;
    @Autowired
    FavoriteDao favoriteDao;

    //获取试题列表
    @GetMapping(value = "/questions")
    public String getQuestions(Model model){
        ArrayList<Subject> subjects = new ArrayList<Subject>();
        subjects = (ArrayList<Subject>) subjectDao.findAll();
        model.addAttribute("subjects",subjects);
        return "/user/questions";
    }

    //根据试题ID获取一道试题
    @GetMapping(value = "/question/{subjectId}")
    @ResponseBody
    public Subject getOneQuestion(@PathVariable("subjectId")Integer subjectId){

        Subject subject = (Subject) subjectDao.findOne(subjectId);
        //return "user/question";
        return subject;
    }

    //根据类型获取试题
    @GetMapping(value = "/questions/{typeId}")
    public String getQuestionsByType(@PathVariable("typeId")Integer typeId,Model model){
        ArrayList<Subject> subjects = new ArrayList<Subject>();
        subjects = (ArrayList<Subject>) subjectDao.findByTypeIdEquals(typeId.toString());
        model.addAttribute("subjects",subjects);
        return "user/questions";
    }

    //试题搜索功能
    @GetMapping(value = "/subjectsearch/{searchString}")
    public String searchQuestion(@PathVariable("searchString")String searchString,Model model){
        ArrayList<Subject> subjects = new ArrayList<Subject>();
        subjects = (ArrayList<Subject>) subjectDao.searchQuestion(searchString);
        model.addAttribute("subjects",subjects);
        return "user/question";
    }

    //试卷搜索功能
    @GetMapping(value = "/spapersearch/{searchString}")
    public String searchPaper(@PathVariable("searchString")String searchString,Model model){
        ArrayList<Paper> papers = new ArrayList<Paper>();
        papers = (ArrayList<Paper>) paperDao.searchPaper(searchString);
        model.addAttribute("papers",papers);
        return "user/papers";
    }

    //根据试卷编号获取试题列表
    @GetMapping(value = "/paperSub/{searchString}")
    public String getSubjectsByPaper(@PathVariable("searchString")String searchString,Model model){
        Paper paper = paperDao.getSubjectByPaper(searchString);
        String subjectList = paper.getSubjectList();
        String[] subjectIds = subjectList.split(";");
        ArrayList<Subject> subjectArrayList = new ArrayList<Subject>();
        for(int i=0;i<=subjectIds.length;i++){
            Subject subject = (Subject) subjectDao.getOne(Integer.parseInt(subjectIds[i]));
            subjectArrayList.add(subject);
        }
        model.addAttribute("subjects",subjectArrayList);
        return "user/paperSub";
    }

    //获取试卷列表
    @GetMapping(value = "/papers")
    public String getPapers(Model model){
        ArrayList<Paper> papers = new ArrayList<Paper>();
        papers = (ArrayList<Paper>) paperDao.findAll();
        model.addAttribute("papers",papers);
        return "user/papers";
    }

    //添加收藏
    @PostMapping(value = "/addFav")
    public Model addFav(HttpServletRequest request,Subject subject,Model model){
        String message = "";
        User user = userDao.findByUsername(request.getRemoteUser());
        Favorite favoriteData = favoriteDao.findByUserIdAndSubjectId(user.getUserid(),subject.getSubjectId());
        if (favoriteData.getId().getUserid() == user.getUserid() && favoriteData.getId().getSubjectId() == subject.getSubjectId()){
            message = "已经收藏过了！";
            model.addAttribute(message);
        }else {
            Favorite favorite = new Favorite();
            favorite.getId().setUserid(user.getUserid());
            favorite.getId().setSubjectId(subject.getSubjectId());
            favorite.setTimestamp(new Timestamp(new Date().getTime()));
            favoriteDao.save(favorite);
            message = "收藏成功！";
            model.addAttribute(message);
        }
        return model;
    }

    @GetMapping(value = "/rand/{typeId}")
    public String getRandomSub(@PathVariable("typeId") String typeId,Model model){
        ArrayList<Subject> subjects = new ArrayList<Subject>();
        subjects = (ArrayList<Subject>) subjectDao.findFirst5ByTypeId(typeId);
        model.addAttribute("subjects",subjects);
        return "user/questions";
    }
}

