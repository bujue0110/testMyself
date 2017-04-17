package com.nepu.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/4/16.
 */
@NoRepositoryBean
public interface BaseDao<T, ID extends Serializable> extends
        JpaRepository<T, ID>, JpaSpecificationExecutor<T> {
}
