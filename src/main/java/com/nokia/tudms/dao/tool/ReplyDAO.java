package com.nokia.tudms.dao.tool;

import com.alibaba.druid.pool.DruidPooledConnection;
import com.nokia.tudms.beans.tool.ReplyBean;
import com.nokia.tudms.dao.DBPool;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 该DAO用于回复问题级获取某一问题的回复
 * @Author Rin
 * @Date 2017/4/30
 */
public class ReplyDAO {
    public static ArrayList<ReplyBean> getReplyList(int askId){
        ArrayList<ReplyBean> replyList = new ArrayList<ReplyBean>();
        try {
            DruidPooledConnection connection = DBPool.getInstance().getConnection();
            PreparedStatement ps=connection.prepareStatement("SELECT reply.*,u1.`username` as `reply-sender-name`,u2.`username` as `reply-target-name` FROM reply,user u1,user u2 WHERE `ask-id`=? AND `reply-sender`=u1.uid AND `reply-target-uid`=u2.uid;");
            ps.setInt(1,askId);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                ReplyBean replyBean = new ReplyBean(rs.getInt("reply-id"),
                        rs.getInt("reply-sender"),
                        rs.getString("reply-sender-name"),
                        rs.getInt("ask-id"),
                        rs.getInt("reply-target-uid"),
                        rs.getString("reply-target-name"),
                        rs.getInt("reply-time"),
                        rs.getString("reply-content"));
                replyList.add(replyBean);
            }
            rs.close();
            ps.close();
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return replyList;
    }
    public static boolean setReply(int replySenderUid,int replyTargetUid,int askId,String content){
        boolean status = false;
        try {
            DruidPooledConnection connection = DBPool.getInstance().getConnection();
            PreparedStatement ps=connection.prepareStatement("INSERT INTO `reply` (`reply-sender`, `ask-id`, `reply-target-uid`, `reply-time`, `reply-content`) VALUES (?,?,?,?,?);");
            ps.setInt(1,replySenderUid);
            ps.setInt(2,askId);
            ps.setInt(3,replyTargetUid);
            ps.setInt(4,(int)(System.currentTimeMillis()/1000));
            ps.setString(5,content);
            ps.execute();
            status=true;

            ps.close();
            connection.close();

        } catch (SQLException e){
            e.printStackTrace();
        }

        return status;
    }
}
