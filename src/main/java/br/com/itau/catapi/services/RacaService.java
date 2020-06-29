package br.com.itau.catapi.services;

import br.com.itau.catapi.beans.Raca;
import br.com.itau.catapi.repositories.RacasRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class RacaService {

    private static final Logger logger = LoggerFactory.getLogger(RacaService.class);

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
        logger.info("Iniciando a busca na API de Gatos todas as raças");
        ResponseEntity<List<Raca>> forEntity = restTemplate.exchange(
                URL_BASE + "/breeds", HttpMethod.GET, null, new ParameterizedTypeReference<List<Raca>>() {
                });
        List<Raca> racas = forEntity.getBody();
        return racas;
    }


    public void salvar(List<Raca> racas) {
        logger.info("Salvando as raças no Banco de Dados");
        racaRepository.saveAll(racas);
    }
}
