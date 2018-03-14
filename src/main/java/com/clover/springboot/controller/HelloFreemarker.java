package com.clover.springboot.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * freemarker测试类
 * 
 * @author zhangdq
 * @time 2018年3月14日 下午4:31:59
 * @Email qiang900714@126.com
 */
@Controller
public class HelloFreemarker {
	
	@RequestMapping("hi")
	public String hello(Map<String, Object> map) {

		map.put("msg", "Hello Freemarker");
		return "hello";
	}
}
