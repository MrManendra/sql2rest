package com.sql2rest;

import com.sql2rest.config.AppConfig;
import com.sql2rest.controller.DataApi;
import com.sql2rest.util.PropertyUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Application {

    public static void main(String[] args) {
        System.out.println("Starting the Application .....");
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        DataApi dataApi = applicationContext.getBean(DataApi.class);

        dataApi.sparkRest();

        String portNo = PropertyUtils.getProperty("server.port");
        System.out.println("Application got started! at portNo:"+ portNo);
        System.out.println("health check endPoint -  http://localhost:"+portNo+"/api/health/check");
    }
}
