package com.nokia.tudms.dao.admin;

import com.alibaba.druid.pool.DruidPooledConnection;
import com.nokia.tudms.dao.DBPool;
import com.nokia.tudms.utils.DeleteDirectory;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author Rin
 * @Date 2017/5/5
 */
public class DeleteToolDAO {
    public static boolean deleteTool(int toolId, String path){
        boolean status = false;
        String toolName = null;
        try {
            DruidPooledConnection connection = DBPool.getInstance().getConnection();
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement("SELECT `tool-name` FROM tool WHERE `tool-id`=?");
            ps.setInt(1, toolId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                toolName = rs.getString(1);
            }

            ps = connection.prepareStatement("DELETE FROM `tool` WHERE `tool-id`=?");
            ps.setInt(1, toolId);
            ps.execute();
            ps.close();
            ps = connection.prepareStatement("DELETE FROM tool_file WHERE `tool-id`=?");
            ps.setInt(1, toolId);
            ps.execute();
            ps.close();
            ps = connection.prepareStatement("DELETE FROM ask WHERE `tool-id`=?");
            ps.setInt(1, toolId);
            ps.execute();
            ps.close();
            ps = connection.prepareStatement("DELETE FROM attention WHERE `tool-id`=?");
            ps.setInt(1, toolId);
            ps.execute();
            ps.close();
            ps = connection.prepareStatement("DELETE FROM comment WHERE `tool-id`=?");
            ps.setInt(1, toolId);
            ps.execute();
            ps.close();
            ps = connection.prepareStatement("DELETE FROM ask WHERE `tool-id`=?");
            ps.setInt(1, toolId);
            ps.execute();
            ps.close();
            ps = connection.prepareStatement("DELETE FROM tool_tag_relation WHERE `tool-id`=?");
            ps.setInt(1, toolId);
            ps.execute();
            ps.close();
            ps = connection.prepareStatement("DELETE FROM zan WHERE `tool-id`=?");
            ps.setInt(1, toolId);
            ps.execute();
            ps.close();

            connection.commit();
            connection.setAutoCommit(true);
            ps.close();
            connection.close();

            File file = new File(path +"tools\\" + toolName);
            System.out.println(path +"tools\\" +toolName);
            if (file.exists()){
                System.out.println("!!");
                DeleteDirectory.deleteDir(file);
            }


            status=true;


        }catch (SQLException e){
            e.printStackTrace();
        }
        return status;
    }
}
