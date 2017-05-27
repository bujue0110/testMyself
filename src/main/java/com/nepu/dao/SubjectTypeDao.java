package com.nepu.dao;

import com.nepu.entity.SubjectType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

/**
 * Created by Administrator on 2017/4/21.
 */
public interface SubjectTypeDao extends BaseDao<SubjectType,Integer> {

    SubjectType findByTypeId(Integer typeId);

    @Transactional
    @Modifying
    @Query(value = "update SubjectType st set st.typeName=?2 where st.typeId=?1")
    int update(Integer typeId,String typeName);
}
