package com.tirmizee.core.configuration;

import java.util.Arrays;
import java.util.List;

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
import org.springframework.security.web.authentication.session.CompositeSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionFixationProtectionStrategy;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.session.ConcurrentSessionFilter;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.session.SimpleRedirectSessionInformationExpiredStrategy;

import com.tirmizee.backend.dao.PermissionDao;
import com.tirmizee.backend.dao.UserDao;
import com.tirmizee.core.constant.Constant;
import com.tirmizee.core.constant.PermissionCode;
import com.tirmizee.core.security.AuthenticationProviderImpl;
import com.tirmizee.core.security.CustomConcurrentSessionControlAuthenStrategy;
import com.tirmizee.core.security.CustomHttpSessionCsrfTokenRepository;
import com.tirmizee.core.security.UserDetailsServiceImpl;

/**
 * @author Pratya Yeekhaday
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private PermissionDao permissionDao;

	@Autowired 
	private AccessDeniedHandler accessDeniedHandler;
	
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
    public ConcurrentSessionFilter concurrentSessionFilter(){
    	return new ConcurrentSessionFilter(sessionRegistry(), sessionInformationExpiredStrategy());
    }
    
    @Bean 
	public CsrfTokenRepository httpSessionCsrfTokenRepository() {
    	CustomHttpSessionCsrfTokenRepository httpSessionCsrfTokenRepository = new CustomHttpSessionCsrfTokenRepository();
    	httpSessionCsrfTokenRepository.setHeaderName(Constant.Resource.APPLICATION.getString("security.csrf.header"));
		return httpSessionCsrfTokenRepository;
	}
    
    @Bean
    public SimpleRedirectSessionInformationExpiredStrategy sessionInformationExpiredStrategy(){
    	return new SimpleRedirectSessionInformationExpiredStrategy("/login?error=Session Expired");
    }
    
	@Bean
	public ServletListenerRegistrationBean<HttpSessionEventPublisher> httpSessionEventPublisher() {
	    return new ServletListenerRegistrationBean<HttpSessionEventPublisher>(new HttpSessionEventPublisher());
	}
	
	@Bean
    public CompositeSessionAuthenticationStrategy sessionAuthenticationStrategy(){
		List<SessionAuthenticationStrategy> delegateStrategies = Arrays.asList(
			new SessionFixationProtectionStrategy(), 
			new CustomConcurrentSessionControlAuthenStrategy(sessionRegistry()), 
			new RegisterSessionAuthenticationStrategy(sessionRegistry())
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
		web.ignoring().antMatchers("/resources/**");
	}
	
	@Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf()
				.csrfTokenRepository(httpSessionCsrfTokenRepository())
				.ignoringAntMatchers("/logout")
				.and()
			.authorizeRequests()
				.antMatchers(
					"/",
					"/login**",
					"/NotFound",
					"/accessdenied",
					"/ServerError",
					"/forgotpassword",
					"/resetpassword/**",
					"/api/user/password/forgot",
					"/api/user/password/reset"
				).permitAll()
				.antMatchers(
					"/firstlogin",
					"/api/user/password/firstlogin"
				).hasAuthority(PermissionCode.PG00)
				.antMatchers(
					"/passwordexpried"
				).hasAuthority(PermissionCode.PG01)
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
