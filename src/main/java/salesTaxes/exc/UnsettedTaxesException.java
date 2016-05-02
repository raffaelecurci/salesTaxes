package salesTaxes.exc;

public class UnsettedTaxesException extends RuntimeException {

	/**
	 * This exception is risen everytime taxes for an Item object are not set.
	 */
	private static final long serialVersionUID = 1L;
	
	public UnsettedTaxesException(){
		super("You must set Import and Sale Tax flags.");
	}

}
