package fr.artapp.reviewservice.exceptions;

public class NoteNotPossibleException extends Exception {
    public NoteNotPossibleException(){
        super("La note doit être entre 0 et 20");
    }

}
