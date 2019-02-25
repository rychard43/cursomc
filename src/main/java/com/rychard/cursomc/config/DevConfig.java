package com.rychard.cursomc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.rychard.cursomc.services.DBService;
import com.rychard.cursomc.services.EmailService;
import com.rychard.cursomc.services.SmtpEmailService;

@Configuration
@Profile("dev")
public class DevConfig {

	@Autowired
	private DBService dbService;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;
	
	@Bean
	public boolean instantiateDatabase() throws ParseException {
		
		if(!"create".equals(strategy)) {
			return false; //verificar se tem create no properties
		}
		
		dbService.instantiateTestDatabase();
		return true;
	}	
	
	@Bean
	public EmailService emailService() {
		return new SmtpEmailService();
	}
	
}
