package domain;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import exception.HospedagemException;

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
	private IConta conta;

	public Hospedagem(Date checkout, IAcomodacao acomodacao, IHospede hospede, ArrayList<Pagamento> pagamento,
			IConta conta) throws HospedagemException {

		if (acomodacao.getEstadoOcupacao() != EEstadoOcupacao.DISPONIVEL) {
			throw new HospedagemException("A Acomodação selecionada não está disponível.");
		}
		
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
		this.pagamento = pagamento;
		this.conta = conta;

		acomodacao.setEstadoOcupacao(EEstadoOcupacao.OCUPADO);
	}

	public void addAcompanhantes(ArrayList<IHospede> acompanhantes) throws HospedagemException{
		if (this.acompanhantes.size() + acompanhantes.size() > acomodacao.getOcupacaoMax()) {
            throw new HospedagemException("Número de acompanhantes excede a capacidade máxima da acomodação.");
        }
		
		this.acompanhantes.addAll(acompanhantes);
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

		// Exibe a lista e o total da conta
		StringBuilder sb = new StringBuilder();
		sb.append("Informações da Hospedagem:\n");
		sb.append(listar());
		sb.append(String.format("Valor total das diárias: %.2f\n", calcularValorTotal()));

		double saldoDevedor = calcularValorTotal();
		double totalPago = pagamento.stream().mapToDouble(Pagamento::getValor).sum();

		if (totalPago < saldoDevedor) {
			sb.append(String.format("Pagamento pendente. Saldo devedor: %.2f\n", saldoDevedor - totalPago));
		} else {
			sb.append("Pagamento concluído.\n");
		}

		System.out.println(sb.toString());

		acomodacao.setEstadoOcupacao(EEstadoOcupacao.MANUTENCAO);
		acomodacao.setEstadoOcupacao(EEstadoOcupacao.DISPONIVEL);
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
