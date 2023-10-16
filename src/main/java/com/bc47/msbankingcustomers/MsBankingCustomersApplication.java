package com.bc47.msbankingcustomers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
//@EnableReactiveMongoRepositories
public class MsBankingCustomersApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsBankingCustomersApplication.class, args);
    }
}
