package study.sso.example.client.common;

import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description :
 * @Author JunTao
 * @Date : 2017/11/30
 */
public class SessionUtils {
    /**
     * 用户信息
     */
    public static final String SESSION_USER = "_sessionUser";


    public static SessionUser getSessionUser(HttpServletRequest request) {
        return (SessionUser) WebUtils.getSessionAttribute(request, SESSION_USER);
    }

    public static void setSessionUser(HttpServletRequest request, SessionUser sessionUser) {
        WebUtils.setSessionAttribute(request, SESSION_USER, sessionUser);
    }


    public static void invalidate(HttpServletRequest request){
        setSessionUser(request, null);
    }
}
