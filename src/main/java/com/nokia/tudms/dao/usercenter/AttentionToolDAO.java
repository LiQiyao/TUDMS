package com.nokia.tudms.dao.usercenter;

import com.alibaba.druid.pool.DruidPooledConnection;
import com.nokia.tudms.beans.usercenter.AttentionTool;
import com.nokia.tudms.dao.DBPool;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

/**
 * Created by Lee on 2017/5/4.
 * 关注工具的DAO
 */
public class AttentionToolDAO {
    private static Logger logger = Logger.getLogger(UserCenterInfoDAO.class.getName());

    private static DBPool dbPool = DBPool.getInstance();

    private static DruidPooledConnection connection  = null;

    public static ArrayList<AttentionTool> queryAttentionTool(int uId){
        ArrayList<AttentionTool> list = new ArrayList<AttentionTool>();
        try {
            connection = dbPool.getConnection();
            PreparedStatement pstm = connection.prepareStatement
                    ("SELECT tool.`tool-id`,tool.`tool-name`,tool_file.`version`,tool_file.`upload-time`,tool.`newest-file-id`  FROM attention,tool,tool_file WHERE attention.`tool-id`=tool.`tool-id` AND tool.`newest-file-id`=tool_file.`file-id` AND attention.`uid`=?");
            pstm.setInt(1, uId);
            ResultSet rs = pstm.executeQuery();
            AttentionTool attentionTool = null;
            while (rs.next()){
                attentionTool = new AttentionTool();
                attentionTool.setToolId(rs.getInt(1));
                attentionTool.setToolName(rs.getString(2));
                attentionTool.setNewestVersion(rs.getString(3));
                int time = rs.getInt(4);
                SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 hh时mm分ss秒");
                attentionTool.setNewestVersionTime(format.format(new Date(time * 1000L)));
                attentionTool.setNewestVersionId(rs.getInt(5));
                list.add(attentionTool);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return list;
    }

    public static int cancelAttention(int uId, int toolId){
        int remain = -1;
        try {
            connection = dbPool.getConnection();
            PreparedStatement pstm = connection.prepareStatement("DELETE FROM attention WHERE `tool-id`=? AND `uid`=?");
            pstm.setInt(1, toolId);
            pstm.setInt(2, uId);
            pstm.execute();
            pstm = connection.prepareStatement("SELECT COUNT(*) FROM attention WHERE `uid`=?");
            pstm.setInt(1, uId);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()){
                remain = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return remain;
    }
}
