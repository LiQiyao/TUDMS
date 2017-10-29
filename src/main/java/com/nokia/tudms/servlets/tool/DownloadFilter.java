package com.nokia.tudms.servlets.tool;

import com.nokia.tudms.dao.tool.DownloadCounterDAO;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Author Rin
 * @Date 2017/5/5
 */
@WebFilter(filterName = "DownloadFilter",urlPatterns="/tools/*")
public class DownloadFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //TODO 用户鉴权，未严格鉴权
        HttpServletRequest hsr=(HttpServletRequest)req;
        String lastPage = hsr.getHeader("Referer");

        String[] refGroup=lastPage.split("tid=");
        String tidStr=refGroup[refGroup.length-1];
        int tid=Integer.parseInt(tidStr);
        DownloadCounterDAO.addDownloadCount(tid);

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
