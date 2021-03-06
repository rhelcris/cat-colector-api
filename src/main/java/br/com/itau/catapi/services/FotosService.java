package br.com.itau.catapi.services;

import br.com.itau.catapi.beans.Foto;
import br.com.itau.catapi.dto.CatFotoDTO;
import br.com.itau.catapi.enums.CategoriaFoto;
import br.com.itau.catapi.enums.TipoFoto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FotosService {

    private static final Logger logger = LoggerFactory.getLogger(FotosService.class);

    @Value("${url.base.cat-api}")
    private String URL_BASE;

    private final RestTemplate restTemplate;

    public FotosService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Foto> buscarFotosPelaRaca(String racaId) {
        logger.info("Buscar na API de Gatos fotos pelo ID da raça: " + racaId);
        String URL_FOTOS = URL_BASE + "/images/search?limit=3&breed_ids=" + racaId;
        ResponseEntity<List<Foto>> forEntity = restTemplate.exchange(
                URL_FOTOS, HttpMethod.GET, null, new ParameterizedTypeReference<List<Foto>>() {
                });
        List<Foto> fotos = forEntity.getBody();
        fotos.forEach(foto -> foto.setTipoFoto(TipoFoto.FOTO));
        return fotos;
    }


    public List<CatFotoDTO> buscarFotosPeloCategoria(CategoriaFoto categoriaFoto) {
        logger.info("Buscar na API de Gatos três fotos pela Categoria: " + categoriaFoto);
        String URL_FOTOS = URL_BASE + "/images/search?limit=3&category_ids=" + categoriaFoto.getId().toString() + "&order=ASC";
        ResponseEntity<List<CatFotoDTO>> fotosReponse = restTemplate.exchange(
                URL_FOTOS, HttpMethod.GET, null, new ParameterizedTypeReference<List<CatFotoDTO>>() {
                });
        List<CatFotoDTO> fotos = fotosReponse.getBody();

        return fotos;
    }

}
