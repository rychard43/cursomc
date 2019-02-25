package com.rychard.cursomc.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

public class MockEmailService extends AbstractEmailService {

	private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class); //pra mostrar no log do server
	
	
	@Override
	public void sendEmail(SimpleMailMessage msg) {
		LOG.info("simulando email...");
		LOG.info(msg.toString());
		LOG.info("Email enviado...");
		
	}

}
