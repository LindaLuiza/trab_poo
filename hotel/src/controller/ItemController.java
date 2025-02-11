package controller;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import domain.Categoria;
import domain.Item;
import dtos.CategoriaDto;
import dtos.ItemDto;
import exception.CategoriaException;
import exception.ItemException;

public class ItemController implements Serializable {

	private static final long serialVersionUID = -333031781349286041L;

	private Map<Long, Item> itens;
	private Map<String, Categoria> categorias;

	public ItemController() {
		this.itens = new TreeMap<>();
		this.categorias = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
	}

	public void createItem(ItemDto itemDto) throws ItemException {
		if (itens.containsKey(itemDto.getCodigo())) {
			throw new ItemException("Já existe item com esse código - " + itemDto.getCodigo());
		}
		Item item = new Item(itemDto.getCodigo(), itemDto.getDescricao(), itemDto.getPreco());
		itens.put(item.getCodigo(), item);
		MainController.save();
	}

	public ItemDto getItemByCodigo(long codigo) {
		Item item = itens.get(codigo);
		if (item == null) {
			return null;
		}
		return new ItemDto(item.getCodigo(), item.getDescricao(), item.getPreco());
	}

	public List<ItemDto> getAllItems() {
		return itens.values().stream().map(i -> new ItemDto(i.getCodigo(), i.getDescricao(), i.getPreco()))
				.collect(Collectors.toList());
	}

	public void createCategoria(CategoriaDto categoriaDto) throws CategoriaException {
		if (categorias.containsKey(categoriaDto.getNome())) {
			throw new CategoriaException("Erro: Já existe uma categoria com o nome " + categoriaDto.getNome());
		}
		Categoria categoria = new Categoria(categoriaDto.getNome());
		categorias.put(categoria.getNome(), categoria);

		MainController.save();
	}

	public CategoriaDto getCategoriaByNome(String nome) {
		Categoria categoria = categorias.get(nome);
		if (categoria == null) {
			return null;
		}
		return new CategoriaDto(categoria.getNome(), categoria.getItens().stream()
				.map(i -> new ItemDto(i.getCodigo(), i.getDescricao(), i.getPreco())).collect(Collectors.toList()));
	}

	public List<CategoriaDto> getAllCategorias() {
		return categorias.values().stream().map(c -> new CategoriaDto(c.getNome(), c.getItens().stream()
				.map(i -> new ItemDto(i.getCodigo(), i.getDescricao(), i.getPreco())).collect(Collectors.toList())))
				.collect(Collectors.toList());
	}

	public void addItemToCategoria(String nomeCategoria, long codigoItem) {
		Categoria categoria = categorias.get(nomeCategoria);
		Item item = itens.get(codigoItem);

		if (categoria != null && item != null) {
			try {
				categoria.addItem(item);
				System.out.println("Item adicionado com sucesso à categoria " + nomeCategoria);
				MainController.save();
			} catch (ItemException e) {
				System.err.println("Erro ao adicionar item: " + e.getMessage());
			}
		} else {
			System.err.println("Categoria ou Item não encontrado.");
		}
	}
}
