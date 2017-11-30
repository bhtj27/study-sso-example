package study.common.util;

import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description :
 * @Author JunTao
 * @Date : 2017/11/30
 */
public class CookieUtils {

    //按名称获取cookie
    public static String getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null || StringUtils.isEmpty(name)) {
            return null;
        }

        for (Cookie cookie : cookies) {
            if (name.equals(cookie.getName())) {
                return cookie.getValue();
            }
        }

        return null;
    }

    //向response中添加cookile
    public static void addCookie(HttpServletRequest request,HttpServletResponse response,Cookie cookie){
        cookie.setPath("/");
        if ("https".equals(request.getScheme())) {
            cookie.setSecure(true);
        }
        cookie.setHttpOnly(true);//JavaScript脚本将无法读取到Cookie信息，这样能有效的防止XSS攻击
        response.addCookie(cookie);
    }

    //清除cookie
    public static void removeCookie(HttpServletResponse response, String name, String path, String domain) {

        Cookie cookie = new Cookie(name, null);

        if (path != null) {
            cookie.setPath(path);
        }

        if (domain != null) {
            cookie.setDomain(domain);
        }

        cookie.setMaxAge(-1000);
        response.addCookie(cookie);
    }
}
