package pers.dlx.selfchat.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import pers.dlx.selfchat.model.core.Response;

/**
 * 进入controller方法之前的异常处理，如springMVC参数绑定解析错误（进入controller方法之后的异常由对应业务的controller本身处理）
 * 
 * @author dinglingxiang
 *
 */
@ControllerAdvice
@ResponseBody
public class WebExceptionHandler {

	private final static Logger logger = LoggerFactory.getLogger(WebExceptionHandler.class);

	@ExceptionHandler
	public Response invalidFormatException(InvalidFormatException e) {
		Response response = new Response();

		e.printStackTrace();
		logger.error("error: {}", e.getMessage(), e);
		response.getRequestResult().setSuccess(false);
		response.setContent("参数格式错误");
		return response;
	}
}
