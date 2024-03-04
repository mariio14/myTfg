package es.udc.fi.tfg.model.services.exceptions;


@SuppressWarnings("serial")
public class InvalidRatingException extends Exception {
	
	private String string;

	public InvalidRatingException(String string) {
		super(string);
	}

	public String getString() {return string;}

	public void setString(String string) {this.string = string;}
	
}


