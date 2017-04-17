package com.nepu.entity.PrimaryKey;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by Administrator on 2017/4/16.
 */
@Embeddable
public class FavPK implements Serializable{

    private Integer userid;
    private Integer subjectId;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public FavPK() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FavPK favPK = (FavPK) o;

        if (userid != null ? !userid.equals(favPK.userid) : favPK.userid != null) return false;
        return subjectId != null ? subjectId.equals(favPK.subjectId) : favPK.subjectId == null;
    }

    @Override
    public int hashCode() {
        int result = userid != null ? userid.hashCode() : 0;
        result = 31 * result + (subjectId != null ? subjectId.hashCode() : 0);
        return result;
    }
}
