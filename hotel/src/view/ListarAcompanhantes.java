package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import controller.HospedagemController;
import controller.MainController;
import domain.Hospedagem;
import domain.Hospede;
import exception.HospedagemException;

public class ListarAcompanhantes extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTable table;
    private DefaultTableModel tableModel;

    public ListarAcompanhantes(String hospedagemId) {
        setTitle("Acompanhantes da Hospedagem " + hospedagemId);
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        // Painel principal
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        setContentPane(panel);
        
        // Modelo da tabela
        tableModel = new DefaultTableModel();
        tableModel.addColumn("CPF");
        tableModel.addColumn("Nome");
        tableModel.addColumn("Email");
        tableModel.addColumn("Telefone");

        // Criando a tabela
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Carregar dados dos acompanhantes
        carregarAcompanhantes(hospedagemId);
    }

    private void carregarAcompanhantes(String hospedagemId) {
        HospedagemController hospedagemController = MainController.getHospedagemController();
        
        try {
            // Buscar a hospedagem pelo ID
            Hospedagem hospedagem = hospedagemController.getHospedagemById(hospedagemId);
            
            if (hospedagem == null) {
                JOptionPane.showMessageDialog(this, "Hospedagem não encontrada!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Obter a lista de acompanhantes
            List<Hospede> acompanhantes = hospedagem.getAcompanhantes();

            if (acompanhantes == null || acompanhantes.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nenhum acompanhante encontrado!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            // Adicionar os acompanhantes na tabela
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


    public static void exibirAcompanhantes(String hospedagemId) {
        SwingUtilities.invokeLater(() -> {
            ListarAcompanhantes frame = new ListarAcompanhantes(hospedagemId);
            frame.setVisible(true);
        });
    }
}