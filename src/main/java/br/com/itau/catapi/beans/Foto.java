package br.com.itau.catapi.beans;

import br.com.itau.catapi.enums.TipoFoto;
import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Data
@Builder
@Entity
@Table(name = "fotos")
public class Foto {

    @Id
    private String id;

    @JsonAlias("url")
    private String urlFoto;

    @Enumerated(EnumType.STRING)
    private TipoFoto tipoFoto;

}
