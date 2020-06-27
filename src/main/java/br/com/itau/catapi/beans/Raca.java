package br.com.itau.catapi.beans;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import java.util.List;

@Data
public class Raca {

    @JsonAlias("id")
    private String id;
    @JsonAlias("name")
    private String nome;
    @JsonAlias("temperament")
    private String temperamento;
    @JsonAlias("origin")
    private String origem;



}
