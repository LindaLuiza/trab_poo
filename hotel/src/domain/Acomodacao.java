package domain;

import java.io.Serializable;

import exception.AcomodacaoException;

public class Acomodacao implements IAcomodacao, Serializable {

	private static final long serialVersionUID = 7929150302721574673L;
	
	private final int numero;
	private final int ocupacaoMax;
	private EEstadoOcupacao estadoOcupacao;
	private TipoAcomodacao tipo;

	public Acomodacao(int numero, int ocupacaoMax, TipoAcomodacao tipo) throws AcomodacaoException {
		if (numero <= 0) {
			throw new AcomodacaoException("Numero deve ser inteiro maior que zero.");
		}

		if (ocupacaoMax <= 0) {
			throw new AcomodacaoException("Ocupação Máxima deve ser inteiro maior que zero.");
		}

		if (tipo == null) {
			throw new AcomodacaoException("Tipo da Acomodacao deve ser informado.");
		}
		
		this.numero = numero;
		this.ocupacaoMax = ocupacaoMax;
		this.estadoOcupacao = EEstadoOcupacao.DISPONIVEL;
		this.tipo = tipo;
	}

	public EEstadoOcupacao getEstadoOcupacao() {
		return estadoOcupacao;
	}

	public void setEstadoOcupacao(EEstadoOcupacao estadoOcupacao) {
		this.estadoOcupacao = estadoOcupacao;
	}

	public int getNumero() {
		return numero;
	}

	public int getOcupacaoMax() {
		return ocupacaoMax;
	}

	public String getTipo() {
		return tipo.getName();
	}

	public double getTarifaDiaria() {
		return tipo.getTarifaDiaria();
	}

	public double getAdicionalAcompanhante() {
		return tipo.getTarifaDiaria();
	}
}
