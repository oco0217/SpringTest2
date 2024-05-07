package com.ezen.www.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.ezen.www.security.CustomAuthUserService;
import com.ezen.www.security.LoginSuccessHandler;
import com.ezen.www.security.LoginfailureHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	//AutenticationMangerBuilder 와 HttpSecurity Override Method 생성
	
	//비밀번호 암호화 객체 PasswordEncoder 빈 생성
	@Bean
	public PasswordEncoder bcPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	//SuccessHandler 객체 빈 생성 => 사용자 커스텀 객체
	@Bean
	public AuthenticationSuccessHandler authSuccessHandler() {
		return new LoginSuccessHandler(); //아직 생성 안함.
	}
	
	//FailureHandler 객체 빈 설정 => 사용자 커스텀 객체
	@Bean
	public AuthenticationFailureHandler authFailureHandler() {
		return new LoginfailureHandler(); //아직 생성 안함
	}
	
	//UserDetail 객체 빈 생성 => 사용자 커스텀 생성
	@Bean
	public UserDetailsService customUserService() {
		return new CustomAuthUserService(); 
	}
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//인증되는 객체로 설정
		auth.userDetailsService(customUserService()).passwordEncoder(bcPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//화면에서 설정되는 권한에 따른 주소 맵핑 설정
		//csrf() : cross site request forgery attack
		//csrf() : 공격에 대한 설정 풀기
		http.csrf().disable();
		
		//권한 승인 요청
		//antMatchers : 접근을 허용하는 값(경로)
		//permitAll : 누구나 접근가능한 경로
		//authenticated() : 인증된 사용자만 가능한 경로
		//auth => hasRole : 권한을 확인
		//USER, ADMIN, MANAGER 
		http.authorizeRequests().antMatchers("/user/list").hasRole("ADMIN")
		.antMatchers("/", "/board/list", "/board/detail", "/comment/**", "/up/**", "/re/**", "/user/register", "/user/login").permitAll()
		.anyRequest().authenticated();
		
		//커스텀 로그인 페이지 구성
		//Controller에 주소요청 맵핑이 같이 있어아 함. (필수)
		//로그인과 로그아웃 둘다 form태그에 씌어서 가야 한다. (로그아웃 페이지 반드시 method="post")
		http.formLogin()
		.usernameParameter("email") //이메일
		.passwordParameter("pwd") //비밀번호
		.loginPage("/user/login") //로그인할 페이지
		.successHandler(authSuccessHandler()) //성공했을 경우
		.failureHandler(authFailureHandler()); //실패했을 경우
//		.failureUrl(null) CustomPage가 없어도 URL로 가게 만들 수 있다. (Scuccsess도 가능)
		
		
		//로그아웃 페이지 반드시 pethod="post"
		http.logout()
		.logoutUrl("/user/logout")
		.invalidateHttpSession(true)
		.deleteCookies("JSESSIONID")
		.logoutSuccessUrl("/");
		
	}
}













