package pers.dlx.selfchat.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pers.dlx.selfchat.shiro.MyUsernamePasswordToken;
import pers.dlx.selfchat.utils.SnowflakeIdWorker;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import springfox.documentation.annotations.ApiIgnore;

import static pers.dlx.selfchat.constants.Consts.ANONYMOUS_PREFIX;

@ApiIgnore
@Controller
public class LoginResource {

	private final static Logger logger = LoggerFactory.getLogger(LoginResource.class);

	/**
	 * 登录(用户名+密码)
	 * 
	 * @param dlzh
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> loginWithPassword(
			@RequestParam(value = "dlzh", required = true) String dlzh,
			@RequestParam(value = "password", required = true) String password) {
		Map<String, Object> map = new HashMap<String, Object>();
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(dlzh, password, true);

		try {
			subject.login(usernamePasswordToken);
			map.put("status", true);
			map.put("url", "/index");
			return map;
		} catch (IncorrectCredentialsException e) {
			map.put("status", false);
			map.put("url", "/login");
			map.put("message", "密码错误");
			return map;
		} catch (AuthenticationException e) {
			map.put("status", false);
			map.put("url", "/login");
			map.put("message", "账号错误");
			return map;
		}
	}


    /**
     * 匿名登录接口
     * @param response
     * @param request
     */
	@RequestMapping(value = "/bypasslogin", method = RequestMethod.GET)
	public void loginWithoutPasswordByGet(
			HttpServletResponse response, HttpServletRequest request) {
		Subject subject = SecurityUtils.getSubject();

		String loginAccount = ANONYMOUS_PREFIX + SnowflakeIdWorker.getNextId();

		// 免认证登录
		MyUsernamePasswordToken token = new MyUsernamePasswordToken(loginAccount);
		try {
			subject.login(token);
		} catch (Exception e) {
			logger.error("error: {}", e.getMessage(), e);
		}

		try {
			response.sendRedirect(request.getContextPath());
		} catch (IOException e) {
			logger.error("error: {}", e.getMessage(), e);
		}

	}

}
