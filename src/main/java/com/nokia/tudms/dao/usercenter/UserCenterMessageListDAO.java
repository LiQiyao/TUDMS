package com.nokia.tudms.dao.usercenter;

import com.alibaba.druid.pool.DruidPooledConnection;
import com.nokia.tudms.beans.usercenter.UserCenterMessage;
import com.nokia.tudms.beans.usercenter.UserCenterMessageList;
import com.nokia.tudms.dao.DBPool;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

/**
 * Created by Lee on 2017/4/25.
 * 消息推送评论列表的DAO
 */
public class UserCenterMessageListDAO {
    private static Logger logger = Logger.getLogger(UserCenterInfoDAO.class.getName());

    private static DBPool dbPool = DBPool.getInstance();

    private static DruidPooledConnection connection  = null;

    public static UserCenterMessageList getUserCenterMessageList(int receiverId, int type){

        UserCenterMessageList list = new UserCenterMessageList();
        try {
            connection = dbPool.getConnection();
            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM message_push WHERE `receiver-uid`=? AND `type` IN (?,?,?) ORDER BY `sendtime` DESC ");
            pstm.setInt(1, receiverId);
            if (type == 0){
                pstm.setInt(2, 1);
                pstm.setInt(3, 2);
                pstm.setInt(4, 3);
            }else {
                pstm.setInt(2, type);
                pstm.setInt(3, type);
                pstm.setInt(4, type);
            }

            ResultSet rs = pstm.executeQuery();
            while (rs.next()){
                UserCenterMessage userCenterMessage = new UserCenterMessage();
                userCenterMessage.setMessageId(rs.getInt(1));
                userCenterMessage.setReceiverId(rs.getInt(2));
                userCenterMessage.setType(rs.getInt(3));
                userCenterMessage.setTitle(rs.getString(4));
                userCenterMessage.setContent(rs.getString(5));
                int time = rs.getInt(6);
                SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 hh:mm:ss");
                userCenterMessage.setSendTime(format.format(new Date(time * 1000L)));
                userCenterMessage.setLink(rs.getString(7));
                userCenterMessage.setHaveRead(rs.getInt(8));
                list.addMessage(userCenterMessage);
            }
            pstm = connection.prepareStatement("SELECT count(*) FROM message_push WHERE `have-read`=0 AND `receiver-uid`=?");
            pstm.setInt(1, receiverId);
            rs = pstm.executeQuery();
            if (rs.next()){
                list.setUnRead(rs.getInt(1));
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
        return list;
    }

    public static void main(String[] args) {
        getUserCenterMessageList(1, 1);
    }
}
