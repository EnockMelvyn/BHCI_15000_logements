package com.example.bhciLogement.clientLogement;

import com.example.bhciLogement.client.ClientDTO;
import com.example.bhciLogement.logement.LogementDTO;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Data
public class ClientLogementDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private Boolean isValidate;
    private String pieceAdmin;

    private Long clientIdClient;
    private String nomClient;
    private String prenomClient;
    private String clientEmail;
    private String clientProfessionClient;
//    private String clientDateNaissance ;
    private String clientLieuNaissance ;

    private Long logementIdLogement;
    private String logementLibelle;
    private int logementSurfaceHabitable;
    private int logementSuperficie;
    private int logementLoyer;
    private int logementNombreTotal;


}
