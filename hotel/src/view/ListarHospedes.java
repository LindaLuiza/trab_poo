package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import controller.HospedeController;
import controller.MainController;
import dtos.HospedeDto;

public class ListarHospedes extends JFrame {

    private static final long serialVersionUID = 7660917117492787396L;
	private JPanel contentPane;
    private JList<HospedeDto> listHospedes;
    private DefaultListModel<HospedeDto> listModel;

    public ListarHospedes() {
    	MainController.load();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        setTitle("Listar Hóspedes");

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);

        GridBagLayout gbl_contentPane = new GridBagLayout();
        gbl_contentPane.columnWidths = new int[] { 0, 0 };
        gbl_contentPane.rowHeights = new int[] { 0, 0, 0 };
        gbl_contentPane.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
        gbl_contentPane.rowWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
        contentPane.setLayout(gbl_contentPane);

        listModel = new DefaultListModel<>();
        listHospedes = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(listHospedes);
        GridBagConstraints gbc_scrollPane = new GridBagConstraints();
        gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
        gbc_scrollPane.fill = GridBagConstraints.BOTH;
        gbc_scrollPane.gridx = 0;
        gbc_scrollPane.gridy = 0;
        contentPane.add(scrollPane, gbc_scrollPane);

        JButton btnAtualizar = new JButton("Atualizar Lista");
        GridBagConstraints gbc_btnAtualizar = new GridBagConstraints();
        gbc_btnAtualizar.insets = new Insets(0, 0, 0, 0);
        gbc_btnAtualizar.gridx = 0;
        gbc_btnAtualizar.gridy = 1;
        contentPane.add(btnAtualizar, gbc_btnAtualizar);

        btnAtualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                carregarHospedes();
            }
        });

        carregarHospedes();
    }

    private void carregarHospedes() {
        HospedeController hospedeController = MainController.getHospedeController();
        List<HospedeDto> hospedes = hospedeController.getHospedes();

        listModel.clear();

        for (HospedeDto hospede : hospedes) {
            listModel.addElement(hospede);
        }

        if (hospedes.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhum hóspede cadastrado.", "Informação", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}