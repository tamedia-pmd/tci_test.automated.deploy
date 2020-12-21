package ch.txg.core.exceptions;

@SuppressWarnings("serial")
public class UnableToParsePDFPropertiesException extends Exception implements java.io.Serializable {

	/**
	 * Unable To Parse PDF Properties Exception
	 */

	public UnableToParsePDFPropertiesException() {
		
	}
	
	public UnableToParsePDFPropertiesException(String errorMessage) {
		super(errorMessage);
	}
	
}
