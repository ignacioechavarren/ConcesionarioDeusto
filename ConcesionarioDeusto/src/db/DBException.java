package db;

/**
 * Representa un error en la base de datos
 *
 */
public class DBException extends Exception {

	/**
	 * Construye la excepción con le mensaje indicado
	 * @param message mensaje contenido en la excepción
	 */
	private static final long serialVersionUID = 1L;
	
	public DBException(String message) {
		super(message);
	}

	/**
	 * Construye la excepción con el mensaje y la excepción
	 * interna anidada.
	 * @param message mensaje de la excepción
	 * @param t excepción interna anidada
	 */
	public DBException(String message, Throwable t) {
		super(message, t);
	}
}
