package com.igmasiri.facebooklogin.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
	private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthenticationSuccessHandlerImpl authenticationSuccessHandler;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	@Override
    protected void configure(HttpSecurity http) throws Exception {
		http
			.addFilterBefore(authenticationFilter(),UsernamePasswordAuthenticationFilter.class)
            .authorizeRequests()  
                .antMatchers(
                        "/",
                        "/css/**",
                        "/img/**",
                        "/js/**",
                        "/owl-carousel/**",
						"/resources/**",
                        "/fonts/**").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
				.successHandler(authenticationSuccessHandler)
				.failureUrl("/login?error")
                .permitAll()
                .and()
            .logout() 
                .permitAll();
    }

	public SimpleAuthenticationFilter authenticationFilter() throws Exception {
		SimpleAuthenticationFilter filter = new SimpleAuthenticationFilter();
		filter.setAuthenticationManager(authenticationManagerBean());
        filter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
		return filter;
	}
}