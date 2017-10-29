package com.nokia.tudms.dao.search;

import com.alibaba.druid.pool.DruidPooledConnection;
import com.nokia.tudms.beans.search.SearchToolBean;
import com.nokia.tudms.beans.search.SearchToolBeanList;
import com.nokia.tudms.dao.DBPool;
import com.nokia.tudms.dao.usercenter.UserCenterInfoDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Created by Lee on 2017/4/27.
 * 搜索工具列表的DAO
 */
public class SearchToolBeanListDAO {
    private static Logger logger = Logger.getLogger(UserCenterInfoDAO.class.getName());

    private static DBPool dbPool = DBPool.getInstance();

    private static DruidPooledConnection connection  = null;

    public static SearchToolBeanList getSearchToolBeanList(String searchKey, String order){
        SearchToolBeanList searchToolBeanList = new SearchToolBeanList();
        try {
            connection = dbPool.getConnection();
            //查询tool表
            PreparedStatement pstm = null;
            if (order!= null && order.equals("zan-count")){
                pstm = connection.prepareStatement("SELECT * FROM tool WHERE `tool-name`LIKE ? OR `brief-intro` LIKE ? ORDER BY `zan-count` DESC");
            } else if (order!= null && order.equals("comment-count")){
                pstm = connection.prepareStatement("SELECT * FROM tool WHERE `tool-name`LIKE ? OR `brief-intro` LIKE ? ORDER BY `comment-count` DESC");
            } else {
                pstm = connection.prepareStatement("SELECT * FROM tool WHERE `tool-name`LIKE ? OR `brief-intro` LIKE ? ORDER BY `download-count` DESC");
            }

            pstm.setString(1, "%" + searchKey + "%");
            pstm.setString(2, "%" + searchKey + "%");
            ResultSet rs = pstm.executeQuery();
            SearchToolBean searchToolBean = null;
            while (rs.next()){
                searchToolBean = new SearchToolBean();
                ArrayList<String> tagList = new ArrayList<String>();
                ArrayList<String> versionList = new ArrayList<String>();

                searchToolBean.setToolId(rs.getInt(1));
                searchToolBean.setToolName(rs.getString(2));
                int uploaderUId = rs.getInt(3);
                searchToolBean.setUploaderUId(uploaderUId);

                PreparedStatement pstmName = connection.prepareStatement("SELECT username FROM user WHERE `uid`=?");
                pstmName.setInt(1, uploaderUId);
                ResultSet rsName = pstmName.executeQuery();
                if (rsName.next()){
                    searchToolBean.setUploaderName(rsName.getString(1));
                }
                searchToolBean.setBrefIntro(rs.getString(5));
                searchToolBean.setDownloadCount(rs.getInt(7));
                //查询点赞数和评论数
                searchToolBean.setZanCount(rs.getInt(8));
                searchToolBean.setCommentCount(rs.getInt(9));
                //查询tag表
                PreparedStatement pstmTag = connection.prepareStatement
                        ("SELECT `tag-name` FROM tag, tool_tag_relation WHERE tag.`tag-id`= tool_tag_relation.`tag-id` AND `tool-id`=?");
                pstmTag.setInt(1, searchToolBean.getToolId());
                ResultSet rsTag = pstmTag.executeQuery();
                while (rsTag.next()){
                    tagList.add(rsTag.getString(1));
                }
                searchToolBean.setTagList(tagList);
                //查询tool_file表
                PreparedStatement pstmFile = connection.prepareStatement
                        ("SELECT `version`, `upload-time`, `file-size` FROM tool_file WHERE `tool-id`=? ORDER BY `upload-time` DESC");
                pstmFile.setInt(1,searchToolBean.getToolId());
                System.out.println(searchToolBean.getToolId());
                ResultSet rsFile = pstmFile.executeQuery();
                boolean first = true;
                while (rsFile.next()){
                    if(first){
                        searchToolBean.setNowVersion(rsFile.getString(1));
                        searchToolBean.setUploadTime(rsFile.getInt(2));
                        searchToolBean.setFileSize(rsFile.getFloat(3));
                        first = false;
                    }
                    versionList.add(rsFile.getString(1));
                }
                searchToolBean.setVersionList(versionList);
                searchToolBeanList.addSearchToolBean(searchToolBean);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return searchToolBeanList;
    }

    public static int getCount(String tableName, int toolId){
        int count = 0;
        try{
            PreparedStatement pstm = connection.prepareStatement("SELECT COUNT(*) FROM "+ tableName + " WHERE `tool-id`=?");
            pstm.setInt(1, toolId);
            ResultSet rs = pstm.executeQuery();
            if(rs.next()){
                count = rs.getInt(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return count;
    }

    public static void main(String[] args) {
        SearchToolBean b = getSearchToolBeanList("工具", "download-count").getList().get(1);
        System.out.println("name " + b.getToolName());
        System.out.println("zan " + b.getZanCount());
        System.out.println("评论 " + b.getCommentCount());
        System.out.println("tag " + b.getTagList());
        System.out.println("当前版本" + b.getNowVersion());
        System.out.println("发布时间" + b.getUploadTime());
        System.out.println("所以版本" + b.getVersionList());
    }
}
