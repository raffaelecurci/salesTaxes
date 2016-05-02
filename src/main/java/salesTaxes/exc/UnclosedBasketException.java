package salesTaxes.exc;

public class UnclosedBasketException extends Exception {
	/**
	 * This class is used to remember the every basket have to be closed before being printable.
	 */
	private static final long serialVersionUID = 1L;
	
	public UnclosedBasketException(){
		super("UnclosedBasketException: you must close the basket before print it out.");
	}
}
