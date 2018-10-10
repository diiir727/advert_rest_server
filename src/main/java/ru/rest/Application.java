package ru.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Application {

    static HibernateDao dao;
    public static void main(String[] args) throws Exception {
        dao = new HibernateDao();
        SpringApplication.run(Application.class, args);
    }
}
