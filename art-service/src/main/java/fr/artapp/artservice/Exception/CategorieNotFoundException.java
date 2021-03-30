package fr.artapp.artservice.Exception;

public class CategorieNotFoundException extends Exception {

    public CategorieNotFoundException() {
        super("Categorie non trouvée");
    }
}

