package fr.artapp.artservice.Exception;

public class ForeignKeyCollisionException extends Exception{
    public ForeignKeyCollisionException() {
        super("Vous devez d'abord supprimer les oeuvre appartenant à cette catégorie");
    }
}
