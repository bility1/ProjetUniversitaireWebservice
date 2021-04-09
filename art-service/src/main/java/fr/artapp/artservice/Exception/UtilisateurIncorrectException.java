package fr.artapp.artservice.Exception;

public class UtilisateurIncorrectException extends Exception{
    public UtilisateurIncorrectException() {
        super("Vous ne pouvez pas modifier cette oeuvre.");
    }
}
