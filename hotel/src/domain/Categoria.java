package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import exception.ItemException;

public class Categoria implements Serializable{

	private static final long serialVersionUID = -8621154512992843110L;
	private final String nome;
	private ArrayList<Item> itens;

	public Categoria(String nome) {
		this.nome = nome;
		this.itens = new ArrayList<Item>();
	}

	public String getNome() {
		return nome;
	}

	public void addItem(Item item) throws ItemException {
	    boolean itemJaExiste = itens.stream().anyMatch(i -> i.getCodigo() == item.getCodigo());

	    if (itemJaExiste) {
	        throw new ItemException("Item já está na categoria");
	    } else {
	        itens.add(item);
	    }
	}

	
	//TODO: Remove itens
	public void removeItem(Item i) {
		return;
	}
	
	public List<Item> getItens() {
        return itens;
    }
	
}
