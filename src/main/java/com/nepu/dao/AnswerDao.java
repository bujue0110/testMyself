package com.nepu.dao;

import com.nepu.entity.Answer;
import com.nepu.entity.PrimaryKey.AnswerPK;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrator on 2017/4/19.
 */
public interface AnswerDao extends BaseDao<Answer,AnswerPK> {
}
