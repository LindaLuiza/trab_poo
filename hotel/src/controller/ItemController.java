package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

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
        List<ItemDto> lista = new ArrayList<>();
        Set<Map.Entry<Long, Item>> entries = itens.entrySet();

        for (Map.Entry<Long, Item> e : entries) {
            Item item = e.getValue();
            lista.add(new ItemDto(item.getCodigo(), item.getDescricao(), item.getPreco()));
        }
        return lista;
    }

    public void createCategoria(CategoriaDto categoriaDto) throws CategoriaException {
        if (categorias.containsKey(categoriaDto.getNome())) {
            throw new CategoriaException(categoriaDto.getNome());
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

        List<ItemDto> itensDto = new ArrayList<>();
        for (Item item : categoria.getItens()) {
            itensDto.add(new ItemDto(item.getCodigo(), item.getDescricao(), item.getPreco()));
        }

        return new CategoriaDto(categoria.getNome(), itensDto);
    }

    public List<CategoriaDto> getAllCategorias() {
        List<CategoriaDto> lista = new ArrayList<>();
        Set<Map.Entry<String, Categoria>> entries = categorias.entrySet();

        for (Map.Entry<String, Categoria> e : entries) {
            Categoria categoria = e.getValue();
            List<ItemDto> itensDto = new ArrayList<>();
            
            for (Item item : categoria.getItens()) {
                itensDto.add(new ItemDto(item.getCodigo(), item.getDescricao(), item.getPreco()));
            }
            
            lista.add(new CategoriaDto(categoria.getNome(), itensDto));
        }
        return lista;
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
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Categoria ou Item não encontrado.");
        }
    }
}
