package com.example.bhciLogement.logement;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class LogementService {
    private LogementRepository logementRepository;

    public List<Logement> getLogements(){ return this.logementRepository.findAll(); }

    public Logement findLogementById (Long idLogement){

           Optional<Logement> logement=this.logementRepository.findById(idLogement);
           if (logement.isPresent()) {
               return logement.get();
           }
           else throw new IllegalStateException("Logement inexistant");
    }

}
