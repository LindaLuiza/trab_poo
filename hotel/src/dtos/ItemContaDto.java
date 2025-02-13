package dtos;

import java.util.Date;

import domain.Item;

public class ItemContaDto {
	
	private Date dataHora;
	private double preco;
	private int qtde;
	private Item item;
	
	public ItemContaDto(int qtde, Item item) {
		this.qtde = qtde;
		this.item = item;
	}

	public ItemContaDto(Date dataHora, double preco, int qtde, Item item) {
		this(qtde, item);
		this.preco = preco;
		this.dataHora = dataHora;
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

	public Item getItem() {
		return item;
	}
	
	

}
