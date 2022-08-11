package com.example.bhciLogement.clientLogement;

import com.example.bhciLogement.client.Client;
import com.example.bhciLogement.client.ClientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path = "api/v1/client/logement/")
public class ClientLogementController {

    private final ClientLogementService clientLogementService;

    @Autowired
    private ClientLogementTransformer clientLogementTransformer;

    public ClientLogementController(ClientLogementService clientLogementService) {
        this.clientLogementService = clientLogementService;
    }

    @GetMapping(path = "waitingList")
    @PreAuthorize("hasRole('USER')")
    public List<ClientLogementDTO> getClientLogementsWaitValidation(){
        return clientLogementTransformer.convertToDto(clientLogementService.getClientLogementsWaitValidation());
    }

    @GetMapping(path = "validatedList")
    @PreAuthorize("hasRole('USER')")
    public List<ClientLogementDTO> getClientLogementsValidate(){
        return clientLogementTransformer.convertToDto(clientLogementService.getClientLogementsValidate());
    }

    @GetMapping(path = "refusedList")
    @PreAuthorize("hasRole('USER')")
    public List<ClientLogementDTO> getClientLogementsNotValidate(){
        return clientLogementTransformer.convertToDto(clientLogementService.getClientLogementsNotValidate());
    }

    @GetMapping(path = "{idClient}")
    @PreAuthorize("hasRole('USER')")
    public List<ClientLogementDTO> getClientLogementsByClient(@PathVariable("idClient") Long idClient){
        return clientLogementTransformer.convertToDto(clientLogementService.getClientLogementByClient(idClient));
    }

    @PutMapping(path = "{idClientLogement}")
    @PreAuthorize("hasRole('USER')")
    public void updateClientLogement(@PathVariable("idClientLogement") Long idClientLogement,
                             @RequestParam Long logementId){
        clientLogementService.updateClientLogement(idClientLogement, logementId);
    }

    @PutMapping(path = "validate/{idClientLogement}")
    @PreAuthorize("hasRole('USER')")
    public void validateLogementClient(@PathVariable("idClientLogement") Long idClientLogement) {
        clientLogementService.validateLogementClient(idClientLogement, true);
    }
    @PutMapping(path = "validateAllSelected")
    @PreAuthorize("hasRole('USER')")
    public void validateLogementsClient(@RequestBody List<ClientLogementDTO> clientLogements) {
        List<Long> listIds = new ArrayList<>();
        clientLogements.stream().forEach(v ->{
            listIds.add(v.getId());
        });
        clientLogementService.validateLogementsClient(listIds, true);
    }

    @PutMapping(path = "refuse/{idClientLogement}")
    @PreAuthorize("hasRole('USER')")
    public void refuseLogementClient(@PathVariable("idClientLogement") Long idClientLogement) {
        clientLogementService.validateLogementClient(idClientLogement, false);
    }
    @PutMapping(path = "refuseAllSelected")
    @PreAuthorize("hasRole('USER')")
    public void refuseLogementsClient(@RequestBody List<ClientLogementDTO> clientLogements) {
        List<Long> listIds = new ArrayList<>();
        clientLogements.stream().forEach(v ->{
            listIds.add(v.getId());
        });
        clientLogementService.validateLogementsClient(listIds, false);
    }
}
