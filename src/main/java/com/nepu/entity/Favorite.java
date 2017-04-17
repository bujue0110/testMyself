package com.nepu.entity;

import com.nepu.entity.PrimaryKey.FavPK;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Administrator on 2017/4/15.
 */
@Entity
public class Favorite {

    @EmbeddedId
    private FavPK id;

    private Timestamp timestamp;

    public FavPK getId() {
        return id;
    }

    public void setId(FavPK id) {
        this.id = id;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

}
