package exception;

public class HospedagemException extends Exception {

	private static final long serialVersionUID = -1639135447939333156L;

	public HospedagemException(String msg) {
		super("Exceção de Hospedagem:" + msg);
	}

}
