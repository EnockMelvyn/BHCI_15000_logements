package com.example.bhciLogement.client;

import com.example.bhciLogement.logement.Logement;
import com.example.bhciLogement.registration.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.security.RolesAllowed;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path = "clients")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    private ClientTransformer clientTransformer;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @RolesAllowed("admin")
    @GetMapping(path = "list")
    public List<ClientDTO> getClients(){
        return clientTransformer.convertToDto(clientService.getClients());
    }


    @PostMapping(path = "addWithFile")
    public ResponseEntity<?> addNewClientFile(@ModelAttribute("clientDTO") ClientDTO clientDTO) {
//        System.out.println(entree.getClient());
//        System.out.println(entree.getLogement());
        try {
            System.out.println("DTO GET FILES");
            System.out.println(clientDTO.getFiles());


            Client client = clientTransformer.convertToEntity(clientDTO);
            Logement logement = new Logement();
            logement.setIdLogement(clientDTO.getIdLogement());

                clientService.addNewClient(client, logement, clientDTO.getFiles());
        }
        catch (Exception e){
            System.out.println(e);
        }
        return new ResponseEntity<String>("Uploaded to: " + clientDTO, HttpStatus.OK);

    }

    @PostMapping(path="add")
    public ResponseEntity<?> addNewClient(@RequestBody ClientDTO clientDTO) {
            System.out.println("DTO GET FILES");
            System.out.println(clientDTO.getFiles());

            Client client = clientTransformer.convertToEntity(clientDTO);
            Logement logement = new Logement();
            logement.setIdLogement(clientDTO.getIdLogement());

            clientService.addNewClient(client, logement);
        return new ResponseEntity<String>("Uploaded to: " + clientDTO, HttpStatus.OK);

    }

//    @PostMapping(path = "client")
//    public ResponseEntity<?> addNewClient(@ModelAttribute("entree") Entree entree) throws IOException {
////        System.out.println(entree.getClient());
//        ClientDTO clientDTO = new ClientDTO();
//        Client client = new Client(entree.getNomClient(), entree.getPrenomClient(), entree.getProfessionClient(),entree.getDateNaissance(),
//                entree.getLieuNaissance(), entree.getNumeroCNI(), entree.getMatriculePro(), entree.getAdressePostale(), entree.getEmail());
////        System.out.println(entree.getLogement());
//        Logement logement = new Logement();
//        logement.setIdLogement(entree.getIdLogement());
//        if (Arrays.stream(entree.getFiles()).count()!=0){
//            clientDTO = clientTransformer.convertToDto(clientService.addNewClient(client, logement, entree.getFiles()));
//        } else {
//            clientDTO = clientTransformer.convertToDto(clientService.addNewClient(client, logement));
//        }
//        return new ResponseEntity<String>("Uploaded to: " + clientDTO, HttpStatus.OK);
//
//    }

    @DeleteMapping(path = "{idClient}")
    public void deleteClient(@PathVariable("idClient") Long idClient){
        clientService.removeClient(idClient);
    }

    @PutMapping(path = "{idClient}")
    public ClientDTO updateClient(@PathVariable("idClient") Long idClient,
                                 @RequestParam(required = false) String name,
                                 @RequestParam(required = false) String email){
        return clientTransformer.convertToDto(clientService.updateClient(idClient, name, email));
    }



}
