package fr.artapp.reviewservice.exceptions;

public class LoginNotCorrectException extends Exception {
    public LoginNotCorrectException(){
        super("Le login n'est pas correct");
    }
}
