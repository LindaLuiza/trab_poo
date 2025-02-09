package domain;

import java.util.Date;

import exception.PagamentoException;

public class Pagamento {

	private final ETipoPagamento tipo;
	private final Date data;
	private final double valor;

	public Pagamento(ETipoPagamento tipo, double valor) throws PagamentoException {
		if (valor <= 0) {
			throw new PagamentoException("Valor pago deve ser maior que zero");
		}
		this.tipo = tipo;
		this.data = new Date();
		this.valor = valor;
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

}
