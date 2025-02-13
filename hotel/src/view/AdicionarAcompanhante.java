package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.HospedagemController;
import controller.MainController;
import dtos.HospedagemDto;
import dtos.HospedeDto;

public class AdicionarAcompanhante extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JComboBox<String> comboBoxIdHospedagem;
    private JTextField CPFField;
    private JTextField NomeField;
    private JTextField EmailField;
    private JTextField TelefoneField;

    public AdicionarAcompanhante() {
        setTitle("Adicionar Acompanhante");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 250);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblHospedagem = new JLabel("Hospedagem ID:");
        lblHospedagem.setBounds(30, 12, 120, 20);
        contentPane.add(lblHospedagem);

        comboBoxIdHospedagem = new JComboBox<>();
        comboBoxIdHospedagem.setBounds(149, 12, 200, 25);
        contentPane.add(comboBoxIdHospedagem);

        JButton btnAdicionar = new JButton("Adicionar");
        btnAdicionar.setBounds(149, 171, 120, 30);
        contentPane.add(btnAdicionar);

        JLabel lblCPF = new JLabel("CPF:");
        lblCPF.setBounds(30, 50, 120, 20);
        contentPane.add(lblCPF);

        CPFField = new JTextField();
        CPFField.setBounds(149, 50, 200, 20);
        contentPane.add(CPFField);
        CPFField.setColumns(10);

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(30, 80, 120, 20);
        contentPane.add(lblNome);

        NomeField = new JTextField();
        NomeField.setBounds(149, 80, 200, 20);
        contentPane.add(NomeField);
        NomeField.setColumns(10);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(30, 110, 120, 20);
        contentPane.add(lblEmail);

        EmailField = new JTextField();
        EmailField.setBounds(149, 110, 200, 20);
        contentPane.add(EmailField);
        EmailField.setColumns(10);

        JLabel lblTelefone = new JLabel("Telefone:");
        lblTelefone.setBounds(30, 140, 120, 20);
        contentPane.add(lblTelefone);

        TelefoneField = new JTextField();
        TelefoneField.setBounds(149, 140, 200, 20);
        contentPane.add(TelefoneField);
        TelefoneField.setColumns(10);

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
            comboBoxIdHospedagem.addItem(hospedagem.getId());
        }
    }

    private void adicionarAcompanhante() {
        String hospedagemId = (String) comboBoxIdHospedagem.getSelectedItem();
        String cpf = CPFField.getText();
        String nome = NomeField.getText();
        String email = EmailField.getText();
        String telefone = TelefoneField.getText();

        if (hospedagemId == null || cpf.isEmpty() || nome.isEmpty() || email.isEmpty() || telefone.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            HospedeDto acompanhante = new HospedeDto(cpf, nome, email, Long.parseLong(telefone));

            HospedagemController hospedagemController = MainController.getHospedagemController();
            hospedagemController.adicionarAcompanhante(hospedagemId, acompanhante);

            JOptionPane.showMessageDialog(this, "Acompanhante adicionado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

            CPFField.setText("");
            NomeField.setText("");
            EmailField.setText("");
            TelefoneField.setText("");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Telefone deve ser um número válido!", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao adicionar acompanhante: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}