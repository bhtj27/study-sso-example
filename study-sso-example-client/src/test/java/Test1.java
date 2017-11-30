import org.junit.Test;
import study.common.util.PropUtils;

/**
 * @Description :
 * @Author JunTao
 * @Date : 2017/11/28
 */
public class Test1 {
    @Test
    public void test1(){
        String ssoServerUrl = PropUtils.getProperty("ssoServerUrl");
        System.out.println("url="+ssoServerUrl);
    }
}
