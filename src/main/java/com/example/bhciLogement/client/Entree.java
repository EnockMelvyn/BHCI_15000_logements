package com.example.bhciLogement.client;

import com.example.bhciLogement.logement.Logement;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.Date;

@Data
public class Entree {
//    private Client client;
//    private Logement logement;
    private MultipartFile[] files;

    private String nomClient;
    private String prenomClient;
    private String professionClient;
    private String contactClient ;
    private Date dateNaissance ;
    private String lieuNaissance ;
    private String numeroCNI ;
    private String matriculePro;
    private String adressePostale;
    private String email;
    private TrancheSalariale trancheSalariale;

    private Long idLogement;
    private String libelle;

}
