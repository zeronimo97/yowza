package com.abc.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * 스프링 시큐리티 관련 설정을 하는 클래스
 * */

@Configuration // 설정하는 클래스입니다.
public class WebSecurityConfig {
	
	@Autowired
	private DataSource dataSource;
	
	
	/**
	 * filter : 어떤 기능을 위한 전처리 / 후처리
	 * interceptor : 
	 * */
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity hs) throws Exception {
		hs.csrf().disable() // CSRF 인증방법 무효
		.authorizeRequests() // 인증 요청
		.antMatchers(// 파일 경로가 아니라 주소 적기
				"/",
				"/user",
				"/user/sign",
				"/images/**",
				"/css/**",
				"/js/**"
				)
		.permitAll()		// 인증없이 접근가능
		.anyRequest().authenticated()	// 위의 주소가 아닌 어떤 요청이라도 인증해야함!
		.and()
		.formLogin()	// 나는 내가 만든 폼으로 로그인 시켜줄거야
		.loginPage("/user/login") // 로그인페이지를 여는 주소
		.loginProcessingUrl("/user/login") // 로그인 페이지에서 실행할 주소
		.permitAll()
		.usernameParameter("userId") // 사용자 정의 로그인 폼에서 사용자 식별자로 사용하는 필드의 name 속성
		.passwordParameter("userPw") // password 속성
		.and()
		.logout() // 로그아웃에 대한 설정
		.logoutSuccessUrl("/") // 로그아웃이 성공했을 때 이동할 주소
		.permitAll()
		.and()
		.cors()
		.and()
		.httpBasic();
		
		
		return hs.build(); // 길게 작성한 객체에 설정된 결과를 returnw
		
		
	}
	
	// 인증용 쿼리
	@Autowired
	public void configure(AuthenticationManagerBuilder auth) throws Exception{
		String userNameQueryForEnabled = 
				// 열을 지정하고 꼭!!! 공백을 넣자(FROM KEYWORD MISSING ERROR 방지)
				"SELECT USERID username, USERPW password, ENABLED " +
				"FROM STORE_USER "+
				"WHERE USERID = ?"; // ? : MEMBERID가 들어갈 곳
		
		String userNameQueryForRole = 
				"SELECT USERID username, ROLENAME role_name " +
				"FROM STORE_USER " +
				"WHERE USERID = ?";
		
		auth.jdbcAuthentication()
		.dataSource(dataSource) // db에 접속하기 위한 정보
		.usersByUsernameQuery(userNameQueryForEnabled) // Enabled용 쿼리
		.authoritiesByUsernameQuery(userNameQueryForRole); // RoleName용 쿼리
		
	}
	
	// 단방향 비밀번호 암호화
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder(); // static은 모두 클래스 영역에 저장됨
	}
	
}
