package com.nokia.tudms.dao;

import com.alibaba.druid.pool.DruidPooledConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Administrator on 2017/4/24.
 */
public class DBTest {

    public static void main(String[] args) {
        DBPool dbPool = DBPool.getInstance();


        try {
            DruidPooledConnection connection = dbPool.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM `user` WHERE `self-intro`=\"a\"");
            while (rs.next()){
                System.out.println(rs.getString(2));
            }
            rs.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        System.out.println(connection);
    }

}