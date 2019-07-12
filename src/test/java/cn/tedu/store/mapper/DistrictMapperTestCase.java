package cn.tedu.store.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.District;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DistrictMapperTestCase {
	
	@Autowired
	DistrictMapper mapper;
	
	@Test
	public void findByParent() {
		List<District> list 
			= mapper.findByParent("");
		System.err.println("BEGIN:");
		for (District item : list) {
			System.err.println(item);
		}
		System.err.println("END.");
	}

	@Test
	public void findByCode() {
		String code = "110000";
		District data = mapper.findByCode(code);
		System.err.println(data);
	}

}
