package main;

import java.util.List;

import controller.AcomodacaoController;
import controller.HospedagemController;
import controller.HospedeController;
import controller.MainController;
import dtos.AcomodacaoDto;
import dtos.HospedagemDto;
import dtos.HospedeDto;
import dtos.TipoAcomodacaoDto;
import exception.AcomodacaoException;
import exception.HospedagemException;
import exception.HospedeException;
import exception.TipoAcomodacaoException;

public class MainHospedagens {

    public static void teste() {
        try {
            AcomodacaoController acomodacaoController = MainController.getAcomodacaoController();
            HospedeController hospedeController = MainController.getHospedeController();
            HospedagemController hospedagemController = MainController.getHospedagemController();

            TipoAcomodacaoDto tipoAcomodacao = new TipoAcomodacaoDto("Suite Master", 2, 300.0);
            AcomodacaoDto acomodacao = new AcomodacaoDto(111, 2, tipoAcomodacao.getName());

            acomodacaoController.createAcomodacao(acomodacao);

            HospedeDto hospede = new HospedeDto("18119185730", "Lewis", "lewis@gmail.com", 999999999);
            hospedeController.createHospede(hospede);

            HospedagemDto hospedagemDto = new HospedagemDto(acomodacao, hospede);
            hospedagemController.createHospedagem(hospedagemDto);

            System.out.println("Hospedagem criada com sucesso!");

            List<HospedagemDto> idHospedagem = hospedagemController.getHospedagens(); 
            
            for (HospedagemDto i : idHospedagem) {
            	String id = i.getId();
    			System.out.println(id);
    			System.out.println(i.getNumeroAcomodacao());
    			hospedagemController.realizarCheckoutHospedagem(id);
    		}
            
            

        } catch (TipoAcomodacaoException | AcomodacaoException | HospedeException | HospedagemException  e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
}
