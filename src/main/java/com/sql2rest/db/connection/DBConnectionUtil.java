package com.sql2rest.db.connection;

import com.sql2rest.util.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionUtil {
    private static final Logger logger = LoggerFactory.getLogger(DBConnectionUtil.class);

    private static final String DRIVER = PropertyUtils.getProperty("jdbc.driver");
    private static final String HOST = PropertyUtils.getProperty("db.server");
    private static final String DB = PropertyUtils.getProperty("db.name");
    private static final String PORT = PropertyUtils.getProperty("db.port");
    private static final String USER_NAME = PropertyUtils.getProperty("db.user");
    private static final String PASSWORD = PropertyUtils.getProperty("db.password");


    static {
        try {
            Class.forName(DRIVER);
            logger.info("Driver Registered!");
        } catch (ClassNotFoundException e) {
            logger.error("Where is your Driver?", e);
        }
    }

    public static Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://"+ HOST +":"+ PORT +"/"+ DB, USER_NAME, PASSWORD);
            connection.setReadOnly(true);
            return  connection;
        } catch (SQLException e) {
            logger.error("Unable to get the connection from database", e);
            return null;
        }
    }

    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("Unable to close the database connection", e);
            }
        }
    }

}

