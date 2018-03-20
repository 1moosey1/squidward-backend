package com.squidward;

import com.squidward.filters.AuthFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class RunApplication {

    public static void main(String[] args) {
        log.debug("Starting Spring Application");
        SpringApplication.run(RunApplication.class,args);
    }

    @Bean
    public FilterRegistrationBean<AuthFilter> authFilterRegistration() {

        FilterRegistrationBean<AuthFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(authFilter());
        registration.addUrlPatterns("/api/*");
        registration.setName("AuthFilter");
        registration.setOrder(1);
        return registration;
    }

    @Bean
    public AuthFilter authFilter() {
        return new AuthFilter();
    }
}
