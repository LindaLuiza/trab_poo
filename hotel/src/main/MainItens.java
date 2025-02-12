package main;

import controller.ItemController;
import controller.MainController;
import dtos.CategoriaDto;
import dtos.ItemDto;
import exception.CategoriaException;
import exception.ItemException;

public class MainItens {

    public static void teste(){
        MainController.load();
        ItemController itemController = MainController.getItemController();

        // Criando os itens
        try {
			itemController.createItem(new ItemDto(10000000, "Item 1", 100.0));
		} catch (ItemException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
        try {
			itemController.createItem(new ItemDto(20000000, "Item 2", 200.0));
		} catch (ItemException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());

		}
        try {
			itemController.createItem(new ItemDto(30000000, "Item 3", 300.0));
		} catch (ItemException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());

		}

        // Criando a categoria corretamente
        try {
			itemController.createCategoria(new CategoriaDto("Categoria A"));
		} catch (CategoriaException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());

		}

        // Adicionando os itens à categoria usando os códigos corretos
        itemController.addItemToCategoria("Categoria A", 10000000);
        itemController.addItemToCategoria("Categoria A", 20000000);
        itemController.addItemToCategoria("Categoria A", 30000000);

        System.out.println("Categoria e itens criados com sucesso!");
        MainController.save();
    }

    public static void main(String[] args) {
        teste();
    }
}
