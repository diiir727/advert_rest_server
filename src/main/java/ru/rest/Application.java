package ru.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@EnableAutoConfiguration
@SpringBootApplication
public class Application {

    static HibernateDao dao;
    public static void main(String[] args) throws Exception {
        dao = new HibernateDao();
        SpringApplication.run(Application.class, args);
    }
}
