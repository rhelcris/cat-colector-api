package br.com.itau.catapi;

import br.com.itau.catapi.beans.Foto;
import br.com.itau.catapi.beans.Gato;
import br.com.itau.catapi.beans.Raca;
import br.com.itau.catapi.dto.CatFotoDTO;
import br.com.itau.catapi.enums.CategoriaFoto;
import br.com.itau.catapi.enums.TipoFoto;
import br.com.itau.catapi.services.FotosService;
import br.com.itau.catapi.services.GatosService;
import br.com.itau.catapi.services.RacaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class CatColectorApiApplication {

	private static final String URL_BASE = "https://api.thecatapi.com/v1";

	private RacaService racaService;
	private FotosService fotosService;
	private GatosService gatosService;

	@Autowired
	public CatColectorApiApplication(RacaService racaService, FotosService fotosService, GatosService gatosService) {
		this.racaService = racaService;
		this.fotosService = fotosService;
		this.gatosService = gatosService;
	}

	public static void main(String[] args) {
		SpringApplication.run(CatColectorApiApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			List<Raca> racas = racaService.buscarTodos();

			List<Gato> gatos = new ArrayList<>();
			int i = 1;
			for (Raca raca : racas) {
				System.out.println(i++ + " - " + raca);

				String racaId = raca.getId();

				// Pegar 3 fotos para cada raça
				List<Foto> fotos = fotosService.buscarFotosPelaRaca(racaId);
				Gato gato = Gato.builder().raca(raca).fotos(fotos).build();
				gatos.add(gato);
			}

			System.out.println("********************* MEUS GATOS ****************** ");
			for (Gato gato : gatos) {
				System.out.println(gato);
			}


			// Até 3 Fotos com chapéu
			List<Gato> gatosComChapeu = this.gatosService.buscarPeloTipoDaFoto(CategoriaFoto.COM_CHAPEU);
			System.out.println(">>>> GATOS COM CHAPEU: " + gatosComChapeu);


			// Até 3 Fotos com óculos
			List<Gato> gatosComOculos = this.gatosService.buscarPeloTipoDaFoto(CategoriaFoto.COM_OCULOS);
			System.out.println(">>>> GATOS COM OCULOS: " + gatosComOculos);
		};
	}

}