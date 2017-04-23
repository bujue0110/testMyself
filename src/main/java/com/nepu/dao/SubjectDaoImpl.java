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
        String sql = "select * from subject s where s.a_item <> and s.type_id = ? order by rand() limit 5";
        List<Subject> subjects = new ArrayList<>();
        jdbcTemplate.query(sql, new Object[]{typeId}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                Subject subject = new Subject();
                subject.setaItem(resultSet.getString("a_item"));
                subject.setContent(resultSet.getString("content"));
                subject.setbItem(resultSet.getString("b_item"));
                subject.setcItem(resultSet.getString("c_item"));
                subject.setdItem(resultSet.getString("d_item"));
                subject.setSubjectId(Integer.parseInt(typeId));
                subject.setAnalysis(resultSet.getString("analysis"));
                subject.setAnswer(resultSet.getString("answer"));
                subject.setSubjectId(resultSet.getInt("subject_id"));
                subjects.add(subject);
            }
        });
        return subjects;


    }
}
