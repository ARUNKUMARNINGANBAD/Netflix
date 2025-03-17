package org.dcwloadassurant.com.Dcwload.appconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class Appconfig {
	
	@Bean
	public BCryptPasswordEncoder passwordendcoder() {
		return new BCryptPasswordEncoder();
	}

}
