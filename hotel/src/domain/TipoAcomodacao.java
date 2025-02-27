package domain;

import java.io.Serializable;

import exception.TipoAcomodacaoException;

public class TipoAcomodacao implements Serializable {

	private static final long serialVersionUID = 3397562176448470280L;

	private final String name;
	private double tarifaDiaria;
	private double adicionalAcompanhante;

	public TipoAcomodacao(String name, double tarifaDiaria, double adicionalAcompanhante)
			throws TipoAcomodacaoException {


		if (tarifaDiaria <= 0 || adicionalAcompanhante <= 0) {
			throw new TipoAcomodacaoException("Tarifas tem que ter valor maior que zero");
		}

		if (name == null || name.isBlank() || name.isEmpty()) {
			throw new TipoAcomodacaoException("Nome deve estar preenchido.");
		}

		this.name = name;
		this.tarifaDiaria = tarifaDiaria;
		this.adicionalAcompanhante = adicionalAcompanhante;
	}
	

	public double getTarifaDiaria() {
		return tarifaDiaria;
	}

	public void setTarifaDiaria(double tarifaDiaria) throws TipoAcomodacaoException {
		if (tarifaDiaria <= 0) {
			throw new TipoAcomodacaoException("Tarifa tem que ter valor maior que zero");
		}
		this.tarifaDiaria = tarifaDiaria;
	}

	public double getAdicionalAcompanhante() {
		return adicionalAcompanhante;
	}

	public void setAdicionalAcompanhante(double adicionalAcompanhante) throws TipoAcomodacaoException {
		if (adicionalAcompanhante <= 0) {
			throw new TipoAcomodacaoException("Tarifa tem que ter valor maior que zero");
		}
		this.adicionalAcompanhante = adicionalAcompanhante;
	}

	public String getName() {
		return name;
	}

}
