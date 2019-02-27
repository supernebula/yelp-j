package evol.security;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * MD5工具类
 */
public class MD5Util {

    /**
     * 对文本进行MD5加密
     * @param text
     * @return
     */
    public static String MD5(String text){
        String encodeStr = DigestUtils.md5Hex(text);
        return encodeStr;
    }

    /**
     * 对文本附加盐，进行MD5加密
     * @param text
     * @param salt
     * @return
     */
    public static String MD5(String text, String salt){
        String encodeStr = DigestUtils.md5Hex(text + salt);
        return encodeStr;
    }
}
