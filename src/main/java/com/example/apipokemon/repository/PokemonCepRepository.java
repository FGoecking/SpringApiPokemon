package com.example.apipokemon.repository;

import com.example.apipokemon.model.PokemonCep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PokemonCepRepository extends JpaRepository<PokemonCep, Long> {


    List<PokemonCep> getAllByPokemon(String idpokemon);

}