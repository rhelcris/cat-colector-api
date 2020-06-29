package br.com.itau.catapi.dto;

import br.com.itau.catapi.beans.Raca;
import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import java.util.List;

@Data
public class CatFotoDTO {

    @JsonAlias("id")
    private String id;
    @JsonAlias("breeds")
    private List<Raca> raca;
    @JsonAlias("url")
    private String url;

}
