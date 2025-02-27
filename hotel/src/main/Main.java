package main;

import java.awt.EventQueue;

import controller.MainController;
import view.MenuView;

public class Main {

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {

			public void run() {

				try {
					MainController.load();

					MenuView frame = new MenuView();
					frame.setVisible(true);
					
					MainAcomodacoes.teste();
					MainHospedes.teste();
					MainItens.teste();
					MainHospedagens.teste();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
