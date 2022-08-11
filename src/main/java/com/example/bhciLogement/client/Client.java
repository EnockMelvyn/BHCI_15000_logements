package com.example.bhciLogement.client;

import com.example.bhciLogement.clientLogement.ClientLogement;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @SequenceGenerator(
//            name = "client_sequence",
//            sequenceName = "client_sequence",
//            allocationSize = 1
//    )
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE,
//            generator = "client_sequence"
//    )
    private Long idClient;
    private String nomClient;
    private String prenomClient;
    private String professionClient;
    private String contactClient ;
    private Date dateNaissance ;
    private String lieuNaissance ;
    private String numeroCNI ;
    private String matriculePro;
    private String poste;
    private String adressePostale;
    private String email;
    private LocalDate created_at;
    private Integer created_by;
    private LocalDate modified_at;
    private Integer modified_by;
    private TrancheSalariale trancheSalariale;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<ClientLogement> clientLogements = new ArrayList<>();

    public void addClientLogement(ClientLogement clientLogement) {
        clientLogements.add(clientLogement);
        clientLogement.setClient(this);
    }
    public void removeClientLogement(ClientLogement clientLogement) {
        clientLogement.setClient(null);
        this.clientLogements.remove(clientLogement);

    }

    public Client() {
    }

    public Client(Long idClient, String nomClient, String prenomClient, String professionClient, Date dateNaissance, String lieuNaissance, String numeroCNI, String matriculePro, String adressePostale, String email) {
        this.idClient = idClient;
        this.nomClient = nomClient;
        this.prenomClient = prenomClient;
        this.professionClient = professionClient;
        this.dateNaissance = dateNaissance;
        this.lieuNaissance = lieuNaissance;
        this.numeroCNI = numeroCNI;
        this.matriculePro = matriculePro;
        this.adressePostale = adressePostale;
        this.email = email;
    }

    public Client(String nomClient, String prenomClient, String professionClient, Date dateNaissance, String lieuNaissance, String numeroCNI, String matriculePro, String adressePostale, String email) {
        this.nomClient = nomClient;
        this.prenomClient = prenomClient;
        this.professionClient = professionClient;
        this.dateNaissance = dateNaissance;
        this.lieuNaissance = lieuNaissance;
        this.numeroCNI = numeroCNI;
        this.matriculePro = matriculePro;
        this.adressePostale = adressePostale;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Client{" +
                "nomClient='" + nomClient + '\'' +
                ", clientLogements=" + clientLogements +
                '}';
    }
}
