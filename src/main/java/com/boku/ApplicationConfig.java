package com.boku;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Loads application.properties from classpath
 * */
@Configuration
@PropertySource("classpath:application.properties")
public class ApplicationConfig {

}
