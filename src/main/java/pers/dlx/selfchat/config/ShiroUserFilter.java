package pers.dlx.selfchat.config;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.filter.authc.UserFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

public class ShiroUserFilter extends UserFilter {

	/**
	 * 在访问过来的时候检测是否为OPTIONS请求，如果是就直接返回true
	 */
	@Override
	protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		if (httpRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
			setHeader(httpRequest, httpResponse);
			return true;
		}
		return super.preHandle(request, response);
	}

	/**
	 * 该方法会在验证失败后调用，这里由于是前后端分离，后台不控制页面跳转 因此重写改成传输JSON数据
	 */
	@Override
	protected void saveRequestAndRedirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
		// super.saveRequestAndRedirectToLogin(request, response);
		saveRequest(request);
		setHeader((HttpServletRequest) request, (HttpServletResponse) response);
		WebUtils.toHttp(response).sendError(HttpServletResponse.SC_UNAUTHORIZED);
	}

	/**
	 * 为response设置header，实现跨域
	 */
	private void setHeader(HttpServletRequest request, HttpServletResponse response) {
		// 跨域的header设置
		response.setHeader("Access-control-Allow-Origin", request.getHeader("Origin"));
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"));
		// 防止乱码，适用于传输JSON数据
		response.setHeader("Content-Type", "application/json;charset=UTF-8");
		response.setStatus(HttpStatus.OK.value());
	}
}
