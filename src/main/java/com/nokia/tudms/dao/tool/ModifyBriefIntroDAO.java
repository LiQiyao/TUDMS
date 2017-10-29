package com.nokia.tudms.dao.tool;

import com.alibaba.druid.pool.DruidPooledConnection;
import com.nokia.tudms.dao.DBPool;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 用于更改简介
 * @Author Rin
 * @Date 2017/5/6
 */
public class ModifyBriefIntroDAO {
    public static void ModifyBriefIntro(int toolId,String content){
        try {
            DruidPooledConnection connection = DBPool.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement("UPDATE `tool` SET `brief-intro`=? WHERE `tool-id`=?;");
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
