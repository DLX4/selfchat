package pers.dlx.selfchat.utils;

import java.util.Optional;

import pers.dlx.selfchat.constants.Consts;
import pers.dlx.selfchat.shiro.UserInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

public class LoginUtils {

	/**
	 * 从session取用户信息
	 * 
	 * @return
	 */
	public static UserInfo sessionUser() {
        UserInfo user = new UserInfo();
		Session session = SecurityUtils.getSubject().getSession();
		user = (UserInfo) session.getAttribute(Consts.LOGIN_USER_KEY);
		if (user == null) {
			SecurityUtils.getSubject().logout();
		}
		return user;
	}

	/**
	 * 返回sessionID
	 * 
	 * @return
	 */
	public static String getSessionId() {
		Session session = SecurityUtils.getSubject().getSession();
		return session.getId() + "";
	}

}
