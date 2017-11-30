package study.sso.example.client.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.util.WebUtils;
import study.sso.example.client.common.SessionUser;
import study.sso.example.client.common.SessionUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description :
 * @Author JunTao
 * @Date : 2017/11/29
 */
public class SsoFilter extends ClientFilter{

    private static Logger logger = LoggerFactory.getLogger(SsoFilter.class);

    // sso授权回调参数token名称
    public static final String SSO_TOKEN_NAME = "__vt_param__";

    @Override
    public void excuteFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException,ServletException {

        String token = getLocalToken(request);//从session中取出token
        if (token == null) {
            if (getParameterToken(request) != null) {
                // 再跳转一次当前URL，以便去掉URL中token参数
                response.sendRedirect(request.getRequestURL().toString());
            }else{
                redirectLogin(request, response);
            }
        }else {
            chain.doFilter(request, response);
        }
    }

    //获取Session中token
    private String getLocalToken(HttpServletRequest request) {
        SessionUser sessionUser = SessionUtils.getSessionUser(request);
        return sessionUser == null ? null : sessionUser.getToken();
    }

    //获取服务端回传token参数且验证
    private String getParameterToken(HttpServletRequest request) throws IOException {
        String token = request.getParameter(SSO_TOKEN_NAME);
        if (token != null) {
            //根据token去全局session中验证是否用户已登录
            invokeAuthenticationInfoInSession(request,token,"abc");
            return token;
        }
        return null;
    }

    //保存认证信息到Session
    private void invokeAuthenticationInfoInSession(HttpServletRequest request, String token, String username) {
        SessionUtils.setSessionUser(request, new SessionUser(token, username));
    }

    //是否Ajax请求
    private boolean isAjaxRequest(HttpServletRequest request) {
        String requestedWith = request.getHeader("X-Requested-With");
        return requestedWith != null ? "XMLHttpRequest".equals(requestedWith) : false;
    }

    //跳转到认证服务器进行登录
    private void redirectLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        request.getSession().invalidate();
        String ssoLoginUrl = new StringBuilder().append(ssoServerUrl).append("?backUrl=")
                .append(request.getRequestURL()).toString();
        logger.debug("sendRedirectUrl={}",ssoLoginUrl);
        response.sendRedirect(ssoLoginUrl);
//        response.sendRedirect("http://www.baidu.com");
        return;
    }
}
