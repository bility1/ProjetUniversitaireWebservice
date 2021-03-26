package fr.artapp.artservice.Exception;

public class CategorieExistePasException extends Exception {
    public CategorieExistePasException() {
        super("Catégorie non existante. veuillez choisir une catégorie existante");
    }
}
