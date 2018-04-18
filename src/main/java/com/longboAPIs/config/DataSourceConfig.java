package com.longboAPIs.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Primary
    @Bean(name = "UserDataSource")
    @Qualifier("UserDataSource")
    @ConfigurationProperties(prefix="spring.datasource.user")
    public DataSource UserDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "JlzxDataSource")
    @Qualifier("JlzxDataSource")
    @ConfigurationProperties(prefix="spring.datasource.jlzx")
    public DataSource JlzxDataSource() {
        return DataSourceBuilder.create().build();
    }

}
