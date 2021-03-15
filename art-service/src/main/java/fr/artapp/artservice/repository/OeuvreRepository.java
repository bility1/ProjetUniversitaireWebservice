package fr.artapp.artservice.repository;

import fr.artapp.artservice.model.Oeuvre;
import org.springframework.data.repository.CrudRepository;

public interface OeuvreRepository  extends CrudRepository<Oeuvre,Long> {
}
