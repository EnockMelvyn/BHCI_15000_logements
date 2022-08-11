package com.example.bhciLogement.client;

import com.example.bhciLogement.logement.Logement;
import com.example.bhciLogement.logement.LogementRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Arrays;

@Configuration
public class ClientConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    CommandLineRunner commandLineRunner(ClientRepository clientRepository, LogementRepository logementRepository){
        return args -> {
//            Client test = new Client(
//                    "Test",
//                    "Test",
//                    "profession_client",
//                    LocalDate.of(2021,01,01),
//                    "Abidjan",
//                    "CNI001004",
//                    "MATRICULE001",
//                    "Abidjan BP 01",
//                    "test@gmail.com");
//            Client innocent = new Client(
//                    "Test",
//                    "Test",
//                    "profession_client",
//                    LocalDate.of(2021,01,01),
//                    "Abidjan",
//                    "CNI001004",
//                    "MATRICULE001",
//                    "Abidjan BP 01",
//                    "test@gmail.com");
//
//            clientRepository.saveAll(
//                    Arrays.asList(test,innocent)
//            );

//            logementRepository.saveAll(
//                    Arrays.asList(new Logement("STUDIO F1 (1 pièces)",22,0,45000,95),
//                            new Logement("STUDIO F2 (2 pièces)",29,0,65000,142),
//                            new Logement("F3 (3 pièces)",54,0,125000,912),
//                            new Logement("F3 (3 pièces)",60,0,135000,8765),
//                            new Logement("F4 (4 pièces)",80,0,165000,3596),
//                            new Logement("VILLA F3",60,120,190000,621),
//                            new Logement("VILLA F4",80,200,240000,730),
//                            new Logement("VILLA F5/200",100,200,320000,108),
//                            new Logement("VILLA F5/300",100,300,350000,31))
//            );
        };
    }
}
