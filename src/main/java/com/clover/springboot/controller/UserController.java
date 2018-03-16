package com.clover.springboot.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.clover.model.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 用户控制类
 * 
 * @author zhangdq
 * @time 2018年3月14日 下午4:45:55
 * @Email qiang900714@126.com
 */
@Api(value = "user测试", tags = {"用户信息接口"})
@Controller
@RequestMapping("/user")
public class UserController {
	
	/**
	 * 查询用户信息
	 * 
	 * @author zhangdq
	 * @time 2018年3月14日 下午4:46:39
	 * @Email qiang900714@126.com
	 * @return
	 */
	@RequestMapping(value = "/query", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation("获取用户信息")
    @ApiImplicitParam(name = "name", value = "用户名", dataType = "string", paramType = "query")
	// 细颗粒解决跨域问题
	// @CrossOrigin(origins = "http://127.0.0.1:8080")
	public User getUserInfo(String name){
		User user = new User();
		user.setAddr("河南郑州");
		user.setName("UncleClover");
		user.setSex("boy");
		user.setAge(22);
		user.setBirthday(new Date());
		return user;
	}
}
