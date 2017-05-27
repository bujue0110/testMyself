package com.nepu.dao;

import com.nepu.entity.Paper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Administrator on 2017/3/18.
 */

public interface PaperDao extends BaseDao<Paper,Integer>{

    @Query("select p from Paper p where p.paperName like %?1%")
    List<Paper> searchPaper(String searchString);

    @Query("select p from Paper p where p.paperId = ?1")
    Paper getSubjectByPaper(Integer searchString);

    Paper findByPaperName(String paperName);

    Paper findByPaperId(Integer paperId);

    @Transactional
    @Modifying
    @Query(value = "update Paper p set p.paperName=?2,p.subjectList=?3 where p.paperId=?1")
    int update(String paperId,String paperName,String subjectList);
}
