package com.example.bhciLogement.client;

import com.example.bhciLogement.clientLogement.ClientLogement;
import com.example.bhciLogement.clientLogement.uploadform.UploadForm;
import com.example.bhciLogement.logement.Logement;
import com.example.bhciLogement.registration.RegistrationRequest;
import com.example.bhciLogement.registration.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@AllArgsConstructor
@Service
public class ClientService {

    private static String UPLOAD_DIR = System.getProperty("user.home") + "/15000Logements/";
    private final ClientRepository clientRepository;

    private RegistrationService registrationService;
//    private final Session session ;

    public List<Client> getClients(){
        return this.clientRepository.findAll();
    }

    public Client getClient(Long idClient){
        return this.clientRepository.findById(idClient)
                .orElseThrow(() ->
                        new IllegalStateException(" Le client id:" + idClient +" n'existe pas"));
    }

    @Transactional
    public Client addNewClient(Client client, Logement logement, MultipartFile[] files) throws IOException {

        ClientLogement clientLogement = new ClientLogement();
        clientLogement.setLogement(logement);
        clientLogement.setClient(client);

        String uuidLogement = UUID.randomUUID().toString();
        String result = null;
        try {
            result = this.saveUploadedFiles(files,UPLOAD_DIR+uuidLogement);
        }
        // Here Catch IOException only.
        // Other Exceptions catch by RestGlobalExceptionHandler class.
        catch (IOException e) {
            e.printStackTrace();
        }
        clientLogement.setPieceAdmin(uuidLogement);
        client.addClientLogement(clientLogement);

        RegistrationRequest regReq = new RegistrationRequest(client.getPrenomClient(),
                client.getNomClient(), "0000", client.getEmail());
        registrationService.register(regReq);
        return  clientRepository.save(client);

    }

    @Transactional
    public Client addNewClient(Client client, Logement logement){

        ClientLogement clientLogement = new ClientLogement();
        clientLogement.setLogement(logement);
        clientLogement.setClient(client);
        client.addClientLogement(clientLogement);

        RegistrationRequest regReq = new RegistrationRequest(client.getPrenomClient(),
                client.getNomClient(), "0000", client.getEmail());
        registrationService.register(regReq);
        return  clientRepository.save(client);

    }

    public void removeClient( Long idClient){
        boolean exists = clientRepository.existsById(idClient);
        if (!exists){
            throw new IllegalStateException(" Le client id:" + idClient +" n'existe pas");
        }
        clientRepository.deleteById(idClient);
    }

    @Transactional
    public Client updateClient( Long idClient, String name, String email){
        Client client = clientRepository.findById(idClient)
                .orElseThrow(() ->
                        new IllegalStateException(" Le client id:" + idClient +" n'existe pas")
                        );

        if (name != null &&
        name.length()>0 && !Objects.equals(client.getNomClient(),name)) {
            client.setNomClient(name);
        }

//        if (email != null &&
//                email.length()>0 && !Objects.equals(client.getEmail(),email)) {
//            Optional<Client> clientOptional = clientRepository
//                    .findClientByEmail(client.getEmail());
//            if (clientOptional.isPresent()){
//                throw new IllegalStateException("Email déja utilisé");
//            }
//            client.setEmail(email);
//        }
        return client;
    }
    private String saveUploadedFiles(MultipartFile[] files, String dir) throws IOException {

        // Make sure directory exists!
        File uploadDir = new File(dir);
        uploadDir.mkdirs();

        StringBuilder sb = new StringBuilder();

        for (MultipartFile file : files) {

            if (file.isEmpty()) {
                continue;
            }
            String uploadFilePath = dir + "/" + file.getOriginalFilename();

            byte[] bytes = file.getBytes();
            Path path = Paths.get(uploadFilePath);
            Files.write(path, bytes);

            sb.append(uploadFilePath).append(", ");
        }
        return sb.toString();
    }

}
