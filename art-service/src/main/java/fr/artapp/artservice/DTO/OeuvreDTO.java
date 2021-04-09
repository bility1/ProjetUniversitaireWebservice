package fr.artapp.artservice.DTO;

import fr.artapp.artservice.model.Categorie;

import javax.persistence.Lob;
import java.io.Serializable;
import java.time.LocalDateTime;

public class OeuvreDTO {

    private Long id;
    private String titre;
    private String date;
    private CategorieDTO categorie;
    private String utilisateurId; //login utilisateur unique
    private byte[] content;

    public OeuvreDTO() {
    }

    public OeuvreDTO(Long id, String titre, String date, CategorieDTO categorie, String utilisateurId) {
        this.id = id;
        this.titre = titre;
        this.date = date;
        this.categorie = categorie;
        this.utilisateurId = utilisateurId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public CategorieDTO getCategorie() {
        return categorie;
    }

    public void setCategorie(CategorieDTO categorie) {
        this.categorie = categorie;
    }

    public String getUtilisateurId() {
        return utilisateurId;
    }

    public void setUtilisateurId(String utilisateurId) {
        this.utilisateurId = utilisateurId;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
