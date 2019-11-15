package com.sql2rest.util;

import java.io.*;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

public class PropertyUtils {
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
            System.out.println("config property loaded");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
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
            System.out.println("sql property loaded");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
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
