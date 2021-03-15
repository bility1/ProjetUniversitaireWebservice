package fr.artapp.artservice.service;

import fr.artapp.artservice.model.Oeuvre;
import fr.artapp.artservice.repository.OeuvreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ArtServiceImpl implements ArtService{

    @Autowired
    OeuvreRepository oeuvreRepository;


    @Override
    public Collection<Oeuvre> getAllOeuvre() {
        return (Collection<Oeuvre>)  oeuvreRepository.findAll();

    }
}
