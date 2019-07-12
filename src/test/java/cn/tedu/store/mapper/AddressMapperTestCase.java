package cn.tedu.store.mapper;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.Address;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressMapperTestCase {

	@Autowired
	AddressMapper mapper;
	
	@Test
	public void insert() {
		Address address = new Address();
		address.setUid(8);
		address.setName("Hello");
		address.setZip("AAAAAA");
		Integer rows = mapper.insert(address);
		System.err.println("rows=" + rows);
	}
	
	@Test
	public void deleteByAid() {
		Integer aid = 18;
		Integer rows = mapper.deleteByAid(aid);
		System.err.println("rows=" + rows);
	}
	
	@Test
	public void updateDefault() {
		Integer aid = 18;
		String modifiedUser = "系统管理员";
		Date modifiedTime = new Date();
		Integer rows = mapper.updateDefault(aid, modifiedUser, modifiedTime);
		System.err.println("rows=" + rows);
	}

	@Test
	public void updateNonDefault() {
		Integer uid = 11;
		Integer rows = mapper.updateNonDefault(uid);
		System.err.println("rows=" + rows);
	}
	
	@Test
	public void countByUid() {
		Integer uid = 8;
		Integer count = mapper.countByUid(uid);
		System.err.println("count=" + count);
	}
	
	@Test
	public void findByUid() {
		Integer uid = 11;
		List<Address> list = mapper.findByUid(uid);
		System.err.println("BEGIN:");
		for (Address item : list) {
			System.err.println(item);
		}
		System.err.println("END.");
	}
	
	@Test
	public void findByAid() {
		Integer aid = 1800;
		Address address = mapper.findByAid(aid);
		System.err.println(address);
	}
	
	@Test
	public void findLastModified() {
		Integer uid = 11;
		Address address = mapper.findLastModified(uid);
		System.err.println(address);
	}
	
}









