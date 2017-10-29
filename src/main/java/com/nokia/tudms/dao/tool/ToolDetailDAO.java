package com.nokia.tudms.dao.tool;

import com.alibaba.druid.pool.DruidPooledConnection;
import com.nokia.tudms.beans.tool.ToolDetailBean;
import com.nokia.tudms.beans.tool.VersionBean;
import com.nokia.tudms.dao.DBPool;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @Author Rin
 * @Date 2017/4/30
 */
public class ToolDetailDAO {
    public static ToolDetailBean getToolDetail(int toolId){
        ToolDetailBean toolDetailBean = new ToolDetailBean();
        toolDetailBean.setToolId(toolId);
        try {
            DruidPooledConnection connection = DBPool.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT tool.*,category.`name` `category-name`,`user`.`username` FROM tool,category,`user` WHERE `tool-id`=? AND `tool`.`category`=category.id AND `user`.`uid`=`tool`.`uploader-uid`;");
            ps.setInt(1,toolId);
            ResultSet rs = ps.executeQuery();
            rs.next();
            toolDetailBean.setToolName(rs.getString("tool-name"));
            toolDetailBean.setCategoryId(rs.getInt("category"));
            toolDetailBean.setCategoryName(rs.getString("category-name"));
            toolDetailBean.setZanCount(rs.getInt("zan-count"));
            toolDetailBean.setCommentCount(rs.getInt("comment-count"));
            toolDetailBean.setDownloadCount(rs.getInt("download-count"));
            toolDetailBean.setBriefIntro(rs.getString("brief-intro"));
            toolDetailBean.setDescription(rs.getString("description"));
            toolDetailBean.setUploaderUid(rs.getInt("uploader-uid"));
            toolDetailBean.setUploaderName(rs.getString("username"));

            PreparedStatement psTag = connection.prepareStatement("SELECT tool_tag_relation.*,tag.`tag-name` FROM tool_tag_relation,tag WHERE `tool-id`=? AND tool_tag_relation.`tag-id`=tag.`tag-id`;");
            psTag.setInt(1,toolId);
            ResultSet rsTag = psTag.executeQuery();
            ArrayList<String> tagList = new ArrayList<String>();
            while (rsTag.next()){
                tagList.add(rsTag.getString("tag-name"));
            }
            toolDetailBean.setTagList(tagList);

            PreparedStatement psVersion = connection.prepareStatement("SELECT * FROM tool_file WHERE `tool-id`=? order by `file-id` desc;");
            psVersion.setInt(1,toolId);
            ResultSet rsVersion = psVersion.executeQuery();
            ArrayList<VersionBean> versionList = new ArrayList<VersionBean>();
            while (rsVersion.next()){
                VersionBean versionBean = new VersionBean(rsVersion.getInt("file-id"),
                        rsVersion.getString("version"),
                        rsVersion.getFloat("file-size"),
                        rsVersion.getInt("upload-time"),
                        rsVersion.getString("file-position"));
                versionList.add(versionBean);
            }
            toolDetailBean.setVersionList(versionList);

            rs.close();
            ps.close();
            rsTag.close();
            psTag.close();
            rsVersion.close();
            psVersion.close();
            connection.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
        return toolDetailBean;
    }
}
