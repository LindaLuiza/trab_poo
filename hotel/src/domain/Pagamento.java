package domain;

import java.util.Date;

public class Pagamento {

	private final ETipoPagamento tipo;
	private final Date data;
	private final double valor;

	public Pagamento(ETipoPagamento tipo, double valor) {
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
