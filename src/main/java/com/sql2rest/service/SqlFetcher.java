package com.sql2rest.service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.sql2rest.db.connection.DBConnectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.*;

@Service
public class SqlFetcher {
    private static final Logger logger = LoggerFactory.getLogger(SqlFetcher.class);

    public String fetch(String query) {

        Connection connection = null;
        try {
            connection = DBConnectionUtil.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            logger.info("Executing query.");
            ResultSet resultSet = preparedStatement.executeQuery();
            logger.info("Query executed.");
            JsonArray jsonArray = new JsonArray();
            while (resultSet.next()){
                ResultSetMetaData metaData = resultSet.getMetaData();
                JsonObject jsonObject = new JsonObject();
                for(int i = 0; i < metaData.getColumnCount(); i++){
                    jsonObject.add(metaData.getColumnLabel(i+1), new Gson().toJsonTree(resultSet.getObject(i+1)));
                }
                jsonArray.add(jsonObject);
            }
            return jsonArray.toString();
        } catch (SQLException e) {
            logger.error("Unable to execute the query- {}", query, e);
            return e.getMessage();
        } finally {
            DBConnectionUtil.close(connection);
        }

    }

}
