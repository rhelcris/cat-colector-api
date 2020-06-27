package br.com.itau.catapi.dto;

import br.com.itau.catapi.beans.Foto;
import br.com.itau.catapi.beans.Raca;
import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import java.util.List;
import java.util.Optional;

@Data
public class CatFotoDTO {

    @JsonAlias("breeds")
    private List<Raca> raca;
    @JsonAlias("url")
    private String url;

}
