package domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import exception.HospedagemException;

public class Hospedagem {

	private static int incioCheckin = 13;
	private static int limiteCheckout = 12;
	private final String id;
	private Date checkin;
	private Date checkout;
	private IAcomodacao acomodacao;
	private IHospede hospede;
	private ArrayList<IHospede> acompanhantes;
	private ArrayList<Pagamento> pagamento;
	private IConta conta;

	public Hospedagem(Date checkout, IAcomodacao acomodacao, IHospede hospede, ArrayList<Pagamento> pagamento,
			IConta conta) throws HospedagemException {
		
		if(acomodacao.getEstadoOcupacao() != EEstadoOcupacao.DISPONIVEL) {
			throw new HospedagemException("A Acomodação selecionada não está disponível.");
		}
		
		id = UUID.randomUUID().toString();
		this.checkin = new Date();
		this.checkout = checkout;
		this.acomodacao = acomodacao;
		this.hospede = hospede;
		this.acompanhantes = new ArrayList<IHospede>();
		this.pagamento = new ArrayList<Pagamento>();
		this.conta = conta;
	}

	public void addAcompanhantes(ArrayList<IHospede> acompanhantes) {
		acompanhantes.addAll(acompanhantes);
	}

	// TODO - Melhorar String Builder
	public StringBuilder listar() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("ID: %s%n", id));
		sb.append(String.format("Check-in: %s%n", checkin));
		sb.append(String.format("Check-out: %s%n", checkout));
		return sb;
	}

}