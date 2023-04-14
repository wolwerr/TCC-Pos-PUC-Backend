package com.pequenosgenios.pg.config;

import com.pequenosgenios.pg.services.impl.DBService;
import org.springframework.beans.factory.annotation.Value;

import java.text.ParseException;

//@Configuration
//@Profile("dev")
public class DevConfig {

    private final DBService dbService;

    public DevConfig(DBService dbService) {
        this.dbService = dbService;
    }

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String strategy;

//    @Bean
    public boolean instantiateDatabase() throws ParseException {

        if(!"create".equals(strategy)){
            return false;
        }

        dbService.instantiateTestDatabase();

        return true;
    }

}