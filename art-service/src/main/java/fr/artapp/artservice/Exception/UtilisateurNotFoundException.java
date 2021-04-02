package fr.artapp.artservice.Exception;

public class UtilisateurNotFoundException extends Exception{
    public UtilisateurNotFoundException() {
        super("Utilisateur non trouvée");
    }
}
