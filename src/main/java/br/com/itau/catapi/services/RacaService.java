package br.com.itau.catapi.services;

import br.com.itau.catapi.beans.Raca;
import br.com.itau.catapi.repositories.RacasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RacaService {

    @Value("${url.base.cat-api}")
    private String URL_BASE;

    private final RestTemplate restTemplate;
    private RacasRepository racaRepository;

    @Autowired
    RacaService(RestTemplate restTemplate, RacasRepository racaRepository) {
        this.restTemplate = restTemplate;
        this.racaRepository = racaRepository;
    }

    public List<Raca> buscarTodos() {
        ResponseEntity<List<Raca>> forEntity = restTemplate.exchange(
                URL_BASE + "/breeds", HttpMethod.GET, null, new ParameterizedTypeReference<List<Raca>>() {
                });
        List<Raca> racas = forEntity.getBody();
        return racas;
    }


    public void salvar(List<Raca> racas) {
        racaRepository.saveAll(racas);
    }
}
