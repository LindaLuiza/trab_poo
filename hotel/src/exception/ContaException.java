package exception;

public class ContaException extends Exception{

	private static final long serialVersionUID = 9189622943994201923L;

	public ContaException(String msg) {
		super("Exceção de Conta:" + msg);
	}
}
