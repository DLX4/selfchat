package pers.dlx.selfchat.utils;

public class BoolUtil {

    public static final String TRUE_STR = "1";
    public static final String FALSE_STR = "0";

    public static final int TRUE_INT = 1;
    public static final int FALSE_INT = 0;

    public static boolean isTrue(String str) {
        return TRUE_STR.equals(str);
    }

    public static boolean isTrue(int i) {
        return i == 1;
    }
}
