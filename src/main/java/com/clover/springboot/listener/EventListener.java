package com.clover.springboot.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 自定义监听器
 * 
 * @author zhangdq
 * @time 2018年3月15日 下午3:52:57
 * @Email qiang900714@126.com
 */
public class EventListener implements ServletContextListener {
	private Logger logger = LoggerFactory.getLogger(EventListener.class);

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		logger.info("初始化监听器~");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		logger.info("销毁监听器~");
	}
}
