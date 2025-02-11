package main;

import java.time.LocalDateTime;

import controller.AcomodacaoController;
import controller.HospedagemController;
import controller.HospedeController;
import controller.MainController;
import domain.ETipoPagamento;
import domain.Pagamento;
import dtos.AcomodacaoDto;
import dtos.HospedagemDto;
import dtos.HospedeDto;
import dtos.TipoAcomodacaoDto;
import exception.AcomodacaoException;
import exception.HospedagemException;
import exception.HospedeException;
import exception.PagamentoException;

public class MainHospedagens {

    public static void teste() {
        try {
            AcomodacaoController acomodacaoController = MainController.getAcomodacaoController();
            HospedeController hospedeController = MainController.getHospedeController();
            HospedagemController hospedagemController = MainController.getHospedagemController();

            TipoAcomodacaoDto tipoAcomodacao = new TipoAcomodacaoDto("Suite Luxo", 2, 300.0);
            AcomodacaoDto acomodacao = new AcomodacaoDto(101, 2, tipoAcomodacao);

            acomodacaoController.createAcomodacao(acomodacao);

            HospedeDto hospede = new HospedeDto("12345678900", "João da Silva", "joao@gmail.com", 999999999);
            hospedeController.createHospede(hospede);

            HospedagemDto hospedagemDto = new HospedagemDto(acomodacao, hospede);
            hospedagemController.createHospedagem(hospedagemDto);

            System.out.println("Hospedagem criada com sucesso!");

            String idHospedagem = hospedagemController.getHospedagens().keySet().iterator().next();  
            double saldoDevedor = hospedagemController.getSaldoDevedor(idHospedagem);
            System.out.println("Saldo devedor inicial: R$ " + saldoDevedor);

            Pagamento pagamento1 = new Pagamento(ETipoPagamento.DEBITO, 200.00);
            hospedagemController.addPagamento(idHospedagem, pagamento1);
            System.out.println("Pagamento de R$ 200,00 registrado.");

            saldoDevedor = hospedagemController.getSaldoDevedor(idHospedagem);
            System.out.println("Saldo devedor após pagamento: R$ " + saldoDevedor);

            Pagamento pagamento2 = new Pagamento(ETipoPagamento.CREDITO, saldoDevedor);
            hospedagemController.addPagamento(idHospedagem, pagamento2);
            System.out.println("Pagamento do saldo restante registrado.");

            hospedagemController.getHospedagemById(idHospedagem).realizarCheckout();
            System.out.println("Checkout realizado com sucesso!");

        } catch (AcomodacaoException | HospedeException | HospedagemException | PagamentoException e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
}
