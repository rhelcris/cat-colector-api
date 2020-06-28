package br.com.itau.catapi;

import br.com.itau.catapi.beans.Gato;
import br.com.itau.catapi.beans.Raca;
import br.com.itau.catapi.enums.CategoriaFoto;
import br.com.itau.catapi.enums.TipoFoto;
import br.com.itau.catapi.services.GatosService;
import br.com.itau.catapi.services.RacaService;
import org.apache.catalina.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.sql.SQLException;
import java.util.List;

@SpringBootApplication
public class CatColectorApiApplication {

	private RacaService racaService;
	private GatosService gatosService;

	@Autowired
	public CatColectorApiApplication(RacaService racaService, GatosService gatosService) {
		this.racaService  = racaService;
		this.gatosService = gatosService;
	}

	public static void main(String[] args) {
		SpringApplication.run(CatColectorApiApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			List<Raca> racas = importarRacas();

			importarGatosComTresFotos(gatosService.gerarGatosComTresFotosPorCadaRaca(racas), ">>>> GATOS COM FOTOS NORMAIS: ");

			// Até 3 Fotos com chapéu
			importarGatosComTresFotos(this.gatosService.buscarPeloTipoECategoriaDaFoto(CategoriaFoto.COM_CHAPEU, TipoFoto.FOTO_CHAPEU), ">>>> GATOS COM CHAPEU: ");

			// Até 3 Fotos com óculos
			importarGatosComTresFotos(this.gatosService.buscarPeloTipoECategoriaDaFoto(CategoriaFoto.COM_OCULOS, TipoFoto.FOTO_OCULOS), ">>>> GATOS COM OCULOS: ");
		};
	}

	private void importarGatosComTresFotos(List<Gato> gatos, String s) {
		List<Gato> listaDeGatosComAteTresFotos = gatos;
		gatosService.salvar(listaDeGatosComAteTresFotos);
		System.out.println(s + listaDeGatosComAteTresFotos);
	}

	private List<Raca> importarRacas() {
		List<Raca> racas = racaService.buscarTodos();
		racaService.salvar(racas);
		return racas;
	}

}