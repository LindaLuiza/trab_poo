package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import domain.Categoria;
import domain.Item;
import dtos.CategoriaDto;
import dtos.ItemDto;
import exception.ItemException;

public class ItemController implements Serializable{

	private static final long serialVersionUID = -333031781349286041L;
	
	private List<Item> itemList = new ArrayList<>();
	private List<Categoria> categoriaList = new ArrayList<>();

	public void createItem(long codigo, String descricao, double preco) {
	    // Verifica se já existe um item com o mesmo código
	    boolean exists = itemList.stream().anyMatch(i -> i.getCodigo() == codigo);
	    
	    if (exists) {
	        System.err.println("Erro: Já existe um item com o código " + codigo);
	        return;  // Sai do método sem adicionar o item
	    }

	    try {
	        Item item = new Item(codigo, descricao, preco);
	        itemList.add(item);
	        System.out.println("Item criado com sucesso: " + descricao);
	    } catch (ItemException e) {
	        System.err.println("Erro ao criar o item: " + e.getMessage());
	    }
	}

	public ItemDto getItemByCodigo(long codigo) {
		Optional<Item> item = itemList.stream().filter(i -> i.getCodigo() == codigo).findFirst();
		return item.map(i -> new ItemDto(i.getCodigo(), i.getDescricao(), i.getPreco())).orElse(null);
	}

	public List<ItemDto> getAllItems() {
		return itemList.stream().map(i -> new ItemDto(i.getCodigo(), i.getDescricao(), i.getPreco()))
				.collect(Collectors.toList());
	}

	public void createCategoria(String nome) {
	    // Verifica se já existe uma categoria com o mesmo nome
	    boolean exists = categoriaList.stream().anyMatch(c -> c.getNome().equalsIgnoreCase(nome));
	    
	    if (exists) {
	        System.err.println("Erro: Já existe uma categoria com o nome " + nome);
	        return;  // Sai do método sem adicionar a categoria
	    }
	    
		Categoria categoria = new Categoria(nome);
		categoriaList.add(categoria);
		System.out.println("Categoria criada com sucesso: " + nome);
	}

	public CategoriaDto getCategoriaByNome(String nome) {
		Optional<Categoria> categoria = categoriaList.stream().filter(c -> c.getNome().equals(nome)).findFirst();
		return categoria.map(c -> new CategoriaDto(c.getNome(), c.getItens().stream()
				.map(i -> new ItemDto(i.getCodigo(), i.getDescricao(), i.getPreco())).collect(Collectors.toList())))
				.orElse(null);
	}

	public List<CategoriaDto> getAllCategorias() {
		return categoriaList.stream().map(c -> new CategoriaDto(c.getNome(), c.getItens().stream()
				.map(i -> new ItemDto(i.getCodigo(), i.getDescricao(), i.getPreco())).collect(Collectors.toList())))
				.collect(Collectors.toList());
	}
	
	public void addItemToCategoria(String nomeCategoria, long codigoItem) {
	    Categoria categoria = categoriaList.stream()
	        .filter(c -> c.getNome().equals(nomeCategoria))
	        .findFirst()
	        .orElse(null);

	    Item item = itemList.stream()
	        .filter(i -> i.getCodigo() == codigoItem)
	        .findFirst()
	        .orElse(null);

	    if (categoria != null && item != null) {
	        try {
	            categoria.addItem(item);  // Delega a verificação para a classe Categoria
	            System.out.println("Item adicionado com sucesso à categoria " + nomeCategoria);
	        } catch (ItemException e) {
	            System.out.println("Erro ao adicionar item: " + e.getMessage());
	        }
	    } else {
	        System.out.println("Categoria ou Item não encontrado.");
	    }
	}

}
