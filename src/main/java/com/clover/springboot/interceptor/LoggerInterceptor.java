package com.clover.springboot.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 日志拦截器
 * 
 * @author zhangdq
 * @time 2018年3月15日 下午4:58:22
 * @Email qiang900714@126.com
 */
@Component
public class LoggerInterceptor implements HandlerInterceptor {
	private Logger logger = LoggerFactory.getLogger(LoggerInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		logger.info("拦截器处理前~");
		logger.info(((HandlerMethod) handler).getBean().getClass().getName());
		logger.info(((HandlerMethod) handler).getMethod().getName());

		request.setAttribute("startTime", System.currentTimeMillis());

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		logger.info("========postHandle=========");
		Long start = (Long) request.getAttribute("startTime");
		logger.info("耗时:" + (System.currentTimeMillis() - start));
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		logger.info("========afterCompletion=========");
		Long start = (Long) request.getAttribute("startTime");
		logger.info("耗时:" + (System.currentTimeMillis() - start));

		logger.info(ex + "");
	}
}
