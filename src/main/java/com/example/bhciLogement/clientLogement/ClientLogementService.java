package com.example.bhciLogement.clientLogement;

import com.example.bhciLogement.client.Client;
import com.example.bhciLogement.client.ClientService;
import com.example.bhciLogement.logement.Logement;
import com.example.bhciLogement.logement.LogementService;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@Service
public class ClientLogementService {

    private final ClientLogementRepository clientLogementRepository;
    private final ClientService clientService;
    private final LogementService logementService;

    private final EntityManager entityManager;
    private final Session session;

    public List<ClientLogement> getClientLogements(){ return this.clientLogementRepository.findAll();}

    public List<ClientLogement> getClientLogementsWaitValidation(){ return this.clientLogementRepository.findClientLogementWaitValidation();}

    public List<ClientLogement> getClientLogementsValidate(){ return this.clientLogementRepository.findClientLogementValidate();}

    public List<ClientLogement> getClientLogementsNotValidate(){ return this.clientLogementRepository.findClientLogementNotValidate();}


    public List<ClientLogement> getClientLogementByClient(Long idClient){
        Client client = clientService.getClient(idClient);
        return this.clientLogementRepository.findClientLogementByClient(client);}

    public ClientLogement getClientLogement( Long idClientLogement){
        return clientLogementRepository.findById(idClientLogement)
                .orElseThrow(() ->
                        new IllegalStateException(" Le client id:" + idClientLogement +" n'existe pas")
                );

    }

    @Transactional
    public void updateClientLogement( Long idClientLogement, Long logementId){
        ClientLogement clientLogement = clientLogementRepository.findById(idClientLogement)
                .orElseThrow(() ->
                        new IllegalStateException(" Le client id:" + idClientLogement +" n'existe pas")
                );

        if (logementId != null) {
            Logement logement = logementService.findLogementById(logementId);
            clientLogement.setLogement(logement);
        }

    }

    @Transactional
    public void validateLogementClient( Long idClientLogement, Boolean validate){
        ClientLogement clientLogement = clientLogementRepository.findById(idClientLogement)
                .orElseThrow(() ->
                        new IllegalStateException(" Le client id:" + idClientLogement +" n'existe pas")
                );

        if (validate == true){
            clientLogement.setIsValidate(true);
        } else {
            clientLogement.setIsValidate(false);
        }
    };

    @Transactional
    public void validateLogementsClient( List<Long> idClientLogement, Boolean validate){
            idClientLogement.stream()
                            .forEach(c-> validateLogementClient(c,validate));
    }

    public static void saveToFile(String folderPath, String fileName) throws IOException {
        File directory = new File(folderPath);
        if (!directory.exists() && !directory.mkdirs()) {
            throw new IOException("Directory does not exist and could not be created");
        }
        String filePath = folderPath + File.separator + fileName;
        File theFile = new File(filePath);
        if (!theFile.exists()) {
            try {
                theFile.createNewFile();
            } catch (IOException e) {
                throw new IOException("Facing issues in creating file " + filePath, e);
            }
        }
    }

}
