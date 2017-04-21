package com.nepu.dao;

import com.nepu.entity.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;


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

}
