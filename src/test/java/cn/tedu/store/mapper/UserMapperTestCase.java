package cn.tedu.store.mapper;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTestCase {

	@Autowired
	public UserMapper mapper;
	
	@Test
	public void insert() {
		User user = new User();
		user.setUsername("root");
		user.setPassword("1234");
		Integer rows = mapper.insert(user);
		System.err.println("rows=" + rows);
	}
	
	@Test
	public void updateInfo() {
		User user = new User();
		user.setUid(14);
		user.setPhone("13800138014");
		user.setEmail("spring@tedu.cn");
		user.setGender(1);
		user.setModifiedUser("ROOT");
		user.setModifiedTime(new Date());
		Integer rows = mapper.updateInfo(user);
		System.err.println("rows=" + rows);
	}
	
	@Test
	public void updatePassword() {
		Integer uid = 12;
		String password = "1234";
		String modifiedUser = "超级管理员";
		Date modifiedTime = new Date();
		Integer rows = mapper.updatePassword(uid, password, modifiedUser, modifiedTime);
		System.err.println("rows=" + rows);
	}
	
	@Test
	public void updateAvatar() {
		Integer uid = 14;
		String avatar = "这里应该是头像的路径";
		String modifiedUser = "超级管理员";
		Date modifiedTime = new Date();
		Integer rows = mapper.updateAvatar(uid, avatar, modifiedUser, modifiedTime);
		System.err.println("rows=" + rows);
	}
	
	@Test
	public void findByUid() {
		Integer uid = 12;
		User user = mapper.findByUid(uid);
		System.err.println(user);
	}
	
	@Test
	public void findByUsername() {
		String username = "digest";
		User user = mapper.findByUsername(username);
		System.err.println(user);
	}

}








