package fr.artapp.artservice.DTO;

import fr.artapp.artservice.model.Categorie;

import java.io.Serializable;
import java.time.LocalDateTime;

public class OeuvreDTO {

    private Long id;
    private String titre;
    private LocalDateTime date;
    private CategorieDTO categorie;
    private String utilisateurId; //login utilisateur unique

    public OeuvreDTO() {
    }

    public OeuvreDTO(Long id, String titre, LocalDateTime date, CategorieDTO categorie, String utilisateurId) {
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
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
}
