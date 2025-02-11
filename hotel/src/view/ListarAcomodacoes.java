package view;

import controller.AcomodacaoController;
import controller.MainController;
import dtos.AcomodacaoDto;
import dtos.TipoAcomodacaoDto;
import exception.AcomodacaoException;
import exception.TipoAcomodacaoException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ListarAcomodacoes extends JFrame {

    private JTextArea textArea;
    private JButton listarButton;
    private AcomodacaoController acomodacaoController;

    public ListarAcomodacoes() {
    	MainController.load();
        // Inicializa o controlador
        acomodacaoController = MainController.getAcomodacaoController();

        // Configuração da janela
        setTitle("Lista de Acomodações");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout
        setLayout(new BorderLayout());

        // Área de texto para mostrar as acomodações
        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        // Botão para listar acomodações
        listarButton = new JButton("Listar Acomodações");
        listarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarAcomodacoes();
            }
        });

        // Adiciona os componentes na janela
        add(scrollPane, BorderLayout.CENTER);
        add(listarButton, BorderLayout.SOUTH);
    }

    private void listarAcomodacoes() {
        textArea.setText("");  // Limpa o texto anterior

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
