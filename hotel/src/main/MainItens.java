package main;

import controller.ItemController;
import controller.MainController;
import dtos.ItemDto;
import exception.ItemException;

public class MainItens {

    public static void teste() throws ItemException {
        MainController.load();
		ItemController itemController = MainController.getItemController();

		itemController.createItem(new ItemDto (10000000, "Item 1", 100.0));
		itemController.createItem(new ItemDto(20000000, "Item 2", 200.0));
		itemController.createItem(new ItemDto (30000000, "Item 3", 300.0));

		itemController.createCategoria("Categoria A");

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
