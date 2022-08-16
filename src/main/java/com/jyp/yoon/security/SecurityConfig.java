package com.jyp.yoon.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class SecurityConfig {
	
	private final CustomAuthSuccessHandler customAuthSuccessHandler;
	
	private final CustomOAuth2UserService customOAuth2UserService;
	//private final CustomAuthenticationFailureHandler failureHandler;
	//private final CustomAuthenticationSuccessHandler successHandler;
	//DaoAuthenticationProvider
	//AuthenticationSuccessHandler

    
    
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorize ->
                        authorize
                        		.antMatchers("/css/**","/js/**","/images/**","/favicon.ico*","/error","/summernote/**").permitAll()
                                .antMatchers("/", "/common/**","/request-key/**,/customer/**","/qna/**","/faq/**","/mailsend").permitAll()
                                .antMatchers("/products/**","/member/**").hasRole("USER")
                                .antMatchers("/admin/**").hasRole("ADMIN")
                                .anyRequest().authenticated()
                );
        
        http.oauth2Login(oauth2Login->
        		oauth2Login
        			.loginPage("/signin")
        			//.permitAll()
        			//.defaultSuccessUrl("/")
        			.userInfoEndpoint()
        				.userService(customOAuth2UserService)
        			);
        
        //http.formLogin();
        //*
        http.formLogin(formLogin ->
                formLogin
                        .loginPage("/signin")
                        .usernameParameter("email")
                        .passwordParameter("pass")
                        .loginProcessingUrl("/signin")
                        .failureUrl("/signin?errMsg")
                        .defaultSuccessUrl("/")
                        //.successHandler(successHandler())
                        .successHandler(customAuthSuccessHandler)//인증성공이후 -- 처리로직
                        //.failureHandler(failureHandler())//로그인실패시 처리
                        .permitAll()
        );
        //*/

        http.logout(logout -> logout
        		.logoutSuccessUrl("/")
        		.invalidateHttpSession(true)
        		);//default is "/login?logout".
        
        http.csrf();
        
        http.exceptionHandling(exception->exception
        		.accessDeniedHandler(new AccessDeniedHandlerImpl())
        		//.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/signin") ));
        );
        //AccessDeniedHandler
        //AuthenticationEntryPoint

        return http.build();
    }

	
}
