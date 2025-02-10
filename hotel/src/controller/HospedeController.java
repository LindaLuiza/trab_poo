package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import domain.Hospede;
import dtos.HospedeDto;
import exception.HospedeException;

public class HospedeController implements Serializable{

	private static final long serialVersionUID = 2063388198930719512L;
	
	private Map<String, Hospede> hospedes;

	public HospedeController() {
		hospedes = new TreeMap<>();
	}

	public void createHospede(HospedeDto h) throws HospedeException {
		if (hospedes.containsKey(h.getCpf())) {
			throw new HospedeException("Já existe um hospede cadastrado com esse CPF - " + h.getCpf());
		}

		Hospede hospede = new Hospede(h.getCpf(), h.getNome(), h.getEmail(), h.getTelefone());
		hospedes.put(hospede.getCpf(), hospede);

		MainController.save();
	}
	
	
	public Hospede getHospede(String cpf) throws HospedeException {
		Hospede h = hospedes.get(cpf);
		if (h == null) {
			throw new HospedeException("Não existe hospede cadastrado com este cpf " + cpf);
		}
		return hospedes.get(cpf);
	}

	public List<HospedeDto> getHospedes() {
		List<HospedeDto> lista = new ArrayList<HospedeDto>();
		Set<Map.Entry<String, Hospede>> entries = hospedes.entrySet();

		Hospede h;

		for (Map.Entry<String, Hospede> e : entries) {
			h = e.getValue();
			lista.add(new HospedeDto(h.getCpf(), h.getNome(), h.getEmail(), h.getTelefone()));
		}

		return lista;

	}

	public Set<String> getKeysHospedes() {
		return hospedes.keySet();
	}

}
