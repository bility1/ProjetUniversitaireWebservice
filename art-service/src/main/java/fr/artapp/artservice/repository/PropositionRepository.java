package fr.artapp.artservice.repository;

import fr.artapp.artservice.model.Proposition;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropositionRepository extends CrudRepository<Proposition,Long> {
}
