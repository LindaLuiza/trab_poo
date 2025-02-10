package controller;

import java.io.Serializable;

import persistence.Serializer;

// * Design Pattern Singleton 

//TODO - Controllers: Item

public class MainController implements Serializable {

	private static final long serialVersionUID = 6341528978400871936L;

	private static MainController instance;

	private AcomodacaoController acomodacaoController;
	private HospedeController hospedeController;
	private HospedagemController hospedagemController;
	private ItemController itemController;

	private MainController() {

		acomodacaoController = new AcomodacaoController();
		hospedeController = new HospedeController();
		hospedagemController = new HospedagemController();
		itemController = new ItemController();

	}

	public static MainController getInstance() {
		return instance;
	}

	public static AcomodacaoController getAcomodacaoController() {
		return instance.acomodacaoController;
	}

	public static HospedeController getHospedeController() {
		return instance.hospedeController;
	}

	public static HospedagemController getHospedagemController() {
		return instance.hospedagemController;
	}

	public static ItemController getItemController() {
		return instance.itemController;
	}

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
