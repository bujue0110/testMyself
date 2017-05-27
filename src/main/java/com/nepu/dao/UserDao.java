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
   @Query("update User u set u.company =?1,u.school =?2,u.interest =?3,u.age =?4 where u.userid =?5")
   int updateInfo(String company,String school,String interest,Integer age,Integer id);

   @Transactional
   @Modifying
   @Query("update User u set u.company =?1,u.school =?2,u.interest =?3,u.age =?4,u.username=?6,u.password=?7,u.role=?8 where u.userid =?5")
   int update(String company,String school,String interest,Integer age,Integer id,String userName,String password,String role);

   User findByUserid(Integer userId);

}
