//package com.clover.springboot.test.User;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.clover.model.User;
//import com.clover.springboot.dao.UserMapper;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class UserTest {
//	@Autowired
//	private UserMapper userMapper;
//
//	@Test
//	public void insertTest() {
//		User user = new User();
//		user.setAddr("河南郑州");
//		user.setId("2018");
//		user.setName("clover");
//		user.setSex("1");
//		
//		userMapper.insert(user);
//	}
//}
