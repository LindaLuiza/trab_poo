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
        // Inicializa o controlador de acomodação
        acomodacaoController = MainController.getAcomodacaoController();

        // Configuração da janela
        setTitle("Lista de Tipos de Acomodação");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout
        setLayout(new BorderLayout());

        // Área de texto para mostrar os tipos de acomodação
        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        // Botão para listar tipos de acomodação
        listarButton = new JButton("Listar Tipos de Acomodação");
        listarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarTiposAcomodacao();
            }
        });

        // Adiciona os componentes na janela
        add(scrollPane, BorderLayout.CENTER);
        add(listarButton, BorderLayout.SOUTH);
    }

    private void listarTiposAcomodacao() {
        textArea.setText("");  // Limpa o texto anterior

        // Obtém a lista de tipos de acomodação
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