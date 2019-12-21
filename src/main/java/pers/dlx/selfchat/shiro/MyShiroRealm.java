package pers.dlx.selfchat.shiro;

import pers.dlx.selfchat.constants.Consts;
import pers.dlx.selfchat.service.impl.LoginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;


public class MyShiroRealm extends AuthorizingRealm {

	@Autowired
	private LoginService loginService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		// 获取登录用户名
		String loginAccount = (String) principalCollection.getPrimaryPrincipal();
		// 查询用户名称
		UserInfo user = loginService.getUserByLoginAccount(loginAccount);
		if (user == null) {
			return null;
		}

		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

//		// 添加角色和权限
//		List<String> permissions = loginService.getPermissions(user);
//
//		if (!CollectionUtils.isEmpty(permissions)) {
//			simpleAuthorizationInfo.addStringPermissions(permissions);
//		}

		return simpleAuthorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
			throws AuthenticationException {
		if (authenticationToken.getPrincipal() == null) {
			return null;
		}
		// 获取用户信息
		String loginAccount = authenticationToken.getPrincipal().toString();
		UserInfo user = loginService.getUserByLoginAccount(loginAccount);
		if (user == null) {
			return null;
		}

		// md5($pass.$salt) salt(用户名)
		ByteSource credentialsSalt = ByteSource.Util.bytes(user);
		SecurityUtils.getSubject().getSession().setAttribute(Consts.LOGIN_USER_KEY, user);
		SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(loginAccount, user.getPassword(),
				credentialsSalt, getName());

		return simpleAuthenticationInfo;
	}

}
