package com.squidward;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@Slf4j
@SpringBootApplication
@Configuration
public class RunApplication {

    public static void main(String[] args) {
     //   log.debug("Starting Spring Application");
        SpringApplication.run(RunApplication.class,args);
    }
}
