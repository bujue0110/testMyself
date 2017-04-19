package com.nepu.dao;

import com.nepu.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NamedEntityGraph;

/**
 * Created by Administrator on 2017/3/15.
 */

public interface UserDao extends BaseDao<User,Integer>{

   User findByUsername(String username);

   @Transactional
   @Modifying
   @Query("update User u set u.company = :company,u.school = :school,u.interest = :interest,u.age = :age where u.userid = :id")
   int updateInfo(String company,String school,String interest,Integer age,Integer id);

}
