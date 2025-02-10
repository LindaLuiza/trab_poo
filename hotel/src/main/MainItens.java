package main;

import java.util.List;

import domain.Categoria;
import domain.Item;
import exception.ItemException;

public class MainItens {
	
	public static void teste() {
        try {
            Item item1 = new Item(1, "Item 1", 100.0);
            Item item2 = new Item(2, "Item 2", 200.0);
            Item item3 = new Item(3, "Item 3", 300.0);

            Categoria categoria = new Categoria("Categoria A");

            categoria.addItem(item1);
            categoria.addItem(item2);
            categoria.addItem(item3);

            System.out.println("Itens da Categoria: " + categoria.getNome());
            List<Item> itens = categoria.getItens();
            for (Item item : itens) {
                System.out.println("Código: " + item.getCodigo() + ", Descrição: " + item.getDescricao() + ", Preço: " + item.getPreco());
            }

        } catch (ItemException e) {
            System.out.println("Erro ao criar item: " + e.getMessage());
        }
    }

}
