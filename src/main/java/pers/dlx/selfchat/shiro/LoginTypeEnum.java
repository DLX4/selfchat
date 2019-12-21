package pers.dlx.selfchat.shiro;

/**
 * 登录类型
 *
 * @author dinglingxiang
 */
public enum LoginTypeEnum {

    PASSWORD("password"), // 密码登录
    NOPASSWD("nopassword"); // 免密登录

    private String code;// 状态值

    private LoginTypeEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
