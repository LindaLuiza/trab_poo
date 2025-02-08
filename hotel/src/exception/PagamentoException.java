package exception;

public class PagamentoException extends Exception {

	private static final long serialVersionUID = -672195273561078562L;

	public PagamentoException(String msg) {
		super("Exceção de Pagamento:" + msg);
	}

}
