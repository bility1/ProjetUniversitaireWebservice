package fr.artapp.artservice.Exception;

public class TitreNotFoundException extends Exception{
    public TitreNotFoundException() {
        super("Titre non trouvée");
    }
}
