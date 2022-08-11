package com.example.bhciLogement.clientLogement;


import com.example.bhciLogement.client.Client;
import com.example.bhciLogement.logement.Logement;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "client_logement")
public class ClientLogement {


//    private ClientLogementKey id = new ClientLogementKey();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
//    @MapsId("clientId")
    @JoinColumn(name = "client_id")
    @JsonIgnore
    private Client client;

    @ManyToOne
//    @MapsId("logementId")
    @JoinColumn(name = "logement_id")
    private Logement logement;
//
    @Column(name = "is_validate")
    private Boolean isValidate;

    @Column(name = "piece_admin")
    private String pieceAdmin;


    public ClientLogement(Client client, Logement logement, Boolean isValidate) {
        this.client = client;
        this.logement = logement;
        this.isValidate = isValidate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClientLogement )) return false;
        return id != null && id.equals(((ClientLogement) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
