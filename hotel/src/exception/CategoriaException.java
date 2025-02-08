package exception;

public class CategoriaException extends Exception {

	private static final long serialVersionUID = -2320510860035227882L;

	public CategoriaException(String msg) {
		super("Exceção de Categoria:" + msg);
	}
	
}
