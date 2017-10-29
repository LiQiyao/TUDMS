package com.nokia.tudms.dao;

import com.alibaba.druid.pool.DruidPooledConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author Rin
 * @Date 2017/4/25
 */
public class UserAccountDAO {

    public static int loginVerify(String userName,String password){
        int ret = -1;
        try {
            DruidPooledConnection connection = DBPool.getInstance().getConnection();
            PreparedStatement loginVerifyPs=connection.prepareStatement("SELECT * FROM user WHERE `username`=? AND `password`=?");
            loginVerifyPs.setString(1,userName);
            loginVerifyPs.setString(2,password);
            ResultSet rs = loginVerifyPs.executeQuery();
            if(rs.next()){
                ret = rs.getInt("uid");
            }
            rs.close();
            loginVerifyPs.close();
            connection.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return ret;
    }
    public static int levelQuery(int uid){
        int ret=0;
        try{
            DruidPooledConnection connection = DBPool.getInstance().getConnection();
            PreparedStatement levelQueryPs=connection.prepareStatement("SELECT `rank` FROM user WHERE `uid`=?");
            levelQueryPs.setInt(1,uid);
            ResultSet rs = levelQueryPs.executeQuery();
            if(rs.next()){
                ret=rs.getInt("rank");
            }
            rs.close();
            levelQueryPs.close();
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return ret;
    }
}
