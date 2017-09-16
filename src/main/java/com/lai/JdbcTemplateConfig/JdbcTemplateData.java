package com.lai.JdbcTemplateConfig;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * Created by lailai on 2017/9/16.
 */

/**
 * 多数据源对jdbcTemplate的支持，在创建jdbcTemplate对象的时候分别注入不同的数据源
 */
public class JdbcTemplateData {

    @Bean(name = "primaryJdbcTemplate")
    public JdbcTemplate primaryJdbcTemplate(@Qualifier("primaryDataSource")DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "secondaryJdbcTemplate")
    public JdbcTemplate secondaryJdbcTemplate(@Qualifier("secondaryDataSource") DataSource dataSource){
        return  new JdbcTemplate(dataSource);
    }
}
