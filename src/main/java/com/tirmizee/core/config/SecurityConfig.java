package com.tirmizee.core.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
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
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.session.ChangeSessionIdAuthenticationStrategy;
import org.springframework.security.web.authentication.session.CompositeSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.csrf.LazyCsrfTokenRepository;
import org.springframework.security.web.session.ConcurrentSessionFilter;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import com.tirmizee.backend.dao.PermissionDao;
import com.tirmizee.backend.dao.UserDao;
import com.tirmizee.backend.service.MessagingService;
import com.tirmizee.backend.service.SessionService;
import com.tirmizee.core.config.security.AuthenticationProviderImpl;
import com.tirmizee.core.config.security.CustomConcurrentSessionControlAuthenStrategy;
import com.tirmizee.core.config.security.CustomHttpSessionCsrfTokenRepository;
import com.tirmizee.core.config.security.CustomHttpSessionEventPublisher;
import com.tirmizee.core.config.security.SessionInformationExpiredStrategyImpl;
import com.tirmizee.core.config.security.UserDetailsServiceImpl;
import com.tirmizee.core.constant.ApplicationResource;
import com.tirmizee.core.constant.PermissionCode;

/**
 * @author Pratya Yeekhaday
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private PermissionDao permissionDao;

	@Autowired
	private SessionService sessionService;
	
	@Autowired
	private MessagingService messagingService;
	
	@Autowired 
	private AccessDeniedHandler accessDeniedHandler;
	
	@Autowired 
	private LogoutSuccessHandler logoutSuccessHandler;
	
	@Autowired 
	private AuthenticationFailureHandler authenticationFailureHandler;
	
	@Autowired 
	private AuthenticationSuccessHandler authenticationSuccessHandler;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(11);
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsServiceImpl(userDao, permissionDao);
	}
	
	@Bean
	public SessionRegistry sessionRegistry() {
	    return new SessionRegistryImpl();
	}
    
    @Bean
    public ConcurrentSessionFilter concurrentSessionFilter() {
    	return new ConcurrentSessionFilter(sessionRegistry(), sessionInformationExpiredStrategy());
    }
    
    @Bean
    public SessionInformationExpiredStrategy sessionInformationExpiredStrategy() {
    	SessionInformationExpiredStrategyImpl sessionExpiredStrategy =
    		new SessionInformationExpiredStrategyImpl("/login?error=Session Expired");
    	sessionExpiredStrategy.setMessagingService(messagingService);
    	sessionExpiredStrategy.setSessionService(sessionService);
    	return sessionExpiredStrategy;
    }
    
	@Bean
	public ServletListenerRegistrationBean<HttpSessionEventPublisher> httpSessionEventPublisher() {
	    return new ServletListenerRegistrationBean<HttpSessionEventPublisher>(new CustomHttpSessionEventPublisher());
	}
	
	/*
    *	https://stackoverflow.com/questions/49057057/how-does-crsf-lazycsrftokenrepository-work
    */    
    @Bean
    public LazyCsrfTokenRepository lazyCsrfTokenRepository() {
    	CustomHttpSessionCsrfTokenRepository httpSessionCsrfTokenRepository = new CustomHttpSessionCsrfTokenRepository();
    	httpSessionCsrfTokenRepository.setHeaderName(ApplicationResource.SECURITY_CSRF_HEADER);
    	return new LazyCsrfTokenRepository(httpSessionCsrfTokenRepository);
    }
	    
	@Bean
    public CompositeSessionAuthenticationStrategy sessionAuthenticationStrategy(){
		List<SessionAuthenticationStrategy> delegateStrategies = Arrays.asList(
//			new SessionFixationProtectionStrategy(),  Legacy
			new ChangeSessionIdAuthenticationStrategy(),
			new RegisterSessionAuthenticationStrategy(sessionRegistry()),
			new CustomConcurrentSessionControlAuthenStrategy(sessionRegistry())
		);
    	return new CompositeSessionAuthenticationStrategy(delegateStrategies);    
    }
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
	    DaoAuthenticationProvider authProvider = new AuthenticationProviderImpl();
	    authProvider.setHideUserNotFoundExceptions(false);
	    authProvider.setUserDetailsService(userDetailsService());
	    authProvider.setPasswordEncoder(passwordEncoder());
	    return authProvider;
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/v2/**","/swagger**/**","/resources/**","/webjars/**","/ws/**");
	}
	
	@Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth
//        	.inMemoryAuthentication()
        	.authenticationProvider(authenticationProvider());
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.headers()
				.frameOptions().deny()
	            .and()
			.csrf()
				.csrfTokenRepository(lazyCsrfTokenRepository())
				.ignoringAntMatchers("/logout")
				.and()
			.authorizeRequests()
				.antMatchers(
					"/",
					"/csrf",
					"/login**",
					"/NotFound",
					"/accessdenied",
					"/ServerError",
					"/forgotpassword",
					"/resetpassword/**",
					"/api/user/password/forgot",
					"/api/user/password/reset",
					"/export/**",
					"/swagger-ui.html"
				).permitAll()
				.antMatchers(
					"/firstlogin",
					"/api/user/password/firstlogin"
				).hasAuthority(PermissionCode.PG00)
				.antMatchers(
					"/passwordexpried"
				).hasAuthority(PermissionCode.PG01)
				.antMatchers(
					"/swagger-ui.html"
				).hasAnyAuthority(PermissionCode.P002)
				.anyRequest().authenticated()
				.and()
			.formLogin()
            	.loginPage("/login")
            	.permitAll()
            	.successHandler(authenticationSuccessHandler)
            	.failureHandler(authenticationFailureHandler)
                .and()
            .logout()
            	.logoutSuccessHandler(logoutSuccessHandler)
            	.invalidateHttpSession(true)
            	.deleteCookies("JSESSIONID")
                .permitAll()
                .and()
            .exceptionHandling()
				.accessDeniedHandler(accessDeniedHandler)
				.and()
            .sessionManagement()
//	            .maximumSessions(1)                        
//	            .maxSessionsPreventsLogin(false)
//				.expiredUrl("/login?error=Session Expried")             
//				.sessionRegistry(sessionRegistry());
				.sessionAuthenticationStrategy(sessionAuthenticationStrategy())
				.and()
			.addFilter(concurrentSessionFilter());
	}

}
