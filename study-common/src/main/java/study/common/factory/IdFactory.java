package study.common.factory;

import java.util.UUID;

/**
 * @Description :
 * @Author JunTao
 * @Date : 2017/11/29
 */
public final class IdFactory {

    public static String createUUID(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replaceAll("-", "");
    }
}
