package br.com.itau.catapi.repositories;

import br.com.itau.catapi.beans.Gato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GatosRepository extends JpaRepository<Gato, Long> {
}
