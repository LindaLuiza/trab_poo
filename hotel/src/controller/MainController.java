package controller;

import java.io.Serializable;

import persistence.Serializer;

// * Design Pattern Singleton 

//TODO - Controllers: Acomodação, Hospede, Pagamento, Conta, Item, Hospede

public class MainController implements Serializable {

	private static final long serialVersionUID = 6341528978400871936L;

	private static MainController instance;

	private AcomodacaoController acomodacaoController;

	// declarar os demais controladores

	private MainController() {

		acomodacaoController = new AcomodacaoController();

		// instanciar os demais controladores

	}

	public static MainController getInstance() {
		return instance;
	}

	public static AcomodacaoController getAcomodacaoController() {
		return instance.acomodacaoController;
	}

	// implementar metodos acessadores estaticos para os demais controladores

	public static void load() {

		instance = Serializer.readFile();

		if (instance == null) {
			instance = new MainController();
		}
	}

	public static void save() {
		Serializer.writeFile(instance);
	}
}
