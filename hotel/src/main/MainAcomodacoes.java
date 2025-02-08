package main;

import controller.AcomodacaoController;
import controller.MainController;
import dtos.AcomodacaoDto;
import dtos.TipoAcomodacaoDto;
import exception.AcomodacaoException;
import exception.TipoAcomodacaoException;

public class MainAcomodacoes {

	public static void teste() {

		AcomodacaoController acomodacaoController = MainController.getAcomodacaoController();

		try {
			acomodacaoController.createTipoAcomodacao(new TipoAcomodacaoDto("Suite Master", 2100, 300));
			acomodacaoController.createTipoAcomodacao(new TipoAcomodacaoDto("Quarto Simples", 500, 70));
			acomodacaoController.createTipoAcomodacao(new TipoAcomodacaoDto("Suite Master", 2100, 300));

		} catch (TipoAcomodacaoException e) {
			System.out.println(e.getMessage());
		}

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
