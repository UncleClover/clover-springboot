package com.clover.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * srpingboot测试类
 * 
 * @author zhangdq
 * @time 2018年3月1日 上午11:04:10
 * @Email qiang900714@126.com
 */

@RestController
public class HelloController {

	@GetMapping("/hello")
	public String index() {
		return "hello world";
	}
}
