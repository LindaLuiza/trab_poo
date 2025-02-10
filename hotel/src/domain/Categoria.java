package domain;

import java.util.ArrayList;
import java.util.List;

public class Categoria {

	private final String nome;
	private ArrayList<Item> itens;

	public Categoria(String nome) {
		this.nome = nome;
		this.itens = new ArrayList<Item>();
	}

	public String getNome() {
		return nome;
	}

	public void addItem(Item i) {
		itens.add(i);
	}
	
	//TODO: Remove itens
	public void removeItem(Item i) {
		return;
	}
	
	public List<Item> getItens() {
        return itens;
    }
	
}
