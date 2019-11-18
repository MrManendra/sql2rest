package com.sql2rest.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

public class PropertyUtils {
    private static final Logger logger = LoggerFactory.getLogger(PropertyUtils.class);

    private static final Properties configProp = new Properties();
    private static final Properties sqlProp = new Properties();

    private PropertyUtils(){}

    public static void reloadProperties(){
        loadProps();
        loadSqlProps();
    }

    public static void loadProps() {
        try {
            InputStream resourceAsStream = new FileInputStream(new File("config.properties"));
            Properties properties = new Properties();
            properties.load(resourceAsStream);
            configProp.putAll(properties);
            logger.info("config property loaded");
        } catch (FileNotFoundException e) {
            logger.error("Unable to locate config.properties", e);
        } catch (IOException e) {
            logger.error("Some problem has occurred while reading the config.properties file", e);
        }
    }

    public static String getProperty(String key){
        if(configProp.isEmpty()){
            loadProps();
        }

        final String value = (String)configProp.get(key);
        return  value;
    }

    public static void loadSqlProps() {
        try {
            InputStream resourceAsStream = new FileInputStream(new File("sql.properties"));
            Properties properties = new Properties();
            properties.load(resourceAsStream);
            sqlProp.putAll(properties);
            logger.info("sql property loaded");
        } catch (FileNotFoundException e) {
            logger.error("Unable to locate sql.properties", e);
        } catch (IOException e) {
            logger.error("Some problem has occurred while reading the sql.properties file", e);
        }
    }


    public static Set<String> getSqlKeys(){
        if(sqlProp.isEmpty()){
            loadSqlProps();
        }
        return sqlProp.keySet().stream().map(Object::toString).collect(Collectors.toSet());
    }

    public static String getSqlProperty(String key){
        if(sqlProp.isEmpty()){
            loadProps();
        }

        final String value = (String)sqlProp.get(key);
        return  value;
    }
}
