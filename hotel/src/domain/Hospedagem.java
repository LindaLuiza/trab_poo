package domain;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import exception.HospedagemException;
import exception.PagamentoException;
import util.UtilDate;

public class Hospedagem implements Serializable {

    private static final long serialVersionUID = -6398699107241630479L;

    private static int inicioCheckin = 13;
    //private static int limiteCheckout = 12;
    private static int limiteCheckout = 23;
    private final String id;
    private Date checkin;
    private Date checkout;
    private Acomodacao acomodacao;
    private Hospede hospede;
    private ArrayList<Hospede> acompanhantes;
    private ArrayList<Pagamento> pagamento;
    private Conta conta;

    public Hospedagem(Acomodacao acomodacao, Hospede hospede) throws HospedagemException {
        if (acomodacao.getEstadoOcupacao() != EEstadoOcupacao.DISPONIVEL) {
            throw new HospedagemException("A Acomodação selecionada não está disponível.");
        }

        this.acompanhantes = new ArrayList<>();
        this.pagamento = new ArrayList<>();

        if (acompanhantes.size() + 1 > acomodacao.getOcupacaoMax()) {
            throw new HospedagemException("Número de hóspedes excede a capacidade máxima da acomodação.");
        }

        Date agora = new Date();
        if (agora.getHours() < inicioCheckin) {
            throw new HospedagemException("O check-in só pode ser feito a partir das 13h.");
        }

        this.id = UUID.randomUUID().toString();
        this.checkin = agora;
        this.acomodacao = acomodacao;
        System.out.println(acomodacao.getTarifaDiaria());
        this.hospede = hospede;
        this.conta = new Conta();

        acomodacao.setEstadoOcupacao(EEstadoOcupacao.OCUPADO);
    }

    public void addAcompanhante(Hospede acompanhante) throws HospedagemException {
        if (this.acompanhantes.size() + 1 > acomodacao.getOcupacaoMax()) {
            throw new HospedagemException("Número de acompanhantes excede a capacidade máxima da acomodação.");
        }
        this.acompanhantes.add(acompanhante);
    }

    public void addPagamento(Pagamento pagamento) throws PagamentoException {
        if (pagamento == null) {
            throw new PagamentoException("O pagamento não pode ser nulo.");
        }
        this.pagamento.add(pagamento);
    }

    public double calcularValorTotal() {
        int diasHospedagem = UtilDate.countDaysBetweenDates(checkin, checkout);
        if (diasHospedagem == 0) diasHospedagem = 1; 
        
        double tarifaDiaria = acomodacao.getTarifaDiaria();
        
        //double valorTotal = diasHospedagem * tarifaDiaria;
        double valorTotal = tarifaDiaria;

        if(acompanhantes.size() > 0) {
        	for (int i = 1; i < acompanhantes.size(); i++) {
                valorTotal += tarifaDiaria * 0.1;
                System.out.println(valorTotal);
            }
        }
        
        //valorTotal += conta.getTotal();

        return valorTotal;
    }
    
    public double calcularValorTotalSaldo() {
    	Date agora = new Date();
        int diasHospedagem = UtilDate.countDaysBetweenDates(checkin, agora);
        if (diasHospedagem == 0) diasHospedagem = 1; 
        
        double tarifaDiaria = acomodacao.getTarifaDiaria();
        
        //double valorTotal = diasHospedagem * tarifaDiaria;
        double valorTotal = tarifaDiaria;

        if(acompanhantes.size() > 0) {
        	for (int i = 1; i < acompanhantes.size(); i++) {
                valorTotal += tarifaDiaria * 0.1;
                System.out.println(valorTotal);
            }
        }
        
        valorTotal += conta.getTotal();

        return valorTotal;
    }


    public void realizarCheckout(String data) throws HospedagemException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            this.checkout = sdf.parse(data);
        } catch (ParseException e) {
            throw new HospedagemException("Data de checkout inválida. Use o formato dd/MM/yyyy.");
        }

        Date agora = new Date();
        if (agora.getHours() > limiteCheckout) {
            throw new HospedagemException("O check-out deve ser feito até as 12h.");
        }

        double valorTotal = calcularValorTotal();
        
        if (this.pagamento.isEmpty()) {
            throw new HospedagemException("Nenhum pagamento foi realizado. Realize o pagamento de R$ " + valorTotal + " primeiro.");
        }

        double totalPago = pagamento.stream().mapToDouble(Pagamento::getValor).sum();

        if (totalPago < valorTotal) {
            double saldoDevedor = valorTotal - totalPago;
            throw new HospedagemException(String.format("Pagamento insuficiente. Saldo devedor: R$ %.2f", saldoDevedor));
        }

        System.out.println(listar());
        acomodacao.setEstadoOcupacao(EEstadoOcupacao.MANUTENCAO);
    }

    public StringBuilder listar() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("ID: %s%n", id));
        sb.append(String.format("Check-in: %s%n", sdf.format(checkin)));
        sb.append(String.format("Check-out: %s%n", checkout != null ? sdf.format(checkout) : "Não realizado"));
        sb.append(String.format("Hóspede: %s%n", hospede.getNome()));
        sb.append(String.format("Acomodação: %s%n", acomodacao.getNumero()));
        sb.append(String.format("Número de acompanhantes: %d%n", acompanhantes.size()));
        return sb;
    }

    // Getters e Setters

    public static int getInicioCheckin() {
        return inicioCheckin;
    }

    public static void setInicioCheckin(int inicioCheckin) {
        Hospedagem.inicioCheckin = inicioCheckin;
    }

    public static int getLimiteCheckout() {
        return limiteCheckout;
    }

    public static void setLimiteCheckout(int limiteCheckout) {
        Hospedagem.limiteCheckout = limiteCheckout;
    }

    public Date getCheckin() {
        return checkin;
    }

    public void setCheckin(Date checkin) {
        this.checkin = checkin;
    }

    public Date getCheckout() {
        return checkout;
    }

    public void setCheckout(Date checkout) {
        this.checkout = checkout;
    }

    public IAcomodacao getAcomodacao() {
        return acomodacao;
    }

    public void setAcomodacao(Acomodacao acomodacao) {
        this.acomodacao = acomodacao;
    }

    public IHospede getHospede() {
        return hospede;
    }

    public void setHospede(Hospede hospede) {
        this.hospede = hospede;
    }

    public ArrayList<Hospede> getAcompanhantes() {
        return acompanhantes;
    }

    public void setAcompanhantes(ArrayList<Hospede> acompanhantes) {
        this.acompanhantes = acompanhantes;
    }

    public ArrayList<Pagamento> getPagamento() {
        return pagamento;
    }

    public void setPagamento(ArrayList<Pagamento> pagamento) {
        this.pagamento = pagamento;
    }

    public String getId() {
        return id;
    }
    
    public Conta getConta() {
    	return conta;
    }
    
    public int getNumeroAcomodacao() {
		return acomodacao.getNumero();
	}
	
	public int getOcupacaoMaxAcomodacao() {
		return acomodacao.getOcupacaoMax();
	}

	public String getTipoAcomodacao() {
		return acomodacao.getTipo();
	}
	
	public String getCpfHospede() {
		return hospede.getCpf();
	}
	
	public String getNomeHospede() {
		return hospede.getNome();
	}
	
	public String getEmailHospede() {
		return hospede.getEmail();
	}
	
	public long getTelephoneHospede() {
		return hospede.getTelefone();
	}

}
