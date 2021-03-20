package fr.artapp.artservice.Exception;

public class OeuvreNotFoundException extends Exception{
    public OeuvreNotFoundException() {
        super("Oeuvre non trouvée");
    }
}
