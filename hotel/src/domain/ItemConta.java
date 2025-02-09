package domain;

import java.util.Date;

import exception.ItemContaException;

public class ItemConta {

	private final Date dataHora;
	private final double preco;
	private final int qtde;
	private final Item item;

	public ItemConta(double preco, int qtde, Item item) throws ItemContaException {
		if (preco <= 0) {
			throw new ItemContaException("Preço deve ser maior que zero.");
		}

		this.dataHora = new Date();
		this.preco = preco;
		this.qtde = qtde;
		this.item = item;
	}

	public Item getItem() {
		return item;
	}

	public Date getDataHora() {
		return dataHora;
	}

	public double getPreco() {
		return preco;
	}

	public int getQtde() {
		return qtde;
	}

	public double getTotal() {
		return this.getQtde() * this.getPreco();
	}

}
