package main;

import controller.AcomodacaoController;
import controller.MainController;
import domain.Categoria;
import domain.Hospede;
import domain.Item;
import dtos.AcomodacaoDto;
import exception.AcomodacaoException;
import exception.TipoAcomodacaoException;

public class MainTestes {

		public static void teste() {
		Categoria bebida = new Categoria("Bebidas");	
	
		Item c3 = new Item(3, "capivara", 2);
		
		bebida.addItem(c3);
		
		System.out.println(bebida.getNome());
		
		Hospede hospedeiro = new Hospede("666.666.666-66", "Linda Luiza", "linda@email", 12343424);
		System.out.println(hospedeiro.getCpf());	
		
		
		AcomodacaoController acomodacaoController = MainController.getAcomodacaoController();

		try {
			acomodacaoController.createAcomodacao(new AcomodacaoDto(101, 2, "Quarto Simples"));
			acomodacaoController.createAcomodacao(new AcomodacaoDto(102, 3, "Quarto Simples"));
			acomodacaoController.createAcomodacao(new AcomodacaoDto(201, 4, "Suite Master"));
			acomodacaoController.createAcomodacao(new AcomodacaoDto(202, 5, "Suite Master"));
			
		} catch (TipoAcomodacaoException e) {
			System.out.println(e.getMessage());
			
		} catch (AcomodacaoException e) {
			System.out.println(e.getMessage());
		}
				
		
		System.out.println("\nListagem Tipos");
		for (String nomeTipo : acomodacaoController.getKeysTiposAcomodacao()) {
			System.out.println(nomeTipo);
		}

		System.out.println("\nListagem Acomodacoes");
		for (Integer numero : acomodacaoController.getKeysAcomodacoes()) {
			System.out.println(numero);
		}
		
	}
	
}
