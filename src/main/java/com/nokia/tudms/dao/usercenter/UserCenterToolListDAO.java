package com.nokia.tudms.dao.usercenter;

import com.alibaba.druid.pool.DruidPooledConnection;
import com.nokia.tudms.beans.usercenter.UserCenterTool;
import com.nokia.tudms.beans.usercenter.UserCenterToolList;
import com.nokia.tudms.dao.DBPool;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

/**
 * Created by Lee on 2017/4/26.
 * 用户中心 上传工具列表的DAO
 */
public class UserCenterToolListDAO {

    private static Logger logger = Logger.getLogger(UserCenterInfoDAO.class.getName());

    private static DBPool dbPool = DBPool.getInstance();

    private static DruidPooledConnection connection  = null;

    public static UserCenterToolList getUserCenterToolList(int uId){
        UserCenterToolList userCenterToolList = new UserCenterToolList();
        try {
            connection = dbPool.getConnection();
            PreparedStatement pstm = connection.prepareStatement
                    ("SELECT * FROM tool, tool_file WHERE `uploader-uid`=? AND tool.`newest-file-id`=tool_file.`file-id`");
            pstm.setInt(1, uId);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()){
                UserCenterTool tool = new UserCenterTool();
                tool.setToolId(rs.getInt(1));
                tool.setName(rs.getString(2));
                tool.setUploaderUId(rs.getInt(3));
                tool.setCategory(rs.getInt(4));
                tool.setBriefIntro(rs.getString(5));
                tool.setDescription(rs.getString(6));
                tool.setDownloadCount(rs.getInt(7));
                tool.setZanCount(rs.getInt(8));
                tool.setCommentCount(rs.getInt(9));
                int time = rs.getInt(14);
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                tool.setNewestVersionTime(format.format(new Date(time*1000L)));
                userCenterToolList.addUserCenterTool(tool);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return userCenterToolList;
    }

    public static void deleteUserCenterTool(int toolId){
        try {
            PreparedStatement pstm = connection.prepareStatement("DELETE FROM tool WHERE `tool-id`=?");
            pstm.setInt(1, toolId);
            pstm.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateUserCenterToolList(){
        //TODO
    }

    public static void main(String[] args) {
        getUserCenterToolList(1);
    }
}
