package br.com.itau.catapi.services;

import br.com.itau.catapi.beans.Foto;
import br.com.itau.catapi.beans.Gato;
import br.com.itau.catapi.beans.Raca;
import br.com.itau.catapi.dto.CatFotoDTO;
import br.com.itau.catapi.enums.CategoriaFoto;
import br.com.itau.catapi.enums.TipoFoto;
import br.com.itau.catapi.repositories.GatosRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class GatosService {

    private static final Logger logger = LoggerFactory.getLogger(GatosService.class);

    private FotosService fotosService;
    private GatosRepository gatosRepository;

    @Autowired
    public GatosService(FotosService fotosService, GatosRepository gatosRepository) {
        this.fotosService = fotosService;
        this.gatosRepository = gatosRepository;
    }

    public List<Gato> buscarPeloTipoECategoriaDaFoto(CategoriaFoto categoriaFoto, TipoFoto tipoFoto) {
        logger.info("Busca gatos pela Categoria: " + categoriaFoto.getDescricao() + " e pelo Tipo de Foto: " + tipoFoto.toString());
        List<CatFotoDTO> fotos = fotosService.buscarFotosPeloCategoria(categoriaFoto);

        List<Gato> listaDeGatos = new ArrayList<>();
        fotos.forEach(foto -> {
            Foto fotoTipado = Foto.builder().id(foto.getId()).urlFoto(foto.getUrl()).tipoFoto(tipoFoto).build();
            Raca raca = foto.getRaca() != null && foto.getRaca().size() > 0 ? foto.getRaca().get(0) : null;

            Gato gato = Gato.builder().raca(raca).fotos(Arrays.asList(fotoTipado)).build();
            listaDeGatos.add(gato);
        });

        return listaDeGatos;
    }

    public Gato buscarGatoComAteTresFotosPelaRaca(Raca raca) {
        logger.info("Busca gatos com até três fotos pela raça.");
        List<Foto> fotos = fotosService.buscarFotosPelaRaca(raca.getId());
        return Gato.builder().raca(raca).fotos(fotos).build();
    }

    @Transactional
    public void salvar(List<Gato> gatos) {
        logger.info("Iniciando a gravação da lista de gatos");
        gatosRepository.saveAll(gatos);
        logger.info("Gatos foram gravados com sucesso!");
    }

    public List<Gato> gerarGatosComTresFotosPorCadaRaca(List<Raca> racas) {
        logger.info("Iniciando a geração de gatos com três fotos para cada Raça");
        List<Gato> gatos = new ArrayList<>();
        for (Raca raca : racas) {
            Gato gato = this.buscarGatoComAteTresFotosPelaRaca(raca);
            gatos.add(gato);
        }
        return gatos;
    }
}
