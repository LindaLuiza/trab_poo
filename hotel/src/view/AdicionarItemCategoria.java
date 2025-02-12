package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import controller.ItemController;
import controller.MainController;
import dtos.CategoriaDto;
import dtos.ItemDto;

public class AdicionarItemCategoria extends JFrame {

    private static final long serialVersionUID = 7467581974801953161L;
	private JPanel contentPane;
    private ItemController itemController;
    private JComboBox<String> categoriaComboBox;
    private JList<ItemDto> itemList;
    private DefaultListModel<ItemDto> listModel;

    public AdicionarItemCategoria() {
        MainController.load();
        itemController = MainController.getItemController();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10)); // Margens internas
        setContentPane(contentPane);

        // Usar GridBagLayout para um layout responsivo
        GridBagLayout gbl_contentPane = new GridBagLayout();
        gbl_contentPane.columnWidths = new int[] { 0, 0, 0 };
        gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0 };
        gbl_contentPane.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
        gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
        contentPane.setLayout(gbl_contentPane);

        // Label e ComboBox para Categoria
        JLabel lblCategoria = new JLabel("Categoria:");
        GridBagConstraints gbc_lblCategoria = new GridBagConstraints();
        gbc_lblCategoria.insets = new Insets(0, 0, 5, 5);
        gbc_lblCategoria.anchor = GridBagConstraints.EAST;
        gbc_lblCategoria.gridx = 0;
        gbc_lblCategoria.gridy = 0;
        contentPane.add(lblCategoria, gbc_lblCategoria);

        categoriaComboBox = new JComboBox<>();
        GridBagConstraints gbc_categoriaComboBox = new GridBagConstraints();
        gbc_categoriaComboBox.insets = new Insets(0, 0, 5, 0);
        gbc_categoriaComboBox.fill = GridBagConstraints.HORIZONTAL;
        gbc_categoriaComboBox.gridx = 1;
        gbc_categoriaComboBox.gridy = 0;
        contentPane.add(categoriaComboBox, gbc_categoriaComboBox);

        // Label e JList para Itens
        JLabel lblItem = new JLabel("Item:");
        GridBagConstraints gbc_lblItem = new GridBagConstraints();
        gbc_lblItem.insets = new Insets(0, 0, 5, 5);
        gbc_lblItem.anchor = GridBagConstraints.NORTHEAST;
        gbc_lblItem.gridx = 0;
        gbc_lblItem.gridy = 1;
        contentPane.add(lblItem, gbc_lblItem);

        listModel = new DefaultListModel<>();
        itemList = new JList<>(listModel);
        itemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(itemList);
        GridBagConstraints gbc_scrollPane = new GridBagConstraints();
        gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
        gbc_scrollPane.fill = GridBagConstraints.BOTH;
        gbc_scrollPane.gridx = 1;
        gbc_scrollPane.gridy = 1;
        contentPane.add(scrollPane, gbc_scrollPane);

        // Botão para adicionar item à categoria
        JButton btnAdicionar = new JButton("Adicionar Item à Categoria");
        GridBagConstraints gbc_btnAdicionar = new GridBagConstraints();
        gbc_btnAdicionar.insets = new Insets(0, 0, 0, 0);
        gbc_btnAdicionar.gridwidth = 2;
        gbc_btnAdicionar.gridx = 0;
        gbc_btnAdicionar.gridy = 3;
        contentPane.add(btnAdicionar, gbc_btnAdicionar);

        btnAdicionar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                adicionarItemACategoria();
            }
        });

        carregarCategorias();
        carregarItens();
    }

    private void carregarCategorias() {
        List<CategoriaDto> categorias = itemController.getAllCategorias();
        for (CategoriaDto categoria : categorias) {
            categoriaComboBox.addItem(categoria.getNome());
        }
    }

    private void carregarItens() {
        List<ItemDto> itens = itemController.getAllItems();
        for (ItemDto item : itens) {
            listModel.addElement(item);
        }
    }

    private void adicionarItemACategoria() {
        String nomeCategoria = (String) categoriaComboBox.getSelectedItem();
        ItemDto itemSelecionado = itemList.getSelectedValue();

        if (nomeCategoria != null && itemSelecionado != null) {
            // Verifica se o item já existe na categoria
            CategoriaDto categoria = itemController.getCategoriaByNome(nomeCategoria);
            if (categoria != null) {
                boolean itemJaExiste = categoria.getItens().stream()
                        .anyMatch(item -> item.getCodigo() == itemSelecionado.getCodigo());

                if (itemJaExiste) {
                    JOptionPane.showMessageDialog(this, "Este item já existe na categoria selecionada.", "Aviso", JOptionPane.WARNING_MESSAGE);
                } else {
                    itemController.addItemToCategoria(nomeCategoria, itemSelecionado.getCodigo());
                    JOptionPane.showMessageDialog(this, "Item adicionado à categoria com sucesso!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecione uma categoria e um item.");
        }
    }
}