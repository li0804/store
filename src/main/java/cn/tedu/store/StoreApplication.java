package cn.tedu.store;

import javax.servlet.MultipartConfigElement;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

@SpringBootApplication
@MapperScan("cn.tedu.store.mapper")
@Configuration
public class StoreApplication{

	public static void main(String[] args) {
		SpringApplication.run(StoreApplication.class, args);
	}
	
	@Bean
	public MultipartConfigElement getMultipartConfig() {
		MultipartConfigFactory factory
			= new MultipartConfigFactory();
		
		DataSize maxSize = DataSize.ofMegabytes(100L);
		factory.setMaxFileSize(maxSize);
		factory.setMaxRequestSize(maxSize);
		
		MultipartConfigElement element
			= factory.createMultipartConfig();
		
		return element;
	}

}





