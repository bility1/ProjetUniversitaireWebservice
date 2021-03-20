package fr.artapp.reviewservice.exceptions;

public class ReviewNotFoundException extends Exception{
    public ReviewNotFoundException(){
        super("Review pas trouvé");
    }
}