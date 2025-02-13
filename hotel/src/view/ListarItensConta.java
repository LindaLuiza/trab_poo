package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import controller.HospedagemController;
import controller.MainController;
import domain.ItemConta;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ListarItensConta extends JFrame {
    
    private static final long serialVersionUID = -297695865539444270L;
	private JPanel contentPane;
    private JComboBox<String> comboBoxIdHospedagem;
    private JTable table;
    private DefaultTableModel tableModel;
    private HospedagemController hospedagemController;

    public ListarItensConta() {
        MainController.load();
        hospedagemController = MainController.getHospedagemController();
        
        setTitle("Listar Itens da Conta");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblIdHospedagem = new JLabel("ID da Hospedagem:");
        lblIdHospedagem.setBounds(10, 10, 120, 25);
        contentPane.add(lblIdHospedagem);
        
        comboBoxIdHospedagem = new JComboBox<>();
        comboBoxIdHospedagem.setBounds(140, 10, 200, 25);
        contentPane.add(comboBoxIdHospedagem);
        carregarIdsHospedagem();
        
        JButton btnListar = new JButton("Listar Itens");
        btnListar.setBounds(360, 10, 120, 25);
        contentPane.add(btnListar);
        btnListar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listarItens();
            }
        });
        
        tableModel = new DefaultTableModel(new Object[][] {}, new String[] {"Código", "Descrição", "Quantidade", "Preço Unitário", "Total"});
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 50, 560, 300);
        contentPane.add(scrollPane);
    }

    private void carregarIdsHospedagem() {
        for (String id : hospedagemController.getKeysHospedagens()) {
            comboBoxIdHospedagem.addItem(id);
        }
    }

    private void listarItens() {
        String idHospedagem = (String) comboBoxIdHospedagem.getSelectedItem();
        if (idHospedagem == null) {
            JOptionPane.showMessageDialog(this, "Selecione uma hospedagem!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        List<ItemConta> itens = hospedagemController.listarItensConta(idHospedagem);
        tableModel.setRowCount(0);
        double totalConta = 0;

        for (ItemConta item : itens) {
            double totalItem = item.getQtde() * item.getItem().getPreco();
            totalConta += totalItem;
            tableModel.addRow(new Object[]{
                    item.getItem().getCodigo(),
                    item.getItem().getDescricao(),
                    item.getQtde(),
                    String.format("R$ %.2f", item.getItem().getPreco()),
                    String.format("R$ %.2f", totalItem)
            });
        }

        tableModel.addRow(new Object[]{"", "", "", "Total da Conta:", String.format("R$ %.2f", totalConta)});
    }

}
