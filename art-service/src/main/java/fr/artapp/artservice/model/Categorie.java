package fr.artapp.artservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomCategorie;
    @JsonManagedReference
    @OneToMany(mappedBy = "categorie")
    private Set<Oeuvre> oeuvre;
    private String description;

    public Categorie(String nomCategorie, Set<Oeuvre> oeuvre, String description) {
        this.nomCategorie = nomCategorie;
        this.oeuvre = oeuvre;
        this.description = description;
    }

    public Categorie() {
    }

    public Set<Oeuvre> getOeuvre() {
        return oeuvre;
    }

    public void setOeuvre(Set<Oeuvre> oeuvre) {
        this.oeuvre = oeuvre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomCategorie() {
        return nomCategorie;
    }

    public void setNomCategorie(String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

