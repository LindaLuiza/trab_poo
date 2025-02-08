package domain;

import java.util.ArrayList;

public class Conta {

	private ArrayList<ItemConta> itens;

	public Conta() {
		this.itens = new ArrayList<ItemConta>();
	}

	void addItem(ItemConta item, int qtde) {
		for (int i = 0; i < qtde; i++) {
			itens.add(item);
		}
	}

	void remove(int index) {
		itens.remove(index);
	}

	double getTotal() {
		// precoTotal = precoTotal + item.getTotal();
		double precoTotal = 0.0;
		for (ItemConta item : itens) {
			precoTotal = precoTotal + item.getPreco();
		}
		return precoTotal;
	}

	StringBuilder listar() {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("------------------------"));
		sb.append(String.format("Items\n"));
		sb.append(String.format("------------------------"));
		int index = 1;

		for (ItemConta item : itens) {
			sb.append(String.format("Index: %d - ", index));
			sb.append(String.format("Descrição: %s - ", item.getItem().getDescricao()));
			sb.append(String.format("Preço: %l%n", item.getItem().getPreco()));
			index++;
		}

		return sb;
	}

}
