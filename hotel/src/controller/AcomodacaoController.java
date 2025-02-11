package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import domain.Acomodacao;
import domain.EEstadoOcupacao;
import domain.TipoAcomodacao;
import dtos.AcomodacaoDto;
import dtos.TipoAcomodacaoDto;
import exception.AcomodacaoException;
import exception.TipoAcomodacaoException;

public class AcomodacaoController implements Serializable {

	private static final long serialVersionUID = 1935946278254096531L;

	private Map<String, TipoAcomodacao> tipos;
	private Map<Integer, Acomodacao> acomodacoes;

	// Lista de objetos e chave para buscar por ela
	// O que as pessoas vão fazer com os objetos? Primeiro criar eles

	public AcomodacaoController() {
		tipos = new TreeMap<>();
		acomodacoes = new TreeMap<>();
	}

	public void createTipoAcomodacao(TipoAcomodacaoDto t) throws TipoAcomodacaoException {
		if (tipos.containsKey(t.getName())) {
			throw new TipoAcomodacaoException("já existe tipo com o nome - " + t.getName());
		}
		TipoAcomodacao tipo = new TipoAcomodacao(t.getName(), t.getTarifaDiaria(), t.getAdicionalAcompanhante());
		tipos.put(tipo.getName(), tipo);

		MainController.save();
	}

	protected TipoAcomodacao getTipoAcomodacao(String name) throws TipoAcomodacaoException {
		TipoAcomodacao t = tipos.get(name);
		if (t == null) {
			throw new TipoAcomodacaoException("Não existe acomodação de nome " + name);
		}
		return tipos.get(name);

		/*
		 * if (!tipos.containsKey(name)) { throw new
		 * TipoAcomodacaoException("Não existe tipo de acomodação de nome " + name); }
		 */
	}

	public List<TipoAcomodacaoDto> getTiposAcomodacoes() {
		List<TipoAcomodacaoDto> lista = new ArrayList<TipoAcomodacaoDto>();
		Set<Map.Entry<String, TipoAcomodacao>> entries = tipos.entrySet();

		TipoAcomodacao t;

		for (Map.Entry<String, TipoAcomodacao> e : entries) {
			t = e.getValue();
			lista.add(new TipoAcomodacaoDto(t.getName(), t.getTarifaDiaria(), t.getAdicionalAcompanhante()));
		}

		return lista;

	}

	public Set<String> getKeysTiposAcomodacao() {
		return tipos.keySet();
	}

	public void createAcomodacao(AcomodacaoDto acomodacaoDto) throws AcomodacaoException, TipoAcomodacaoException {
		TipoAcomodacao tipoAcomodacao = this.getTipoAcomodacao(acomodacaoDto.getTipo());

		if (acomodacoes.containsKey(acomodacaoDto.getNumero())) {
			throw new AcomodacaoException("Já existe uma acomodação com este numero " + acomodacaoDto.getNumero());
		}

		Acomodacao acomodacao = new Acomodacao(acomodacaoDto.getNumero(), acomodacaoDto.getOcupacaoMax(),
				tipoAcomodacao);
		acomodacoes.put(acomodacao.getNumero(), acomodacao);

		MainController.save();

	}

	public Acomodacao getAcomodacao(int numero) throws AcomodacaoException {
		Acomodacao acomodacao = acomodacoes.get(numero);
		if (acomodacao == null) {
			throw new AcomodacaoException("Não existe acomodação de numero " + numero);
		}
		return acomodacao;
	}

	public List<AcomodacaoDto> getAcomodacoes() {

		List<AcomodacaoDto> lista = new ArrayList<>();

		Set<Map.Entry<Integer, Acomodacao>> entries = acomodacoes.entrySet();

		Acomodacao a;

		for (Map.Entry<Integer, Acomodacao> e : entries) {
			a = e.getValue();
			lista.add(new AcomodacaoDto(a.getNumero(), a.getOcupacaoMax(), a.getEstadoOcupacao(), a.getTipo(),
					a.getTarifaDiaria(), a.getAdicionalAcompanhante()));
		}

		return lista;
	}
	
	public List<AcomodacaoDto> getAcomodacoesDisponiveis() {

		List<AcomodacaoDto> lista = new ArrayList<>();

		Set<Map.Entry<Integer, Acomodacao>> entries = acomodacoes.entrySet();

		Acomodacao a;

		for (Map.Entry<Integer, Acomodacao> e : entries) {
			a = e.getValue();
			if(a.getEstadoOcupacao() == EEstadoOcupacao.DISPONIVEL) {
				lista.add(new AcomodacaoDto(a.getNumero(), a.getOcupacaoMax(), a.getEstadoOcupacao(), a.getTipo(),
						a.getTarifaDiaria(), a.getAdicionalAcompanhante()));
			}
		}

		return lista;
	}

	public Set<Integer> getKeysAcomodacoes() {
		return acomodacoes.keySet();
	}	
	
}
