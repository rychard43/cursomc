package com.rychard.cursomc.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.rychard.cursomc.domain.enums.EstadoPagamento;

@Entity
public class PagamentoComBoleto extends Pagamento {

	private static final long serialVersionUID = 1L;
	
	private Date dataVenciento;
	private Date dataPagamento;
	
	public PagamentoComBoleto() {
		
	}

	public PagamentoComBoleto(Integer id, EstadoPagamento estado, Pedido pedido, Date dataVenciento, Date dataPagamento ) {
		super(id, estado, pedido);
		this.dataVenciento=dataVenciento;
		this.dataPagamento=dataPagamento;
		
	}

	public Date getDataVenciento() {
		return dataVenciento;
	}

	public void setDataVenciento(Date dataVenciento) {
		this.dataVenciento = dataVenciento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	
	
	
	
}
