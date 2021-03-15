package fr.artapp.authservice.service;

import fr.artapp.authservice.model.User;
import fr.artapp.authservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;


    @Override
    public Collection<User> getAllUser() {
        return (Collection<User>)  userRepository.findAll();

    }
}
