package com.nepu.controller;

import com.nepu.dao.SubjectDao;
import com.nepu.dao.SubjectTypeDao;
import com.nepu.entity.Subject;
import com.nepu.entity.SubjectType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/21.
 */
@Controller
@RequestMapping(value = "/teacher")
public class TeacherController {
    @Autowired
    SubjectTypeDao subjectTypeDao;
    @Autowired
    SubjectDao subjectDao;


    //添加试题
    @PostMapping(value = "/addSubject")
    public @ResponseBody Map<String, Object> addSubject(HttpServletRequest request){
        String content = request.getParameter("content");
        String aItem = request.getParameter("aItem");
        String bItem = request.getParameter("bItem");
        String cItem = request.getParameter("cItem");
        String dItem = request.getParameter("dItem");
        String answer = request.getParameter("answer");
        String analysis = request.getParameter("analysis");



        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultString","添加成功！");
        return resultMap;
    }

    //跳转到添加试题页，加载试题类别
    @GetMapping("/skipAdd")
    public String skipAdd (Model model){
        List<SubjectType> subjectTypes = subjectTypeDao.findAll();
        model.addAttribute("subjectTypes",subjectTypes);
        return "teacher/addSubject";
    }

    //根据类型获取试题
    @GetMapping(value = "/questions/{typeId}")
    public String getQuestionsByType(@PathVariable("typeId")String typeId, Model model,HttpServletRequest request){
        String pageNumberStr = request.getParameter("pageNumber");
        if (pageNumberStr == null || "".equals(pageNumberStr)){
            pageNumberStr = "1";
        }
        int pageNumber = Integer.parseInt(pageNumberStr);
        int pageSize = 5;

        PageRequest pageRequest = this.buildPageRequest(pageNumber,pageSize);
        Page<Subject> subjects = subjectDao.findByTypeId(typeId,pageRequest);

        model.addAttribute("subjects",subjects);
        model.addAttribute("totalPageNumber",subjects.getTotalElements());
        model.addAttribute("pageSize",pageSize);
        model.addAttribute("typeId",typeId);
        return "teacher/questions";
    }
    //构建PageRequest
    private PageRequest buildPageRequest(int pageNumber, int pageSize) {
        return new PageRequest(pageNumber - 1, pageSize, null);
    }
}
