package com.boku;

import static java.lang.System.exit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.boku.service.SalesTaxService;

/**
 * SpringBootConsoleApplication which is the starting point for application launch and calls the run method which calls the program to calculate
 * sales tax and returns a string response
 * @author darshanarajput
 * */
@SpringBootApplication
public class SpringBootConsoleApplication implements CommandLineRunner {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SpringBootConsoleApplication.class);
    
    @Autowired
    private SalesTaxService service;

    public static void main(String[] args) throws Exception {
    	LOGGER.debug("SpringBootConsoleApplication :: Application started");
        SpringApplication app = new SpringApplication(SpringBootConsoleApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);

    }
    
    /**
     * Execution point for salestaxservice which calculates sales tax and returns the result back
     * */
    @Override
    public void run(String... args) throws Exception {
    	LOGGER.info("SpringBootConsoleApplication :: Running program to calculate sales tax");
    	String response = service.calculateSalesTax();
    	LOGGER.info("SpringBootConsoleApplication :: Program executed with response {}", response);
        exit(0);
    }
}