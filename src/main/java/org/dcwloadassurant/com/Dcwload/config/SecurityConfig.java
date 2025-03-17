package org.dcwloadassurant.com.Dcwload.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Bean
	public AuthenticationManager autenticationmanager(
			AuthenticationConfiguration configuration) throws Exception{
		System.out.println("am load AuthenticationManager");
	 return configuration.getAuthenticationManager();
		
    }
	
	//Authentication
	@Bean
	public DaoAuthenticationProvider authrnticationProvider() {
		System.out.println("am load DaoAuthenticationProvider");
	   	DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
	   	provider.setUserDetailsService(userDetailsService);
	   	provider.setPasswordEncoder(passwordEncoder);
	   	return provider;
	}
	
	//Authorization
	@Bean
	public SecurityFilterChain configAuth(HttpSecurity http) throws Exception {
		System.out.println("am load SecurityFilterChain");

		http.authorizeRequests(request -> request
						.antMatchers("/register", "/save","/signin","/login").permitAll()
						.antMatchers("/customer", "/generateCsv", "/unbundle").hasAuthority("CUSTOMER")
						.antMatchers("/dashboard/**","/check-session").hasAuthority("ADMIN")
				)
				.formLogin(form -> form.loginPage("/login").permitAll().defaultSuccessUrl("/dashboard", true))
				.logout(logout -> logout.logoutUrl("/logout") // Logout endpoint
						.logoutSuccessUrl("/login?logout") // Redirect to login page with a logout message
						.invalidateHttpSession(true) // Invalidate session
						.deleteCookies("JSESSIONID") // Clear session cookies
						.permitAll())
				.headers(headers -> headers
						.frameOptions().sameOrigin() // Allow iframes from the same origin
				).sessionManagement(session -> session
						.invalidSessionUrl("/login") // Redirect when session is invalid
						.maximumSessions(1) // Max concurrent sessions per user
						.expiredUrl("/login") // Redirect when session expires
						.and()
						.sessionFixation().migrateSession()); // Protect against session fixation;
		//http.addFilterBefore(new SessionValidationFilter(), UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

	////////////////////////////////////////////////////////////////////////////////
//	@Bean
//	public ServletContextInitializer servletContextInitializer() {
//		return new ServletContextInitializer() {
//			@Override
//			public void onStartup(ServletContext servletContext) throws ServletException {
//				servletContext.setSessionTimeout(7); // 5 minutes
//			}
//		};
//	}



}