package br.com.itau.catapi.beans;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Gato {

    // Gerar ID
    private String id;
    private Raca raca;
    private List<Foto> fotos;

}
