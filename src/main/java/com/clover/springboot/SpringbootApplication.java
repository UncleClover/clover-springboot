package com.clover.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

/**
 * spring boot 程序入口
 * 
 * @author zhangdq
 * @time 2018年3月1日 上午11:22:50
 * @Email qiang900714@126.com
 */
@SpringBootApplication
public class SpringbootApplication extends WebMvcConfigurerAdapter{

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

		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++");

		return new HttpMessageConverters(converter);
	}
}