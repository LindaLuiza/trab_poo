package domain;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

import exception.HospedagemException;
import exception.PagamentoException;

public class Hospedagem {

	private static int inicioCheckin = 13;
	private static int limiteCheckout = 12;
	private final String id;
	private LocalDateTime checkin;
	private LocalDateTime checkout;
	private IAcomodacao acomodacao;
	private IHospede hospede;
	private ArrayList<IHospede> acompanhantes;
	private ArrayList<Pagamento> pagamento;
	//private IConta conta;

	public Hospedagem(Acomodacao acomodacao, Hospede hospede) throws HospedagemException {

		if (acomodacao.getEstadoOcupacao() != EEstadoOcupacao.DISPONIVEL) {
			throw new HospedagemException("A Acomodação selecionada não está disponível.");
		}
		
		this.acompanhantes = new ArrayList<IHospede>();
		
		if (acompanhantes.size() + 1 > acomodacao.getOcupacaoMax()) { 
            throw new HospedagemException("Número de hóspedes excede a capacidade máxima da acomodação.");
        }

		LocalDateTime agora = LocalDateTime.now();
		if (agora.getHour() < inicioCheckin) {
			throw new HospedagemException("O check-in só pode ser feito a partir das 13h.");
		}

		id = UUID.randomUUID().toString();
		this.checkin = agora;
		//this.checkout = checkout.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDateTime();
		this.acomodacao = acomodacao;
		this.hospede = hospede;
		this.acompanhantes = new ArrayList<IHospede>();
		//this.conta = (IConta) new Conta();

		acomodacao.setEstadoOcupacao(EEstadoOcupacao.OCUPADO);
	}

	public void addAcompanhantes(ArrayList<IHospede> acompanhantes) throws HospedagemException{
		if (this.acompanhantes.size() + acompanhantes.size() > acomodacao.getOcupacaoMax()) {
            throw new HospedagemException("Número de acompanhantes excede a capacidade máxima da acomodação.");
        }
		
		this.acompanhantes.addAll(acompanhantes);
	}
	
	public void addPagamento(Pagamento pagamento) throws PagamentoException {
	    if (pagamento == null) {
	        throw new PagamentoException("O pagamento não pode ser nulo.");
	    }

	    this.pagamento.add(pagamento);
	}


	public double calcularValorTotal() {
		Duration duration = Duration.between(checkin, checkout);
		long diasHospedagem = duration.toDays();

		double tarifaDiaria = acomodacao.getTarifaDiaria();
		double valorTotal = diasHospedagem * tarifaDiaria;

		for (int i = 1; i < acompanhantes.size(); i++) {
			valorTotal += tarifaDiaria * 0.1;
		}

		valorTotal += conta.getTotal();

		return valorTotal;
	}

	public void realizarCheckout() throws HospedagemException {
		LocalDateTime agora = LocalDateTime.now();
		if (agora.getHour() > limiteCheckout) {
			throw new HospedagemException("O check-out deve ser feito até as 12h.");
		}
		
		double valorTotal = calcularValorTotal();
	    double totalPago = pagamento.stream().mapToDouble(Pagamento::getValor).sum();

	    if (totalPago < valorTotal) {
	        double saldoDevedor = valorTotal - totalPago;
	        throw new HospedagemException(String.format("Pagamento insuficiente. Saldo devedor: R$ %.2f", saldoDevedor));
	    }
		
		this.checkout = agora;

		// Exibe a lista e o total da conta
		StringBuilder sb = new StringBuilder();
		sb.append("Informações da Hospedagem:\n");
		sb.append(listar());
		sb.append(String.format("Valor total das diárias: %.2f\n", calcularValorTotal()));


		System.out.println(sb.toString());

		acomodacao.setEstadoOcupacao(EEstadoOcupacao.MANUTENCAO);
	}
	
	

	public static int getInicioCheckin() {
		return inicioCheckin;
	}

	public static void setInicioCheckin(int inicioCheckin) {
		Hospedagem.inicioCheckin = inicioCheckin;
	}

	public static int getLimiteCheckout() {
		return limiteCheckout;
	}

	public static void setLimiteCheckout(int limiteCheckout) {
		Hospedagem.limiteCheckout = limiteCheckout;
	}

	public LocalDateTime getCheckin() {
		return checkin;
	}

	public void setCheckin(LocalDateTime checkin) {
		this.checkin = checkin;
	}

	public LocalDateTime getCheckout() {
		return checkout;
	}

	public void setCheckout(LocalDateTime checkout) {
		this.checkout = checkout;
	}

	public IAcomodacao getAcomodacao() {
		return acomodacao;
	}

	public void setAcomodacao(IAcomodacao acomodacao) {
		this.acomodacao = acomodacao;
	}

	public IHospede getHospede() {
		return hospede;
	}

	public void setHospede(IHospede hospede) {
		this.hospede = hospede;
	}

	public ArrayList<IHospede> getAcompanhantes() {
		return acompanhantes;
	}

	public void setAcompanhantes(ArrayList<IHospede> acompanhantes) {
		this.acompanhantes = acompanhantes;
	}

	public ArrayList<Pagamento> getPagamento() {
		return pagamento;
	}

	public void setPagamento(ArrayList<Pagamento> pagamento) {
		this.pagamento = pagamento;
	}
/*
	public IConta getConta() {
		return conta;
	}

	public void setConta(IConta conta) {
		this.conta = conta;
	}
*/
	public String getId() {
		return id;
	}
	
	public int getNumeroAcomodacao() {
		return acomodacao.getNumero();
	}
	
	public int getOcupacaoMaxAcomodacao() {
		return acomodacao.getOcupacaoMax();
	}

	public String getTipoAcomodacao() {
		return acomodacao.getTipo();
	}
	
	public String getCpfHospede() {
		return hospede.getCpf();
	}
	
	public String getNomeHospede() {
		return hospede.getNome();
	}
	
	public String getEmailHospede() {
		return hospede.getEmail();
	}
	
	public long getTelephoneHospede() {
		return hospede.getTelefone();
	}
	
	public StringBuilder listar() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("ID: %s%n", id));
		sb.append(String.format("Check-in: %s%n", checkin));
		sb.append(String.format("Check-out: %s%n", checkout));
		sb.append(String.format("Hóspede: %s%n", hospede.getNome()));
		sb.append(String.format("Acomodação: %s%n", acomodacao.getNumero()));
		sb.append(String.format("Número de acompanhantes: %d%n", acompanhantes.size()));
		return sb;
	}
}
