package com.nepu.dao;

import com.nepu.entity.Answer;
import com.nepu.entity.PrimaryKey.AnswerPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2017/4/19.
 */
public interface AnswerDao extends BaseDao<Answer,AnswerPK> {

    List<Answer> findById_PaperId(Integer paperId);
    List<Answer> findById_Userid(Integer userid);
    Answer findById(AnswerPK answerPK);

    @Transactional
    @Modifying
    @Query("update Answer a set a.marked = '已批阅',a.remark = :remark,a.score = :score,a.wrongList = :wrongList where a.id.userid = :userid and a.id.paperId = :paperId")
    int mark(String remark,String score,String wrongList,Integer userid,Integer paperId);
}
