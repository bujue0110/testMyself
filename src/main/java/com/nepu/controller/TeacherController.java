package com.nepu.controller;

import com.nepu.DTO.AnswerDTO;
import com.nepu.dao.*;
import com.nepu.entity.Answer;
import com.nepu.entity.Paper;
import com.nepu.entity.PrimaryKey.AnswerPK;
import com.nepu.entity.Subject;
import com.nepu.entity.SubjectType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

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
    @Autowired
    PaperDao paperDao;
    @Autowired
    AnswerDao answerDao;
    @Autowired
    UserDao userDao;

    //添加试题
    @PostMapping(value = "/addSubject")
    public @ResponseBody Map<String, Object> addSubject(HttpServletRequest request) throws Exception{
        Map<String, Object> resultMap = new HashMap<>();
        try {
            String content = request.getParameter("content");
            String aItem = request.getParameter("aItem");
            String bItem = request.getParameter("bItem");
            String cItem = request.getParameter("cItem");
            String dItem = request.getParameter("dItem");
            String answer = request.getParameter("answer");
            String analysis = request.getParameter("analysis");
            String subjectType = request.getParameter("subjectType");
            Subject subject = new Subject();
            subject.setContent(content);
            subject.setaItem(aItem);
            subject.setbItem(bItem);
            subject.setcItem(cItem);
            subject.setdItem(dItem);
            subject.setAnswer(answer);
            subject.setAnalysis(analysis);
            subject.setTypeId(subjectType);
            subjectDao.save(subject);
            resultMap.put("resultString","添加成功！");
        }catch (Exception e){
            resultMap.put("resultString","添加失败！");
        }
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

    //添加试题到试题篮
    @PostMapping(value = "/addSubjectToPaper")
    public @ResponseBody Map<String, Object> addSubjectToPaper(HttpServletRequest request){
        Map<String, Object> resultMap = new HashMap<>();
        Integer subjectId = Integer.parseInt(request.getParameter("subjectData"));
        //List<String> subjectIds = new ArrayList<>();
        ArrayList<Integer> subjects =  (ArrayList<Integer>) request.getSession().getAttribute("subjects");
        if (subjects == null){
            subjects = new ArrayList<>();
            subjects.add(subjectId);
            resultMap.put("resultString","添加成功了！");
        }else {
            if (subjects.contains(subjectId)){
                resultMap.put("resultString","已经有这道题了！");
            }else {
                subjects.add(subjectId);
                resultMap.put("resultString","添加成功了！");
            }
        }
        request.getSession().setAttribute("subjects",subjects);
        //向页面输出两个超链接 一个是继续购物 ，一个是结算
        return resultMap;
    }

    //查看试题篮
    @GetMapping(value = "/queryCart")
    public String queryCart(HttpServletRequest request,Model model){
        ArrayList<Integer> subjects =  (ArrayList<Integer>) request.getSession().getAttribute("subjects");
        List<Subject> subjectList = new ArrayList<>();
        if (subjects == null){
            //试题篮为空

        }else {
            for (int i = 0;i<=subjects.size()-1;i++){
                Subject subject = subjectDao.findBySubjectId(subjects.get(i));
                subjectList.add(subject);
            }
            model.addAttribute("subjects",subjectList);
        }
        return "/teacher/cart";
    }

    //从试题篮移除试题
    @PostMapping(value = "/removeFromCart")
    public @ResponseBody Map<String, Object> removeFromCart(HttpServletRequest request){
        Map<String, Object> resultMap = new HashMap<>();
        try{
            ArrayList<Integer> subjects =  (ArrayList<Integer>) request.getSession().getAttribute("subjects");
            Integer subjectId = Integer.parseInt(request.getParameter("subjectId"));

            for(int i = 0 , len= subjects.size();i<len;++i){
                if(subjects.get(i)==subjectId){
                    subjects.remove(i);
                    --len;//减少一个
                    --i;
                }
            }
            request.getSession().setAttribute("subjects",subjects);
            resultMap.put("resultString","移除成功！");
        }catch (Exception e){
            resultMap.put("resultString","移除失败！");
        }
        return resultMap;
    }

    //清空试题篮
    public @ResponseBody Map<String, Object> clearCart(HttpServletRequest request){
        Map<String, Object> resultMap = new HashMap<>();
        try {
            ArrayList<Integer> subjects =  (ArrayList<Integer>) request.getSession().getAttribute("subjects");
            subjects.clear();
            request.getSession().setAttribute("subjects",subjects);
            resultMap.put("resultString","试题篮已经清空，为您跳转到主页！");
        }catch (Exception e){
            resultMap.put("resultString","清空成功！");
        }
        return resultMap;
    }

    //生成试卷
    @PostMapping(value = "/createPaperByCart")
    public @ResponseBody Map<String, Object> createPaperByCart(HttpServletRequest request){
        Map<String, Object> resultMap = new HashMap<>();
        String paperName = request.getParameter("paperName");
        ArrayList<Integer> subjects =  (ArrayList<Integer>) request.getSession().getAttribute("subjects");
        if (subjects == null){
            resultMap.put("resultString","试题篮中还没有试题！");
        }else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0;i<=subjects.size()-1;i++){
                sb.append(";");
                sb.append(subjects.get(i));
            }
            Paper paper = new Paper();
            paper.setPaperName(paperName);
            paper.setSubjectList(sb.toString().substring(1));
            paperDao.save(paper);
            subjects.clear();
            request.getSession().setAttribute("subjects",subjects);
            resultMap.put("resultString","试卷生成成功！");
        }
        return resultMap;
    }

    //查询学生对于某套试卷提交的答案(按试卷名查询)
    @GetMapping(value = "/queryAnswer/{paperName}")
    public String queryAnswer(@PathVariable("paperName")String paperName,Model model) throws Exception{
        //String paperName = request.getParameter("paperName");
        Integer paperId = paperDao.findByPaperName(paperName).getPaperId();

        List<Answer> answers = answerDao.findById_PaperId(paperId);
        List<AnswerDTO> answerDTOS = new ArrayList<>();
        for(int i = 0;i<answers.size();i++){
            String userName =userDao.findByUserid(answers.get(i).getId().getUserid()).getUsername();
            AnswerDTO answerDTO = new AnswerDTO();
            answerDTO.setId(answers.get(i).getId());
            answerDTO.setMarked(answers.get(i).getMarked());
            //answerDTO.setRemark(answers.get(i).getRemark());
            //answerDTO.setScore(answers.get(i).getScore());
            //answerDTO.setStudentAnswer(answers.get(i).getStudentAnswer());
            answerDTO.setUserName(userName);
            //answerDTO.setWrongList(answers.get(i).getWrongList());
            answerDTOS.add(answerDTO);
        }
        model.addAttribute("answerDTOS",answerDTOS);
        return "teacher/answerList";
    }
    //答卷详情
    @GetMapping(value = "/answerDetail")
    public String mark(HttpServletRequest request,Model model){
        Integer paperId = Integer.parseInt(request.getParameter("paperId"));
        Integer userid = Integer.parseInt(request.getParameter("userid"));
        AnswerPK answerPK = new AnswerPK();
        answerPK.setUserid(userid);
        answerPK.setPaperId(paperId);
        Answer answer = answerDao.findById(answerPK);
        model.addAttribute("answer",answer);
        return "teacher/answerDetail";
    }

    //提交批改
    @PostMapping(value ="/submitMark" )
    public @ResponseBody Map<String, Object> submitMark(HttpServletRequest request) throws Exception{
        Map<String, Object> resultMap = new HashMap<>();
        Integer userid = Integer.parseInt(request.getParameter("userid"));
        Integer paperId = Integer.parseInt(request.getParameter("paperId"));
        String remark = request.getParameter("remark");
        String score = request.getParameter("score");
        String wrongList = request.getParameter("wrongList");
        Integer result = answerDao.mark(remark,score,wrongList,userid,paperId);
        if (result == 1){
            resultMap.put("returnString","提交成功！");
        }else {
            resultMap.put("returnString","提交失败！");
        }
        return resultMap;

    }

}
