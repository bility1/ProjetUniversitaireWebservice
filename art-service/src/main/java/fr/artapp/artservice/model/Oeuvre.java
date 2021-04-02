package fr.artapp.artservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import fr.artapp.artservice.DTO.OeuvreDTO;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class Oeuvre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //@Column (name = "titre")
    private String titre;
    private LocalDateTime date;
    @JsonBackReference
    @ManyToOne
    private Categorie categorie;
    private String utilisateurId; //login utilisateur unique
    //attribut image

    public Oeuvre(String titre, LocalDateTime date, Categorie categorie, String utilisateurId) {
        this.titre = titre;
        this.date = date;
        this.categorie = categorie;
        this.utilisateurId = utilisateurId;
    }

    public Oeuvre() {
    }
    /*
    public Oeuvre (String titre){
        this.titre=titre;
    }*/

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Long getId() {
        return id; }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getUtilisateurId() {
        return utilisateurId;
    }

    public void setUtilisateurId(String utilisateurId) {
        this.utilisateurId = utilisateurId;
    }
}

