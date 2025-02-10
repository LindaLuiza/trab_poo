package dtos;

import java.util.Date;

import domain.ETipoPagamento;

public class PagamentoDto {

	private ETipoPagamento tipo;
	private Date data;
	private double valor;

	public PagamentoDto(ETipoPagamento tipo, double valor) {
		this.tipo = tipo;
		this.valor = valor;

	}

	public PagamentoDto(ETipoPagamento tipo, Date data, double valor) {
		this(tipo, valor);
		this.data = data;
	}

	public ETipoPagamento getTipo() {
		return tipo;
	}

	public Date getData() {
		return data;
	}

	public double getValor() {
		return valor;
	}

	@Override
	public String toString() {
		return "PagamentoDto [tipo=" + tipo + ", data=" + data + ", valor=" + valor + "]";
	}

}
