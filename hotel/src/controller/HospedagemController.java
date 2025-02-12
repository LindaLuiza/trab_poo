package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import domain.Hospedagem;
import domain.Pagamento;
import dtos.AcomodacaoDto;
import dtos.HospedagemDto;
import dtos.HospedeDto;
import exception.AcomodacaoException;
import exception.HospedagemException;
import exception.HospedeException;
import exception.PagamentoException;

public class HospedagemController implements Serializable {

	private static final long serialVersionUID = -2986449868123120251L;

	private Map<String, Hospedagem> hospedagens;

	public HospedagemController() {
		hospedagens = new TreeMap<>();
	}

	public void createHospedagem(HospedagemDto h) throws HospedagemException, AcomodacaoException, HospedeException {
		AcomodacaoController acomodacaoController = MainController.getAcomodacaoController();
		HospedeController hospedeController = MainController.getHospedeController();

		Hospedagem hospedagem = new Hospedagem(acomodacaoController.getAcomodacao(h.getNumeroAcomodacao()),
				hospedeController.getHospede(h.getCpfHospede()));
		hospedagens.put(hospedagem.getId(), hospedagem);

		MainController.save();
	}

	public Set<String> getKeysHospedagens() {
		return hospedagens.keySet();
	}

	public List<HospedagemDto> getHospedagens() {
		List<HospedagemDto> lista = new ArrayList<>();
		Set<Map.Entry<String, Hospedagem>> entries = hospedagens.entrySet();

		for (Map.Entry<String, Hospedagem> entry : entries) {
			Hospedagem h = entry.getValue();
			lista.add(new HospedagemDto(h.getId(), h.getCheckin(), h.getCheckout(),
					new AcomodacaoDto(h.getNumeroAcomodacao(), h.getOcupacaoMaxAcomodacao(), h.getTipoAcomodacao()),
					new HospedeDto(h.getCpfHospede(), h.getNomeHospede(), h.getEmailHospede(), h.getTelephoneHospede())));
		}

		return lista;
	}

	public void realizarCheckoutHospedagem(String idHospedagem) throws HospedagemException {
		Hospedagem hospedagem = hospedagens.get(idHospedagem);
		if (hospedagem == null) {
			throw new HospedagemException("Hospedagem com ID " + idHospedagem + " não encontrada.");
		}

		try {
			hospedagem.realizarCheckout();
			System.out.println("Checkout finalizado para a hospedagem ID: " + idHospedagem);
			MainController.save();
		} catch (HospedagemException e) {
			throw new HospedagemException("Erro ao realizar o checkout: " + e.getMessage());
		}
	}

	public double getSaldoDevedor(String idHospedagem) throws HospedagemException {
		Hospedagem hospedagem = getHospedagemById(idHospedagem);
		double valorTotal = hospedagem.calcularValorTotal();
		double totalPago = hospedagem.getPagamento().stream().mapToDouble(Pagamento::getValor).sum();
		return Math.max(0, valorTotal - totalPago);
	}

	public void addPagamento(String idHospedagem, Pagamento pagamento) throws HospedagemException, PagamentoException {
		Hospedagem hospedagem = getHospedagemById(idHospedagem);
		hospedagem.addPagamento(pagamento);
		MainController.save();
	}

	public List<Pagamento> getPagamentos(String idHospedagem) throws HospedagemException {
		Hospedagem hospedagem = getHospedagemById(idHospedagem);
		return hospedagem.getPagamento();
	}

	public Hospedagem getHospedagemById(String idHospedagem) throws HospedagemException {
		Hospedagem hospedagem = hospedagens.get(idHospedagem);
		if (hospedagem == null) {
			throw new HospedagemException("Hospedagem com ID " + idHospedagem + " não encontrada.");
		}
		return hospedagem;
	}

}
