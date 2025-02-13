package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import domain.Conta;
import domain.Hospedagem;
import domain.Hospede;
import domain.Item;
import domain.ItemConta;
import domain.Pagamento;
import dtos.AcomodacaoDto;
import dtos.HospedagemDto;
import dtos.HospedeDto;
import dtos.ItemDto;
import dtos.PagamentoDto;
import exception.AcomodacaoException;
import exception.HospedagemException;
import exception.HospedeException;
import exception.ItemContaException;
import exception.ItemException;
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
					new HospedeDto(h.getCpfHospede(), h.getNomeHospede(), h.getEmailHospede(),
							h.getTelephoneHospede())));
		}

		return lista;
	}

	public void realizarCheckoutHospedagem(String idHospedagem, String checkOutDate) throws HospedagemException {
		Hospedagem hospedagem = hospedagens.get(idHospedagem);
		if (hospedagem == null) {
			throw new HospedagemException("Hospedagem com ID " + idHospedagem + " não encontrada.");
		}

		try {
			hospedagem.realizarCheckout(checkOutDate);
			System.out.println("Checkout finalizado para a hospedagem ID: " + idHospedagem);
			MainController.save();
		} catch (HospedagemException e) {
			throw new HospedagemException("Erro ao realizar o checkout: " + e.getMessage());
		}
	}

	public double getSaldoDevedor(String idHospedagem) throws HospedagemException {
		Hospedagem hospedagem = getHospedagemById(idHospedagem);
		double valorTotal = hospedagem.calcularValorTotalSaldo();
		double totalPago = hospedagem.getPagamento().stream().mapToDouble(Pagamento::getValor).sum();
		return Math.max(0, valorTotal - totalPago);
	}

	public double getSaldoTotal(String idHospedagem) throws HospedagemException {
		Hospedagem hospedagem = getHospedagemById(idHospedagem);
		double valorTotal = hospedagem.calcularValorTotal();
		return valorTotal;
	}

	public void addPagamento(String idHospedagem, PagamentoDto pagamento)
			throws HospedagemException, PagamentoException {
		Hospedagem hospedagem = getHospedagemById(idHospedagem);
		hospedagem.addPagamento(new Pagamento(pagamento.getTipo(), pagamento.getValor()));
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

	public void addItemConta(String idHospedagem, long codigoItem, int qtde) {
		ItemController itemController = MainController.getItemController();
		ItemDto item = itemController.getItemByCodigo(codigoItem);
		ItemConta itemConta = null;
		try {
			itemConta = new ItemConta(qtde, new Item(item.getCodigo(), item.getDescricao(), item.getPreco()));
		} catch (ItemContaException | ItemException e) {
			System.out.println("Erro ao adicionar Item a Conta" + e);
		}

		Hospedagem hospedagem = null;
		
		try {
			hospedagem = getHospedagemById(idHospedagem);
		} catch (HospedagemException e) {
			System.out.println("Erro: Hospedagem não encontrada");
		}
		
		Conta conta = hospedagem.getConta();
		
		conta.addItem(itemConta, qtde);

	}
	
	public List<ItemConta> listarItensConta(String idHospedagem) {
	    Hospedagem hospedagem;
	    
	    try {
	        hospedagem = getHospedagemById(idHospedagem);
	    } catch (HospedagemException e) {
	        System.out.println("Erro: Hospedagem não encontrada. " + e.getMessage());
	        return new ArrayList<>();
	    }

	    Conta conta = hospedagem.getConta();

	    if (conta.getItens().isEmpty()) {
	        System.out.println("A conta não possui itens registrados.");
	        return new ArrayList<>();
	    }

	    System.out.println("Itens encontrados:");
	    for (ItemConta item : conta.getItens()) {
	        System.out.println("Código: " + item.getItem().getCodigo() +
	                           " | Descrição: " + item.getItem().getDescricao() +
	                           " | Quantidade: " + item.getQtde() +
	                           " | Preço Unitário: R$ " + item.getItem().getPreco());
	    }

	    return conta.getItens();
	}


	
	public void adicionarAcompanhante(String idHospedagem, HospedeDto acompanhante) {
	    try {
	        Hospedagem hospedagem = getHospedagemById(idHospedagem);
	        
	        if (hospedagem == null) {
	            System.out.println("Hospedagem não encontrada.");
	            return;
	        }

	        hospedagem.addAcompanhante(new Hospede(acompanhante.getCpf(), acompanhante.getNome(), acompanhante.getEmail(), acompanhante.getTelefone()));
	        System.out.println("Acompanhante adicionado com sucesso!");

	    } catch (HospedagemException e) {
	        System.out.println("Erro ao adicionar acompanhante: " + e.getMessage());
	    } catch (HospedeException e) {
			System.out.println("Hoapede Exception" + e);
		}
	}

}
