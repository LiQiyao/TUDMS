package com.nokia.tudms.dao.tool;

import com.alibaba.druid.pool.DruidPooledConnection;
import com.nokia.tudms.dao.DBPool;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author Rin
 * @Date 2017/4/30
 */
public class ZanDAO {
    public static synchronized boolean dianZan(int uid,int toolId){
        boolean status=true;
        try {
            DruidPooledConnection connection = DBPool.getInstance().getConnection();
            PreparedStatement psCheck = connection.prepareStatement("SELECT * FROM zan WHERE `uid`=? AND `tool-id`=?");
            psCheck.setInt(1,uid);
            psCheck.setInt(2,toolId);
            ResultSet rsCheck = psCheck.executeQuery();
            if(!rsCheck.next()){ // 用户未曾点过赞
                status=true;
                PreparedStatement psDianZan = connection.prepareStatement("INSERT INTO `zan` (`uid`, `tool-id`) VALUES (?, ?);");
                psDianZan.setInt(1,uid);
                psDianZan.setInt(2,toolId);
                psDianZan.execute();

                PreparedStatement psAddZan = connection.prepareStatement("update tool set `zan-count`=`zan-count`+1 where `tool-id`=?;");
                psAddZan.setInt(1,toolId);
                psAddZan.execute();

                psDianZan.close();
                psAddZan.close();

            } else {
                status=false;
                PreparedStatement psRemoveZan = connection.prepareStatement("DELETE FROM `zan` WHERE `uid`=? and`tool-id`=?;");
                psRemoveZan.setInt(1,uid);
                psRemoveZan.setInt(2,toolId);
                psRemoveZan.execute();

                PreparedStatement psMinusZan = connection.prepareStatement("update tool set `zan-count`=`zan-count`-1 where `tool-id`=?;");
                psMinusZan.setInt(1,toolId);
                psMinusZan.execute();

                psRemoveZan.close();
                psMinusZan.close();
            }
            rsCheck.close();
            psCheck.close();
            connection.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
        return status;
    }
}
