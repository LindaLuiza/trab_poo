package view;

import controller.AcomodacaoController;
import controller.MainController;
import dtos.AcomodacaoDto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ListarAcomodacoes extends JFrame {

    private static final long serialVersionUID = 4109418822854478308L;
	private JTextArea textArea;
    private JButton listarButton;
    private AcomodacaoController acomodacaoController;

    public ListarAcomodacoes() {
    	MainController.load();
      
        acomodacaoController = MainController.getAcomodacaoController();

 
        setTitle("Lista de Acomodações");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);


        setLayout(new BorderLayout());


        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        listarButton = new JButton("Listar Acomodações");
        listarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarAcomodacoes();
            }
        });

    
        add(scrollPane, BorderLayout.CENTER);
        add(listarButton, BorderLayout.SOUTH);
    }

    private void listarAcomodacoes() {
        textArea.setText("");

        List<AcomodacaoDto> acomodacoes = acomodacaoController.getAcomodacoes();

        if (acomodacoes.isEmpty()) {
            textArea.append("Nenhuma acomodação encontrada.\n");
        } else {
            for (AcomodacaoDto a : acomodacoes) {
                textArea.append(
                    "Número: " + a.getNumero() + ", " +
                    "Tipo: " + a.getTipo() + ", " +
                    "Ocup. Máx.: " + a.getOcupacaoMax() + ", " +
                    "Estado: " + a.getEstadoOcupacao() + ", " +
                    "Tarifa Diária: R$" + a.getTarifaDiaria() + ", " +
                    "Adicional Acompanhante: R$" + a.getAdicionalAcompanhante() +
                    "\n"
                );
            }
        }
    }
}


