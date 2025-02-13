package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import controller.HospedagemController;
import controller.MainController;

public class AdicionarItemConta extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JComboBox<String> comboBoxIdHospedagem;
    private JTextField txtCodigoItem;
    private JTextField txtQuantidade;
    private HospedagemController hospedagemController;

    public AdicionarItemConta() {
    	MainController.load();
        hospedagemController = MainController.getHospedagemController();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblIdHospedagem = new JLabel("ID da Hospedagem:");
        lblIdHospedagem.setBounds(10, 10, 120, 20);
        contentPane.add(lblIdHospedagem);

        comboBoxIdHospedagem = new JComboBox<>();
        comboBoxIdHospedagem.setBounds(140, 10, 200, 20);
        contentPane.add(comboBoxIdHospedagem);

        carregarIdsHospedagem();

        JLabel lblCodigoItem = new JLabel("Código do Item:");
        lblCodigoItem.setBounds(10, 40, 120, 20);
        contentPane.add(lblCodigoItem);

        txtCodigoItem = new JTextField();
        txtCodigoItem.setBounds(140, 40, 200, 20);
        contentPane.add(txtCodigoItem);
        txtCodigoItem.setColumns(10);

        JLabel lblQuantidade = new JLabel("Quantidade:");
        lblQuantidade.setBounds(10, 70, 120, 20);
        contentPane.add(lblQuantidade);

        txtQuantidade = new JTextField();
        txtQuantidade.setBounds(140, 70, 200, 20);
        contentPane.add(txtQuantidade);
        txtQuantidade.setColumns(10);

        JButton btnAdicionarItem = new JButton("Adicionar Item");
        btnAdicionarItem.addActionListener(e -> adicionarItem());
        btnAdicionarItem.setBounds(140, 110, 120, 30);
        contentPane.add(btnAdicionarItem);
        MainController.save();
    }

    private void carregarIdsHospedagem() {
        for (var hospedagem : hospedagemController.getHospedagens()) {
            comboBoxIdHospedagem.addItem(hospedagem.getId().toString());
        }
    }

    private void adicionarItem() {
        try {
            String idHospedagem = (String) comboBoxIdHospedagem.getSelectedItem();
            long codigoItem = Long.parseLong(txtCodigoItem.getText());
            int quantidade = Integer.parseInt(txtQuantidade.getText());

            hospedagemController.addItemConta(idHospedagem, codigoItem, quantidade);

            System.out.println("Item adicionado -> Hospedagem ID: " + idHospedagem + 
                               ", Código: " + codigoItem + 
                               ", Quantidade: " + quantidade);

            JOptionPane.showMessageDialog(this, "Item adicionado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

            txtCodigoItem.setText("");
            txtQuantidade.setText("");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, insira valores válidos para código do item e quantidade.", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao adicionar item: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

}
