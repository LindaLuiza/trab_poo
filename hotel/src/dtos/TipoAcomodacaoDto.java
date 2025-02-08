package dtos;

public class TipoAcomodacaoDto {

	private final String name;
	private double tarifaDiaria;
	private double adicionalAcompanhante;

	public TipoAcomodacaoDto(String name, double tarifaDiaria, double adicionalAcompanhante) {
		super();
		this.name = name;
		this.tarifaDiaria = tarifaDiaria;
		this.adicionalAcompanhante = adicionalAcompanhante;
	}

	public double getTarifaDiaria() {
		return tarifaDiaria;
	}

	public double getAdicionalAcompanhante() {
		return adicionalAcompanhante;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "TipoAcomodacaoDto [name=" + name + ", tarifaDiaria=" + tarifaDiaria + ", adicionalAcompanhante="
				+ adicionalAcompanhante + "]";
	}

}
