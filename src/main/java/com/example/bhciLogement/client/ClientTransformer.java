package com.example.bhciLogement.client;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClientTransformer {

    private ModelMapper modelMapper = new ModelMapper() ;


    public ClientDTO convertToDto(Client client) {
        ClientDTO clientDto = modelMapper.map(client, ClientDTO.class);
        client.getClientLogements()
                .forEach(clientLogement -> {
                    System.out.println(clientLogement.getId());
                    clientDto.getClientLogementsId().add(clientLogement.getId());
                    clientDto.getClientLogementsLogementId().add(clientLogement.getLogement().getIdLogement());
                    clientDto.getClientLogementsLogementLibelle().add(clientLogement.getLogement().getLibelle());
                    clientDto.getClientLogementsIsValidate().add(clientLogement.getIsValidate());
                });
        return clientDto;
    }

    public Client convertToEntity(ClientDTO clientDTO){
        Client client = modelMapper.map(clientDTO, Client.class);
        return client;
    }

    public List<Client> convertToEntity(List<ClientDTO> clientDTOS) {
        List<Client> clients= mapList(clientDTOS, Client.class);
        return clients;
    }

    public List<ClientDTO> convertToDto(List<Client> clients) {
        List<ClientDTO> clientDTOS= mapList(clients, ClientDTO.class);
        return clientDTOS;
    }

    <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        return source
                .stream()
                .map(element -> modelMapper.map(element, targetClass))
                .collect(Collectors.toList());
    }


}
