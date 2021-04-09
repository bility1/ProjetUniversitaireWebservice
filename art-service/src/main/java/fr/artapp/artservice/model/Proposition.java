package fr.artapp.artservice.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Proposition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomCategorie;
    private String description;
    @ElementCollection(targetClass=String.class)
    private Set<String> utilisateurs;
    private String auteur;

    public Proposition() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNomCategorie() {
        return nomCategorie;
    }

    public void setNomCategorie(String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public Set<String> getUtilisateurs() {
        return utilisateurs;
    }

    public void setUtilisateurs(Set<String> utilisateurs) {
        this.utilisateurs = utilisateurs;
    }
}
