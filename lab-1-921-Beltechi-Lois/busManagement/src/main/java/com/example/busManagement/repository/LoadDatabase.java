package com.example.busManagement.repository;

import com.example.busManagement.domain.Person;
//import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {


    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);


    @Bean
    CommandLineRunner initDatabase(IRepositoryPerson repository) {

        return args -> {
            log.info("Preloading " + repository.save(new Person("Bilbo Baggins", "burglar", "2020/02/11","Male","0743591501")));
            log.info("Preloading " + repository.save(new Person("Bilsbo Bafggins", "bursglar", "2020/02/12","Female","0743596501")));

            // fetch all customers
            log.info("People found with findAll():");
            log.info("-------------------------------");
            for (Person person : repository.findAll()) {
                log.info(person.toString());
            }
            log.info("");



        };
    }
}
