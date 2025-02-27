package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import controller.HospedagemController;
import controller.MainController;
import dtos.HospedagemDto;
import exception.HospedagemException;

public class CriarCheckOut extends JFrame {

    private static final long serialVersionUID = -2918591675458360336L;

    private JPanel ContentPanel;
    private JTextField DataCheckOutField;
    private JComboBox<String> comboBoxIdHospedagem; 
    public CriarCheckOut() {
        ContentPanel = new JPanel();
        setContentPane(ContentPanel);

        ContentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        JPanel panel = new JPanel();

        JButton btnNewButton = new JButton("Add CheckOut");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                realizeCheckOut();
            }
        });

        GroupLayout gl_contentPanel = new GroupLayout(ContentPanel);
        gl_contentPanel.setHorizontalGroup(
            gl_contentPanel.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPanel.createSequentialGroup()
                    .addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPanel.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(panel, GroupLayout.PREFERRED_SIZE, 367, GroupLayout.PREFERRED_SIZE))
                        .addGroup(gl_contentPanel.createSequentialGroup()
                            .addGap(130)
                            .addComponent(btnNewButton)))
                    .addContainerGap(36, Short.MAX_VALUE))
        );
        gl_contentPanel.setVerticalGroup(
            gl_contentPanel.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPanel.createSequentialGroup()
                    .addComponent(panel, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
                    .addGap(18)
                    .addComponent(btnNewButton)
                    .addContainerGap(14, Short.MAX_VALUE))
        );

        JPanel DataCheckOut = new JPanel();

        JLabel DataCheckOutButton = new JLabel("Data CheckOut");
        DataCheckOut.add(DataCheckOutButton);

        DataCheckOutField = new JTextField();
        DataCheckOut.add(DataCheckOutField);
        DataCheckOutField.setColumns(10);

        JPanel HospedagemID = new JPanel();

        JLabel HospedagemIDButton = new JLabel("Hospedagem ID");
        HospedagemID.add(HospedagemIDButton);

        comboBoxIdHospedagem = new JComboBox<>(); // Inicializa o JComboBox
        HospedagemID.add(comboBoxIdHospedagem);

        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
            gl_panel.createParallelGroup(Alignment.TRAILING)
                .addGroup(gl_panel.createSequentialGroup()
                    .addContainerGap(80, Short.MAX_VALUE)
                    .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                        .addComponent(HospedagemID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(DataCheckOut, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(47))
        );
        gl_panel.setVerticalGroup(
            gl_panel.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel.createSequentialGroup()
                    .addComponent(HospedagemID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(DataCheckOut, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(92, Short.MAX_VALUE))
        );

        panel.setLayout(gl_panel);
        ContentPanel.setLayout(gl_contentPanel);

        setTitle("Realizar CheckOut");
        setSize(425, 260);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Carregar os IDs das hospedagens ao abrir a janela
        carregarIdsHospedagem();

        setVisible(true);
    }

    private void carregarIdsHospedagem() {
        HospedagemController hospedagemController = MainController.getHospedagemController();
        
        // Limpar o combobox antes de adicionar novos itens
        comboBoxIdHospedagem.removeAllItems();

        // Adicionar os IDs das hospedagens ao combobox
        for (HospedagemDto hospedagem : hospedagemController.getHospedagens()) {
            comboBoxIdHospedagem.addItem(hospedagem.getId()); // Adiciona o ID da hospedagem
        }
    }

    private void realizeCheckOut() {
        String hospedagemId = (String) comboBoxIdHospedagem.getSelectedItem(); // Obtém o ID selecionado
        String dataCheckout = DataCheckOutField.getText();

        try {
            HospedagemController hospedagemController = MainController.getHospedagemController();
            
            // Realizar o checkout
            hospedagemController.realizarCheckoutHospedagem(hospedagemId, dataCheckout);
            
            JOptionPane.showMessageDialog(this, "CheckOut realizado com sucesso!", "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
            clearFields();
        } catch (HospedagemException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro inesperado: " + ex.getMessage(), "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        comboBoxIdHospedagem.setSelectedIndex(0); // Reinicia o combobox para o primeiro item
        DataCheckOutField.setText(""); // Limpa o campo de data
    }
}