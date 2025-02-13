package view;

import controller.ItemController;
import controller.MainController;
import dtos.ItemDto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ListarItens extends JFrame {

    private static final long serialVersionUID = 7791782717052727715L;
	private JTextArea textArea;
    private JButton listarButton;
    private ItemController itemController;

    public ListarItens() {
    	MainController.load();
        
        itemController = MainController.getItemController();

        setTitle("Lista de Itens");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        listarButton = new JButton("Listar Itens");
        listarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarItens();
            }
        });

        add(scrollPane, BorderLayout.CENTER);
        add(listarButton, BorderLayout.SOUTH);
    }

    private void listarItens() {
        textArea.setText("");  

        List<ItemDto> itens = itemController.getAllItems();

        if (itens.isEmpty()) {
            textArea.append("Nenhum item encontrado.\n");
        } else {
            for (ItemDto item : itens) {
                textArea.append(
                    "Código: " + item.getCodigo() + ", " +
                    "Descrição: " + item.getDescricao() + ", " +
                    "Preço: R$" + item.getPreco() +
                    "\n"
                );
            }
        }
    }
}