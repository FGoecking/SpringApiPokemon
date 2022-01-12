package com.example.apipokemon.service;


import com.example.apipokemon.DTO.LocalDTO;
import com.example.apipokemon.DTO.PokemonDTO;
import com.example.apipokemon.model.PokemonCep;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class LocalService {

    private static final String BASEURL = "https://viacep.com.br/ws/";


    public List<LocalDTO> getLocalByCep(List<PokemonCep> pokemonCepList) throws IOException, InterruptedException {

        List<LocalDTO> localDTOList = new ArrayList<>();

        pokemonCepList.forEach( pokemonCep -> {

            String cep = pokemonCep.getCep();

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(BASEURL + cep + "/json"))
                    .build();

            HttpResponse<String> response = null;
            try {
                response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            response.statusCode();
            ObjectMapper mapper = new ObjectMapper();
            LocalDTO localDTO = new LocalDTO();
            try {
                localDTO = mapper.readValue(response.body(), new TypeReference<LocalDTO>() {
                });
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            localDTOList.add(localDTO);
        });


        return localDTOList;
    }
}
