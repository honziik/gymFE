package com.example.project.cfg;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean(name = "primaryDataSource")
    @Primary
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:h2:mem:testdb") // Primary database URL
                .driverClassName("org.h2.Driver")
                .username("sa")
                .password("")
                .build();
    }

    @Bean(name = "backupDataSource")
    public DataSource backupDataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:h2:mem:backupdb") // Backup database URL
                .driverClassName("org.h2.Driver")
                .username("sa")
                .password("")
                .build();
    }
}