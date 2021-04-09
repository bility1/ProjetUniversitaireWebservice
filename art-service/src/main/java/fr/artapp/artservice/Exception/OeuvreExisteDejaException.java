package fr.artapp.artservice.Exception;

public class OeuvreExisteDejaException extends Exception {
    public OeuvreExisteDejaException() {
        super("Oeuvre déjà existante");
    }
}
