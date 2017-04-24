package com.nepu.entity;

import com.nepu.entity.PrimaryKey.AnswerPK;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Administrator on 2017/4/15.
 */
@Entity
public class Answer {

    @EmbeddedId
    private AnswerPK id;

    private String studentAnswer;
    private Integer score;
    private String wrongList;
    private String remark;
    private String marked;//是否进行了批阅

    public AnswerPK getId() {
        return id;
    }

    public void setId(AnswerPK id) {
        this.id = id;
    }

    public String getStudentAnswer() {
        return studentAnswer;
    }

    public void setStudentAnswer(String studentAnswer) {
        this.studentAnswer = studentAnswer;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getWrongList() {
        return wrongList;
    }

    public void setWrongList(String wrongList) {
        this.wrongList = wrongList;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getMarked() {
        return marked;
    }

    public void setMarked(String marked) {
        this.marked = marked;
    }
}
