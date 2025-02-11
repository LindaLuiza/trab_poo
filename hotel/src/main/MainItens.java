package main;

import controller.ItemController;
import controller.MainController;
import exception.ItemException;

public class MainItens {

    public static void teste() throws ItemException {
        MainController.load();
        // Obtém o ItemController
		ItemController itemController = MainController.getItemController();

		// Cria os itens usando o controlador
		itemController.createItem(1, "Item 1", 100.0);
		itemController.createItem(2, "Item 2", 200.0);
		itemController.createItem(3, "Item 3", 300.0);

		// Cria a categoria usando o controlador
		itemController.createCategoria("Categoria A");

		// Adiciona os itens à categoria
		itemController.addItemToCategoria("Categoria A", 1);
		itemController.addItemToCategoria("Categoria A", 2);
		itemController.addItemToCategoria("Categoria A", 3);

		System.out.println("Categoria e itens criados com sucesso!");
        MainController.save();
    }

    public static void main(String[] args) throws ItemException {
        teste();
    }
}
