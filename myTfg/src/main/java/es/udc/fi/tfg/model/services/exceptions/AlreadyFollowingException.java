package es.udc.fi.tfg.model.services.exceptions;

public class AlreadyFollowingException extends Exception{

    private String string;

    public AlreadyFollowingException (String string) {
        super(string);
    }

    public String getString() {return string;}

    public void setString(String string) {this.string = string;}
}
