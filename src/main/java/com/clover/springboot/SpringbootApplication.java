package com.clover.springboot;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.clover.springboot.filter.TimeFilter;
import com.clover.springboot.interceptor.LoggerInterceptor;
import com.clover.springboot.listener.EventListener;
import com.clover.springboot.servlet.TipsServlet;

/**
 * spring boot 程序入口
 * 
 * @author zhangdq
 * @time 2018年3月1日 上午11:22:50
 * @Email qiang900714@126.com
 */
@SpringBootApplication
public class SpringbootApplication extends WebMvcConfigurerAdapter implements ServletContextInitializer {
	@Autowired
	private LoggerInterceptor loggerInterceptor;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}

	/**
	 * JSON转换
	 * 
	 * @author zhangdq
	 * @time 2018年3月15日 上午11:22:48
	 * @Email qiang900714@126.com
	 * @return
	 */
	@Bean
	public HttpMessageConverters fastJsonHttpMessageConverters() {
		FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();

		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);

		fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);

		HttpMessageConverter<?> converter = fastJsonHttpMessageConverter;

		return new HttpMessageConverters(converter);
	}

	/**
	 * 提示servlet注册
	 * 
	 * @author zhangdq
	 * @time 2018年3月15日 下午2:42:49
	 * @Email qiang900714@126.com
	 * @return
	 */
	@Bean
	public ServletRegistrationBean tipsServletRegister() {
		return new ServletRegistrationBean(new TipsServlet(), "/tips");
	}

	/**
	 * 时间过滤器
	 * 
	 * @author zhangdq
	 * @time 2018年3月15日 下午3:11:33
	 * @Email qiang900714@126.com
	 * @return
	 */
	@Bean
	public FilterRegistrationBean timeFilterRegister() {
		FilterRegistrationBean filter = new FilterRegistrationBean();
		TimeFilter timeFilter = new TimeFilter();
		filter.setFilter(timeFilter);

		List<String> urls = new ArrayList<>();
		urls.add("/tips");
		filter.setUrlPatterns(urls);

		return filter;
	}

	/**
	 * 事件监听器
	 * 
	 * @author zhangdq
	 * @time 2018年3月15日 下午5:19:40
	 * @Email qiang900714@126.com
	 * @return
	 */
	@Bean
	public ServletListenerRegistrationBean<EventListener> eventListenerRegister() {
		return new ServletListenerRegistrationBean<EventListener>(new EventListener());
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loggerInterceptor);

	}

	/**
	 * 自定义filter、servlet和listener配置方式
	 * 
	 * @author zhangdq
	 */
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		// servlet配置
		// servletContext.addServlet("tips", new
		// TipsServlet()).addMapping("/tips");

		// 过滤器
		// servletContext.addFilter("timeFilter", new
		// TimeFilter()).addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST),
		// true, "/*");

		// listener配置
		// servletContext.addListener(new EventListener());
	}
}