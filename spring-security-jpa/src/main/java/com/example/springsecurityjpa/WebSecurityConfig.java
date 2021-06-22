package com.example.springsecurityjpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.springsecurityjpa.user.UserService;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserService userService;

	// loại encode của password
	@Bean
	public PasswordEncoder passwordEncoder() {
		// de Spring Security su dung ma hoa mat khau cua user
		return new BCryptPasswordEncoder();
	}

	// Cung cấp thông tin của User để spring security tiến hành xác thực
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userService) // cung cap UserService cho spring security (lấy thông tin user từ Db để compare
			.passwordEncoder(passwordEncoder()); // cung cap password encoder
		
	}
	
	// config những page được phép truy cập mà không cần login hoặc ngược lại, chỉ định form login...bla bla
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http	
			.authorizeRequests().antMatchers("/","/home","/h2-console/**").permitAll()
			.anyRequest().authenticated()
			.and()
			.formLogin() // cho phep user su dung form login de dang nhap (dang su dung trang login default cua spring security)
			.defaultSuccessUrl("/hello") // url duoc forward khi login success
			.permitAll()
			.and()
			.logout()
			.permitAll();
		
		http.csrf().disable(); // disable co che crsf de co the access vao h2 tren trinh duyet
        http.headers().frameOptions().disable();
	}
}
