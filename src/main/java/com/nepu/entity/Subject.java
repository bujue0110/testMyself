package com.nepu.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Subject {

    @Id
    @GeneratedValue
    private Integer subjectId;
    private String typeId;
    private String content;
    private String answer;
    private String analysis;
    private String aItem;
    private String bItem;
    private String cItem;
    private String dItem;


    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }

    public String getaItem() {
        return aItem;
    }

    public void setaItem(String aItem) {
        this.aItem = aItem;
    }

    public String getbItem() {
        return bItem;
    }

    public void setbItem(String bItem) {
        this.bItem = bItem;
    }

    public String getcItem() {
        return cItem;
    }

    public void setcItem(String cItem) {
        this.cItem = cItem;
    }

    public String getdItem() {
        return dItem;
    }

    public void setdItem(String dItem) {
        this.dItem = dItem;
    }
}
