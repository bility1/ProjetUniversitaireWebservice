package fr.artapp.artservice.Exception;

public class PropositionNotFoundException extends Exception{
    public PropositionNotFoundException() {
        super("Proposition non trouvée");
    }
}
