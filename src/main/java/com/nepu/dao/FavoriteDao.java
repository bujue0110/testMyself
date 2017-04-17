package com.nepu.dao;

import com.nepu.entity.Favorite;
import com.nepu.entity.PrimaryKey.FavPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Created by Administrator on 2017/4/15.
 */
public interface FavoriteDao extends BaseDao<Favorite,FavPK>{

    @Query("select f from Favorite f where f.id.userid=?1 and f.id.subjectId=?2")
    Favorite findByUserIdAndSubjectId(Integer userId,Integer SubjectId);
}
