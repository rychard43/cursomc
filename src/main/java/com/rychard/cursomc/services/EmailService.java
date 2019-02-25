package com.rychard.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.rychard.cursomc.domain.Pedido;

public interface EmailService {

	void sendOderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);

}
