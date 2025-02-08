package exception;

public class TipoAcomodacaoException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public TipoAcomodacaoException(String msg) {
		super("Exceção de Tipo de Acomodacao:" + msg);
	}
}
