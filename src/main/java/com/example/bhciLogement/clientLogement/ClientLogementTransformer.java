package com.example.bhciLogement.clientLogement;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClientLogementTransformer {private ModelMapper modelMapper = new ModelMapper() ;


    public ClientLogementDTO convertToDto(ClientLogement clientLogement) {
        ClientLogementDTO clientLogementDto = modelMapper.map(clientLogement, ClientLogementDTO.class);
        return clientLogementDto;
    }

    public ClientLogement convertToEntity(ClientLogementDTO clientLogementDTO){
        ClientLogement post = modelMapper.map(clientLogementDTO, ClientLogement.class);
        return post;
    }

    public List<ClientLogement> convertToEntity(List<ClientLogementDTO> clientLogementDTOS) {
        List<ClientLogement> clientLogements= mapList(clientLogementDTOS, ClientLogement.class);
        return clientLogements;
    }

    public List<ClientLogementDTO> convertToDto(List<ClientLogement> clientLogements) {
        List<ClientLogementDTO> clientLogementDTOS= mapList(clientLogements, ClientLogementDTO.class);
        return clientLogementDTOS;
    }

    <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        return source
                .stream()
                .map(element -> modelMapper.map(element, targetClass))
                .collect(Collectors.toList());
    }
}
