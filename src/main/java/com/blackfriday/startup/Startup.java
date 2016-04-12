package com.blackfriday.startup;


import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.blackfriday.services.UserServiceImpl;

@SpringBootApplication
@ComponentScan(basePackages={"com.blackfriday"})
public class Startup {

    /**
     * General Logger Instance.
     */
    protected static final Logger logger = Logger.getLogger(UserServiceImpl.class.getName());
    

    public static void main(String[] args) {
        //DOMConfigurator.configure("log4j.xml");
        logger.info("Starting application. -> main()");
        SpringApplication.run(Startup.class, args);
    }

}
