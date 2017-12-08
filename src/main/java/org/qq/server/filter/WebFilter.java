package org.qq.server.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dxx on 2017/12/8.
 */
public class WebFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        String servletPath = request.getServletPath();
        String pathInfo = request.getPathInfo();
        if (pathInfo.length() > 0) {
            servletPath = servletPath + pathInfo;
        }

        if ((null == userId || "".equals(userId)) && !servletPath.contains("/login.html")) {
            response.sendRedirect(request.getContextPath() + "/login.html");
            return;
        } else {
            chain.doFilter(request, response);
        }

    }

    public void destroy() {

    }

}
