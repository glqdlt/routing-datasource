package com.glqdlt.ex.routingdatasource.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;


@PropertySource("classpath:datasource.properties")
@Configuration
public class DataSourceConfig {

    @Value("${db.jdbc.url.db1}")
    private String db1Url;
    @Value("${db.jdbc.url.db2}")
    private String db2Url;
    @Value("${db.jdbc.username}")
    private String id;
    @Value("${db.jdbc.password}")
    private String pw;

    @Bean()
    public DataSource masterSource() {
        return DataSourceBuilder.create()
                .url(db1Url)
                .username(id)
                .password(pw)
                .build();
    }


    @Bean()
    public DataSource slaveSource() {
        return DataSourceBuilder.create()
                .url(db2Url)
                .username(id)
                .password(pw)
                .build();
    }

    @Bean()
    public DataSource routingDataSource() {
        ReplicationDataSource routingDataSource = new ReplicationDataSource();
        Map<Object, Object> sources = new HashMap<>();
        sources.put("master", masterSource());
        sources.put("slave", slaveSource());
        routingDataSource.setTargetDataSources(sources);
        routingDataSource.setDefaultTargetDataSource(masterSource());
        return routingDataSource;
    }

    @Primary
    @Bean("dataSource")
    public DataSource lazyDataSource() {
        return new LazyConnectionDataSourceProxy(routingDataSource());
    }

}
