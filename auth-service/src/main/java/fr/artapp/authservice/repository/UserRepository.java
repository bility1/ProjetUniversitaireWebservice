package fr.artapp.authservice.repository;

import fr.artapp.authservice.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {
}
