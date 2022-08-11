package com.example.bhciLogement.logement;

import com.example.bhciLogement.clientLogement.ClientLogementDTO;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

@Data
public class LogementDTO {

    private static final long serialVersionUID = 1L;

    private Long idLogement;
    private String libelle;
    private int surfaceHabitable;
    private int superficie;
    private int loyer;
    private int nombreTotal;
    private LocalDate created_at;
    private Integer created_by;
    private LocalDate modified_at;
    private Integer modified_by;

//    private Collection<ClientLogementDTO> clientLogements = new ArrayList<>();

}
