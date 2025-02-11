package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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

import controller.AcomodacaoController;
import controller.MainController;
import dtos.AcomodacaoDto;
import dtos.TipoAcomodacaoDto;
import exception.TipoAcomodacaoException;

public class CriarAcomodacao extends JFrame {

	private static final long serialVersionUID = -4976495726690900847L;

	private JPanel ContentPanel;
	private JTextField OcupacaoMaxField;
	private JTextField NumeroField;
	private String tipoSelecionado;

	public CriarAcomodacao() {

		ContentPanel = new JPanel();
		setContentPane(ContentPanel);

		ContentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		JPanel panel = new JPanel();

		JButton btnNewButton = new JButton("Add Acomodação");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addAcomodacao();
			}
		});
		GroupLayout gl_contentPanel = new GroupLayout(ContentPanel);
		gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING).addGroup(
				gl_contentPanel.createSequentialGroup().addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup().addContainerGap().addComponent(panel,
								GroupLayout.PREFERRED_SIZE, 367, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup().addGap(105).addComponent(btnNewButton)))
						.addContainerGap(36, Short.MAX_VALUE)));
		gl_contentPanel
				.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING).addGroup(Alignment.LEADING,
						gl_contentPanel.createSequentialGroup()
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
								.addGap(18).addComponent(btnNewButton).addContainerGap(14, Short.MAX_VALUE)));

		JPanel OcupacaoMaxima = new JPanel();

		JLabel OcupacaoMaxButton = new JLabel("Ocupação Máxima");
		OcupacaoMaxima.add(OcupacaoMaxButton);

		OcupacaoMaxField = new JTextField();
		OcupacaoMaxima.add(OcupacaoMaxField);
		OcupacaoMaxField.setColumns(10);

		JPanel Número = new JPanel();

		JLabel NumeroButton = new JLabel("Número");
		Número.add(NumeroButton);

		NumeroField = new JTextField();
		NumeroField.setColumns(10);
		Número.add(NumeroField);

		JPanel TipoAcomodacao = new JPanel();

		JLabel TipoAcomButton = new JLabel("Tipo");
		TipoAcomodacao.add(TipoAcomButton);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup().addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup().addGap(88).addComponent(Número,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup().addGap(74).addComponent(OcupacaoMaxima,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup().addGap(51).addComponent(TipoAcomodacao,
								GroupLayout.PREFERRED_SIZE, 256, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(37, Short.MAX_VALUE)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup()
				.addComponent(Número, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(OcupacaoMaxima, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(TipoAcomodacao, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
				.addContainerGap(57, Short.MAX_VALUE)));

		JComboBox<String> TipoAcomcomboBox = new JComboBox<String>();
		TipoAcomodacao.add(TipoAcomcomboBox);
		carregarTiposAcomodacao(TipoAcomcomboBox);
		
		TipoAcomcomboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tipoSelecionado = (String) TipoAcomcomboBox.getSelectedItem();
			}
		});

		panel.setLayout(gl_panel);
		ContentPanel.setLayout(gl_contentPanel);

		setTitle("Criar Item");
		setSize(425, 260);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setVisible(true);
	}

	private void carregarTiposAcomodacao(JComboBox<String> comboBox) {
		AcomodacaoController acomodacaoController = MainController.getAcomodacaoController();
		List<TipoAcomodacaoDto> tipos = acomodacaoController.getTiposAcomodacoes();
		for (TipoAcomodacaoDto tipo : tipos) {
			comboBox.addItem(tipo.getName());
		}
	}

	private void addAcomodacao() {
		int numero = Integer.parseInt(NumeroField.getText());
		int ocupacaoMaxima = Integer.parseInt(OcupacaoMaxField.getText());

		if (tipoSelecionado == null || tipoSelecionado.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Selecione um tipo de acomodação!", "Erro", JOptionPane.ERROR_MESSAGE);
			return;
		}

		try {
			AcomodacaoController acomodacaoController = MainController.getAcomodacaoController();
			TipoAcomodacaoDto tipoAcomodacaoDto = acomodacaoController.getTiposAcomodacoes().stream()
					.filter(t -> t.getName().equals(tipoSelecionado)).findFirst()
					.orElseThrow(() -> new TipoAcomodacaoException("Tipo de acomodação não encontrado"));

			AcomodacaoDto acomodacaoDto = new AcomodacaoDto(numero, ocupacaoMaxima, tipoAcomodacaoDto.getName());
			acomodacaoController.createAcomodacao(acomodacaoDto);
			JOptionPane.showMessageDialog(this, "Acomodação adicionada com sucesso!", "Sucesso",
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
		NumeroField.setText("");
		OcupacaoMaxField.setText("");
	}
}
