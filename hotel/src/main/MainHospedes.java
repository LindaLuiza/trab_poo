package main;

import controller.HospedeController;
import controller.MainController;
import dtos.HospedeDto;
import exception.HospedeException;

public class MainHospedes {

	public static void teste() {
		HospedeController hospedeController = MainController.getHospedeController();
		
		try {
			
			hospedeController.createHospede(new HospedeDto("13006639790", "Linda", "linda@email.com", 999999999));
		}
		catch (HospedeException e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println("\nListagem Hospedes");
		for (String cpf : hospedeController.getKeysHospedes()) {
			System.out.println(cpf);
		}
		
	}
	
}
