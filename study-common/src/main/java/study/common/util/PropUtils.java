package study.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Description :读取study.properties配置文件
 * @Author JunTao
 * @Date : 2017/11/28
 */
public class PropUtils {
    private static Logger logger= LoggerFactory.getLogger(PropUtils.class);
    private static Properties props;

    static{
        loadProps();
    }

    private static void loadProps(){
        logger.debug("load study.properties start...");
        props = new Properties();
        InputStream in = null;
        try{
            in = PropUtils.class.getClassLoader().getResourceAsStream("study.properties");
            props.load(in);
        }catch (FileNotFoundException e ){
            logger.error("not find study.properties...");
            throw new RuntimeException(e);
        }catch (IOException e1){
            logger.error("load study.properties error",e1);
        }finally {
            try {
                if(null != in) {
                    in.close();
                }
            } catch (IOException e2) {
                logger.error("close study.properties error",e2);
            }
        }
        logger.debug("load study.properties success,key-value:{}",props);
    }

    public static String getProperty(String key){
        if(null == props) {
            loadProps();
        }
        return props.getProperty(key);
    }

    public static String getProperty(String key, String defaultValue) {
        if(null == props) {
            loadProps();
        }
        return props.getProperty(key, defaultValue);
    }
}
