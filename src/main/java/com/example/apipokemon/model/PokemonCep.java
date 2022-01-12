package com.example.apipokemon.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class PokemonCep {

    @Id
    @SequenceGenerator(name = "pokemoncep_sequence", sequenceName = "pokemoncep_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pokemoncep_sequence")
    private Long idtabela;

    private String pokemon;

    private String cep;
}
