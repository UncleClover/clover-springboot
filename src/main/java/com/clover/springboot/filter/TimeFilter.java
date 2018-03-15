package com.clover.springboot.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TimeFilter implements Filter {
	private Logger logger = LoggerFactory.getLogger(TimeFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("初始化TimeFilter~");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// 开始时间
		Long startTime = System.currentTimeMillis();
		
		chain.doFilter(request, response);
		
		// 结束时间
		Long endTime = System.currentTimeMillis();
		logger.info("Filter耗时：" + (endTime - startTime));
	}

	@Override
	public void destroy() {
		logger.info("销毁TimeFilter~");
	}
}
