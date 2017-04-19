package com.nepu.controller;

import com.nepu.dao.*;
import com.nepu.entity.*;
import com.nepu.entity.PrimaryKey.AnswerPK;
import com.nepu.entity.PrimaryKey.FavPK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
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
    @Autowired
    SubjectDaoImpl subjectDaoImpl;
    @Autowired
    AnswerDao answerDao;

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

//    //试卷搜索功能
//    @GetMapping(value = "/papersearch/{searchString}")
//    public String searchPaper(@PathVariable("searchString")String searchString,Model model){
//        ArrayList<Paper> papers = new ArrayList<Paper>();
//        papers = (ArrayList<Paper>) paperDao.searchPaper(searchString);
//        model.addAttribute("papers",papers);
//        return "user/papers";
//    }

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
    public @ResponseBody Map<String, Object> addFav(HttpServletRequest request){
        String subjectIdString = request.getParameter("subjectData");
        Integer subjectId = Integer.parseInt(subjectIdString);
        Map<String, Object> resultMap = new HashMap<>();
        User user = userDao.findByUsername(request.getRemoteUser());
        Favorite favoriteData = favoriteDao.findByUserIdAndSubjectId(user.getUserid(),subjectId);
        if(favoriteData!=null){
            resultMap.put("resultString","已经收藏过了！");
        }else {
            Favorite favorite = new Favorite();
            FavPK favPK = new FavPK();
            favPK.setSubjectId(subjectId);
            favPK.setUserid(user.getUserid());
            favorite.setId(favPK);
            favorite.setTimestamp(new Timestamp(new Date().getTime()));
            favoriteDao.save(favorite);
            resultMap.put("resultString","收藏成功！");
        }
        return resultMap;
    }

    @GetMapping(value = "/rand/{typeId}")
    public String getRandomSub(@PathVariable("typeId") String typeId,Model model){
        ArrayList<Subject> subjects = new ArrayList<Subject>();
        subjects = (ArrayList<Subject>) subjectDaoImpl.getRandomSub(typeId);
        model.addAttribute("subjects",subjects);
        return "user/randtest";
    }

    //提交随机测试答案
    @PostMapping(value = "/randSubmit")
    public @ResponseBody Map<String, Object> randSubmit(HttpServletRequest request) throws IOException {
        String map = request.getParameter("map");
        String[] entrys = map.split(",");
        StringBuilder sb = new StringBuilder();
        for(int i=1;i<+entrys.length+1;i++){
            String[] id_answer=entrys[i-1].split("@");
            String id = id_answer[0].substring(6);
            String answer = id_answer[1];
            String rightAnswer = subjectDao.findBySubjectId(Integer.parseInt(id)).getAnswer();
            if(!answer.equals(rightAnswer)){
                sb.append("第"+i+"题错误  ");
            }
        }
        String s = sb.toString();
        // 返回结果串
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("status", "success");
        resultMap.put("resultString", sb.toString());
        System.out.print(resultMap.get("resultString"));
        return resultMap;
    }

    //修改个人信息
    @PostMapping(value = "/updateInfo")
    public @ResponseBody Map<String, Object> updateInfo(HttpServletRequest request){
        Map<String, Object> resultMap = new HashMap<>();
        String company = request.getParameter("company");
        String school = request.getParameter("school");
        String interest = request.getParameter("interest");
        Integer age = Integer.parseInt(request.getParameter("age"));
        User user = userDao.findByUsername(request.getRemoteUser());
        Integer id = user.getUserid();
        int result = userDao.updateInfo(company,school,interest,age,id);
        if(result == 1){
            resultMap.put("resultString","修改成功");
        }else {
            resultMap.put("resultString","修改失败");
        }
        return resultMap;
    }
    //提交对于某套试卷的答案
    @PostMapping(value = "/paperSubmit")
    public @ResponseBody Map<String, Object>paperSubmit(HttpServletRequest request){
        String map = request.getParameter("map");
        String[] entrys = map.split(",");
        StringBuilder sb = new StringBuilder();
        for(int i=1;i<+entrys.length+1;i++){
            String[] id_answer=entrys[i-1].split("@");
           // String id = id_answer[0].substring(6);
            String answer = id_answer[1];
            String answerString = "第"+i+"题:"+answer+";";
            sb.append(answerString);
        }
        Answer answer = new Answer();
        AnswerPK answerPK = new AnswerPK();
        answerPK.setPaperId(Integer.parseInt(request.getParameter("paperId")));
        answerPK.setUserid(userDao.findByUsername(request.getRemoteUser()).getUserid());
        answer.setId(answerPK);
        answer.setStudentAnswer(sb.toString());
        answerDao.save(answer);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultString","提交成功");
        return resultMap;
    }

}

