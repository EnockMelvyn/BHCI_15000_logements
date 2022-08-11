package com.example.bhciLogement.clientLogement;


import com.example.bhciLogement.client.Client;
import com.example.bhciLogement.logement.Logement;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@Data
public class ClientLogementKey implements Serializable {

    private static final long serialVersionUID = 1L;
//
    private Long clientId;
    private Long logementId;

}
