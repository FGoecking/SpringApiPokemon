package com.example.apipokemon.service;


import com.example.apipokemon.DTO.ListaPokemonDTO;
import com.example.apipokemon.DTO.PokemonDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class PokemonService {



    private static final String BASEURL = "https://pokeapi.co/api/v2/pokemon";



    public ListaPokemonDTO getPokemons() throws IOException, InterruptedException {

        int i = 1;
        int offset = 60;
        ListaPokemonDTO listaPokemonDTOS = new ListaPokemonDTO();

        do {HttpClient client = HttpClient.newHttpClient();
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(BASEURL + "/?limit="+ offset +"&offset=" + (offset * i)))
                    .build();

            HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            response.statusCode();
            ObjectMapper mapper = new ObjectMapper();
            listaPokemonDTOS = mapper.readValue(response.body(), new TypeReference<ListaPokemonDTO>() {});} while(i < 15);
        i++;
        return listaPokemonDTOS;


    }

    public PokemonDTO getPokemonById(String id) throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(BASEURL + id + "/"))
                .build();

        HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        response.statusCode();
        ObjectMapper mapper = new ObjectMapper();
        PokemonDTO pokemonDTO = mapper.readValue(response.body(), new TypeReference<PokemonDTO>() {});

        return pokemonDTO;
    }
}
