package com.rychard.cursomc.domain.enums;

public enum TipoCliente {

	PESSOASFISICA(1, "Pessoa Fisica"),
	PESSOAJURIDICA(2, "Pessoa Juridica");
	
	private int cod;
	private String descricao;
	
	private TipoCliente(int cod, String descricao) {
		this.cod=cod;
		this.descricao=descricao;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static TipoCliente toEnum(Integer cod) {//static pq essa operação tem q ser possivel de ser executada mesmo sem instanciar objetos
		if(cod==null) {
			return null;
		}
		
		for(TipoCliente t : TipoCliente.values()) {
			if(cod.equals(t.getCod())) {
				return t;
			}
		}
		
		throw new IllegalArgumentException("Id invalido: "+cod);
	}
}
