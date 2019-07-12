package cn.tedu.store.conf;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import cn.tedu.store.interceptor.LoginInterceptor;

@Configuration
public class LoginInterceptorConfigurer 
	implements WebMvcConfigurer {

	@Override
	public void addInterceptors(
			InterceptorRegistry registry) {
		// 创建拦截器对象
		HandlerInterceptor interceptor
			= new LoginInterceptor();
		
		// 白名单，即：不要求登录即可访问的路径
		List<String> patterns = new ArrayList<>();
		patterns.add("/js/**");
		patterns.add("/css/**");
		patterns.add("/images/**");
		patterns.add("/bootstrap3/**");
		patterns.add("/web/register.html");
		patterns.add("/web/login.html");
		patterns.add("/web/index.html");
		patterns.add("/users/reg");
		patterns.add("/users/login");
		patterns.add("/districts/**");
		patterns.add("/goods/**");
		
		// 通过注册工具添加拦截器对象
		registry.addInterceptor(interceptor)
			.addPathPatterns("/**")
			.excludePathPatterns(patterns);
	}

}







