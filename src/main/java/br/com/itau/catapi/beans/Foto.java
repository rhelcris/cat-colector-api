package br.com.itau.catapi.beans;

import br.com.itau.catapi.enums.TipoFoto;
import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Builder;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@Builder
public class Foto {

    @JsonAlias("url")
    private String urlFoto;

    @Enumerated(EnumType.STRING)
    private TipoFoto tipoFoto;

}
