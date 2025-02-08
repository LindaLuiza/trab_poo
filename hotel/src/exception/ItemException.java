package exception;

public class ItemException extends Exception {

	private static final long serialVersionUID = 1921925653323294202L;

	public ItemException(String msg) {
		super("Exceção de Item:" + msg);
	}

}
