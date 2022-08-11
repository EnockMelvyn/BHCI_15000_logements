package com.example.bhciLogement.logement;

import com.example.bhciLogement.clientLogement.ClientLogement;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

@Getter
@Setter
@Entity
@Table(name = "logement")
@Data
@NoArgsConstructor @AllArgsConstructor
public class Logement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToMany(mappedBy = "logement", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Collection<ClientLogement> clientLogements = new ArrayList<>();

    public Logement(String libelle, int surfaceHabitable, int superficie, int loyer, int nombreTotal) {
        this.libelle = libelle;
        this.surfaceHabitable = surfaceHabitable;
        this.superficie = superficie;
        this.loyer = loyer;
        this.nombreTotal = nombreTotal;
    }
}
