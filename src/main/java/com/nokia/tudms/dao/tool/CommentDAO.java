package com.nokia.tudms.dao.tool;

import com.alibaba.druid.pool.DruidPooledConnection;
import com.nokia.tudms.beans.tool.CommentBean;
import com.nokia.tudms.dao.DBPool;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @Author Rin
 * @Date 2017/4/29
 */
public class CommentDAO {
    public static int getNum(int toolId){
        int num=-1;
        try {
            DruidPooledConnection connection = DBPool.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT COUNT(*) FROM comment WHERE `tool-id`=?;");
            ps.setInt(1,toolId);
            ResultSet rs = ps.executeQuery();
            rs.next();
            num=rs.getInt(1);

            rs.close();
            ps.close();
            connection.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
        return num;
    }
    public static ArrayList<CommentBean> getComments(int toolId,int startPos,int num){
        ArrayList<CommentBean> commentList=new ArrayList<CommentBean>();
        try {
            DruidPooledConnection connection = DBPool.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT comment.*,user.`username` FROM comment,user WHERE `tool-id`=? AND `sender-uid`=`uid` ORDER BY `comment-id` desc LIMIT ?,?;");
            ps.setInt(1,toolId);
            ps.setInt(2,startPos);
            ps.setInt(3,num);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                CommentBean commentBean = new CommentBean(rs.getInt("comment-id"),
                        rs.getInt("sender-uid"),
                        rs.getString("username"),
                        rs.getString("content"),
                        rs.getInt("comment-time"));
                commentList.add(commentBean);
            }

            rs.close();
            ps.close();
            connection.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
        return commentList;
    }
    public static boolean addComment(int toolId,int senderUid,String content){
        boolean status=false;
        try {
            DruidPooledConnection connection = DBPool.getInstance().getConnection();
            PreparedStatement ps=connection.prepareStatement("INSERT INTO `comment` (`sender-uid`, `tool-id`, `content`, `comment-time`) VALUES (?, ?, ?, ?);");
            ps.setInt(1,senderUid);
            ps.setInt(2,toolId);
            ps.setString(3,content);
            ps.setInt(4,(int)(System.currentTimeMillis()/1000));
            ps.execute();
            status=true;

            PreparedStatement psAddOne = connection.prepareStatement("update tool set `comment-count`=`comment-count`+1 where `tool-id`=?;");
            psAddOne.setInt(1,toolId);
            psAddOne.execute();

            ps.close();
            psAddOne.close();
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return status;
    }
}
