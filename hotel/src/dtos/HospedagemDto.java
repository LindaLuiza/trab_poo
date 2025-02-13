package dtos;

import java.util.Date;

public class HospedagemDto {

	private String id;
	private Date checkin;
	private Date checkout;
	private AcomodacaoDto acomodacao;
	private HospedeDto hospede;
	// private ContaDto conta;

	public HospedagemDto(AcomodacaoDto acomodacao, HospedeDto hospede) {
		this.acomodacao = acomodacao;
		this.hospede = hospede;
	}

	public HospedagemDto(String id, Date checkin, Date checkout, AcomodacaoDto acomodacao,
			HospedeDto hospede) {
		this.id = id;
		this.checkin = checkin;
		this.checkout = checkout;
		this.acomodacao = acomodacao;
		this.hospede = hospede;
		// this.conta = conta;
	}

	public String getId() {
		return id;
	}

	public Date getCheckin() {
		return checkin;
	}

	public Date getCheckout() {
		return checkout;
	}

	public AcomodacaoDto getAcomodacao() {
		return acomodacao;
	}

	public HospedeDto getHospede() {
		return hospede;
	}

	public int getNumeroAcomodacao() {
		return acomodacao.getNumero();
	}

	public String getCpfHospede() {
		return hospede.getCpf();
	}

	/*
	 * public String getConta() { return conta; }
	 */
	
}
