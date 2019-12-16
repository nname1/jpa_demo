package com.example.demo.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:env.properties")
public class PropertyUtils {
    @Autowired
    private Environment env;

    @Bean
    public TestBean testEnv() {
        TestBean testBean = new TestBean();
        testBean.setApiUrl(env.getProperty("test.url"));
        testBean.setToken(env.getProperty("test.token"));
        return testBean;
    }
}