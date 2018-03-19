//package com.clover.common;
//
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
//import org.springframework.context.annotation.Bean;
//import org.springframework.http.converter.HttpMessageConverter;
//
//import com.alibaba.fastjson.serializer.SerializerFeature;
//import com.alibaba.fastjson.support.config.FastJsonConfig;
//import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
//
///**
// * fastjson数据转换
// * 
// * @author zhangdq
// * @time 2018年3月14日 下午4:40:55
// * @Email qiang900714@126.com
// */
//@EnableAutoConfiguration
//public class WebConfig {
//	
//	@Bean
//	public HttpMessageConverters fastJsonHttpMessageConverters() {
//		FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
//
//		FastJsonConfig fastJsonConfig = new FastJsonConfig();
//		fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
//
//		fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
//
//		HttpMessageConverter<?> converter = fastJsonHttpMessageConverter;
//
//		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++");
//		
//		return new HttpMessageConverters(converter);
//	}
//}
