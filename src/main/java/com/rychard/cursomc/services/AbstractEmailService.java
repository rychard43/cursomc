package com.rychard.cursomc.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.rychard.cursomc.domain.Pedido;

public abstract class AbstractEmailService implements EmailService{

	@Value("${default.sender}")
	private String sender;
	
	
	@Override
	public void sendOderConfirmationEmail(Pedido obj) {
		SimpleMailMessage sm =  prepareSimpleMailMessageFromPedido(obj);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido obj) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(obj.getCliente().getEmail());//email para quem vai mandar
		sm.setFrom(sender);//quem enviou o email
		sm.setSubject("Pedido confirmado !! Codigo: "+obj.getId());//assunto do email
		sm.setSentDate(new Date(System.currentTimeMillis()));//horaio de acordo com o server
		sm.setText(obj.toString());//corpo do email
	
		return sm;
	}
	
	
}
