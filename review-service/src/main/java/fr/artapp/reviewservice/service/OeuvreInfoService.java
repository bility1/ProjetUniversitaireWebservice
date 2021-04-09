package fr.artapp.reviewservice.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import fr.artapp.reviewservice.DTO.OeuvreDTO;
import fr.artapp.reviewservice.exceptions.NoteNotPossibleException;
import fr.artapp.reviewservice.exceptions.OeuvreNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class OeuvreInfoService {

    @Value("${hostUrl}")
    private String hostUrl;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @HystrixCommand(fallbackMethod = "getFallBackVerifOeuvreExist")
    public boolean verifOeuvreExist(Long OeuvreId) {
        String uri =hostUrl+"/api/art/oeuvres/"+OeuvreId;
        OeuvreDTO oeuvreDTO= webClientBuilder.build().get()
                .uri(uri)
                .retrieve()
                .bodyToMono(OeuvreDTO.class)
                .block();
        return true;
    }

    public boolean getFallBackVerifOeuvreExist(Long OeuvreId)  {
        return false;
    }


}
