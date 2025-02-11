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

import controller.AcomodacaoController;
import controller.MainController;
import dtos.TipoAcomodacaoDto;
import exception.TipoAcomodacaoException;

public class CriarTipoAcomodacao extends JFrame{

	private static final long serialVersionUID = 2045203840205285776L;
	
	private JPanel ContentPanel;
	private JTextField TarifaDiariaField;
	private JTextField NomeField;
	private JTextField AddAcompField;

	public CriarTipoAcomodacao() {

		ContentPanel = new JPanel();
		setContentPane(ContentPanel);

		ContentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		JPanel panel = new JPanel();

		JButton btnNewButton = new JButton("Add Tipo Acomodação");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addTipoAcomodacao();
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
							.addGap(105)
							.addComponent(btnNewButton)))
					.addContainerGap(36, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPanel.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnNewButton)
					.addContainerGap(14, Short.MAX_VALUE))
		);

		JPanel TarifaDiaria = new JPanel();

		JLabel TarifaDiariaButton = new JLabel("Tarifa Diária");
		TarifaDiaria.add(TarifaDiariaButton);

		TarifaDiariaField = new JTextField();
		TarifaDiaria.add(TarifaDiariaField);
		TarifaDiariaField.setColumns(10);

		JPanel Nome = new JPanel();

		JLabel NomeButton = new JLabel("Nome");
		Nome.add(NomeButton);

		NomeField = new JTextField();
		NomeField.setColumns(10);
		Nome.add(NomeField);

		JPanel AdcAcompanhante = new JPanel();

		JLabel AddAcompButton = new JLabel("Adicional Acompanhante");
		AdcAcompanhante.add(AddAcompButton);

		AddAcompField = new JTextField();
		AddAcompField.setColumns(10);
		AdcAcompanhante.add(AddAcompField);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(88)
							.addComponent(Nome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(74)
							.addComponent(TarifaDiaria, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(31)
							.addComponent(AdcAcompanhante, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(33, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(Nome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(TarifaDiaria, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(AdcAcompanhante, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(57, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		ContentPanel.setLayout(gl_contentPanel);

		setTitle("Criar Tipo Acomodação");
		setSize(425, 260);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setVisible(true);

	}
	
	private void addTipoAcomodacao() {
		String nome = NomeField.getText();
		double tarifaDiaria = Double.parseDouble(TarifaDiariaField.getText());
		double adicionalAcompanhante = Double.parseDouble(AddAcompField.getText());

		try {
			TipoAcomodacaoDto tipoAcomodacaoDto = new TipoAcomodacaoDto(nome, tarifaDiaria, adicionalAcompanhante);
			AcomodacaoController acomodacaoController = MainController.getAcomodacaoController();
			acomodacaoController.createTipoAcomodacao(tipoAcomodacaoDto);
			JOptionPane.showMessageDialog(this, "Tipo Acomodacao adicionado com sucesso!", "Sucesso",
					JOptionPane.INFORMATION_MESSAGE);
			clearFields();
		} catch (TipoAcomodacaoException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, "Erro inesperado: " + ex.getMessage(), "Erro",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void clearFields() {
		NomeField.setText("");
		TarifaDiariaField.setText("");
		AddAcompField.setText("");
	}
	
}
