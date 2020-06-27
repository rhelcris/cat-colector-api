package br.com.itau.catapi.services;

import br.com.itau.catapi.beans.Foto;
import br.com.itau.catapi.dto.CatFotoDTO;
import br.com.itau.catapi.enums.CategoriaFoto;
import br.com.itau.catapi.enums.TipoFoto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FotosService {

    @Value("${url.base.cat-api}")
    private String URL_BASE;

    private final RestTemplate restTemplate;

    public FotosService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Foto> buscarFotosPelaRaca(String racaId) {
        String URL_FOTOS = URL_BASE + "/images/search?limit=3&breed_ids=" + racaId;
        ResponseEntity<List<Foto>> forEntity = restTemplate.exchange(
                URL_FOTOS, HttpMethod.GET, null, new ParameterizedTypeReference<List<Foto>>() {
                });
        List<Foto> fotos = forEntity.getBody();
        fotos.forEach(foto -> foto.setTipoFoto(TipoFoto.FOTO));
        return fotos;
    }


    public List<CatFotoDTO> buscarFotosPeloCategoria(CategoriaFoto categoriaFoto) {
        String URL_FOTOS = URL_BASE + "/images/search?limit=3&category_ids=" + categoriaFoto.getId().toString() + "&order=ASC";
        ResponseEntity<List<CatFotoDTO>> fotosReponse = restTemplate.exchange(
                URL_FOTOS, HttpMethod.GET, null, new ParameterizedTypeReference<List<CatFotoDTO>>() {
                });
        List<CatFotoDTO> fotos = fotosReponse.getBody();

        return fotos;
    }

//			fotosComChapeu.forEach(foto -> {
//        Foto fotoComChapeu = Foto.builder().urlFoto(foto.getUrl()).tipoFoto(TipoFoto.FOTO_CHAPEU).build();
//        Raca raca = foto.getRaca() != null && foto.getRaca().size() > 0 ? foto.getRaca().get(0) : null;
//
//        Gato gato = Gato.builder().raca(raca).fotos(Arrays.asList(fotoComChapeu)).build();
//        gatosComChapeu.add(gato);
//    });

}
