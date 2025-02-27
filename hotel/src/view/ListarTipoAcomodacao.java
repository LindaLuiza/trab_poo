package view;

import controller.AcomodacaoController;
import controller.MainController;
import dtos.TipoAcomodacaoDto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ListarTipoAcomodacao extends JFrame {

    private static final long serialVersionUID = -6515561218278404223L;
	private JTextArea textArea;
    private JButton listarButton;
    private AcomodacaoController acomodacaoController; 
    public ListarTipoAcomodacao() {
        MainController.load();
        acomodacaoController = MainController.getAcomodacaoController();

        setTitle("Lista de Tipos de Acomodação");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());
        
        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        listarButton = new JButton("Listar Tipos de Acomodação");
        listarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarTiposAcomodacao();
            }
        });

        add(scrollPane, BorderLayout.CENTER);
        add(listarButton, BorderLayout.SOUTH);
    }

    private void listarTiposAcomodacao() {
        textArea.setText("");  

        List<TipoAcomodacaoDto> tiposAcomodacao = acomodacaoController.getTiposAcomodacoes();

        if (tiposAcomodacao.isEmpty()) {
            textArea.append("Nenhum tipo de acomodação encontrado.\n");
        } else {
            for (TipoAcomodacaoDto tipo : tiposAcomodacao) {
                textArea.append(
                    "Nome: " + tipo.getName() + ", " +
                    "Tarifa Diária: R$" + tipo.getTarifaDiaria() + ", " +
                    "Adicional Acompanhante: R$" + tipo.getAdicionalAcompanhante() +
                    "\n"
                );
            }
        }
    }
}