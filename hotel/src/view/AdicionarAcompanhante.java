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
        
        JPanel CPF = new JPanel();
        CPF.setBounds(30, 51, 155, 29);
        contentPane.add(CPF);
        
        JLabel CPFButton = new JLabel("CPF");
        CPF.add(CPFButton);
        
        CPFField = new JTextField();
        CPFField.setColumns(10);
        CPF.add(CPFField);
        
        JPanel Nome = new JPanel();
        Nome.setBounds(250, 51, 169, 29);
        contentPane.add(Nome);
        
        JLabel NomeButton = new JLabel("Nome");
        Nome.add(NomeButton);
        
        NomeField = new JTextField();
        NomeField.setColumns(10);
        Nome.add(NomeField);
        
        JPanel Email = new JPanel();
        Email.setBounds(30, 104, 166, 29);
        contentPane.add(Email);
        
        JLabel EmailButton = new JLabel("Email");
        Email.add(EmailButton);
        
        EmailField = new JTextField();
        EmailField.setColumns(10);
        Email.add(EmailField);
        
        JPanel Telefone = new JPanel();
        Telefone.setBounds(219, 104, 200, 29);
        contentPane.add(Telefone);
        
        JLabel TelefoneButton = new JLabel("Telefone");
        Telefone.add(TelefoneButton);
        
        TelefoneField = new JTextField();
        TelefoneField.setColumns(10);
        Telefone.add(TelefoneField);

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
        String cpf = CPFField.getText();
		String nome = NomeField.getText();
		String email = EmailField.getText();
		long telefone = Long.parseLong(TelefoneField.getText());

        if (hospedagemId == null || cpf.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        HospedagemController hospedagemController = MainController.getHospedagemController();
        boolean sucesso = hospedagemController.adicionarAcompanhante(hospedagemId, new HospedeDto(cpf, nome, email, telefone));

        if (sucesso) {
            JOptionPane.showMessageDialog(this, "Acompanhante adicionado com sucesso!");
            cpf.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao adicionar acompanhante!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
