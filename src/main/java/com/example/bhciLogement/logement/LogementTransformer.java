package com.example.bhciLogement.logement;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LogementTransformer {private ModelMapper modelMapper = new ModelMapper() ;


//    private ModelMapper modelMapper = new ModelMapper() ;


    public LogementDTO convertToDto(Logement logement) {
        LogementDTO logementDto = modelMapper.map(logement, LogementDTO.class);
        return logementDto;
    }

    public Logement convertToEntity(LogementDTO logementDTO){
        Logement post = modelMapper.map(logementDTO, Logement.class);
        return post;
    }

    public List<Logement> convertToEntity(List<LogementDTO> logementDTOS) {
        List<Logement> logements= mapList(logementDTOS, Logement.class);
        return logements;
    }

    public List<LogementDTO> convertToDto(List<Logement> logements) {
        List<LogementDTO> logementDTOS= mapList(logements, LogementDTO.class);
        return logementDTOS;
    }

    <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        return source
                .stream()
                .map(element -> modelMapper.map(element, targetClass))
                .collect(Collectors.toList());
    }
}
