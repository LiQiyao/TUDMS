package com.nokia.tudms.dao.admin;

import com.alibaba.druid.pool.DruidPooledConnection;
import com.nokia.tudms.dao.DBPool;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author Rin
 * @Date 2017/5/2
 */
public class UsernameUidQueryDAO {
    public static int getUidByUserName(String userName){
        int uid = -1;
        try{
            DruidPooledConnection connection = DBPool.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT uid FROM user WHERE `username`=?;");
            ps.setString(1,userName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                uid=rs.getInt(1);
            }
            rs.close();
            ps.close();
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return uid;
    }
}
