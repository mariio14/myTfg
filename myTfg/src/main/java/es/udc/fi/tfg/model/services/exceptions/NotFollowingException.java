package es.udc.fi.tfg.model.services.exceptions;

public class NotFollowingException  extends Exception {

    private String string;

    public NotFollowingException (String string) {
        super(string);
    }

    public String getString() {return string;}

    public void setString(String string) {this.string = string;}

}