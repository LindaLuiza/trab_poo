package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import domain.Categoria;
import domain.Item;
import dtos.CategoriaDto;
import dtos.ItemDto;
import exception.ItemException;

public class ItemController {

	private List<Item> itemList = new ArrayList<>();
	private List<Categoria> categoriaList = new ArrayList<>();

	public void createItem(long codigo, String descricao, double preco) {
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
}
