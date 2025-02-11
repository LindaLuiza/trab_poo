package view;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.LayoutStyle.ComponentPlacement;

public class JanelaPrincipal extends JFrame {
	public JanelaPrincipal() {
		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		setTitle("Admin");
		
		JTabbedPane TabsContainer = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(TabsContainer);
		
		JPanel HospedagensContainer = new JPanel();
		TabsContainer.addTab("Hospedagem", null, HospedagensContainer, null);
		
		JButton btnNewButton_1_1_1 = new JButton("Realizar CheckIn");
		btnNewButton_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	                
			}
		});
		
		JButton btnNewButton_1 = new JButton("Realizar CheckOut");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GroupLayout gl_HospedagensContainer = new GroupLayout(HospedagensContainer);
		gl_HospedagensContainer.setHorizontalGroup(
			gl_HospedagensContainer.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_HospedagensContainer.createSequentialGroup()
					.addGap(120)
					.addGroup(gl_HospedagensContainer.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnNewButton_1)
						.addComponent(btnNewButton_1_1_1))
					.addContainerGap(134, Short.MAX_VALUE))
		);
		gl_HospedagensContainer.setVerticalGroup(
			gl_HospedagensContainer.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_HospedagensContainer.createSequentialGroup()
					.addContainerGap(31, Short.MAX_VALUE)
					.addComponent(btnNewButton_1_1_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton_1)
					.addGap(18))
		);
		HospedagensContainer.setLayout(gl_HospedagensContainer);
		
		JPanel CadastrosContainer = new JPanel();
		TabsContainer.addTab("Cadastros", null, CadastrosContainer, null);
		
		JPanel panel_1 = new JPanel();
		CadastrosContainer.add(panel_1);
		
		JButton CadastoHospede = new JButton("Cadastrar Hospede");
		
		JButton CadastroAcomodacao = new JButton("Cadastrar Acomodacao");
		CadastroAcomodacao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		JButton CadastrarTipoAcomodacao = new JButton("Cadastrar Tipo de Acomodacao");
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
					.addContainerGap(125, Short.MAX_VALUE)
					.addComponent(CadastoHospede)
					.addGap(139))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(108)
					.addComponent(CadastroAcomodacao)
					.addContainerGap(130, Short.MAX_VALUE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(74)
					.addComponent(CadastrarTipoAcomodacao)
					.addContainerGap(108, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addComponent(CadastroAcomodacao)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(CadastoHospede)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(CadastrarTipoAcomodacao)
					.addContainerGap())
		);
		panel_1.setLayout(gl_panel_1);
		
		JPanel ListagemContainer = new JPanel();
		TabsContainer.addTab("Listagem", null, ListagemContainer, null);
		
		JPanel panel_1_1 = new JPanel();
		ListagemContainer.add(panel_1_1);
		
		JButton btnListarHospede = new JButton("Listar Hospede");
		
		JButton btnNewButton_1_1_2 = new JButton("Listar Acomodacao");
		
		JButton btnNewButton_1_2_1 = new JButton("Listar Tipo de Acomodacao");
		btnNewButton_1_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GroupLayout gl_panel_1_1 = new GroupLayout(panel_1_1);
		gl_panel_1_1.setHorizontalGroup(
			gl_panel_1_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1_1.createSequentialGroup()
					.addContainerGap(129, Short.MAX_VALUE)
					.addGroup(gl_panel_1_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1_1.createSequentialGroup()
							.addGap(12)
							.addComponent(btnListarHospede))
						.addComponent(btnNewButton_1_1_2))
					.addGap(139))
				.addGroup(Alignment.LEADING, gl_panel_1_1.createSequentialGroup()
					.addGap(102)
					.addComponent(btnNewButton_1_2_1)
					.addContainerGap(110, Short.MAX_VALUE))
		);
		gl_panel_1_1.setVerticalGroup(
			gl_panel_1_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1_1.createSequentialGroup()
					.addComponent(btnNewButton_1_1_2)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnListarHospede)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton_1_2_1)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_1_1.setLayout(gl_panel_1_1);
		CadastoHospede.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CriarHospede hospedeWindow = new CriarHospede();
				hospedeWindow.setVisible(true);  
			}
		});
		
		Box verticalBox_3 = Box.createVerticalBox();
		getContentPane().add(verticalBox_3);
		
		Box verticalBox_1 = Box.createVerticalBox();
		verticalBox_3.add(verticalBox_1);
		
		Component verticalStrut = Box.createVerticalStrut(60);
		verticalBox_1.add(verticalStrut);
		
		Box verticalBox_2 = Box.createVerticalBox();
		verticalBox_3.add(verticalBox_2);
		
	}
	public void Formulario() {
		this.setTitle("Teste");
		this.setSize(80, 600);
	}
}
