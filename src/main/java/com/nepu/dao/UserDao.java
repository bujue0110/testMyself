package com.nepu.dao;

import com.nepu.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.NamedEntityGraph;

/**
 * Created by Administrator on 2017/3/15.
 */

public interface UserDao extends BaseDao<User,Integer>{

   User findByUsername(String username);

}
