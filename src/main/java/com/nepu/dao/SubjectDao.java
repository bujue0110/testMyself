package com.nepu.dao;

import com.nepu.entity.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;


import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Administrator on 2017/3/19.
 */

public interface SubjectDao extends BaseDao<Subject,Integer>{

    List<Subject> findByTypeIdEquals(String typeId);

    @Query("select s from Subject s where s.content like %?1% or " +
            "s.aItem like %?1% or s.bItem like %?1% or " +
            "s.cItem like %?1% or s.dItem like %?1%" )
    List<Subject> searchQuestion(String searchString);

    Subject findBySubjectId(Integer subjectId);

    Page<Subject> findByTypeId(String typeId, Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = "update Subject s set s.content=?2,s.aItem=?3,s.bItem=?4,s.cItem=?5,s.dItem=?6,s.answer=?7,s.analysis=?8 where s.subjectId=?1")
    int update(int subjectId,String content,String aItem,String bItem,String cItem,String dItem,String answer,String analysis);

}
