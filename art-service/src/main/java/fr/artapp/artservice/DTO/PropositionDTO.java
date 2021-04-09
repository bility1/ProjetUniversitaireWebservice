package fr.artapp.artservice.DTO;

import java.util.Set;

public class PropositionDTO {
    private Long id;
    private String nomCategorie;
    private String description;
    private Set<String> utilisateurs;
    private String auteur;

    public PropositionDTO() {
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
