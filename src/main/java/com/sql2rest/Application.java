package com.sql2rest;

import com.sql2rest.config.AppConfig;
import com.sql2rest.controller.DataApi;
import com.sql2rest.util.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Application {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        logger.info("Starting the Application .....");
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        DataApi dataApi = applicationContext.getBean(DataApi.class);

        dataApi.sparkRest();

        String portNo = PropertyUtils.getProperty("server.port");
        logger.info("Application got started! at portNo:{}", portNo);
        logger.info("health check endPoint -  http://localhost:{}/api/health/check", portNo);
    }
}
