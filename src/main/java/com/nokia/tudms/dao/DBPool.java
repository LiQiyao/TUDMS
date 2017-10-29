package com.nokia.tudms.dao;

import java.io.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

import com.alibaba.druid.pool.*;

public class DBPool {
    private static Logger logger = Logger.getLogger(DBPool.class.getName());
    private volatile static DBPool dbPool = null;
    private static DruidDataSource druidDataSource = null;
    static {
        Properties properties = loadPropertyFile("/jdbc.properties");
        try {
            druidDataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private DBPool() {}
    public static DBPool getInstance() {
        if (dbPool == null) {
            synchronized(DBPool.class){
                if (dbPool == null){
                    dbPool = new DBPool();
                }
            }
        }
        return dbPool;
    }
    public DruidPooledConnection getConnection() throws SQLException {
        return druidDataSource.getConnection();
    }
    public static Properties loadPropertyFile(String file) {
        if(file == null || file.equals("")){
            logger.warning("FileName can not be null");
        }
        Properties properties = new Properties();

        URL url = DBPool.class.getResource(file);
        System.out.println(url);
        InputStream in = DBPool.class.getResourceAsStream(file);
        if(in == null){
            logger.warning("File not find");
        }

        try {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}