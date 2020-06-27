package br.com.itau.catapi.services;

import br.com.itau.catapi.beans.Foto;
import br.com.itau.catapi.beans.Gato;
import br.com.itau.catapi.beans.Raca;
import br.com.itau.catapi.dto.CatFotoDTO;
import br.com.itau.catapi.enums.CategoriaFoto;
import br.com.itau.catapi.enums.TipoFoto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class GatosService {

    private FotosService fotosService;

    @Autowired
    public GatosService(FotosService fotosService) {
        this.fotosService = fotosService;
    }

    public List<Gato> buscarPeloTipoECategoriaDaFoto(CategoriaFoto categoriaFoto, TipoFoto tipoFoto) {
        List<CatFotoDTO> fotos = fotosService.buscarFotosPeloCategoria(categoriaFoto);

        List<Gato> listaDeGatos = new ArrayList<>();
        fotos.forEach(foto -> {
            Foto fotoTipado = Foto.builder().urlFoto(foto.getUrl()).tipoFoto(tipoFoto).build();
            Raca raca = foto.getRaca() != null && foto.getRaca().size() > 0 ? foto.getRaca().get(0) : null;

            Gato gato = Gato.builder().raca(raca).fotos(Arrays.asList(fotoTipado)).build();
            listaDeGatos.add(gato);
        });

        return listaDeGatos;
    }

}
