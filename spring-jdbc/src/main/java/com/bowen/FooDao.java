package com.bowen;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Repository
@Slf4j
public class FooDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SimpleJdbcInsert simpleJdbcInsert;


    public void insertData(){
        Arrays.asList("b","c").forEach( bar -> {
            jdbcTemplate.update("insert into FOO(BAR) values(?)", bar);
        });

        HashMap<String,String> row = new HashMap<>();
        row.put("BAR", "d");
        Number id = simpleJdbcInsert.executeAndReturnKey(row);
        log.info("ID of d:{}", id);

    }

    public void listData(){
        log.info("count:{}", jdbcTemplate.queryForObject("select count(*) from FOO", Long.class));
        List<String> list = jdbcTemplate.queryForList("select BAR from FOO", String.class);
        list.forEach(e -> log.info("Bar:{}", e));

        List<Foo> fooList = jdbcTemplate.query("select * from FOO", new RowMapper<Foo>() {
            @Override
            public Foo mapRow(ResultSet resultSet, int i) throws SQLException {
                return Foo.builder()
                        .id(resultSet.getLong(1))
                        .bar(resultSet.getString(2))
                        .build();
            }
        });

        fooList.forEach(f -> log.info("Foo:{}", f));
    }

}
