package com.nokia.tudms.dao.search;

import com.alibaba.druid.pool.DruidPooledConnection;
import com.nokia.tudms.beans.search.AdvancedSearchInputBean;
import com.nokia.tudms.beans.search.SearchToolBean;
import com.nokia.tudms.beans.search.SearchToolBeanList;
import com.nokia.tudms.dao.DBPool;
import com.nokia.tudms.dao.usercenter.UserCenterInfoDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Created by Lee on 2017/4/29.
 * 高级搜索DAO
 */
public class AdvancedSearchDAO {
    public static Logger logger = Logger.getLogger(UserCenterInfoDAO.class.getName());

    public static DBPool dbPool = DBPool.getInstance();

    public static DruidPooledConnection connection  = null;

    public static SearchToolBeanList advancedSearch(AdvancedSearchInputBean asiBean){
        SearchToolBeanList searchToolBeanList = new SearchToolBeanList();
        try {
            connection = dbPool.getConnection();
            String sql = "SELECT DISTINCT tool.`tool-id` FROM tool, user, tag, tool_tag_relation, tool_file  WHERE tool.`uploader-uid`=user.`uid` AND " +
                    " tool.`tool-id`=tool_tag_relation.`tool-id` AND tag.`tag-id`=tool_tag_relation.`tag-id` AND " +
                    "tool.`tool-id`=tool_file.`tool-id`  ";
            if (!"".equals(asiBean.getToolNameKey())){
                sql += " AND tool.`tool-name` LIKE " + "'%" + asiBean.getToolNameKey() +"%' ";
            }
            if (!"".equals(asiBean.getUploaderName())){
                sql += " AND user.`username`=" + asiBean.getUploaderName();
            }
            if (asiBean.getUploaderTimeSt() != 0){
                sql += " AND tool_file.`upload-time`>=" + asiBean.getUploaderTimeSt();
            }
            if(asiBean.getUploaderTimeEn() != 0){
                sql += " AND tool_file.`upload-time`<="+ asiBean.getUploaderTimeEn();
            }
            if (!"".equals(asiBean.getTagName())){
                sql += " AND tag.`tag-name`=" + asiBean.getTagName();
            }
            if (!"".equals(asiBean.getCategory())){
                if (asiBean.getCategory() != 0){
                    sql += " AND tool.`category`=" + asiBean.getCategory();
                }
            }
            sql += " ORDER BY  tool.`download-count` DESC";
            logger.info(sql);
            PreparedStatement pstm  = connection.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()){
                SearchToolBean searchToolBean = new SearchToolBean();
                ArrayList<String> tagList = new ArrayList<String>();
                ArrayList<String> versionList = new ArrayList<String>();

                PreparedStatement pstm2 = connection.prepareStatement("SELECT * FROM tool WHERE `tool-id`=?");
                pstm2.setInt(1, rs.getInt(1));
                ResultSet rs2 = pstm2.executeQuery();
                if (rs2.next()){
                    searchToolBean.setToolId(rs2.getInt(1));
                    searchToolBean.setToolName(rs2.getString(2));
                    searchToolBean.setUploaderUId(rs2.getInt(3));
                    searchToolBean.setBrefIntro(rs2.getString(5));
                    searchToolBean.setDownloadCount(rs2.getInt(7));
                    //查询点赞数和评论数
                    searchToolBean.setZanCount(rs2.getInt(8));
                    searchToolBean.setCommentCount(rs2.getInt(9));
                }
                pstm2.close();
                rs2.close();
                //查询发布者名
                PreparedStatement pstmUploaderName = connection.prepareStatement("select `username`  from user where uid = ?");
                pstmUploaderName.setInt(1, searchToolBean.getUploaderUId());
                ResultSet rsUN = pstmUploaderName.executeQuery();
                if (rsUN.next()){
                    searchToolBean.setUploaderName(rsUN.getString("username"));
                }
                pstmUploaderName.close();
                rsUN.close();
                //查询标签
                PreparedStatement pstmTag = connection.prepareStatement
                        ("SELECT `tag-name` FROM tag, tool_tag_relation WHERE tag.`tag-id`= tool_tag_relation.`tag-id` AND `tool-id`=?");
                pstmTag.setInt(1, searchToolBean.getToolId());
                ResultSet rsTag = pstmTag.executeQuery();
                while (rsTag.next()){
                    tagList.add(rsTag.getString(1));
                }
                searchToolBean.setTagList(tagList);
                pstmTag.close();
                rsTag.close();
                //查询tool_file表
                PreparedStatement pstmFile = connection.prepareStatement
                        ("SELECT `version`, `upload-time`, `file-size` FROM tool_file WHERE `tool-id`=? ORDER BY `upload-time` ASC");
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
                pstmFile.close();
                rsFile.close();
                searchToolBean.setVersionList(versionList);
                searchToolBeanList.addSearchToolBean(searchToolBean);
            }
            pstm.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return searchToolBeanList;
    }

    public static void main(String[] args) {
        AdvancedSearchInputBean advancedSearchInputBean = new AdvancedSearchInputBean();
        advancedSearchInputBean.setCategory(1);
        advancedSearchInputBean.setToolNameKey("工具");
        advancedSearchInputBean.setUploaderName("'test1'");
        advancedSearchInputBean.setUploaderTimeSt(232);
        advancedSearchInputBean.setUploaderTimeEn(1999999999);
        advancedSearchInputBean.setTagName("'标签1'");

        ArrayList<SearchToolBean> a = advancedSearch(advancedSearchInputBean).getList();
        for (SearchToolBean b : a){
            System.out.println("name " + b.getToolName());
            System.out.println("zan " + b.getZanCount());
            System.out.println("评论 " + b.getCommentCount());
            System.out.println("tag " + b.getTagList());
            System.out.println("当前版本" + b.getNowVersion());
            System.out.println("发布时间" + b.getUploadTime());
            System.out.println("所以版本" + b.getVersionList());
        }

    }
}
