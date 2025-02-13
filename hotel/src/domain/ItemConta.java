package domain;

import java.io.Serializable;
import java.util.Date;

import exception.ItemContaException;

public class ItemConta implements Serializable {

	private static final long serialVersionUID = 2835191358738544934L;
	
	private final Date dataHora;
	private final double preco;
	private final int qtde;
	private final Item item;

	public ItemConta(int qtde, Item item) throws ItemContaException {
		this.dataHora = new Date();
		this.preco = item.getPreco();
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
