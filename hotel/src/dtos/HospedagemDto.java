package dtos;

import java.time.LocalDateTime;

public class HospedagemDto {

	private String id;
	private LocalDateTime checkin;
	private LocalDateTime checkout;
	private AcomodacaoDto acomodacao;
	private HospedeDto hospede;
	// private ContaDto conta;

	public HospedagemDto(AcomodacaoDto acomodacao, HospedeDto hospede) {
		this.acomodacao = acomodacao;
		this.hospede = hospede;
	}

	public HospedagemDto(String id, LocalDateTime checkin, LocalDateTime checkout, AcomodacaoDto acomodacao,
			HospedeDto hospede, ContaDto conta) {
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

	public LocalDateTime getCheckin() {
		return checkin;
	}

	public LocalDateTime getCheckout() {
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
