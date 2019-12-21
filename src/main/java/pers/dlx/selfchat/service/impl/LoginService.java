package pers.dlx.selfchat.service.impl;

import org.springframework.stereotype.Service;
import pers.dlx.selfchat.shiro.UserInfo;

/**
 * 用户登录业务层
 *
 * @author dinglingxiang
 */
@Service
public class LoginService {

    /**
     * 通过登录账号获取用户信息
     *
     * @param loginAccount 登录账号
     * @return
     */
    public UserInfo getUserByLoginAccount(String loginAccount) {
        return null;
    }
}
