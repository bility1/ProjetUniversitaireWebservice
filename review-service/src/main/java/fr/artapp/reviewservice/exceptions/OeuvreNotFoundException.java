package fr.artapp.reviewservice.exceptions;

public class OeuvreNotFoundException extends Exception {


    public OeuvreNotFoundException() {
        super("Oeuvre non trouvée !");
    }
}
