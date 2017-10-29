package com.nokia.tudms.dao.tool;

import com.alibaba.druid.pool.DruidPooledConnection;
import com.nokia.tudms.dao.DBPool;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @Author Rin
 * @Date 2017/5/5
 */
public class DownloadCounterDAO {
    public static void addDownloadCount(int toolId){
        try {
            DruidPooledConnection connection = DBPool.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement("update tool set `download-count`=`download-count`+1 where `tool-id`=?;");
            ps.setInt(1, toolId);
            ps.execute();
            ps.close();
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
