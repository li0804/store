package cn.tedu.store;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.DigestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageDigestTestCase {

	@Test
	public void md5() {
		String password = "TeduJSD1811BJ";
		String md5Password 
			= DigestUtils.md5DigestAsHex(
				password.getBytes());
		System.err.println(md5Password);
	}
	
	@Test
	public void uuid() {
		for (int i = 0; i < 10; i++) {
			String uuid = UUID.randomUUID().toString();
			System.err.println(uuid);
		}
	}
	
}









