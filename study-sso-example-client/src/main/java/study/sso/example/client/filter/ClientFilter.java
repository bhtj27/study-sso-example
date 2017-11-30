package study.sso.example.client.filter;

import study.common.util.PropUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description :
 * @Author JunTao
 * @Date : 2017/11/28
 */
public abstract class ClientFilter implements Filter {

    //跳转到认证中心的地址
    protected String ssoServerUrl;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //拦截客户端所有请求
        excuteFilter((HttpServletRequest) request, (HttpServletResponse)response, chain);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ssoServerUrl = PropUtils.getProperty("sso.server.url");
        if (ssoServerUrl == null || ssoServerUrl.equals("")) {
            throw new IllegalArgumentException("sso.server.url is not empty");
        }
    }

    @Override
    public void destroy() {

    }

    public abstract void excuteFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException;
}
