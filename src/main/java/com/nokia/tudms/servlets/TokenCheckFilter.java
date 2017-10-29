package com.nokia.tudms.servlets;

import com.nokia.tudms.beans.LoggedUser;
import com.nokia.tudms.utils.TokenManager;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Token检查过滤器用于过滤所有请求，检查cookie中是否存在Token，并校验信息，装载入request的attribute中，供下一层的servlet或jsp获取
 * @Author Rin
 * @Date 2017/4/24
 */
@WebFilter(filterName = "TokenCheckFilter",urlPatterns="/*")
public class TokenCheckFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        Cookie [] allCookies=((HttpServletRequest)req).getCookies();
        if(allCookies!=null) {
            for (Cookie cookie : allCookies) {
                if (cookie.getName().equals("token")) {
                    LoggedUser loggedUserBean = TokenManager.getInstance().findUser(cookie.getValue());
                    req.setAttribute("loggedUserBean", loggedUserBean);
                }
            }
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
