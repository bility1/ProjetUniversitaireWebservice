package fr.artapp.reviewservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "avis")
public class Review {

    @Id
    private String idAvis;
    @Field(value = "note")
    private Integer note;
    @Field(value = "commentaire")
    private String commentaire;
    @Field(value = "idOeuvre")
    private Long idOeuvre;
    @Field(value = "loginUtilisateur")
    private String loginUtilisateur;

    public Review() {
    }

    public Review(String idAvis, Integer note, String commentaire, Long idOeuvre, String loginUtilisateur) {
        this.idAvis = idAvis;
        this.note = note;
        this.commentaire = commentaire;
        this.idOeuvre = idOeuvre;
        this.loginUtilisateur = loginUtilisateur;
    }

    public String getIdAvis() {
        return idAvis;
    }

    public void setIdAvis(String idAvis) {
        this.idAvis = idAvis;
    }

    public Integer getNote() {
        return note;
    }

    public void setNote(Integer note) {
        this.note = note;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Long getIdOeuvre() {
        return idOeuvre;
    }

    public void setIdOeuvre(Long idOeuvre) {
        this.idOeuvre = idOeuvre;
    }

    public String getLoginUtilisateur() {
        return loginUtilisateur;
    }

    public void setLoginUtilisateur(String loginUtilisateur) {
        this.loginUtilisateur = loginUtilisateur;
    }
}
