package com.clover.springboot;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.clover.springboot.filter.TimeFilter;
import com.clover.springboot.interceptor.LoggerInterceptor;
import com.clover.springboot.listener.EventListener;
import com.clover.springboot.servlet.TipsServlet;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * spring boot 程序入口
 * 
 * @author zhangdq
 * @time 2018年3月1日 上午11:22:50
 * @Email qiang900714@126.com
 */
@SpringBootApplication
@EnableSwagger2
public class SpringbootApplication extends WebMvcConfigurerAdapter implements ServletContextInitializer {
	@Autowired
	private LoggerInterceptor loggerInterceptor;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}

//	
//	@Bean
//	@ConditionalOnMissingBean
//	public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) {
//		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//		sqlSessionFactoryBean.setDataSource(dataSource);
//
//		ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
//		Resource mybatisConfigXml = resourcePatternResolver.getResource("classpath:mybatis/mybatis-config.xml");
//		sqlSessionFactoryBean.setConfigLocation(mybatisConfigXml);
//
//		// 设置mapper映射文件
//		Resource[] mapperXml;
//		try {
//			mapperXml = resourcePatternResolver.getResources("classpath:mybatis/mapper/*.xml");
//			sqlSessionFactoryBean.setMapperLocations(mapperXml);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		return sqlSessionFactoryBean;
//	}
	
	@Bean
	@ConditionalOnBean(SqlSessionFactoryBean.class)
	public MapperScannerConfigurer mapperScannerConfigurer() {
		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
		mapperScannerConfigurer.setBasePackage("com.clover.springboot.dao");
		return mapperScannerConfigurer;
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

	/**
	 * 解决跨域问题1
	 * @author zhangdq
	 * @time 2018年3月16日 上午11:20:19
	 * @Email qiang900714@126.com
	 * @return
	 */
//	@Override
//	public void addCorsMappings(CorsRegistry registry) {
//		registry.addMapping("user/**").allowedOrigins("http://127.0.0.1:8080");
//	}

	/**
	 * 解决跨域问题2
	 * @author zhangdq
	 * @time 2018年3月16日 上午11:20:17
	 * @Email qiang900714@126.com
	 * @return
	 */
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("user/**").allowedOrigins("http://127.0.0.1:8080");
			}
		};
	}

	/**
	 * swagger2
	 * 
	 * @author zhangdq
	 * @time 2018年3月16日 上午11:31:20
	 * @Email qiang900714@126.com
	 * @return
	 */
	@Bean
	public Docket accessToken() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("api")// 定义组
				.select() // 选择那些路径和 api 会生成 document
				.apis(RequestHandlerSelectors.basePackage("com.clover.springboot.controller")) // 拦截的包路径
				.paths(PathSelectors.regex("/*/.*"))// 拦截的接口路径
				.build() // 创建
				.apiInfo(apiInfo()); // 配置说明
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()//
				.title("spring boot")// 标题
				.description("spring boot")// 描述
				.termsOfServiceUrl("https://github.com/UncleClover")//
				.contact(new Contact("UncleClover", "https://github.com/UncleClover", "qiang900714@126.com"))// 联系
				.version("1.0")// 版本
				.build();
	}
}