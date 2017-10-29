package com.nokia.tudms.dao;

import com.alibaba.druid.pool.DruidPooledConnection;
import com.nokia.tudms.beans.*;
import com.nokia.tudms.beans.admin.ToolMgrBean;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @Author Rin
 * @Date 2017/4/25
 */
public class ToolDAO {
    public static ArrayList<IndexToolItemBean> getToolItemList(int type,int startPos,int num){
        ArrayList<IndexToolItemBean> toolItemList=new ArrayList<IndexToolItemBean>();
        try {
            DruidPooledConnection connection = DBPool.getInstance().getConnection();
            //获取0-index从startPos到startPos+num的全部工具信息
            PreparedStatement ps;
            if (type == 0) {
                ps = connection.prepareStatement("SELECT tool.`tool-id`,`tool-name`,`uploader-uid`,`category`,`brief-intro`,`download-count`,`zan-count`,`comment-count`,`newest-file-id`,`file-size` \n" +
                        "FROM tool,tool_file \n" +
                        "WHERE `file-id`=`newest-file-id` \n" +
                        "order by `download-count` desc limit ?,?;");
                ps.setInt(1,startPos);
                ps.setInt(2,num);
            } else {
                ps = connection.prepareStatement("SELECT tool.`tool-id`,`tool-name`,`uploader-uid`,`category`,`brief-intro`,`download-count`,`zan-count`,`comment-count`,`newest-file-id`,`file-size` \n" +
                        "FROM tool,tool_file \n" +
                        "WHERE `file-id`=`newest-file-id` AND `category`=? \n" +
                        "order by `download-count` desc limit ?,?;");
                ps.setInt(1,type);
                ps.setInt(2,startPos);
                ps.setInt(3,num);
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                IndexToolItemBean itemBean = new IndexToolItemBean(rs.getInt("tool-id"),
                        rs.getInt("category"),
                        rs.getString("tool-name"),
                        rs.getFloat("file-size"),
                        rs.getString("brief-intro"),
                        rs.getInt("zan-count"),
                        rs.getInt("download-count"),
                        rs.getInt("comment-count"));
                toolItemList.add(itemBean);
            }
            rs.close();
            ps.close();
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return toolItemList;
    }

    public static ArrayList<ToolMgrBean> getToolListMgr(){
        ArrayList<ToolMgrBean> toolList = new ArrayList<ToolMgrBean>();
        try {
            DruidPooledConnection connection = DBPool.getInstance().getConnection();
            String sql = "SELECT tool.`tool-id`,tool.`tool-name`,uid,username,category,category.name categoryName,version\n" +
                    "FROM tool,category,tool_file,user\n" +
                    "WHERE tool.category=category.id AND tool.`newest-file-id`=tool_file.`file-id` AND `uploader-uid`=user.uid\n" +
                    "ORDER BY `tool-id` ASC;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                ToolMgrBean toolMgrBean = new ToolMgrBean();
                toolMgrBean.setToolId(rs.getInt("tool-id"));
                toolMgrBean.setToolName(rs.getString("tool-name"));
                toolMgrBean.setUploaderId(rs.getInt("uid"));
                toolMgrBean.setUploaderName(rs.getString("username"));
                toolMgrBean.setCategoryId(rs.getInt("category"));
                toolMgrBean.setCategoryName(rs.getString("categoryName"));
                toolMgrBean.setVersion(rs.getString("version"));
                toolList.add(toolMgrBean);
            }
            rs.close();
            ps.close();
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return toolList;
    }
}
