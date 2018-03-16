package com.clover.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常测试类
 * 
 * @author zhangdq
 * @time 2018年3月16日 上午9:37:55
 * @Email qiang900714@126.com
 */
@Controller
@RequestMapping("/exception")
public class ExceptionController {

	/**
	 * 异常测试
	 * 
	 * @author zhangdq
	 * @time 2018年3月16日 上午9:39:29
	 * @Email qiang900714@126.com
	 * @return
	 */
	@RequestMapping("/test")
	@ResponseBody
	public int exception() {
		int i = 1 / 0;
		return 1;
	}
}
