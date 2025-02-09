package dtos;

public class HospedeDto {

	private final String cpf;
	private String nome;
	private String email;
	private long telefone;

	public HospedeDto(String cpf, String nome, String email, long telefone) {
		super();
		this.cpf = cpf;
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
	}

	public String getCpf() {
		return cpf;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public long getTelefone() {
		return telefone;
	}

}
