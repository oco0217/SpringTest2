package com.ezen.www.config;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		
		return new Class[] {RootConfig.class, SecurityConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		
		return new Class[] {ServletConfiguration.class};
	}

	@Override
	protected String[] getServletMappings() {
		
		return new String[] {"/"};
	}

	@Override
	protected Filter[] getServletFilters() {
		//filter 설정
		
		CharacterEncodingFilter encoding = new CharacterEncodingFilter();
		encoding.setEncoding("UTF-8");
		encoding.setForceEncoding(true); //외부로 나가는 데이터도 인코딩 설정
		
		return new Filter[] {encoding};
	}

	@Override
	protected void customizeRegistration(Dynamic registration) {
		//그외 기타 사용자 설정하는 곳
		//파일 업로드 설정
		//사용자 지정 익셉션 처리 설정
		String uploadLocation = "D:\\_myProject\\_java\\_fileUpload";
		int maxFileSize = 1024*1024*20;
		int mxRequestSize = maxFileSize * 2;
		int fileSizeThreshold = maxFileSize;
		
		//multipartConfig 객체 생성후 사이즈 정의
		MultipartConfigElement multipartConfig = 
				new MultipartConfigElement(uploadLocation, maxFileSize, mxRequestSize, fileSizeThreshold);
		
		//multipartConfig 객체 생성한 instance를 registration에 Setting
		registration.setMultipartConfig(multipartConfig);
		
	} 

	
	
}































