package br.com.itau.catapi.services;

import br.com.itau.catapi.beans.Raca;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class RacaService {

    private final RestTemplate restTemplate;

    @Value("${url.base.cat-api}")
    private String URL_BASE;

    RacaService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Raca> buscarTodos() {
        ResponseEntity<List<Raca>> forEntity = restTemplate.exchange(
                URL_BASE + "/breeds", HttpMethod.GET, null, new ParameterizedTypeReference<List<Raca>>() {
                });
        List<Raca> racas = forEntity.getBody();
        return racas;
    }

}
