package com.nokia.tudms.dao.tool;

import com.alibaba.druid.pool.DruidPooledConnection;
import com.nokia.tudms.dao.DBPool;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author Rin
 * @Date 2017/5/2
 */
public class AttentionDAO {
    public static synchronized boolean changeAttentionStatus(int uid,int toolId){
        boolean status=true;
        try {
            DruidPooledConnection connection = DBPool.getInstance().getConnection();
            PreparedStatement psCheck = connection.prepareStatement("SELECT * FROM attention WHERE `uid`=? AND `tool-id`=?;");
            psCheck.setInt(1,uid);
            psCheck.setInt(2,toolId);
            ResultSet rsCheck = psCheck.executeQuery();
            if(!rsCheck.next()){ // 用户未曾关注
                status=true;
                PreparedStatement psAddAttention = connection.prepareStatement("INSERT INTO `attention` (`uid`, `tool-id`) VALUES (?, ?);");
                psAddAttention.setInt(1,uid);
                psAddAttention.setInt(2,toolId);
                psAddAttention.execute();
                psAddAttention.close();

            } else {
                status=false;
                PreparedStatement psRemoveAttention = connection.prepareStatement("DELETE FROM `attention` WHERE `uid`=? and`tool-id`=?;");
                psRemoveAttention.setInt(1,uid);
                psRemoveAttention.setInt(2,toolId);
                psRemoveAttention.execute();

                psRemoveAttention.close();
            }
            rsCheck.close();
            psCheck.close();
            connection.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
        return status;
    }
    public static boolean getAttentionStatus(int uid,int toolId){
        boolean status=false;
        try {
            DruidPooledConnection connection = DBPool.getInstance().getConnection();
            PreparedStatement psCheck = connection.prepareStatement("SELECT * FROM attention WHERE `uid`=? AND `tool-id`=?;");
            psCheck.setInt(1, uid);
            psCheck.setInt(2, toolId);
            ResultSet rsCheck = psCheck.executeQuery();
            if (rsCheck.next()) { // 用户关注
                status=true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return status;
    }
}
