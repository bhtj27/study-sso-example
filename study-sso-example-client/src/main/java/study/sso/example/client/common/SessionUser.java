package study.sso.example.client.common;

import java.io.Serializable;

/**
 * @Description :
 * @Author JunTao
 * @Date : 2017/11/30
 */
public class SessionUser implements Serializable {
    private String token;
    private String username;

    public SessionUser() {

    }

    public SessionUser(String token, String username) {
        this.token = token;
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
