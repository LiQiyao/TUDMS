package com.nokia.tudms.dao.tool;

import com.alibaba.druid.pool.DruidPooledConnection;
import com.nokia.tudms.dao.DBPool;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 用于更改详细描述
 * @Author Rin
 * @Date 2017/5/6
 */
public class ModifyDescriptionDAO {
    public static void modifyDescription(int toolId,String content){
        try {
            DruidPooledConnection connection = DBPool.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement("UPDATE `tool` SET `description`=? WHERE `tool-id`=?;");
            ps.setString(1,content);
            ps.setInt(2,toolId);
            ps.execute();
            ps.close();
            connection.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
