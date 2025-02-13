package domain;

import java.io.Serializable;
import java.util.ArrayList;

public class Conta implements Serializable{

	private static final long serialVersionUID = 5517716049946906098L;
	
	private ArrayList<ItemConta> itens;

	public Conta() {
		this.itens = new ArrayList<ItemConta>();
	}

	public void addItem(ItemConta item, int qtde) {
		for (int i = 0; i < qtde; i++) {
			itens.add(item);
		}
	}

	public void remove(int index) {
		itens.remove(index);
	}

	public ArrayList<ItemConta> getItens() {
		return itens;
	}

	public void setItens(ArrayList<ItemConta> itens) {
		this.itens = itens;
	}

	public double getTotal() {
		double precoTotal = 0.0;
		for (ItemConta item : itens) {
			precoTotal = precoTotal + item.getPreco();
		}
		return precoTotal;
	}

	StringBuilder listar() {
		StringBuilder sb = new StringBuilder();

		sb.append("------------------------\n");
		sb.append("Items\n");
		sb.append("------------------------\n");

		int index = 1;
		for (ItemConta item : itens) {
			sb.append(String.format("Index: %d - ", index));
			sb.append(String.format("Descrição: %s - ", item.getItem().getDescricao()));
			sb.append(String.format("Preço: %.2f%n", item.getItem().getPreco()));
			index++;
		}

		sb.append("------------------------\n");
		sb.append(String.format("Total: %.2f\n", getTotal()));

		return sb;
	}

}
