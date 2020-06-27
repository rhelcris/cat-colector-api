package br.com.itau.catapi;

import br.com.itau.catapi.beans.Foto;
import br.com.itau.catapi.beans.Gato;
import br.com.itau.catapi.beans.Raca;
import br.com.itau.catapi.enums.TipoFoto;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class CatColectorApiApplication {

	private static final String URL_BASE = "https://api.thecatapi.com/v1";

	public static void main(String[] args) {
		SpringApplication.run(CatColectorApiApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			ResponseEntity<List<Raca>> forEntity = restTemplate.exchange(
					URL_BASE + "/breeds", HttpMethod.GET, null, new ParameterizedTypeReference<List<Raca>>() {
					});

			System.out.println(forEntity.getBody());

			List<Gato> gatos = new ArrayList<>();

			int i = 1;
			for (Raca raca : forEntity.getBody()) {
				System.out.println(i++ + " - " + raca);

				List<Foto> fotosDoGato = new ArrayList<>();
				String racaId = raca.getId();

				// Pegar 3 fotos
				String URL_FOTOS = URL_BASE + "/images/search?limit=3&breed_ids=" + racaId;
				ResponseEntity<List<Foto>> fotosResponse = restTemplate.exchange(
						URL_FOTOS, HttpMethod.GET, null, new ParameterizedTypeReference<List<Foto>>() {
						});
				List<Foto> fotos = fotosResponse.getBody();
				fotos.forEach(foto -> foto.setTipoFoto(TipoFoto.FOTO));
				fotosDoGato.addAll(fotos);


				System.out.println(raca);

				Gato gato = Gato.builder().raca(raca).fotos(fotosDoGato).build();
				gatos.add(gato);
			}

			System.out.println("********************* MEUS GATOS ****************** ");
			for (Gato gato : gatos) {
				System.out.println(gato);
			}

		};
	}

}