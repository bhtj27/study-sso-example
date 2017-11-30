package study.sso.example.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import study.common.util.PropUtils;

/**
 * @Description :
 * @Author JunTao
 * @Date : 2017/11/28
 */
public class TestController {

    private static Logger logger = LoggerFactory.getLogger(TestController.class);

    public static void main(String[] args){
        String ssoServerUrl = PropUtils.getProperty("ssoServerUrl");
        System.out.println("url="+ssoServerUrl);
    }
}
