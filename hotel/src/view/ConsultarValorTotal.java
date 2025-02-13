package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import controller.HospedagemController;
import controller.MainController;
import exception.HospedagemException;

public class ConsultarValorTotal extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JComboBox<String> comboBoxIdHospedagem;
    private JLabel lblValorTotal;

    public ConsultarValorTotal() {
        setTitle("Consultar Valor Total");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 400, 250);
        contentPane = new JPanel();
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(new GridLayout(3, 1, 10, 10));

        JPanel panelSelection = new JPanel();
        panelSelection.add(new JLabel("ID da Hospedagem:"));
        comboBoxIdHospedagem = new JComboBox<>();
        carregarIdsHospedagem();
        panelSelection.add(comboBoxIdHospedagem);
        contentPane.add(panelSelection);

        JPanel panelButton = new JPanel();
        JButton btnConsultar = new JButton("Consultar");
        btnConsultar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                consultarValorTotal();
            }
        });
        panelButton.add(btnConsultar);
        contentPane.add(panelButton);

        JPanel panelResult = new JPanel();
        lblValorTotal = new JLabel("Valor Total: R$ 0,00");
        panelResult.add(lblValorTotal);
        contentPane.add(panelResult);

        setVisible(true);
    }

    private void carregarIdsHospedagem() {
        HospedagemController hospedagemController = MainController.getHospedagemController();
        for (var hospedagem : hospedagemController.getHospedagens()) {
            comboBoxIdHospedagem.addItem(hospedagem.getId().toString());
        }
    }

    private void consultarValorTotal() {
        String hospedagemId = (String) comboBoxIdHospedagem.getSelectedItem();
        if (hospedagemId != null) {
            HospedagemController hospedagemController = MainController.getHospedagemController();
            double valorTotal = 0;
			try {
				valorTotal = hospedagemController.getSaldoTotal(hospedagemId);
			} catch (HospedagemException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            lblValorTotal.setText("Valor Total: R$ " + String.format("%.2f", valorTotal));
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma hospedagem!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
