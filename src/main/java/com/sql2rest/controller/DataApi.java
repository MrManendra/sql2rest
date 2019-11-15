package com.sql2rest.controller;

import com.sql2rest.service.SqlFetcher;
import com.sql2rest.util.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

import static spark.Spark.get;
import static spark.Spark.port;

@Service
public class DataApi {
    private static final Logger logger  = LoggerFactory.getLogger(DataApi.class);

    @Autowired private SqlFetcher sqlFetcher;

    public void sparkRest(){
        try{
            port(Integer.parseInt(PropertyUtils.getProperty("server.port")));
        }catch (Exception e){
            throw new RuntimeException("server.port is not configured correctly", e);
        }

        get("/api/health/check", (req, res)->"Fine");

        get("/api/reloadProperties", (req, res)->{
            PropertyUtils.reloadProperties();
            sql2Rest();
            return "reloaded successfully!";
        });


        sql2Rest();
    }

    private void sql2Rest() {
        for(String key : PropertyUtils.getSqlKeys()) {
            get("/api/"+key, (req, res) -> {
                res.type("application/json");

                String query = PropertyUtils.getSqlProperty(key);
                System.out.println("query name="+key+", query value from file="+query);
                Set<String> queryParamKeys = req.queryParams();
                for (String queryParamKey : queryParamKeys) {
                    query = query.replace(":" + queryParamKey, req.queryParams(queryParamKey));
                }

                System.out.println("final query=" + query);

                String result = sqlFetcher.fetch(query);
                return result;
            });
        }
    }
}
