package com.tirmizee.core.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import com.tirmizee.backend.dao.UserDao;
import com.tirmizee.core.security.AuthenticationProviderImpl;
import com.tirmizee.core.security.UserDetailsServiceImpl;

/**
 * @author Pratya Yeekhaday
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	public static final String PERMISSION_FIRST_LOGIN = "FIRST_LOGIN";
	public static final String PERMISSION_PASSWORD_EXPRIED = "PASSWORD_EXPRIED";
	
	@Autowired
	private UserDao userDao;

	@Autowired 
	private AccessDeniedHandler accessDeniedHandler;
	
	@Autowired 
	private AuthenticationFailureHandler authenticationFailureHandler;
	
	@Autowired 
	private AuthenticationSuccessHandler authenticationSuccessHandler;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(13);
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsServiceImpl(userDao);
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
	    DaoAuthenticationProvider authProvider = new AuthenticationProviderImpl();
	    authProvider.setHideUserNotFoundExceptions(false);
	    authProvider.setUserDetailsService(userDetailsService());
	    authProvider.setPasswordEncoder(passwordEncoder());
	    return authProvider;
	}
	
	@Bean
	public SessionRegistry sessionRegistry() {
	    return new SessionRegistryImpl();
	}
	
	@Bean
	public ServletListenerRegistrationBean<HttpSessionEventPublisher> httpSessionEventPublisher() {
	    return new ServletListenerRegistrationBean<HttpSessionEventPublisher>(new HttpSessionEventPublisher());
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
	}
	
	@Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.exceptionHandling()
				.accessDeniedHandler(accessDeniedHandler)
				.and()
			.csrf()
				.ignoringAntMatchers("/logout")
				.and()
			.authorizeRequests()
				.antMatchers(
					"/",
					"/login**"
				).permitAll()
				.antMatchers(
					"/firstlogin",
					"/api/user/password/firstlogin"
				).hasAuthority(PERMISSION_FIRST_LOGIN)
				.antMatchers(
					"/passwordexpried"
					).hasAuthority(PERMISSION_PASSWORD_EXPRIED)
				.anyRequest().authenticated()
				.and()
			.formLogin()
            	.loginPage("/login")
            	.permitAll()
            	.successHandler(authenticationSuccessHandler)
            	.failureHandler(authenticationFailureHandler)
                .and()
            .logout()
            	.invalidateHttpSession(true)
            	.deleteCookies("JSESSIONID")
                .permitAll()
                .and()
            .sessionManagement()                         
                .maximumSessions(1)                        
                .maxSessionsPreventsLogin(false)
				.expiredUrl("/login?error=This username being used by another")             
				.sessionRegistry(sessionRegistry());    
	}

}
