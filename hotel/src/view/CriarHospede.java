package view;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

public class CriarHospede extends JFrame {

	private static final long serialVersionUID = 7606755412652491686L;

	private JPanel ContentPanel;
	private JTextField CPFField;
	private JTextField NomeField;
	private JTextField EmailField;
	private JTextField TelefoneField;

	public CriarHospede() {

		ContentPanel = new JPanel();
		setContentPane(ContentPanel);

		ContentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JPanel panel = new JPanel();
		
		JButton btnNewButton = new JButton("Add Hospede");
		GroupLayout gl_contentPanel = new GroupLayout(ContentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(134)
							.addComponent(btnNewButton))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 367, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(36, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
					.addComponent(btnNewButton)
					.addContainerGap())
		);
		
		JPanel CPF = new JPanel();
		
		JLabel CPFButton = new JLabel("CPF");
		CPF.add(CPFButton);
		
		CPFField = new JTextField();
		CPF.add(CPFField);
		CPFField.setColumns(10);
		
		JPanel Nome = new JPanel();
		
		JLabel NomeButton = new JLabel("Nome");
		Nome.add(NomeButton);
		
		NomeField = new JTextField();
		NomeField.setColumns(10);
		Nome.add(NomeField);
		
		JPanel Email = new JPanel();
		
		JLabel EmailButton = new JLabel("Email");
		Email.add(EmailButton);
		
		EmailField = new JTextField();
		EmailField.setColumns(10);
		Email.add(EmailField);
		
		JPanel Telefone = new JPanel();
		
		JLabel TelefoneButton = new JLabel("Telefone");
		Telefone.add(TelefoneButton);
		
		TelefoneField = new JTextField();
		TelefoneField.setColumns(10);
		Telefone.add(TelefoneField);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(97)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(Nome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(CPF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(Email, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(57, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap(45, Short.MAX_VALUE)
					.addComponent(Telefone, GroupLayout.PREFERRED_SIZE, 278, GroupLayout.PREFERRED_SIZE)
					.addGap(44))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(5)
					.addComponent(CPF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(Nome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(Email, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(Telefone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(43, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		ContentPanel.setLayout(gl_contentPanel);

		//setBounds(100, 100, 700, 300);

		setTitle("Criar Hospede");
		setSize(425, 260);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setVisible(true);
	}
}
