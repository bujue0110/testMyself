package com.nepu.entity.PrimaryKey;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by Administrator on 2017/4/16.
 */
@Embeddable
public class AnswerPK implements Serializable{

    private Integer userid;
    private Integer paperId;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getPaperId() {
        return paperId;
    }

    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
    }

    public AnswerPK() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnswerPK answerPK = (AnswerPK) o;

        if (userid != null ? !userid.equals(answerPK.userid) : answerPK.userid != null) return false;
        return paperId != null ? paperId.equals(answerPK.paperId) : answerPK.paperId == null;
    }

    @Override
    public int hashCode() {
        int result = userid != null ? userid.hashCode() : 0;
        result = 31 * result + (paperId != null ? paperId.hashCode() : 0);
        return result;
    }
}
