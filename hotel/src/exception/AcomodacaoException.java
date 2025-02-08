package exception;

public class AcomodacaoException extends Exception {

	private static final long serialVersionUID = 6642134725972685964L;

	public AcomodacaoException(String msg) {
		super("Exceção de Acomodacao:" + msg);
	}
	
}
