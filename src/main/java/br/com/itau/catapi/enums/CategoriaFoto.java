package br.com.itau.catapi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CategoriaFoto {

    COM_CHAPEU(1, "Com chapéu"),
    COM_OCULOS(4, "Com óculos");

    private Integer id;
    private String descricao;

//    CategoriaFoto(Integer id, String descricao) {
//        this.id = id;
//        this.descricao = descricao;
//    }

}
