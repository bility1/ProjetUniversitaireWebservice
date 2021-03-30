package fr.artapp.reviewservice.exceptions;

public class LoginNotCorrectException extends Throwable {
    public LoginNotCorrectException(){
        super("Le login n'est pas correct");
    }
}
