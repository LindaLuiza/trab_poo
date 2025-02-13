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
import javax.swing.JToolBar;

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
				CriarCheckIn checkIn = new CriarCheckIn();
				checkIn.setVisible(true);
			}
		});

		JButton CheckOut = new JButton(" Realizar Check Out   ");
		CheckOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CriarCheckOut checkOut = new CriarCheckOut();
				checkOut.setVisible(true);
			}
		});
		GroupLayout gl_HospedagensContainer = new GroupLayout(HospedagensContainer);
		gl_HospedagensContainer.setHorizontalGroup(
			gl_HospedagensContainer.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_HospedagensContainer.createSequentialGroup()
					.addGroup(gl_HospedagensContainer.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_HospedagensContainer.createSequentialGroup()
							.addGap(143)
							.addComponent(CheckIn))
						.addGroup(gl_HospedagensContainer.createSequentialGroup()
							.addGap(135)
							.addComponent(CheckOut)))
					.addContainerGap(180, Short.MAX_VALUE))
		);
		gl_HospedagensContainer.setVerticalGroup(
			gl_HospedagensContainer.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_HospedagensContainer.createSequentialGroup()
					.addGap(23)
					.addComponent(CheckIn)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(CheckOut)
					.addContainerGap(92, Short.MAX_VALUE))
		);
		HospedagensContainer.setLayout(gl_HospedagensContainer);

		JPanel CadastrosContainer = new JPanel();
		TabsContainer.addTab("Cadastros", null, CadastrosContainer, null);

		JPanel CadastrarBotoesContainer = new JPanel();
		CadastrosContainer.add(CadastrarBotoesContainer);

		JButton CadastoHospede = new JButton("Cadastrar Hospede");

		JButton CadastroAcomodacao = new JButton("Cadastrar Acomodação");
		CadastroAcomodacao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CriarAcomodacao acomodacaoWindow = new CriarAcomodacao();
				acomodacaoWindow.setVisible(true);
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
		CadastrarItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CriarItem itemWindow = new CriarItem();
				itemWindow.setVisible(true);
			}
		});

		JButton CadastrarCategoria = new JButton("Cadastrar Categoria");
		CadastrarCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CriarCategoria categoriaWindow = new CriarCategoria();
				categoriaWindow.setVisible(true);
			}
		});
		GroupLayout gl_CadastrarBotoesContainer = new GroupLayout(CadastrarBotoesContainer);
		gl_CadastrarBotoesContainer.setHorizontalGroup(
			gl_CadastrarBotoesContainer.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_CadastrarBotoesContainer.createSequentialGroup()
					.addGroup(gl_CadastrarBotoesContainer.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_CadastrarBotoesContainer.createSequentialGroup()
							.addGap(111)
							.addGroup(gl_CadastrarBotoesContainer.createParallelGroup(Alignment.LEADING)
								.addComponent(CadastrarTipoAcomodacao)
								.addGroup(gl_CadastrarBotoesContainer.createSequentialGroup()
									.addGap(12)
									.addGroup(gl_CadastrarBotoesContainer.createParallelGroup(Alignment.TRAILING)
										.addComponent(CadastrarCategoria, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE)
										.addComponent(CadastrarItem, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE)))))
						.addGroup(gl_CadastrarBotoesContainer.createSequentialGroup()
							.addGap(131)
							.addComponent(CadastroAcomodacao))
						.addGroup(gl_CadastrarBotoesContainer.createSequentialGroup()
							.addGap(141)
							.addComponent(CadastoHospede)))
					.addContainerGap(127, Short.MAX_VALUE))
		);
		gl_CadastrarBotoesContainer.setVerticalGroup(
			gl_CadastrarBotoesContainer.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_CadastrarBotoesContainer.createSequentialGroup()
					.addContainerGap()
					.addComponent(CadastoHospede)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(CadastroAcomodacao)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(CadastrarTipoAcomodacao)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(CadastrarItem)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(CadastrarCategoria))
		);
		CadastrarBotoesContainer.setLayout(gl_CadastrarBotoesContainer);

		JPanel ListagemContainer = new JPanel();
		TabsContainer.addTab("Listagem", null, ListagemContainer, null);

		JPanel ListarBotoesContainer = new JPanel();
		ListagemContainer.add(ListarBotoesContainer);

		JButton ListarHospede = new JButton("Listar Hospedes");
		ListarHospede.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarHospedes listarHospedes = new ListarHospedes();
				listarHospedes.setVisible(true);
			}
		});

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
				ListarTipoAcomodacao listarTipoAcomodacao = new ListarTipoAcomodacao();
		        listarTipoAcomodacao.setVisible(true);
			}
		});

		JButton ListarItens = new JButton("Listar Itens");
		ListarItens.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 ListarItens listarItens = new ListarItens();  // Abre a janela de listagem de itens
			     listarItens.setVisible(true);
			}
		});

		JButton ListarCategorias = new JButton("Listar Categorias");
		ListarCategorias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarCategoria listarCategorias = new ListarCategoria();  // Abre a janela de listagem de categorias
		        listarCategorias.setVisible(true);
			}
		});
		
		JButton btnListarHospedagens = new JButton("Listar Hospedagens");
		btnListarHospedagens.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarHospedagens listarHospedagem = new ListarHospedagens();  // Abre a janela de listagem de categorias
		        listarHospedagem.setVisible(true);
			}
		});
		GroupLayout gl_ListarBotoesContainer = new GroupLayout(ListarBotoesContainer);
		gl_ListarBotoesContainer.setHorizontalGroup(
			gl_ListarBotoesContainer.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_ListarBotoesContainer.createSequentialGroup()
					.addGap(79)
					.addComponent(ListarHospede)
					.addPreferredGap(ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
					.addComponent(ListarItens)
					.addGap(59))
				.addGroup(gl_ListarBotoesContainer.createSequentialGroup()
					.addGroup(gl_ListarBotoesContainer.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_ListarBotoesContainer.createSequentialGroup()
							.addGap(64)
							.addComponent(ListarAcomodacao))
						.addGroup(gl_ListarBotoesContainer.createSequentialGroup()
							.addGap(41)
							.addComponent(ListarTipoAcomodacao)))
					.addGap(30)
					.addGroup(gl_ListarBotoesContainer.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnListarHospedagens)
						.addComponent(ListarCategorias))
					.addContainerGap(38, Short.MAX_VALUE))
		);
		gl_ListarBotoesContainer.setVerticalGroup(
			gl_ListarBotoesContainer.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_ListarBotoesContainer.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_ListarBotoesContainer.createParallelGroup(Alignment.BASELINE)
						.addComponent(ListarHospede)
						.addComponent(ListarItens))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_ListarBotoesContainer.createParallelGroup(Alignment.BASELINE)
						.addComponent(ListarAcomodacao)
						.addComponent(ListarCategorias))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_ListarBotoesContainer.createParallelGroup(Alignment.BASELINE)
						.addComponent(ListarTipoAcomodacao)
						.addComponent(btnListarHospedagens))
					.addContainerGap(62, Short.MAX_VALUE))
		);
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
			gl_ProcessarPagamento.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_ProcessarPagamento.createSequentialGroup()
					.addContainerGap(141, Short.MAX_VALUE)
					.addComponent(CriarPagamento)
					.addGap(153))
				.addGroup(gl_ProcessarPagamento.createSequentialGroup()
					.addGap(147)
					.addComponent(ConsultarValorTotal)
					.addContainerGap(150, Short.MAX_VALUE))
				.addGroup(gl_ProcessarPagamento.createSequentialGroup()
					.addGap(132)
					.addComponent(ConsultarSaldoDevedor)
					.addContainerGap(142, Short.MAX_VALUE))
		);
		gl_ProcessarPagamento.setVerticalGroup(
			gl_ProcessarPagamento.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_ProcessarPagamento.createSequentialGroup()
					.addContainerGap()
					.addComponent(CriarPagamento)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(ConsultarSaldoDevedor)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(ConsultarValorTotal)
					.addContainerGap(31, Short.MAX_VALUE))
		);
		ProcessarPagamento.setLayout(gl_ProcessarPagamento);
		
		JPanel GerenciarItens = new JPanel();
		TabsContainer.addTab("Itens", null, GerenciarItens, null);
		
		JButton btnAdicionarItemEm = new JButton("Adicionar Item em Categoria");
		btnAdicionarItemEm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdicionarItemCategoria addItemCategoria = new AdicionarItemCategoria();  // Abre a janela de listagem de itens
			     addItemCategoria.setVisible(true);
			}
		});
		
		JButton btnAdicionarItemEm_1 = new JButton("Adicionar item em Conta");
		GroupLayout gl_GerenciarItens = new GroupLayout(GerenciarItens);
		gl_GerenciarItens.setHorizontalGroup(
			gl_GerenciarItens.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_GerenciarItens.createSequentialGroup()
					.addContainerGap(147, Short.MAX_VALUE)
					.addGroup(gl_GerenciarItens.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_GerenciarItens.createSequentialGroup()
							.addComponent(btnAdicionarItemEm_1)
							.addGap(139))
						.addGroup(Alignment.TRAILING, gl_GerenciarItens.createSequentialGroup()
							.addComponent(btnAdicionarItemEm)
							.addGap(127))))
		);
		gl_GerenciarItens.setVerticalGroup(
			gl_GerenciarItens.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_GerenciarItens.createSequentialGroup()
					.addGap(22)
					.addComponent(btnAdicionarItemEm)
					.addGap(18)
					.addComponent(btnAdicionarItemEm_1)
					.addContainerGap(81, Short.MAX_VALUE))
		);
		GerenciarItens.setLayout(gl_GerenciarItens);
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
