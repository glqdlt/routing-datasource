package com.glqdlt.ex.routingdatasource;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@Slf4j
@SpringBootApplication
@ComponentScan(basePackages = "com.glqdlt.ex.*")
@MapperScan(basePackages = "com.glqdlt.ex.*")
public class RoutingDatasourceApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(RoutingDatasourceApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {

    }
}
