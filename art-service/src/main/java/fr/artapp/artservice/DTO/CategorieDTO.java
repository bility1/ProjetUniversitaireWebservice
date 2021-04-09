package fr.artapp.artservice.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import fr.artapp.artservice.model.Oeuvre;

import javax.persistence.OneToMany;
import java.util.Set;

public class CategorieDTO {

    private Long id;
    private String nomCategorie;
    private String description;

    public CategorieDTO(Long id, String nomCategorie, String description) {
        this.id = id;
        this.nomCategorie = nomCategorie;
        this.description = description;
    }

    public CategorieDTO() {
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
