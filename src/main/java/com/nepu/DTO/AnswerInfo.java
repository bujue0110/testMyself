package com.nepu.DTO;

import com.nepu.entity.PrimaryKey.AnswerPK;

import javax.persistence.EmbeddedId;

/**
 * Created by Administrator on 2017/5/24.
 */
public class AnswerInfo {
    @EmbeddedId
    private AnswerPK id;
    private Integer paperId;

    public Integer getPaperId() {
        return paperId;
    }

    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
    }

    private String paperName;
    private String marked;//是否进行了批阅

    public AnswerPK getId() {
        return id;
    }

    public void setId(AnswerPK id) {
        this.id = id;
    }

    public String getPaperName() {
        return paperName;
    }

    public void setPaperName(String userName) {
        this.paperName = paperName;
    }

    public String getMarked() {
        return marked;
    }

    public void setMarked(String marked) {
        this.marked = marked;
    }
}
