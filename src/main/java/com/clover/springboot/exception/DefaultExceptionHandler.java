package com.clover.springboot.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理类
 * 
 * @author zhangdq
 * @time 2018年3月16日 上午9:34:49
 * @Email qiang900714@126.com
 */
@ControllerAdvice
public class DefaultExceptionHandler {

	@ExceptionHandler(Exception.class)
	@ResponseBody
	public Map<String, Object> exceptionHandler(Exception e) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 500);
		map.put("msg", e.getMessage());
		return map;
	}
}
