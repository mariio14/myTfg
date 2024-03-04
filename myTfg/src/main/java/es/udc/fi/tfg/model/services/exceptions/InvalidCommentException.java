package es.udc.fi.tfg.model.services.exceptions;



public class InvalidCommentException extends Exception {
	
	private String string;

	public InvalidCommentException(String string) {
		super(string);
	}

	public String getString() {return string;}

	public void setString(String string) {this.string = string;}
    
}

