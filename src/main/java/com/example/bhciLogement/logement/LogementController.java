package com.example.bhciLogement.logement;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path = "logements/")
public class LogementController {

    private final LogementService logementService;

    private LogementTransformer logementTransformer;

    @GetMapping(path = "list")
    public List<LogementDTO> getLogements(){
        return logementTransformer.convertToDto(logementService.getLogements());
    }
}
