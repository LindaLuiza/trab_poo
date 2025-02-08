package exception;

public class ItemContaException extends Exception {

	private static final long serialVersionUID = -244738149472106345L;

	public ItemContaException(String msg) {
		super("Exceção de Item Conta:" + msg);
	}
}
