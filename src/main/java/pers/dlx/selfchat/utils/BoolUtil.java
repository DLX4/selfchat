package pers.dlx.selfchat.utils;

public class BoolUtil {

    public static final String TRUE_STR = "1";
    public static final String FALSE_STR = "0";

    /**
     * 字符串和布尔值的转换规则："0":false "1":true
     *
     * @param str
     * @return
     */
    public static boolean isTrue(String str) {
        return TRUE_STR.equals(str);
    }
}
