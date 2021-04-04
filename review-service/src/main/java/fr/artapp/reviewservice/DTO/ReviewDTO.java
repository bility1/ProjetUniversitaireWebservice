package fr.artapp.reviewservice.DTO;


public class ReviewDTO {

    private String idAvis;
    private Integer note;
    private String commentaire;
    private Long idOeuvre;
    private String loginUtilisateur;

    public ReviewDTO() {
    }

    public ReviewDTO(String idAvis, Integer note, String commentaire, Long idOeuvre, String loginUtilisateur) {
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
