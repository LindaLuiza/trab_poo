package view;

import controller.ItemController;
import controller.MainController;
import dtos.CategoriaDto;
import dtos.ItemDto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ListarCategoria extends JFrame {

	private static final long serialVersionUID = -7030772270234300112L;
	private JTextArea textArea;
    private JButton listarButton;
    private ItemController itemController;

    public ListarCategoria() {
        MainController.load();
        // Inicializa o controlador de itens e categorias
        itemController = MainController.getItemController();

        // Configuração da janela
        setTitle("Lista de Categorias");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout
        setLayout(new BorderLayout());

        // Área de texto para mostrar as categorias e seus itens
        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        // Botão para listar categorias
        listarButton = new JButton("Listar Categorias");
        listarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarCategorias();
            }
        });

        // Adiciona os componentes na janela
        add(scrollPane, BorderLayout.CENTER);
        add(listarButton, BorderLayout.SOUTH);
    }

    private void listarCategorias() {
        textArea.setText("");  // Limpa o texto anterior

        // Obtém a lista de categorias
        List<CategoriaDto> categorias = itemController.getAllCategorias();

        if (categorias.isEmpty()) {
            textArea.append("Nenhuma categoria encontrada.\n");
        } else {
            for (CategoriaDto categoria : categorias) {
                textArea.append("Categoria: " + categoria.getNome() + "\n");

                List<ItemDto> itens = categoria.getItens();
                if (itens.isEmpty()) {
                    textArea.append("  Nenhum item nesta categoria.\n");
                } else {
                    for (ItemDto item : itens) {
                        textArea.append(
                            "  Código: " + item.getCodigo() + ", " +
                            "Descrição: " + item.getDescricao() + ", " +
                            "Preço: R$" + item.getPreco() + "\n"
                        );
                    }
                }
                textArea.append("\n"); // Linha em branco entre categorias
            }
        }
    }
}
