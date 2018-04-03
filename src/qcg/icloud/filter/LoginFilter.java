package qcg.icloud.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Author: qcg
 * @Description:
 * @Date: 2018/4/2 14:26
 */
public class LoginFilter implements Filter {

    /**
     * 需要被过滤的
     * 从web.xml中获取被过滤的url，是以","分割的
     */
    private String unCheckedServlet;
    private String checkedServlet;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        unCheckedServlet = filterConfig.getInitParameter("unCheckedServlet");
        checkedServlet = filterConfig.getInitParameter("checkedServlet");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        List<String> lists = Arrays.asList(checkedServlet.split(","));
        String urlPath = ((HttpServletRequest) servletRequest).getServletPath();
        //判断登录的地址是否在已有的lists中,如果存在，则验证，不存在，则放行
        if (lists.contains(urlPath)){
            String userName = (String)((HttpServletRequest) servletRequest).getSession().getAttribute("userName");
            if ("".equals(userName) || userName == null){
                ((HttpServletResponse)servletResponse).sendRedirect("/index.jsp");
                return;
            }
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
