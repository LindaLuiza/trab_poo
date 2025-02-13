package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;

import controller.HospedagemController;
import controller.MainController;
import dtos.HospedagemDto;
import exception.HospedagemException;

public class ConsultarSaldoDevedor extends JFrame {

    private static final long serialVersionUID = -4208440609121498674L;
	private JPanel contentPane;
    private JComboBox<String> comboBoxIdHospedagem;
    private JLabel lblSaldoDevedor;

    public ConsultarSaldoDevedor() {
        setTitle("Consultar Saldo Devedor");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 400, 250);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblHospedagem = new JLabel("Hospedagem ID:");
        lblHospedagem.setBounds(30, 30, 120, 20);
        contentPane.add(lblHospedagem);

        comboBoxIdHospedagem = new JComboBox<>();
        comboBoxIdHospedagem.setBounds(160, 30, 180, 25);
        contentPane.add(comboBoxIdHospedagem);

        JButton btnConsultar = new JButton("Consultar Saldo");
        btnConsultar.setBounds(120, 80, 150, 30);
        contentPane.add(btnConsultar);

        lblSaldoDevedor = new JLabel("Saldo Devedor: R$ 0,00");
        lblSaldoDevedor.setBounds(30, 130, 300, 20);
        contentPane.add(lblSaldoDevedor);

        carregarIdsHospedagem();

        btnConsultar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                consultarSaldoDevedor();
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

    private void consultarSaldoDevedor() {
    	MainController.load();
        String hospedagemId = (String) comboBoxIdHospedagem.getSelectedItem();
        if (hospedagemId == null) {
            JOptionPane.showMessageDialog(this, "Selecione uma hospedagem!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        HospedagemController hospedagemController = MainController.getHospedagemController();
        double saldoDevedor = 0;
		try {
			saldoDevedor = hospedagemController.getSaldoDevedor(hospedagemId);
		} catch (HospedagemException e) {
			
			e.printStackTrace();
		}

        lblSaldoDevedor.setText("Saldo Devedor: R$ " + String.format("%.2f", saldoDevedor));
    }
}
