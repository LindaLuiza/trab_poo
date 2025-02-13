package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import controller.HospedagemController;
import controller.MainController;
import domain.Hospedagem;
import domain.Hospede;
import dtos.HospedagemDto;
import exception.HospedagemException;

public class ListarAcompanhantes extends JFrame {

    private static final long serialVersionUID = 107133454397224150L;
    private JTable table;
    private DefaultTableModel tableModel;
    private JComboBox<String> comboBoxHospedagem;

    public ListarAcompanhantes() {
        setTitle("Listar Acompanhantes");
        setSize(500, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());
        setContentPane(panel);

     
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Selecione a hospedagem:"));

        comboBoxHospedagem = new JComboBox<>();
        carregarHospedagens(); 
        topPanel.add(comboBoxHospedagem);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(e -> buscarAcompanhantes());
        topPanel.add(btnBuscar);

        panel.add(topPanel, BorderLayout.NORTH);

   
        tableModel = new DefaultTableModel();
        tableModel.addColumn("CPF");
        tableModel.addColumn("Nome");
        tableModel.addColumn("Email");
        tableModel.addColumn("Telefone");

        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);
    }

    private void carregarHospedagens() {
        HospedagemController hospedagemController = MainController.getHospedagemController();
        List<HospedagemDto> hospedagens = hospedagemController.getHospedagens(); 

        if (hospedagens != null) {
            for (HospedagemDto hospedagem : hospedagens) {
                comboBoxHospedagem.addItem(hospedagem.getId()); 
            }
        }
    }

    private void buscarAcompanhantes() {
        String hospedagemId = (String) comboBoxHospedagem.getSelectedItem();
        if (hospedagemId != null) {
            carregarAcompanhantes(hospedagemId);
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma hospedagem válida!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void carregarAcompanhantes(String hospedagemId) {
        tableModel.setRowCount(0); 
        HospedagemController hospedagemController = MainController.getHospedagemController();
        
        try {
            Hospedagem hospedagem = hospedagemController.getHospedagemById(hospedagemId);

            if (hospedagem == null) {
                JOptionPane.showMessageDialog(this, "Hospedagem não encontrada!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            List<Hospede> acompanhantes = hospedagem.getAcompanhantes();

            if (acompanhantes == null || acompanhantes.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nenhum acompanhante encontrado!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            for (Hospede hospede : acompanhantes) {
                tableModel.addRow(new Object[]{
                    hospede.getCpf(),
                    hospede.getNome(),
                    hospede.getEmail(),
                    hospede.getTelefone()
                });
            }
        } catch (HospedagemException e) {
            JOptionPane.showMessageDialog(this, "Erro ao buscar hospedagem: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void exibirAcompanhantes() {
        SwingUtilities.invokeLater(() -> {
            ListarAcompanhantes frame = new ListarAcompanhantes();
            frame.setVisible(true);
        });
    }
}
