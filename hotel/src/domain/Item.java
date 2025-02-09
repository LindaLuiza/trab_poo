package domain;

import exception.ItemException;

public class Item {

	private final long codigo;
	private final String descricao;
	private double preco;

	public Item(long codigo, String descricao, double preco) throws ItemException {
		super();
		
		if(preco <= 0) {
			throw new ItemException("Preço deve ser maior que zero.");
		}
		
		this.codigo = codigo;
		this.descricao = descricao;
		this.preco = preco;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public long getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

}
