package pers.dlx.selfchat.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * 签证点评自定义验证凭据
 *
 * @author dinglingxiang
 */
public class MyUsernamePasswordToken extends UsernamePasswordToken {

    private static final long serialVersionUID = -2564928913725078138L;

    private LoginTypeEnum type;

    public MyUsernamePasswordToken() {
        super();
    }

    public MyUsernamePasswordToken(String username, String password, LoginTypeEnum type, boolean rememberMe,
                                   String host) {
        super(username, password, rememberMe, host);
        this.type = type;
    }

    /**
     * 免密登录
     */
    public MyUsernamePasswordToken(String username) {
        super(username, "", false, null);
        this.type = LoginTypeEnum.NOPASSWD;
    }

    /**
     * 账号密码登录
     */
    public MyUsernamePasswordToken(String username, String password) {
        super(username, password, false, null);
        this.type = LoginTypeEnum.PASSWORD;
    }

    public LoginTypeEnum getType() {
        return type;
    }

    public void setType(LoginTypeEnum type) {
        this.type = type;
    }
}
