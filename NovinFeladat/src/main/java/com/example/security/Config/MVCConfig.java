package com.example.security.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MVCConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/hello").setViewName("hello");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/register").setViewName("register");
        registry.addViewController("/persons").setViewName("PersonList");
        registry.addViewController("/receipts").setViewName("receiptList");
        registry.addViewController("/addReceipt").setViewName("addReceipt");
        registry.addViewController("/roles").setViewName("roles");
        registry.addViewController("/addRole").setViewName("addRole");
        registry.addViewController("/error_page").setViewName("error_page");

    }

}