package com.nepu.DTO;

import com.nepu.entity.PrimaryKey.AnswerPK;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

/**
 * Created by Administrator on 2017/4/24.
 */
public class AnswerDTO {
    @EmbeddedId
    private AnswerPK id;
    private String userName;
    private String marked;//是否进行了批阅

    public AnswerPK getId() {
        return id;
    }

    public void setId(AnswerPK id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMarked() {
        return marked;
    }

    public void setMarked(String marked) {
        this.marked = marked;
    }
}
