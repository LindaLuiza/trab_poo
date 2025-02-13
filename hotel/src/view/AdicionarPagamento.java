package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.HospedagemController;
import controller.MainController;
import domain.ETipoPagamento;
import dtos.PagamentoDto;
import exception.HospedagemException;
import exception.PagamentoException;

public class AdicionarPagamento extends JFrame {

    private static final long serialVersionUID = -2097534581997088186L;
	private JPanel contentPane;
    private JComboBox<String> comboBoxIdHospedagem;
    private JTextField txtValorPagamento;
    private JComboBox<String> comboBoxTipoPagamento;


    public AdicionarPagamento() {
    	MainController.load();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        setTitle("Adicionar Pagamento");

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Label e ComboBox para o ID da hospedagem
        JLabel lblIdHospedagem = new JLabel("ID da Hospedagem:");
        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.EAST;
        contentPane.add(lblIdHospedagem, gbc);

        comboBoxIdHospedagem = new JComboBox<>();
        carregarIdsHospedagem(); // Preencher o ComboBox com os IDs
        gbc.gridx = 1; gbc.gridy = 0; gbc.fill = GridBagConstraints.HORIZONTAL;
        contentPane.add(comboBoxIdHospedagem, gbc);

        // Label e ComboBox para o tipo de pagamento
        JLabel lblTipoPagamento = new JLabel("Tipo de Pagamento:");
        gbc.gridx = 0; gbc.gridy = 1;
        contentPane.add(lblTipoPagamento, gbc);

        comboBoxTipoPagamento = new JComboBox<>(new String[]{"Credito", "Debito", "Pix"});
        gbc.gridx = 1; gbc.gridy = 1;
        contentPane.add(comboBoxTipoPagamento, gbc);

        // Label e campo para o valor do pagamento
        JLabel lblValorPagamento = new JLabel("Valor do Pagamento:");
        gbc.gridx = 0; gbc.gridy = 2;
        contentPane.add(lblValorPagamento, gbc);

        txtValorPagamento = new JTextField();
        gbc.gridx = 1; gbc.gridy = 2; gbc.fill = GridBagConstraints.HORIZONTAL;
        contentPane.add(txtValorPagamento, gbc);
        txtValorPagamento.setColumns(10);

        // Botão para adicionar pagamento
        JButton btnAdicionarPagamento = new JButton("Adicionar Pagamento");
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        contentPane.add(btnAdicionarPagamento, gbc);

        btnAdicionarPagamento.addActionListener(this::adicionarPagamento);
    }

    private void carregarIdsHospedagem() {
        HospedagemController hospedagemController = MainController.getHospedagemController();
        
        for (var hospedagem : hospedagemController.getHospedagens()) { // Supondo que existe getHospedagens()
            comboBoxIdHospedagem.addItem(hospedagem.getId().toString()); // Ajuste conforme o tipo de ID
        }
    }


    private void adicionarPagamento(ActionEvent e) {
        MainController.load();

        String idHospedagem = (String) comboBoxIdHospedagem.getSelectedItem();
        String tipoPagamentoStr = (String) comboBoxTipoPagamento.getSelectedItem();
        String valorPagamentoStr = txtValorPagamento.getText().trim();

        if (idHospedagem == null || valorPagamentoStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double valorPagamento = Double.parseDouble(valorPagamentoStr);
            ETipoPagamento tipoPagamento = ETipoPagamento.valueOf(tipoPagamentoStr.toUpperCase().replace(" ", "_"));

            HospedagemController hospedagemController = MainController.getHospedagemController();
            PagamentoDto pagamentoDto = new PagamentoDto(tipoPagamento, new Date(), valorPagamento);
            hospedagemController.addPagamento(idHospedagem, pagamentoDto);

            JOptionPane.showMessageDialog(this, "Pagamento adicionado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            txtValorPagamento.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Valor do pagamento inválido. Insira um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (HospedagemException | PagamentoException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao adicionar pagamento: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
