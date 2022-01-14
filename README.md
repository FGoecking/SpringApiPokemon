# Api Pokemon-cep


Este projeto é uma api que faz consultas nas apis e no banco de dados.

- [PokeApi](https://pokeapi.co/)
- [ViaCep](https://viacep.com.br/)



## Lógica
```sh
/webclient/pokemon
```
A Api caso não receba nenhum parametro retorna todos os pokemons.


```sh
/webclient/pokemon/{id}
```
A Api ao receber um id de pokemon busca os dados do pokemon na PokeApi, após isso busca no banco de dadoso cep das localizações que o pokemon apareceu, após isso busca os dados referentes a esse cep na ViaCep e concatena na resposta esses dados na resposta da api.

```sh
/webclient/pokemon/novolocal
```
A Api salva no banco de dados uma nova relação pokemon-cep quando enviado um json no seguinte formato: 

> {
    "pokemon": "1",
    "cep": "32220530"
}

```sh
/webclient/upload
```
A Api salva os dados do arquivo que for passado nos parametros no banco de dados
