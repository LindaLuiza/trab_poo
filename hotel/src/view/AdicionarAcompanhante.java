package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import controller.HospedagemController;
import controller.MainController;
import dtos.HospedagemDto;

import java.util.List;

public class AdicionarAcompanhante extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtNomeAcompanhante;
    private JComboBox<String> comboBoxIdHospedagem;
    
    public AdicionarAcompanhante() {
        setTitle("Adicionar Acompanhante");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 250);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblHospedagem = new JLabel("Hospedagem ID:");
        lblHospedagem.setBounds(30, 30, 120, 20);
        contentPane.add(lblHospedagem);

        comboBoxIdHospedagem = new JComboBox<>();
        comboBoxIdHospedagem.setBounds(160, 30, 200, 25);
        contentPane.add(comboBoxIdHospedagem);

        JLabel lblNomeAcompanhante = new JLabel("Nome do Acompanhante:");
        lblNomeAcompanhante.setBounds(30, 70, 180, 20);
        contentPane.add(lblNomeAcompanhante);

        txtNomeAcompanhante = new JTextField();
        txtNomeAcompanhante.setBounds(160, 70, 200, 25);
        contentPane.add(txtNomeAcompanhante);
        txtNomeAcompanhante.setColumns(10);

        JButton btnAdicionar = new JButton("Adicionar");
        btnAdicionar.setBounds(150, 120, 120, 30);
        contentPane.add(btnAdicionar);

        carregarIdsHospedagem();

        btnAdicionar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                adicionarAcompanhante();
            }
        });
    }

    private void carregarIdsHospedagem() {
        MainController.load();
        HospedagemController hospedagemController = MainController.getHospedagemController();
        List<HospedagemDto> hospedagens = hospedagemController.getHospedagens();

        for (HospedagemDto hospedagem : hospedagens) {
            comboBoxIdHospedagem.addItem(hospedagem.getId().toString());
        }
    }

    private void adicionarAcompanhante() {
        String hospedagemId = (String) comboBoxIdHospedagem.getSelectedItem();
        String nomeAcompanhante = txtNomeAcompanhante.getText().trim();

        if (hospedagemId == null || nomeAcompanhante.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        HospedagemController hospedagemController = MainController.getHospedagemController();
        boolean sucesso = hospedagemController.

        if (sucesso) {
            JOptionPane.showMessageDialog(this, "Acompanhante adicionado com sucesso!");
            txtNomeAcompanhante.setText(""); // Limpa o campo
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao adicionar acompanhante!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
