package pers.dlx.selfchat.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

/**
 * selfchat系统定制登录验证（支持密码校验和免密登录）
 *
 * @author dinglingxiang
 */
public class MyCredentialsMatcher extends HashedCredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        if (token instanceof MyUsernamePasswordToken) {
            MyUsernamePasswordToken tk = (MyUsernamePasswordToken) token;
            if (tk.getType().equals(LoginTypeEnum.NOPASSWD)) {
                return true;
            }
        }

        return super.doCredentialsMatch(token, info);
    }
}
