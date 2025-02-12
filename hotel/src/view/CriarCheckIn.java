package view;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.HospedagemController;
import controller.MainController;
import dtos.AcomodacaoDto;
import dtos.HospedagemDto;
import dtos.HospedeDto;
import exception.AcomodacaoException;
import exception.HospedagemException;
import exception.HospedeException;

public class CriarCheckIn extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtNumeroAcomodacao;
    private JTextField txtCpfHospede;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CriarCheckIn frame = new CriarCheckIn();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public CriarCheckIn() {
    	MainController.load();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 200);
        setTitle("Realizar Check-In");

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);

        // Usar GridBagLayout para um layout responsivo
        GridBagLayout gbl_contentPane = new GridBagLayout();
        gbl_contentPane.columnWidths = new int[] { 0, 0, 0 };
        gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0 };
        gbl_contentPane.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
        gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
        contentPane.setLayout(gbl_contentPane);

        // Label e campo para o número da acomodação
        JLabel lblNumeroAcomodacao = new JLabel("Número da Acomodação:");
        GridBagConstraints gbc_lblNumeroAcomodacao = new GridBagConstraints();
        gbc_lblNumeroAcomodacao.insets = new Insets(0, 0, 5, 5);
        gbc_lblNumeroAcomodacao.anchor = GridBagConstraints.EAST;
        gbc_lblNumeroAcomodacao.gridx = 0;
        gbc_lblNumeroAcomodacao.gridy = 0;
        contentPane.add(lblNumeroAcomodacao, gbc_lblNumeroAcomodacao);

        txtNumeroAcomodacao = new JTextField();
        GridBagConstraints gbc_txtNumeroAcomodacao = new GridBagConstraints();
        gbc_txtNumeroAcomodacao.insets = new Insets(0, 0, 5, 0);
        gbc_txtNumeroAcomodacao.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtNumeroAcomodacao.gridx = 1;
        gbc_txtNumeroAcomodacao.gridy = 0;
        contentPane.add(txtNumeroAcomodacao, gbc_txtNumeroAcomodacao);
        txtNumeroAcomodacao.setColumns(10);

        // Label e campo para o CPF do hóspede
        JLabel lblCpfHospede = new JLabel("CPF do Hóspede:");
        GridBagConstraints gbc_lblCpfHospede = new GridBagConstraints();
        gbc_lblCpfHospede.insets = new Insets(0, 0, 5, 5);
        gbc_lblCpfHospede.anchor = GridBagConstraints.EAST;
        gbc_lblCpfHospede.gridx = 0;
        gbc_lblCpfHospede.gridy = 1;
        contentPane.add(lblCpfHospede, gbc_lblCpfHospede);

        txtCpfHospede = new JTextField();
        GridBagConstraints gbc_txtCpfHospede = new GridBagConstraints();
        gbc_txtCpfHospede.insets = new Insets(0, 0, 5, 0);
        gbc_txtCpfHospede.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtCpfHospede.gridx = 1;
        gbc_txtCpfHospede.gridy = 1;
        contentPane.add(txtCpfHospede, gbc_txtCpfHospede);
        txtCpfHospede.setColumns(10);

        // Botão para realizar o check-in
        JButton btnCheckIn = new JButton("Realizar Check-In");
        GridBagConstraints gbc_btnCheckIn = new GridBagConstraints();
        gbc_btnCheckIn.insets = new Insets(0, 0, 0, 0);
        gbc_btnCheckIn.gridwidth = 2;
        gbc_btnCheckIn.gridx = 0;
        gbc_btnCheckIn.gridy = 2;
        contentPane.add(btnCheckIn, gbc_btnCheckIn);

        btnCheckIn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                realizarCheckIn();
            }
        });
    }

    private void realizarCheckIn() {
        String numeroAcomodacaoStr = txtNumeroAcomodacao.getText().trim();
        String cpfHospede = txtCpfHospede.getText().trim();

        if (numeroAcomodacaoStr.isEmpty() || cpfHospede.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Converter o número da acomodação para inteiro
            int numeroAcomodacao = Integer.parseInt(numeroAcomodacaoStr);

            HospedagemController hospedagemController = MainController.getHospedagemController();

            // Obter Acomodacao do domínio
            domain.Acomodacao acomodacao = MainController.getAcomodacaoController().getAcomodacao(numeroAcomodacao);

            // Converter Acomodacao para AcomodacaoDto
            AcomodacaoDto acomodacaoDto = new AcomodacaoDto(
                acomodacao.getNumero(),
                acomodacao.getOcupacaoMax(),
                acomodacao.getTipo()
            );

            // Obter Hospede do domínio
            domain.Hospede hospede = MainController.getHospedeController().getHospede(cpfHospede);

            // Converter Hospede para HospedeDto
            HospedeDto hospedeDto = new HospedeDto(
                hospede.getCpf(),
                hospede.getNome(),
                hospede.getEmail(),
                hospede.getTelefone()
            );

            // Criar o DTO para a hospedagem
            HospedagemDto hospedagemDto = new HospedagemDto(acomodacaoDto, hospedeDto);

            // Realizar o check-in
            hospedagemController.createHospedagem(hospedagemDto);

            JOptionPane.showMessageDialog(this, "Check-in realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            limparCampos();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Número da acomodação inválido. Insira um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (HospedagemException | AcomodacaoException | HospedeException e) {
            JOptionPane.showMessageDialog(this, "Erro ao realizar o check-in: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }



    private void limparCampos() {
        txtNumeroAcomodacao.setText("");
        txtCpfHospede.setText("");
    }
}