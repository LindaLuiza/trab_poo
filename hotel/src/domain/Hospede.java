package domain;

import exception.HospedeException;
import util.CpfValidator;

public class Hospede implements IHospede{

	private final String cpf;
	private String nome;
	private String email;
	private long telefone;

	public Hospede(String cpf, String nome, String email, long telefone) throws HospedeException{
		if (!CpfValidator.validate(cpf)) {
            throw new HospedeException("CPF inválido: " + cpf);
        }
		this.cpf = cpf;
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getTelefone() {
		return telefone;
	}

	public void setTelefone(long telefone) {
		this.telefone = telefone;
	}

	public String getCpf() {
		return cpf;
	}

}
