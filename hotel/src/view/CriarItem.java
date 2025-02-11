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
import dtos.ItemDto;
import exception.ItemException;

public class CriarItem extends JFrame{

	private static final long serialVersionUID = 2077427730403227591L;
	
	private JPanel ContentPanel;
	private JTextField DescricaoField;
	private JTextField CodigoField;
	private JTextField PrecoField;

	public CriarItem() {

		ContentPanel = new JPanel();
		setContentPane(ContentPanel);

		ContentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		JPanel panel = new JPanel();

		JButton btnNewButton = new JButton("Add Item");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addItem();
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

		JPanel Descrição = new JPanel();

		JLabel DescricaoButton = new JLabel("Descrição");
		Descrição.add(DescricaoButton);

		DescricaoField = new JTextField();
		Descrição.add(DescricaoField);
		DescricaoField.setColumns(10);

		JPanel Código = new JPanel();

		JLabel CodigoButton = new JLabel("Código");
		Código.add(CodigoButton);

		CodigoField = new JTextField();
		CodigoField.setColumns(10);
		Código.add(CodigoField);
		
		JPanel Preço = new JPanel();
		
		JLabel PrecoButton = new JLabel("Preço");
		Preço.add(PrecoButton);
		
		PrecoField = new JTextField();
		PrecoField.setColumns(10);
		Preço.add(PrecoField);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(85)
							.addComponent(Descrição, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(97)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(Preço, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE)
								.addComponent(Código, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(84, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(5)
					.addComponent(Descrição, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(Código, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(Preço, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(52, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		ContentPanel.setLayout(gl_contentPanel);

		setTitle("Criar Item");
		setSize(425, 260);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setVisible(true);

	}

	private void addItem() {
		String descricao = DescricaoField.getText();
		long codigo = Long.parseLong(CodigoField.getText());
		double preco = Double.parseDouble(PrecoField.getText());

		try {
			ItemDto itemDto = new ItemDto(codigo, descricao, preco);
			ItemController itemController = MainController.getItemController();
			itemController.createItem(itemDto);
			
			JOptionPane.showMessageDialog(this, "Item adicionado com sucesso!", "Sucesso",
					JOptionPane.INFORMATION_MESSAGE);
			clearFields();
		} catch (ItemException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, "Erro inesperado: " + ex.getMessage(), "Erro",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void clearFields() {
		DescricaoField.setText("");
		CodigoField.setText("");
		PrecoField.setText("");
	}
}
