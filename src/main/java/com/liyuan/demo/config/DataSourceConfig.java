package com.liyuan.demo.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfig {


    @Bean(name = "masterDataSource")
    @ConfigurationProperties(prefix = "mysql.datasource.master")
    public DataSource masterDataSource(){
        return new HikariDataSource();
    }

    @Bean(name = "slaveDataSource1")
    @ConfigurationProperties(prefix = "mysql.datasource.slave1")
    public DataSource slaveDataSource1(){
        return new HikariDataSource();
    }

    @Bean(name = "slaveDataSource2")
    @ConfigurationProperties(prefix = "mysql.datasource.slave2")
    public DataSource slaveDataSource2(){
        return new HikariDataSource();
    }

    @Bean(name = "dynamicDataSource")
    public DataSource dynamicDataSource(@Qualifier("masterDataSource") DataSource masterDataSource,
                                          @Qualifier("slaveDataSource1") DataSource slaveDataSource1,
                                          @Qualifier("slaveDataSource2") DataSource slaveDataSource2){
        DynamicDataSource dynamicDataSource = new DynamicDataSource ();
        Map<Object,Object> targetDataSource = new HashMap<>();

        targetDataSource.put(DBTypeEnum.MASTER,masterDataSource);
        targetDataSource.put(DBTypeEnum.SLAVE1,slaveDataSource1);
        targetDataSource.put(DBTypeEnum.SLAVE2,slaveDataSource2);

        dynamicDataSource.setDefaultTargetDataSource(masterDataSource);
        dynamicDataSource.setTargetDataSources(targetDataSource);
        return dynamicDataSource;
    }
}
