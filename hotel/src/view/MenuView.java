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
import javax.swing.border.EmptyBorder;

public class MenuView extends JFrame {

	private static final long serialVersionUID = 6825905059315521698L;
	
	private JPanel ContentPanel; 

	public MenuView() {
		ContentPanel = new JPanel();
		setContentPane(ContentPanel);
		
		ContentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setBounds(100, 100, 600, 300);
		
		ContentPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		setTitle("Admin");

		JTabbedPane TabsContainer = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(TabsContainer);

		JPanel HospedagensContainer = new JPanel();
		TabsContainer.addTab("Hospedagem", null, HospedagensContainer, null);

		JButton CheckIn = new JButton("Realizar Check In");
		CheckIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});

		JButton CheckOut = new JButton(" Realizar Check Out   ");
		CheckOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GroupLayout gl_HospedagensContainer = new GroupLayout(HospedagensContainer);
		gl_HospedagensContainer.setHorizontalGroup(gl_HospedagensContainer.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_HospedagensContainer.createSequentialGroup().addGroup(gl_HospedagensContainer
						.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_HospedagensContainer.createSequentialGroup().addGap(143).addComponent(CheckIn))
						.addGroup(gl_HospedagensContainer.createSequentialGroup().addGap(128).addComponent(CheckOut)))
						.addContainerGap(144, Short.MAX_VALUE)));
		gl_HospedagensContainer.setVerticalGroup(
				gl_HospedagensContainer.createParallelGroup(Alignment.TRAILING).addGroup(Alignment.LEADING,
						gl_HospedagensContainer.createSequentialGroup().addGap(23).addComponent(CheckIn)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(CheckOut)
								.addContainerGap(61, Short.MAX_VALUE)));
		HospedagensContainer.setLayout(gl_HospedagensContainer);

		JPanel CadastrosContainer = new JPanel();
		TabsContainer.addTab("Cadastros", null, CadastrosContainer, null);

		JPanel CadastrarBotoesContainer = new JPanel();
		CadastrosContainer.add(CadastrarBotoesContainer);

		JButton CadastoHospede = new JButton("Cadastrar Hospede");

		JButton CadastroAcomodacao = new JButton("Cadastrar Acomodação");
		CadastroAcomodacao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});

		JButton CadastrarTipoAcomodacao = new JButton("Cadastrar Tipo de Acomodação");
		CadastrarTipoAcomodacao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CriarTipoAcomodacao tipoAcomodacaoWindow = new CriarTipoAcomodacao();
				tipoAcomodacaoWindow.setVisible(true);
			}
		});

		JButton CadastrarItem = new JButton("Cadastrar Item");

		JButton CadastrarCategoria = new JButton("Cadastrar Categoria");
		GroupLayout gl_CadastrarBotoesContainer = new GroupLayout(CadastrarBotoesContainer);
		gl_CadastrarBotoesContainer.setHorizontalGroup(gl_CadastrarBotoesContainer
				.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_CadastrarBotoesContainer.createSequentialGroup().addContainerGap(125, Short.MAX_VALUE)
						.addComponent(CadastoHospede).addGap(139))
				.addGroup(Alignment.LEADING,
						gl_CadastrarBotoesContainer.createSequentialGroup().addGap(110).addComponent(CadastroAcomodacao)
								.addContainerGap(128, Short.MAX_VALUE))
				.addGroup(Alignment.LEADING,
						gl_CadastrarBotoesContainer.createSequentialGroup().addGap(84)
								.addComponent(CadastrarTipoAcomodacao).addContainerGap(98, Short.MAX_VALUE))
				.addGroup(Alignment.LEADING,
						gl_CadastrarBotoesContainer.createSequentialGroup().addGap(111)
								.addGroup(gl_CadastrarBotoesContainer.createParallelGroup(Alignment.LEADING)
										.addComponent(CadastrarCategoria, GroupLayout.PREFERRED_SIZE, 198,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(CadastrarItem, GroupLayout.PREFERRED_SIZE, 198,
												GroupLayout.PREFERRED_SIZE))
								.addContainerGap(127, Short.MAX_VALUE)));
		gl_CadastrarBotoesContainer.setVerticalGroup(gl_CadastrarBotoesContainer.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_CadastrarBotoesContainer.createSequentialGroup().addContainerGap()
						.addComponent(CadastoHospede).addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(CadastroAcomodacao).addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(CadastrarTipoAcomodacao)
						.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(CadastrarItem).addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(CadastrarCategoria)));
		CadastrarBotoesContainer.setLayout(gl_CadastrarBotoesContainer);

		JPanel ListagemContainer = new JPanel();
		TabsContainer.addTab("Listagem", null, ListagemContainer, null);

		JPanel ListarBotoesContainer = new JPanel();
		ListagemContainer.add(ListarBotoesContainer);

		JButton ListarHospede = new JButton("Listar Hospedes");

		JButton ListarAcomodacao = new JButton("Listar Acomodações");
		ListarAcomodacao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarAcomodacoes listarAcomodacoes = new ListarAcomodacoes();
		        listarAcomodacoes.setVisible(true);
			}
		});

		JButton ListarTipoAcomodacao = new JButton("Listar Tipos de Acomodação");
		ListarTipoAcomodacao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		JButton ListarItens = new JButton("Listar Itens");
		ListarItens.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		JButton ListarCategorias = new JButton("Listar Categorias");
		GroupLayout gl_ListarBotoesContainer = new GroupLayout(ListarBotoesContainer);
		gl_ListarBotoesContainer.setHorizontalGroup(gl_ListarBotoesContainer.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_ListarBotoesContainer.createSequentialGroup().addContainerGap(141, Short.MAX_VALUE)
						.addComponent(ListarHospede).addGap(153))
				.addGroup(gl_ListarBotoesContainer.createSequentialGroup().addGap(124).addComponent(ListarAcomodacao)
						.addContainerGap(144, Short.MAX_VALUE))
				.addGroup(gl_ListarBotoesContainer.createSequentialGroup().addGap(99).addComponent(ListarTipoAcomodacao)
						.addContainerGap(113, Short.MAX_VALUE))
				.addGroup(gl_ListarBotoesContainer.createSequentialGroup().addGap(162).addComponent(ListarItens)
						.addContainerGap(167, Short.MAX_VALUE))
				.addGroup(gl_ListarBotoesContainer.createSequentialGroup().addGap(131).addComponent(ListarCategorias)
						.addContainerGap(156, Short.MAX_VALUE)));
		gl_ListarBotoesContainer.setVerticalGroup(gl_ListarBotoesContainer.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_ListarBotoesContainer.createSequentialGroup().addContainerGap().addComponent(ListarHospede)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(ListarAcomodacao)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(ListarTipoAcomodacao)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(ListarItens)
						.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(ListarCategorias)));
		ListarBotoesContainer.setLayout(gl_ListarBotoesContainer);

		JPanel PagamentosContainer = new JPanel();
		TabsContainer.addTab("Pagamentos", null, PagamentosContainer, null);

		JPanel ProcessarPagamento = new JPanel();
		PagamentosContainer.add(ProcessarPagamento);

		JButton CriarPagamento = new JButton("Adicionar Pagamento");

		JButton ConsultarSaldoDevedor = new JButton("Consultar Saldo Devedor");

		JButton ConsultarValorTotal = new JButton("Consultar Valor Total");
		GroupLayout gl_ProcessarPagamento = new GroupLayout(ProcessarPagamento);
		gl_ProcessarPagamento.setHorizontalGroup(
				gl_ProcessarPagamento.createParallelGroup(Alignment.LEADING).addGap(0, 436, Short.MAX_VALUE)
						.addGroup(gl_ProcessarPagamento.createSequentialGroup().addContainerGap(141, Short.MAX_VALUE)
								.addComponent(CriarPagamento).addGap(153))
						.addGroup(gl_ProcessarPagamento.createSequentialGroup().addGap(124)
								.addGroup(gl_ProcessarPagamento.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_ProcessarPagamento.createSequentialGroup().addGap(12)
												.addComponent(ConsultarValorTotal))
										.addComponent(ConsultarSaldoDevedor))
								.addContainerGap(145, Short.MAX_VALUE)));
		gl_ProcessarPagamento.setVerticalGroup(gl_ProcessarPagamento.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_ProcessarPagamento.createSequentialGroup().addContainerGap().addComponent(CriarPagamento)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(ConsultarSaldoDevedor)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(ConsultarValorTotal)
						.addContainerGap(31, Short.MAX_VALUE)));
		ProcessarPagamento.setLayout(gl_ProcessarPagamento);
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
