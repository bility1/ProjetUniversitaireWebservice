package fr.artapp.artservice.Exception;

public class UtilisateurManqueDroitException extends Exception{
    public UtilisateurManqueDroitException() {
        super("Vous n'avez pas les droits pour modifier une catégorie.");
    }
}
