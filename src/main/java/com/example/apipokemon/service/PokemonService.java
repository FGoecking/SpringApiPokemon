package com.example.apipokemon.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.example.apipokemon.DTO.ListaPokemonDTO;
import com.example.apipokemon.DTO.PokemonDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PokemonService {

	private static final String BASEURL = "https://pokeapi.co/api/v2/pokemon";

	public ListaPokemonDTO getPokemons()
			throws IOException, InterruptedException {

		int limit = 60;
		ListaPokemonDTO listaPokemonDTOS = new ListaPokemonDTO();
		listaPokemonDTOS.setResults(new ArrayList<>());

		String address = BASEURL + "/?limit=" + limit + "&offset=0";
		do {
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest httpRequest = HttpRequest.newBuilder().GET()
					.uri(URI.create(address)).build();

			HttpResponse<String> response = client.send(httpRequest,
					HttpResponse.BodyHandlers.ofString());

			if (response.statusCode() == 200) {
				ObjectMapper mapper = new ObjectMapper();

				ListaPokemonDTO listaTemp = mapper.readValue(response.body(),
						new TypeReference<ListaPokemonDTO>() {
						});

				if (listaTemp != null) {
					listaPokemonDTOS.getResults()
							.addAll(listaTemp.getResults());
					
					address = listaTemp.getNext();
				} else {
					break;
				}
			}

		} while (address != null);

		return listaPokemonDTOS;

	}

	public PokemonDTO getPokemonById(String id)
			throws IOException, InterruptedException {

		HttpClient client = HttpClient.newHttpClient();
		HttpRequest httpRequest = HttpRequest.newBuilder().GET()
				.uri(URI.create(BASEURL + "/" + id)).build();

		HttpResponse<String> response = client.send(httpRequest,
				HttpResponse.BodyHandlers.ofString());
		response.statusCode();
		ObjectMapper mapper = new ObjectMapper();
		PokemonDTO pokemonDTO = mapper.readValue(response.body(),
				new TypeReference<PokemonDTO>() {
				});

		return pokemonDTO;
	}
}
