package com.nepu.dao;

import com.nepu.entity.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/18.
 */
@Repository
public class SubjectDaoImpl {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Subject> getRandomSub(final String typeId) {
        String sql = "select * from subject s where s.type_id = ?  order by rand() limit 5";
        List<Subject> subjects = new ArrayList<>();
        jdbcTemplate.query(sql, new Object[]{typeId}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                Subject subject = new Subject();
                subject.setaItem(resultSet.getString("a_item"));
                subject.setContent(resultSet.getString("content"));

                subjects.add(subject);
            }
        });
        return subjects;


    }
}
