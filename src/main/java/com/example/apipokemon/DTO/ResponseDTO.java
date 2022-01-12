package com.example.apipokemon.DTO;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseDTO {

    private String id;
    private String name;
    private Double height;
    private Double weight;
    private String imagem;

    private List<LocalDTO> localDTOList;
}
