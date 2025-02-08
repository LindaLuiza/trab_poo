package domain;

public enum ETipoPagamento {

	PIX("Pagamento por PIX"), DEBITO("Pagamento por Débito"), CREDITO("Pagamento por Crédito");

	private final String descricao;

	ETipoPagamento(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
