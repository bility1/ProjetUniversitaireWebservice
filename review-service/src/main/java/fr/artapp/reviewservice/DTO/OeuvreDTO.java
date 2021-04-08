package fr.artapp.reviewservice.DTO;

import java.time.LocalDateTime;

public class OeuvreDTO {

    private Long id;


    public OeuvreDTO() {
    }

    public OeuvreDTO(Long id) {
        this.id = id;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }




}
