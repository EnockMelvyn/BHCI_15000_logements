package com.example.bhciLogement.client;

import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.web.multipart.MultipartFile;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class ClientDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long idClient;
    private String nomClient;
    private String prenomClient;
    private String professionClient;
    private String contactClient;
    private String dateNaissance ;
    private String lieuNaissance ;
    private String numeroCNI ;
    private String matriculePro;
    private String adressePostale;
    private String email;
    private String trancheSalariale;

    private MultipartFile[] files;
    private Long idLogement;

    private List<Long> clientLogementsId = new ArrayList<>();
    private List<Boolean> clientLogementsIsValidate= new ArrayList<>();
    private List<Long> clientLogementsLogementId = new ArrayList<>();
    private List<String> clientLogementsLogementLibelle = new ArrayList<>();



}
