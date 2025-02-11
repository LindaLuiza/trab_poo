package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import controller.ItemController;
import controller.MainController;
import dtos.CategoriaDto;
import exception.CategoriaException;

public class CriarCategoria extends JFrame {

	private static final long serialVersionUID = 8969424554069765429L;
	
	private JPanel ContentPanel;
	private JTextField NomeField;

	public CriarCategoria() {

		ContentPanel = new JPanel();
		setContentPane(ContentPanel);

		ContentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		JPanel panel = new JPanel();

		JButton btnNewButton = new JButton("Add Categoria");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addCategoria();
			}
		});
		GroupLayout gl_contentPanel = new GroupLayout(ContentPanel);
		gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup().addGroup(gl_contentPanel
						.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup().addGap(134).addComponent(btnNewButton))
						.addGroup(gl_contentPanel.createSequentialGroup().addContainerGap().addComponent(panel,
								GroupLayout.PREFERRED_SIZE, 367, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(36, Short.MAX_VALUE)));
		gl_contentPanel
				.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
						gl_contentPanel.createSequentialGroup()
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
								.addComponent(btnNewButton).addContainerGap()));

		JPanel Nome = new JPanel();

		JLabel NomeButton = new JLabel("Nome");
		Nome.add(NomeButton);

		NomeField = new JTextField();
		Nome.add(NomeField);
		NomeField.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(104, Short.MAX_VALUE)
					.addComponent(Nome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(94))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(Nome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(127, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		ContentPanel.setLayout(gl_contentPanel);

		setTitle("Criar Hospede");
		setSize(425, 260);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setVisible(true);

	}

	private void addCategoria() {
		String nome = NomeField.getText();

		try {
			CategoriaDto categoriaDto = new CategoriaDto(nome);
			ItemController itemController = MainController.getItemController();
			itemController.createCategoria(categoriaDto);
			
			JOptionPane.showMessageDialog(this, "Categoria adicionada com sucesso!", "Sucesso",
					JOptionPane.INFORMATION_MESSAGE);
			clearFields();
		} catch (CategoriaException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, "Erro inesperado: " + ex.getMessage(), "Erro",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void clearFields() {
		NomeField.setText("");
	}

}
